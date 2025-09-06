package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

final class zzdj extends zzdu {
    final String zza;
    final zzbz zzb;
    final zzef zzc;

    zzdj(zzef zzef0, String s, zzbz zzbz0) {
        this.zzc = zzef0;
        this.zza = s;
        this.zzb = zzbz0;
        super(zzef0, true);
    }

    @Override  // com.google.android.gms.internal.measurement.zzdu
    final void zza() throws RemoteException {
        ((zzcc)Preconditions.checkNotNull(zzef.zze(this.zzc))).getMaxUserProperties(this.zza, this.zzb);
    }

    @Override  // com.google.android.gms.internal.measurement.zzdu
    protected final void zzb() {
        this.zzb.zze(null);
    }
}

