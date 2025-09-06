package com.google.android.gms.internal.auth;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public final class zzbb extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    final int zza;
    public final String zzb;
    public final PendingIntent zzc;

    static {
        zzbb.CREATOR = new zzbc();
    }

    zzbb(int v, String s, PendingIntent pendingIntent0) {
        this.zza = 1;
        this.zzb = (String)Preconditions.checkNotNull(s);
        this.zzc = (PendingIntent)Preconditions.checkNotNull(pendingIntent0);
    }

    public zzbb(String s, PendingIntent pendingIntent0) {
        this(1, s, pendingIntent0);
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 1, this.zza);
        SafeParcelWriter.writeString(parcel0, 2, this.zzb, false);
        SafeParcelWriter.writeParcelable(parcel0, 3, this.zzc, v, false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

