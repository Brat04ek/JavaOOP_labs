package com.di;

public class CircularInjectException extends RuntimeException {
    public CircularInjectException(String message) {
        super(message);
    }
}
