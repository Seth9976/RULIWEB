package com.google.android.gms.common.stats;

import android.content.Context;
import android.content.Intent;
import java.util.List;

@Deprecated
public class WakeLockTracker {
    private static final WakeLockTracker zza;

    static {
        WakeLockTracker.zza = new WakeLockTracker();
    }

    public static WakeLockTracker getInstance() {
        return WakeLockTracker.zza;
    }

    public void registerAcquireEvent(Context context0, Intent intent0, String s, String s1, String s2, int v, String s3) {
    }

    public void registerDeadlineEvent(Context context0, String s, String s1, String s2, int v, List list0, boolean z, long v1) {
    }

    public void registerEvent(Context context0, String s, int v, String s1, String s2, String s3, int v1, List list0) {
    }

    public void registerEvent(Context context0, String s, int v, String s1, String s2, String s3, int v1, List list0, long v2) {
    }

    public void registerReleaseEvent(Context context0, Intent intent0) {
    }
}

