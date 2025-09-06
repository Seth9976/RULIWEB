package com.google.android.gms.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import javax.annotation.Nullable;

public final class zzq extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    private final boolean zza;
    @Nullable
    private final String zzb;
    private final int zzc;
    private final int zzd;

    static {
        zzq.CREATOR = new zzr();
    }

    zzq(boolean z, String s, int v, int v1) {
        this.zza = z;
        this.zzb = s;
        this.zzc = zzy.zza(v) - 1;
        this.zzd = zzd.zza(v1) - 1;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeBoolean(parcel0, 1, this.zza);
        SafeParcelWriter.writeString(parcel0, 2, this.zzb, false);
        SafeParcelWriter.writeInt(parcel0, 3, this.zzc);
        SafeParcelWriter.writeInt(parcel0, 4, this.zzd);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }

    @Nullable
    public final String zza() {
        return this.zzb;
    }

    public final boolean zzb() {
        return this.zza;
    }

    public final int zzc() {
        return zzd.zza(this.zzd);
    }

    public final int zzd() {
        return zzy.zza(this.zzc);
    }
}

