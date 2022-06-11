package com.demo.restaurant.enums;

public enum ProductErrorMessage {
    PRODUCT_DUPLICATE("Product with name (%s) already exists."),
    PRODUCT_NOT_FOUND("Error searching product with ID (%s).");

    private final String message;

    ProductErrorMessage(String message) {
        this.message = message;
    }

    //Returns the enumeration message
    public String getMessage() {
        return this.message;
    }
}
