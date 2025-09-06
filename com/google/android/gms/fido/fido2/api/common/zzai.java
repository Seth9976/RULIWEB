package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Arrays;

public final class zzai extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    private final byte[][] zza;

    static {
        zzai.CREATOR = new zzaj();
    }

    public zzai(byte[][] arr2_b) {
        Preconditions.checkArgument(arr2_b != null);
        boolean z;
        Preconditions.checkArgument(1 == (arr2_b.length & 1 ^ 1));
        for(int v = 0; v < arr2_b.length; v += 2) {
            Preconditions.checkArgument(v == 0 || arr2_b[v] != null);
            Preconditions.checkArgument(arr2_b[v + 1] != null);
            switch(arr2_b[v + 1].length) {
                case 0x20: 
                case 0x40: {
                    z = true;
                    break;
                }
                default: {
                    z = false;
                }
            }
            Preconditions.checkArgument(z);
        }
        this.zza = arr2_b;
    }

    @Override
    public final boolean equals(Object object0) {
        return object0 instanceof zzai ? Arrays.deepEquals(this.zza, ((zzai)object0).zza) : false;
    }

    @Override
    public final int hashCode() {
        byte[][] arr2_b = this.zza;
        int v1 = 0;
        for(int v = 0; v < arr2_b.length; ++v) {
            v1 ^= Objects.hashCode(new Object[]{arr2_b[v]});
        }
        return v1;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeByteArrayArray(parcel0, 1, this.zza, false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

