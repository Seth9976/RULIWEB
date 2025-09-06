package com.google.android.gms.measurement.internal;

final class zzam implements Runnable {
    final zzgy zza;
    final zzan zzb;

    zzam(zzan zzan0, zzgy zzgy0) {
        this.zzb = zzan0;
        this.zza = zzgy0;
        super();
    }

    @Override
    public final void run() {
        this.zza.zzay();
        if(zzab.zza()) {
            this.zza.zzaB().zzp(this);
            return;
        }
        boolean z = this.zzb.zze();
        zzan.zza(this.zzb, 0L);
        if(z) {
            this.zzb.zzc();
        }
    }
}

