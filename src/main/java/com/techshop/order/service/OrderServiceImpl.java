package com.techshop.order.service;

import com.techshop.common.util.AdjusterUtils;
import com.techshop.order.dto.order.CreateOrderDetailDto;
import com.techshop.order.dto.order.OrderInfo;
import com.techshop.order.dto.order.OrderWithNoneAccountDto;
import com.techshop.order.dto.order.UpdateOrderDto;
import com.techshop.order.entity.*;
import com.techshop.order.repository.OrderDetailRepository;
import com.techshop.order.repository.OrderRepository;

import com.techshop.product.converter.ProductConverter;
import com.techshop.product.entity.Variant;
import com.techshop.product.service.ProductService;
import com.techshop.product.service.VariantService;
import com.techshop.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private  OrderRepository repository;
    private  OrderDetailRepository orderDetailRepository;
    private  VariantService variantService;
    private  VoucherService voucherService;
    private  UserService userService;
    private  ProductService productService;
    private  ProductConverter converter;

    public OrderServiceImpl(ProductConverter converter, OrderRepository repository, OrderDetailRepository orderDetailRepository,VariantService variantService, VoucherService voucherService, UserService userService, ProductService productService) {
        this.repository = repository;
        this.orderDetailRepository = orderDetailRepository;
        this.variantService = variantService;
        this.voucherService = voucherService;
        this.userService = userService;
        this.productService = productService;
        this.converter =  converter;
    }

    @Override
    public List<Order> getOrders() {
        List<Order> orders = repository.findAllByIsDeletedFalse();

        return orders.stream().filter(o -> !o.getOrderStatus().equals(OrderStatus.PUTTING)).collect(Collectors.toList());
    }

    @Override
    public Order getOrder(Long orderId) {
        Optional<Order> order = repository.findById(orderId);

        if (!order.isPresent()) {
            throw new IllegalStateException("Order not exists");
        }

        return order.get();
    }

    public Order createCart() {
        Order order = new Order();

        order.setOrderStatus(OrderStatus.PUTTING);
        order.setPaymentStatus(PaymentStatus.UNPAID);
        order.setUser(userService.getProfile());

        return repository.save(order);
    }

    @Override
    public Order addCartItem(CreateOrderDetailDto dto) {
        List<Order> orderList = repository.findByUser(userService.getProfile());
        Optional<Order> cartOptional = orderList.stream().filter(o -> o.getOrderStatus().equals(OrderStatus.PUTTING)).findFirst();

        Order cart = cartOptional.orElseGet(this::createCart);

        Optional<OrderDetail> orderDetailOptional = orderDetailRepository.findById(new OrderDetailPK(cart.getOrderId(), dto.getVariantId()));

        if (orderDetailOptional.isPresent()) {
            OrderDetail orderDetail = orderDetailOptional.get();
            orderDetail.setQuantity(dto.getQuantity());
            orderDetail.setUnitPrice(orderDetail.getVariant().getPrice());

            cart.getOrderDetails().removeIf(o -> Objects.equals(o.getVariant().getVariantId(), dto.getVariantId()));

            cart.getOrderDetails().add(orderDetail);

            return repository.save(cart);
        }

        OrderDetail orderDetail = new OrderDetail();
        Variant variant = variantService.getById(dto.getVariantId());
        orderDetail.setOrder(cart);
        orderDetail.setVariant(variant);
        orderDetail.setQuantity(dto.getQuantity());
        orderDetail.setUnitPrice(variant.getPrice());

        cart.getOrderDetails().add(orderDetail);

        return repository.save(cart);
    }

    @Override
    public Order deleteCartItem(Long variantId) {
        List<Order> orderList = repository.findByUser(userService.getProfile());
        Optional<Order> cartOptional = orderList.stream().filter(o -> o.getOrderStatus().equals(OrderStatus.PUTTING)).findFirst();

        if (!cartOptional.isPresent()) {
            throw new IllegalStateException("Cart not found");
        }

        Order cart = cartOptional.get();

        Optional<OrderDetail> orderDetailOptional = orderDetailRepository.findById(new OrderDetailPK(cartOptional.get().getOrderId(), variantId));

        if (!orderDetailOptional.isPresent()) {
            throw new IllegalStateException("Cart item not found");
        }

        OrderDetail orderDetail = orderDetailOptional.get();

        cart.getOrderDetails().remove(orderDetail);

        orderDetailRepository.delete(orderDetail);
        return repository.save(cart);
    }

    @Override
    public Order addVoucher(String voucherName) {
        List<Order> orderList = repository.findByUser(userService.getProfile());
        Optional<Order> cartOptional = orderList.stream().filter(o -> o.getOrderStatus().equals(OrderStatus.PUTTING)).findFirst();

        Order cart = cartOptional.orElseGet(this::createCart);

        Voucher voucher = voucherService.getVoucherByName(voucherName);

        cart.setVoucher(voucher);

        return repository.save(cart);
    }

    @Override
    public Order removeVoucher() {
        List<Order> orderList = repository.findByUser(userService.getProfile());
        Optional<Order> cartOptional = orderList.stream().filter(o -> o.getOrderStatus().equals(OrderStatus.PUTTING)).findFirst();

        Order cart = cartOptional.orElseGet(this::createCart);

        cart.setVoucher(null);

        return repository.save(cart);
    }

    @Override
    public List<Order> getYourOrders() {
        return repository.findByUser(userService.getProfile()).stream().filter(o -> !o.getOrderStatus().equals(OrderStatus.PUTTING)).collect(Collectors.toList());
    }

    @Override
    public Order getYourCart() {
        List<Order> orderList = repository.findByUser(userService.getProfile());
        Optional<Order> cartOptional = orderList.stream().filter(o -> o.getOrderStatus().equals(OrderStatus.PUTTING)).findFirst();

        Order cart = cartOptional.orElse(null);

        if (cart == null) {
            throw new IllegalStateException("Cart not found!");
        }

        return cart;
    }

    @Override
    public Order checkout() {
        Order cart = getYourCart();

        if (cart.getVoucher() != null) {
            Integer voucherAmount = cart.getVoucher().getAmount();
            cart.getVoucher().setAmount(voucherAmount - 1);
        }

        cart.getOrderDetails().forEach(detail ->
                variantService.handleQuantity(detail.getVariant().getVariantId(), detail.getQuantity(), "sub")
        );

        cart.setOrderStatus(OrderStatus.PENDING);
        cart.setCreatedAt(LocalDateTime.now());

        return repository.save(cart);
    }

    @Override
    public Order changeOrderStatus(UpdateOrderDto dto) {
        Order order = getOrder(dto.getOrderId());

        if(dto.getOrderStatus() == OrderStatus.CANCELED){
            if(order.getOrderStatus() == OrderStatus.PENDING) {
                order.getOrderDetails().forEach(detail ->
                        variantService.handleQuantity(detail.getVariant().getVariantId(), detail.getQuantity(), "add")
                );
            }
        }

        order.setOrderStatus(dto.getOrderStatus());
        order.setPaymentStatus(dto.getPaymentStatus());


        return repository.save(order);
    }

    @Override
    public Map<LocalDate, List<Order>> getOrderReport(LocalDate start, LocalDate end, String compression) {
        return repository.findByCreatedAtBetweenOrderByCreatedAt(start.atStartOfDay(), end.atStartOfDay())
                .stream().filter(o -> o.getPaymentStatus().equals(PaymentStatus.PAID))
                .collect(Collectors.groupingBy(item -> item.getCreatedAt().toLocalDate()
                        .with(AdjusterUtils.getAdjuster().get(compression))));
    }

    @Override
    public Object getBestSeller() {
        List<OrderDetail> orderDetails = orderDetailRepository.findAll().stream().filter(o -> o.getOrder().getOrderStatus().equals(OrderStatus.COMPLETED)).collect(Collectors.toList());
        List<Map<String, Object>> sold = new ArrayList<>();
        LinkedHashMap<Long, Integer> sorted = new LinkedHashMap<>();

        Map<Long, Integer> calc = orderDetails.stream().collect(Collectors
                .groupingBy(o -> o.getVariant().getProduct().getProductId(),
                        Collectors.summingInt(OrderDetail::getQuantity)));

        calc.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).forEachOrdered(x -> sorted.put(x.getKey(), x.getValue()));
        sorted.forEach((aLong, integer) -> {
            Map<String, Object> temp = new HashMap<>();

            temp.put("product",converter.toProductWithVariant(productService.getProductById(aLong)));
            temp.put("quantity_sold", integer);

            sold.add(temp);
        });

        return sold;
    }

    @Override
    public Boolean deleteOrder(Long orderId) {
        Order order = getOrder(orderId);

        order.setIsDeleted(true);
        repository.save(order);

        return true;
    }

    @Override
    public void updateInfoCheckOut(OrderInfo orderInfo) {
        List<Order> orderList = repository.findByUser(userService.getProfile());
        Order order = orderList.stream().filter(o -> o.getOrderStatus().equals(OrderStatus.PUTTING)).findFirst().get();

        order.setDeliveryAddress(orderInfo.getDeliveryAddress());
        order.setRecipientName(orderInfo.getRecipientName());
        order.setPhoneNumber(orderInfo.getPhoneNumber());

        repository.save(order);
    }

    @Transactional
    @Override
    public Order checkoutWithNoneAccount(OrderWithNoneAccountDto dto) {
        Order order = new Order();
        order.setDeliveryAddress(dto.getDeliveryAddress());
        order.setPhoneNumber(dto.getPhoneNumber());
        order.setRecipientName(dto.getRecipientName());
        order.setPaymentStatus(PaymentStatus.UNPAID);
        order.setOrderStatus(OrderStatus.PENDING);

        Set<OrderDetail> orderDetail = new HashSet<>();
        dto.getOrderDetail().forEach(item -> {
            OrderDetail detail = new OrderDetail();
            Variant variant = variantService.getById(item.getVariantId());
            detail.setVariant(variant);
            detail.setQuantity(item.getQuantity());
            detail.setUnitPrice(variant.getPrice());
            detail.setOrder(order);

            orderDetail.add(detail);
        });

        order.setOrderDetails(orderDetail);
        order.setCreatedAt(LocalDateTime.now());

        order.getOrderDetails().forEach(detail ->
                variantService.handleQuantity(detail.getVariant().getVariantId(), detail.getQuantity(), "sub")
        );


        return repository.save(order);
    }
}
