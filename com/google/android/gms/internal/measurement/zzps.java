package com.google.android.gms.internal.measurement;

public final class zzps implements zzpr {
    public static final zzib zza;
    public static final zzib zzb;
    public static final zzib zzc;

    static {
        zzhy zzhy0 = new zzhy(zzhq.zza("com.google.android.gms.measurement")).zzb().zza();
        zzps.zza = zzhy0.zzf("measurement.item_scoped_custom_parameters.client", true);
        zzps.zzb = zzhy0.zzf("measurement.item_scoped_custom_parameters.service", false);
        zzps.zzc = zzhy0.zzd("measurement.id.item_scoped_custom_parameters.service", 0L);
    }

    @Override  // com.google.android.gms.internal.measurement.zzpr
    public final boolean zza() {
        return true;
    }

    @Override  // com.google.android.gms.internal.measurement.zzpr
    public final boolean zzb() {
        return ((Boolean)zzps.zza.zzb()).booleanValue();
    }

    @Override  // com.google.android.gms.internal.measurement.zzpr
    public final boolean zzc() {
        return ((Boolean)zzps.zzb.zzb()).booleanValue();
    }
}

