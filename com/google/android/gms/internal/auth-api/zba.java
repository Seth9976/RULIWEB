package com.google.android.gms.internal.auth-api;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public class zba implements IInterface {
    private final IBinder zba;
    private final String zbb;

    protected zba(IBinder iBinder0, String s) {
        this.zba = iBinder0;
        this.zbb = s;
    }

    @Override  // android.os.IInterface
    public final IBinder asBinder() {
        return this.zba;
    }

    protected final Parcel zba() {
        Parcel parcel0 = Parcel.obtain();
        parcel0.writeInterfaceToken(this.zbb);
        return parcel0;
    }

    protected final void zbb(int v, Parcel parcel0) throws RemoteException {
        Parcel parcel1 = Parcel.obtain();
        try {
            this.zba.transact(v, parcel0, parcel1, 0);
            parcel1.readException();
        }
        finally {
            parcel0.recycle();
            parcel1.recycle();
        }
    }
}

