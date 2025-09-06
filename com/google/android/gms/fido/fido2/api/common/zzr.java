package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzr implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        int v = SafeParcelReader.validateObjectHeader(parcel0);
        byte[] arr_b = null;
        byte[] arr_b1 = null;
        byte[] arr_b2 = null;
        long v1 = 0L;
        while(parcel0.dataPosition() < v) {
            int v2 = SafeParcelReader.readHeader(parcel0);
            switch(((char)v2)) {
                case 1: {
                    v1 = SafeParcelReader.readLong(parcel0, v2);
                    break;
                }
                case 2: {
                    arr_b = SafeParcelReader.createByteArray(parcel0, v2);
                    break;
                }
                case 3: {
                    arr_b1 = SafeParcelReader.createByteArray(parcel0, v2);
                    break;
                }
                case 4: {
                    arr_b2 = SafeParcelReader.createByteArray(parcel0, v2);
                    break;
                }
                default: {
                    SafeParcelReader.skipUnknownField(parcel0, v2);
                }
            }
        }
        SafeParcelReader.ensureAtEnd(parcel0, v);
        return new zzq(v1, arr_b, arr_b1, arr_b2);
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new zzq[v];
    }
}

