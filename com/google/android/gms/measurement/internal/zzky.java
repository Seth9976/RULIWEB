package com.google.android.gms.measurement.internal;

import java.util.Map;

final class zzky implements zzev {
    final String zza;
    final zzlh zzb;

    zzky(zzlh zzlh0, String s) {
        this.zzb = zzlh0;
        this.zza = s;
        super();
    }

    @Override  // com.google.android.gms.measurement.internal.zzev
    public final void zza(String s, int v, Throwable throwable0, byte[] arr_b, Map map0) {
        this.zzb.zzK(v, throwable0, arr_b, this.zza);
    }
}

