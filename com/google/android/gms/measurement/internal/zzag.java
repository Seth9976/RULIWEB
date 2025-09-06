package com.google.android.gms.measurement.internal;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources.NotFoundException;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.ProcessUtils;
import com.google.android.gms.common.wrappers.Wrappers;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

public final class zzag extends zzgw {
    private Boolean zza;
    private zzaf zzb;
    private Boolean zzc;

    zzag(zzgd zzgd0) {
        super(zzgd0);
        this.zzb = (String s, String s1) -> null;
    }

    public static final long zzA() {
        return (long)(((Long)zzeg.zzD.zza(null)));
    }

    private final String zzB(String s, String s1) {
        try {
            String s2 = (String)Class.forName("android.os.SystemProperties").getMethod("get", String.class, String.class).invoke(null, s, "");
            Preconditions.checkNotNull(s2);
            return s2;
        }
        catch(ClassNotFoundException classNotFoundException0) {
            this.zzt.zzaA().zzd().zzb("Could not find SystemProperties class", classNotFoundException0);
            return "";
        }
        catch(NoSuchMethodException noSuchMethodException0) {
            this.zzt.zzaA().zzd().zzb("Could not find SystemProperties.get() method", noSuchMethodException0);
            return "";
        }
        catch(IllegalAccessException illegalAccessException0) {
            this.zzt.zzaA().zzd().zzb("Could not access SystemProperties.get()", illegalAccessException0);
            return "";
        }
        catch(InvocationTargetException invocationTargetException0) {
            this.zzt.zzaA().zzd().zzb("SystemProperties.get() threw an exception", invocationTargetException0);
            return "";
        }
    }

    public final double zza(String s, zzef zzef0) {
        if(s == null) {
            return (double)(((Double)zzef0.zza(null)));
        }
        String s1 = this.zzb.zza(s, zzef0.zzb());
        if(TextUtils.isEmpty(s1)) {
            return (double)(((Double)zzef0.zza(null)));
        }
        try {
            return (double)(((Double)zzef0.zza(Double.parseDouble(s1))));
        }
        catch(NumberFormatException unused_ex) {
            return (double)(((Double)zzef0.zza(null)));
        }
    }

    final int zzb(String s) {
        return this.zzf(s, zzeg.zzH, 500, 2000);
    }

    // 去混淆评级： 低(20)
    public final int zzc() {
        return this.zzt.zzv().zzai(201500000, true) ? 100 : 25;
    }

    public final int zzd(String s) {
        return this.zzf(s, zzeg.zzI, 25, 100);
    }

    public final int zze(String s, zzef zzef0) {
        if(s == null) {
            return (int)(((Integer)zzef0.zza(null)));
        }
        String s1 = this.zzb.zza(s, zzef0.zzb());
        if(TextUtils.isEmpty(s1)) {
            return (int)(((Integer)zzef0.zza(null)));
        }
        try {
            return (int)(((Integer)zzef0.zza(Integer.parseInt(s1))));
        }
        catch(NumberFormatException unused_ex) {
            return (int)(((Integer)zzef0.zza(null)));
        }
    }

    public final int zzf(String s, zzef zzef0, int v, int v1) {
        return Math.max(Math.min(this.zze(s, zzef0), v1), v);
    }

    public final long zzh() {
        return 79000L;
    }

    public final long zzi(String s, zzef zzef0) {
        if(s == null) {
            return (long)(((Long)zzef0.zza(null)));
        }
        String s1 = this.zzb.zza(s, zzef0.zzb());
        if(TextUtils.isEmpty(s1)) {
            return (long)(((Long)zzef0.zza(null)));
        }
        try {
            return (long)(((Long)zzef0.zza(Long.parseLong(s1))));
        }
        catch(NumberFormatException unused_ex) {
            return (long)(((Long)zzef0.zza(null)));
        }
    }

    final Bundle zzj() {
        try {
            if(this.zzt.zzaw().getPackageManager() == null) {
                this.zzt.zzaA().zzd().zza("Failed to load metadata: PackageManager is null");
                return null;
            }
            ApplicationInfo applicationInfo0 = Wrappers.packageManager(this.zzt.zzaw()).getApplicationInfo("com.ruliweb.www.ruliapp", 0x80);
            if(applicationInfo0 == null) {
                this.zzt.zzaA().zzd().zza("Failed to load metadata: ApplicationInfo is null");
                return null;
            }
            return applicationInfo0.metaData;
        }
        catch(PackageManager.NameNotFoundException packageManager$NameNotFoundException0) {
            this.zzt.zzaA().zzd().zzb("Failed to load metadata: Package name not found", packageManager$NameNotFoundException0);
            return null;
        }
    }

    final Boolean zzk(String s) {
        Preconditions.checkNotEmpty(s);
        Bundle bundle0 = this.zzj();
        if(bundle0 == null) {
            this.zzt.zzaA().zzd().zza("Failed to load metadata: Metadata bundle is null");
            return null;
        }
        return bundle0.containsKey(s) ? Boolean.valueOf(bundle0.getBoolean(s)) : null;
    }

    public final String zzl() {
        return this.zzB("debug.firebase.analytics.app", "");
    }

    public final String zzm() {
        return this.zzB("debug.deferred.deeplink", "");
    }

    final String zzn() {
        return "FA";
    }

    public final String zzo(String s, zzef zzef0) {
        return s == null ? ((String)zzef0.zza(null)) : ((String)zzef0.zza(this.zzb.zza(s, zzef0.zzb())));
    }

    final List zzp(String s) {
        Integer integer0;
        new String("analytics.safelisted_events");
        Bundle bundle0 = this.zzj();
        if(bundle0 == null) {
            this.zzt.zzaA().zzd().zza("Failed to load metadata: Metadata bundle is null");
            integer0 = null;
        }
        else {
            integer0 = bundle0.containsKey("analytics.safelisted_events") ? bundle0.getInt("analytics.safelisted_events") : null;
        }
        if(integer0 != null) {
            try {
                String[] arr_s = this.zzt.zzaw().getResources().getStringArray(((int)integer0));
                return arr_s == null ? null : Arrays.asList(arr_s);
            }
            catch(Resources.NotFoundException resources$NotFoundException0) {
                this.zzt.zzaA().zzd().zzb("Failed to load string array from metadata: resource not found", resources$NotFoundException0);
            }
        }
        return null;
    }

    final void zzq(zzaf zzaf0) {
        this.zzb = zzaf0;
    }

    public final boolean zzr() {
        Boolean boolean0 = this.zzk("google_analytics_adid_collection_enabled");
        return boolean0 == null || boolean0.booleanValue();
    }

    public final boolean zzs(String s, zzef zzef0) {
        if(s == null) {
            return ((Boolean)zzef0.zza(null)).booleanValue();
        }
        String s1 = this.zzb.zza(s, zzef0.zzb());
        return TextUtils.isEmpty(s1) ? ((Boolean)zzef0.zza(null)).booleanValue() : ((Boolean)zzef0.zza(Boolean.valueOf("1".equals(s1)))).booleanValue();
    }

    public final boolean zzt(String s) {
        return "1".equals(this.zzb.zza(s, "gaia_collection_enabled"));
    }

    public final boolean zzu() {
        Boolean boolean0 = this.zzk("google_analytics_automatic_screen_reporting_enabled");
        return boolean0 == null || boolean0.booleanValue();
    }

    public final boolean zzv() {
        Boolean boolean0 = this.zzk("firebase_analytics_collection_deactivated");
        return boolean0 != null && boolean0.booleanValue();
    }

    public final boolean zzw(String s) {
        return "1".equals(this.zzb.zza(s, "measurement.event_sampling_enabled"));
    }

    final boolean zzx() {
        if(this.zza == null) {
            Boolean boolean0 = this.zzk("app_measurement_lite");
            this.zza = boolean0;
            if(boolean0 == null) {
                this.zza = Boolean.FALSE;
            }
        }
        return this.zza.booleanValue() || !this.zzt.zzN();
    }

    @EnsuresNonNull({"this.isMainProcess"})
    public final boolean zzy() {
        if(this.zzc == null) {
            synchronized(this) {
                if(this.zzc == null) {
                    boolean z = false;
                    ApplicationInfo applicationInfo0 = this.zzt.zzaw().getApplicationInfo();
                    String s = ProcessUtils.getMyProcessName();
                    if(applicationInfo0 != null) {
                        if(applicationInfo0.processName != null && applicationInfo0.processName.equals(s)) {
                            z = true;
                        }
                        this.zzc = Boolean.valueOf(z);
                    }
                    if(this.zzc == null) {
                        this.zzc = Boolean.TRUE;
                        this.zzt.zzaA().zzd().zza("My process not in the list of running processes");
                    }
                }
            }
            return this.zzc.booleanValue();
        }
        return this.zzc.booleanValue();
    }

    public static final long zzz() {
        return (long)(((Long)zzeg.zzd.zza(null)));
    }
}

