package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class zzaw implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        String s = parcel0.readString();
        try {
            return zzay.zza(s);
        }
        catch(zzax zzax0) {
            throw new RuntimeException(zzax0);
        }
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new zzay[v];
    }
}

