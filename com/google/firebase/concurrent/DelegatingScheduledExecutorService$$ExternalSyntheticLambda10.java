package com.google.firebase.concurrent;

import java.util.concurrent.Callable;

public final class DelegatingScheduledExecutorService..ExternalSyntheticLambda10 implements Callable {
    public final DelegatingScheduledExecutorService f$0;
    public final Callable f$1;
    public final Completer f$2;

    public DelegatingScheduledExecutorService..ExternalSyntheticLambda10(DelegatingScheduledExecutorService delegatingScheduledExecutorService0, Callable callable0, Completer delegatingScheduledFuture$Completer0) {
        this.f$0 = delegatingScheduledExecutorService0;
        this.f$1 = callable0;
        this.f$2 = delegatingScheduledFuture$Completer0;
    }

    @Override
    public final Object call() {
        return this.f$0.lambda$schedule$4$com-google-firebase-concurrent-DelegatingScheduledExecutorService(this.f$1, this.f$2);
    }
}

