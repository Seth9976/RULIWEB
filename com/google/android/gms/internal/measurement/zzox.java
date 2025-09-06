package com.google.android.gms.internal.measurement;

public final class zzox implements zzow {
    public static final zzib zza;

    static {
        zzox.zza = new zzhy(zzhq.zza("com.google.android.gms.measurement")).zzb().zza().zzf("measurement.client.ad_id_consent_fix", true);
    }

    @Override  // com.google.android.gms.internal.measurement.zzow
    public final boolean zza() {
        return true;
    }

    @Override  // com.google.android.gms.internal.measurement.zzow
    public final boolean zzb() {
        return ((Boolean)zzox.zza.zzb()).booleanValue();
    }
}

