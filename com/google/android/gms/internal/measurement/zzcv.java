package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

final class zzcv extends zzdu {
    final zzef zza;

    zzcv(zzef zzef0) {
        this.zza = zzef0;
        super(zzef0, true);
    }

    @Override  // com.google.android.gms.internal.measurement.zzdu
    final void zza() throws RemoteException {
        ((zzcc)Preconditions.checkNotNull(zzef.zze(this.zza))).resetAnalyticsData(this.zzh);
    }
}

