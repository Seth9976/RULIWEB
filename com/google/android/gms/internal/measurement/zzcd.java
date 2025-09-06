package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzcd extends zzbm implements zzcf {
    zzcd(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.measurement.api.internal.IBundleReceiver");
    }

    @Override  // com.google.android.gms.internal.measurement.zzcf
    public final void zze(Bundle bundle0) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzbo.zzd(parcel0, bundle0);
        this.zzc(1, parcel0);
    }
}

