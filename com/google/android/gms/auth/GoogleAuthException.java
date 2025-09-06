package com.google.android.gms.auth;

public class GoogleAuthException extends Exception {
    public GoogleAuthException() {
    }

    public GoogleAuthException(String s) {
        super(s);
    }

    public GoogleAuthException(String s, Throwable throwable0) {
        super(s, throwable0);
    }

    public GoogleAuthException(Throwable throwable0) {
        super(throwable0);
    }
}

