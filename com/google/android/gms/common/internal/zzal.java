package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzal implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        int v = SafeParcelReader.validateObjectHeader(parcel0);
        int v1 = 0;
        while(parcel0.dataPosition() < v) {
            int v2 = SafeParcelReader.readHeader(parcel0);
            if(((char)v2) == 1) {
                v1 = SafeParcelReader.readInt(parcel0, v2);
            }
            else {
                SafeParcelReader.skipUnknownField(parcel0, v2);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel0, v);
        return new zzak(v1);
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new zzak[v];
    }
}

