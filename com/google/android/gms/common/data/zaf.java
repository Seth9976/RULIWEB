package com.google.android.gms.common.data;

import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zaf implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        int v = SafeParcelReader.validateObjectHeader(parcel0);
        String[] arr_s = null;
        CursorWindow[] arr_cursorWindow = null;
        Bundle bundle0 = null;
        int v1 = 0;
        int v2 = 0;
        while(parcel0.dataPosition() < v) {
            int v3 = SafeParcelReader.readHeader(parcel0);
            switch(((char)v3)) {
                case 1: {
                    arr_s = SafeParcelReader.createStringArray(parcel0, v3);
                    break;
                }
                case 2: {
                    arr_cursorWindow = (CursorWindow[])SafeParcelReader.createTypedArray(parcel0, v3, CursorWindow.CREATOR);
                    break;
                }
                case 3: {
                    v2 = SafeParcelReader.readInt(parcel0, v3);
                    break;
                }
                case 4: {
                    bundle0 = SafeParcelReader.createBundle(parcel0, v3);
                    break;
                }
                case 1000: {
                    v1 = SafeParcelReader.readInt(parcel0, v3);
                    break;
                }
                default: {
                    SafeParcelReader.skipUnknownField(parcel0, v3);
                }
            }
        }
        SafeParcelReader.ensureAtEnd(parcel0, v);
        DataHolder dataHolder0 = new DataHolder(v1, arr_s, arr_cursorWindow, v2, bundle0);
        dataHolder0.zad();
        return dataHolder0;
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new DataHolder[v];
    }
}

