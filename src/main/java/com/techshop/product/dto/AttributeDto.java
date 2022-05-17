package com.techshop.product.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AttributeDto {
    private Long attributeId;

    @NotNull
    @NotEmpty
    private String attributeName;
}
