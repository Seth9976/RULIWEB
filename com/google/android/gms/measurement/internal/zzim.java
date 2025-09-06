package com.google.android.gms.measurement.internal;

import java.util.Map;

public final class zzim implements Runnable {
    public final zzin zza;
    public final int zzb;
    public final Exception zzc;
    public final byte[] zzd;
    public final Map zze;

    public zzim(zzin zzin0, int v, Exception exception0, byte[] arr_b, Map map0) {
        this.zza = zzin0;
        this.zzb = v;
        this.zzc = exception0;
        this.zzd = arr_b;
        this.zze = map0;
    }

    @Override
    public final void run() {
        this.zza.zza(this.zzb, this.zzc, this.zzd, this.zze);
    }
}

