package com.techshop.product.service;

import com.techshop.product.dto.attribute.UpdateVariantAttributeDto;
import com.techshop.product.dto.variant.CreateVariantDto;
import com.techshop.product.dto.variant.UpdateVariantDto;
import com.techshop.product.entity.Attribute;
import com.techshop.product.entity.Product;
import com.techshop.product.entity.Variant;
import com.techshop.product.entity.VariantAttribute;
import com.techshop.product.repository.VariantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VariantServiceImpl  implements VariantService{

    private VariantRepository repository;
    private ProductService productService;
    private AttributeService attributeService;
    private VariantAttributeService variantAttributeService;

    public VariantServiceImpl(VariantRepository repository, ProductService productService, AttributeService attributeService,  VariantAttributeService variantAttributeService){
        this.repository = repository;
        this.productService = productService;
        this.attributeService = attributeService;
        this.variantAttributeService= variantAttributeService;
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

        for(UpdateVariantAttributeDto createAttribute : dto.getAttributes()){
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

    @Override
    public Variant updateVariant(UpdateVariantDto dto) {
        Variant variant = getById(dto.getVariantId());
        variant.getAttributes().clear();

        variant.setVariantName(dto.getVariantName());
        variant.setVariantDesc(dto.getVariantDesc());
        variant.setSku(dto.getSku());
        variant.setImgUrl(dto.getImgUrl());
        variant.setPrice(dto.getPrice());

        for(UpdateVariantAttributeDto updateAtrribute : dto.getAttributes()){
            VariantAttribute variantAttribute = variantAttributeService.getVariantAttributeById(variant.getVariantId(), updateAtrribute.getAttributeId());

            variantAttribute.setDescription(updateAtrribute.getDescription());
            variantAttribute.setValue(updateAtrribute.getValue());

            variant.getAttributes().add(variantAttribute);
        }

        return repository.save(variant);
    }

    @Override
    public Variant getById(Long variantId) {
        Optional<Variant> variant = repository.findById(variantId);
        if(!variant.isPresent())
            throw new IllegalStateException("Variant with ID: " + variantId + " is not existed");

        return variant.get();
    }


}
