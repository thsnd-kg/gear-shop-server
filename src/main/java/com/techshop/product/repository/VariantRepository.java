package com.techshop.product.repository;

import com.techshop.product.entity.Variant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VariantRepository extends JpaRepository<Variant, Long> {
    @Query("SELECT v FROM Variant v WHERE v.product.productId = :productId AND v.activeFlag = :activeFlag")
    List<Variant> findByProductIdAndActiveFlag(Long productId, String activeFlag);
}
