package com.google.android.gms.measurement.internal;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzcl;
import com.google.android.gms.internal.measurement.zzib;
import com.google.android.gms.internal.measurement.zzos;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.checkerframework.dataflow.qual.Pure;
import org.checkerframework.dataflow.qual.SideEffectFree;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzgd implements zzgy {
    private zzek zzA;
    private boolean zzB;
    private Boolean zzC;
    private long zzD;
    private volatile Boolean zzE;
    private volatile boolean zzF;
    private int zzG;
    private final AtomicInteger zzH;
    protected Boolean zza;
    protected Boolean zzb;
    final long zzc;
    private static volatile zzgd zzd;
    private final Context zze;
    private final String zzf;
    private final String zzg;
    private final String zzh;
    private final boolean zzi;
    private final zzab zzj;
    private final zzag zzk;
    private final zzfi zzl;
    private final zzet zzm;
    private final zzga zzn;
    private final zzkp zzo;
    private final zzlp zzp;
    private final zzeo zzq;
    private final Clock zzr;
    private final zziz zzs;
    private final zzik zzt;
    private final zzd zzu;
    private final zzio zzv;
    private final String zzw;
    private zzem zzx;
    private zzjz zzy;
    private zzao zzz;

    zzgd(zzhi zzhi0) {
        boolean z = false;
        this.zzB = false;
        this.zzH = new AtomicInteger(0);
        Preconditions.checkNotNull(zzhi0);
        Context context0 = zzhi0.zza;
        zzab zzab0 = new zzab(context0);
        this.zzj = zzab0;
        zzed.zza = zzab0;
        this.zze = context0;
        this.zzf = zzhi0.zzb;
        this.zzg = zzhi0.zzc;
        this.zzh = zzhi0.zzd;
        this.zzi = zzhi0.zzh;
        this.zzE = zzhi0.zze;
        this.zzw = zzhi0.zzj;
        this.zzF = true;
        zzcl zzcl0 = zzhi0.zzg;
        if(zzcl0 != null) {
            Bundle bundle0 = zzcl0.zzg;
            if(bundle0 != null) {
                Object object0 = bundle0.get("measurementEnabled");
                if(object0 instanceof Boolean) {
                    this.zza = (Boolean)object0;
                }
                Object object1 = zzcl0.zzg.get("measurementDeactivated");
                if(object1 instanceof Boolean) {
                    this.zzb = (Boolean)object1;
                }
            }
        }
        zzib.zzd(context0);
        Clock clock0 = DefaultClock.getInstance();
        this.zzr = clock0;
        this.zzc = zzhi0.zzi == null ? clock0.currentTimeMillis() : ((long)zzhi0.zzi);
        this.zzk = new zzag(this);
        zzfi zzfi0 = new zzfi(this);
        zzfi0.zzw();
        this.zzl = zzfi0;
        zzet zzet0 = new zzet(this);
        zzet0.zzw();
        this.zzm = zzet0;
        zzlp zzlp0 = new zzlp(this);
        zzlp0.zzw();
        this.zzp = zzlp0;
        this.zzq = new zzeo(new zzhh(zzhi0, this));
        this.zzu = new zzd(this);
        zziz zziz0 = new zziz(this);
        zziz0.zzb();
        this.zzs = zziz0;
        zzik zzik0 = new zzik(this);
        zzik0.zzb();
        this.zzt = zzik0;
        zzkp zzkp0 = new zzkp(this);
        zzkp0.zzb();
        this.zzo = zzkp0;
        zzio zzio0 = new zzio(this);
        zzio0.zzw();
        this.zzv = zzio0;
        zzga zzga0 = new zzga(this);
        zzga0.zzw();
        this.zzn = zzga0;
        if(zzhi0.zzg == null || zzhi0.zzg.zzb == 0L) {
            z = true;
        }
        if(context0.getApplicationContext() instanceof Application) {
            zzik zzik1 = this.zzq();
            if(zzik1.zzt.zze.getApplicationContext() instanceof Application) {
                Application application0 = (Application)zzik1.zzt.zze.getApplicationContext();
                if(zzik1.zza == null) {
                    zzik1.zza = new zzij(zzik1);
                }
                if(z) {
                    application0.unregisterActivityLifecycleCallbacks(zzik1.zza);
                    application0.registerActivityLifecycleCallbacks(zzik1.zza);
                    zzik1.zzt.zzaA().zzj().zza("Registered activity lifecycle callback");
                }
            }
        }
        else {
            this.zzaA().zzk().zza("Application context is not an Application");
        }
        zzga0.zzp(new zzgc(this, zzhi0));
    }

    static void zzA(zzgd zzgd0, zzhi zzhi0) {
        zzgd0.zzaB().zzg();
        zzao zzao0 = new zzao(zzgd0);
        zzao0.zzw();
        zzgd0.zzz = zzao0;
        zzek zzek0 = new zzek(zzgd0, zzhi0.zzf);
        zzek0.zzb();
        zzgd0.zzA = zzek0;
        zzem zzem0 = new zzem(zzgd0);
        zzem0.zzb();
        zzgd0.zzx = zzem0;
        zzjz zzjz0 = new zzjz(zzgd0);
        zzjz0.zzb();
        zzgd0.zzy = zzjz0;
        zzgd0.zzp.zzx();
        zzgd0.zzl.zzx();
        zzgd0.zzA.zzc();
        zzer zzer0 = zzgd0.zzaA().zzi();
        zzgd0.zzk.zzh();
        zzer0.zzb("App measurement initialized, version", 79000L);
        zzgd0.zzaA().zzi().zza("To enable debug logging run: adb shell setprop log.tag.FA VERBOSE");
        String s = zzek0.zzl();
        if(TextUtils.isEmpty(zzgd0.zzf)) {
            if(zzgd0.zzv().zzaf(s)) {
                zzgd0.zzaA().zzi().zza("Faster debug mode event logging enabled. To disable, run:\n  adb shell setprop debug.firebase.analytics.app .none.");
            }
            else {
                zzgd0.zzaA().zzi().zza("To enable faster debug mode event logging run:\n  adb shell setprop debug.firebase.analytics.app " + s);
            }
        }
        zzgd0.zzaA().zzc().zza("Debug-level message logging enabled");
        if(zzgd0.zzG != zzgd0.zzH.get()) {
            zzgd0.zzaA().zzd().zzc("Not all components initialized", zzgd0.zzG, zzgd0.zzH.get());
        }
        zzgd0.zzB = true;
    }

    final void zzB() {
        this.zzH.incrementAndGet();
    }

    final void zzC(String s, int v, Throwable throwable0, byte[] arr_b, Map map0) {
        switch(v) {
            case 200: 
            case 204: {
            label_3:
                if(throwable0 == null) {
                    this.zzm().zzn.zza(true);
                    if(arr_b != null && arr_b.length != 0) {
                        try {
                            JSONObject jSONObject0 = new JSONObject(new String(arr_b));
                            String s1 = jSONObject0.optString("deeplink", "");
                            String s2 = jSONObject0.optString("gclid", "");
                            double f = jSONObject0.optDouble("timestamp", 0.0);
                            if(TextUtils.isEmpty(s1)) {
                                this.zzaA().zzc().zza("Deferred Deep Link is empty.");
                                return;
                            }
                            zzlp zzlp0 = this.zzv();
                            if(TextUtils.isEmpty(s1)) {
                                this.zzaA().zzk().zzc("Deferred Deep Link validation failed. gclid, deep link", s2, s1);
                                return;
                            }
                            List list0 = zzlp0.zzt.zze.getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse(s1)), 0);
                            if(list0 == null || list0.isEmpty()) {
                                this.zzaA().zzk().zzc("Deferred Deep Link validation failed. gclid, deep link", s2, s1);
                                return;
                            }
                            Bundle bundle0 = new Bundle();
                            bundle0.putString("gclid", s2);
                            bundle0.putString("_cis", "ddp");
                            this.zzt.zzG("auto", "_cmp", bundle0);
                            zzlp zzlp1 = this.zzv();
                            if(!TextUtils.isEmpty(s1)) {
                                try {
                                    SharedPreferences.Editor sharedPreferences$Editor0 = zzlp1.zzt.zze.getSharedPreferences("google.analytics.deferred.deeplink.prefs", 0).edit();
                                    sharedPreferences$Editor0.putString("deeplink", s1);
                                    sharedPreferences$Editor0.putLong("timestamp", Double.doubleToRawLongBits(f));
                                    if(sharedPreferences$Editor0.commit()) {
                                        goto label_33;
                                    }
                                }
                                catch(RuntimeException runtimeException0) {
                                    zzlp1.zzt.zzaA().zzd().zzb("Failed to persist Deferred Deep Link. exception", runtimeException0);
                                }
                                return;
                            label_33:
                                Intent intent0 = new Intent("android.google.analytics.action.DEEPLINK_ACTION");
                                zzlp1.zzt.zze.sendBroadcast(intent0);
                                return;
                            }
                        }
                        catch(JSONException jSONException0) {
                            this.zzaA().zzd().zzb("Failed to parse the Deferred Deep Link response. exception", jSONException0);
                        }
                        return;
                    }
                    this.zzaA().zzc().zza("Deferred Deep Link response empty.");
                    return;
                }
                break;
            }
            case 304: {
                v = 304;
                goto label_3;
            }
        }
        this.zzaA().zzk().zzc("Network Request for Deferred Deep Link failed. response, exception", v, throwable0);
    }

    final void zzD() {
        ++this.zzG;
    }

    public final void zzE() {
        this.zzaB().zzg();
        zzgd.zzR(this.zzr());
        String s = this.zzh().zzl();
        Pair pair0 = this.zzm().zzb(s);
        if(this.zzk.zzr() && !((Boolean)pair0.second).booleanValue() && !TextUtils.isEmpty(((CharSequence)pair0.first))) {
            zzio zzio0 = this.zzr();
            zzio0.zzv();
            ConnectivityManager connectivityManager0 = (ConnectivityManager)zzio0.zzt.zze.getSystemService("connectivity");
            NetworkInfo networkInfo0 = null;
            if(connectivityManager0 != null) {
                try {
                    networkInfo0 = connectivityManager0.getActiveNetworkInfo();
                }
                catch(SecurityException unused_ex) {
                }
            }
            if(networkInfo0 != null && networkInfo0.isConnected()) {
                zzlp zzlp0 = this.zzv();
                this.zzh().zzt.zzk.zzh();
                URL uRL0 = zzlp0.zzE(79000L, s, ((String)pair0.first), this.zzm().zzo.zza() - 1L);
                if(uRL0 != null) {
                    zzio zzio1 = this.zzr();
                    zzgb zzgb0 = new zzgb(this);
                    zzio1.zzg();
                    zzio1.zzv();
                    Preconditions.checkNotNull(uRL0);
                    Preconditions.checkNotNull(zzgb0);
                    zzio1.zzt.zzaB().zzo(new zzin(zzio1, s, uRL0, null, null, zzgb0));
                }
                return;
            }
            this.zzaA().zzk().zza("Network is not available for Deferred Deep Link request. Skipping");
            return;
        }
        this.zzaA().zzc().zza("ADID unavailable to retrieve Deferred Deep Link. Skipping");
    }

    final void zzF(boolean z) {
        this.zzE = Boolean.valueOf(z);
    }

    public final void zzG(boolean z) {
        this.zzaB().zzg();
        this.zzF = z;
    }

    protected final void zzH(zzcl zzcl0) {
        zzhb zzhb1;
        this.zzaB().zzg();
        zzhb zzhb0 = this.zzm().zzc();
        int v = zzhb0.zza();
        Boolean boolean0 = this.zzk.zzk("google_analytics_default_allow_ad_storage");
        Boolean boolean1 = this.zzk.zzk("google_analytics_default_allow_analytics_storage");
        if((boolean0 != null || boolean1 != null) && this.zzm().zzl(-10)) {
            zzhb1 = new zzhb(boolean0, boolean1, -10);
        }
        else if(!TextUtils.isEmpty(this.zzh().zzm()) && (v == 0 || v == 30 || (v == 10 || v == 30) || v == 40)) {
            this.zzq().zzR(new zzhb(null, null, -10), this.zzc);
            zzhb1 = null;
        }
        else if(!TextUtils.isEmpty(this.zzh().zzm()) || zzcl0 == null || zzcl0.zzg == null || !this.zzm().zzl(30)) {
            zzhb1 = null;
        }
        else {
            zzhb1 = zzhb.zzb(zzcl0.zzg, 30);
            if(!zzhb1.zzl()) {
                zzhb1 = null;
            }
        }
        if(zzhb1 != null) {
            this.zzq().zzR(zzhb1, this.zzc);
            zzhb0 = zzhb1;
        }
        this.zzq().zzV(zzhb0);
        if(this.zzm().zzc.zza() == 0L) {
            this.zzaA().zzj().zzb("Persisting first open", this.zzc);
            this.zzm().zzc.zzb(this.zzc);
        }
        this.zzq().zzb.zzc();
        if(this.zzM()) {
            if(!TextUtils.isEmpty(this.zzh().zzm()) || !TextUtils.isEmpty(this.zzh().zzk())) {
                zzlp zzlp0 = this.zzv();
                String s = this.zzh().zzm();
                zzfi zzfi0 = this.zzm();
                zzfi0.zzg();
                String s1 = zzfi0.zza().getString("gmp_app_id", null);
                String s2 = this.zzh().zzk();
                zzfi zzfi1 = this.zzm();
                zzfi1.zzg();
                if(zzlp0.zzao(s, s1, s2, zzfi1.zza().getString("admob_app_id", null))) {
                    this.zzaA().zzi().zza("Rechecking which service to use due to a GMP App Id change");
                    zzfi zzfi2 = this.zzm();
                    zzfi2.zzg();
                    Boolean boolean2 = zzfi2.zzd();
                    SharedPreferences.Editor sharedPreferences$Editor0 = zzfi2.zza().edit();
                    sharedPreferences$Editor0.clear();
                    sharedPreferences$Editor0.apply();
                    if(boolean2 != null) {
                        zzfi2.zzh(boolean2);
                    }
                    this.zzi().zzj();
                    this.zzy.zzs();
                    this.zzy.zzr();
                    this.zzm().zzc.zzb(this.zzc);
                    this.zzm().zze.zzb(null);
                }
                zzfi zzfi3 = this.zzm();
                String s3 = this.zzh().zzm();
                zzfi3.zzg();
                SharedPreferences.Editor sharedPreferences$Editor1 = zzfi3.zza().edit();
                sharedPreferences$Editor1.putString("gmp_app_id", s3);
                sharedPreferences$Editor1.apply();
                zzfi zzfi4 = this.zzm();
                String s4 = this.zzh().zzk();
                zzfi4.zzg();
                SharedPreferences.Editor sharedPreferences$Editor2 = zzfi4.zza().edit();
                sharedPreferences$Editor2.putString("admob_app_id", s4);
                sharedPreferences$Editor2.apply();
            }
            if(!this.zzm().zzc().zzj(zzha.zzb)) {
                this.zzm().zze.zzb(null);
            }
            this.zzq().zzO(this.zzm().zze.zza());
            zzos.zzc();
            if(this.zzk.zzs(null, zzeg.zzae)) {
                zzlp zzlp1 = this.zzv();
                try {
                    zzlp1.zzt.zze.getClassLoader().loadClass("com.google.firebase.remoteconfig.FirebaseRemoteConfig");
                }
                catch(ClassNotFoundException unused_ex) {
                    if(!TextUtils.isEmpty(this.zzm().zzp.zza())) {
                        this.zzaA().zzk().zza("Remote config removed with active feature rollouts");
                        this.zzm().zzp.zzb(null);
                    }
                }
            }
            if(!TextUtils.isEmpty(this.zzh().zzm()) || !TextUtils.isEmpty(this.zzh().zzk())) {
                boolean z = this.zzJ();
                if(!this.zzm().zzj() && !this.zzk.zzv()) {
                    this.zzm().zzi(!z);
                }
                if(z) {
                    this.zzq().zzz();
                }
                this.zzu().zza.zza();
                this.zzt().zzu(new AtomicReference());
                this.zzt().zzH(this.zzm().zzs.zza());
            }
        }
        else if(this.zzJ()) {
            if(!this.zzv().zzae("android.permission.INTERNET")) {
                this.zzaA().zzd().zza("App is missing INTERNET permission");
            }
            if(!this.zzv().zzae("android.permission.ACCESS_NETWORK_STATE")) {
                this.zzaA().zzd().zza("App is missing ACCESS_NETWORK_STATE permission");
            }
            if(!Wrappers.packageManager(this.zze).isCallerInstantApp() && !this.zzk.zzx()) {
                if(!zzlp.zzal(this.zze)) {
                    this.zzaA().zzd().zza("AppMeasurementReceiver not registered/enabled");
                }
                if(!zzlp.zzam(this.zze, false)) {
                    this.zzaA().zzd().zza("AppMeasurementService not registered/enabled");
                }
            }
            this.zzaA().zzd().zza("Uploading is not possible. App measurement disabled");
        }
        this.zzm().zzi.zza(true);
    }

    public final boolean zzI() {
        return this.zzE != null && this.zzE.booleanValue();
    }

    public final boolean zzJ() {
        return this.zza() == 0;
    }

    public final boolean zzK() {
        this.zzaB().zzg();
        return this.zzF;
    }

    @Pure
    public final boolean zzL() {
        return TextUtils.isEmpty(this.zzf);
    }

    protected final boolean zzM() {
        if(!this.zzB) {
            throw new IllegalStateException("AppMeasurement is not initialized");
        }
        this.zzaB().zzg();
        if(this.zzC == null || this.zzD == 0L || !this.zzC.booleanValue() && Math.abs(this.zzr.elapsedRealtime() - this.zzD) > 1000L) {
            this.zzD = this.zzr.elapsedRealtime();
            boolean z = true;
            Boolean boolean0 = Boolean.valueOf(this.zzv().zzae("android.permission.INTERNET") && this.zzv().zzae("android.permission.ACCESS_NETWORK_STATE") && (Wrappers.packageManager(this.zze).isCallerInstantApp() || this.zzk.zzx() || zzlp.zzal(this.zze) && zzlp.zzam(this.zze, false)));
            this.zzC = boolean0;
            if(boolean0.booleanValue()) {
                if(!this.zzv().zzY(this.zzh().zzm(), this.zzh().zzk()) && TextUtils.isEmpty(this.zzh().zzk())) {
                    z = false;
                }
                this.zzC = Boolean.valueOf(z);
            }
        }
        return this.zzC.booleanValue();
    }

    @Pure
    public final boolean zzN() {
        return this.zzi;
    }

    static final void zzO() {
        throw new IllegalStateException("Unexpected call on client side");
    }

    private static final void zzP(zzgw zzgw0) {
        if(zzgw0 == null) {
            throw new IllegalStateException("Component not created");
        }
    }

    private static final void zzQ(zzf zzf0) {
        if(zzf0 == null) {
            throw new IllegalStateException("Component not created");
        }
        if(!zzf0.zze()) {
            throw new IllegalStateException("Component not initialized: " + zzf0.getClass());
        }
    }

    private static final void zzR(zzgx zzgx0) {
        if(zzgx0 == null) {
            throw new IllegalStateException("Component not created");
        }
        if(!zzgx0.zzy()) {
            throw new IllegalStateException("Component not initialized: " + zzgx0.getClass());
        }
    }

    public final int zza() {
        this.zzaB().zzg();
        if(this.zzk.zzv()) {
            return 1;
        }
        if(this.zzb != null && this.zzb.booleanValue()) {
            return 2;
        }
        this.zzaB().zzg();
        if(!this.zzF) {
            return 8;
        }
        Boolean boolean0 = this.zzm().zzd();
        if(boolean0 != null) {
            return boolean0.booleanValue() ? 0 : 3;
        }
        Boolean boolean1 = this.zzk.zzk("firebase_analytics_collection_enabled");
        if(boolean1 != null) {
            return boolean1.booleanValue() ? 0 : 4;
        }
        Boolean boolean2 = this.zza;
        if(boolean2 != null) {
            return boolean2.booleanValue() ? 0 : 5;
        }
        return this.zzE != null && !this.zzE.booleanValue() ? 7 : 0;
    }

    @Override  // com.google.android.gms.measurement.internal.zzgy
    @Pure
    public final zzet zzaA() {
        zzgd.zzR(this.zzm);
        return this.zzm;
    }

    @Override  // com.google.android.gms.measurement.internal.zzgy
    @Pure
    public final zzga zzaB() {
        zzgd.zzR(this.zzn);
        return this.zzn;
    }

    @Override  // com.google.android.gms.measurement.internal.zzgy
    @Pure
    public final Context zzaw() {
        return this.zze;
    }

    @Override  // com.google.android.gms.measurement.internal.zzgy
    @Pure
    public final Clock zzax() {
        return this.zzr;
    }

    @Override  // com.google.android.gms.measurement.internal.zzgy
    @Pure
    public final zzab zzay() {
        return this.zzj;
    }

    @Pure
    public final zzd zzd() {
        zzd zzd0 = this.zzu;
        if(zzd0 == null) {
            throw new IllegalStateException("Component not created");
        }
        return zzd0;
    }

    @Pure
    public final zzag zzf() {
        return this.zzk;
    }

    @Pure
    public final zzao zzg() {
        zzgd.zzR(this.zzz);
        return this.zzz;
    }

    @Pure
    public final zzek zzh() {
        zzgd.zzQ(this.zzA);
        return this.zzA;
    }

    @Pure
    public final zzem zzi() {
        zzgd.zzQ(this.zzx);
        return this.zzx;
    }

    @Pure
    public final zzeo zzj() {
        return this.zzq;
    }

    public final zzet zzl() {
        return this.zzm == null || !this.zzm.zzy() ? null : this.zzm;
    }

    @Pure
    public final zzfi zzm() {
        zzgd.zzP(this.zzl);
        return this.zzl;
    }

    @SideEffectFree
    final zzga zzo() {
        return this.zzn;
    }

    public static zzgd zzp(Context context0, zzcl zzcl0, Long long0) {
        if(zzcl0 != null && (zzcl0.zze == null || zzcl0.zzf == null)) {
            zzcl0 = new zzcl(zzcl0.zza, zzcl0.zzb, zzcl0.zzc, zzcl0.zzd, null, null, zzcl0.zzg, null);
        }
        Preconditions.checkNotNull(context0);
        Preconditions.checkNotNull(context0.getApplicationContext());
        if(zzgd.zzd == null) {
            synchronized(zzgd.class) {
                if(zzgd.zzd == null) {
                    zzgd.zzd = new zzgd(new zzhi(context0, zzcl0, long0));
                }
            }
        }
        else if(zzcl0 != null && (zzcl0.zzg != null && zzcl0.zzg.containsKey("dataCollectionDefaultEnabled"))) {
            Preconditions.checkNotNull(zzgd.zzd);
            zzgd zzgd0 = zzgd.zzd;
            zzgd0.zzE = Boolean.valueOf(zzcl0.zzg.getBoolean("dataCollectionDefaultEnabled"));
        }
        Preconditions.checkNotNull(zzgd.zzd);
        return zzgd.zzd;
    }

    @Pure
    public final zzik zzq() {
        zzgd.zzQ(this.zzt);
        return this.zzt;
    }

    @Pure
    public final zzio zzr() {
        zzgd.zzR(this.zzv);
        return this.zzv;
    }

    @Pure
    public final zziz zzs() {
        zzgd.zzQ(this.zzs);
        return this.zzs;
    }

    @Pure
    public final zzjz zzt() {
        zzgd.zzQ(this.zzy);
        return this.zzy;
    }

    @Pure
    public final zzkp zzu() {
        zzgd.zzQ(this.zzo);
        return this.zzo;
    }

    @Pure
    public final zzlp zzv() {
        zzgd.zzP(this.zzp);
        return this.zzp;
    }

    @Pure
    public final String zzw() {
        return this.zzf;
    }

    @Pure
    public final String zzx() {
        return this.zzg;
    }

    @Pure
    public final String zzy() {
        return this.zzh;
    }

    @Pure
    public final String zzz() {
        return this.zzw;
    }
}

