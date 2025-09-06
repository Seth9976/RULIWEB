package androidx.work.impl;

import androidx.work.WorkerParameters.RuntimeExtras;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u0005H\u0016J\u001A\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u00052\b\u0010\u0006\u001A\u0004\u0018\u00010\u0007H&J\u0010\u0010\b\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u0005H\u0016J\u0018\u0010\b\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u00052\u0006\u0010\t\u001A\u00020\nH&J\u0018\u0010\u000B\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u00052\u0006\u0010\t\u001A\u00020\nH\u0016ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\fÀ\u0006\u0001"}, d2 = {"Landroidx/work/impl/WorkLauncher;", "", "startWork", "", "workSpecId", "Landroidx/work/impl/StartStopToken;", "runtimeExtras", "Landroidx/work/WorkerParameters$RuntimeExtras;", "stopWork", "reason", "", "stopWorkWithReason", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public interface WorkLauncher {
    void startWork(StartStopToken arg1);

    void startWork(StartStopToken arg1, RuntimeExtras arg2);

    void stopWork(StartStopToken arg1);

    void stopWork(StartStopToken arg1, int arg2);

    void stopWorkWithReason(StartStopToken arg1, int arg2);
}

