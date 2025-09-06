package com.google.android.gms.tasks;

public class TaskCompletionSource {
    private final zzw zza;

    public TaskCompletionSource() {
        this.zza = new zzw();
    }

    public TaskCompletionSource(CancellationToken cancellationToken0) {
        this.zza = new zzw();
        cancellationToken0.onCanceledRequested(new zzs(this));
    }

    public Task getTask() {
        return this.zza;
    }

    public void setException(Exception exception0) {
        this.zza.zza(exception0);
    }

    public void setResult(Object object0) {
        this.zza.zzb(object0);
    }

    public boolean trySetException(Exception exception0) {
        return this.zza.zzd(exception0);
    }

    public boolean trySetResult(Object object0) {
        return this.zza.zze(object0);
    }
}

