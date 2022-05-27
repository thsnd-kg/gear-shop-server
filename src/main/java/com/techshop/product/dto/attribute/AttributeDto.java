package com.techshop.product.dto.attribute;

import com.techshop.product.entity.Variant;
import com.techshop.product.entity.VariantAttribute;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
public class AttributeDto {
    private Long attributeId;
    private String attributeName;
    private String description;
    private String value;
    private String attributeIcon;
    private String tagName;
    private Long tagId;

    public AttributeDto(VariantAttribute variantAttribute){
        this.attributeId = variantAttribute.getAttribute().getAttributeId();
        this.attributeIcon = variantAttribute.getAttribute().getAttributeIcon();
        this.attributeName = variantAttribute.getAttribute().getAttributeName();
        this.description = variantAttribute.getDescription();
        this.value = variantAttribute.getValue();

        if(variantAttribute.getTag() !=null){
            this.tagId = variantAttribute.getTag().getTagId();
            this.tagName = variantAttribute.getTag().getTagName();
        }

    }


}
