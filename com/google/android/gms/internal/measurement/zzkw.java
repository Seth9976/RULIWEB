package com.google.android.gms.internal.measurement;

final class zzkw implements zzmg {
    private static final zzkw zza;

    static {
        zzkw.zza = new zzkw();
    }

    public static zzkw zza() {
        return zzkw.zza;
    }

    @Override  // com.google.android.gms.internal.measurement.zzmg
    public final zzmf zzb(Class class0) {
        if(zzlb.class.isAssignableFrom(class0)) {
            try {
                return (zzmf)zzlb.zzbC(class0.asSubclass(zzlb.class)).zzl(3, null, null);
            }
            catch(Exception exception0) {
                throw new RuntimeException("Unable to get message info for " + class0.getName(), exception0);
            }
        }
        throw new IllegalArgumentException("Unsupported message type: " + class0.getName());
    }

    @Override  // com.google.android.gms.internal.measurement.zzmg
    public final boolean zzc(Class class0) {
        return zzlb.class.isAssignableFrom(class0);
    }
}

