package com.google.android.gms.auth.api.identity;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

@Deprecated
public final class BeginSignInResult extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    private final PendingIntent zba;

    static {
        BeginSignInResult.CREATOR = new zbe();
    }

    public BeginSignInResult(PendingIntent pendingIntent0) {
        this.zba = (PendingIntent)Preconditions.checkNotNull(pendingIntent0);
    }

    public PendingIntent getPendingIntent() {
        return this.zba;
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeParcelable(parcel0, 1, this.getPendingIntent(), v, false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

