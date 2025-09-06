package com.google.android.gms.common;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzp implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        int v = SafeParcelReader.validateObjectHeader(parcel0);
        String s = null;
        IBinder iBinder0 = null;
        boolean z = false;
        boolean z1 = false;
        boolean z2 = false;
        boolean z3 = false;
        while(parcel0.dataPosition() < v) {
            int v1 = SafeParcelReader.readHeader(parcel0);
            switch(((char)v1)) {
                case 1: {
                    s = SafeParcelReader.createString(parcel0, v1);
                    break;
                }
                case 2: {
                    z = SafeParcelReader.readBoolean(parcel0, v1);
                    break;
                }
                case 3: {
                    z1 = SafeParcelReader.readBoolean(parcel0, v1);
                    break;
                }
                case 4: {
                    iBinder0 = SafeParcelReader.readIBinder(parcel0, v1);
                    break;
                }
                case 5: {
                    z2 = SafeParcelReader.readBoolean(parcel0, v1);
                    break;
                }
                case 6: {
                    z3 = SafeParcelReader.readBoolean(parcel0, v1);
                    break;
                }
                default: {
                    SafeParcelReader.skipUnknownField(parcel0, v1);
                }
            }
        }
        SafeParcelReader.ensureAtEnd(parcel0, v);
        return new zzo(s, z, z1, iBinder0, z2, z3);
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new zzo[v];
    }
}

