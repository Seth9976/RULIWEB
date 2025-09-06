package com.google.android.gms.auth.api.accounttransfer;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.HashSet;

public final class zzx implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        int v = SafeParcelReader.validateObjectHeader(parcel0);
        HashSet hashSet0 = new HashSet();
        int v1 = 0;
        String s = null;
        byte[] arr_b = null;
        PendingIntent pendingIntent0 = null;
        DeviceMetaData deviceMetaData0 = null;
        int v2 = 0;
        while(parcel0.dataPosition() < v) {
            int v3 = SafeParcelReader.readHeader(parcel0);
            switch(((char)v3)) {
                case 1: {
                    v1 = SafeParcelReader.readInt(parcel0, v3);
                    hashSet0.add(1);
                    break;
                }
                case 2: {
                    s = SafeParcelReader.createString(parcel0, v3);
                    hashSet0.add(2);
                    break;
                }
                case 3: {
                    v2 = SafeParcelReader.readInt(parcel0, v3);
                    hashSet0.add(3);
                    break;
                }
                case 4: {
                    arr_b = SafeParcelReader.createByteArray(parcel0, v3);
                    hashSet0.add(4);
                    break;
                }
                case 5: {
                    pendingIntent0 = (PendingIntent)SafeParcelReader.createParcelable(parcel0, v3, PendingIntent.CREATOR);
                    hashSet0.add(5);
                    break;
                }
                case 6: {
                    deviceMetaData0 = (DeviceMetaData)SafeParcelReader.createParcelable(parcel0, v3, DeviceMetaData.CREATOR);
                    hashSet0.add(6);
                    break;
                }
                default: {
                    SafeParcelReader.skipUnknownField(parcel0, v3);
                }
            }
        }
        if(parcel0.dataPosition() != v) {
            throw new ParseException("Overread allowed size end=" + v, parcel0);
        }
        return new zzw(hashSet0, v1, s, v2, arr_b, pendingIntent0, deviceMetaData0);
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new zzw[v];
    }
}

