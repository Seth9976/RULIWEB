package com.google.android.gms.internal.auth;

import android.net.Uri;
import javax.annotation.Nullable;

public final class zzcz {
    final Uri zza;
    final String zzb;
    final String zzc;
    final boolean zzd;
    final boolean zze;

    public zzcz(Uri uri0) {
        this(null, uri0, "", "", false, false, false, false, null);
    }

    private zzcz(String s, Uri uri0, String s1, String s2, boolean z, boolean z1, boolean z2, boolean z3, @Nullable zzdg zzdg0) {
        this.zza = uri0;
        this.zzb = "";
        this.zzc = "";
        this.zzd = z;
        this.zze = z2;
    }

    public final zzcz zza() {
        return new zzcz(null, this.zza, this.zzb, this.zzc, this.zzd, false, true, false, null);
    }

    public final zzcz zzb() {
        if(!this.zzb.isEmpty()) {
            throw new IllegalStateException("Cannot set GServices prefix and skip GServices");
        }
        return new zzcz(null, this.zza, this.zzb, this.zzc, true, false, this.zze, false, null);
    }

    public final zzdc zzc(String s, double f) {
        return new zzcx(this, s, 0.0, true);
    }

    public final zzdc zzd(String s, long v) {
        return new zzcv(this, s, v, true);
    }

    public final zzdc zze(String s, boolean z) {
        return new zzcw(this, s, Boolean.valueOf(z), true);
    }

    public final zzdc zzf(String s, Object object0, zzhy zzhy0) {
        return new zzcy(this, "getTokenRefactor__blocked_packages", object0, true, zzhy0);
    }
}

