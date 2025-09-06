package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zan implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        int v = SafeParcelReader.validateObjectHeader(parcel0);
        String s = null;
        String s1 = null;
        long v1 = 0L;
        long v2 = 0L;
        int v3 = 0;
        int v4 = 0;
        int v5 = 0;
        int v6 = 0;
        int v7 = -1;
        while(parcel0.dataPosition() < v) {
            int v8 = SafeParcelReader.readHeader(parcel0);
            switch(((char)v8)) {
                case 1: {
                    v3 = SafeParcelReader.readInt(parcel0, v8);
                    break;
                }
                case 2: {
                    v4 = SafeParcelReader.readInt(parcel0, v8);
                    break;
                }
                case 3: {
                    v5 = SafeParcelReader.readInt(parcel0, v8);
                    break;
                }
                case 4: {
                    v1 = SafeParcelReader.readLong(parcel0, v8);
                    break;
                }
                case 5: {
                    v2 = SafeParcelReader.readLong(parcel0, v8);
                    break;
                }
                case 6: {
                    s = SafeParcelReader.createString(parcel0, v8);
                    break;
                }
                case 7: {
                    s1 = SafeParcelReader.createString(parcel0, v8);
                    break;
                }
                case 8: {
                    v6 = SafeParcelReader.readInt(parcel0, v8);
                    break;
                }
                case 9: {
                    v7 = SafeParcelReader.readInt(parcel0, v8);
                    break;
                }
                default: {
                    SafeParcelReader.skipUnknownField(parcel0, v8);
                }
            }
        }
        SafeParcelReader.ensureAtEnd(parcel0, v);
        return new MethodInvocation(v3, v4, v5, v1, v2, s, s1, v6, v7);
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new MethodInvocation[v];
    }
}

