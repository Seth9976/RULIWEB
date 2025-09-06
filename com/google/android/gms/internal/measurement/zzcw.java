package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

final class zzcw extends zzdu {
    final long zza;
    final zzef zzb;

    zzcw(zzef zzef0, long v) {
        this.zzb = zzef0;
        this.zza = v;
        super(zzef0, true);
    }

    @Override  // com.google.android.gms.internal.measurement.zzdu
    final void zza() throws RemoteException {
        ((zzcc)Preconditions.checkNotNull(zzef.zze(this.zzb))).setSessionTimeoutDuration(this.zza);
    }
}

