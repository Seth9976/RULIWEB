package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class zzat implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        String s = parcel0.readString();
        try {
            return TokenBindingStatus.fromString(s);
        }
        catch(UnsupportedTokenBindingStatusException tokenBinding$UnsupportedTokenBindingStatusException0) {
            throw new RuntimeException(tokenBinding$UnsupportedTokenBindingStatusException0);
        }
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new TokenBindingStatus[v];
    }
}

