package com.techshop.product.service;

import com.techshop.product.entity.VariantAttribute;

public interface VariantAttributeService {
    VariantAttribute getVariantAttributeById(Long variantId, Long attributeId);
}
