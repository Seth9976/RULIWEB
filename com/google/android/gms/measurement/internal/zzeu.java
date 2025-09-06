package com.google.android.gms.measurement.internal;

import android.os.Bundle;

public final class zzeu {
    public final String zza;
    public final String zzb;
    public final long zzc;
    public final Bundle zzd;

    public zzeu(String s, String s1, Bundle bundle0, long v) {
        this.zza = s;
        this.zzb = s1;
        this.zzd = bundle0;
        this.zzc = v;
    }

    @Override
    public final String toString() {
        return "origin=" + this.zzb + ",name=" + this.zza + ",params=" + this.zzd.toString();
    }

    public final zzau zza() {
        zzas zzas0 = new zzas(new Bundle(this.zzd));
        return new zzau(this.zza, zzas0, this.zzb, this.zzc);
    }

    public static zzeu zzb(zzau zzau0) {
        Bundle bundle0 = zzau0.zzb.zzc();
        return new zzeu(zzau0.zza, zzau0.zzc, bundle0, zzau0.zzd);
    }
}

