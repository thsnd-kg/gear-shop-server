package com.techshop.product.service;

import com.techshop.product.dto.product.ProductDetailDto;
import com.techshop.product.dto.product.ProductDto;
import com.techshop.product.dto.product.ProductWithVariantDto;
import com.techshop.product.entity.Product;
import com.techshop.product.search.ProductSearchCriteria;

import java.util.List;

public interface ProductService {
    List<ProductWithVariantDto> getProducts();
    Object getAll(ProductSearchCriteria searchCriteria);
    Product getProductById(Long id);
    ProductWithVariantDto createProduct(ProductDto dto);
    ProductWithVariantDto updateProduct(ProductDto dto);
    Boolean deleteProduct(Long productId);
//    ProductDetailDto getVariantsByProductId(Long productId);
    ProductWithVariantDto getProductDetailById(Long productId);
    ProductWithVariantDto getByProductLink(String productLink);

    List<ProductWithVariantDto> getProductByCategoryLink(String categoryLink);
}
