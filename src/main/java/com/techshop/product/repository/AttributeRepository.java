package com.techshop.product.repository;

import com.techshop.product.entity.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Long> {
//    List<Attribute> findAttributeByCategoryId(Long categoryId);
}
