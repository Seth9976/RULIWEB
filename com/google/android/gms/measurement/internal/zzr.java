package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.List;

public final class zzr implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        int v = SafeParcelReader.validateObjectHeader(parcel0);
        long v1 = 0L;
        long v2 = 0L;
        long v3 = 0L;
        long v4 = 0L;
        long v5 = 0L;
        long v6 = 0L;
        String s = null;
        String s1 = null;
        String s2 = null;
        String s3 = null;
        String s4 = null;
        String s5 = null;
        String s6 = null;
        Boolean boolean0 = null;
        List list0 = null;
        String s7 = null;
        String s8 = null;
        String s9 = "";
        String s10 = "";
        long v7 = 0xFFFFFFFF80000000L;
        boolean z = true;
        boolean z1 = false;
        int v8 = 0;
        boolean z2 = true;
        boolean z3 = false;
        boolean z4 = false;
        while(parcel0.dataPosition() < v) {
            int v9 = SafeParcelReader.readHeader(parcel0);
            switch(((char)v9)) {
                case 2: {
                    s = SafeParcelReader.createString(parcel0, v9);
                    break;
                }
                case 3: {
                    s1 = SafeParcelReader.createString(parcel0, v9);
                    break;
                }
                case 4: {
                    s2 = SafeParcelReader.createString(parcel0, v9);
                    break;
                }
                case 5: {
                    s3 = SafeParcelReader.createString(parcel0, v9);
                    break;
                }
                case 6: {
                    v1 = SafeParcelReader.readLong(parcel0, v9);
                    break;
                }
                case 7: {
                    v2 = SafeParcelReader.readLong(parcel0, v9);
                    break;
                }
                case 8: {
                    s4 = SafeParcelReader.createString(parcel0, v9);
                    break;
                }
                case 9: {
                    z = SafeParcelReader.readBoolean(parcel0, v9);
                    break;
                }
                case 10: {
                    z1 = SafeParcelReader.readBoolean(parcel0, v9);
                    break;
                }
                case 11: {
                    v7 = SafeParcelReader.readLong(parcel0, v9);
                    break;
                }
                case 12: {
                    s5 = SafeParcelReader.createString(parcel0, v9);
                    break;
                }
                case 13: {
                    v3 = SafeParcelReader.readLong(parcel0, v9);
                    break;
                }
                case 14: {
                    v4 = SafeParcelReader.readLong(parcel0, v9);
                    break;
                }
                case 15: {
                    v8 = SafeParcelReader.readInt(parcel0, v9);
                    break;
                }
                case 16: {
                    z2 = SafeParcelReader.readBoolean(parcel0, v9);
                    break;
                }
                case 18: {
                    z3 = SafeParcelReader.readBoolean(parcel0, v9);
                    break;
                }
                case 19: {
                    s6 = SafeParcelReader.createString(parcel0, v9);
                    break;
                }
                case 21: {
                    boolean0 = SafeParcelReader.readBooleanObject(parcel0, v9);
                    break;
                }
                case 22: {
                    v5 = SafeParcelReader.readLong(parcel0, v9);
                    break;
                }
                case 23: {
                    list0 = SafeParcelReader.createStringList(parcel0, v9);
                    break;
                }
                case 24: {
                    s7 = SafeParcelReader.createString(parcel0, v9);
                    break;
                }
                case 25: {
                    s9 = SafeParcelReader.createString(parcel0, v9);
                    break;
                }
                case 26: {
                    s10 = SafeParcelReader.createString(parcel0, v9);
                    break;
                }
                case 27: {
                    s8 = SafeParcelReader.createString(parcel0, v9);
                    break;
                }
                case 28: {
                    z4 = SafeParcelReader.readBoolean(parcel0, v9);
                    break;
                }
                case 29: {
                    v6 = SafeParcelReader.readLong(parcel0, v9);
                    break;
                }
                default: {
                    SafeParcelReader.skipUnknownField(parcel0, v9);
                }
            }
        }
        SafeParcelReader.ensureAtEnd(parcel0, v);
        return new zzq(s, s1, s2, s3, v1, v2, s4, z, z1, v7, s5, v3, v4, v8, z2, z3, s6, boolean0, v5, list0, s7, s9, s10, s8, z4, v6);
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new zzq[v];
    }
}

