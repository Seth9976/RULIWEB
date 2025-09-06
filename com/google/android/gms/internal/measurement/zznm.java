package com.google.android.gms.internal.measurement;

import java.io.IOException;

final class zznm extends zznk {
    @Override  // com.google.android.gms.internal.measurement.zznk
    final int zza(Object object0) {
        return ((zznl)object0).zza();
    }

    @Override  // com.google.android.gms.internal.measurement.zznk
    final int zzb(Object object0) {
        return ((zznl)object0).zzb();
    }

    @Override  // com.google.android.gms.internal.measurement.zznk
    final Object zzc(Object object0) {
        zznl zznl0 = ((zzlb)object0).zzc;
        if(zznl0 == zznl.zzc()) {
            zznl0 = zznl.zzf();
            ((zzlb)object0).zzc = zznl0;
        }
        return zznl0;
    }

    @Override  // com.google.android.gms.internal.measurement.zznk
    final Object zzd(Object object0) {
        return ((zzlb)object0).zzc;
    }

    @Override  // com.google.android.gms.internal.measurement.zznk
    final Object zze(Object object0, Object object1) {
        if(!zznl.zzc().equals(object1)) {
            if(zznl.zzc().equals(object0)) {
                return zznl.zze(((zznl)object0), ((zznl)object1));
            }
            ((zznl)object0).zzd(((zznl)object1));
        }
        return object0;
    }

    @Override  // com.google.android.gms.internal.measurement.zznk
    final void zzf(Object object0, int v, long v1) {
        ((zznl)object0).zzj(v << 3, v1);
    }

    @Override  // com.google.android.gms.internal.measurement.zznk
    final void zzg(Object object0) {
        ((zzlb)object0).zzc.zzh();
    }

    @Override  // com.google.android.gms.internal.measurement.zznk
    final void zzh(Object object0, Object object1) {
        ((zzlb)object0).zzc = (zznl)object1;
    }

    @Override  // com.google.android.gms.internal.measurement.zznk
    final void zzi(Object object0, zzoc zzoc0) throws IOException {
        ((zznl)object0).zzk(zzoc0);
    }
}

