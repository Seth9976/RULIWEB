package com.google.android.gms.internal.measurement;

import java.util.concurrent.Callable;

public final class zzb implements Callable {
    public final zzc zza;

    public zzb(zzc zzc0) {
        this.zza = zzc0;
    }

    @Override
    public final Object call() {
        return new zzk(this.zza.zzc);
    }
}

