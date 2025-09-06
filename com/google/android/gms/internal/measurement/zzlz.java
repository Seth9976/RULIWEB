package com.google.android.gms.internal.measurement;

final class zzlz implements zzmg {
    private final zzmg[] zza;

    zzlz(zzmg[] arr_zzmg) {
        this.zza = arr_zzmg;
    }

    @Override  // com.google.android.gms.internal.measurement.zzmg
    public final zzmf zzb(Class class0) {
        zzmg[] arr_zzmg = this.zza;
        for(int v = 0; v < 2; ++v) {
            zzmg zzmg0 = arr_zzmg[v];
            if(zzmg0.zzc(class0)) {
                return zzmg0.zzb(class0);
            }
        }
        throw new UnsupportedOperationException("No factory is available for message type: " + class0.getName());
    }

    @Override  // com.google.android.gms.internal.measurement.zzmg
    public final boolean zzc(Class class0) {
        zzmg[] arr_zzmg = this.zza;
        for(int v = 0; v < 2; ++v) {
            if(arr_zzmg[v].zzc(class0)) {
                return true;
            }
        }
        return false;
    }
}

