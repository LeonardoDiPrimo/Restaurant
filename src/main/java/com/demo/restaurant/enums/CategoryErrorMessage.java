package com.demo.restaurant.enums;

public enum CategoryErrorMessage {
    CATEGORY_NOT_FOUND("Error al buscar categoría con ID (%s)."),
    CATEGORY_DUPLICATE("La categoría con nombre (%s) ya existe."),
    CATEGORY_DEPRECATED("Error la categoría (%s) está eliminada.");

    private final String message;

    CategoryErrorMessage(String message) {
        this.message = message;
    }

    //Returns the enumeration message
    public String getMessage() {
        return this.message;
    }
}
