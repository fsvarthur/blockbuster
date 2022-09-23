package com.example.blockbuster;

public class VideoNotFoundException extends RuntimeException {
    public VideoNotFoundException(Long id) {
        super("Could not find video "+id);
    }
}
