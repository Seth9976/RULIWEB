package com.google.android.gms.internal.auth-api-phone;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

public abstract class zzi extends zzb implements zzj {
    public zzi() {
        super("com.google.android.gms.auth.api.phone.internal.ISmsRetrieverResultCallback");
    }

    @Override  // com.google.android.gms.internal.auth-api-phone.zzb
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        if(v == 1) {
            Status status0 = (Status)zzc.zza(parcel0, Status.CREATOR);
            zzc.zzb(parcel0);
            this.zzb(status0);
            return true;
        }
        return false;
    }
}

