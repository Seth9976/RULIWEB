package com.google.android.gms.tasks;

import java.util.concurrent.ExecutionException;

final class zzaf implements zzae {
    private final Object zza;
    private final int zzb;
    private final zzw zzc;
    private int zzd;
    private int zze;
    private int zzf;
    private Exception zzg;
    private boolean zzh;

    public zzaf(int v, zzw zzw0) {
        this.zza = new Object();
        this.zzb = v;
        this.zzc = zzw0;
    }

    @Override  // com.google.android.gms.tasks.OnCanceledListener
    public final void onCanceled() {
        synchronized(this.zza) {
            ++this.zzf;
            this.zzh = true;
            this.zza();
        }
    }

    @Override  // com.google.android.gms.tasks.OnFailureListener
    public final void onFailure(Exception exception0) {
        synchronized(this.zza) {
            ++this.zze;
            this.zzg = exception0;
            this.zza();
        }
    }

    @Override  // com.google.android.gms.tasks.OnSuccessListener
    public final void onSuccess(Object object0) {
        synchronized(this.zza) {
            ++this.zzd;
            this.zza();
        }
    }

    private final void zza() {
        if(this.zzd + this.zze + this.zzf == this.zzb) {
            if(this.zzg != null) {
                ExecutionException executionException0 = new ExecutionException(this.zze + " out of " + this.zzb + " underlying tasks failed", this.zzg);
                this.zzc.zza(executionException0);
                return;
            }
            if(this.zzh) {
                this.zzc.zzc();
                return;
            }
            this.zzc.zzb(null);
        }
    }
}

