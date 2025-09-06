package com.google.android.gms.internal.measurement;

public final class zzpj implements zzpi {
    public static final zzib zza;
    public static final zzib zzb;
    public static final zzib zzc;

    static {
        zzhy zzhy0 = new zzhy(zzhq.zza("com.google.android.gms.measurement")).zza();
        zzpj.zza = zzhy0.zzf("measurement.client.sessions.check_on_reset_and_enable2", true);
        zzpj.zzb = zzhy0.zzf("measurement.client.sessions.check_on_startup", true);
        zzpj.zzc = zzhy0.zzf("measurement.client.sessions.start_session_before_view_screen", true);
    }

    @Override  // com.google.android.gms.internal.measurement.zzpi
    public final boolean zza() {
        return true;
    }

    @Override  // com.google.android.gms.internal.measurement.zzpi
    public final boolean zzb() {
        return ((Boolean)zzpj.zza.zzb()).booleanValue();
    }
}

