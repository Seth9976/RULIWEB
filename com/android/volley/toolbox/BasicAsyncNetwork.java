package com.android.volley.toolbox;

import android.os.SystemClock;
import com.android.volley.AsyncNetwork.OnRequestComplete;
import com.android.volley.AsyncNetwork;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestTask;
import com.android.volley.VolleyError;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

public class BasicAsyncNetwork extends AsyncNetwork {
    public static class Builder {
        private static final int DEFAULT_POOL_SIZE = 0x1000;
        private AsyncHttpStack mAsyncStack;
        private ByteArrayPool mPool;

        public Builder(AsyncHttpStack asyncHttpStack0) {
            this.mAsyncStack = asyncHttpStack0;
            this.mPool = null;
        }

        public BasicAsyncNetwork build() {
            if(this.mPool == null) {
                this.mPool = new ByteArrayPool(0x1000);
            }
            return new BasicAsyncNetwork(this.mAsyncStack, this.mPool, null);
        }

        public Builder setPool(ByteArrayPool byteArrayPool0) {
            this.mPool = byteArrayPool0;
            return this;
        }
    }

    class InvokeRetryPolicyTask extends RequestTask {
        final OnRequestComplete callback;
        final Request request;
        final RetryInfo retryInfo;

        InvokeRetryPolicyTask(Request request0, RetryInfo networkUtility$RetryInfo0, OnRequestComplete asyncNetwork$OnRequestComplete0) {
            super(request0);
            this.request = request0;
            this.retryInfo = networkUtility$RetryInfo0;
            this.callback = asyncNetwork$OnRequestComplete0;
        }

        @Override
        public void run() {
            try {
                NetworkUtility.attemptRetryOnException(this.request, this.retryInfo);
                BasicAsyncNetwork.this.performRequest(this.request, this.callback);
            }
            catch(VolleyError volleyError0) {
                this.callback.onError(volleyError0);
            }
        }
    }

    class ResponseParsingTask extends RequestTask {
        OnRequestComplete callback;
        HttpResponse httpResponse;
        InputStream inputStream;
        Request request;
        long requestStartMs;
        List responseHeaders;
        int statusCode;

        ResponseParsingTask(InputStream inputStream0, HttpResponse httpResponse0, Request request0, OnRequestComplete asyncNetwork$OnRequestComplete0, long v, List list0, int v1) {
            super(request0);
            this.inputStream = inputStream0;
            this.httpResponse = httpResponse0;
            this.request = request0;
            this.callback = asyncNetwork$OnRequestComplete0;
            this.requestStartMs = v;
            this.responseHeaders = list0;
            this.statusCode = v1;
        }

        @Override
        public void run() {
            byte[] arr_b;
            try {
                arr_b = NetworkUtility.inputStreamToBytes(this.inputStream, this.httpResponse.getContentLength(), BasicAsyncNetwork.this.mPool);
            }
            catch(IOException iOException0) {
                BasicAsyncNetwork.this.onRequestFailed(this.request, this.callback, iOException0, this.requestStartMs, this.httpResponse, null);
                return;
            }
            BasicAsyncNetwork.this.onResponseRead(this.requestStartMs, this.statusCode, this.httpResponse, this.request, this.callback, this.responseHeaders, arr_b);
        }
    }

    private final AsyncHttpStack mAsyncStack;
    private final ByteArrayPool mPool;

    private BasicAsyncNetwork(AsyncHttpStack asyncHttpStack0, ByteArrayPool byteArrayPool0) {
        this.mAsyncStack = asyncHttpStack0;
        this.mPool = byteArrayPool0;
    }

    BasicAsyncNetwork(AsyncHttpStack asyncHttpStack0, ByteArrayPool byteArrayPool0, com.android.volley.toolbox.BasicAsyncNetwork.1 basicAsyncNetwork$10) {
        this(asyncHttpStack0, byteArrayPool0);
    }

    private void onRequestFailed(Request request0, OnRequestComplete asyncNetwork$OnRequestComplete0, IOException iOException0, long v, HttpResponse httpResponse0, byte[] arr_b) {
        try {
            RetryInfo networkUtility$RetryInfo0 = NetworkUtility.shouldRetryException(request0, iOException0, v, httpResponse0, arr_b);
            this.getBlockingExecutor().execute(new InvokeRetryPolicyTask(this, request0, networkUtility$RetryInfo0, asyncNetwork$OnRequestComplete0));
        }
        catch(VolleyError volleyError0) {
            asyncNetwork$OnRequestComplete0.onError(volleyError0);
        }
    }

    private void onRequestSucceeded(Request request0, long v, HttpResponse httpResponse0, OnRequestComplete asyncNetwork$OnRequestComplete0) {
        int v1 = httpResponse0.getStatusCode();
        List list0 = httpResponse0.getHeaders();
        if(v1 == 304) {
            asyncNetwork$OnRequestComplete0.onSuccess(NetworkUtility.getNotModifiedNetworkResponse(request0, SystemClock.elapsedRealtime() - v, list0));
            return;
        }
        byte[] arr_b = httpResponse0.getContentBytes() != null || httpResponse0.getContent() != null ? httpResponse0.getContentBytes() : new byte[0];
        if(arr_b != null) {
            this.onResponseRead(v, v1, httpResponse0, request0, asyncNetwork$OnRequestComplete0, list0, arr_b);
            return;
        }
        InputStream inputStream0 = httpResponse0.getContent();
        this.getBlockingExecutor().execute(new ResponseParsingTask(this, inputStream0, httpResponse0, request0, asyncNetwork$OnRequestComplete0, v, list0, v1));
    }

    private void onResponseRead(long v, int v1, HttpResponse httpResponse0, Request request0, OnRequestComplete asyncNetwork$OnRequestComplete0, List list0, byte[] arr_b) {
        NetworkUtility.logSlowRequests(SystemClock.elapsedRealtime() - v, request0, arr_b, v1);
        if(v1 >= 200 && v1 <= 299) {
            asyncNetwork$OnRequestComplete0.onSuccess(new NetworkResponse(v1, arr_b, false, SystemClock.elapsedRealtime() - v, list0));
            return;
        }
        this.onRequestFailed(request0, asyncNetwork$OnRequestComplete0, new IOException(), v, httpResponse0, arr_b);
    }

    @Override  // com.android.volley.AsyncNetwork
    public void performRequest(Request request0, OnRequestComplete asyncNetwork$OnRequestComplete0) {
        if(this.getBlockingExecutor() == null) {
            throw new IllegalStateException("mBlockingExecuter must be set before making a request");
        }
        long v = SystemClock.elapsedRealtime();
        Map map0 = HttpHeaderParser.getCacheHeaders(request0.getCacheEntry());
        com.android.volley.toolbox.BasicAsyncNetwork.1 basicAsyncNetwork$10 = new com.android.volley.toolbox.AsyncHttpStack.OnRequestComplete() {
            @Override  // com.android.volley.toolbox.AsyncHttpStack$OnRequestComplete
            public void onAuthError(AuthFailureError authFailureError0) {
                asyncNetwork$OnRequestComplete0.onError(authFailureError0);
            }

            @Override  // com.android.volley.toolbox.AsyncHttpStack$OnRequestComplete
            public void onError(IOException iOException0) {
                BasicAsyncNetwork.this.onRequestFailed(request0, asyncNetwork$OnRequestComplete0, iOException0, v, null, null);
            }

            @Override  // com.android.volley.toolbox.AsyncHttpStack$OnRequestComplete
            public void onSuccess(HttpResponse httpResponse0) {
                BasicAsyncNetwork.this.onRequestSucceeded(request0, v, httpResponse0, asyncNetwork$OnRequestComplete0);
            }
        };
        this.mAsyncStack.executeRequest(request0, map0, basicAsyncNetwork$10);
    }

    @Override  // com.android.volley.AsyncNetwork
    public void setBlockingExecutor(ExecutorService executorService0) {
        super.setBlockingExecutor(executorService0);
        this.mAsyncStack.setBlockingExecutor(executorService0);
    }

    @Override  // com.android.volley.AsyncNetwork
    public void setNonBlockingExecutor(ExecutorService executorService0) {
        super.setNonBlockingExecutor(executorService0);
        this.mAsyncStack.setNonBlockingExecutor(executorService0);
    }
}

