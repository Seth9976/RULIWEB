package com.google.android.gms.internal.auth;

final class zzes implements zzfv {
    private static final zzes zza;

    static {
        zzes.zza = new zzes();
    }

    public static zzes zza() {
        return zzes.zza;
    }

    @Override  // com.google.android.gms.internal.auth.zzfv
    public final zzfu zzb(Class class0) {
        if(zzev.class.isAssignableFrom(class0)) {
            try {
                return (zzfu)zzev.zzb(class0.asSubclass(zzev.class)).zzn(3, null, null);
            }
            catch(Exception exception0) {
                throw new RuntimeException("Unable to get message info for " + class0.getName(), exception0);
            }
        }
        throw new IllegalArgumentException("Unsupported message type: " + class0.getName());
    }

    @Override  // com.google.android.gms.internal.auth.zzfv
    public final boolean zzc(Class class0) {
        return zzev.class.isAssignableFrom(class0);
    }
}

