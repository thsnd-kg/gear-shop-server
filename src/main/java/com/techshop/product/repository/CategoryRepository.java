package com.techshop.product.repository;

import com.techshop.product.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByActiveFlag(String isActive);
    Category findByCategoryLink(String categoryLink);
}
