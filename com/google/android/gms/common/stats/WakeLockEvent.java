package com.google.android.gms.common.stats;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.List;
import javax.annotation.Nullable;

@Deprecated
public final class WakeLockEvent extends StatsEvent {
    public static final Parcelable.Creator CREATOR;
    final int zza;
    private final long zzb;
    private final int zzc;
    private final String zzd;
    private final String zze;
    private final String zzf;
    private final int zzg;
    @Nullable
    private final List zzh;
    private final String zzi;
    private final long zzj;
    private final int zzk;
    private final String zzl;
    private final float zzm;
    private final long zzn;
    private final boolean zzo;

    static {
        WakeLockEvent.CREATOR = new zza();
    }

    WakeLockEvent(int v, long v1, int v2, String s, int v3, @Nullable List list0, String s1, long v4, int v5, String s2, String s3, float f, long v6, String s4, boolean z) {
        this.zza = v;
        this.zzb = v1;
        this.zzc = v2;
        this.zzd = s;
        this.zze = s2;
        this.zzf = s4;
        this.zzg = v3;
        this.zzh = list0;
        this.zzi = s1;
        this.zzj = v4;
        this.zzk = v5;
        this.zzl = s3;
        this.zzm = f;
        this.zzn = v6;
        this.zzo = z;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 1, this.zza);
        SafeParcelWriter.writeLong(parcel0, 2, this.zzb);
        SafeParcelWriter.writeString(parcel0, 4, this.zzd, false);
        SafeParcelWriter.writeInt(parcel0, 5, this.zzg);
        SafeParcelWriter.writeStringList(parcel0, 6, this.zzh, false);
        SafeParcelWriter.writeLong(parcel0, 8, this.zzj);
        SafeParcelWriter.writeString(parcel0, 10, this.zze, false);
        SafeParcelWriter.writeInt(parcel0, 11, this.zzc);
        SafeParcelWriter.writeString(parcel0, 12, this.zzi, false);
        SafeParcelWriter.writeString(parcel0, 13, this.zzl, false);
        SafeParcelWriter.writeInt(parcel0, 14, this.zzk);
        SafeParcelWriter.writeFloat(parcel0, 15, this.zzm);
        SafeParcelWriter.writeLong(parcel0, 16, this.zzn);
        SafeParcelWriter.writeString(parcel0, 17, this.zzf, false);
        SafeParcelWriter.writeBoolean(parcel0, 18, this.zzo);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }

    @Override  // com.google.android.gms.common.stats.StatsEvent
    public final int zza() {
        return this.zzc;
    }

    @Override  // com.google.android.gms.common.stats.StatsEvent
    public final long zzb() {
        return this.zzb;
    }

    @Override  // com.google.android.gms.common.stats.StatsEvent
    public final String zzc() {
        String s = "";
        String s1 = this.zzh == null ? "" : TextUtils.join(",", this.zzh);
        String s2 = this.zze;
        String s3 = this.zzl;
        float f = this.zzm;
        String s4 = this.zzf;
        boolean z = this.zzo;
        StringBuilder stringBuilder0 = new StringBuilder("\t");
        stringBuilder0.append(this.zzd);
        stringBuilder0.append("\t");
        stringBuilder0.append(this.zzg);
        stringBuilder0.append("\t");
        stringBuilder0.append(s1);
        stringBuilder0.append("\t");
        stringBuilder0.append(this.zzk);
        stringBuilder0.append("\t");
        if(s2 == null) {
            s2 = "";
        }
        stringBuilder0.append(s2);
        stringBuilder0.append("\t");
        if(s3 == null) {
            s3 = "";
        }
        stringBuilder0.append(s3);
        stringBuilder0.append("\t");
        stringBuilder0.append(f);
        stringBuilder0.append("\t");
        if(s4 != null) {
            s = s4;
        }
        stringBuilder0.append(s);
        stringBuilder0.append("\t");
        stringBuilder0.append(z);
        return stringBuilder0.toString();
    }
}

