package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.common.zzb;
import com.google.android.gms.internal.common.zzc;

public abstract class zzac extends zzb implements IGmsCallbacks {
    public zzac() {
        super("com.google.android.gms.common.internal.IGmsCallbacks");
    }

    @Override  // com.google.android.gms.internal.common.zzb
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        switch(v) {
            case 1: {
                int v2 = parcel0.readInt();
                IBinder iBinder0 = parcel0.readStrongBinder();
                Bundle bundle0 = (Bundle)zzc.zza(parcel0, Bundle.CREATOR);
                zzc.zzb(parcel0);
                this.onPostInitComplete(v2, iBinder0, bundle0);
                break;
            }
            case 2: {
                int v3 = parcel0.readInt();
                Bundle bundle1 = (Bundle)zzc.zza(parcel0, Bundle.CREATOR);
                zzc.zzb(parcel0);
                this.zzb(v3, bundle1);
                break;
            }
            case 3: {
                int v4 = parcel0.readInt();
                IBinder iBinder1 = parcel0.readStrongBinder();
                zzk zzk0 = (zzk)zzc.zza(parcel0, zzk.CREATOR);
                zzc.zzb(parcel0);
                this.zzc(v4, iBinder1, zzk0);
                break;
            }
            default: {
                return false;
            }
        }
        parcel1.writeNoException();
        return true;
    }
}

