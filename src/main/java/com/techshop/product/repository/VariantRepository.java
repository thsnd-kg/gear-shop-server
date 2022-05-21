package com.techshop.product.repository;

import com.techshop.product.entity.Variant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VariantRepository extends JpaRepository<Variant, Long> {
    @Query("SELECT v FROM Variant v JOIN v.product p WHERE p.productId = :productId")
    List<Variant> findByProductId(Long productId);
}
