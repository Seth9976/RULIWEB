package com.google.android.gms.internal.measurement;

public final class zzqz implements zzqy {
    public static final zzib zza;
    public static final zzib zzb;
    public static final zzib zzc;
    public static final zzib zzd;
    public static final zzib zze;
    public static final zzib zzf;

    static {
        zzhy zzhy0 = new zzhy(zzhq.zza("com.google.android.gms.measurement")).zzb().zza();
        zzqz.zza = zzhy0.zzf("measurement.client.sessions.background_sessions_enabled", true);
        zzqz.zzb = zzhy0.zzf("measurement.client.sessions.enable_fix_background_engagement", false);
        zzqz.zzc = zzhy0.zzf("measurement.client.sessions.immediate_start_enabled_foreground", true);
        zzqz.zzd = zzhy0.zzf("measurement.client.sessions.remove_expired_session_properties_enabled", true);
        zzqz.zze = zzhy0.zzf("measurement.client.sessions.session_id_enabled", true);
        zzqz.zzf = zzhy0.zzd("measurement.id.client.sessions.enable_fix_background_engagement", 0L);
    }

    @Override  // com.google.android.gms.internal.measurement.zzqy
    public final boolean zza() {
        return ((Boolean)zzqz.zzb.zzb()).booleanValue();
    }
}

