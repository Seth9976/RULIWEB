package com.google.android.gms.cloudmessaging;

import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import android.os.RemoteException;

public final class zzd implements Parcelable {
    public static final Parcelable.Creator CREATOR;
    Messenger zza;
    IMessengerCompat zzb;

    static {
        zzd.CREATOR = new zzb();
    }

    public zzd(IBinder iBinder0) {
        this.zza = new Messenger(iBinder0);
    }

    @Override  // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override
    public final boolean equals(Object object0) {
        if(object0 == null) {
            return false;
        }
        try {
            return this.zza().equals(((zzd)object0).zza());
        }
        catch(ClassCastException unused_ex) {
            return false;
        }
    }

    @Override
    public final int hashCode() {
        return this.zza().hashCode();
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        Messenger messenger0 = this.zza;
        if(messenger0 != null) {
            parcel0.writeStrongBinder(messenger0.getBinder());
            return;
        }
        parcel0.writeStrongBinder(this.zzb.asBinder());
    }

    public final IBinder zza() {
        Messenger messenger0 = this.zza;
        return messenger0 == null ? this.zzb.asBinder() : messenger0.getBinder();
    }

    public final void zzb(Message message0) throws RemoteException {
        Messenger messenger0 = this.zza;
        if(messenger0 != null) {
            messenger0.send(message0);
            return;
        }
        this.zzb.send(message0);
    }
}

