package com.google.android.gms.internal.measurement;

final class zzme {
    private static final zzmd zza;
    private static final zzmd zzb;

    static {
        zzmd zzmd0 = null;
        try {
            zzmd0 = (zzmd)Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor(null).newInstance(null);
        }
        catch(Exception unused_ex) {
        }
        zzme.zza = zzmd0;
        zzme.zzb = new zzmd();
    }

    static zzmd zza() {
        return zzme.zza;
    }

    static zzmd zzb() {
        return zzme.zzb;
    }
}

