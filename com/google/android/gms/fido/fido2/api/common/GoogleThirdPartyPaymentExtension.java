package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public class GoogleThirdPartyPaymentExtension extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    private final boolean zza;

    static {
        GoogleThirdPartyPaymentExtension.CREATOR = new zzaf();
    }

    public GoogleThirdPartyPaymentExtension(boolean z) {
        this.zza = z;
    }

    @Override
    public boolean equals(Object object0) {
        return object0 instanceof GoogleThirdPartyPaymentExtension ? this.zza == ((GoogleThirdPartyPaymentExtension)object0).getThirdPartyPayment() : false;
    }

    public boolean getThirdPartyPayment() {
        return this.zza;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(new Object[]{Boolean.valueOf(this.zza)});
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeBoolean(parcel0, 1, this.getThirdPartyPayment());
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

