package androidx.work.impl;

import androidx.work.impl.model.WorkSpec;
import java.util.List;
import java.util.Set;

public final class WorkerUpdater..ExternalSyntheticLambda0 implements Runnable {
    public final WorkDatabase f$0;
    public final WorkSpec f$1;
    public final WorkSpec f$2;
    public final List f$3;
    public final String f$4;
    public final Set f$5;
    public final boolean f$6;

    public WorkerUpdater..ExternalSyntheticLambda0(WorkDatabase workDatabase0, WorkSpec workSpec0, WorkSpec workSpec1, List list0, String s, Set set0, boolean z) {
        this.f$0 = workDatabase0;
        this.f$1 = workSpec0;
        this.f$2 = workSpec1;
        this.f$3 = list0;
        this.f$4 = s;
        this.f$5 = set0;
        this.f$6 = z;
    }

    @Override
    public final void run() {
        WorkerUpdater.updateWorkImpl$lambda$2(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}

