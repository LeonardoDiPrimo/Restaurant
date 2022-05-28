package com.demo.restaurant.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class NonUniqueResultException extends RuntimeException {
    public NonUniqueResultException(String message) {
        super(message);
    }
}
