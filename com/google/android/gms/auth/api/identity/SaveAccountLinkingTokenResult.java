package com.google.android.gms.auth.api.identity;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public class SaveAccountLinkingTokenResult extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    private final PendingIntent zba;

    static {
        SaveAccountLinkingTokenResult.CREATOR = new zbo();
    }

    public SaveAccountLinkingTokenResult(PendingIntent pendingIntent0) {
        this.zba = pendingIntent0;
    }

    @Override
    public boolean equals(Object object0) {
        return object0 instanceof SaveAccountLinkingTokenResult ? Objects.equal(this.zba, ((SaveAccountLinkingTokenResult)object0).zba) : false;
    }

    public PendingIntent getPendingIntent() {
        return this.zba;
    }

    public boolean hasResolution() {
        return this.zba != null;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(new Object[]{this.zba});
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeParcelable(parcel0, 1, this.getPendingIntent(), v, false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

