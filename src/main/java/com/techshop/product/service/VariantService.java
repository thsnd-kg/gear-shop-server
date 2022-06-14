package com.techshop.product.service;

import com.techshop.product.dto.variant.CreateVariantDto;
import com.techshop.product.dto.variant.UpdateVariantDto;
import com.techshop.product.dto.variant.VariantWithAttributesDto;
import com.techshop.product.entity.Variant;

import java.util.List;

public interface VariantService {
    List<VariantWithAttributesDto> getByProductId(Long productId);
    void createVariant(CreateVariantDto dto);
    void updateVariant(UpdateVariantDto dto);
    Variant getById(Long variantId);
    VariantWithAttributesDto getVariantDetailById(Long variantId);

    void deleteVariant(Long variantId);

    void handleQuantity(Long variantId, Integer quantity);
}
