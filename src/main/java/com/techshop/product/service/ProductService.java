package com.techshop.product.service;

import com.techshop.product.dto.product.ProductDetailDto;
import com.techshop.product.dto.product.ProductDto;
import com.techshop.product.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();
    List<Product> getAll();
    Product getProductById(Long id);
    Product createProduct(ProductDto dto);
    Product updateProduct(ProductDto dto);
    Boolean deleteProduct(Long productId);
//    ProductDetailDto getVariantsByProductId(Long productId);
}
