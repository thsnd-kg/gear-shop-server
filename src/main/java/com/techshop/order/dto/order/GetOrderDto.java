package com.techshop.order.dto.order;

import com.techshop.order.dto.voucher.GetVoucherDto;
import com.techshop.order.entity.Order;
import com.techshop.order.entity.OrderStatus;
import com.techshop.order.entity.PaymentStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class GetOrderDto {
    private  Long orderId;
    private  OrderStatus orderStatus;
    private  PaymentStatus paymentStatus;
    private  String username;
    private  GetVoucherDto voucher;
    private  Long totalPrice;
    private  List<GetOrderDetailDto> orderDetails;
    private  LocalDateTime createdAt;
}
