package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

final class zzdt extends zzdu {
    final String zza;
    final String zzb;
    final Object zzc;
    final boolean zzd;
    final zzef zze;

    zzdt(zzef zzef0, String s, String s1, Object object0, boolean z) {
        this.zze = zzef0;
        this.zza = s;
        this.zzb = s1;
        this.zzc = object0;
        this.zzd = z;
        super(zzef0, true);
    }

    @Override  // com.google.android.gms.internal.measurement.zzdu
    final void zza() throws RemoteException {
        Object object0 = Preconditions.checkNotNull(zzef.zze(this.zze));
        IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(this.zzc);
        ((zzcc)object0).setUserProperty(this.zza, this.zzb, iObjectWrapper0, this.zzd, this.zzh);
    }
}

