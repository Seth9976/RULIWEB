package com.google.android.gms.internal.measurement;

public final class zzqn implements zzqm {
    public static final zzib zza;
    public static final zzib zzb;
    public static final zzib zzc;
    public static final zzib zzd;
    public static final zzib zze;
    public static final zzib zzf;
    public static final zzib zzg;
    public static final zzib zzh;
    public static final zzib zzi;
    public static final zzib zzj;
    public static final zzib zzk;
    public static final zzib zzl;
    public static final zzib zzm;
    public static final zzib zzn;

    static {
        zzhy zzhy0 = new zzhy(zzhq.zza("com.google.android.gms.measurement")).zzb().zza();
        zzqn.zza = zzhy0.zzf("measurement.redaction.app_instance_id", true);
        zzqn.zzb = zzhy0.zzf("measurement.redaction.client_ephemeral_aiid_generation", true);
        zzqn.zzc = zzhy0.zzf("measurement.redaction.config_redacted_fields", true);
        zzqn.zzd = zzhy0.zzf("measurement.redaction.device_info", true);
        zzqn.zze = zzhy0.zzf("measurement.redaction.e_tag", true);
        zzqn.zzf = zzhy0.zzf("measurement.redaction.enhanced_uid", true);
        zzqn.zzg = zzhy0.zzf("measurement.redaction.populate_ephemeral_app_instance_id", true);
        zzqn.zzh = zzhy0.zzf("measurement.redaction.google_signals", true);
        zzqn.zzi = zzhy0.zzf("measurement.redaction.no_aiid_in_config_request", true);
        zzqn.zzj = zzhy0.zzf("measurement.redaction.retain_major_os_version", true);
        zzqn.zzk = zzhy0.zzf("measurement.redaction.scion_payload_generator", true);
        zzqn.zzl = zzhy0.zzf("measurement.redaction.upload_redacted_fields", true);
        zzqn.zzm = zzhy0.zzf("measurement.redaction.upload_subdomain_override", true);
        zzqn.zzn = zzhy0.zzf("measurement.redaction.user_id", true);
    }

    @Override  // com.google.android.gms.internal.measurement.zzqm
    public final boolean zza() {
        return ((Boolean)zzqn.zzj.zzb()).booleanValue();
    }

    @Override  // com.google.android.gms.internal.measurement.zzqm
    public final boolean zzb() {
        return ((Boolean)zzqn.zzk.zzb()).booleanValue();
    }
}

