package com.android.volley;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicReference;

public abstract class AsyncNetwork implements Network {
    public interface OnRequestComplete {
        void onError(VolleyError arg1);

        void onSuccess(NetworkResponse arg1);
    }

    private ExecutorService mBlockingExecutor;
    private ExecutorService mNonBlockingExecutor;
    private ScheduledExecutorService mNonBlockingScheduledExecutor;

    protected ExecutorService getBlockingExecutor() {
        return this.mBlockingExecutor;
    }

    protected ExecutorService getNonBlockingExecutor() {
        return this.mNonBlockingExecutor;
    }

    protected ScheduledExecutorService getNonBlockingScheduledExecutor() {
        return this.mNonBlockingScheduledExecutor;
    }

    @Override  // com.android.volley.Network
    public NetworkResponse performRequest(Request request0) throws VolleyError {
        CountDownLatch countDownLatch0 = new CountDownLatch(1);
        AtomicReference atomicReference0 = new AtomicReference();
        AtomicReference atomicReference1 = new AtomicReference();
        this.performRequest(request0, new OnRequestComplete() {
            @Override  // com.android.volley.AsyncNetwork$OnRequestComplete
            public void onError(VolleyError volleyError0) {
                atomicReference1.set(volleyError0);
                countDownLatch0.countDown();
            }

            @Override  // com.android.volley.AsyncNetwork$OnRequestComplete
            public void onSuccess(NetworkResponse networkResponse0) {
                atomicReference0.set(networkResponse0);
                countDownLatch0.countDown();
            }
        });
        try {
            countDownLatch0.await();
        }
        catch(InterruptedException interruptedException0) {
            VolleyLog.e(interruptedException0, "while waiting for CountDownLatch", new Object[0]);
            Thread.currentThread().interrupt();
            throw new VolleyError(interruptedException0);
        }
        if(atomicReference0.get() == null) {
            throw atomicReference1.get() == null ? new VolleyError("Neither response entry was set") : ((VolleyError)atomicReference1.get());
        }
        return (NetworkResponse)atomicReference0.get();
    }

    public abstract void performRequest(Request arg1, OnRequestComplete arg2);

    public void setBlockingExecutor(ExecutorService executorService0) {
        this.mBlockingExecutor = executorService0;
    }

    public void setNonBlockingExecutor(ExecutorService executorService0) {
        this.mNonBlockingExecutor = executorService0;
    }

    public void setNonBlockingScheduledExecutor(ScheduledExecutorService scheduledExecutorService0) {
        this.mNonBlockingScheduledExecutor = scheduledExecutorService0;
    }
}

