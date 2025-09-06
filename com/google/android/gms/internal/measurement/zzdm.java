package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

final class zzdm extends zzdu {
    final zzbz zza;
    final int zzb;
    final zzef zzc;

    zzdm(zzef zzef0, zzbz zzbz0, int v) {
        this.zzc = zzef0;
        this.zza = zzbz0;
        this.zzb = v;
        super(zzef0, true);
    }

    @Override  // com.google.android.gms.internal.measurement.zzdu
    final void zza() throws RemoteException {
        ((zzcc)Preconditions.checkNotNull(zzef.zze(this.zzc))).getTestFlag(this.zza, this.zzb);
    }

    @Override  // com.google.android.gms.internal.measurement.zzdu
    protected final void zzb() {
        this.zza.zze(null);
    }
}

