package com.google.android.gms.internal.measurement;

public final class zzpd implements zzpc {
    public static final zzib zza;

    static {
        zzpd.zza = new zzhy(zzhq.zza("com.google.android.gms.measurement")).zzb().zza().zzf("measurement.client.deep_link_referrer_fix", true);
    }

    @Override  // com.google.android.gms.internal.measurement.zzpc
    public final boolean zza() {
        return true;
    }

    @Override  // com.google.android.gms.internal.measurement.zzpc
    public final boolean zzb() {
        return ((Boolean)zzpd.zza.zzb()).booleanValue();
    }
}

