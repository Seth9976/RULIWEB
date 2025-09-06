package com.google.android.gms.tasks;

import java.util.concurrent.Executor;
import javax.annotation.Nullable;

final class zzl implements zzq {
    private final Executor zza;
    private final Object zzb;
    @Nullable
    private OnFailureListener zzc;

    public zzl(Executor executor0, OnFailureListener onFailureListener0) {
        this.zzb = new Object();
        this.zza = executor0;
        this.zzc = onFailureListener0;
    }

    static OnFailureListener zza(zzl zzl0) {
        return zzl0.zzc;
    }

    static Object zzb(zzl zzl0) {
        return zzl0.zzb;
    }

    @Override  // com.google.android.gms.tasks.zzq
    public final void zzc() {
        synchronized(this.zzb) {
            this.zzc = null;
        }
    }

    @Override  // com.google.android.gms.tasks.zzq
    public final void zzd(Task task0) {
        if(!task0.isSuccessful() && !task0.isCanceled()) {
            Object object0 = this.zzb;
            synchronized(object0) {
                if(this.zzc == null) {
                    return;
                }
            }
            zzk zzk0 = new zzk(this, task0);
            this.zza.execute(zzk0);
        }
    }
}

