package com.google.android.gms.measurement.internal;

final class zzfa implements Runnable {
    final boolean zza;
    final zzfb zzb;

    zzfa(zzfb zzfb0, boolean z) {
        this.zzb = zzfb0;
        this.zza = z;
        super();
    }

    @Override
    public final void run() {
        zzfb.zza(this.zzb).zzJ(this.zza);
    }
}

