package com.techshop.product.service;

import com.techshop.product.entity.Product;
import com.techshop.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepo;

    @Override
    public Product createProduct(Product product) {
        return productRepo.save(product);
    }
}
