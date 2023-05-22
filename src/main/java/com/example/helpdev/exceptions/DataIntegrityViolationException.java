package com.example.helpdev.exceptions;

public class DataIntegrityViolationException extends RuntimeException{
    private static final long serialVerionUID = 1L;

    public DataIntegrityViolationException(String message) {
        super(message);
    }

    public DataIntegrityViolationException(String message, Throwable cause) {
        super(message, cause);
    }
}
