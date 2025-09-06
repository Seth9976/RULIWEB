package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public final class zam extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    final int zaa;
    final String zab;
    final Field zac;

    static {
        zam.CREATOR = new zak();
    }

    zam(int v, String s, Field fastJsonResponse$Field0) {
        this.zaa = v;
        this.zab = s;
        this.zac = fastJsonResponse$Field0;
    }

    zam(String s, Field fastJsonResponse$Field0) {
        this.zaa = 1;
        this.zab = s;
        this.zac = fastJsonResponse$Field0;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 1, this.zaa);
        SafeParcelWriter.writeString(parcel0, 2, this.zab, false);
        SafeParcelWriter.writeParcelable(parcel0, 3, this.zac, v, false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

