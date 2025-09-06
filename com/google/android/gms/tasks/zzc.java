package com.google.android.gms.tasks;

final class zzc implements Runnable {
    final Task zza;
    final zzd zzb;

    zzc(zzd zzd0, Task task0) {
        this.zzb = zzd0;
        this.zza = task0;
        super();
    }

    @Override
    public final void run() {
        Object object0;
        if(this.zza.isCanceled()) {
            this.zzb.zzc.zzc();
            return;
        }
        try {
            object0 = this.zzb.zzb.then(this.zza);
        }
        catch(RuntimeExecutionException runtimeExecutionException0) {
            if(runtimeExecutionException0.getCause() instanceof Exception) {
                this.zzb.zzc.zza(((Exception)runtimeExecutionException0.getCause()));
                return;
            }
            this.zzb.zzc.zza(runtimeExecutionException0);
            return;
        }
        catch(Exception exception0) {
            this.zzb.zzc.zza(exception0);
            return;
        }
        this.zzb.zzc.zzb(object0);
    }
}

