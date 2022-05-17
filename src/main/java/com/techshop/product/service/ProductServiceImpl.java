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
        return repo.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return repo.getById(id);
    }

    @Override
    public Product createProduct(ProductDto dto) {
        Product product = handleData(dto, false);
        if(product == null)
            return null;

        return repo.save(product);
    }

    @Override
    public Product updateProduct(ProductDto dto) {
        Product product = handleData(dto, true);
        if(product == null)
            return null;

        return repo.save(product);
    }

    @Override
    public Boolean deteleProduct(Long productId) {
        return null;
    }

    public Product handleData(ProductDto dto, boolean hasId){
        Product product = new Product();

        if(hasId)
            product = repo.getById(dto.getProductId());

        if(product == null)
            return null;

        if(dto.getBrandId() != null) {
            Brand brand = brandService.getBrandById(dto.getBrandId());

            if (brand != null)
                product.setBrand(brand);
            else
                return null;
        }

        if(dto.getCategoryId() != null) {
            Category category = cateService.getCategoryById(dto.getCategoryId());

            if (category != null)
                product.setCategory(category);
            else
                return null;
        }

        if(dto.getProductName() != null)
            product.setProductName(dto.getProductName());

        return  product;
    }
}
