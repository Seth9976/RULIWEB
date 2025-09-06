package com.google.android.gms.auth.api.signin;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public class SignInAccount extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator CREATOR;
    @Deprecated
    final String zba;
    @Deprecated
    final String zbb;
    private final GoogleSignInAccount zbc;

    static {
        SignInAccount.CREATOR = new zbc();
    }

    SignInAccount(String s, GoogleSignInAccount googleSignInAccount0, String s1) {
        this.zbc = googleSignInAccount0;
        this.zba = Preconditions.checkNotEmpty(s, "8.3 and 8.4 SDKs require non-null email");
        this.zbb = Preconditions.checkNotEmpty(s1, "8.3 and 8.4 SDKs require non-null userId");
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeString(parcel0, 4, this.zba, false);
        SafeParcelWriter.writeParcelable(parcel0, 7, this.zbc, v, false);
        SafeParcelWriter.writeString(parcel0, 8, this.zbb, false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }

    public final GoogleSignInAccount zba() {
        return this.zbc;
    }
}

