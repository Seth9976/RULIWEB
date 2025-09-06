package com.google.android.gms.internal.measurement;

import android.net.Uri;
import javax.annotation.Nullable;

public final class zzhy {
    final Uri zza;
    final String zzb;
    final String zzc;
    final boolean zzd;
    final boolean zze;

    public zzhy(Uri uri0) {
        this(null, uri0, "", "", false, false, false, false, null);
    }

    private zzhy(String s, Uri uri0, String s1, String s2, boolean z, boolean z1, boolean z2, boolean z3, @Nullable zzig zzig0) {
        this.zza = uri0;
        this.zzb = "";
        this.zzc = "";
        this.zzd = z;
        this.zze = z2;
    }

    public final zzhy zza() {
        return new zzhy(null, this.zza, this.zzb, this.zzc, this.zzd, false, true, false, null);
    }

    public final zzhy zzb() {
        if(!this.zzb.isEmpty()) {
            throw new IllegalStateException("Cannot set GServices prefix and skip GServices");
        }
        return new zzhy(null, this.zza, this.zzb, this.zzc, true, false, this.zze, false, null);
    }

    public final zzib zzc(String s, double f) {
        return new zzhw(this, "measurement.test.double_flag", -3.0, true);
    }

    public final zzib zzd(String s, long v) {
        return new zzhu(this, s, v, true);
    }

    public final zzib zze(String s, String s1) {
        return new zzhx(this, s, s1, true);
    }

    public final zzib zzf(String s, boolean z) {
        return new zzhv(this, s, Boolean.valueOf(z), true);
    }
}

