package com.example.blockbuster.Exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String e) {
        super(e);
    }
}
