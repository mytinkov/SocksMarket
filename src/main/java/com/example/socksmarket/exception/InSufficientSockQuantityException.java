package com.example.socksmarket.exception;

public class InSufficientSockQuantityException extends RuntimeException {
    public InSufficientSockQuantityException(String message) {
        super(message);
    }
}
