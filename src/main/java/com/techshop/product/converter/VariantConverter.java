package com.techshop.product.converter;

import com.techshop.product.dto.attribute.AttributeDto;
import com.techshop.product.dto.attribute.AttributeWithTagDto;
import com.techshop.product.dto.variant.VariantWithAttributesDto;
import com.techshop.product.entity.Variant;
import com.techshop.product.service.AttributeService;
import com.techshop.product.service.VariantService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class VariantConverter {
    private AttributeService attributeService;

    private VariantConverter(AttributeService attributeService){
        this.attributeService = attributeService;
    }

    public VariantWithAttributesDto toVariantWithAttributes(Variant variant) {
        VariantWithAttributesDto result = new VariantWithAttributesDto();

        result.setVariantId(variant.getVariantId());
        result.setSku(variant.getSku());
        result.setVariantName(variant.getVariantName());
        result.setVariantDesc(variant.getVariantDesc());
        result.setImgUrl(variant.getImgUrl());
        result.setPrice(variant.getPrice());

        List<AttributeDto> attributes = variant.getAttributes().isEmpty()
                                        ? new ArrayList<>()
                                        : variant.getAttributes().stream().map(AttributeDto::new).collect(Collectors.toList());

        result.setAttributes(attributes);
        return result;
    }
}
