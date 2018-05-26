package com.janbabs.bookshop.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    private String message;

    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
