package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public final class zzab extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    private final long zza;

    static {
        zzab.CREATOR = new zzac();
    }

    public zzab(long v) {
        this.zza = (long)(((Long)Preconditions.checkNotNull(v)));
    }

    @Override
    public final boolean equals(Object object0) {
        return object0 instanceof zzab ? this.zza == ((zzab)object0).zza : false;
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zza});
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeLong(parcel0, 1, this.zza);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

