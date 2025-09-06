package com.google.firebase.concurrent;

import java.util.concurrent.Callable;

public final class DelegatingScheduledExecutorService..ExternalSyntheticLambda9 implements Runnable {
    public final Callable f$0;
    public final Completer f$1;

    public DelegatingScheduledExecutorService..ExternalSyntheticLambda9(Callable callable0, Completer delegatingScheduledFuture$Completer0) {
        this.f$0 = callable0;
        this.f$1 = delegatingScheduledFuture$Completer0;
    }

    @Override
    public final void run() {
        DelegatingScheduledExecutorService.lambda$schedule$3(this.f$0, this.f$1);
    }
}

