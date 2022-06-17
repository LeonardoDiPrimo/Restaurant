package com.demo.restaurant.enums;

public enum PurchaseErrorMessage {
    PURCHASE_ERROR("Error al realizar la compra."),
    AMOUNT_ERROR("La cantidad del producto %s tiene que ser mayor a 0."),
    STOCK_ERROR("El stock actual del producto %s es %s y se está intentando realizar una compra de %s.");

    private final String message;

    PurchaseErrorMessage(String message) {
        this.message = message;
    }

    //Returns the enumeration message
    public String getMessage() {
        return this.message;
    }
}
