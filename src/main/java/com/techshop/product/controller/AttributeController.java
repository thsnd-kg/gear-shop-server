package com.techshop.product.controller;

import com.techshop.common.ResponseHandler;
import com.techshop.product.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/attributes")
public class AttributeController {
    private AttributeService service;

    public AttributeController(AttributeService service){
        this.service= service;
    }

    @GetMapping
    public Object getAll() {
        return ResponseHandler.getResponse(service.getAll(), HttpStatus.OK);
    }
}
