package androidx.work.impl;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public final class Processor..ExternalSyntheticLambda1 implements Callable {
    public final Processor f$0;
    public final ArrayList f$1;
    public final String f$2;

    public Processor..ExternalSyntheticLambda1(Processor processor0, ArrayList arrayList0, String s) {
        this.f$0 = processor0;
        this.f$1 = arrayList0;
        this.f$2 = s;
    }

    @Override
    public final Object call() {
        return this.f$0.lambda$startWork$0$androidx-work-impl-Processor(this.f$1, this.f$2);
    }
}

