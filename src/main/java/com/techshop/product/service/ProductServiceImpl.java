package com.techshop.product.service;

import com.techshop.product.dto.ProductDto;
import com.techshop.product.entity.Brand;
import com.techshop.product.entity.Category;
import com.techshop.product.entity.Product;
import com.techshop.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository repo;

    @Autowired
    private BrandService brandService;

    @Autowired
    private CategoryService cateService;

    @Override
    public List<Product> getProducts() {
        return repo.findByActiveFlag("Y");
    }

    @Override
    public List<Product> getAll() {
        return repo.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> product = repo.findById(id);
        if(!product.isPresent() )
            throw new IllegalStateException("Product with productId " + id +" is not existed");

        if(product.get().getActiveFlag().equals("D"))
            throw new IllegalStateException("Product with productId " + id +" is deleted");

        return product.get();
    }

    @Override
    public Product createProduct(ProductDto dto) {
        Product product = handleData(dto, false);
        return repo.save(product);
    }

    @Override
    public Product updateProduct(ProductDto dto) {
        Product product = handleData(dto, true);
        return repo.save(product);
    }

    @Override
    public Boolean deleteProduct(Long productId) {
        Product product = getProductById(productId);
        product.setActiveFlag("D");
        repo.save(product);
        return true;
    }



    public Product handleData(ProductDto dto, boolean hasId){
        Product product = new Product();

        if(hasId)
            product = repo.getById(dto.getProductId());


        if(dto.getBrandId() != null) {
            Brand brand = brandService.getBrandById(dto.getBrandId());
            product.setBrand(brand);
        }

        if(dto.getCategoryId() != null) {
            Category category = cateService.getCategoryById(dto.getCategoryId());
            product.setCategory(category);
        }

        if(dto.getProductName() != null)
            product.setProductName(dto.getProductName());

        if(dto.getProductDesc() != null)
            product.setProductDesc(dto.getProductDesc());

        if(dto.getImgUrl() != null)
            product.setImgUrl(dto.getImgUrl());

        return  product;
    }
}
