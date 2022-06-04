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
        result.setUsername(order.getUser().getUsername());
        result.setVoucher(
                order.getVoucher() == null
                ? null
                : new GetVoucherDto(order.getVoucher()));
        result.setTotalPrice(order.getTotalPrice());
        result.setCreatedAt(order.getCreatedAt());

        List<GetOrderDetailDto> orders = new ArrayList<>();
        order.getOrderDetails().forEach(item -> {
            GetOrderDetailDto detailDto = new GetOrderDetailDto();
            detailDto.setQuantity(item.getQuantity());
            detailDto.setVariant(service.getVariantDetailById(item.getVariant().getVariantId()));
            orders.add(detailDto);
        });

        result.setOrderDetails(orders);

        return result;
    }
}
