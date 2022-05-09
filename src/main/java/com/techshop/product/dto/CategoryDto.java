package com.techshop.product.dto;

import com.techshop.product.entity.Attribute;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CategoryDto {
    private Long categoryId;

    @NotEmpty
    @NotBlank
    private String categoryName;

    private String description;
    private Long parentId;

    private List<AttributeDto> attributes = new ArrayList<>();
}
