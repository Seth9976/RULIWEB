package com.google.android.gms.measurement.internal;

abstract class zzf extends zze {
    private boolean zza;

    zzf(zzgd zzgd0) {
        super(zzgd0);
        this.zzt.zzD();
    }

    protected final void zza() {
        if(!this.zze()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void zzb() {
        if(this.zza) {
            throw new IllegalStateException("Can\'t initialize twice");
        }
        if(!this.zzf()) {
            this.zzt.zzB();
            this.zza = true;
        }
    }

    public final void zzc() {
        if(this.zza) {
            throw new IllegalStateException("Can\'t initialize twice");
        }
        this.zzd();
        this.zzt.zzB();
        this.zza = true;
    }

    protected void zzd() {
    }

    final boolean zze() {
        return this.zza;
    }

    protected abstract boolean zzf();
}

