package com.google.android.gms.internal.auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public final class zzaq extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    final int zza;
    public final String zzb;

    static {
        zzaq.CREATOR = new zzar();
    }

    zzaq(int v, String s) {
        this.zza = 1;
        this.zzb = (String)Preconditions.checkNotNull(s);
    }

    public zzaq(String s) {
        this(1, s);
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 1, this.zza);
        SafeParcelWriter.writeString(parcel0, 2, this.zzb, false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

