package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;

final class zzcn extends zzdu {
    final Bundle zza;
    final zzef zzb;

    zzcn(zzef zzef0, Bundle bundle0) {
        this.zzb = zzef0;
        this.zza = bundle0;
        super(zzef0, true);
    }

    @Override  // com.google.android.gms.internal.measurement.zzdu
    final void zza() throws RemoteException {
        ((zzcc)Preconditions.checkNotNull(zzef.zze(this.zzb))).setConditionalUserProperty(this.zza, this.zzh);
    }
}

