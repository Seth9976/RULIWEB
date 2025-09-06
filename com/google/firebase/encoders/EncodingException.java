package com.google.firebase.encoders;

public final class EncodingException extends RuntimeException {
    public EncodingException(String s) {
        super(s);
    }

    public EncodingException(String s, Exception exception0) {
        super(s, exception0);
    }
}

