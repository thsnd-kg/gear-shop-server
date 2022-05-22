package com.techshop.product.dto.attribute;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateVariantAttributeDto {
    private Long attributeId;
    private String value;
    private String description;
}
