package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzt;
import java.util.concurrent.Callable;

public final class zzfq implements Callable {
    public final zzfu zza;

    public zzfq(zzfu zzfu0) {
        this.zza = zzfu0;
    }

    @Override
    public final Object call() {
        return new zzt(this.zza.zze);
    }
}

