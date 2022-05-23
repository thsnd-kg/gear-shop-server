package com.techshop.product.service;

import com.techshop.common.util.StringUtils;
import com.techshop.product.converter.ProductConverter;
import com.techshop.product.dto.product.ProductDetailDto;
import com.techshop.product.dto.product.ProductDto;
import com.techshop.product.dto.product.ProductWithVariantDto;
import com.techshop.product.entity.Brand;
import com.techshop.product.entity.Category;
import com.techshop.product.entity.Product;
import com.techshop.product.entity.Variant;
import com.techshop.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
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

    @Autowired
    private ProductConverter converter;
    

    @Override
    public List<ProductWithVariantDto> getProducts() {
        List<Product> products = repo.findByActiveFlag("Y");
        List<ProductWithVariantDto> result = new ArrayList<>();

        products.forEach(product -> result.add(converter.toProductWithVariant(product)));

        return result;
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
    public ProductWithVariantDto createProduct(ProductDto dto) {
        Product product = handleData(dto, false);
        Product result = repo.save(product);
        return converter.toProductWithVariant(result);
    }

    @Override
    public ProductWithVariantDto updateProduct(ProductDto dto) {
        Product product = handleData(dto, true);
        Product result = repo.save(product);
        return converter.toProductWithVariant(result);
    }

    @Override
    public Boolean deleteProduct(Long productId) {
        Product product = getProductById(productId);
        product.setActiveFlag("D");
        repo.save(product);
        return true;
    }

    @Override
    public ProductWithVariantDto getProductDetailById(Long productId) {

        Product product = getProductById(productId);
        return converter.toProductWithVariant(product);
    }

    @Override
    public ProductWithVariantDto getByProductLink(String productLink) {
        Product product = repo.findByProductLink(productLink);
        return converter.toProductWithVariant(product);
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

        if(dto.getProductName() != null){
            product.setProductName(dto.getProductName());
            String productLink = StringUtils.deAccent(dto.getProductName());
            product.setProductLink(productLink);
        }


        if(dto.getProductDesc() != null)
            product.setProductDesc(dto.getProductDesc());

        if(dto.getImgUrl() != null)
            product.setImgUrl(dto.getImgUrl());

        return  product;
    }


    /* */
//    @Override
//    public ProductDetailDto getVariantsByProductId(Long productId) {
////        ProductDetailDto response = new ProductDetailDto();
////        Product product = getProductById(productId);
////
////        response.setProductDesc(product.getProductDesc());
////        response.setProductName(product.getProductName());
////        response.setProductId(product.getProductId());
////        response.setCategory(product.getCategory());
////        response.setBrand(product.getBrand());
////
//////        List<Variant> variants = variantService.getByProductId(productId);
////        response.setVariants(variants);
////        return response;
//        return repo.findByActiveFlag("Y")
//    }
}
