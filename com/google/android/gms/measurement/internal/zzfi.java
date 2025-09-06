package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences;
import android.util.Pair;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzov;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull.List;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

final class zzfi extends zzgx {
    static final Pair zza;
    public zzfg zzb;
    public final zzfe zzc;
    public final zzfe zzd;
    public final zzfh zze;
    public final zzfe zzf;
    public final zzfc zzg;
    public final zzfh zzh;
    public final zzfc zzi;
    public final zzfe zzj;
    public final zzfe zzk;
    public boolean zzl;
    public final zzfc zzm;
    public final zzfc zzn;
    public final zzfe zzo;
    public final zzfh zzp;
    public final zzfh zzq;
    public final zzfe zzr;
    public final zzfd zzs;
    private SharedPreferences zzu;
    private String zzv;
    private boolean zzw;
    private long zzx;

    static {
        zzfi.zza = new Pair("", 0L);
    }

    zzfi(zzgd zzgd0) {
        super(zzgd0);
        this.zzf = new zzfe(this, "session_timeout", 1800000L);
        this.zzg = new zzfc(this, "start_new_session", true);
        this.zzj = new zzfe(this, "last_pause_time", 0L);
        this.zzk = new zzfe(this, "session_id", 0L);
        this.zzh = new zzfh(this, "non_personalized_ads", null);
        this.zzi = new zzfc(this, "allow_remote_dynamite", false);
        this.zzc = new zzfe(this, "first_open_time", 0L);
        this.zzd = new zzfe(this, "app_install_time", 0L);
        this.zze = new zzfh(this, "app_instance_id", null);
        this.zzm = new zzfc(this, "app_backgrounded", false);
        this.zzn = new zzfc(this, "deep_link_retrieval_complete", false);
        this.zzo = new zzfe(this, "deep_link_retrieval_attempts", 0L);
        this.zzp = new zzfh(this, "firebase_feature_rollouts", null);
        this.zzq = new zzfh(this, "deferred_attribution_cache", null);
        this.zzr = new zzfe(this, "deferred_attribution_cache_timestamp", 0L);
        this.zzs = new zzfd(this, "default_event_parameters", null);
    }

    protected final SharedPreferences zza() {
        this.zzg();
        this.zzv();
        Preconditions.checkNotNull(this.zzu);
        return this.zzu;
    }

    @Override  // com.google.android.gms.measurement.internal.zzgx
    @EnsuresNonNull.List({@EnsuresNonNull({"this.preferences"}), @EnsuresNonNull({"this.monitoringSample"})})
    protected final void zzaC() {
        SharedPreferences sharedPreferences0 = this.zzt.zzaw().getSharedPreferences("com.google.android.gms.measurement.prefs", 0);
        this.zzu = sharedPreferences0;
        boolean z = sharedPreferences0.getBoolean("has_been_opened", false);
        this.zzl = z;
        if(!z) {
            SharedPreferences.Editor sharedPreferences$Editor0 = this.zzu.edit();
            sharedPreferences$Editor0.putBoolean("has_been_opened", true);
            sharedPreferences$Editor0.apply();
        }
        this.zzb = new zzfg(this, "health_monitor", Math.max(0L, ((long)(((Long)zzeg.zzc.zza(null))))), null);
    }

    final Pair zzb(String s) {
        this.zzg();
        zzov.zzc();
        if(this.zzt.zzf().zzs(null, zzeg.zzaI) && !this.zzc().zzj(zzha.zza)) {
            return new Pair("", Boolean.FALSE);
        }
        long v = this.zzt.zzax().elapsedRealtime();
        String s1 = this.zzv;
        if(s1 != null && v < this.zzx) {
            return new Pair(s1, Boolean.valueOf(this.zzw));
        }
        this.zzx = v + this.zzt.zzf().zzi(s, zzeg.zza);
        try {
            Info advertisingIdClient$Info0 = AdvertisingIdClient.getAdvertisingIdInfo(this.zzt.zzaw());
            this.zzv = "";
            String s2 = advertisingIdClient$Info0.getId();
            if(s2 != null) {
                this.zzv = s2;
            }
            this.zzw = advertisingIdClient$Info0.isLimitAdTrackingEnabled();
            return new Pair(this.zzv, Boolean.valueOf(this.zzw));
        }
        catch(Exception exception0) {
            this.zzt.zzaA().zzc().zzb("Unable to get advertising id", exception0);
            this.zzv = "";
            return new Pair("", Boolean.valueOf(this.zzw));
        }
    }

    final zzhb zzc() {
        this.zzg();
        return zzhb.zzc(this.zza().getString("consent_settings", "G1"), this.zza().getInt("consent_source", 100));
    }

    final Boolean zzd() {
        this.zzg();
        return this.zza().contains("measurement_enabled") ? Boolean.valueOf(this.zza().getBoolean("measurement_enabled", true)) : null;
    }

    @Override  // com.google.android.gms.measurement.internal.zzgx
    protected final boolean zzf() {
        return true;
    }

    final void zzh(Boolean boolean0) {
        this.zzg();
        SharedPreferences.Editor sharedPreferences$Editor0 = this.zza().edit();
        if(boolean0 == null) {
            sharedPreferences$Editor0.remove("measurement_enabled");
        }
        else {
            sharedPreferences$Editor0.putBoolean("measurement_enabled", boolean0.booleanValue());
        }
        sharedPreferences$Editor0.apply();
    }

    final void zzi(boolean z) {
        this.zzg();
        this.zzt.zzaA().zzj().zzb("App measurement setting deferred collection", Boolean.valueOf(z));
        SharedPreferences.Editor sharedPreferences$Editor0 = this.zza().edit();
        sharedPreferences$Editor0.putBoolean("deferred_analytics_collection", z);
        sharedPreferences$Editor0.apply();
    }

    final boolean zzj() {
        return this.zzu == null ? false : this.zzu.contains("deferred_analytics_collection");
    }

    final boolean zzk(long v) {
        return v - this.zzf.zza() > this.zzj.zza();
    }

    final boolean zzl(int v) {
        return zzhb.zzk(v, this.zza().getInt("consent_source", 100));
    }
}

