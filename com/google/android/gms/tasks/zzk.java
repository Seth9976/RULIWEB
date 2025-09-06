package com.google.android.gms.tasks;

import com.google.android.gms.common.internal.Preconditions;

final class zzk implements Runnable {
    final Task zza;
    final zzl zzb;

    zzk(zzl zzl0, Task task0) {
        this.zzb = zzl0;
        this.zza = task0;
        super();
    }

    @Override
    public final void run() {
        synchronized(zzl.zzb(this.zzb)) {
            zzl zzl0 = this.zzb;
            if(zzl.zza(zzl0) != null) {
                zzl.zza(zzl0).onFailure(((Exception)Preconditions.checkNotNull(this.zza.getException())));
            }
        }
    }
}

