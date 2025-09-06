package com.google.android.gms.internal.measurement;

public final class zzqb implements zzqa {
    public static final zzib zza;
    public static final zzib zzb;

    static {
        zzhy zzhy0 = new zzhy(zzhq.zza("com.google.android.gms.measurement")).zzb().zza();
        zzqb.zza = zzhy0.zzf("measurement.collection.client.log_target_api_version", true);
        zzqb.zzb = zzhy0.zzf("measurement.collection.service.log_target_api_version", true);
    }

    @Override  // com.google.android.gms.internal.measurement.zzqa
    public final boolean zza() {
        return true;
    }

    @Override  // com.google.android.gms.internal.measurement.zzqa
    public final boolean zzb() {
        return ((Boolean)zzqb.zza.zzb()).booleanValue();
    }

    @Override  // com.google.android.gms.internal.measurement.zzqa
    public final boolean zzc() {
        return ((Boolean)zzqb.zzb.zzb()).booleanValue();
    }
}

