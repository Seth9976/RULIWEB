package com.google.firebase.components;

public class InvalidRegistrarException extends RuntimeException {
    public InvalidRegistrarException(String s) {
        super(s);
    }

    public InvalidRegistrarException(String s, Throwable throwable0) {
        super(s, throwable0);
    }
}

