package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences.Editor;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;

public final class zzfg {
    final String zza;
    final zzfi zzb;
    private final String zzc;
    private final String zzd;
    private final long zze;

    zzfg(zzfi zzfi0, String s, long v, zzff zzff0) {
        this.zzb = zzfi0;
        super();
        new String("health_monitor");
        Preconditions.checkArgument(v > 0L);
        this.zza = "health_monitor:start";
        this.zzc = "health_monitor:count";
        this.zzd = "health_monitor:value";
        this.zze = v;
    }

    public final Pair zza() {
        long v1;
        this.zzb.zzg();
        this.zzb.zzg();
        long v = this.zzc();
        if(v == 0L) {
            this.zzd();
            v1 = 0L;
        }
        else {
            v1 = Math.abs(v - this.zzb.zzt.zzax().currentTimeMillis());
        }
        long v2 = this.zze;
        if(v1 < v2) {
            return null;
        }
        if(v1 > v2 + v2) {
            this.zzd();
            return null;
        }
        String s = this.zzb.zza().getString(this.zzd, null);
        long v3 = this.zzb.zza().getLong(this.zzc, 0L);
        this.zzd();
        return s == null || v3 <= 0L ? zzfi.zza : new Pair(s, v3);
    }

    public final void zzb(String s, long v) {
        this.zzb.zzg();
        if(this.zzc() == 0L) {
            this.zzd();
        }
        if(s == null) {
            s = "";
        }
        long v1 = this.zzb.zza().getLong(this.zzc, 0L);
        if(v1 <= 0L) {
            SharedPreferences.Editor sharedPreferences$Editor0 = this.zzb.zza().edit();
            sharedPreferences$Editor0.putString(this.zzd, s);
            sharedPreferences$Editor0.putLong(this.zzc, 1L);
            sharedPreferences$Editor0.apply();
            return;
        }
        long v2 = this.zzb.zzt.zzv().zzG().nextLong();
        SharedPreferences.Editor sharedPreferences$Editor1 = this.zzb.zza().edit();
        if((v2 & 0x7FFFFFFFFFFFFFFFL) < 0x7FFFFFFFFFFFFFFFL / (v1 + 1L)) {
            sharedPreferences$Editor1.putString(this.zzd, s);
        }
        sharedPreferences$Editor1.putLong(this.zzc, v1 + 1L);
        sharedPreferences$Editor1.apply();
    }

    private final long zzc() {
        return this.zzb.zza().getLong(this.zza, 0L);
    }

    private final void zzd() {
        this.zzb.zzg();
        long v = this.zzb.zzt.zzax().currentTimeMillis();
        SharedPreferences.Editor sharedPreferences$Editor0 = this.zzb.zza().edit();
        sharedPreferences$Editor0.remove(this.zzc);
        sharedPreferences$Editor0.remove(this.zzd);
        sharedPreferences$Editor0.putLong(this.zza, v);
        sharedPreferences$Editor0.apply();
    }
}

