package com.techshop.order.service;

import com.techshop.order.dto.order.CreateOrderDetailDto;
import com.techshop.order.dto.order.OrderInfo;
import com.techshop.order.dto.order.OrderWithNoneAccountDto;
import com.techshop.order.dto.order.UpdateOrderDto;
import com.techshop.order.entity.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface OrderService {
    List<Order> getOrders();

    Order getOrder(Long orderId);

    Order addCartItem(CreateOrderDetailDto dto);

    Order deleteCartItem(Long variantId);

    Order addVoucher(String voucherName);

    Order removeVoucher();

    List<Order> getYourOrders();

    Order getYourCart();

    Order checkout();

    Order changeOrderStatus(UpdateOrderDto dto);

    Map<LocalDate, List<Order>> getOrderReport(LocalDate start, LocalDate end, String compression);

    Object getBestSeller();

    Boolean deleteOrder(Long orderId);

    void updateInfoCheckOut(OrderInfo orderInfo);

    Order checkoutWithNoneAccount(OrderWithNoneAccountDto dto);

    Object getRevenue();
}
