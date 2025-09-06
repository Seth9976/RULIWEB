package com.google.android.gms.common.moduleinstall;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zae implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        int v = SafeParcelReader.validateObjectHeader(parcel0);
        Long long0 = null;
        Long long1 = null;
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
                    v2 = SafeParcelReader.readInt(parcel0, v4);
                    break;
                }
                case 3: {
                    long0 = SafeParcelReader.readLongObject(parcel0, v4);
                    break;
                }
                case 4: {
                    long1 = SafeParcelReader.readLongObject(parcel0, v4);
                    break;
                }
                case 5: {
                    v3 = SafeParcelReader.readInt(parcel0, v4);
                    break;
                }
                default: {
                    SafeParcelReader.skipUnknownField(parcel0, v4);
                }
            }
        }
        SafeParcelReader.ensureAtEnd(parcel0, v);
        return new ModuleInstallStatusUpdate(v1, v2, long0, long1, v3);
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new ModuleInstallStatusUpdate[v];
    }
}

