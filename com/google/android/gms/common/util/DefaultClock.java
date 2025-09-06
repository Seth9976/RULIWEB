package com.google.android.gms.common.util;

import android.os.SystemClock;

public class DefaultClock implements Clock {
    private static final DefaultClock zza;

    static {
        DefaultClock.zza = new DefaultClock();
    }

    @Override  // com.google.android.gms.common.util.Clock
    public final long currentThreadTimeMillis() {
        return SystemClock.currentThreadTimeMillis();
    }

    @Override  // com.google.android.gms.common.util.Clock
    public final long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    @Override  // com.google.android.gms.common.util.Clock
    public final long elapsedRealtime() {
        return SystemClock.elapsedRealtime();
    }

    public static Clock getInstance() {
        return DefaultClock.zza;
    }

    @Override  // com.google.android.gms.common.util.Clock
    public final long nanoTime() {
        return System.nanoTime();
    }
}

