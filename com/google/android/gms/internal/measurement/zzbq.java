package com.google.android.gms.internal.measurement;

import android.os.IBinder;
import android.os.IInterface;

public abstract class zzbq extends zzbn implements zzbr {
    public static zzbr zzb(IBinder iBinder0) {
        IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.finsky.externalreferrer.IGetInstallReferrerService");
        return iInterface0 instanceof zzbr ? ((zzbr)iInterface0) : new zzbp(iBinder0);
    }
}

