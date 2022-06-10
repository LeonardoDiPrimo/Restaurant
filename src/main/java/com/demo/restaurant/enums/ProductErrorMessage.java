package com.demo.restaurant.enums;

public enum ProductErrorMessage {
    PRODUCT_DUPLICATE("Product with name (%s) already exists.");

    private final String message;

    private ProductErrorMessage(String message) {
        this.message = message;
    }

    //Returns the enumeration message
    public String getMessage() {
        return this.message;
    }
}
