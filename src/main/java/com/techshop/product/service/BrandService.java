package com.techshop.product.service;

import com.techshop.product.dto.BrandDto;
import com.techshop.product.entity.Brand;

import java.util.List;

public interface BrandService {
    List<Brand> getBrands();
    List<Brand> getBrandsActive();
    Brand getBrandById(Long brandId);
    Brand createBrand(BrandDto dto);
    Brand updateBrand(BrandDto dto);
    Boolean deleteBrand(Long brandId);
}
