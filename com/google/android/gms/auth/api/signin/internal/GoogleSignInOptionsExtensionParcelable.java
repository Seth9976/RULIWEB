package com.google.android.gms.auth.api.signin.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.api.signin.GoogleSignInOptionsExtension;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public class GoogleSignInOptionsExtensionParcelable extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    final int zaa;
    private int zab;
    private Bundle zac;

    static {
        GoogleSignInOptionsExtensionParcelable.CREATOR = new zaa();
    }

    GoogleSignInOptionsExtensionParcelable(int v, int v1, Bundle bundle0) {
        this.zaa = v;
        this.zab = v1;
        this.zac = bundle0;
    }

    public GoogleSignInOptionsExtensionParcelable(GoogleSignInOptionsExtension googleSignInOptionsExtension0) {
        this(1, googleSignInOptionsExtension0.getExtensionType(), googleSignInOptionsExtension0.toBundle());
    }

    public int getType() {
        return this.zab;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 1, this.zaa);
        SafeParcelWriter.writeInt(parcel0, 2, this.getType());
        SafeParcelWriter.writeBundle(parcel0, 3, this.zac, false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

