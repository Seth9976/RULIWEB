package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public final class zav extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    final int zaa;
    final IBinder zab;
    private final ConnectionResult zac;
    private final boolean zad;
    private final boolean zae;

    static {
        zav.CREATOR = new zaw();
    }

    zav(int v, IBinder iBinder0, ConnectionResult connectionResult0, boolean z, boolean z1) {
        this.zaa = v;
        this.zab = iBinder0;
        this.zac = connectionResult0;
        this.zad = z;
        this.zae = z1;
    }

    @Override
    public final boolean equals(Object object0) {
        if(object0 == null) {
            return false;
        }
        if(this == object0) {
            return true;
        }
        return object0 instanceof zav ? this.zac.equals(((zav)object0).zac) && Objects.equal(this.zab(), ((zav)object0).zab()) : false;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 1, this.zaa);
        SafeParcelWriter.writeIBinder(parcel0, 2, this.zab, false);
        SafeParcelWriter.writeParcelable(parcel0, 3, this.zac, v, false);
        SafeParcelWriter.writeBoolean(parcel0, 4, this.zad);
        SafeParcelWriter.writeBoolean(parcel0, 5, this.zae);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }

    public final ConnectionResult zaa() {
        return this.zac;
    }

    public final IAccountAccessor zab() {
        return this.zab == null ? null : Stub.asInterface(this.zab);
    }

    public final boolean zac() {
        return this.zad;
    }

    public final boolean zad() {
        return this.zae;
    }
}

