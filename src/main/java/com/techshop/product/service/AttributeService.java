package com.techshop.product.service;

import com.techshop.product.entity.Attribute;

import java.util.List;

public interface AttributeService {
//    boolean removeAttribute(Long attributeId);
//    List<Attribute> addAttributesToCategory(Long categoryId, List<AttributeDto> attributes);
        Attribute getById(Long attributeId);
        List<Attribute> getAll();
}
