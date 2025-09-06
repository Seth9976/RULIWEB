package com.google.android.gms.measurement.internal;

abstract class zzgx extends zzgw {
    private boolean zza;

    zzgx(zzgd zzgd0) {
        super(zzgd0);
        this.zzt.zzD();
    }

    protected void zzaC() {
    }

    protected abstract boolean zzf();

    protected final void zzv() {
        if(!this.zzy()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void zzw() {
        if(this.zza) {
            throw new IllegalStateException("Can\'t initialize twice");
        }
        if(!this.zzf()) {
            this.zzt.zzB();
            this.zza = true;
        }
    }

    public final void zzx() {
        if(this.zza) {
            throw new IllegalStateException("Can\'t initialize twice");
        }
        this.zzaC();
        this.zzt.zzB();
        this.zza = true;
    }

    final boolean zzy() {
        return this.zza;
    }
}

