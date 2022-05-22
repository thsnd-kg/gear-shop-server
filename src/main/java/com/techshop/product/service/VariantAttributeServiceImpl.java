package com.techshop.product.service;

import com.techshop.product.entity.VariantAttribute;
import com.techshop.product.key.VariantAttributeKey;
import com.techshop.product.repository.VariantAttributeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VariantAttributeServiceImpl implements VariantAttributeService {
    private VariantAttributeRepository repository;

    public VariantAttributeServiceImpl(VariantAttributeRepository repository){
        this.repository = repository;
    }

    @Override
    public VariantAttribute getVariantAttributeById(Long variantId, Long attributeId) {
        VariantAttributeKey key = new VariantAttributeKey(variantId, attributeId);
        Optional<VariantAttribute> result = repository.findById(key);

        if(!result.isPresent())
            throw new IllegalStateException("VariantAttribute is not exists");

        return result.get();
    }
}
