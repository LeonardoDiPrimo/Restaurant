package com.demo.restaurant.enums;

public enum UserErrorMessage {
    USER_DUPLICATE("User with email (%s) already exists."),
    USER_NOT_FOUND("Error searching user with ID (%s)."),
    USER_AUTHENTICATION_ERROR("Unauthorized user.");

    private final String message;

    private UserErrorMessage(String message) {
        this.message = message;
    }

    //Returns the enumeration message
    public String getMessage() {
        return this.message;
    }
}
