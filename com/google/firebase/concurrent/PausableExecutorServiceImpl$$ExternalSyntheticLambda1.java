package com.google.firebase.concurrent;

import java.util.concurrent.Callable;

public final class PausableExecutorServiceImpl..ExternalSyntheticLambda1 implements Callable {
    public final Runnable f$0;
    public final Object f$1;

    public PausableExecutorServiceImpl..ExternalSyntheticLambda1(Runnable runnable0, Object object0) {
        this.f$0 = runnable0;
        this.f$1 = object0;
    }

    @Override
    public final Object call() {
        return PausableExecutorServiceImpl.lambda$submit$0(this.f$0, this.f$1);
    }
}

