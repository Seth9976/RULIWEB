package com.google.android.gms.common.util.concurrent;

import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class NamedThreadFactory implements ThreadFactory {
    private final String zza;
    private final ThreadFactory zzb;

    public NamedThreadFactory(String s) {
        this.zzb = Executors.defaultThreadFactory();
        Preconditions.checkNotNull(s, "Name must not be null");
        this.zza = s;
    }

    @Override
    public final Thread newThread(Runnable runnable0) {
        zza zza0 = new zza(runnable0, 0);
        Thread thread0 = this.zzb.newThread(zza0);
        thread0.setName(this.zza);
        return thread0;
    }
}

