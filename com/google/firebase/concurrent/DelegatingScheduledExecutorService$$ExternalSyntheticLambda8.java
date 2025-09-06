package com.google.firebase.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public final class DelegatingScheduledExecutorService..ExternalSyntheticLambda8 implements Resolver {
    public final DelegatingScheduledExecutorService f$0;
    public final Callable f$1;
    public final long f$2;
    public final TimeUnit f$3;

    public DelegatingScheduledExecutorService..ExternalSyntheticLambda8(DelegatingScheduledExecutorService delegatingScheduledExecutorService0, Callable callable0, long v, TimeUnit timeUnit0) {
        this.f$0 = delegatingScheduledExecutorService0;
        this.f$1 = callable0;
        this.f$2 = v;
        this.f$3 = timeUnit0;
    }

    @Override  // com.google.firebase.concurrent.DelegatingScheduledFuture$Resolver
    public final ScheduledFuture addCompleter(Completer delegatingScheduledFuture$Completer0) {
        return this.f$0.lambda$schedule$5$com-google-firebase-concurrent-DelegatingScheduledExecutorService(this.f$1, this.f$2, this.f$3, delegatingScheduledFuture$Completer0);
    }
}

