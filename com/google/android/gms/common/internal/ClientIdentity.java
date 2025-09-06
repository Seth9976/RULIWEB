package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public class ClientIdentity extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    public final String packageName;
    public final int uid;

    static {
        ClientIdentity.CREATOR = new zaa();
    }

    public ClientIdentity(int v, String s) {
        this.uid = v;
        this.packageName = s;
    }

    @Override
    public final boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        return object0 instanceof ClientIdentity ? ((ClientIdentity)object0).uid == this.uid && Objects.equal(((ClientIdentity)object0).packageName, this.packageName) : false;
    }

    @Override
    public final int hashCode() {
        return this.uid;
    }

    @Override
    public final String toString() {
        return this.uid + ":" + this.packageName;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 1, this.uid);
        SafeParcelWriter.writeString(parcel0, 2, this.packageName, false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

