package com.google.android.gms.internal.auth;

public enum zzho {
    DOUBLE(zzhp.zzd, 1),
    FLOAT(zzhp.zzc, 5),
    INT64(zzhp.zzb, 0),
    UINT64(zzhp.zzb, 0),
    INT32(zzhp.zza, 0),
    FIXED64(zzhp.zzb, 1),
    FIXED32(zzhp.zza, 5),
    BOOL(zzhp.zze, 0),
    STRING(zzhp.zzf, 2),
    GROUP(zzhp.zzi, 3),
    MESSAGE(zzhp.zzi, 2),
    BYTES(zzhp.zzg, 2),
    UINT32(zzhp.zza, 0),
    ENUM(zzhp.zzh, 0),
    SFIXED32(zzhp.zza, 5),
    SFIXED64(zzhp.zzb, 1),
    SINT32(zzhp.zza, 0),
    SINT64(zzhp.zzb, 0);

    private final zzhp zzt;

    private zzho(zzhp zzhp0, int v1) {
        this.zzt = zzhp0;
    }

    public final zzhp zza() {
        return this.zzt;
    }
}

