package com.demo.restaurant.enums;

public enum UserErrorMessage {
    USER_DUPLICATE("El usuario con correo electrónico (%s) ya existe."),
    USER_NOT_FOUND("Error al buscar usuario con ID (%s)."),
    USER_AUTHENTICATION_ERROR("Usuario no Autorizado."),
    USER_DEPRECATED("Error el usuario está eliminado.");

    private final String message;

    UserErrorMessage(String message) {
        this.message = message;
    }

    //Returns the enumeration message
    public String getMessage() {
        return this.message;
    }
}
