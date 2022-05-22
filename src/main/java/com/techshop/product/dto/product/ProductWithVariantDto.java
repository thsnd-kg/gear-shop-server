package com.techshop.product.dto.product;


import com.techshop.product.dto.variant.VariantWithAttributesDto;
import com.techshop.product.entity.Brand;
import com.techshop.product.entity.Category;
import com.techshop.product.entity.Product;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ProductWithVariantDto {
    private Long productId;
    private Brand brand;
    private Category category;
    private String productName;
    private String productDesc;
    private String imgUrl;
    private List<VariantWithAttributesDto> variants;

//    public ProductWithVariantDto(Product product) {
//        this.productId = product.getProductId();
//        this.productName = product.getProductName();
//        this.category = product.getCategory();
//        this.brand = product.getBrand();
//        this.productDesc = product.getProductDesc();
//        this.imgUrl = product.getImgUrl();
//        this.variants = product.getVariants().isEmpty()
//                ? new ArrayList<>()
//                : product.getVariants().stream().map(VariantWithAttributesDto::new)
//                .collect(Collectors.toList());
//    }
}
