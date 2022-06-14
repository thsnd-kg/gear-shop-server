package com.techshop.order.dto.order;

import com.techshop.order.entity.OrderDetail;

import com.techshop.product.dto.variant.VariantWithAttributesDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetOrderDetailDto {
    private  VariantWithAttributesDto variant;
    private  Integer quantity;
    private Long unitPrice;
}
