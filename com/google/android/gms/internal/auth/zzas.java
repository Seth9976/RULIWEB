package com.google.android.gms.internal.auth;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.api.accounttransfer.DeviceMetaData;
import com.google.android.gms.auth.api.accounttransfer.zzo;
import com.google.android.gms.auth.api.accounttransfer.zzw;
import com.google.android.gms.common.api.Status;

public abstract class zzas extends zzb implements zzat {
    public zzas() {
        super("com.google.android.gms.auth.api.accounttransfer.internal.IAccountTransferCallbacks");
    }

    @Override  // com.google.android.gms.internal.auth.zzb
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        switch(v) {
            case 1: {
                Status status0 = (Status)zzc.zza(parcel0, Status.CREATOR);
                zzc.zzb(parcel0);
                this.zzh(status0);
                return true;
            }
            case 2: {
                Status status1 = (Status)zzc.zza(parcel0, Status.CREATOR);
                zzw zzw0 = (zzw)zzc.zza(parcel0, zzw.CREATOR);
                zzc.zzb(parcel0);
                this.zzf(status1, zzw0);
                return true;
            }
            case 3: {
                Status status2 = (Status)zzc.zza(parcel0, Status.CREATOR);
                zzo zzo0 = (zzo)zzc.zza(parcel0, zzo.CREATOR);
                zzc.zzb(parcel0);
                this.zzg(status2, zzo0);
                return true;
            }
            case 4: {
                this.zze();
                return true;
            }
            case 5: {
                Status status3 = (Status)zzc.zza(parcel0, Status.CREATOR);
                zzc.zzb(parcel0);
                this.zzd(status3);
                return true;
            }
            case 6: {
                byte[] arr_b = parcel0.createByteArray();
                zzc.zzb(parcel0);
                this.zzb(arr_b);
                return true;
            }
            case 7: {
                DeviceMetaData deviceMetaData0 = (DeviceMetaData)zzc.zza(parcel0, DeviceMetaData.CREATOR);
                zzc.zzb(parcel0);
                this.zzc(deviceMetaData0);
                return true;
            }
            default: {
                return false;
            }
        }
    }
}

