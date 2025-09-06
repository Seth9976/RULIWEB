package com.google.android.gms.internal.auth;

final class zzgd {
    private static final zzgc zza;
    private static final zzgc zzb;

    static {
        zzgc zzgc0 = null;
        try {
            zzgc0 = (zzgc)Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(null).newInstance(null);
        }
        catch(Exception unused_ex) {
        }
        zzgd.zza = zzgc0;
        zzgd.zzb = new zzgc();
    }

    static zzgc zza() {
        return zzgd.zza;
    }

    static zzgc zzb() {
        return zzgd.zzb;
    }
}

