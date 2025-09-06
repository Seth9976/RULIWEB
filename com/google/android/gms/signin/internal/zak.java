package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.zav;

public final class zak extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    final int zaa;
    private final ConnectionResult zab;
    private final zav zac;

    static {
        zak.CREATOR = new zal();
    }

    zak(int v, ConnectionResult connectionResult0, zav zav0) {
        this.zaa = v;
        this.zab = connectionResult0;
        this.zac = zav0;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 1, this.zaa);
        SafeParcelWriter.writeParcelable(parcel0, 2, this.zab, v, false);
        SafeParcelWriter.writeParcelable(parcel0, 3, this.zac, v, false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }

    public final ConnectionResult zaa() {
        return this.zab;
    }

    public final zav zab() {
        return this.zac;
    }
}

