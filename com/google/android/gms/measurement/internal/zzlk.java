package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public final class zzlk extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    public final int zza;
    public final String zzb;
    public final long zzc;
    public final Long zzd;
    public final String zze;
    public final String zzf;
    public final Double zzg;

    static {
        zzlk.CREATOR = new zzll();
    }

    zzlk(int v, String s, long v1, Long long0, Float float0, String s1, String s2, Double double0) {
        this.zza = v;
        this.zzb = s;
        this.zzc = v1;
        this.zzd = long0;
        if(v == 1) {
            this.zzg = float0 == null ? null : float0.doubleValue();
        }
        else {
            this.zzg = double0;
        }
        this.zze = s1;
        this.zzf = s2;
    }

    zzlk(zzlm zzlm0) {
        this(zzlm0.zzc, zzlm0.zzd, zzlm0.zze, zzlm0.zzb);
    }

    zzlk(String s, long v, Object object0, String s1) {
        Preconditions.checkNotEmpty(s);
        this.zza = 2;
        this.zzb = s;
        this.zzc = v;
        this.zzf = s1;
        if(object0 == null) {
            this.zzd = null;
            this.zzg = null;
            this.zze = null;
            return;
        }
        if(object0 instanceof Long) {
            this.zzd = (Long)object0;
            this.zzg = null;
            this.zze = null;
            return;
        }
        if(object0 instanceof String) {
            this.zzd = null;
            this.zzg = null;
            this.zze = (String)object0;
            return;
        }
        if(!(object0 instanceof Double)) {
            throw new IllegalArgumentException("User attribute given of un-supported type");
        }
        this.zzd = null;
        this.zzg = (Double)object0;
        this.zze = null;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        zzll.zza(this, parcel0, v);
    }

    public final Object zza() {
        Long long0 = this.zzd;
        if(long0 != null) {
            return long0;
        }
        Double double0 = this.zzg;
        if(double0 != null) {
            return double0;
        }
        String s = this.zze;
        return s != null ? s : null;
    }
}

