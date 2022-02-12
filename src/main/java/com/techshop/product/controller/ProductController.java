package com.techshop.product.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @GetMapping
    public Object findAllProduct() {
        boolean test = true;

        return new ResponseEntity<>(test, HttpStatus.OK);
    }

}
