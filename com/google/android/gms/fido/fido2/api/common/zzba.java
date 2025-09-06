package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzba implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        int v = SafeParcelReader.validateObjectHeader(parcel0);
        int v1 = 0;
        int v2 = 0;
        int v3 = 0;
        while(parcel0.dataPosition() < v) {
            int v4 = SafeParcelReader.readHeader(parcel0);
            switch(((char)v4)) {
                case 1: {
                    v1 = SafeParcelReader.readInt(parcel0, v4);
                    break;
                }
                case 2: {
                    v2 = SafeParcelReader.readShort(parcel0, v4);
                    break;
                }
                case 3: {
                    v3 = SafeParcelReader.readShort(parcel0, v4);
                    break;
                }
                default: {
                    SafeParcelReader.skipUnknownField(parcel0, v4);
                }
            }
        }
        SafeParcelReader.ensureAtEnd(parcel0, v);
        return new UvmEntry(v1, ((short)v2), ((short)v3));
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new UvmEntry[v];
    }
}

