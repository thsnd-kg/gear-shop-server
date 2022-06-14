package com.techshop.order.dto.order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderInfo {
    private String deliveryAddress;
    private String phoneNumber;
    private String recipientName;
}
