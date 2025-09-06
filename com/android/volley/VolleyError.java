package com.android.volley;

public class VolleyError extends Exception {
    public final NetworkResponse networkResponse;
    private long networkTimeMs;

    public VolleyError() {
        this.networkResponse = null;
    }

    public VolleyError(NetworkResponse networkResponse0) {
        this.networkResponse = networkResponse0;
    }

    public VolleyError(String s) {
        super(s);
        this.networkResponse = null;
    }

    public VolleyError(String s, Throwable throwable0) {
        super(s, throwable0);
        this.networkResponse = null;
    }

    public VolleyError(Throwable throwable0) {
        super(throwable0);
        this.networkResponse = null;
    }

    public long getNetworkTimeMs() {
        return this.networkTimeMs;
    }

    void setNetworkTimeMs(long v) {
        this.networkTimeMs = v;
    }
}

