package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

final class zzcy extends zzdu {
    final String zza;
    final zzef zzb;

    zzcy(zzef zzef0, String s) {
        this.zzb = zzef0;
        this.zza = s;
        super(zzef0, true);
    }

    @Override  // com.google.android.gms.internal.measurement.zzdu
    final void zza() throws RemoteException {
        ((zzcc)Preconditions.checkNotNull(zzef.zze(this.zzb))).beginAdUnitExposure(this.zza, this.zzi);
    }
}

