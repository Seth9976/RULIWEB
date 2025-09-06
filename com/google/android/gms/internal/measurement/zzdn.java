package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

final class zzdn extends zzdu {
    final boolean zza;
    final zzef zzb;

    zzdn(zzef zzef0, boolean z) {
        this.zzb = zzef0;
        this.zza = z;
        super(zzef0, true);
    }

    @Override  // com.google.android.gms.internal.measurement.zzdu
    final void zza() throws RemoteException {
        ((zzcc)Preconditions.checkNotNull(zzef.zze(this.zzb))).setDataCollectionEnabled(this.zza);
    }
}

