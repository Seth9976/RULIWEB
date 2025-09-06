package com.google.android.gms.tasks;

import java.util.concurrent.Executor;
import javax.annotation.Nullable;

final class zzn implements zzq {
    private final Executor zza;
    private final Object zzb;
    @Nullable
    private OnSuccessListener zzc;

    public zzn(Executor executor0, OnSuccessListener onSuccessListener0) {
        this.zzb = new Object();
        this.zza = executor0;
        this.zzc = onSuccessListener0;
    }

    static OnSuccessListener zza(zzn zzn0) {
        return zzn0.zzc;
    }

    static Object zzb(zzn zzn0) {
        return zzn0.zzb;
    }

    @Override  // com.google.android.gms.tasks.zzq
    public final void zzc() {
        synchronized(this.zzb) {
            this.zzc = null;
        }
    }

    @Override  // com.google.android.gms.tasks.zzq
    public final void zzd(Task task0) {
        if(task0.isSuccessful()) {
            Object object0 = this.zzb;
            synchronized(object0) {
                if(this.zzc == null) {
                    return;
                }
            }
            zzm zzm0 = new zzm(this, task0);
            this.zza.execute(zzm0);
        }
    }
}

