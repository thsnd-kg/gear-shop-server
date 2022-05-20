package com.techshop.product.controller;

import com.techshop.common.ResponseHandler;
import com.techshop.product.dto.ProductDto;
import com.techshop.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin
@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private  ProductService productService;

    @GetMapping
    public Object getProducts() {
        return ResponseHandler.getResponse(productService.getProducts(), HttpStatus.OK);
    }

    @GetMapping(path = "/{product-id}")
    public Object getProductById(@PathVariable("product-id") Long productId){
        return ResponseHandler.getResponse(productService.getProductById(productId), HttpStatus.OK);
    }

    @PostMapping
    public Object createProduct(@Valid @RequestBody ProductDto newProduct, BindingResult errors){
        if(errors.hasErrors())
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

        return ResponseHandler.getResponse(productService.createProduct(newProduct), HttpStatus.OK);
    }

    @PutMapping
    public Object updateProduct(@RequestBody ProductDto updatedProduct, BindingResult errors){
        if(errors.hasErrors())
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

        return ResponseHandler.getResponse(productService.updateProduct(updatedProduct), HttpStatus.OK);
    }
}
