package com.google.android.gms.internal.auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public final class zzbw extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    final int zza;
    String zzb;

    static {
        zzbw.CREATOR = new zzbx();
    }

    public zzbw() {
        this.zza = 1;
    }

    zzbw(int v, String s) {
        this.zza = v;
        this.zzb = s;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 1, this.zza);
        SafeParcelWriter.writeString(parcel0, 2, this.zzb, false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }

    public final zzbw zza(String s) {
        this.zzb = s;
        return this;
    }
}

