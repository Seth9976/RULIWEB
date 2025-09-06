package com.google.android.gms.internal.auth;

import android.os.IBinder;
import android.os.IInterface;

public abstract class zze extends zzb implements zzf {
    public static zzf zzb(IBinder iBinder0) {
        if(iBinder0 == null) {
            return null;
        }
        IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.auth.IAuthManagerService");
        return iInterface0 instanceof zzf ? ((zzf)iInterface0) : new zzd(iBinder0);
    }
}

