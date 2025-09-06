package com.google.android.gms.auth.api.proxy;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zza implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        int v = SafeParcelReader.validateObjectHeader(parcel0);
        String s = null;
        byte[] arr_b = null;
        Bundle bundle0 = null;
        long v1 = 0L;
        int v2 = 0;
        int v3 = 0;
        while(parcel0.dataPosition() < v) {
            int v4 = SafeParcelReader.readHeader(parcel0);
            switch(((char)v4)) {
                case 1: {
                    s = SafeParcelReader.createString(parcel0, v4);
                    break;
                }
                case 2: {
                    v3 = SafeParcelReader.readInt(parcel0, v4);
                    break;
                }
                case 3: {
                    v1 = SafeParcelReader.readLong(parcel0, v4);
                    break;
                }
                case 4: {
                    arr_b = SafeParcelReader.createByteArray(parcel0, v4);
                    break;
                }
                case 5: {
                    bundle0 = SafeParcelReader.createBundle(parcel0, v4);
                    break;
                }
                case 1000: {
                    v2 = SafeParcelReader.readInt(parcel0, v4);
                    break;
                }
                default: {
                    SafeParcelReader.skipUnknownField(parcel0, v4);
                }
            }
        }
        SafeParcelReader.ensureAtEnd(parcel0, v);
        return new ProxyRequest(v2, s, v3, v1, arr_b, bundle0);
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new ProxyRequest[v];
    }
}

