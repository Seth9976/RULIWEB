package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.zat;

public final class zai extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    final int zaa;
    final zat zab;

    static {
        zai.CREATOR = new zaj();
    }

    zai(int v, zat zat0) {
        this.zaa = v;
        this.zab = zat0;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 1, this.zaa);
        SafeParcelWriter.writeParcelable(parcel0, 2, this.zab, v, false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

