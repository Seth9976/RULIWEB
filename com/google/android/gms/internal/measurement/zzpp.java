package com.google.android.gms.internal.measurement;

public final class zzpp implements zzpo {
    public static final zzib zza;

    static {
        zzpp.zza = new zzhy(zzhq.zza("com.google.android.gms.measurement")).zzb().zza().zzf("measurement.gmscore_feature_tracking", true);
    }

    @Override  // com.google.android.gms.internal.measurement.zzpo
    public final boolean zza() {
        return true;
    }

    @Override  // com.google.android.gms.internal.measurement.zzpo
    public final boolean zzb() {
        return ((Boolean)zzpp.zza.zzb()).booleanValue();
    }
}

