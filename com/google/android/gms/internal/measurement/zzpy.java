package com.google.android.gms.internal.measurement;

public final class zzpy implements zzpx {
    public static final zzib zza;
    public static final zzib zzb;
    public static final zzib zzc;

    static {
        zzhy zzhy0 = new zzhy(zzhq.zza("com.google.android.gms.measurement")).zza();
        zzpy.zza = zzhy0.zzd("measurement.id.lifecycle.app_in_background_parameter", 0L);
        zzpy.zzb = zzhy0.zzf("measurement.lifecycle.app_backgrounded_tracking", true);
        zzpy.zzc = zzhy0.zzf("measurement.lifecycle.app_in_background_parameter", false);
    }

    @Override  // com.google.android.gms.internal.measurement.zzpx
    public final boolean zza() {
        return ((Boolean)zzpy.zzc.zzb()).booleanValue();
    }
}

