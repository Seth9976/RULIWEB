package com.google.android.gms.internal.auth;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

public abstract class zzn extends zzb implements zzo {
    public zzn() {
        super("com.google.android.gms.auth.account.data.IGetTokenWithDetailsCallback");
    }

    @Override  // com.google.android.gms.internal.auth.zzb
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        if(v == 2) {
            Status status0 = (Status)zzc.zza(parcel0, Status.CREATOR);
            Bundle bundle0 = (Bundle)zzc.zza(parcel0, Bundle.CREATOR);
            zzc.zzb(parcel0);
            this.zzb(status0, bundle0);
            return true;
        }
        return false;
    }
}

