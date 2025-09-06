package com.google.android.gms.internal.fido;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import java.util.ArrayList;

public abstract class zzf extends zzb implements zzg {
    public zzf() {
        super("com.google.android.gms.fido.fido2.api.ICredentialListCallback");
    }

    @Override  // com.google.android.gms.internal.fido.zzb
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        switch(v) {
            case 1: {
                ArrayList arrayList0 = zzc.zzb(parcel0);
                zzc.zzc(parcel0);
                this.zzb(arrayList0);
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

