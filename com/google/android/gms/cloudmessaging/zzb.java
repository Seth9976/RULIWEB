package com.google.android.gms.cloudmessaging;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class zzb implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        return new zzd(parcel0.readStrongBinder());
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new zzd[v];
    }
}

