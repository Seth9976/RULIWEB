package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

@Deprecated
public final class zzak extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    final int zza;

    static {
        zzak.CREATOR = new zzal();
    }

    zzak(int v) {
        this.zza = v;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 1, this.zza);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

