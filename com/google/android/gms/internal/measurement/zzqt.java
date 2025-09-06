package com.google.android.gms.internal.measurement;

public final class zzqt implements zzqs {
    public static final zzib zza;

    static {
        zzqt.zza = new zzhy(zzhq.zza("com.google.android.gms.measurement")).zzb().zza().zzf("measurement.sessionid.enable_client_session_id", true);
    }

    @Override  // com.google.android.gms.internal.measurement.zzqs
    public final boolean zza() {
        return true;
    }

    @Override  // com.google.android.gms.internal.measurement.zzqs
    public final boolean zzb() {
        return ((Boolean)zzqt.zza.zzb()).booleanValue();
    }
}

