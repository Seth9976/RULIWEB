package com.android.volley.toolbox;

import android.os.SystemClock;
import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class RequestFuture implements ErrorListener, Listener, Future {
    private VolleyError mException;
    private Request mRequest;
    private Object mResult;
    private boolean mResultReceived;

    private RequestFuture() {
        this.mResultReceived = false;
    }

    @Override
    public boolean cancel(boolean z) {
        synchronized(this) {
            if(this.mRequest == null) {
                return false;
            }
            if(!this.isDone()) {
                this.mRequest.cancel();
                return true;
            }
            return false;
        }
    }

    private Object doGet(Long long0) throws InterruptedException, ExecutionException, TimeoutException {
        synchronized(this) {
            if(this.mException == null) {
                if(this.mResultReceived) {
                    return this.mResult;
                }
                if(long0 == null) {
                    while(!this.isDone()) {
                        this.wait(0L);
                    }
                }
                else if(((long)long0) > 0L) {
                    long v1 = SystemClock.uptimeMillis();
                    long v2 = ((long)long0) + v1;
                    while(!this.isDone() && v1 < v2) {
                        this.wait(v2 - v1);
                        v1 = SystemClock.uptimeMillis();
                    }
                }
                if(this.mException != null) {
                    throw new ExecutionException(this.mException);
                }
                if(!this.mResultReceived) {
                    throw new TimeoutException();
                }
                return this.mResult;
            }
        }
        throw new ExecutionException(this.mException);
    }

    @Override
    public Object get() throws InterruptedException, ExecutionException {
        try {
            return this.doGet(null);
        }
        catch(TimeoutException timeoutException0) {
            throw new AssertionError(timeoutException0);
        }
    }

    @Override
    public Object get(long v, TimeUnit timeUnit0) throws InterruptedException, ExecutionException, TimeoutException {
        return this.doGet(TimeUnit.MILLISECONDS.convert(v, timeUnit0));
    }

    @Override
    public boolean isCancelled() {
        return this.mRequest == null ? false : this.mRequest.isCanceled();
    }

    @Override
    public boolean isDone() {
        synchronized(this) {
            return this.mResultReceived || this.mException != null || this.isCancelled();
        }
    }

    public static RequestFuture newFuture() {
        return new RequestFuture();
    }

    @Override  // com.android.volley.Response$ErrorListener
    public void onErrorResponse(VolleyError volleyError0) {
        synchronized(this) {
            this.mException = volleyError0;
            this.notifyAll();
        }
    }

    @Override  // com.android.volley.Response$Listener
    public void onResponse(Object object0) {
        synchronized(this) {
            this.mResultReceived = true;
            this.mResult = object0;
            this.notifyAll();
        }
    }

    public void setRequest(Request request0) {
        this.mRequest = request0;
    }
}

