package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.List;

public final class zzq extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    public final String zza;
    public final String zzb;
    public final String zzc;
    public final String zzd;
    public final long zze;
    public final long zzf;
    public final String zzg;
    public final boolean zzh;
    public final boolean zzi;
    public final long zzj;
    public final String zzk;
    @Deprecated
    public final long zzl;
    public final long zzm;
    public final int zzn;
    public final boolean zzo;
    public final boolean zzp;
    public final String zzq;
    public final Boolean zzr;
    public final long zzs;
    public final List zzt;
    public final String zzu;
    public final String zzv;
    public final String zzw;
    public final String zzx;
    public final boolean zzy;
    public final long zzz;

    static {
        zzq.CREATOR = new zzr();
    }

    zzq(String s, String s1, String s2, long v, String s3, long v1, long v2, String s4, boolean z, boolean z1, String s5, long v3, long v4, int v5, boolean z2, boolean z3, String s6, Boolean boolean0, long v6, List list0, String s7, String s8, String s9, String s10, boolean z4, long v7) {
        Preconditions.checkNotEmpty(s);
        this.zza = s;
        if(TextUtils.isEmpty(s1)) {
            s1 = null;
        }
        this.zzb = s1;
        this.zzc = s2;
        this.zzj = v;
        this.zzd = s3;
        this.zze = v1;
        this.zzf = v2;
        this.zzg = s4;
        this.zzh = z;
        this.zzi = z1;
        this.zzk = s5;
        this.zzl = 0L;
        this.zzm = v4;
        this.zzn = v5;
        this.zzo = z2;
        this.zzp = z3;
        this.zzq = s6;
        this.zzr = boolean0;
        this.zzs = v6;
        this.zzt = list0;
        this.zzu = null;
        this.zzv = s8;
        this.zzw = s9;
        this.zzx = s10;
        this.zzy = z4;
        this.zzz = v7;
    }

    zzq(String s, String s1, String s2, String s3, long v, long v1, String s4, boolean z, boolean z1, long v2, String s5, long v3, long v4, int v5, boolean z2, boolean z3, String s6, Boolean boolean0, long v6, List list0, String s7, String s8, String s9, String s10, boolean z4, long v7) {
        this.zza = s;
        this.zzb = s1;
        this.zzc = s2;
        this.zzj = v2;
        this.zzd = s3;
        this.zze = v;
        this.zzf = v1;
        this.zzg = s4;
        this.zzh = z;
        this.zzi = z1;
        this.zzk = s5;
        this.zzl = v3;
        this.zzm = v4;
        this.zzn = v5;
        this.zzo = z2;
        this.zzp = z3;
        this.zzq = s6;
        this.zzr = boolean0;
        this.zzs = v6;
        this.zzt = list0;
        this.zzu = s7;
        this.zzv = s8;
        this.zzw = s9;
        this.zzx = s10;
        this.zzy = z4;
        this.zzz = v7;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeString(parcel0, 2, this.zza, false);
        SafeParcelWriter.writeString(parcel0, 3, this.zzb, false);
        SafeParcelWriter.writeString(parcel0, 4, this.zzc, false);
        SafeParcelWriter.writeString(parcel0, 5, this.zzd, false);
        SafeParcelWriter.writeLong(parcel0, 6, this.zze);
        SafeParcelWriter.writeLong(parcel0, 7, this.zzf);
        SafeParcelWriter.writeString(parcel0, 8, this.zzg, false);
        SafeParcelWriter.writeBoolean(parcel0, 9, this.zzh);
        SafeParcelWriter.writeBoolean(parcel0, 10, this.zzi);
        SafeParcelWriter.writeLong(parcel0, 11, this.zzj);
        SafeParcelWriter.writeString(parcel0, 12, this.zzk, false);
        SafeParcelWriter.writeLong(parcel0, 13, this.zzl);
        SafeParcelWriter.writeLong(parcel0, 14, this.zzm);
        SafeParcelWriter.writeInt(parcel0, 15, this.zzn);
        SafeParcelWriter.writeBoolean(parcel0, 16, this.zzo);
        SafeParcelWriter.writeBoolean(parcel0, 18, this.zzp);
        SafeParcelWriter.writeString(parcel0, 19, this.zzq, false);
        SafeParcelWriter.writeBooleanObject(parcel0, 21, this.zzr, false);
        SafeParcelWriter.writeLong(parcel0, 22, this.zzs);
        SafeParcelWriter.writeStringList(parcel0, 23, this.zzt, false);
        SafeParcelWriter.writeString(parcel0, 24, this.zzu, false);
        SafeParcelWriter.writeString(parcel0, 25, this.zzv, false);
        SafeParcelWriter.writeString(parcel0, 26, this.zzw, false);
        SafeParcelWriter.writeString(parcel0, 27, this.zzx, false);
        SafeParcelWriter.writeBoolean(parcel0, 28, this.zzy);
        SafeParcelWriter.writeLong(parcel0, 29, this.zzz);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

