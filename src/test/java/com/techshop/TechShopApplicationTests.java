package com.techshop;

import com.techshop.product.entity.Product;
import com.techshop.product.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TechShopApplicationTests {

    @Autowired
    private ProductService service;

    @Test
    void contextLoads() {
        Product product = new Product();
        product.setName("Alibaba");
        product.setDescription("123123");
        service.createProduct(product);
    }

}
