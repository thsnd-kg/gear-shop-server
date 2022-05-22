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

    @GetMapping("/{variant-id}")
    public Object getVariantDetailById(@PathVariable("variant-id") Long variantId) {
        try {

            return ResponseHandler.getResponse(service.getVariantDetailById(variantId), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping()
    public Object createVariant(@Valid @RequestBody CreateVariantDto dto, BindingResult errors) {
        try {
            if (errors.hasErrors())
                return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

            service.createVariant(dto);
            return ResponseHandler.getResponse( HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping()
    public Object updateVariant(@Valid @RequestBody UpdateVariantDto dto, BindingResult errors) {
        try {
            if (errors.hasErrors())
                return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);
//
            service.updateVariant(dto);
            return ResponseHandler.getResponse( HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }
}
