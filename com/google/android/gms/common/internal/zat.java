package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public final class zat extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    final int zaa;
    private final Account zab;
    private final int zac;
    private final GoogleSignInAccount zad;

    static {
        zat.CREATOR = new zau();
    }

    zat(int v, Account account0, int v1, GoogleSignInAccount googleSignInAccount0) {
        this.zaa = v;
        this.zab = account0;
        this.zac = v1;
        this.zad = googleSignInAccount0;
    }

    public zat(Account account0, int v, GoogleSignInAccount googleSignInAccount0) {
        this(2, account0, v, googleSignInAccount0);
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 1, this.zaa);
        SafeParcelWriter.writeParcelable(parcel0, 2, this.zab, v, false);
        SafeParcelWriter.writeInt(parcel0, 3, this.zac);
        SafeParcelWriter.writeParcelable(parcel0, 4, this.zad, v, false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

