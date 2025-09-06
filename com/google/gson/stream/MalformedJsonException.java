package com.google.gson.stream;

import java.io.IOException;

public final class MalformedJsonException extends IOException {
    private static final long serialVersionUID = 1L;

    public MalformedJsonException(String s) {
        super(s);
    }

    public MalformedJsonException(String s, Throwable throwable0) {
        super(s);
        this.initCause(throwable0);
    }

    public MalformedJsonException(Throwable throwable0) {
        this.initCause(throwable0);
    }
}

