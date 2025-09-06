package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

final class zzdh extends zzdu {
    final Bundle zza;
    final zzbz zzb;
    final zzef zzc;

    zzdh(zzef zzef0, Bundle bundle0, zzbz zzbz0) {
        this.zzc = zzef0;
        this.zza = bundle0;
        this.zzb = zzbz0;
        super(zzef0, true);
    }

    @Override  // com.google.android.gms.internal.measurement.zzdu
    final void zza() throws RemoteException {
        ((zzcc)Preconditions.checkNotNull(zzef.zze(this.zzc))).performAction(this.zza, this.zzb, this.zzh);
    }

    @Override  // com.google.android.gms.internal.measurement.zzdu
    protected final void zzb() {
        this.zzb.zze(null);
    }
}

