package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.wrappers.InstantApps;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzpz;
import com.google.android.gms.internal.measurement.zzqu;
import com.google.android.gms.internal.measurement.zzrj;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.List;
import java.util.Locale;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

public final class zzek extends zzf {
    private String zza;
    private String zzb;
    private int zzc;
    private String zzd;
    private String zze;
    private long zzf;
    private final long zzg;
    private List zzh;
    private String zzi;
    private int zzj;
    private String zzk;
    private String zzl;
    private String zzm;
    private long zzn;
    private String zzo;

    zzek(zzgd zzgd0, long v) {
        super(zzgd0);
        this.zzn = 0L;
        this.zzo = null;
        this.zzg = v;
    }

    @Override  // com.google.android.gms.measurement.internal.zzf
    @EnsuresNonNull({"appId", "appStore", "appName", "gmpAppId", "gaAppId"})
    protected final void zzd() {
        String s3;
        PackageManager packageManager0 = this.zzt.zzaw().getPackageManager();
        int v = 0x80000000;
        String s = "";
        String s1 = "Unknown";
        String s2 = "unknown";
        if(packageManager0 == null) {
            this.zzt.zzaA().zzd().zzb("PackageManager is null, app identity information might be inaccurate. appId", zzet.zzn("com.ruliweb.www.ruliapp"));
            goto label_23;
        }
        else {
            try {
                s2 = packageManager0.getInstallerPackageName("com.ruliweb.www.ruliapp");
            }
            catch(IllegalArgumentException unused_ex) {
                this.zzt.zzaA().zzd().zzb("Error retrieving app installer package name. appId", zzet.zzn("com.ruliweb.www.ruliapp"));
            }
            if(s2 == null) {
                s2 = "manual_install";
            }
            else if("com.android.vending".equals(s2)) {
                s2 = "";
            }
            try {
                PackageInfo packageInfo0 = packageManager0.getPackageInfo("com.ruliweb.www.ruliapp", 0);
                if(packageInfo0 == null) {
                label_23:
                    s3 = "Unknown";
                }
                else {
                    CharSequence charSequence0 = packageManager0.getApplicationLabel(packageInfo0.applicationInfo);
                    s3 = TextUtils.isEmpty(charSequence0) ? "Unknown" : charSequence0.toString();
                    s1 = packageInfo0.versionName;
                    v = packageInfo0.versionCode;
                }
            }
            catch(PackageManager.NameNotFoundException unused_ex) {
                this.zzt.zzaA().zzd().zzc("Error retrieving package info. appId, appName", zzet.zzn("com.ruliweb.www.ruliapp"), "Unknown");
                s3 = "Unknown";
            }
        }
        this.zza = "com.ruliweb.www.ruliapp";
        this.zzd = s2;
        this.zzb = s1;
        this.zzc = v;
        this.zze = s3;
        this.zzf = 0L;
        boolean z = !TextUtils.isEmpty(this.zzt.zzw()) && "am".equals(this.zzt.zzx());
        int v1 = this.zzt.zza();
        switch(v1) {
            case 0: {
                this.zzt.zzaA().zzj().zza("App measurement collection enabled");
                break;
            }
            case 1: {
                this.zzt.zzaA().zzi().zza("App measurement deactivated via the manifest");
                break;
            }
            case 2: {
                this.zzt.zzaA().zzj().zza("App measurement deactivated via the init parameters");
                break;
            }
            case 3: {
                this.zzt.zzaA().zzi().zza("App measurement disabled by setAnalyticsCollectionEnabled(false)");
                break;
            }
            case 4: {
                this.zzt.zzaA().zzi().zza("App measurement disabled via the manifest");
                break;
            }
            case 5: {
                this.zzt.zzaA().zzj().zza("App measurement disabled via the init parameters");
                break;
            }
            case 6: {
                this.zzt.zzaA().zzl().zza("App measurement deactivated via resources. This method is being deprecated. Please refer to https://firebase.google.com/support/guides/disable-analytics");
                break;
            }
            case 7: {
                this.zzt.zzaA().zzi().zza("App measurement disabled via the global data collection setting");
                break;
            }
            default: {
                this.zzt.zzaA().zzi().zza("App measurement disabled due to denied storage consent");
            }
        }
        this.zzk = "";
        this.zzl = "";
        if(z) {
            this.zzl = this.zzt.zzw();
        }
        try {
            String s4 = zziq.zzc(this.zzt.zzaw(), "google_app_id", this.zzt.zzz());
            if(!TextUtils.isEmpty(s4)) {
                s = s4;
            }
            this.zzk = s;
            if(!TextUtils.isEmpty(s4)) {
                Context context0 = this.zzt.zzaw();
                String s5 = this.zzt.zzz();
                Preconditions.checkNotNull(context0);
                Resources resources0 = context0.getResources();
                if(TextUtils.isEmpty(s5)) {
                    s5 = zzfv.zza(context0);
                }
                this.zzl = zzfv.zzb("admob_app_id", resources0, s5);
            }
            if(v1 == 0) {
                this.zzt.zzaA().zzj().zzc("App measurement enabled for app package, google app id", this.zza, (TextUtils.isEmpty(this.zzk) ? this.zzl : this.zzk));
            }
        }
        catch(IllegalStateException illegalStateException0) {
            this.zzt.zzaA().zzd().zzc("Fetching Google App Id failed with exception. appId", zzet.zzn("com.ruliweb.www.ruliapp"), illegalStateException0);
        }
        this.zzh = null;
        List list0 = this.zzt.zzf().zzp("analytics.safelisted_events");
        if(list0 == null) {
            this.zzh = null;
        }
        else if(list0.isEmpty()) {
            this.zzt.zzaA().zzl().zza("Safelisted event list is empty. Ignoring");
        }
        else {
            for(Object object0: list0) {
                if(this.zzt.zzv().zzac("safelisted event", ((String)object0))) {
                    continue;
                }
                goto label_88;
            }
            this.zzh = list0;
        }
    label_88:
        if(packageManager0 != null) {
            this.zzj = InstantApps.isInstantApp(this.zzt.zzaw());
            return;
        }
        this.zzj = 0;
    }

    @Override  // com.google.android.gms.measurement.internal.zzf
    protected final boolean zzf() {
        return true;
    }

    final int zzh() {
        this.zza();
        return this.zzj;
    }

    final int zzi() {
        this.zza();
        return this.zzc;
    }

    final zzq zzj(String s) {
        String s9;
        long v12;
        Boolean boolean2;
        int v11;
        int v5;
        Object object0;
        Class class0;
        this.zzg();
        String s1 = this.zzl();
        String s2 = this.zzm();
        this.zza();
        String s3 = this.zzb;
        this.zza();
        long v = (long)this.zzc;
        this.zza();
        Preconditions.checkNotNull(this.zzd);
        String s4 = this.zzd;
        this.zzt.zzf().zzh();
        this.zza();
        this.zzg();
        long v1 = this.zzf;
        if(v1 == 0L) {
            zzlp zzlp0 = this.zzt.zzv();
            Context context0 = this.zzt.zzaw();
            zzlp0.zzg();
            Preconditions.checkNotNull(context0);
            new String("com.ruliweb.www.ruliapp");
            PackageManager packageManager0 = context0.getPackageManager();
            MessageDigest messageDigest0 = zzlp.zzF();
            long v2 = -1L;
            if(messageDigest0 == null) {
                zzlp0.zzt.zzaA().zzd().zza("Could not get MD5 instance");
                v1 = -1L;
            }
            else if(packageManager0 == null) {
                v1 = 0L;
            }
            else {
                try {
                    if(zzlp0.zzah(context0, "com.ruliweb.www.ruliapp")) {
                        v2 = 0L;
                    }
                    else {
                        PackageInfo packageInfo0 = Wrappers.packageManager(context0).getPackageInfo("com.ruliweb.www.ruliapp", 0x40);
                        if(packageInfo0.signatures == null || packageInfo0.signatures.length <= 0) {
                            zzlp0.zzt.zzaA().zzk().zza("Could not get signatures");
                        }
                        else {
                            v2 = zzlp.zzp(messageDigest0.digest(packageInfo0.signatures[0].toByteArray()));
                        }
                    }
                }
                catch(PackageManager.NameNotFoundException packageManager$NameNotFoundException0) {
                    zzlp0.zzt.zzaA().zzd().zzb("Package name not found", packageManager$NameNotFoundException0);
                    v1 = 0L;
                    goto label_44;
                }
                v1 = v2;
            }
        label_44:
            this.zzf = v1;
        }
        boolean z = this.zzt.zzJ();
        int v3 = !this.zzt.zzm().zzl;
        this.zzg();
        long v4 = 0L;
        String s5 = null;
        if(this.zzt.zzJ()) {
            zzrj.zzc();
            if(this.zzt.zzf().zzs(null, zzeg.zzac)) {
                this.zzt.zzaA().zzj().zza("Disabled IID for tests.");
                v5 = v3;
            }
            else {
                try {
                    class0 = this.zzt.zzaw().getClassLoader().loadClass("com.google.firebase.analytics.FirebaseAnalytics");
                }
                catch(ClassNotFoundException unused_ex) {
                    v5 = v3;
                    goto label_74;
                }
                if(class0 == null) {
                    v5 = v3;
                }
                else {
                    try {
                        v5 = v3;
                        object0 = class0.getDeclaredMethod("getInstance", Context.class).invoke(null, this.zzt.zzaw());
                    }
                    catch(Exception unused_ex) {
                        this.zzt.zzaA().zzm().zza("Failed to obtain Firebase Analytics instance");
                        goto label_74;
                    }
                    if(object0 != null) {
                        try {
                            s5 = (String)class0.getDeclaredMethod("getFirebaseInstanceId", null).invoke(object0, null);
                        }
                        catch(Exception unused_ex) {
                            this.zzt.zzaA().zzl().zza("Failed to retrieve Firebase Instance Id");
                        }
                    }
                }
            }
        }
        else {
            v5 = v3;
        }
    label_74:
        zzgd zzgd0 = this.zzt;
        long v6 = zzgd0.zzm().zzc.zza();
        long v7 = v6 == 0L ? zzgd0.zzc : Math.min(zzgd0.zzc, v6);
        this.zza();
        int v8 = this.zzj;
        boolean z1 = this.zzt.zzf().zzr();
        zzfi zzfi0 = this.zzt.zzm();
        zzfi0.zzg();
        boolean z2 = zzfi0.zza().getBoolean("deferred_analytics_collection", false);
        this.zza();
        String s6 = this.zzl;
        Boolean boolean0 = this.zzt.zzf().zzk("google_analytics_default_allow_ad_personalization_signals");
        Boolean boolean1 = boolean0 == null ? null : Boolean.valueOf(!boolean0.booleanValue());
        long v9 = this.zzg;
        List list0 = this.zzh;
        String s7 = this.zzt.zzm().zzc().zzi();
        if(this.zzi == null) {
            this.zzi = this.zzt.zzv().zzC();
        }
        String s8 = this.zzi;
        zzqu.zzc();
        if(this.zzt.zzf().zzs(null, zzeg.zzan)) {
            this.zzg();
            if(this.zzn != 0L) {
                long v10 = this.zzt.zzax().currentTimeMillis() - this.zzn;
                if(this.zzm != null && v10 > 86400000L && this.zzo == null) {
                    this.zzo();
                }
            }
            if(this.zzm == null) {
                this.zzo();
            }
            v11 = v8;
            boolean2 = boolean1;
            v12 = v9;
            s9 = this.zzm;
        }
        else {
            v11 = v8;
            boolean2 = boolean1;
            v12 = v9;
            s9 = null;
        }
        Boolean boolean3 = this.zzt.zzf().zzk("google_analytics_sgtm_upload_enabled");
        boolean z3 = boolean3 == null ? false : boolean3.booleanValue();
        zzpz.zzc();
        if(this.zzt.zzf().zzs(null, zzeg.zzaD)) {
            int v13 = 0;
            zzlp zzlp1 = this.zzt.zzv();
            String s10 = this.zzl();
            if(zzlp1.zzt.zzaw().getPackageManager() != null) {
                try {
                    if(Wrappers.packageManager(zzlp1.zzt.zzaw()).getApplicationInfo(s10, 0) != null) {
                        v13 = 35;
                    }
                }
                catch(PackageManager.NameNotFoundException unused_ex) {
                    zzlp1.zzt.zzaA().zzi().zzb("PackageManager failed to find running app: app_id", s10);
                }
                v4 = (long)v13;
            }
        }
        return new zzq(s1, s2, s3, v, s4, 79000L, v1, s, z, ((boolean)v5), s5, 0L, v7, v11, z1, z2, s6, boolean2, v12, list0, null, s7, s8, s9, z3, v4);
    }

    final String zzk() {
        this.zza();
        return this.zzl;
    }

    final String zzl() {
        this.zza();
        Preconditions.checkNotNull(this.zza);
        return this.zza;
    }

    final String zzm() {
        this.zzg();
        this.zza();
        Preconditions.checkNotNull(this.zzk);
        return this.zzk;
    }

    final List zzn() {
        return this.zzh;
    }

    final void zzo() {
        String s;
        this.zzg();
        if(this.zzt.zzm().zzc().zzj(zzha.zzb)) {
            byte[] arr_b = new byte[16];
            this.zzt.zzv().zzG().nextBytes(arr_b);
            s = String.format(Locale.US, "%032x", new BigInteger(1, arr_b));
        }
        else {
            this.zzt.zzaA().zzc().zza("Analytics Storage consent is not granted");
            s = null;
        }
        this.zzt.zzaA().zzc().zza(String.format("Resetting session stitching token to %s", (s == null ? "null" : "not null")));
        this.zzm = s;
        this.zzn = this.zzt.zzax().currentTimeMillis();
    }

    final boolean zzp(String s) {
        boolean z = this.zzo != null && !this.zzo.equals(s);
        this.zzo = s;
        return z;
    }
}

