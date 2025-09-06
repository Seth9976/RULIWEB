package com.google.android.gms.internal.auth;

public class zzet extends zzdp {
    protected zzev zza;
    private final zzev zzb;

    protected zzet(zzev zzev0) {
        this.zzb = zzev0;
        if(zzev0.zzm()) {
            throw new IllegalArgumentException("Default instance must be immutable.");
        }
        this.zza = zzev0.zzc();
    }

    @Override  // com.google.android.gms.internal.auth.zzdp
    public final Object clone() throws CloneNotSupportedException {
        return this.zzb();
    }

    @Override  // com.google.android.gms.internal.auth.zzdp
    public final zzdp zza() {
        return this.zzb();
    }

    public final zzet zzb() {
        zzet zzet0 = (zzet)this.zzb.zzn(5, null, null);
        zzet0.zza = this.zzc();
        return zzet0;
    }

    public zzev zzc() {
        if(!this.zza.zzm()) {
            return this.zza;
        }
        this.zza.zzi();
        return this.zza;
    }

    @Override  // com.google.android.gms.internal.auth.zzfw
    public zzfx zzd() {
        return this.zzc();
    }

    @Override  // com.google.android.gms.internal.auth.zzfy
    public final zzfx zze() {
        throw null;
    }
}

