package com.google.android.gms.auth.api.signin.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.internal.auth-api.zba;
import com.google.android.gms.internal.auth-api.zbc;

public final class zbs extends zba implements IInterface {
    zbs(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.auth.api.signin.internal.ISignInService");
    }

    public final void zbc(zbr zbr0, GoogleSignInOptions googleSignInOptions0) throws RemoteException {
        Parcel parcel0 = this.zba();
        zbc.zbd(parcel0, zbr0);
        zbc.zbc(parcel0, googleSignInOptions0);
        this.zbb(103, parcel0);
    }

    public final void zbd(zbr zbr0, GoogleSignInOptions googleSignInOptions0) throws RemoteException {
        Parcel parcel0 = this.zba();
        zbc.zbd(parcel0, zbr0);
        zbc.zbc(parcel0, googleSignInOptions0);
        this.zbb(102, parcel0);
    }

    public final void zbe(zbr zbr0, GoogleSignInOptions googleSignInOptions0) throws RemoteException {
        Parcel parcel0 = this.zba();
        zbc.zbd(parcel0, zbr0);
        zbc.zbc(parcel0, googleSignInOptions0);
        this.zbb(101, parcel0);
    }
}

