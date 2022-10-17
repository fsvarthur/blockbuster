package com.example.blockbuster.Exception;

public class VideoNotFoundException extends RuntimeException {
    public VideoNotFoundException(String e) {
        super("Could not find video "+ e);
    }
}
