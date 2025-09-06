package com.google.firebase.analytics.connector;

import java.util.concurrent.Executor;

public final class zza implements Executor {
    public static final zza zza;

    static {
        zza.zza = new zza();
    }

    @Override
    public final void execute(Runnable runnable0) {
        runnable0.run();
    }
}

