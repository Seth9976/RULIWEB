package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class zzp implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        int v = parcel0.readInt();
        try {
            return COSEAlgorithmIdentifier.fromCoseValue(v);
        }
        catch(UnsupportedAlgorithmIdentifierException cOSEAlgorithmIdentifier$UnsupportedAlgorithmIdentifierException0) {
            throw new RuntimeException(cOSEAlgorithmIdentifier$UnsupportedAlgorithmIdentifierException0);
        }
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new COSEAlgorithmIdentifier[v];
    }
}

