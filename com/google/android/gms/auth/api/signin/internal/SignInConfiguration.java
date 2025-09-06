package com.google.android.gms.auth.api.signin.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public final class SignInConfiguration extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator CREATOR;
    private final String zba;
    private final GoogleSignInOptions zbb;

    static {
        SignInConfiguration.CREATOR = new zbu();
    }

    public SignInConfiguration(String s, GoogleSignInOptions googleSignInOptions0) {
        this.zba = Preconditions.checkNotEmpty(s);
        this.zbb = googleSignInOptions0;
    }

    @Override
    public final boolean equals(Object object0) {
        if(!(object0 instanceof SignInConfiguration)) {
            return false;
        }
        if(this.zba.equals(((SignInConfiguration)object0).zba)) {
            GoogleSignInOptions googleSignInOptions0 = ((SignInConfiguration)object0).zbb;
            return this.zbb == null ? googleSignInOptions0 == null : this.zbb.equals(googleSignInOptions0);
        }
        return false;
    }

    @Override
    public final int hashCode() {
        return new HashAccumulator().addObject(this.zba).addObject(this.zbb).hash();
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeString(parcel0, 2, this.zba, false);
        SafeParcelWriter.writeParcelable(parcel0, 5, this.zbb, v, false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }

    public final GoogleSignInOptions zba() {
        return this.zbb;
    }
}

