package com.techshop.product.service;

import com.techshop.product.dto.attribute.CreateAttributeDto;
import com.techshop.product.dto.variant.CreateVariantDto;
import com.techshop.product.entity.Attribute;
import com.techshop.product.entity.Product;
import com.techshop.product.entity.Variant;
import com.techshop.product.entity.VariantAttribute;
import com.techshop.product.repository.VariantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VariantServiceImpl  implements VariantService{

    private VariantRepository repository;
    private ProductService productService;
    private AttributeService attributeService;

    public VariantServiceImpl(VariantRepository repository, ProductService productService, AttributeService attributeService){
        this.repository = repository;
        this.productService = productService;
        this.attributeService = attributeService;
    }

    @Override
    public List<Variant> getByProductId(Long productId) {
        return repository.findByProductId(productId);
    }

    @Override
    public Variant createVariant(CreateVariantDto dto) {
        Product product = productService.getProductById(dto.getProductId());

        Variant variant = new Variant();
        variant.setProduct(product);
        variant.setVariantName(dto.getVariantName());
        variant.setVariantDesc(dto.getVariantDesc());
        variant.setSku(dto.getSku());
        variant.setImgUrl(dto.getImgUrl());
        variant.setPrice(dto.getPrice());

        for(CreateAttributeDto createAttribute : dto.getAttributes()){
            VariantAttribute variantAttribute = new VariantAttribute();
            Attribute attribute = attributeService.getById(createAttribute.getAttributeId());

            variantAttribute.setVariant(variant);
            variantAttribute.setAttribute(attribute);
            variantAttribute.setDescription(createAttribute.getDescription());
            variantAttribute.setValue(createAttribute.getValue());

            variant.getAttributes().add(variantAttribute);
        }

        return repository.save(variant);
    }
}
