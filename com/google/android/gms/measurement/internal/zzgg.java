package com.google.android.gms.measurement.internal;

final class zzgg implements Runnable {
    final zzac zza;
    final zzgv zzb;

    zzgg(zzgv zzgv0, zzac zzac0) {
        this.zzb = zzgv0;
        this.zza = zzac0;
        super();
    }

    @Override
    public final void run() {
        zzgv.zzc(this.zzb).zzA();
        if(this.zza.zzc.zza() == null) {
            zzgv.zzc(this.zzb).zzN(this.zza);
            return;
        }
        zzgv.zzc(this.zzb).zzT(this.zza);
    }
}

