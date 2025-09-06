package com.google.gson;

public class JsonParseException extends RuntimeException {
    static final long serialVersionUID = 0xC74904BDB8787122L;

    public JsonParseException(String s) {
        super(s);
    }

    public JsonParseException(String s, Throwable throwable0) {
        super(s, throwable0);
    }

    public JsonParseException(Throwable throwable0) {
        super(throwable0);
    }
}

