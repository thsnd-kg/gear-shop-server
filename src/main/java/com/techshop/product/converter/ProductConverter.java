package com.techshop.product.converter;

import com.techshop.product.dto.product.ProductWithVariantDto;
import com.techshop.product.dto.variant.VariantWithAttributesDto;
import com.techshop.product.entity.Product;
import com.techshop.product.entity.Variant;
import com.techshop.product.service.VariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductConverter {
    private VariantService variantService;
    private ProductConverter(@Lazy VariantService variantService){
        this.variantService = variantService;
    }

//    private static final ProductConverter instance = new ProductConverter();
//    public static ProductConverter getInstance(){
//        return  instance;
//    }

    public ProductWithVariantDto toProductWithVariant(Product product){
        ProductWithVariantDto result = new ProductWithVariantDto();

        result.setProductId(product.getProductId());
        result.setProductName( product.getProductName());
        result.setCategory(product.getCategory());
        result.setBrand( product.getBrand());
        result.setProductDesc(product.getProductDesc());
        result.setImgUrl(product.getImgUrl());
        result.setProductLink(product.getProductLink());

        List<VariantWithAttributesDto> variants = variantService.getByProductId(product.getProductId());
        result.setVariants(variants);
        result.setTotalVariant(variants.size());

        return result;
    }
}
