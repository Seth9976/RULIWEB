package com.google.firebase;

import android.os.SystemClock;

public abstract class StartupTime {
    public static StartupTime create(long v, long v1, long v2) {
        return new AutoValue_StartupTime(v, v1, v2);
    }

    public abstract long getElapsedRealtime();

    public abstract long getEpochMillis();

    public abstract long getUptimeMillis();

    public static StartupTime now() {
        return StartupTime.create(System.currentTimeMillis(), SystemClock.elapsedRealtime(), SystemClock.uptimeMillis());
    }
}

