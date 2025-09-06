package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.List;

public final class zzt implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        int v = SafeParcelReader.validateObjectHeader(parcel0);
        List list0 = null;
        List list1 = null;
        List list2 = null;
        List list3 = null;
        List list4 = null;
        int v1 = 0;
        while(parcel0.dataPosition() < v) {
            int v2 = SafeParcelReader.readHeader(parcel0);
            switch(((char)v2)) {
                case 1: {
                    v1 = SafeParcelReader.readInt(parcel0, v2);
                    break;
                }
                case 2: {
                    list0 = SafeParcelReader.createStringList(parcel0, v2);
                    break;
                }
                case 3: {
                    list1 = SafeParcelReader.createStringList(parcel0, v2);
                    break;
                }
                case 4: {
                    list2 = SafeParcelReader.createStringList(parcel0, v2);
                    break;
                }
                case 5: {
                    list3 = SafeParcelReader.createStringList(parcel0, v2);
                    break;
                }
                case 6: {
                    list4 = SafeParcelReader.createStringList(parcel0, v2);
                    break;
                }
                default: {
                    SafeParcelReader.skipUnknownField(parcel0, v2);
                }
            }
        }
        SafeParcelReader.ensureAtEnd(parcel0, v);
        return new zzs(v1, list0, list1, list2, list3, list4);
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new zzs[v];
    }
}

