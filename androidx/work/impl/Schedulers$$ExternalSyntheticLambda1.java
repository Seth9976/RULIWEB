package androidx.work.impl;

import androidx.work.Configuration;
import androidx.work.impl.model.WorkGenerationalId;
import java.util.List;

public final class Schedulers..ExternalSyntheticLambda1 implements Runnable {
    public final List f$0;
    public final WorkGenerationalId f$1;
    public final Configuration f$2;
    public final WorkDatabase f$3;

    public Schedulers..ExternalSyntheticLambda1(List list0, WorkGenerationalId workGenerationalId0, Configuration configuration0, WorkDatabase workDatabase0) {
        this.f$0 = list0;
        this.f$1 = workGenerationalId0;
        this.f$2 = configuration0;
        this.f$3 = workDatabase0;
    }

    @Override
    public final void run() {
        Schedulers.lambda$registerRescheduling$0(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}

