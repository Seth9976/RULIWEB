package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public final class zzav implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        int v = SafeParcelReader.validateObjectHeader(parcel0);
        long v1 = 0L;
        String s = null;
        zzas zzas0 = null;
        String s1 = null;
        while(parcel0.dataPosition() < v) {
            int v2 = SafeParcelReader.readHeader(parcel0);
            switch(((char)v2)) {
                case 2: {
                    s = SafeParcelReader.createString(parcel0, v2);
                    break;
                }
                case 3: {
                    zzas0 = (zzas)SafeParcelReader.createParcelable(parcel0, v2, zzas.CREATOR);
                    break;
                }
                case 4: {
                    s1 = SafeParcelReader.createString(parcel0, v2);
                    break;
                }
                case 5: {
                    v1 = SafeParcelReader.readLong(parcel0, v2);
                    break;
                }
                default: {
                    SafeParcelReader.skipUnknownField(parcel0, v2);
                }
            }
        }
        SafeParcelReader.ensureAtEnd(parcel0, v);
        return new zzau(s, zzas0, s1, v1);
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new zzau[v];
    }

    static void zza(zzau zzau0, Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeString(parcel0, 2, zzau0.zza, false);
        SafeParcelWriter.writeParcelable(parcel0, 3, zzau0.zzb, v, false);
        SafeParcelWriter.writeString(parcel0, 4, zzau0.zzc, false);
        SafeParcelWriter.writeLong(parcel0, 5, zzau0.zzd);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

