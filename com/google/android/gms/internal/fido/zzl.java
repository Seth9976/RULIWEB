package com.google.android.gms.internal.fido;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

public abstract class zzl extends zzb implements zzm {
    public zzl() {
        super("com.google.android.gms.fido.fido2.internal.privileged.IFido2PrivilegedCallbacks");
    }

    @Override  // com.google.android.gms.internal.fido.zzb
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        if(v == 1) {
            Status status0 = (Status)zzc.zza(parcel0, Status.CREATOR);
            PendingIntent pendingIntent0 = (PendingIntent)zzc.zza(parcel0, PendingIntent.CREATOR);
            zzc.zzc(parcel0);
            this.zzb(status0, pendingIntent0);
            return true;
        }
        return false;
    }
}

