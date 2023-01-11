package com.example.blockbuster.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String e) {
        super(e);
    }
}
