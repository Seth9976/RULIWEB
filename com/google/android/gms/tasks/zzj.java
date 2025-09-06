package com.google.android.gms.tasks;

import java.util.concurrent.Executor;
import javax.annotation.Nullable;

final class zzj implements zzq {
    private final Executor zza;
    private final Object zzb;
    @Nullable
    private OnCompleteListener zzc;

    public zzj(Executor executor0, OnCompleteListener onCompleteListener0) {
        this.zzb = new Object();
        this.zza = executor0;
        this.zzc = onCompleteListener0;
    }

    static OnCompleteListener zza(zzj zzj0) {
        return zzj0.zzc;
    }

    static Object zzb(zzj zzj0) {
        return zzj0.zzb;
    }

    @Override  // com.google.android.gms.tasks.zzq
    public final void zzc() {
        synchronized(this.zzb) {
            this.zzc = null;
        }
    }

    @Override  // com.google.android.gms.tasks.zzq
    public final void zzd(Task task0) {
        synchronized(this.zzb) {
            if(this.zzc == null) {
                return;
            }
        }
        zzi zzi0 = new zzi(this, task0);
        this.zza.execute(zzi0);
    }
}

