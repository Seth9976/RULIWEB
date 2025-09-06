package androidx.work.impl.utils;

import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkManagerImpl;

public final class CancelWorkRunnable..ExternalSyntheticLambda0 implements Runnable {
    public final WorkDatabase f$0;
    public final String f$1;
    public final WorkManagerImpl f$2;

    public CancelWorkRunnable..ExternalSyntheticLambda0(WorkDatabase workDatabase0, String s, WorkManagerImpl workManagerImpl0) {
        this.f$0 = workDatabase0;
        this.f$1 = s;
        this.f$2 = workManagerImpl0;
    }

    @Override
    public final void run() {
        CancelWorkRunnable.$r8$lambda$gmz-7SyxTGDd6CwHjvOsJ11-hcc(this.f$0, this.f$1, this.f$2);
    }
}

