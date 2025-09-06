package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzu;
import java.util.concurrent.Callable;

public final class zzfp implements Callable {
    public final zzfu zza;
    public final String zzb;

    public zzfp(zzfu zzfu0, String s) {
        this.zza = zzfu0;
        this.zzb = s;
    }

    @Override
    public final Object call() {
        return new zzu("internal.appMetadata", new zzfn(this.zza, this.zzb));
    }
}

