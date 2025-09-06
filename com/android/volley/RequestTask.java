package com.android.volley;

public abstract class RequestTask implements Runnable {
    final Request mRequest;

    public RequestTask(Request request0) {
        this.mRequest = request0;
    }

    public int compareTo(RequestTask requestTask0) {
        return this.mRequest.compareTo(requestTask0.mRequest);
    }
}

