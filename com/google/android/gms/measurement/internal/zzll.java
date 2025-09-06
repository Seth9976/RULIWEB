package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public final class zzll implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        int v = SafeParcelReader.validateObjectHeader(parcel0);
        String s = null;
        Long long0 = null;
        Float float0 = null;
        String s1 = null;
        String s2 = null;
        Double double0 = null;
        long v1 = 0L;
        int v2 = 0;
        while(parcel0.dataPosition() < v) {
            int v3 = SafeParcelReader.readHeader(parcel0);
            switch(((char)v3)) {
                case 1: {
                    v2 = SafeParcelReader.readInt(parcel0, v3);
                    break;
                }
                case 2: {
                    s = SafeParcelReader.createString(parcel0, v3);
                    break;
                }
                case 3: {
                    v1 = SafeParcelReader.readLong(parcel0, v3);
                    break;
                }
                case 4: {
                    long0 = SafeParcelReader.readLongObject(parcel0, v3);
                    break;
                }
                case 5: {
                    float0 = SafeParcelReader.readFloatObject(parcel0, v3);
                    break;
                }
                case 6: {
                    s1 = SafeParcelReader.createString(parcel0, v3);
                    break;
                }
                case 7: {
                    s2 = SafeParcelReader.createString(parcel0, v3);
                    break;
                }
                case 8: {
                    double0 = SafeParcelReader.readDoubleObject(parcel0, v3);
                    break;
                }
                default: {
                    SafeParcelReader.skipUnknownField(parcel0, v3);
                }
            }
        }
        SafeParcelReader.ensureAtEnd(parcel0, v);
        return new zzlk(v2, s, v1, long0, float0, s1, s2, double0);
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new zzlk[v];
    }

    static void zza(zzlk zzlk0, Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 1, zzlk0.zza);
        SafeParcelWriter.writeString(parcel0, 2, zzlk0.zzb, false);
        SafeParcelWriter.writeLong(parcel0, 3, zzlk0.zzc);
        SafeParcelWriter.writeLongObject(parcel0, 4, zzlk0.zzd, false);
        SafeParcelWriter.writeFloatObject(parcel0, 5, null, false);
        SafeParcelWriter.writeString(parcel0, 6, zzlk0.zze, false);
        SafeParcelWriter.writeString(parcel0, 7, zzlk0.zzf, false);
        SafeParcelWriter.writeDoubleObject(parcel0, 8, zzlk0.zzg, false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

