package com.techshop.product.service;

import com.techshop.product.dto.AttributeDto;
import com.techshop.product.dto.CategoryDto;
import com.techshop.product.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getCategories();
    List<Category> getCategoriesActive();
    Category getCategoryById(Long categoryId);
    Boolean deleteCategory(Long categoryId);
    Category createCategory(CategoryDto dto);
    Category updateCategory(CategoryDto updatedCategory);
    boolean isExisted(Long categoryId);

    boolean removeAttributes(CategoryDto dto);
}
