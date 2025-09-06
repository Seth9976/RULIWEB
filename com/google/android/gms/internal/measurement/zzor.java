package com.google.android.gms.internal.measurement;

public final class zzor implements zzoq {
    public static final zzib zza;
    public static final zzib zzb;
    public static final zzib zzc;

    static {
        zzhy zzhy0 = new zzhy(zzhq.zza("com.google.android.gms.measurement")).zzb().zza();
        zzor.zza = zzhy0.zzf("measurement.collection.event_safelist", true);
        zzor.zzb = zzhy0.zzf("measurement.service.store_null_safelist", true);
        zzor.zzc = zzhy0.zzf("measurement.service.store_safelist", true);
    }

    @Override  // com.google.android.gms.internal.measurement.zzoq
    public final boolean zza() {
        return true;
    }

    @Override  // com.google.android.gms.internal.measurement.zzoq
    public final boolean zzb() {
        return ((Boolean)zzor.zzb.zzb()).booleanValue();
    }

    @Override  // com.google.android.gms.internal.measurement.zzoq
    public final boolean zzc() {
        return ((Boolean)zzor.zzc.zzb()).booleanValue();
    }
}

