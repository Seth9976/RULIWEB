package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.server.converter.zaa;

public final class zaj implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        int v = SafeParcelReader.validateObjectHeader(parcel0);
        String s = null;
        String s1 = null;
        zaa zaa0 = null;
        int v1 = 0;
        int v2 = 0;
        boolean z = false;
        int v3 = 0;
        boolean z1 = false;
        int v4 = 0;
        while(parcel0.dataPosition() < v) {
            int v5 = SafeParcelReader.readHeader(parcel0);
            switch(((char)v5)) {
                case 1: {
                    v1 = SafeParcelReader.readInt(parcel0, v5);
                    break;
                }
                case 2: {
                    v2 = SafeParcelReader.readInt(parcel0, v5);
                    break;
                }
                case 3: {
                    z = SafeParcelReader.readBoolean(parcel0, v5);
                    break;
                }
                case 4: {
                    v3 = SafeParcelReader.readInt(parcel0, v5);
                    break;
                }
                case 5: {
                    z1 = SafeParcelReader.readBoolean(parcel0, v5);
                    break;
                }
                case 6: {
                    s = SafeParcelReader.createString(parcel0, v5);
                    break;
                }
                case 7: {
                    v4 = SafeParcelReader.readInt(parcel0, v5);
                    break;
                }
                case 8: {
                    s1 = SafeParcelReader.createString(parcel0, v5);
                    break;
                }
                case 9: {
                    zaa0 = (zaa)SafeParcelReader.createParcelable(parcel0, v5, zaa.CREATOR);
                    break;
                }
                default: {
                    SafeParcelReader.skipUnknownField(parcel0, v5);
                }
            }
        }
        SafeParcelReader.ensureAtEnd(parcel0, v);
        return new Field(v1, v2, z, v3, z1, s, v4, s1, zaa0);
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new Field[v];
    }
}

