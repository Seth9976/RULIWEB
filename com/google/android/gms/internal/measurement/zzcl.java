package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public final class zzcl extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    public final long zza;
    public final long zzb;
    public final boolean zzc;
    public final String zzd;
    public final String zze;
    public final String zzf;
    public final Bundle zzg;
    public final String zzh;

    static {
        zzcl.CREATOR = new zzcm();
    }

    public zzcl(long v, long v1, boolean z, String s, String s1, String s2, Bundle bundle0, String s3) {
        this.zza = v;
        this.zzb = v1;
        this.zzc = z;
        this.zzd = s;
        this.zze = s1;
        this.zzf = s2;
        this.zzg = bundle0;
        this.zzh = s3;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeLong(parcel0, 1, this.zza);
        SafeParcelWriter.writeLong(parcel0, 2, this.zzb);
        SafeParcelWriter.writeBoolean(parcel0, 3, this.zzc);
        SafeParcelWriter.writeString(parcel0, 4, this.zzd, false);
        SafeParcelWriter.writeString(parcel0, 5, this.zze, false);
        SafeParcelWriter.writeString(parcel0, 6, this.zzf, false);
        SafeParcelWriter.writeBundle(parcel0, 7, this.zzg, false);
        SafeParcelWriter.writeString(parcel0, 8, this.zzh, false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

