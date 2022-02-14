package com.techshop.product.controller;

import com.techshop.common.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @GetMapping
    public Object findAllProduct() {
        Map<String, Object> map = new HashMap<>();
        map.put("Name", "aliababa");

        return ResponseHandler.getResponse(map, HttpStatus.OK);
    }

}
