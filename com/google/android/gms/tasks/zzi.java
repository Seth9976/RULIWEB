package com.google.android.gms.tasks;

final class zzi implements Runnable {
    final Task zza;
    final zzj zzb;

    zzi(zzj zzj0, Task task0) {
        this.zzb = zzj0;
        this.zza = task0;
        super();
    }

    @Override
    public final void run() {
        synchronized(zzj.zzb(this.zzb)) {
            zzj zzj0 = this.zzb;
            if(zzj.zza(zzj0) != null) {
                zzj.zza(zzj0).onComplete(this.zza);
            }
        }
    }
}

