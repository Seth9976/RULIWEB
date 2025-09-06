package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzbp extends zzbm implements zzbr {
    zzbp(IBinder iBinder0) {
        super(iBinder0, "com.google.android.finsky.externalreferrer.IGetInstallReferrerService");
    }

    @Override  // com.google.android.gms.internal.measurement.zzbr
    public final Bundle zzd(Bundle bundle0) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzbo.zzd(parcel0, bundle0);
        Parcel parcel1 = this.zzb(1, parcel0);
        Bundle bundle1 = (Bundle)zzbo.zza(parcel1, Bundle.CREATOR);
        parcel1.recycle();
        return bundle1;
    }
}

