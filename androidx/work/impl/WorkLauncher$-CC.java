package androidx.work.impl;

import kotlin.jvm.internal.Intrinsics;

public final class WorkLauncher.-CC {
    public static void $default$startWork(WorkLauncher _this, StartStopToken startStopToken0) {
        Intrinsics.checkNotNullParameter(startStopToken0, "workSpecId");
        _this.startWork(startStopToken0, null);
    }

    public static void $default$stopWork(WorkLauncher _this, StartStopToken startStopToken0) {
        Intrinsics.checkNotNullParameter(startStopToken0, "workSpecId");
        _this.stopWork(startStopToken0, 0xFFFFFE00);
    }

    public static void $default$stopWorkWithReason(WorkLauncher _this, StartStopToken startStopToken0, int v) {
        Intrinsics.checkNotNullParameter(startStopToken0, "workSpecId");
        _this.stopWork(startStopToken0, v);
    }
}

