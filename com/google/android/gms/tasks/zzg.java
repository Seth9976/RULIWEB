package com.google.android.gms.tasks;

final class zzg implements Runnable {
    final zzh zza;

    zzg(zzh zzh0) {
        this.zza = zzh0;
        super();
    }

    @Override
    public final void run() {
        synchronized(zzh.zzb(this.zza)) {
            zzh zzh0 = this.zza;
            if(zzh0.zzc != null) {
                zzh0.zzc.onCanceled();
            }
        }
    }
}

