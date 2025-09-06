package com.google.android.gms.internal.auth;

final class zzfo implements zzfv {
    private final zzfv[] zza;

    zzfo(zzfv[] arr_zzfv) {
        this.zza = arr_zzfv;
    }

    @Override  // com.google.android.gms.internal.auth.zzfv
    public final zzfu zzb(Class class0) {
        zzfv[] arr_zzfv = this.zza;
        for(int v = 0; v < 2; ++v) {
            zzfv zzfv0 = arr_zzfv[v];
            if(zzfv0.zzc(class0)) {
                return zzfv0.zzb(class0);
            }
        }
        throw new UnsupportedOperationException("No factory is available for message type: " + class0.getName());
    }

    @Override  // com.google.android.gms.internal.auth.zzfv
    public final boolean zzc(Class class0) {
        zzfv[] arr_zzfv = this.zza;
        for(int v = 0; v < 2; ++v) {
            if(arr_zzfv[v].zzc(class0)) {
                return true;
            }
        }
        return false;
    }
}

