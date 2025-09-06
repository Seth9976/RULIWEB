package com.google.android.gms.internal.auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public final class zzav extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    final int zza;
    public final String zzb;
    public final int zzc;

    static {
        zzav.CREATOR = new zzaw();
    }

    zzav(int v, String s, int v1) {
        this.zza = 1;
        this.zzb = (String)Preconditions.checkNotNull(s);
        this.zzc = v1;
    }

    public zzav(String s, int v) {
        this(1, s, v);
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 1, this.zza);
        SafeParcelWriter.writeString(parcel0, 2, this.zzb, false);
        SafeParcelWriter.writeInt(parcel0, 3, this.zzc);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

