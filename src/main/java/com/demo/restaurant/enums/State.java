package com.demo.restaurant.enums;

public enum State {
    PENDING("Pendiente"),
    IN_TRANSIT("En transito"),
    COMPLETED("Completado");

    private final String message;

    State(String message) {
        this.message = message;
    }

    //Returns the enumeration message
    public String getMessage() {
        return this.message;
    }
}
