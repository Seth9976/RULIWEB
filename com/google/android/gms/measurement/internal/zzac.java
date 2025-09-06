package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public final class zzac extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    public String zza;
    public String zzb;
    public zzlk zzc;
    public long zzd;
    public boolean zze;
    public String zzf;
    public final zzau zzg;
    public long zzh;
    public zzau zzi;
    public final long zzj;
    public final zzau zzk;

    static {
        zzac.CREATOR = new zzad();
    }

    zzac(zzac zzac0) {
        Preconditions.checkNotNull(zzac0);
        this.zza = zzac0.zza;
        this.zzb = zzac0.zzb;
        this.zzc = zzac0.zzc;
        this.zzd = zzac0.zzd;
        this.zze = zzac0.zze;
        this.zzf = zzac0.zzf;
        this.zzg = zzac0.zzg;
        this.zzh = zzac0.zzh;
        this.zzi = zzac0.zzi;
        this.zzj = zzac0.zzj;
        this.zzk = zzac0.zzk;
    }

    zzac(String s, String s1, zzlk zzlk0, long v, boolean z, String s2, zzau zzau0, long v1, zzau zzau1, long v2, zzau zzau2) {
        this.zza = s;
        this.zzb = s1;
        this.zzc = zzlk0;
        this.zzd = v;
        this.zze = z;
        this.zzf = s2;
        this.zzg = zzau0;
        this.zzh = v1;
        this.zzi = zzau1;
        this.zzj = v2;
        this.zzk = zzau2;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeString(parcel0, 2, this.zza, false);
        SafeParcelWriter.writeString(parcel0, 3, this.zzb, false);
        SafeParcelWriter.writeParcelable(parcel0, 4, this.zzc, v, false);
        SafeParcelWriter.writeLong(parcel0, 5, this.zzd);
        SafeParcelWriter.writeBoolean(parcel0, 6, this.zze);
        SafeParcelWriter.writeString(parcel0, 7, this.zzf, false);
        SafeParcelWriter.writeParcelable(parcel0, 8, this.zzg, v, false);
        SafeParcelWriter.writeLong(parcel0, 9, this.zzh);
        SafeParcelWriter.writeParcelable(parcel0, 10, this.zzi, v, false);
        SafeParcelWriter.writeLong(parcel0, 11, this.zzj);
        SafeParcelWriter.writeParcelable(parcel0, 12, this.zzk, v, false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

