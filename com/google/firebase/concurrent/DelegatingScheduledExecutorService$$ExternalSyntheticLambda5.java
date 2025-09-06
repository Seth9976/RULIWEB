package com.google.firebase.concurrent;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public final class DelegatingScheduledExecutorService..ExternalSyntheticLambda5 implements Resolver {
    public final DelegatingScheduledExecutorService f$0;
    public final Runnable f$1;
    public final long f$2;
    public final long f$3;
    public final TimeUnit f$4;

    public DelegatingScheduledExecutorService..ExternalSyntheticLambda5(DelegatingScheduledExecutorService delegatingScheduledExecutorService0, Runnable runnable0, long v, long v1, TimeUnit timeUnit0) {
        this.f$0 = delegatingScheduledExecutorService0;
        this.f$1 = runnable0;
        this.f$2 = v;
        this.f$3 = v1;
        this.f$4 = timeUnit0;
    }

    @Override  // com.google.firebase.concurrent.DelegatingScheduledFuture$Resolver
    public final ScheduledFuture addCompleter(Completer delegatingScheduledFuture$Completer0) {
        return this.f$0.lambda$scheduleAtFixedRate$8$com-google-firebase-concurrent-DelegatingScheduledExecutorService(this.f$1, this.f$2, this.f$3, this.f$4, delegatingScheduledFuture$Completer0);
    }
}

