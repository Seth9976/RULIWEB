package com.google.android.gms.internal.auth;

final class zzfj extends zzfl {
    private zzfj() {
        super(null);
    }

    zzfj(zzfi zzfi0) {
        super(null);
    }

    @Override  // com.google.android.gms.internal.auth.zzfl
    final void zza(Object object0, long v) {
        ((zzez)zzhj.zzf(object0, v)).zzb();
    }

    @Override  // com.google.android.gms.internal.auth.zzfl
    final void zzb(Object object0, Object object1, long v) {
        zzez zzez0 = (zzez)zzhj.zzf(object0, v);
        zzez zzez1 = (zzez)zzhj.zzf(object1, v);
        int v1 = zzez0.size();
        int v2 = zzez1.size();
        if(v1 > 0 && v2 > 0) {
            if(!zzez0.zzc()) {
                zzez0 = zzez0.zzd(v2 + v1);
            }
            zzez0.addAll(zzez1);
        }
        if(v1 > 0) {
            zzez1 = zzez0;
        }
        zzhj.zzp(object0, v, zzez1);
    }
}

