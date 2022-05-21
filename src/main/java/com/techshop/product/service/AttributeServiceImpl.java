package com.techshop.product.service;

import com.techshop.product.entity.Attribute;
import com.techshop.product.repository.AttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttributeServiceImpl implements AttributeService{
    private final AttributeRepository repository;

    @Autowired
    public AttributeServiceImpl(AttributeRepository repository) {
        this.repository = repository;

    }

    @Override
    public Attribute getById(Long attributeId) {
        Optional<Attribute> attribute = repository.findById(attributeId);
        if(!attribute.isPresent())
            throw new IllegalArgumentException("Attribute with ID: " + attributeId + " is not existed");

        return attribute.get();
    }

    @Override
    public List<Attribute> getAll() {
        return repository.findAll();
    }


//    private Attribute createAttribute(Long categoryId, AttributeDto dto){
//        Attribute attribute = new Attribute();
//        attribute.setAttributeName(dto.getAttributeName());
//        attribute.setCategoryId(categoryId);
//        return repository.save(attribute);
//    }


//    @Override
//    public boolean removeAttribute(Long attributeId) {
//        return false;
//    }
//
//    @Override
//    public List<Attribute> addAttributesToCategory(Long categoryId, List<AttributeDto> attributes) {
//        attributes.forEach(attribute -> createAttribute(categoryId, attribute));
//        return repository.findAttributeByCategoryId(categoryId);
//    }



}
