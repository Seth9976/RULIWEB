package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences.Editor;
import com.google.android.gms.common.internal.Preconditions;

public final class zzfh {
    final zzfi zza;
    private final String zzb;
    private boolean zzc;
    private String zzd;

    public zzfh(zzfi zzfi0, String s, String s1) {
        this.zza = zzfi0;
        super();
        Preconditions.checkNotEmpty(s);
        this.zzb = s;
    }

    public final String zza() {
        if(!this.zzc) {
            this.zzc = true;
            this.zzd = this.zza.zza().getString(this.zzb, null);
        }
        return this.zzd;
    }

    public final void zzb(String s) {
        SharedPreferences.Editor sharedPreferences$Editor0 = this.zza.zza().edit();
        sharedPreferences$Editor0.putString(this.zzb, s);
        sharedPreferences$Editor0.apply();
        this.zzd = s;
    }
}

