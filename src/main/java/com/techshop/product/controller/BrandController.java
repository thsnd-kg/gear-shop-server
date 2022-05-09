package com.techshop.product.controller;

import com.techshop.common.ResponseHandler;
import com.techshop.product.dto.BrandDto;
import com.techshop.product.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/brands")
public class BrandController {
    private final BrandService service;

    @Autowired
    public BrandController(BrandService service) {
        this.service = service;
    }

    @GetMapping
    public Object getBrands(@RequestParam(value = "onlyActive") Boolean isActive){
        if(isActive)
            return ResponseHandler.getResponse(service.getBrandsActive(), HttpStatus.OK);

        return ResponseHandler.getResponse(service.getBrands(), HttpStatus.OK);
    }


    @GetMapping(path = "/{brand-id}")
    public Object getBrandById(@PathVariable("brand-id") Long brandId){
        try{
            if(brandId == null)
                throw new IllegalStateException("Brand Id must not be null");

            return ResponseHandler.getResponse(service.getBrandById(brandId), HttpStatus.OK);
        } catch (Exception e){
             return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public Object createBrand(@Valid @RequestBody BrandDto newBrand, BindingResult errors){
        try{
            if(errors.hasErrors())
                return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

            return ResponseHandler.getResponse(service.createBrand(newBrand), HttpStatus.OK);
        }catch (Exception e){
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping
    public Object updateBrand(@RequestBody BrandDto updatedBrand, BindingResult errors){
        try{
            if(errors.hasErrors())
                return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

            return ResponseHandler.getResponse(service.updateBrand(updatedBrand), HttpStatus.OK);
        }catch (Exception e){
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{brand-id}")
    public Object deleteBrand(@PathVariable("brand-id") Long brandId){
        try{
            if(brandId == null)
                throw new IllegalStateException("Brand Id must not be null");

            return ResponseHandler.getResponse(service.deleteBrand(brandId), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }

    }
}

