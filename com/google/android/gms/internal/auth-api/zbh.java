package com.google.android.gms.internal.auth-api;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.api.identity.AuthorizationResult;
import com.google.android.gms.common.api.Status;

public abstract class zbh extends zbb implements zbi {
    public zbh() {
        super("com.google.android.gms.auth.api.identity.internal.IAuthorizationCallback");
    }

    @Override  // com.google.android.gms.internal.auth-api.zbb
    protected final boolean zba(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        if(v == 1) {
            Status status0 = (Status)zbc.zba(parcel0, Status.CREATOR);
            AuthorizationResult authorizationResult0 = (AuthorizationResult)zbc.zba(parcel0, AuthorizationResult.CREATOR);
            zbc.zbb(parcel0);
            this.zbb(status0, authorizationResult0);
            return true;
        }
        return false;
    }
}

