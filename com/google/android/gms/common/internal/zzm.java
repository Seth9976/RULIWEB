package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzm implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        int v = SafeParcelReader.validateObjectHeader(parcel0);
        RootTelemetryConfiguration rootTelemetryConfiguration0 = null;
        int[] arr_v = null;
        int[] arr_v1 = null;
        boolean z = false;
        boolean z1 = false;
        int v1 = 0;
        while(parcel0.dataPosition() < v) {
            int v2 = SafeParcelReader.readHeader(parcel0);
            switch(((char)v2)) {
                case 1: {
                    rootTelemetryConfiguration0 = (RootTelemetryConfiguration)SafeParcelReader.createParcelable(parcel0, v2, RootTelemetryConfiguration.CREATOR);
                    break;
                }
                case 2: {
                    z = SafeParcelReader.readBoolean(parcel0, v2);
                    break;
                }
                case 3: {
                    z1 = SafeParcelReader.readBoolean(parcel0, v2);
                    break;
                }
                case 4: {
                    arr_v = SafeParcelReader.createIntArray(parcel0, v2);
                    break;
                }
                case 5: {
                    v1 = SafeParcelReader.readInt(parcel0, v2);
                    break;
                }
                case 6: {
                    arr_v1 = SafeParcelReader.createIntArray(parcel0, v2);
                    break;
                }
                default: {
                    SafeParcelReader.skipUnknownField(parcel0, v2);
                }
            }
        }
        SafeParcelReader.ensureAtEnd(parcel0, v);
        return new ConnectionTelemetryConfiguration(rootTelemetryConfiguration0, z, z1, arr_v, v1, arr_v1);
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new ConnectionTelemetryConfiguration[v];
    }
}

