package com.google.android.gms.internal.auth;

final class zzfp implements zzgj {
    private static final zzfv zza;
    private final zzfv zzb;

    static {
        zzfp.zza = new zzfn();
    }

    public zzfp() {
        zzfv zzfv0;
        zzfv[] arr_zzfv;
        try {
            arr_zzfv = new zzfv[]{zzes.zza(), null};
            zzfv0 = (zzfv)Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", null).invoke(null, null);
        }
        catch(Exception unused_ex) {
            zzfv0 = zzfp.zza;
        }
        arr_zzfv[1] = zzfv0;
        zzfo zzfo0 = new zzfo(arr_zzfv);
        super();
        this.zzb = zzfo0;
    }

    @Override  // com.google.android.gms.internal.auth.zzgj
    public final zzgi zza(Class class0) {
        zzgk.zze(class0);
        zzfu zzfu0 = this.zzb.zzb(class0);
        if(zzfu0.zzb()) {
            return zzev.class.isAssignableFrom(class0) ? zzgb.zzb(zzgk.zzb(), zzeo.zzb(), zzfu0.zza()) : zzgb.zzb(zzgk.zza(), zzeo.zza(), zzfu0.zza());
        }
        if(zzev.class.isAssignableFrom(class0)) {
            return zzfp.zzb(zzfu0) ? zzga.zzj(class0, zzfu0, zzgd.zzb(), zzfl.zzd(), zzgk.zzb(), zzeo.zzb(), zzft.zzb()) : zzga.zzj(class0, zzfu0, zzgd.zzb(), zzfl.zzd(), zzgk.zzb(), null, zzft.zzb());
        }
        return zzfp.zzb(zzfu0) ? zzga.zzj(class0, zzfu0, zzgd.zza(), zzfl.zzc(), zzgk.zza(), zzeo.zza(), zzft.zza()) : zzga.zzj(class0, zzfu0, zzgd.zza(), zzfl.zzc(), zzgk.zza(), null, zzft.zza());
    }

    private static boolean zzb(zzfu zzfu0) {
        return zzfu0.zzc() - 1 != 1;
    }
}

