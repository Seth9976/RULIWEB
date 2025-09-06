package com.google.android.gms.internal.measurement;

final class zzma implements zzmu {
    private static final zzmg zza;
    private final zzmg zzb;

    static {
        zzma.zza = new zzly();
    }

    public zzma() {
        zzmg zzmg0;
        zzmg[] arr_zzmg;
        try {
            arr_zzmg = new zzmg[]{zzkw.zza(), null};
            zzmg0 = (zzmg)Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", null).invoke(null, null);
        }
        catch(Exception unused_ex) {
            zzmg0 = zzma.zza;
        }
        arr_zzmg[1] = zzmg0;
        zzlz zzlz0 = new zzlz(arr_zzmg);
        super();
        this.zzb = zzlz0;
    }

    @Override  // com.google.android.gms.internal.measurement.zzmu
    public final zzmt zza(Class class0) {
        zzmv.zzC(class0);
        zzmf zzmf0 = this.zzb.zzb(class0);
        if(zzmf0.zzb()) {
            return zzlb.class.isAssignableFrom(class0) ? zzmm.zzc(zzmv.zzz(), zzkq.zzb(), zzmf0.zza()) : zzmm.zzc(zzmv.zzy(), zzkq.zza(), zzmf0.zza());
        }
        if(zzlb.class.isAssignableFrom(class0)) {
            return zzma.zzb(zzmf0) ? zzml.zzl(class0, zzmf0, zzmo.zzb(), zzlw.zzd(), zzmv.zzz(), zzkq.zzb(), zzme.zzb()) : zzml.zzl(class0, zzmf0, zzmo.zzb(), zzlw.zzd(), zzmv.zzz(), null, zzme.zzb());
        }
        return zzma.zzb(zzmf0) ? zzml.zzl(class0, zzmf0, zzmo.zza(), zzlw.zzc(), zzmv.zzy(), zzkq.zza(), zzme.zza()) : zzml.zzl(class0, zzmf0, zzmo.zza(), zzlw.zzc(), zzmv.zzy(), null, zzme.zza());
    }

    private static boolean zzb(zzmf zzmf0) {
        return zzmf0.zzc() == 1;
    }
}

