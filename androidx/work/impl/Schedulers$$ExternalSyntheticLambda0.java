package androidx.work.impl;

import androidx.work.Configuration;
import androidx.work.impl.model.WorkGenerationalId;
import java.util.List;
import java.util.concurrent.Executor;

public final class Schedulers..ExternalSyntheticLambda0 implements ExecutionListener {
    public final Executor f$0;
    public final List f$1;
    public final Configuration f$2;
    public final WorkDatabase f$3;

    public Schedulers..ExternalSyntheticLambda0(Executor executor0, List list0, Configuration configuration0, WorkDatabase workDatabase0) {
        this.f$0 = executor0;
        this.f$1 = list0;
        this.f$2 = configuration0;
        this.f$3 = workDatabase0;
    }

    @Override  // androidx.work.impl.ExecutionListener
    public final void onExecuted(WorkGenerationalId workGenerationalId0, boolean z) {
        Schedulers.lambda$registerRescheduling$1(this.f$0, this.f$1, this.f$2, this.f$3, workGenerationalId0, z);
    }
}

