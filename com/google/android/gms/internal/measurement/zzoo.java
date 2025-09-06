package com.google.android.gms.internal.measurement;

public final class zzoo implements zzon {
    public static final zzib zza;
    public static final zzib zzb;
    public static final zzib zzc;
    public static final zzib zzd;

    static {
        zzhy zzhy0 = new zzhy(zzhq.zza("com.google.android.gms.measurement")).zza();
        zzoo.zza = zzhy0.zzf("measurement.client.consent_state_v1", true);
        zzoo.zzb = zzhy0.zzf("measurement.client.3p_consent_state_v1", true);
        zzoo.zzc = zzhy0.zzf("measurement.service.consent_state_v1_W36", true);
        zzoo.zzd = zzhy0.zzd("measurement.service.storage_consent_support_version", 203600L);
    }

    @Override  // com.google.android.gms.internal.measurement.zzon
    public final long zza() {
        return (long)(((Long)zzoo.zzd.zzb()));
    }
}

