package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

final class zzcp extends zzdu {
    final String zza;
    final String zzb;
    final zzbz zzc;
    final zzef zzd;

    zzcp(zzef zzef0, String s, String s1, zzbz zzbz0) {
        this.zzd = zzef0;
        this.zza = s;
        this.zzb = s1;
        this.zzc = zzbz0;
        super(zzef0, true);
    }

    @Override  // com.google.android.gms.internal.measurement.zzdu
    final void zza() throws RemoteException {
        ((zzcc)Preconditions.checkNotNull(zzef.zze(this.zzd))).getConditionalUserProperties(this.zza, this.zzb, this.zzc);
    }

    @Override  // com.google.android.gms.internal.measurement.zzdu
    protected final void zzb() {
        this.zzc.zze(null);
    }
}

