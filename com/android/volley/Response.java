package com.android.volley;

public class Response {
    public interface ErrorListener {
        void onErrorResponse(VolleyError arg1);
    }

    public interface Listener {
        void onResponse(Object arg1);
    }

    public final Entry cacheEntry;
    public final VolleyError error;
    public boolean intermediate;
    public final Object result;

    private Response(VolleyError volleyError0) {
        this.intermediate = false;
        this.result = null;
        this.cacheEntry = null;
        this.error = volleyError0;
    }

    private Response(Object object0, Entry cache$Entry0) {
        this.intermediate = false;
        this.result = object0;
        this.cacheEntry = cache$Entry0;
        this.error = null;
    }

    public static Response error(VolleyError volleyError0) {
        return new Response(volleyError0);
    }

    public boolean isSuccess() {
        return this.error == null;
    }

    public static Response success(Object object0, Entry cache$Entry0) {
        return new Response(object0, cache$Entry0);
    }
}

