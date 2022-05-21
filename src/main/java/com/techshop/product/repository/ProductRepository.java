package com.techshop.product.repository;

import com.techshop.product.dto.ProductDetailDto;
import com.techshop.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.activeFlag = :activeFlag")
    List<Product> findByActiveFlag(String activeFlag);

    @Query("SELECT p FROM Product p WHERE p.activeFlag = 'Y' ")
    ProductDetailDto getProductDetail(Long productId);
}
