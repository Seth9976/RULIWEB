package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

final class zzdg extends zzdu {
    final String zza;
    final Object zzb;
    final zzef zzc;

    zzdg(zzef zzef0, boolean z, int v, String s, Object object0, Object object1, Object object2) {
        this.zzc = zzef0;
        this.zza = s;
        this.zzb = object0;
        super(zzef0, false);
    }

    @Override  // com.google.android.gms.internal.measurement.zzdu
    final void zza() throws RemoteException {
        Object object0 = Preconditions.checkNotNull(zzef.zze(this.zzc));
        IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(this.zzb);
        IObjectWrapper iObjectWrapper1 = ObjectWrapper.wrap(null);
        IObjectWrapper iObjectWrapper2 = ObjectWrapper.wrap(null);
        ((zzcc)object0).logHealthData(5, this.zza, iObjectWrapper0, iObjectWrapper1, iObjectWrapper2);
    }
}

