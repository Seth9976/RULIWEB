package com.google.android.gms.measurement.internal;

abstract class zzku extends zzkt {
    private boolean zza;

    zzku(zzlh zzlh0) {
        super(zzlh0);
        this.zzf.zzM();
    }

    protected final void zzW() {
        if(!this.zzY()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void zzX() {
        if(this.zza) {
            throw new IllegalStateException("Can\'t initialize twice");
        }
        this.zzb();
        this.zzf.zzH();
        this.zza = true;
    }

    final boolean zzY() {
        return this.zza;
    }

    protected abstract boolean zzb();
}

