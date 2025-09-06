package com.google.android.gms.internal.auth-api;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

public abstract class zbn extends zbb implements zbo {
    public zbn() {
        super("com.google.android.gms.auth.api.identity.internal.IGetPhoneNumberHintIntentCallback");
    }

    @Override  // com.google.android.gms.internal.auth-api.zbb
    protected final boolean zba(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        if(v == 1) {
            Status status0 = (Status)zbc.zba(parcel0, Status.CREATOR);
            PendingIntent pendingIntent0 = (PendingIntent)zbc.zba(parcel0, PendingIntent.CREATOR);
            zbc.zbb(parcel0);
            this.zbb(status0, pendingIntent0);
            return true;
        }
        return false;
    }
}

