package com.android.volley;

public interface ResponseDelivery {
    void postError(Request arg1, VolleyError arg2);

    void postResponse(Request arg1, Response arg2);

    void postResponse(Request arg1, Response arg2, Runnable arg3);
}

