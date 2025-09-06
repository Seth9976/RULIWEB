package com.google.android.gms.internal.auth-api;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.GetPhoneNumberHintIntentRequest;
import com.google.android.gms.auth.api.identity.GetSignInIntentRequest;
import com.google.android.gms.common.api.internal.IStatusCallback;

public final class zbv extends zba implements IInterface {
    zbv(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.auth.api.identity.internal.ISignInService");
    }

    public final void zbc(zbl zbl0, BeginSignInRequest beginSignInRequest0) throws RemoteException {
        Parcel parcel0 = this.zba();
        zbc.zbd(parcel0, zbl0);
        zbc.zbc(parcel0, beginSignInRequest0);
        this.zbb(1, parcel0);
    }

    public final void zbd(zbo zbo0, GetPhoneNumberHintIntentRequest getPhoneNumberHintIntentRequest0, String s) throws RemoteException {
        Parcel parcel0 = this.zba();
        zbc.zbd(parcel0, zbo0);
        zbc.zbc(parcel0, getPhoneNumberHintIntentRequest0);
        parcel0.writeString(s);
        this.zbb(4, parcel0);
    }

    public final void zbe(zbq zbq0, GetSignInIntentRequest getSignInIntentRequest0) throws RemoteException {
        Parcel parcel0 = this.zba();
        zbc.zbd(parcel0, zbq0);
        zbc.zbc(parcel0, getSignInIntentRequest0);
        this.zbb(3, parcel0);
    }

    public final void zbf(IStatusCallback iStatusCallback0, String s) throws RemoteException {
        Parcel parcel0 = this.zba();
        zbc.zbd(parcel0, iStatusCallback0);
        parcel0.writeString(s);
        this.zbb(2, parcel0);
    }
}

