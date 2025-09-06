package com.google.firebase.concurrent;

public final class LimitedConcurrencyExecutor..ExternalSyntheticLambda0 implements Runnable {
    public final LimitedConcurrencyExecutor f$0;
    public final Runnable f$1;

    public LimitedConcurrencyExecutor..ExternalSyntheticLambda0(LimitedConcurrencyExecutor limitedConcurrencyExecutor0, Runnable runnable0) {
        this.f$0 = limitedConcurrencyExecutor0;
        this.f$1 = runnable0;
    }

    @Override
    public final void run() {
        this.f$0.lambda$decorate$0$com-google-firebase-concurrent-LimitedConcurrencyExecutor(this.f$1);
    }
}

