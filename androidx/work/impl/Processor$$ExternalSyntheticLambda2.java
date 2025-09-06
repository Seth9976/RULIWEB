package androidx.work.impl;

import com.google.common.util.concurrent.ListenableFuture;

public final class Processor..ExternalSyntheticLambda2 implements Runnable {
    public final Processor f$0;
    public final ListenableFuture f$1;
    public final WorkerWrapper f$2;

    public Processor..ExternalSyntheticLambda2(Processor processor0, ListenableFuture listenableFuture0, WorkerWrapper workerWrapper0) {
        this.f$0 = processor0;
        this.f$1 = listenableFuture0;
        this.f$2 = workerWrapper0;
    }

    @Override
    public final void run() {
        this.f$0.lambda$startWork$1$androidx-work-impl-Processor(this.f$1, this.f$2);
    }
}

