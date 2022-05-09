package com.techshop.product.service;

import com.techshop.product.dto.ProductDto;
import com.techshop.product.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();
    Product getProductById(Long id);
    Product createProduct(ProductDto dto);
    Product updateProduct(ProductDto dto);
    Boolean deteleProduct(Long productId);
}
