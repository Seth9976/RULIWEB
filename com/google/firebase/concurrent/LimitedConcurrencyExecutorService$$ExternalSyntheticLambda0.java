package com.google.firebase.concurrent;

import java.util.concurrent.Callable;

public final class LimitedConcurrencyExecutorService..ExternalSyntheticLambda0 implements Callable {
    public final Runnable f$0;

    public LimitedConcurrencyExecutorService..ExternalSyntheticLambda0(Runnable runnable0) {
        this.f$0 = runnable0;
    }

    @Override
    public final Object call() {
        return LimitedConcurrencyExecutorService.lambda$submit$1(this.f$0);
    }
}

