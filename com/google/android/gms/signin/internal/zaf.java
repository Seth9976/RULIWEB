package com.google.android.gms.signin.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.internal.base.zaa;
import com.google.android.gms.internal.base.zac;

public final class zaf extends zaa implements IInterface {
    zaf(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.signin.internal.ISignInService");
    }

    public final void zae(int v) throws RemoteException {
        Parcel parcel0 = this.zaa();
        parcel0.writeInt(v);
        this.zac(7, parcel0);
    }

    public final void zaf(IAccountAccessor iAccountAccessor0, int v, boolean z) throws RemoteException {
        Parcel parcel0 = this.zaa();
        zac.zad(parcel0, iAccountAccessor0);
        parcel0.writeInt(v);
        parcel0.writeInt(((int)z));
        this.zac(9, parcel0);
    }

    public final void zag(zai zai0, zae zae0) throws RemoteException {
        Parcel parcel0 = this.zaa();
        zac.zac(parcel0, zai0);
        zac.zad(parcel0, zae0);
        this.zac(12, parcel0);
    }
}

