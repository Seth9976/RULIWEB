package com.google.android.gms.internal.measurement;

public final class zzpm implements zzpl {
    public static final zzib zza;
    public static final zzib zzb;
    public static final zzib zzc;
    public static final zzib zzd;

    static {
        zzhy zzhy0 = new zzhy(zzhq.zza("com.google.android.gms.measurement")).zzb().zza();
        zzpm.zza = zzhy0.zzf("measurement.client.global_params", true);
        zzpm.zzb = zzhy0.zzf("measurement.service.global_params_in_payload", true);
        zzpm.zzc = zzhy0.zzf("measurement.service.clear_global_params_on_uninstall", true);
        zzpm.zzd = zzhy0.zzf("measurement.service.global_params", true);
    }

    @Override  // com.google.android.gms.internal.measurement.zzpl
    public final boolean zza() {
        return true;
    }

    @Override  // com.google.android.gms.internal.measurement.zzpl
    public final boolean zzb() {
        return ((Boolean)zzpm.zzc.zzb()).booleanValue();
    }
}

