package com.android.volley;

public class ParseError extends VolleyError {
    public ParseError() {
    }

    public ParseError(NetworkResponse networkResponse0) {
        super(networkResponse0);
    }

    public ParseError(Throwable throwable0) {
        super(throwable0);
    }
}

