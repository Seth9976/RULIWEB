package com.google.android.gms.internal.measurement;

final class zzbd implements zzbf {
    private final zzg zza;
    private final String zzb;

    public zzbd(zzg zzg0, String s) {
        this.zza = zzg0;
        this.zzb = s;
    }

    @Override  // com.google.android.gms.internal.measurement.zzbf
    public final zzg zza(zzap zzap0) {
        zzg zzg0 = this.zza.zza();
        zzg0.zzf(this.zzb, zzap0);
        return zzg0;
    }
}

