package com.google.android.gms.tasks;

import java.util.concurrent.Executor;
import javax.annotation.Nullable;

final class zzh implements zzq {
    private final Executor zza;
    private final Object zzb;
    @Nullable
    private OnCanceledListener zzc;

    public zzh(Executor executor0, OnCanceledListener onCanceledListener0) {
        this.zzb = new Object();
        this.zza = executor0;
        this.zzc = onCanceledListener0;
    }

    static Object zzb(zzh zzh0) {
        return zzh0.zzb;
    }

    @Override  // com.google.android.gms.tasks.zzq
    public final void zzc() {
        synchronized(this.zzb) {
            this.zzc = null;
        }
    }

    @Override  // com.google.android.gms.tasks.zzq
    public final void zzd(Task task0) {
        if(task0.isCanceled()) {
            Object object0 = this.zzb;
            synchronized(object0) {
                if(this.zzc == null) {
                    return;
                }
            }
            zzg zzg0 = new zzg(this);
            this.zza.execute(zzg0);
        }
    }
}

