package com.google.android.gms.internal.measurement;

public final class zzrl implements zzrk {
    public static final zzib zza;

    static {
        zzrl.zza = new zzhy(zzhq.zza("com.google.android.gms.measurement")).zza().zzf("measurement.integration.disable_firebase_instance_id", false);
    }

    @Override  // com.google.android.gms.internal.measurement.zzrk
    public final boolean zza() {
        return true;
    }

    @Override  // com.google.android.gms.internal.measurement.zzrk
    public final boolean zzb() {
        return ((Boolean)zzrl.zza.zzb()).booleanValue();
    }
}

