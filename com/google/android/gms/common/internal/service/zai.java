package com.google.android.gms.common.internal.service;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.internal.TelemetryData;
import com.google.android.gms.internal.base.zaa;
import com.google.android.gms.internal.base.zac;

public final class zai extends zaa implements IInterface {
    zai(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.common.internal.service.IClientTelemetryService");
    }

    public final void zae(TelemetryData telemetryData0) throws RemoteException {
        Parcel parcel0 = this.zaa();
        zac.zac(parcel0, telemetryData0);
        this.zad(1, parcel0);
    }
}

