package com.google.firebase.concurrent;

public final class DelegatingScheduledExecutorService..ExternalSyntheticLambda6 implements Runnable {
    public final Runnable f$0;
    public final Completer f$1;

    public DelegatingScheduledExecutorService..ExternalSyntheticLambda6(Runnable runnable0, Completer delegatingScheduledFuture$Completer0) {
        this.f$0 = runnable0;
        this.f$1 = delegatingScheduledFuture$Completer0;
    }

    @Override
    public final void run() {
        DelegatingScheduledExecutorService.lambda$schedule$0(this.f$0, this.f$1);
    }
}

