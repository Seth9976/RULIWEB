package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzcg extends zzbm implements zzci {
    zzcg(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.measurement.api.internal.IEventHandlerProxy");
    }

    @Override  // com.google.android.gms.internal.measurement.zzci
    public final int zzd() throws RemoteException {
        Parcel parcel0 = this.zzb(2, this.zza());
        int v = parcel0.readInt();
        parcel0.recycle();
        return v;
    }

    @Override  // com.google.android.gms.internal.measurement.zzci
    public final void zze(String s, String s1, Bundle bundle0, long v) throws RemoteException {
        Parcel parcel0 = this.zza();
        parcel0.writeString(s);
        parcel0.writeString(s1);
        zzbo.zzd(parcel0, bundle0);
        parcel0.writeLong(v);
        this.zzc(1, parcel0);
    }
}

