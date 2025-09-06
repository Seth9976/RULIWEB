package com.google.android.gms.tasks;

final class zzm implements Runnable {
    final Task zza;
    final zzn zzb;

    zzm(zzn zzn0, Task task0) {
        this.zzb = zzn0;
        this.zza = task0;
        super();
    }

    @Override
    public final void run() {
        synchronized(zzn.zzb(this.zzb)) {
            zzn zzn0 = this.zzb;
            if(zzn.zza(zzn0) != null) {
                zzn.zza(zzn0).onSuccess(this.zza.getResult());
            }
        }
    }
}

