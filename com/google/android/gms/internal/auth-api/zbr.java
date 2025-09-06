package com.google.android.gms.internal.auth-api;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.api.identity.SaveAccountLinkingTokenResult;
import com.google.android.gms.common.api.Status;

public abstract class zbr extends zbb implements zbs {
    public zbr() {
        super("com.google.android.gms.auth.api.identity.internal.ISaveAccountLinkingTokenCallback");
    }

    @Override  // com.google.android.gms.internal.auth-api.zbb
    protected final boolean zba(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        if(v == 1) {
            Status status0 = (Status)zbc.zba(parcel0, Status.CREATOR);
            SaveAccountLinkingTokenResult saveAccountLinkingTokenResult0 = (SaveAccountLinkingTokenResult)zbc.zba(parcel0, SaveAccountLinkingTokenResult.CREATOR);
            zbc.zbb(parcel0);
            this.zbb(status0, saveAccountLinkingTokenResult0);
            return true;
        }
        return false;
    }
}

