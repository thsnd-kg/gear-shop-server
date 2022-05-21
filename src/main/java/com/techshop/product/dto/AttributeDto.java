package com.techshop.product.dto;

import com.techshop.product.entity.Variant;
import com.techshop.product.entity.VariantAttribute;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AttributeDto {
    private Long attributeId;
    private String attributeName;
    private String description;
    private String value;
    private String attributeIcon;

    public AttributeDto(VariantAttribute variantAttribute){
        this.attributeId = variantAttribute.getAttribute().getAttributeId();
        this.attributeIcon = variantAttribute.getAttribute().getAttributeIcon();
        this.attributeName = variantAttribute.getAttribute().getAttributeName();
        this.description = variantAttribute.getDescription();
        this.value = variantAttribute.getValue();
    }


}
