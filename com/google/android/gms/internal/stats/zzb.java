package com.google.android.gms.internal.stats;

import java.io.Closeable;

public final class zzb implements Closeable, AutoCloseable {
    private static final zzb zza;

    static {
        zzb.zza = new zzb(false, null);
    }

    private zzb(boolean z, zzd zzd0) {
    }

    @Override
    public final void close() {
    }

    public static zzb zza(boolean z, zzc zzc0) {
        return zzb.zza;
    }
}

