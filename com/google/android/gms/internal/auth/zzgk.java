package com.google.android.gms.internal.auth;

final class zzgk {
    public static final int zza;
    private static final Class zzb;
    private static final zzgz zzc;
    private static final zzgz zzd;

    static {
        Class class1;
        Class class0;
        zzgz zzgz0 = null;
        try {
            class0 = null;
            class0 = Class.forName("com.google.protobuf.GeneratedMessage");
        }
        catch(Throwable unused_ex) {
        }
        try {
            zzgk.zzb = class0;
            class1 = null;
            class1 = Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        }
        catch(Throwable unused_ex) {
        }
        if(class1 != null) {
            try {
                zzgz0 = (zzgz)class1.getConstructor(null).newInstance(null);
            }
            catch(Throwable unused_ex) {
            }
        }
        zzgk.zzc = zzgz0;
        zzgk.zzd = new zzhb();
    }

    public static zzgz zza() {
        return zzgk.zzc;
    }

    public static zzgz zzb() {
        return zzgk.zzd;
    }

    static Object zzc(Object object0, int v, int v1, Object object1, zzgz zzgz0) {
        if(object1 == null) {
            object1 = zzgz0.zza(object0);
        }
        zzgz0.zzd(object1, v, ((long)v1));
        return object1;
    }

    static void zzd(zzgz zzgz0, Object object0, Object object1) {
        zzgz0.zzf(object0, zzgz0.zzc(zzgz0.zzb(object0), zzgz0.zzb(object1)));
    }

    public static void zze(Class class0) {
        if(!zzev.class.isAssignableFrom(class0) && (zzgk.zzb != null && !zzgk.zzb.isAssignableFrom(class0))) {
            throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
        }
    }

    // 去混淆评级： 低(20)
    static boolean zzf(Object object0, Object object1) {
        return object0 == object1 || object0 != null && object0.equals(object1);
    }
}

