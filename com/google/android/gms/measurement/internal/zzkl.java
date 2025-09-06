package com.google.android.gms.measurement.internal;

final class zzkl {
    final zzkp zza;
    private zzkk zzb;

    zzkl(zzkp zzkp0) {
        this.zza = zzkp0;
        super();
    }

    final void zza(long v) {
        this.zzb = new zzkk(this, this.zza.zzt.zzax().currentTimeMillis(), v);
        zzkp.zzh(this.zza).postDelayed(this.zzb, 2000L);
    }

    final void zzb() {
        this.zza.zzg();
        zzkk zzkk0 = this.zzb;
        if(zzkk0 != null) {
            zzkp.zzh(this.zza).removeCallbacks(zzkk0);
        }
        this.zza.zzt.zzm().zzm.zza(false);
        this.zza.zzm(false);
    }
}

