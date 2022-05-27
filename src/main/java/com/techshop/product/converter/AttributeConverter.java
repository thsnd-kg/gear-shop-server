package com.techshop.product.converter;

import com.techshop.product.dto.attribute.AttributeWithTagDto;
import com.techshop.product.dto.product.ProductWithVariantDto;
import com.techshop.product.dto.variant.VariantWithAttributesDto;
import com.techshop.product.entity.Attribute;
import com.techshop.product.entity.Product;
import com.techshop.product.entity.Tag;
import com.techshop.product.entity.VariantAttribute;
import com.techshop.product.service.AttributeService;
import com.techshop.product.service.VariantService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AttributeConverter {
    private AttributeService attributeService;
    private AttributeConverter(@Lazy AttributeService service){
        this.attributeService = service;
    }

//    private static final ProductConverter instance = new ProductConverter();
//    public static ProductConverter getInstance(){
//        return  instance;
//    }

//    public AttributeWithTagDto toAttributeWithTagDto(VariantAttribute variantAttribute){
//        AttributeWithTagDto result = new AttributeWithTagDto();
//
//
//        result.setAttributeId(variantAttribute.getAttribute().getAttributeId());
//        result.setAttributeName( variantAttribute.getAttribute().getAttributeName());
//        result.setDescription(variantAttribute.getDescription());
//        result.setValue( variantAttribute.getValue());
//
//        Tag tag = attributeService.getTagByAttributeId(variantAttribute.getAttribute().getAttributeId());
//        result.setTagId(tag.getTagId());
//        result.setTagName(tag.getTagName());
//
//        return result;
//    }
}