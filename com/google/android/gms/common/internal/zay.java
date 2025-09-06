package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zay implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        int v = SafeParcelReader.validateObjectHeader(parcel0);
        Scope[] arr_scope = null;
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
                    v3 = SafeParcelReader.readInt(parcel0, v4);
                    break;
                }
                case 4: {
                    arr_scope = (Scope[])SafeParcelReader.createTypedArray(parcel0, v4, Scope.CREATOR);
                    break;
                }
                default: {
                    SafeParcelReader.skipUnknownField(parcel0, v4);
                }
            }
        }
        SafeParcelReader.ensureAtEnd(parcel0, v);
        return new zax(v1, v2, v3, arr_scope);
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new zax[v];
    }
}

