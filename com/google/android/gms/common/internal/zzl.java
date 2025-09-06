package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzl implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        int v = SafeParcelReader.validateObjectHeader(parcel0);
        Bundle bundle0 = null;
        Feature[] arr_feature = null;
        ConnectionTelemetryConfiguration connectionTelemetryConfiguration0 = null;
        int v1 = 0;
        while(parcel0.dataPosition() < v) {
            int v2 = SafeParcelReader.readHeader(parcel0);
            switch(((char)v2)) {
                case 1: {
                    bundle0 = SafeParcelReader.createBundle(parcel0, v2);
                    break;
                }
                case 2: {
                    arr_feature = (Feature[])SafeParcelReader.createTypedArray(parcel0, v2, Feature.CREATOR);
                    break;
                }
                case 3: {
                    v1 = SafeParcelReader.readInt(parcel0, v2);
                    break;
                }
                case 4: {
                    connectionTelemetryConfiguration0 = (ConnectionTelemetryConfiguration)SafeParcelReader.createParcelable(parcel0, v2, ConnectionTelemetryConfiguration.CREATOR);
                    break;
                }
                default: {
                    SafeParcelReader.skipUnknownField(parcel0, v2);
                }
            }
        }
        SafeParcelReader.ensureAtEnd(parcel0, v);
        return new zzk(bundle0, arr_feature, v1, connectionTelemetryConfiguration0);
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new zzk[v];
    }
}

