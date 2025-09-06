package androidx.work.impl.model;

import kotlin.jvm.internal.Intrinsics;

public final class SystemIdInfoDao.-CC {
    public static SystemIdInfo $default$getSystemIdInfo(SystemIdInfoDao _this, WorkGenerationalId workGenerationalId0) {
        Intrinsics.checkNotNullParameter(workGenerationalId0, "id");
        return _this.getSystemIdInfo(workGenerationalId0.getWorkSpecId(), workGenerationalId0.getGeneration());
    }

    public static void $default$removeSystemIdInfo(SystemIdInfoDao _this, WorkGenerationalId workGenerationalId0) {
        Intrinsics.checkNotNullParameter(workGenerationalId0, "id");
        _this.removeSystemIdInfo(workGenerationalId0.getWorkSpecId(), workGenerationalId0.getGeneration());
    }
}

