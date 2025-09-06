package com.google.android.gms.internal.base;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public class zab extends Binder implements IInterface {
    protected zab(String s) {
        this.attachInterface(this, s);
    }

    @Override  // android.os.IInterface
    public final IBinder asBinder() {
        return this;
    }

    @Override  // android.os.Binder
    public final boolean onTransact(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        if(v > 0xFFFFFF) {
            return super.onTransact(v, parcel0, parcel1, v1) ? true : this.zaa(v, parcel0, parcel1, v1);
        }
        parcel0.enforceInterface(this.getInterfaceDescriptor());
        return this.zaa(v, parcel0, parcel1, v1);
    }

    protected boolean zaa(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        throw null;
    }
}

