package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;

final class zzcr extends zzdu {
    final Activity zza;
    final String zzb;
    final String zzc;
    final zzef zzd;

    zzcr(zzef zzef0, Activity activity0, String s, String s1) {
        this.zzd = zzef0;
        this.zza = activity0;
        this.zzb = s;
        this.zzc = s1;
        super(zzef0, true);
    }

    @Override  // com.google.android.gms.internal.measurement.zzdu
    final void zza() throws RemoteException {
        ((zzcc)Preconditions.checkNotNull(zzef.zze(this.zzd))).setCurrentScreen(ObjectWrapper.wrap(this.zza), this.zzb, this.zzc, this.zzh);
    }
}

