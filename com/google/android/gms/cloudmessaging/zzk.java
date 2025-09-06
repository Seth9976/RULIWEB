package com.google.android.gms.cloudmessaging;

public final class zzk implements Runnable {
    public final zzm zza;
    public final zzp zzb;

    public zzk(zzm zzm0, zzp zzp0) {
        this.zza = zzm0;
        this.zzb = zzp0;
    }

    @Override
    public final void run() {
        this.zza.zze(this.zzb.zza);
    }
}

