package com.google.firebase.concurrent;

public final class CustomThreadFactory..ExternalSyntheticLambda0 implements Runnable {
    public final CustomThreadFactory f$0;
    public final Runnable f$1;

    public CustomThreadFactory..ExternalSyntheticLambda0(CustomThreadFactory customThreadFactory0, Runnable runnable0) {
        this.f$0 = customThreadFactory0;
        this.f$1 = runnable0;
    }

    @Override
    public final void run() {
        this.f$0.lambda$newThread$0$com-google-firebase-concurrent-CustomThreadFactory(this.f$1);
    }
}

