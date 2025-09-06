package com.google.android.gms.internal.measurement;

public final class zzpv implements zzpu {
    public static final zzib zza;
    public static final zzib zzb;
    public static final zzib zzc;
    public static final zzib zzd;

    static {
        zzhy zzhy0 = new zzhy(zzhq.zza("com.google.android.gms.measurement")).zza();
        zzpv.zza = zzhy0.zzf("measurement.sdk.collection.enable_extend_user_property_size", true);
        zzpv.zzb = zzhy0.zzf("measurement.sdk.collection.last_deep_link_referrer2", true);
        zzpv.zzc = zzhy0.zzf("measurement.sdk.collection.last_deep_link_referrer_campaign2", false);
        zzpv.zzd = zzhy0.zzd("measurement.id.sdk.collection.last_deep_link_referrer2", 0L);
    }

    @Override  // com.google.android.gms.internal.measurement.zzpu
    public final boolean zza() {
        return ((Boolean)zzpv.zzc.zzb()).booleanValue();
    }
}

