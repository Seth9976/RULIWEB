package com.google.crypto.tink.internal;

public final class TinkBugException extends RuntimeException {
    public TinkBugException(String s) {
        super(s);
    }

    public TinkBugException(String s, Throwable throwable0) {
        super(s, throwable0);
    }

    public TinkBugException(Throwable throwable0) {
        super(throwable0);
    }
}

