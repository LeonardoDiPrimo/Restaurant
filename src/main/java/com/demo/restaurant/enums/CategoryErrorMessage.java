package com.demo.restaurant.enums;

public enum CategoryErrorMessage {
    CATEGORY_NOT_FOUND("Error searching category with ID (%s)."),
    CATEGORY_DUPLICATE("Category with name (%s) already exists."),
    CATEGORY_DEPRECATED("Error the category is deprecated");

    private final String message;

    private CategoryErrorMessage(String message) {
        this.message = message;
    }

    //Returns the enumeration message
    public String getMessage() {
        return this.message;
    }
}
