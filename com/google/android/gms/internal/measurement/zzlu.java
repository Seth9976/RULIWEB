package com.google.android.gms.internal.measurement;

final class zzlu extends zzlw {
    private zzlu() {
        super(null);
    }

    zzlu(zzlt zzlt0) {
        super(null);
    }

    @Override  // com.google.android.gms.internal.measurement.zzlw
    final void zza(Object object0, long v) {
        ((zzli)zznu.zzf(object0, v)).zzb();
    }

    @Override  // com.google.android.gms.internal.measurement.zzlw
    final void zzb(Object object0, Object object1, long v) {
        zzli zzli0 = (zzli)zznu.zzf(object0, v);
        zzli zzli1 = (zzli)zznu.zzf(object1, v);
        int v1 = zzli0.size();
        int v2 = zzli1.size();
        if(v1 > 0 && v2 > 0) {
            if(!zzli0.zzc()) {
                zzli0 = zzli0.zzd(v2 + v1);
            }
            zzli0.addAll(zzli1);
        }
        if(v1 > 0) {
            zzli1 = zzli0;
        }
        zznu.zzs(object0, v, zzli1);
    }
}

