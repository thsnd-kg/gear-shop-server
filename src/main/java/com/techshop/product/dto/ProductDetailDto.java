package com.techshop.product.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.techshop.product.entity.Brand;
import com.techshop.product.entity.Category;
import com.techshop.product.entity.Product;
import com.techshop.product.entity.Variant;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class ProductDetailDto {
    private Long productId;
    private String productName;
    private String productDesc;
    private String imgUrl;
    private String activeFlag = "Y";
    private Brand brand;
    private Category category;
    private Collection<Variant> variants = new ArrayList<>();

}
