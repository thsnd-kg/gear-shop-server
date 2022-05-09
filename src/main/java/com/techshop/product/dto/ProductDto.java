package com.techshop.product.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private Long productId;
    private Long brandId;
    private Long categoryId;
    private String productName;
}

