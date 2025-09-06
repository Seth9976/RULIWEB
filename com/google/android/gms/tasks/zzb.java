package com.google.android.gms.tasks;

final class zzb extends CancellationToken {
    private final zzw zza;

    zzb() {
        this.zza = new zzw();
    }

    @Override  // com.google.android.gms.tasks.CancellationToken
    public final boolean isCancellationRequested() {
        return this.zza.isComplete();
    }

    @Override  // com.google.android.gms.tasks.CancellationToken
    public final CancellationToken onCanceledRequested(OnTokenCanceledListener onTokenCanceledListener0) {
        zza zza0 = new zza(this, onTokenCanceledListener0);
        this.zza.addOnSuccessListener(TaskExecutors.MAIN_THREAD, zza0);
        return this;
    }

    public final void zza() {
        this.zza.zze(null);
    }
}

