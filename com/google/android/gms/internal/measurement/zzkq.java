package com.google.android.gms.internal.measurement;

final class zzkq {
    private static final zzko zza;
    private static final zzko zzb;

    static {
        zzkq.zza = new zzkp();
        zzko zzko0 = null;
        try {
            zzko0 = (zzko)Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(null).newInstance(null);
        }
        catch(Exception unused_ex) {
        }
        zzkq.zzb = zzko0;
    }

    static zzko zza() {
        zzko zzko0 = zzkq.zzb;
        if(zzko0 == null) {
            throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
        }
        return zzko0;
    }

    static zzko zzb() {
        return zzkq.zza;
    }
}

