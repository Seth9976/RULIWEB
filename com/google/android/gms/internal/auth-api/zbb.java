package com.google.android.gms.internal.auth-api;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public class zbb extends Binder implements IInterface {
    protected zbb(String s) {
        this.attachInterface(this, s);
    }

    @Override  // android.os.IInterface
    public final IBinder asBinder() {
        return this;
    }

    @Override  // android.os.Binder
    public final boolean onTransact(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        if(v > 0xFFFFFF) {
            return super.onTransact(v, parcel0, parcel1, v1) ? true : this.zba(v, parcel0, parcel1, v1);
        }
        parcel0.enforceInterface(this.getInterfaceDescriptor());
        return this.zba(v, parcel0, parcel1, v1);
    }

    protected boolean zba(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        throw null;
    }
}

