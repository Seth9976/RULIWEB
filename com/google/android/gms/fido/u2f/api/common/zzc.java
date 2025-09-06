package com.google.android.gms.fido.u2f.api.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class zzc implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        return ErrorCode.toErrorCode(parcel0.readInt());
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new ErrorCode[v];
    }
}

