package com.google.android.gms.internal.auth-api;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.api.identity.SaveAccountLinkingTokenRequest;
import com.google.android.gms.auth.api.identity.SavePasswordRequest;

public final class zbm extends zba implements IInterface {
    zbm(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.auth.api.identity.internal.ICredentialSavingService");
    }

    public final void zbc(zbs zbs0, SaveAccountLinkingTokenRequest saveAccountLinkingTokenRequest0) throws RemoteException {
        Parcel parcel0 = this.zba();
        zbc.zbd(parcel0, zbs0);
        zbc.zbc(parcel0, saveAccountLinkingTokenRequest0);
        this.zbb(1, parcel0);
    }

    public final void zbd(zbu zbu0, SavePasswordRequest savePasswordRequest0) throws RemoteException {
        Parcel parcel0 = this.zba();
        zbc.zbd(parcel0, zbu0);
        zbc.zbc(parcel0, savePasswordRequest0);
        this.zbb(2, parcel0);
    }
}

