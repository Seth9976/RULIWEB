package com.google.android.gms.common;

import java.util.concurrent.Callable;

public final class zze implements Callable {
    public final boolean zza;
    public final String zzb;
    public final zzj zzc;

    public zze(boolean z, String s, zzj zzj0) {
        this.zza = z;
        this.zzb = s;
        this.zzc = zzj0;
    }

    @Override
    public final Object call() {
        return zzn.zzd(this.zza, this.zzb, this.zzc);
    }
}

