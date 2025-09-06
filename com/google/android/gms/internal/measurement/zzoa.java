package com.google.android.gms.internal.measurement;

public enum zzoa {
    DOUBLE(zzob.zzd, 1),
    FLOAT(zzob.zzc, 5),
    INT64(zzob.zzb, 0),
    UINT64(zzob.zzb, 0),
    INT32(zzob.zza, 0),
    FIXED64(zzob.zzb, 1),
    FIXED32(zzob.zza, 5),
    BOOL(zzob.zze, 0),
    STRING(zzob.zzf, 2),
    GROUP(zzob.zzi, 3),
    MESSAGE(zzob.zzi, 2),
    BYTES(zzob.zzg, 2),
    UINT32(zzob.zza, 0),
    ENUM(zzob.zzh, 0),
    SFIXED32(zzob.zza, 5),
    SFIXED64(zzob.zzb, 1),
    SINT32(zzob.zza, 0),
    SINT64(zzob.zzb, 0);

    private final zzob zzt;

    private zzoa(zzob zzob0, int v1) {
        this.zzt = zzob0;
    }

    public final zzob zza() {
        return this.zzt;
    }
}

