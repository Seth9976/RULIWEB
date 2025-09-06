package com.google.firebase.concurrent;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public final class DelegatingScheduledExecutorService..ExternalSyntheticLambda0 implements Resolver {
    public final DelegatingScheduledExecutorService f$0;
    public final Runnable f$1;
    public final long f$2;
    public final TimeUnit f$3;

    public DelegatingScheduledExecutorService..ExternalSyntheticLambda0(DelegatingScheduledExecutorService delegatingScheduledExecutorService0, Runnable runnable0, long v, TimeUnit timeUnit0) {
        this.f$0 = delegatingScheduledExecutorService0;
        this.f$1 = runnable0;
        this.f$2 = v;
        this.f$3 = timeUnit0;
    }

    @Override  // com.google.firebase.concurrent.DelegatingScheduledFuture$Resolver
    public final ScheduledFuture addCompleter(Completer delegatingScheduledFuture$Completer0) {
        return this.f$0.lambda$schedule$2$com-google-firebase-concurrent-DelegatingScheduledExecutorService(this.f$1, this.f$2, this.f$3, delegatingScheduledFuture$Completer0);
    }
}

