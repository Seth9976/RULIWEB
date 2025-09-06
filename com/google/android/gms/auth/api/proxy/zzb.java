package com.google.android.gms.auth.api.proxy;

import android.app.PendingIntent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzb implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        int v = SafeParcelReader.validateObjectHeader(parcel0);
        PendingIntent pendingIntent0 = null;
        Bundle bundle0 = null;
        byte[] arr_b = null;
        int v1 = 0;
        int v2 = 0;
        int v3 = 0;
        while(parcel0.dataPosition() < v) {
            int v4 = SafeParcelReader.readHeader(parcel0);
            switch(((char)v4)) {
                case 1: {
                    v2 = SafeParcelReader.readInt(parcel0, v4);
                    break;
                }
                case 2: {
                    pendingIntent0 = (PendingIntent)SafeParcelReader.createParcelable(parcel0, v4, PendingIntent.CREATOR);
                    break;
                }
                case 3: {
                    v3 = SafeParcelReader.readInt(parcel0, v4);
                    break;
                }
                case 4: {
                    bundle0 = SafeParcelReader.createBundle(parcel0, v4);
                    break;
                }
                case 5: {
                    arr_b = SafeParcelReader.createByteArray(parcel0, v4);
                    break;
                }
                case 1000: {
                    v1 = SafeParcelReader.readInt(parcel0, v4);
                    break;
                }
                default: {
                    SafeParcelReader.skipUnknownField(parcel0, v4);
                }
            }
        }
        SafeParcelReader.ensureAtEnd(parcel0, v);
        return new ProxyResponse(v1, v2, pendingIntent0, v3, bundle0, arr_b);
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new ProxyResponse[v];
    }
}

