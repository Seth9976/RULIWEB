package com.google.android.gms.internal.measurement;

abstract class zzlw {
    private static final zzlw zza;
    private static final zzlw zzb;

    static {
        zzlw.zza = new zzls(null);
        zzlw.zzb = new zzlu(null);
    }

    zzlw(zzlv zzlv0) {
    }

    abstract void zza(Object arg1, long arg2);

    abstract void zzb(Object arg1, Object arg2, long arg3);

    static zzlw zzc() {
        return zzlw.zza;
    }

    static zzlw zzd() {
        return zzlw.zzb;
    }
}

