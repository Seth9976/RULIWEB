package androidx.work.impl;

import androidx.work.WorkerParameters.RuntimeExtras;

public final class WorkLauncherImpl..ExternalSyntheticLambda0 implements Runnable {
    public final WorkLauncherImpl f$0;
    public final StartStopToken f$1;
    public final RuntimeExtras f$2;

    public WorkLauncherImpl..ExternalSyntheticLambda0(WorkLauncherImpl workLauncherImpl0, StartStopToken startStopToken0, RuntimeExtras workerParameters$RuntimeExtras0) {
        this.f$0 = workLauncherImpl0;
        this.f$1 = startStopToken0;
        this.f$2 = workerParameters$RuntimeExtras0;
    }

    @Override
    public final void run() {
        WorkLauncherImpl.$r8$lambda$IJHwq8ui46LmYGGj0wRpXW6AsK0(this.f$0, this.f$1, this.f$2);
    }
}

