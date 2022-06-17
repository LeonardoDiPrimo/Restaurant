package com.demo.restaurant.enums;

public enum ProductErrorMessage {
    PRODUCT_DUPLICATE("El producto con nombre (%s) ya existe."),
    PRODUCT_NOT_FOUND("Error al buscar producto con ID (%s)."),
    PRODUCT_DEPRECATED("El producto (%s) esta eliminado.");

    private final String message;

    ProductErrorMessage(String message) {
        this.message = message;
    }

    //Returns the enumeration message
    public String getMessage() {
        return this.message;
    }
}
