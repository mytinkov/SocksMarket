package com.example.socksmarket.exception;

public class InvalidSockRequestException extends RuntimeException {
    public InvalidSockRequestException(String message) {
        super(message);
    }
}

