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
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private  ProductService productService;

    @GetMapping
    public Object getProducts(@RequestParam(value = "onlyActive") Boolean isActive) {
        if(isActive)
            return ResponseHandler.getResponse(productService.getProducts(), HttpStatus.OK);

        return ResponseHandler.getResponse(productService.getAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{product-id}")
    public Object getProductById(@PathVariable("product-id") Long productId){
        try{
            return ResponseHandler.getResponse(productService.getProductById(productId), HttpStatus.OK);
        }catch (Exception e){
            return ResponseHandler.getResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(path = "/{product-id}/variants")
    public Object getProductDetail(@PathVariable("product-id") Long productId){
        try{
            return ResponseHandler.getResponse(productService.getVariantsByProductId(productId), HttpStatus.OK);
        }catch (Exception e){
            return ResponseHandler.getResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping
    public Object createProduct(@Valid @RequestBody ProductDto newProduct, BindingResult errors){
        try{
            return ResponseHandler.getResponse(productService.createProduct(newProduct), HttpStatus.OK);
        }catch (Exception e){
            return ResponseHandler.getResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public Object updateProduct(@RequestBody ProductDto updatedProduct, BindingResult errors){
        try{
            return ResponseHandler.getResponse(productService.updateProduct(updatedProduct), HttpStatus.OK);
        }catch (Exception e){
            return ResponseHandler.getResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{product-id}")
    public Object deleteProduct(@PathVariable("product-id") Long productId){
        try{
            return ResponseHandler.getResponse(productService.deleteProduct(productId), HttpStatus.OK);
        }catch (Exception e){
            return ResponseHandler.getResponse(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
