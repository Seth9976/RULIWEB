package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences.Editor;
import com.google.android.gms.common.internal.Preconditions;

public final class zzfc {
    final zzfi zza;
    private final String zzb;
    private final boolean zzc;
    private boolean zzd;
    private boolean zze;

    public zzfc(zzfi zzfi0, String s, boolean z) {
        this.zza = zzfi0;
        super();
        Preconditions.checkNotEmpty(s);
        this.zzb = s;
        this.zzc = z;
    }

    public final void zza(boolean z) {
        SharedPreferences.Editor sharedPreferences$Editor0 = this.zza.zza().edit();
        sharedPreferences$Editor0.putBoolean(this.zzb, z);
        sharedPreferences$Editor0.apply();
        this.zze = z;
    }

    public final boolean zzb() {
        if(!this.zzd) {
            this.zzd = true;
            this.zze = this.zza.zza().getBoolean(this.zzb, this.zzc);
        }
        return this.zze;
    }
}

