package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

final class zzdl extends zzdu {
    final zzbz zza;
    final zzef zzb;

    zzdl(zzef zzef0, zzbz zzbz0) {
        this.zzb = zzef0;
        this.zza = zzbz0;
        super(zzef0, true);
    }

    @Override  // com.google.android.gms.internal.measurement.zzdu
    final void zza() throws RemoteException {
        ((zzcc)Preconditions.checkNotNull(zzef.zze(this.zzb))).getSessionId(this.zza);
    }

    @Override  // com.google.android.gms.internal.measurement.zzdu
    protected final void zzb() {
        this.zza.zze(null);
    }
}

