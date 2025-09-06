package com.google.firebase.concurrent;

import androidx.concurrent.futures.AbstractResolvableFuture;
import java.util.concurrent.Delayed;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

class DelegatingScheduledFuture extends AbstractResolvableFuture implements ScheduledFuture {
    interface Completer {
        void set(Object arg1);

        void setException(Throwable arg1);
    }

    interface Resolver {
        ScheduledFuture addCompleter(Completer arg1);
    }

    private final ScheduledFuture upstreamFuture;

    DelegatingScheduledFuture(Resolver delegatingScheduledFuture$Resolver0) {
        this.upstreamFuture = delegatingScheduledFuture$Resolver0.addCompleter(new Completer() {
            @Override  // com.google.firebase.concurrent.DelegatingScheduledFuture$Completer
            public void set(Object object0) {
                DelegatingScheduledFuture.access$000(DelegatingScheduledFuture.this, object0);
            }

            @Override  // com.google.firebase.concurrent.DelegatingScheduledFuture$Completer
            public void setException(Throwable throwable0) {
                DelegatingScheduledFuture.access$100(DelegatingScheduledFuture.this, throwable0);
            }
        });
    }

    static boolean access$000(DelegatingScheduledFuture delegatingScheduledFuture0, Object object0) {
        return delegatingScheduledFuture0.set(object0);
    }

    static boolean access$100(DelegatingScheduledFuture delegatingScheduledFuture0, Throwable throwable0) {
        return delegatingScheduledFuture0.setException(throwable0);
    }

    @Override  // androidx.concurrent.futures.AbstractResolvableFuture
    protected void afterDone() {
        boolean z = this.wasInterrupted();
        this.upstreamFuture.cancel(z);
    }

    @Override
    public int compareTo(Object object0) {
        return this.compareTo(((Delayed)object0));
    }

    public int compareTo(Delayed delayed0) {
        return this.upstreamFuture.compareTo(delayed0);
    }

    @Override
    public long getDelay(TimeUnit timeUnit0) {
        return this.upstreamFuture.getDelay(timeUnit0);
    }
}

