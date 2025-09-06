package com.google.android.gms.internal.measurement;

public final class zzrf implements zzre {
    public static final zzib zza;
    public static final zzib zzb;

    static {
        zzhy zzhy0 = new zzhy(zzhq.zza("com.google.android.gms.measurement")).zzb().zza();
        zzrf.zza = zzhy0.zzf("measurement.sgtm.client.dev", false);
        zzrf.zzb = zzhy0.zzf("measurement.sgtm.service", false);
    }

    @Override  // com.google.android.gms.internal.measurement.zzre
    public final boolean zza() {
        return true;
    }

    @Override  // com.google.android.gms.internal.measurement.zzre
    public final boolean zzb() {
        return ((Boolean)zzrf.zza.zzb()).booleanValue();
    }

    @Override  // com.google.android.gms.internal.measurement.zzre
    public final boolean zzc() {
        return ((Boolean)zzrf.zzb.zzb()).booleanValue();
    }
}

