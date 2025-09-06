package com.google.android.gms.internal.auth-api;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.api.identity.SavePasswordResult;
import com.google.android.gms.common.api.Status;

public abstract class zbt extends zbb implements zbu {
    public zbt() {
        super("com.google.android.gms.auth.api.identity.internal.ISavePasswordCallback");
    }

    @Override  // com.google.android.gms.internal.auth-api.zbb
    protected final boolean zba(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        if(v == 1) {
            Status status0 = (Status)zbc.zba(parcel0, Status.CREATOR);
            SavePasswordResult savePasswordResult0 = (SavePasswordResult)zbc.zba(parcel0, SavePasswordResult.CREATOR);
            zbc.zbb(parcel0);
            this.zbb(status0, savePasswordResult0);
            return true;
        }
        return false;
    }
}

