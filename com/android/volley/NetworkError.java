package com.android.volley;

public class NetworkError extends VolleyError {
    public NetworkError() {
    }

    public NetworkError(NetworkResponse networkResponse0) {
        super(networkResponse0);
    }

    public NetworkError(Throwable throwable0) {
        super(throwable0);
    }
}

