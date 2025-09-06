package androidx.work.impl;

import androidx.work.impl.model.WorkGenerationalId;

public final class Processor..ExternalSyntheticLambda0 implements Runnable {
    public final Processor f$0;
    public final WorkGenerationalId f$1;
    public final boolean f$2;

    public Processor..ExternalSyntheticLambda0(Processor processor0, WorkGenerationalId workGenerationalId0, boolean z) {
        this.f$0 = processor0;
        this.f$1 = workGenerationalId0;
        this.f$2 = z;
    }

    @Override
    public final void run() {
        this.f$0.lambda$runOnExecuted$2$androidx-work-impl-Processor(this.f$1, this.f$2);
    }
}

