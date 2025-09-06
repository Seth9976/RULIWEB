package com.google.android.gms.tasks;

import java.util.concurrent.CancellationException;

final class zzo implements Runnable {
    final Task zza;
    final zzp zzb;

    zzo(zzp zzp0, Task task0) {
        this.zzb = zzp0;
        this.zza = task0;
        super();
    }

    @Override
    public final void run() {
        Task task0;
        try {
            task0 = zzp.zza(this.zzb).then(this.zza.getResult());
        }
        catch(RuntimeExecutionException runtimeExecutionException0) {
            if(runtimeExecutionException0.getCause() instanceof Exception) {
                this.zzb.onFailure(((Exception)runtimeExecutionException0.getCause()));
                return;
            }
            this.zzb.onFailure(runtimeExecutionException0);
            return;
        }
        catch(CancellationException unused_ex) {
            this.zzb.onCanceled();
            return;
        }
        catch(Exception exception0) {
            this.zzb.onFailure(exception0);
            return;
        }
        if(task0 == null) {
            NullPointerException nullPointerException0 = new NullPointerException("Continuation returned null");
            this.zzb.onFailure(nullPointerException0);
            return;
        }
        task0.addOnSuccessListener(TaskExecutors.zza, this.zzb);
        task0.addOnFailureListener(TaskExecutors.zza, this.zzb);
        task0.addOnCanceledListener(TaskExecutors.zza, this.zzb);
    }
}

