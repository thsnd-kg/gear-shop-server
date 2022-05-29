package com.techshop.product.search;

public enum ProductField {
    productId("productId"),
    productName("productName"),
    category("category"),
    brand("brand");

    public final String field;

    ProductField(String field) {
        this.field = field;
    }
}
