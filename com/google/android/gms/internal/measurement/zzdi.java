package com.google.android.gms.internal.measurement;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

final class zzdi implements ThreadFactory {
    private final ThreadFactory zza;

    zzdi(zzef zzef0) {
        this.zza = Executors.defaultThreadFactory();
    }

    @Override
    public final Thread newThread(Runnable runnable0) {
        Thread thread0 = this.zza.newThread(runnable0);
        thread0.setName("ScionFrontendApi");
        return thread0;
    }
}

