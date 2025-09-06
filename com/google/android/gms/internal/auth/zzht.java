package com.google.android.gms.internal.auth;

public final class zzht implements zzdj {
    private static final zzht zza;
    private final zzdj zzb;

    static {
        zzht.zza = new zzht();
    }

    public zzht() {
        zzdj zzdj0 = zzdo.zzb(new zzhv());
        super();
        this.zzb = zzdo.zza(zzdj0);
    }

    @Override  // com.google.android.gms.internal.auth.zzdj
    public final Object zza() {
        return this.zzb();
    }

    public final zzhu zzb() {
        return (zzhu)this.zzb.zza();
    }

    public static boolean zzc() {
        return zzht.zza.zzb().zza();
    }
}

