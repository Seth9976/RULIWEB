package com.google.android.gms.internal.measurement;

public final class zzrc implements zzrb {
    public static final zzib zza;
    public static final zzib zzb;

    static {
        zzhy zzhy0 = new zzhy(zzhq.zza("com.google.android.gms.measurement")).zzb().zza();
        zzrc.zza = zzhy0.zzf("measurement.sfmc.client", true);
        zzrc.zzb = zzhy0.zzf("measurement.sfmc.service", true);
    }

    @Override  // com.google.android.gms.internal.measurement.zzrb
    public final boolean zza() {
        return true;
    }

    @Override  // com.google.android.gms.internal.measurement.zzrb
    public final boolean zzb() {
        return ((Boolean)zzrc.zza.zzb()).booleanValue();
    }

    @Override  // com.google.android.gms.internal.measurement.zzrb
    public final boolean zzc() {
        return ((Boolean)zzrc.zzb.zzb()).booleanValue();
    }
}

