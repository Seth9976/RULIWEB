package com.google.gson;

public final class JsonSyntaxException extends JsonParseException {
    private static final long serialVersionUID = 1L;

    public JsonSyntaxException(String s) {
        super(s);
    }

    public JsonSyntaxException(String s, Throwable throwable0) {
        super(s, throwable0);
    }

    public JsonSyntaxException(Throwable throwable0) {
        super(throwable0);
    }
}

