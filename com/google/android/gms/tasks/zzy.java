package com.google.android.gms.tasks;

import com.google.android.gms.internal.tasks.zza;

public final class zzy implements OnCompleteListener {
    public final zza zza;
    public final TaskCompletionSource zzb;
    public final zzb zzc;

    public zzy(zza zza0, TaskCompletionSource taskCompletionSource0, zzb zzb0) {
        this.zza = zza0;
        this.zzb = taskCompletionSource0;
        this.zzc = zzb0;
    }

    @Override  // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(Task task0) {
        this.zza.removeCallbacksAndMessages(null);
        TaskCompletionSource taskCompletionSource0 = this.zzb;
        if(task0.isSuccessful()) {
            taskCompletionSource0.trySetResult(task0.getResult());
            return;
        }
        if(task0.isCanceled()) {
            this.zzc.zza();
            return;
        }
        Exception exception0 = task0.getException();
        exception0.getClass();
        taskCompletionSource0.trySetException(exception0);
    }
}

