package com.techshop.product.service;

import com.techshop.product.dto.product.ProductDetailDto;
import com.techshop.product.dto.product.ProductDto;
import com.techshop.product.dto.product.ProductWithVariantDto;
import com.techshop.product.entity.Product;

import java.util.List;

public interface ProductService {
    List<ProductWithVariantDto> getProducts();
    List<Product> getAll();
    Product getProductById(Long id);
    ProductWithVariantDto createProduct(ProductDto dto);
    ProductWithVariantDto updateProduct(ProductDto dto);
    Boolean deleteProduct(Long productId);
//    ProductDetailDto getVariantsByProductId(Long productId);
    ProductWithVariantDto getProductDetailById(Long productId);
}
