package com.techshop.product.repository;

import com.techshop.product.entity.VariantAttribute;
import com.techshop.product.key.VariantAttributeKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VariantAttributeRepository extends JpaRepository<VariantAttribute, VariantAttributeKey> {

}
