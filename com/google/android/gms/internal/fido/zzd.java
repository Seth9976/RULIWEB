package com.google.android.gms.internal.fido;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

public abstract class zzd extends zzb implements zze {
    public zzd() {
        super("com.google.android.gms.fido.fido2.api.IBooleanCallback");
    }

    @Override  // com.google.android.gms.internal.fido.zzb
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        boolean z = false;
        switch(v) {
            case 1: {
                int v2 = parcel0.readInt();
                zzc.zzc(parcel0);
                if(v2 != 0) {
                    z = true;
                }
                this.zzb(z);
                break;
            }
            case 2: {
                Status status0 = (Status)zzc.zza(parcel0, Status.CREATOR);
                zzc.zzc(parcel0);
                this.zzc(status0);
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

