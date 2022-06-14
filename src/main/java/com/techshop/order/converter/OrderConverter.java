package com.techshop.order.converter;

import com.techshop.order.dto.order.GetOrderDetailDto;
import com.techshop.order.dto.order.GetOrderDto;
import com.techshop.order.dto.voucher.GetVoucherDto;
import com.techshop.order.entity.Order;
import com.techshop.product.service.VariantService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderConverter {
    private VariantService service;

    public OrderConverter(@Lazy VariantService service){
        this.service = service;
    }

    public GetOrderDto toGetOrderDto(Order order){
        GetOrderDto result = new GetOrderDto();
        result.setOrderId(order.getOrderId());
        result.setOrderStatus(order.getOrderStatus());
        result.setPaymentStatus(order.getPaymentStatus());
        if(order.getUser() !=null )
             result.setUsername(order.getUser().getUsername());

        result.setVoucher(
                order.getVoucher() == null
                ? null
                : new GetVoucherDto(order.getVoucher()));
        result.setTotalPrice(order.getTotalPrice());
        result.setCreatedAt(order.getCreatedAt());
        result.setDeliveryAddress(order.getDeliveryAddress());
        result.setRecipientName(order.getRecipientName());
        order.setPhoneNumber(order.getPhoneNumber());

        List<GetOrderDetailDto> orders = new ArrayList<>();
        order.getOrderDetails().forEach(item -> {
            GetOrderDetailDto detailDto = new GetOrderDetailDto();
            detailDto.setQuantity(item.getQuantity());
            detailDto.setUnitPrice(item.getUnitPrice());
            detailDto.setVariant(service.getVariantDetailById(item.getVariant().getVariantId()));
            orders.add(detailDto);
        });

        if (result.getVoucher() != null){
            Long discountPrice = order.getTotalPrice() * order.getVoucher().getVoucherValue() / 100;
            if(discountPrice > order.getVoucher().getCappedAt())
                result.setDiscountPrice(order.getVoucher().getCappedAt());
            else
                result.setDiscountPrice(discountPrice);
        }


        result.setOrderDetails(orders);

        return result;
    }
}
