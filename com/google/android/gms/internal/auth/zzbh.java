package com.google.android.gms.internal.auth;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.api.proxy.ProxyRequest;

public final class zzbh extends zza implements IInterface {
    zzbh(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.auth.api.internal.IAuthService");
    }

    public final void zzd(zzbg zzbg0) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzc.zzd(parcel0, zzbg0);
        this.zzc(3, parcel0);
    }

    public final void zze(zzbg zzbg0, ProxyRequest proxyRequest0) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzc.zzd(parcel0, zzbg0);
        zzc.zzc(parcel0, proxyRequest0);
        this.zzc(1, parcel0);
    }
}

