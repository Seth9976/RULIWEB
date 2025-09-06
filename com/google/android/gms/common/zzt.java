package com.google.android.gms.common;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzt implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        int v = SafeParcelReader.validateObjectHeader(parcel0);
        boolean z = false;
        String s = null;
        IBinder iBinder0 = null;
        boolean z1 = false;
        while(parcel0.dataPosition() < v) {
            int v1 = SafeParcelReader.readHeader(parcel0);
            switch(((char)v1)) {
                case 1: {
                    s = SafeParcelReader.createString(parcel0, v1);
                    break;
                }
                case 2: {
                    iBinder0 = SafeParcelReader.readIBinder(parcel0, v1);
                    break;
                }
                case 3: {
                    z = SafeParcelReader.readBoolean(parcel0, v1);
                    break;
                }
                case 4: {
                    z1 = SafeParcelReader.readBoolean(parcel0, v1);
                    break;
                }
                default: {
                    SafeParcelReader.skipUnknownField(parcel0, v1);
                }
            }
        }
        SafeParcelReader.ensureAtEnd(parcel0, v);
        return new zzs(s, iBinder0, z, z1);
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new zzs[v];
    }
}

