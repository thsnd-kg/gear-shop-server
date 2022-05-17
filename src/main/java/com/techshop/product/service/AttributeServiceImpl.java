package com.techshop.product.service;

import com.techshop.product.dto.AttributeDto;
import com.techshop.product.dto.CategoryDto;
import com.techshop.product.entity.Attribute;
import com.techshop.product.entity.Category;
import com.techshop.product.repository.AttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttributeServiceImpl implements AttributeService{
    private final AttributeRepository repository;

    @Autowired
    public AttributeServiceImpl(AttributeRepository repository) {
        this.repository = repository;
;
    }

    private Attribute createAttribute(Long categoryId, AttributeDto dto){
        Attribute attribute = new Attribute();
        attribute.setAttributeName(dto.getAttributeName());
        attribute.setCategoryId(categoryId);
        return repository.save(attribute);
    }


    @Override
    public boolean removeAttribute(Long attributeId) {
        return false;
    }

    @Override
    public List<Attribute> addAttributesToCategory(Long categoryId, List<AttributeDto> attributes) {
        attributes.forEach(attribute -> createAttribute(categoryId, attribute));
        return repository.findAttributeByCategoryId(categoryId);
    }



}
