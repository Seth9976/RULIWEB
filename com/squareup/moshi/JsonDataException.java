package com.squareup.moshi;

import javax.annotation.Nullable;

public final class JsonDataException extends RuntimeException {
    public JsonDataException() {
    }

    public JsonDataException(@Nullable String s) {
        super(s);
    }

    public JsonDataException(@Nullable String s, @Nullable Throwable throwable0) {
        super(s, throwable0);
    }

    public JsonDataException(@Nullable Throwable throwable0) {
        super(throwable0);
    }
}

