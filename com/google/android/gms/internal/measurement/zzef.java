package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.os.RemoteException;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.dynamite.DynamiteModule.LoadingException;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.gms.measurement.internal.zzfv;
import com.google.android.gms.measurement.internal.zzhf;
import com.google.android.gms.measurement.internal.zzhg;
import com.google.android.gms.measurement.internal.zziq;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class zzef {
    protected final Clock zza;
    protected final ExecutorService zzb;
    private static volatile zzef zzc;
    private final String zzd;
    private final AppMeasurementSdk zze;
    private final List zzf;
    private int zzg;
    private boolean zzh;
    private final String zzi;
    private volatile zzcc zzj;

    protected zzef(Context context0, String s, String s1, String s2, Bundle bundle0) {
        this.zzd = s == null || !this.zzW(s1, s2) ? "FA" : s;
        this.zza = DefaultClock.getInstance();
        zzdi zzdi0 = new zzdi(this);
        ThreadPoolExecutor threadPoolExecutor0 = new ThreadPoolExecutor(1, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), zzdi0);
        int v = 1;
        threadPoolExecutor0.allowCoreThreadTimeOut(true);
        this.zzb = Executors.unconfigurableExecutorService(threadPoolExecutor0);
        this.zze = new AppMeasurementSdk(this);
        this.zzf = new ArrayList();
        try {
            String s3 = zziq.zzc(context0, "google_app_id", zzfv.zza(context0));
        }
        catch(IllegalStateException unused_ex) {
            goto label_19;
        }
        if(s3 != null && !this.zzS()) {
            this.zzi = null;
            this.zzh = true;
            Log.w(this.zzd, "Disabling data collection. Found google_app_id in strings.xml but Google Analytics for Firebase is missing. Remove this value or add Google Analytics for Firebase to resume data collection.");
            return;
        }
    label_19:
        if(this.zzW(s1, s2)) {
            this.zzi = s1;
        }
        else {
            this.zzi = "fa";
            if(s1 == null || s2 == null) {
                if(s2 != null) {
                    v = 0;
                }
                if((v ^ (s1 == null ? 1 : 0)) != 0) {
                    Log.w(this.zzd, "Specified origin or custom app id is null. Both parameters will be ignored.");
                }
            }
            else {
                Log.v(this.zzd, "Deferring to Google Analytics for Firebase for event data collection. https://firebase.google.com/docs/analytics");
            }
        }
        this.zzV(new zzcx(this, s1, s2, context0, bundle0));
        Application application0 = (Application)context0.getApplicationContext();
        if(application0 == null) {
            Log.w(this.zzd, "Unable to register lifecycle notifications. Application null.");
            return;
        }
        application0.registerActivityLifecycleCallbacks(new zzee(this));
    }

    public final void zzA(String s, String s1, Bundle bundle0, long v) {
        this.zzU(s, s1, bundle0, true, false, v);
    }

    public final void zzB(int v, String s, Object object0, Object object1, Object object2) {
        this.zzV(new zzdg(this, false, 5, s, object0, null, null));
    }

    public final void zzC(zzhg zzhg0) {
        zzdw zzdw0;
        Preconditions.checkNotNull(zzhg0);
        synchronized(this.zzf) {
            for(int v1 = 0; v1 < this.zzf.size(); ++v1) {
                if(zzhg0.equals(((Pair)this.zzf.get(v1)).first)) {
                    Log.w(this.zzd, "OnEventListener already registered.");
                    return;
                }
            }
            zzdw0 = new zzdw(zzhg0);
            Pair pair0 = new Pair(zzhg0, zzdw0);
            this.zzf.add(pair0);
        }
        if(this.zzj != null) {
            try {
                this.zzj.registerOnMeasurementEventListener(zzdw0);
                return;
            }
            catch(RemoteException | BadParcelableException | IllegalArgumentException | IllegalStateException | NetworkOnMainThreadException | NullPointerException | SecurityException | UnsupportedOperationException unused_ex) {
                Log.w(this.zzd, "Failed to register event listener on calling thread. Trying again on the dynamite thread.");
            }
        }
        this.zzV(new zzdq(this, zzdw0));
    }

    public final void zzD() {
        this.zzV(new zzcv(this));
    }

    public final void zzE(Bundle bundle0) {
        this.zzV(new zzcn(this, bundle0));
    }

    public final void zzF(Bundle bundle0) {
        this.zzV(new zzct(this, bundle0));
    }

    public final void zzG(Bundle bundle0) {
        this.zzV(new zzcu(this, bundle0));
    }

    public final void zzH(Activity activity0, String s, String s1) {
        this.zzV(new zzcr(this, activity0, s, s1));
    }

    public final void zzI(boolean z) {
        this.zzV(new zzdn(this, z));
    }

    public final void zzJ(Bundle bundle0) {
        this.zzV(new zzdo(this, bundle0));
    }

    public final void zzK(zzhf zzhf0) {
        zzdv zzdv0 = new zzdv(zzhf0);
        if(this.zzj != null) {
            try {
                this.zzj.setEventInterceptor(zzdv0);
                return;
            }
            catch(RemoteException | BadParcelableException | IllegalArgumentException | IllegalStateException | NetworkOnMainThreadException | NullPointerException | SecurityException | UnsupportedOperationException unused_ex) {
                Log.w(this.zzd, "Failed to set event interceptor on calling thread. Trying again on the dynamite thread.");
            }
        }
        this.zzV(new zzdp(this, zzdv0));
    }

    public final void zzL(Boolean boolean0) {
        this.zzV(new zzcs(this, boolean0));
    }

    public final void zzM(long v) {
        this.zzV(new zzcw(this, v));
    }

    public final void zzN(String s) {
        this.zzV(new zzcq(this, s));
    }

    public final void zzO(String s, String s1, Object object0, boolean z) {
        this.zzV(new zzdt(this, s, s1, object0, z));
    }

    public final void zzP(zzhg zzhg0) {
        zzdw zzdw0;
        Pair pair0;
        Preconditions.checkNotNull(zzhg0);
        synchronized(this.zzf) {
            for(int v1 = 0; true; ++v1) {
                pair0 = null;
                if(v1 >= this.zzf.size()) {
                    break;
                }
                if(zzhg0.equals(((Pair)this.zzf.get(v1)).first)) {
                    pair0 = (Pair)this.zzf.get(v1);
                    break;
                }
            }
            if(pair0 == null) {
                Log.w(this.zzd, "OnEventListener had not been registered.");
                return;
            }
            this.zzf.remove(pair0);
            zzdw0 = (zzdw)pair0.second;
        }
        if(this.zzj != null) {
            try {
                this.zzj.unregisterOnMeasurementEventListener(zzdw0);
                return;
            }
            catch(RemoteException | BadParcelableException | IllegalArgumentException | IllegalStateException | NetworkOnMainThreadException | NullPointerException | SecurityException | UnsupportedOperationException unused_ex) {
                Log.w(this.zzd, "Failed to unregister event listener on calling thread. Trying again on the dynamite thread.");
            }
        }
        this.zzV(new zzdr(this, zzdw0));
    }

    static boolean zzQ(zzef zzef0) {
        return zzef0.zzh;
    }

    static boolean zzR(zzef zzef0, String s, String s1) {
        return zzef0.zzW(s, s1);
    }

    protected final boolean zzS() {
        try {
            Class.forName("com.google.firebase.analytics.FirebaseAnalytics", false, this.getClass().getClassLoader());
            return true;
        }
        catch(ClassNotFoundException unused_ex) {
            return false;
        }
    }

    private final void zzT(Exception exception0, boolean z, boolean z1) {
        this.zzh |= z;
        if(z) {
            Log.w(this.zzd, "Data collection startup failed. No data will be collected.", exception0);
            return;
        }
        if(z1) {
            this.zzB(5, "Error with data collection. Data lost.", exception0, null, null);
        }
        Log.w(this.zzd, "Error with data collection. Data lost.", exception0);
    }

    private final void zzU(String s, String s1, Bundle bundle0, boolean z, boolean z1, Long long0) {
        this.zzV(new zzds(this, long0, s, s1, bundle0, z, z1));
    }

    private final void zzV(zzdu zzdu0) {
        this.zzb.execute(zzdu0);
    }

    private final boolean zzW(String s, String s1) {
        return s1 != null && s != null && !this.zzS();
    }

    public final int zza(String s) {
        zzbz zzbz0 = new zzbz();
        this.zzV(new zzdj(this, s, zzbz0));
        Integer integer0 = (Integer)zzbz.zzf(zzbz0.zzb(10000L), Integer.class);
        return integer0 == null ? 25 : ((int)integer0);
    }

    public final long zzb() {
        zzbz zzbz0 = new zzbz();
        this.zzV(new zzdc(this, zzbz0));
        Long long0 = zzbz0.zzc(500L);
        if(long0 == null) {
            long v = new Random(System.nanoTime() ^ this.zza.currentTimeMillis()).nextLong();
            int v1 = this.zzg + 1;
            this.zzg = v1;
            return v + ((long)v1);
        }
        return (long)long0;
    }

    public final Bundle zzc(Bundle bundle0, boolean z) {
        zzbz zzbz0 = new zzbz();
        this.zzV(new zzdh(this, bundle0, zzbz0));
        return z ? zzbz0.zzb(5000L) : null;
    }

    public final AppMeasurementSdk zzd() {
        return this.zze;
    }

    static zzcc zze(zzef zzef0) {
        return zzef0.zzj;
    }

    protected final zzcc zzf(Context context0, boolean z) {
        try {
            return zzcb.asInterface(DynamiteModule.load(context0, DynamiteModule.PREFER_HIGHEST_OR_LOCAL_VERSION, "com.google.android.gms.measurement.dynamite").instantiate("com.google.android.gms.measurement.internal.AppMeasurementDynamiteService"));
        }
        catch(LoadingException dynamiteModule$LoadingException0) {
            this.zzT(dynamiteModule$LoadingException0, true, false);
            return null;
        }
    }

    public static zzef zzg(Context context0, String s, String s1, String s2, Bundle bundle0) {
        Preconditions.checkNotNull(context0);
        if(zzef.zzc == null) {
            synchronized(zzef.class) {
                if(zzef.zzc == null) {
                    zzef.zzc = new zzef(context0, s, s1, s2, bundle0);
                }
                return zzef.zzc;
            }
        }
        return zzef.zzc;
    }

    public final Long zzh() {
        zzbz zzbz0 = new zzbz();
        this.zzV(new zzdl(this, zzbz0));
        return zzbz0.zzc(120000L);
    }

    public final Object zzi(int v) {
        zzbz zzbz0 = new zzbz();
        this.zzV(new zzdm(this, zzbz0, v));
        return zzbz.zzf(zzbz0.zzb(15000L), Object.class);
    }

    static String zzj(zzef zzef0) {
        return zzef0.zzd;
    }

    public final String zzk() {
        return this.zzi;
    }

    public final String zzl() {
        zzbz zzbz0 = new zzbz();
        this.zzV(new zzdk(this, zzbz0));
        return zzbz0.zzd(120000L);
    }

    public final String zzm() {
        zzbz zzbz0 = new zzbz();
        this.zzV(new zzdb(this, zzbz0));
        return zzbz0.zzd(50L);
    }

    public final String zzn() {
        zzbz zzbz0 = new zzbz();
        this.zzV(new zzde(this, zzbz0));
        return zzbz0.zzd(500L);
    }

    public final String zzo() {
        zzbz zzbz0 = new zzbz();
        this.zzV(new zzdd(this, zzbz0));
        return zzbz0.zzd(500L);
    }

    public final String zzp() {
        zzbz zzbz0 = new zzbz();
        this.zzV(new zzda(this, zzbz0));
        return zzbz0.zzd(500L);
    }

    public final List zzq(String s, String s1) {
        zzbz zzbz0 = new zzbz();
        this.zzV(new zzcp(this, s, s1, zzbz0));
        List list0 = (List)zzbz.zzf(zzbz0.zzb(5000L), List.class);
        return list0 == null ? Collections.EMPTY_LIST : list0;
    }

    public final Map zzr(String s, String s1, boolean z) {
        zzbz zzbz0 = new zzbz();
        this.zzV(new zzdf(this, s, s1, z, zzbz0));
        Bundle bundle0 = zzbz0.zzb(5000L);
        if(bundle0 != null && bundle0.size() != 0) {
            Map map0 = new HashMap(bundle0.size());
            for(Object object0: bundle0.keySet()) {
                String s2 = (String)object0;
                Object object1 = bundle0.get(s2);
                if(object1 instanceof Double || object1 instanceof Long || object1 instanceof String) {
                    map0.put(s2, object1);
                }
            }
            return map0;
        }
        return Collections.EMPTY_MAP;
    }

    static void zzs(zzef zzef0, zzcc zzcc0) {
        zzef0.zzj = zzcc0;
    }

    static void zzt(zzef zzef0, Exception exception0, boolean z, boolean z1) {
        zzef0.zzT(exception0, z, z1);
    }

    static void zzu(zzef zzef0, zzdu zzdu0) {
        zzef0.zzV(zzdu0);
    }

    public final void zzv(String s) {
        this.zzV(new zzcy(this, s));
    }

    public final void zzw(String s, String s1, Bundle bundle0) {
        this.zzV(new zzco(this, s, s1, bundle0));
    }

    public final void zzx(String s) {
        this.zzV(new zzcz(this, s));
    }

    public final void zzy(String s, Bundle bundle0) {
        this.zzU(null, s, bundle0, false, true, null);
    }

    public final void zzz(String s, String s1, Bundle bundle0) {
        this.zzU(s, s1, bundle0, true, true, null);
    }
}

