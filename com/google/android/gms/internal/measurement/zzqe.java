package com.google.android.gms.internal.measurement;

public final class zzqe implements zzqd {
    public static final zzib zza;
    public static final zzib zzb;
    public static final zzib zzc;
    public static final zzib zzd;
    public static final zzib zze;

    static {
        zzhy zzhy0 = new zzhy(zzhq.zza("com.google.android.gms.measurement")).zza();
        zzqe.zza = zzhy0.zzf("measurement.test.boolean_flag", false);
        zzqe.zzb = zzhy0.zzc("measurement.test.double_flag", -3.0);
        zzqe.zzc = zzhy0.zzd("measurement.test.int_flag", -2L);
        zzqe.zzd = zzhy0.zzd("measurement.test.long_flag", -1L);
        zzqe.zze = zzhy0.zze("measurement.test.string_flag", "---");
    }

    @Override  // com.google.android.gms.internal.measurement.zzqd
    public final double zza() {
        return (double)(((Double)zzqe.zzb.zzb()));
    }

    @Override  // com.google.android.gms.internal.measurement.zzqd
    public final long zzb() {
        return (long)(((Long)zzqe.zzc.zzb()));
    }

    @Override  // com.google.android.gms.internal.measurement.zzqd
    public final long zzc() {
        return (long)(((Long)zzqe.zzd.zzb()));
    }

    @Override  // com.google.android.gms.internal.measurement.zzqd
    public final String zzd() {
        return (String)zzqe.zze.zzb();
    }

    @Override  // com.google.android.gms.internal.measurement.zzqd
    public final boolean zze() {
        return ((Boolean)zzqe.zza.zzb()).booleanValue();
    }
}

