package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public final class zzau extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    public final String zza;
    public final zzas zzb;
    public final String zzc;
    public final long zzd;

    static {
        zzau.CREATOR = new zzav();
    }

    zzau(zzau zzau0, long v) {
        Preconditions.checkNotNull(zzau0);
        this.zza = zzau0.zza;
        this.zzb = zzau0.zzb;
        this.zzc = zzau0.zzc;
        this.zzd = v;
    }

    public zzau(String s, zzas zzas0, String s1, long v) {
        this.zza = s;
        this.zzb = zzas0;
        this.zzc = s1;
        this.zzd = v;
    }

    @Override
    public final String toString() {
        return "origin=" + this.zzc + ",name=" + this.zza + ",params=" + this.zzb;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        zzav.zza(this, parcel0, v);
    }
}

