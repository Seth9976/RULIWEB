package com.google.android.gms.internal.measurement;

public enum zzkt {
    DOUBLE(0, 1, zzlm.zze),
    FLOAT(1, 1, zzlm.zzd),
    INT64(2, 1, zzlm.zzc),
    UINT64(3, 1, zzlm.zzc),
    INT32(4, 1, zzlm.zzb),
    FIXED64(5, 1, zzlm.zzc),
    FIXED32(6, 1, zzlm.zzb),
    BOOL(7, 1, zzlm.zzf),
    STRING(8, 1, zzlm.zzg),
    MESSAGE(9, 1, zzlm.zzj),
    BYTES(10, 1, zzlm.zzh),
    UINT32(11, 1, zzlm.zzb),
    ENUM(12, 1, zzlm.zzi),
    SFIXED32(13, 1, zzlm.zzb),
    SFIXED64(14, 1, zzlm.zzc),
    SINT32(15, 1, zzlm.zzb),
    SINT64(16, 1, zzlm.zzc),
    GROUP(17, 1, zzlm.zzj),
    DOUBLE_LIST(18, 2, zzlm.zze),
    FLOAT_LIST(19, 2, zzlm.zzd),
    INT64_LIST(20, 2, zzlm.zzc),
    UINT64_LIST(21, 2, zzlm.zzc),
    INT32_LIST(22, 2, zzlm.zzb),
    FIXED64_LIST(23, 2, zzlm.zzc),
    FIXED32_LIST(24, 2, zzlm.zzb),
    BOOL_LIST(25, 2, zzlm.zzf),
    STRING_LIST(26, 2, zzlm.zzg),
    MESSAGE_LIST(27, 2, zzlm.zzj),
    BYTES_LIST(28, 2, zzlm.zzh),
    UINT32_LIST(29, 2, zzlm.zzb),
    ENUM_LIST(30, 2, zzlm.zzi),
    SFIXED32_LIST(0x1F, 2, zzlm.zzb),
    SFIXED64_LIST(0x20, 2, zzlm.zzc),
    SINT32_LIST(33, 2, zzlm.zzb),
    SINT64_LIST(34, 2, zzlm.zzc),
    DOUBLE_LIST_PACKED(35, 3, zzlm.zze),
    FLOAT_LIST_PACKED(36, 3, zzlm.zzd),
    INT64_LIST_PACKED(37, 3, zzlm.zzc),
    UINT64_LIST_PACKED(38, 3, zzlm.zzc),
    INT32_LIST_PACKED(39, 3, zzlm.zzb),
    FIXED64_LIST_PACKED(40, 3, zzlm.zzc),
    FIXED32_LIST_PACKED(41, 3, zzlm.zzb),
    BOOL_LIST_PACKED(42, 3, zzlm.zzf),
    UINT32_LIST_PACKED(43, 3, zzlm.zzb),
    ENUM_LIST_PACKED(44, 3, zzlm.zzi),
    SFIXED32_LIST_PACKED(45, 3, zzlm.zzb),
    SFIXED64_LIST_PACKED(46, 3, zzlm.zzc),
    SINT32_LIST_PACKED(0x2F, 3, zzlm.zzb),
    SINT64_LIST_PACKED(0x30, 3, zzlm.zzc),
    GROUP_LIST(49, 2, zzlm.zzj),
    MAP(50, 4, zzlm.zza);

    private static final zzkt[] zzZ;
    private final zzlm zzab;
    private final int zzac;
    private final Class zzad;

    static {
        zzkt[] arr_zzkt = (zzkt[])zzkt.zzaa.clone();
        zzkt.zzZ = new zzkt[arr_zzkt.length];
        for(int v = 0; v < arr_zzkt.length; ++v) {
            zzkt zzkt0 = arr_zzkt[v];
            zzkt.zzZ[zzkt0.zzac] = zzkt0;
        }
    }

    private zzkt(int v1, int v2, zzlm zzlm0) {
        this.zzac = v1;
        this.zzab = zzlm0;
        switch(v2 - 1) {
            case 1: {
                this.zzad = zzlm0.zza();
                break;
            }
            case 3: {
                this.zzad = zzlm0.zza();
                break;
            }
            default: {
                this.zzad = null;
            }
        }
        if(v2 == 1) {
            zzlm0.ordinal();
        }
    }

    public final int zza() {
        return this.zzac;
    }
}

