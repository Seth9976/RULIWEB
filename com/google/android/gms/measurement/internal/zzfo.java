package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzn;
import java.util.concurrent.Callable;

public final class zzfo implements Callable {
    public final zzfu zza;
    public final String zzb;

    public zzfo(zzfu zzfu0, String s) {
        this.zza = zzfu0;
        this.zzb = s;
    }

    @Override
    public final Object call() {
        return new zzn("internal.remoteConfig", new zzft(this.zza, this.zzb));
    }
}

