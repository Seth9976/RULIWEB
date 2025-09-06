package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.common.zza;

public final class zzy extends zza implements zzaa {
    zzy(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.common.internal.ICertData");
    }

    @Override  // com.google.android.gms.common.internal.zzaa
    public final int zzc() throws RemoteException {
        Parcel parcel0 = this.zzB(2, this.zza());
        int v = parcel0.readInt();
        parcel0.recycle();
        return v;
    }

    @Override  // com.google.android.gms.common.internal.zzaa
    public final IObjectWrapper zzd() throws RemoteException {
        Parcel parcel0 = this.zzB(1, this.zza());
        IObjectWrapper iObjectWrapper0 = Stub.asInterface(parcel0.readStrongBinder());
        parcel0.recycle();
        return iObjectWrapper0;
    }
}

