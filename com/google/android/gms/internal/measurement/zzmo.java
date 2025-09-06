package com.google.android.gms.internal.measurement;

final class zzmo {
    private static final zzmn zza;
    private static final zzmn zzb;

    static {
        zzmn zzmn0 = null;
        try {
            zzmn0 = (zzmn)Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(null).newInstance(null);
        }
        catch(Exception unused_ex) {
        }
        zzmo.zza = zzmn0;
        zzmo.zzb = new zzmn();
    }

    static zzmn zza() {
        return zzmo.zza;
    }

    static zzmn zzb() {
        return zzmo.zzb;
    }
}

