package com.example.helpdev.exceptions;

public class ObjectNotFoundException extends RuntimeException{
    private static final long serialVerionUID = 1L;

    public ObjectNotFoundException(String message) {
        super(message);
    }

    public ObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
