package com.techshop.product.service;

import com.techshop.product.dto.variant.CreateVariantDto;
import com.techshop.product.dto.variant.UpdateVariantDto;
import com.techshop.product.entity.Variant;

import java.util.List;

public interface VariantService {
    List<Variant> getByProductId(Long productId);
    Variant createVariant(CreateVariantDto dto);
    Variant updateVariant(UpdateVariantDto dto);
    Variant getById(Long variantId);
}
