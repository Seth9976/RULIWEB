package com.google.android.gms.internal.auth;

abstract class zzfl {
    private static final zzfl zza;
    private static final zzfl zzb;

    static {
        zzfl.zza = new zzfh(null);
        zzfl.zzb = new zzfj(null);
    }

    zzfl(zzfk zzfk0) {
    }

    abstract void zza(Object arg1, long arg2);

    abstract void zzb(Object arg1, Object arg2, long arg3);

    static zzfl zzc() {
        return zzfl.zza;
    }

    static zzfl zzd() {
        return zzfl.zzb;
    }
}

