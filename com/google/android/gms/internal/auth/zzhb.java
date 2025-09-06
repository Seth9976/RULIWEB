package com.google.android.gms.internal.auth;

final class zzhb extends zzgz {
    @Override  // com.google.android.gms.internal.auth.zzgz
    final Object zza(Object object0) {
        zzha zzha0 = ((zzev)object0).zzc;
        if(zzha0 == zzha.zza()) {
            zzha0 = zzha.zzd();
            ((zzev)object0).zzc = zzha0;
        }
        return zzha0;
    }

    @Override  // com.google.android.gms.internal.auth.zzgz
    final Object zzb(Object object0) {
        return ((zzev)object0).zzc;
    }

    @Override  // com.google.android.gms.internal.auth.zzgz
    final Object zzc(Object object0, Object object1) {
        if(!zzha.zza().equals(object1)) {
            if(zzha.zza().equals(object0)) {
                return zzha.zzc(((zzha)object0), ((zzha)object1));
            }
            ((zzha)object0).zzb(((zzha)object1));
        }
        return object0;
    }

    @Override  // com.google.android.gms.internal.auth.zzgz
    final void zzd(Object object0, int v, long v1) {
        ((zzha)object0).zzh(v << 3, v1);
    }

    @Override  // com.google.android.gms.internal.auth.zzgz
    final void zze(Object object0) {
        ((zzev)object0).zzc.zzf();
    }

    @Override  // com.google.android.gms.internal.auth.zzgz
    final void zzf(Object object0, Object object1) {
        ((zzev)object0).zzc = (zzha)object1;
    }
}

