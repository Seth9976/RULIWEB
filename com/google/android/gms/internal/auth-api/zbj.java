package com.google.android.gms.internal.auth-api;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.api.identity.AuthorizationRequest;

public final class zbj extends zba implements IInterface {
    zbj(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.auth.api.identity.internal.IAuthorizationService");
    }

    public final void zbc(zbi zbi0, AuthorizationRequest authorizationRequest0) throws RemoteException {
        Parcel parcel0 = this.zba();
        zbc.zbd(parcel0, zbi0);
        zbc.zbc(parcel0, authorizationRequest0);
        this.zbb(1, parcel0);
    }
}

