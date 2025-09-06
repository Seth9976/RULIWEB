package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzce extends zzbn implements zzcf {
    public zzce() {
        super("com.google.android.gms.measurement.api.internal.IBundleReceiver");
    }

    @Override  // com.google.android.gms.internal.measurement.zzbn
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        if(v == 1) {
            Bundle bundle0 = (Bundle)zzbo.zza(parcel0, Bundle.CREATOR);
            zzbo.zzc(parcel0);
            this.zze(bundle0);
            parcel1.writeNoException();
            return true;
        }
        return false;
    }
}

