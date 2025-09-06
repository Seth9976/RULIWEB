package com.google.android.gms.internal.auth;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.AccountChangeEventsResponse;
import com.google.android.gms.common.api.Status;

public abstract class zzl extends zzb implements zzm {
    public zzl() {
        super("com.google.android.gms.auth.account.data.IGetAccountChangeEventsCallback");
    }

    @Override  // com.google.android.gms.internal.auth.zzb
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        if(v == 2) {
            Status status0 = (Status)zzc.zza(parcel0, Status.CREATOR);
            AccountChangeEventsResponse accountChangeEventsResponse0 = (AccountChangeEventsResponse)zzc.zza(parcel0, AccountChangeEventsResponse.CREATOR);
            zzc.zzb(parcel0);
            this.zzb(status0, accountChangeEventsResponse0);
            return true;
        }
        return false;
    }
}

