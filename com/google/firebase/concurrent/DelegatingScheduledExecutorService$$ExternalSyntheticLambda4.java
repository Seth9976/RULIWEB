package com.google.firebase.concurrent;

public final class DelegatingScheduledExecutorService..ExternalSyntheticLambda4 implements Runnable {
    public final DelegatingScheduledExecutorService f$0;
    public final Runnable f$1;
    public final Completer f$2;

    public DelegatingScheduledExecutorService..ExternalSyntheticLambda4(DelegatingScheduledExecutorService delegatingScheduledExecutorService0, Runnable runnable0, Completer delegatingScheduledFuture$Completer0) {
        this.f$0 = delegatingScheduledExecutorService0;
        this.f$1 = runnable0;
        this.f$2 = delegatingScheduledFuture$Completer0;
    }

    @Override
    public final void run() {
        this.f$0.lambda$schedule$1$com-google-firebase-concurrent-DelegatingScheduledExecutorService(this.f$1, this.f$2);
    }
}

