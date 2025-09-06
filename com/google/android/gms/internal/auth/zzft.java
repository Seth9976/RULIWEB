package com.google.android.gms.internal.auth;

final class zzft {
    private static final zzfs zza;
    private static final zzfs zzb;

    static {
        zzfs zzfs0 = null;
        try {
            zzfs0 = (zzfs)Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor(null).newInstance(null);
        }
        catch(Exception unused_ex) {
        }
        zzft.zza = zzfs0;
        zzft.zzb = new zzfs();
    }

    static zzfs zza() {
        return zzft.zza;
    }

    static zzfs zzb() {
        return zzft.zzb;
    }
}

