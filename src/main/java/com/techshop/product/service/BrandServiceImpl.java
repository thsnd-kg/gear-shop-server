package com.techshop.product.service;

import com.techshop.product.dto.BrandDto;
import com.techshop.product.dto.CategoryDto;
import com.techshop.product.dto.ProductDto;
import com.techshop.product.entity.Brand;
import com.techshop.product.entity.Category;
import com.techshop.product.entity.Product;
import com.techshop.product.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService{
    private final BrandRepository repo;

    @Autowired
    public BrandServiceImpl(BrandRepository repository) {
        this.repo = repository;
    }


    @Override
    public List<Brand> getBrands() {
        return repo.findAll();
    }

    @Override
    public List<Brand> getBrandsActive() {
        return repo.findByIsDeletedFalse();
    }

    @Override
    public Brand getBrandById(Long brandId) {
        Optional<Brand> brand = repo.findById(brandId);
        if(brand.isPresent())
            return brand.get();

        throw new IllegalStateException("Brand does not exist");
    }

    @Override
    public Brand createBrand(BrandDto dto) {
        Brand brand = handleData(dto, false);
        return repo.save(brand);
    }

    @Override
    public Brand updateBrand(BrandDto dto) {
        Brand brand = handleData(dto, true);
        return repo.save(brand);
    }

    @Override
    public Boolean deleteBrand(Long brandId) {
        if(!isExisted(brandId))
            throw new IllegalStateException("Brand does not exist");

        Brand brand = repo.getById(brandId);
        brand.setIsDeleted(true);
        repo.save(brand);
        return true;
    }

    public boolean isExisted(Long brandId){
        Optional<Brand> brand = repo.findById(brandId);
        if(brand.isPresent())
            return true;

        return false;
    }

    public Brand handleData(BrandDto dto, boolean hasId){
        Brand brand = new Brand();

        if(hasId) {
            if (dto.getBrandId() == null)
                throw new IllegalStateException("Brand Id must not be null");
            if (isExisted(dto.getBrandId()))
                brand = repo.getById(dto.getBrandId());
        }

        if(dto.getName() != null)
            brand.setName(dto.getName());

        if(dto.getDescription() !=null) {
            brand.setDescription(dto.getDescription());
        }

        return  brand;
    }

}