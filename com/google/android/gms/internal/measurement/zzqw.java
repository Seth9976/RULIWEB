package com.google.android.gms.internal.measurement;

public final class zzqw implements zzqv {
    public static final zzib zza;
    public static final zzib zzb;
    public static final zzib zzc;
    public static final zzib zzd;

    static {
        zzhy zzhy0 = new zzhy(zzhq.zza("com.google.android.gms.measurement")).zzb().zza();
        zzqw.zza = zzhy0.zzf("measurement.collection.enable_session_stitching_token.client.dev", true);
        zzqw.zzb = zzhy0.zzf("measurement.collection.enable_session_stitching_token.first_open_fix", true);
        zzqw.zzc = zzhy0.zzf("measurement.session_stitching_token_enabled", false);
        zzqw.zzd = zzhy0.zzf("measurement.link_sst_to_sid", true);
    }

    @Override  // com.google.android.gms.internal.measurement.zzqv
    public final boolean zza() {
        return true;
    }

    @Override  // com.google.android.gms.internal.measurement.zzqv
    public final boolean zzb() {
        return ((Boolean)zzqw.zza.zzb()).booleanValue();
    }

    @Override  // com.google.android.gms.internal.measurement.zzqv
    public final boolean zzc() {
        return ((Boolean)zzqw.zzb.zzb()).booleanValue();
    }

    @Override  // com.google.android.gms.internal.measurement.zzqv
    public final boolean zzd() {
        return ((Boolean)zzqw.zzc.zzb()).booleanValue();
    }

    @Override  // com.google.android.gms.internal.measurement.zzqv
    public final boolean zze() {
        return ((Boolean)zzqw.zzd.zzb()).booleanValue();
    }
}

