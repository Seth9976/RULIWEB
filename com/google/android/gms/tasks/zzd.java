package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

final class zzd implements zzq {
    private final Executor zza;
    private final Continuation zzb;
    private final zzw zzc;

    public zzd(Executor executor0, Continuation continuation0, zzw zzw0) {
        this.zza = executor0;
        this.zzb = continuation0;
        this.zzc = zzw0;
    }

    @Override  // com.google.android.gms.tasks.zzq
    public final void zzc() {
        throw new UnsupportedOperationException();
    }

    @Override  // com.google.android.gms.tasks.zzq
    public final void zzd(Task task0) {
        zzc zzc0 = new zzc(this, task0);
        this.zza.execute(zzc0);
    }
}

