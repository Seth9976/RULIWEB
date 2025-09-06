package com.google.gson;

public final class JsonIOException extends JsonParseException {
    private static final long serialVersionUID = 1L;

    public JsonIOException(String s) {
        super(s);
    }

    public JsonIOException(String s, Throwable throwable0) {
        super(s, throwable0);
    }

    public JsonIOException(Throwable throwable0) {
        super(throwable0);
    }
}

