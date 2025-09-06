package com.google.android.gms.dynamite;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.common.zza;
import com.google.android.gms.internal.common.zzc;

public final class zzr extends zza implements IInterface {
    zzr(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.dynamite.IDynamiteLoaderV2");
    }

    public final IObjectWrapper zze(IObjectWrapper iObjectWrapper0, String s, int v, IObjectWrapper iObjectWrapper1) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzc.zze(parcel0, iObjectWrapper0);
        parcel0.writeString(s);
        parcel0.writeInt(v);
        zzc.zze(parcel0, iObjectWrapper1);
        Parcel parcel1 = this.zzB(2, parcel0);
        IObjectWrapper iObjectWrapper2 = Stub.asInterface(parcel1.readStrongBinder());
        parcel1.recycle();
        return iObjectWrapper2;
    }

    public final IObjectWrapper zzf(IObjectWrapper iObjectWrapper0, String s, int v, IObjectWrapper iObjectWrapper1) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzc.zze(parcel0, iObjectWrapper0);
        parcel0.writeString(s);
        parcel0.writeInt(v);
        zzc.zze(parcel0, iObjectWrapper1);
        Parcel parcel1 = this.zzB(3, parcel0);
        IObjectWrapper iObjectWrapper2 = Stub.asInterface(parcel1.readStrongBinder());
        parcel1.recycle();
        return iObjectWrapper2;
    }
}

