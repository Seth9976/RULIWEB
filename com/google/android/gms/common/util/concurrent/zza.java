package com.google.android.gms.common.util.concurrent;

import android.os.Process;

final class zza implements Runnable {
    private final Runnable zza;

    public zza(Runnable runnable0, int v) {
        this.zza = runnable0;
    }

    @Override
    public final void run() {
        Process.setThreadPriority(0);
        this.zza.run();
    }
}

