package com.google.android.gms.common.server;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public class FavaDiagnosticsEntity extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator CREATOR;
    final int zaa;
    public final String zab;
    public final int zac;

    static {
        FavaDiagnosticsEntity.CREATOR = new zaa();
    }

    public FavaDiagnosticsEntity(int v, String s, int v1) {
        this.zaa = v;
        this.zab = s;
        this.zac = v1;
    }

    public FavaDiagnosticsEntity(String s, int v) {
        this.zaa = 1;
        this.zab = s;
        this.zac = v;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 1, this.zaa);
        SafeParcelWriter.writeString(parcel0, 2, this.zab, false);
        SafeParcelWriter.writeInt(parcel0, 3, this.zac);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

