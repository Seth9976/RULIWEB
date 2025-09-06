package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public final class zzag extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    private final String zza;

    static {
        zzag.CREATOR = new zzah();
    }

    public zzag(String s) {
        this.zza = (String)Preconditions.checkNotNull(s);
    }

    @Override
    public final boolean equals(Object object0) {
        return object0 instanceof zzag ? this.zza.equals(((zzag)object0).zza) : false;
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zza});
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeString(parcel0, 1, this.zza, false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

