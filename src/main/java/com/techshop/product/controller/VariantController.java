package com.techshop.product.controller;

import com.techshop.common.ResponseHandler;
import com.techshop.product.dto.variant.CreateVariantDto;
import com.techshop.product.dto.variant.UpdateVariantDto;
import com.techshop.product.dto.variant.VariantWithAttributesDto;
import com.techshop.product.service.VariantService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/variants")
public class VariantController {
    private VariantService service;

    public VariantController(VariantService service){
        this.service = service;
    }

    @PostMapping()
    public Object createVariant(@Valid @RequestBody CreateVariantDto dto, BindingResult errors) {
        try {
            if (errors.hasErrors())
                return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

            VariantWithAttributesDto variant = new VariantWithAttributesDto(service.createVariant(dto));
            return ResponseHandler.getResponse(variant, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping()
    public Object createVariant(@Valid @RequestBody UpdateVariantDto dto, BindingResult errors) {
        try {
            if (errors.hasErrors())
                return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

            VariantWithAttributesDto variant = new VariantWithAttributesDto(service.updateVariant(dto));
            return ResponseHandler.getResponse(variant, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }
}
