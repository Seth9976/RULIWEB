package com.android.volley.toolbox;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.VolleyLog;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicReference;

public abstract class AsyncHttpStack extends BaseHttpStack {
    public interface OnRequestComplete {
        void onAuthError(AuthFailureError arg1);

        void onError(IOException arg1);

        void onSuccess(HttpResponse arg1);
    }

    static class Response {
        AuthFailureError authFailureError;
        HttpResponse httpResponse;
        IOException ioException;

        private Response(HttpResponse httpResponse0, IOException iOException0, AuthFailureError authFailureError0) {
            this.httpResponse = httpResponse0;
            this.ioException = iOException0;
            this.authFailureError = authFailureError0;
        }

        Response(HttpResponse httpResponse0, IOException iOException0, AuthFailureError authFailureError0, com.android.volley.toolbox.AsyncHttpStack.1 asyncHttpStack$10) {
            this(httpResponse0, iOException0, authFailureError0);
        }
    }

    private ExecutorService mBlockingExecutor;
    private ExecutorService mNonBlockingExecutor;

    @Override  // com.android.volley.toolbox.BaseHttpStack
    public final HttpResponse executeRequest(Request request0, Map map0) throws IOException, AuthFailureError {
        CountDownLatch countDownLatch0 = new CountDownLatch(1);
        AtomicReference atomicReference0 = new AtomicReference();
        this.executeRequest(request0, map0, new OnRequestComplete() {
            @Override  // com.android.volley.toolbox.AsyncHttpStack$OnRequestComplete
            public void onAuthError(AuthFailureError authFailureError0) {
                Response asyncHttpStack$Response0 = new Response(null, null, authFailureError0, null);
                atomicReference0.set(asyncHttpStack$Response0);
                countDownLatch0.countDown();
            }

            @Override  // com.android.volley.toolbox.AsyncHttpStack$OnRequestComplete
            public void onError(IOException iOException0) {
                Response asyncHttpStack$Response0 = new Response(null, iOException0, null, null);
                atomicReference0.set(asyncHttpStack$Response0);
                countDownLatch0.countDown();
            }

            @Override  // com.android.volley.toolbox.AsyncHttpStack$OnRequestComplete
            public void onSuccess(HttpResponse httpResponse0) {
                Response asyncHttpStack$Response0 = new Response(httpResponse0, null, null, null);
                atomicReference0.set(asyncHttpStack$Response0);
                countDownLatch0.countDown();
            }
        });
        try {
            countDownLatch0.await();
        }
        catch(InterruptedException interruptedException0) {
            VolleyLog.e(interruptedException0, "while waiting for CountDownLatch", new Object[0]);
            Thread.currentThread().interrupt();
            throw new InterruptedIOException(interruptedException0.toString());
        }
        Response asyncHttpStack$Response0 = (Response)atomicReference0.get();
        if(asyncHttpStack$Response0.httpResponse != null) {
            return asyncHttpStack$Response0.httpResponse;
        }
        if(asyncHttpStack$Response0.ioException == null) {
            throw asyncHttpStack$Response0.authFailureError;
        }
        throw asyncHttpStack$Response0.ioException;
    }

    public abstract void executeRequest(Request arg1, Map arg2, OnRequestComplete arg3);

    protected ExecutorService getBlockingExecutor() {
        return this.mBlockingExecutor;
    }

    protected ExecutorService getNonBlockingExecutor() {
        return this.mNonBlockingExecutor;
    }

    public void setBlockingExecutor(ExecutorService executorService0) {
        this.mBlockingExecutor = executorService0;
    }

    public void setNonBlockingExecutor(ExecutorService executorService0) {
        this.mNonBlockingExecutor = executorService0;
    }
}

