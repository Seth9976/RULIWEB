package com.google.android.gms.internal.measurement;

public final class zzou implements zzot {
    public static final zzib zza;

    static {
        zzou.zza = new zzhy(zzhq.zza("com.google.android.gms.measurement")).zza().zzf("measurement.client.firebase_feature_rollout.v1.enable", true);
    }

    @Override  // com.google.android.gms.internal.measurement.zzot
    public final boolean zza() {
        return true;
    }

    @Override  // com.google.android.gms.internal.measurement.zzot
    public final boolean zzb() {
        return ((Boolean)zzou.zza.zzb()).booleanValue();
    }
}

