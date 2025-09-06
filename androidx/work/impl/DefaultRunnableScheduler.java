package androidx.work.impl;

import android.os.Handler;
import android.os.Looper;
import androidx.core.os.HandlerCompat;
import androidx.work.RunnableScheduler;

public class DefaultRunnableScheduler implements RunnableScheduler {
    private final Handler mHandler;

    public DefaultRunnableScheduler() {
        this.mHandler = HandlerCompat.createAsync(Looper.getMainLooper());
    }

    @Override  // androidx.work.RunnableScheduler
    public void cancel(Runnable runnable0) {
        this.mHandler.removeCallbacks(runnable0);
    }

    public Handler getHandler() {
        return this.mHandler;
    }

    @Override  // androidx.work.RunnableScheduler
    public void scheduleWithDelay(long v, Runnable runnable0) {
        this.mHandler.postDelayed(runnable0, v);
    }
}

