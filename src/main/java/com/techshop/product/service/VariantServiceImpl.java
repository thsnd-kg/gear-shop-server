package com.techshop.product.service;

import com.techshop.product.entity.Variant;
import com.techshop.product.repository.VariantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VariantServiceImpl  implements VariantService{

    private VariantRepository repository;

    public VariantServiceImpl(VariantRepository repository){
        this.repository = repository;
    }

    @Override
    public List<Variant> getByProductId(Long productId) {
        return repository.findByProductId(productId);
    }
}
