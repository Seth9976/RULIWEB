package com.google.android.gms.auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.List;

public final class zzm implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        int v = SafeParcelReader.validateObjectHeader(parcel0);
        String s = null;
        Long long0 = null;
        List list0 = null;
        String s1 = null;
        int v1 = 0;
        boolean z = false;
        boolean z1 = false;
        while(parcel0.dataPosition() < v) {
            int v2 = SafeParcelReader.readHeader(parcel0);
            switch(((char)v2)) {
                case 1: {
                    v1 = SafeParcelReader.readInt(parcel0, v2);
                    break;
                }
                case 2: {
                    s = SafeParcelReader.createString(parcel0, v2);
                    break;
                }
                case 3: {
                    long0 = SafeParcelReader.readLongObject(parcel0, v2);
                    break;
                }
                case 4: {
                    z = SafeParcelReader.readBoolean(parcel0, v2);
                    break;
                }
                case 5: {
                    z1 = SafeParcelReader.readBoolean(parcel0, v2);
                    break;
                }
                case 6: {
                    list0 = SafeParcelReader.createStringList(parcel0, v2);
                    break;
                }
                case 7: {
                    s1 = SafeParcelReader.createString(parcel0, v2);
                    break;
                }
                default: {
                    SafeParcelReader.skipUnknownField(parcel0, v2);
                }
            }
        }
        SafeParcelReader.ensureAtEnd(parcel0, v);
        return new TokenData(v1, s, long0, z, z1, list0, s1);
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new TokenData[v];
    }
}

