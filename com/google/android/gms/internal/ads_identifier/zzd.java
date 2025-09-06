package com.google.android.gms.internal.ads_identifier;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzd extends zza implements zzf {
    zzd(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
    }

    @Override  // com.google.android.gms.internal.ads_identifier.zzf
    public final String zzc() throws RemoteException {
        Parcel parcel0 = this.zzb(1, this.zza());
        String s = parcel0.readString();
        parcel0.recycle();
        return s;
    }

    @Override  // com.google.android.gms.internal.ads_identifier.zzf
    public final boolean zzd() throws RemoteException {
        Parcel parcel0 = this.zzb(6, this.zza());
        boolean z = zzc.zzb(parcel0);
        parcel0.recycle();
        return z;
    }

    @Override  // com.google.android.gms.internal.ads_identifier.zzf
    public final boolean zze(boolean z) throws RemoteException {
        Parcel parcel0 = this.zza();
        zzc.zza(parcel0, true);
        Parcel parcel1 = this.zzb(2, parcel0);
        boolean z1 = zzc.zzb(parcel1);
        parcel1.recycle();
        return z1;
    }
}

