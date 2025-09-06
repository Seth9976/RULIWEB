package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.base.zaa;
import com.google.android.gms.internal.base.zac;

public final class zam extends zaa implements IInterface {
    zam(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.common.internal.ISignInButtonCreator");
    }

    public final IObjectWrapper zae(IObjectWrapper iObjectWrapper0, zax zax0) throws RemoteException {
        Parcel parcel0 = this.zaa();
        zac.zad(parcel0, iObjectWrapper0);
        zac.zac(parcel0, zax0);
        Parcel parcel1 = this.zab(2, parcel0);
        IObjectWrapper iObjectWrapper1 = Stub.asInterface(parcel1.readStrongBinder());
        parcel1.recycle();
        return iObjectWrapper1;
    }
}

