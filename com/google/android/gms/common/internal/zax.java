package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public final class zax extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    final int zaa;
    private final int zab;
    private final int zac;
    @Deprecated
    private final Scope[] zad;

    static {
        zax.CREATOR = new zay();
    }

    zax(int v, int v1, int v2, Scope[] arr_scope) {
        this.zaa = v;
        this.zab = v1;
        this.zac = v2;
        this.zad = arr_scope;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 1, this.zaa);
        SafeParcelWriter.writeInt(parcel0, 2, this.zab);
        SafeParcelWriter.writeInt(parcel0, 3, this.zac);
        SafeParcelWriter.writeTypedArray(parcel0, 4, this.zad, v, false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

