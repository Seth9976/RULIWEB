package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

final class zzf implements OnCanceledListener, OnFailureListener, OnSuccessListener, zzq {
    private final Executor zza;
    private final Continuation zzb;
    private final zzw zzc;

    public zzf(Executor executor0, Continuation continuation0, zzw zzw0) {
        this.zza = executor0;
        this.zzb = continuation0;
        this.zzc = zzw0;
    }

    @Override  // com.google.android.gms.tasks.OnCanceledListener
    public final void onCanceled() {
        this.zzc.zzc();
    }

    @Override  // com.google.android.gms.tasks.OnFailureListener
    public final void onFailure(Exception exception0) {
        this.zzc.zza(exception0);
    }

    @Override  // com.google.android.gms.tasks.OnSuccessListener
    public final void onSuccess(Object object0) {
        this.zzc.zzb(object0);
    }

    @Override  // com.google.android.gms.tasks.zzq
    public final void zzc() {
        throw new UnsupportedOperationException();
    }

    @Override  // com.google.android.gms.tasks.zzq
    public final void zzd(Task task0) {
        zze zze0 = new zze(this, task0);
        this.zza.execute(zze0);
    }
}

