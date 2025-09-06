package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences.Editor;
import com.google.android.gms.common.internal.Preconditions;

public final class zzfe {
    final zzfi zza;
    private final String zzb;
    private final long zzc;
    private boolean zzd;
    private long zze;

    public zzfe(zzfi zzfi0, String s, long v) {
        this.zza = zzfi0;
        super();
        Preconditions.checkNotEmpty(s);
        this.zzb = s;
        this.zzc = v;
    }

    public final long zza() {
        if(!this.zzd) {
            this.zzd = true;
            this.zze = this.zza.zza().getLong(this.zzb, this.zzc);
        }
        return this.zze;
    }

    public final void zzb(long v) {
        SharedPreferences.Editor sharedPreferences$Editor0 = this.zza.zza().edit();
        sharedPreferences$Editor0.putLong(this.zzb, v);
        sharedPreferences$Editor0.apply();
        this.zze = v;
    }
}

