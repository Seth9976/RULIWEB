package com.google.android.gms.tasks;

final class zzs implements OnTokenCanceledListener {
    final TaskCompletionSource zza;

    zzs(TaskCompletionSource taskCompletionSource0) {
        this.zza = taskCompletionSource0;
        super();
    }

    @Override  // com.google.android.gms.tasks.OnTokenCanceledListener
    public final void onCanceled() {
        this.zza.zza.zzc();
    }
}

