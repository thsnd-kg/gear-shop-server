package com.techshop.product.dto.variant;
import com.techshop.product.dto.attribute.AttributeDto;
import com.techshop.product.entity.Variant;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class VariantWithAttributesDto {

    private Long variantId;
    private String sku;
    private String variantName;
    private String variantDesc;
    private String imgUrl;
    private Long price;
    private String imgUrl;
    private List<AttributeDto> attributes;

    public VariantWithAttributesDto(Variant variant){
        this.variantId = variant.getVariantId();
        this.sku = variant.getSku();
        this.variantName = variant.getVariantName();
        this.variantDesc = variant.getVariantDesc();
        this.imgUrl = variant.getImgUrl();
        this.price = variant.getPrice();
        this.attributes = variant.getAttributes().isEmpty() ? new ArrayList<>() :  variant.getAttributes().stream().map(AttributeDto::new).collect(Collectors.toList());
    }
}
