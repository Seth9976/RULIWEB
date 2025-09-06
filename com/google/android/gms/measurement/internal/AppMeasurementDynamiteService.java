package com.google.android.gms.measurement.internal;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.measurement.zzcb;
import com.google.android.gms.internal.measurement.zzcf;
import com.google.android.gms.internal.measurement.zzci;
import com.google.android.gms.internal.measurement.zzck;
import com.google.android.gms.internal.measurement.zzcl;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

public class AppMeasurementDynamiteService extends zzcb {
    zzgd zza;
    private final Map zzb;

    public AppMeasurementDynamiteService() {
        this.zza = null;
        this.zzb = new ArrayMap();
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public void beginAdUnitExposure(String s, long v) throws RemoteException {
        this.zzb();
        this.zza.zzd().zzd(s, v);
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public void clearConditionalUserProperty(String s, String s1, Bundle bundle0) throws RemoteException {
        this.zzb();
        this.zza.zzq().zzA(s, s1, bundle0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public void clearMeasurementEnabled(long v) throws RemoteException {
        this.zzb();
        this.zza.zzq().zzU(null);
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public void endAdUnitExposure(String s, long v) throws RemoteException {
        this.zzb();
        this.zza.zzd().zze(s, v);
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public void generateEventId(zzcf zzcf0) throws RemoteException {
        this.zzb();
        long v = this.zza.zzv().zzq();
        this.zzb();
        this.zza.zzv().zzV(zzcf0, v);
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public void getAppInstanceId(zzcf zzcf0) throws RemoteException {
        this.zzb();
        this.zza.zzaB().zzp(new zzi(this, zzcf0));
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public void getCachedAppInstanceId(zzcf zzcf0) throws RemoteException {
        this.zzb();
        this.zzc(zzcf0, this.zza.zzq().zzo());
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public void getConditionalUserProperties(String s, String s1, zzcf zzcf0) throws RemoteException {
        this.zzb();
        this.zza.zzaB().zzp(new zzm(this, zzcf0, s, s1));
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public void getCurrentScreenClass(zzcf zzcf0) throws RemoteException {
        this.zzb();
        this.zzc(zzcf0, this.zza.zzq().zzp());
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public void getCurrentScreenName(zzcf zzcf0) throws RemoteException {
        this.zzb();
        this.zzc(zzcf0, this.zza.zzq().zzq());
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public void getGmpAppId(zzcf zzcf0) throws RemoteException {
        String s;
        this.zzb();
        zzik zzik0 = this.zza.zzq();
        if(zzik0.zzt.zzw() == null) {
            try {
                s = zziq.zzc(zzik0.zzt.zzaw(), "google_app_id", zzik0.zzt.zzz());
            }
            catch(IllegalStateException illegalStateException0) {
                zzik0.zzt.zzaA().zzd().zzb("getGoogleAppId failed with exception", illegalStateException0);
                s = null;
            }
        }
        else {
            s = zzik0.zzt.zzw();
        }
        this.zzc(zzcf0, s);
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public void getMaxUserProperties(String s, zzcf zzcf0) throws RemoteException {
        this.zzb();
        this.zza.zzq().zzh(s);
        this.zzb();
        this.zza.zzv().zzU(zzcf0, 25);
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public void getSessionId(zzcf zzcf0) throws RemoteException {
        this.zzb();
        zzik zzik0 = this.zza.zzq();
        zzik0.zzt.zzaB().zzp(new zzhy(zzik0, zzcf0));
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public void getTestFlag(zzcf zzcf0, int v) throws RemoteException {
        this.zzb();
        switch(v) {
            case 0: {
                this.zza.zzv().zzW(zzcf0, this.zza.zzq().zzr());
                return;
            }
            case 1: {
                this.zza.zzv().zzV(zzcf0, ((long)this.zza.zzq().zzm()));
                return;
            }
            case 2: {
                zzlp zzlp0 = this.zza.zzv();
                double f = (double)this.zza.zzq().zzj();
                Bundle bundle0 = new Bundle();
                bundle0.putDouble("r", f);
                try {
                    zzcf0.zze(bundle0);
                }
                catch(RemoteException remoteException0) {
                    zzlp0.zzt.zzaA().zzk().zzb("Error returning double value to wrapper", remoteException0);
                }
                return;
            }
            case 3: {
                this.zza.zzv().zzU(zzcf0, ((int)this.zza.zzq().zzl()));
                return;
            }
            case 4: {
                this.zza.zzv().zzQ(zzcf0, this.zza.zzq().zzi().booleanValue());
            }
        }
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public void getUserProperties(String s, String s1, boolean z, zzcf zzcf0) throws RemoteException {
        this.zzb();
        this.zza.zzaB().zzp(new zzk(this, zzcf0, s, s1, z));
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public void initForTests(Map map0) throws RemoteException {
        this.zzb();
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public void initialize(IObjectWrapper iObjectWrapper0, zzcl zzcl0, long v) throws RemoteException {
        zzgd zzgd0 = this.zza;
        if(zzgd0 == null) {
            this.zza = zzgd.zzp(((Context)Preconditions.checkNotNull(((Context)ObjectWrapper.unwrap(iObjectWrapper0)))), zzcl0, v);
            return;
        }
        zzgd0.zzaA().zzk().zza("Attempting to initialize multiple times");
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public void isDataCollectionEnabled(zzcf zzcf0) throws RemoteException {
        this.zzb();
        this.zza.zzaB().zzp(new zzn(this, zzcf0));
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public void logEvent(String s, String s1, Bundle bundle0, boolean z, boolean z1, long v) throws RemoteException {
        this.zzb();
        this.zza.zzq().zzE(s, s1, bundle0, z, z1, v);
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public void logEventAndBundle(String s, String s1, Bundle bundle0, zzcf zzcf0, long v) throws RemoteException {
        this.zzb();
        Preconditions.checkNotEmpty(s1);
        (bundle0 == null ? new Bundle() : new Bundle(bundle0)).putString("_o", "app");
        zzau zzau0 = new zzau(s1, new zzas(bundle0), "app", v);
        this.zza.zzaB().zzp(new zzj(this, zzcf0, zzau0, s));
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public void logHealthData(int v, String s, IObjectWrapper iObjectWrapper0, IObjectWrapper iObjectWrapper1, IObjectWrapper iObjectWrapper2) throws RemoteException {
        this.zzb();
        Object object0 = null;
        Object object1 = iObjectWrapper0 == null ? null : ObjectWrapper.unwrap(iObjectWrapper0);
        Object object2 = iObjectWrapper1 == null ? null : ObjectWrapper.unwrap(iObjectWrapper1);
        if(iObjectWrapper2 != null) {
            object0 = ObjectWrapper.unwrap(iObjectWrapper2);
        }
        this.zza.zzaA().zzu(v, true, false, s, object1, object2, object0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public void onActivityCreated(IObjectWrapper iObjectWrapper0, Bundle bundle0, long v) throws RemoteException {
        this.zzb();
        zzij zzij0 = this.zza.zzq().zza;
        if(zzij0 != null) {
            this.zza.zzq().zzB();
            zzij0.onActivityCreated(((Activity)ObjectWrapper.unwrap(iObjectWrapper0)), bundle0);
        }
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public void onActivityDestroyed(IObjectWrapper iObjectWrapper0, long v) throws RemoteException {
        this.zzb();
        zzij zzij0 = this.zza.zzq().zza;
        if(zzij0 != null) {
            this.zza.zzq().zzB();
            zzij0.onActivityDestroyed(((Activity)ObjectWrapper.unwrap(iObjectWrapper0)));
        }
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public void onActivityPaused(IObjectWrapper iObjectWrapper0, long v) throws RemoteException {
        this.zzb();
        zzij zzij0 = this.zza.zzq().zza;
        if(zzij0 != null) {
            this.zza.zzq().zzB();
            zzij0.onActivityPaused(((Activity)ObjectWrapper.unwrap(iObjectWrapper0)));
        }
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public void onActivityResumed(IObjectWrapper iObjectWrapper0, long v) throws RemoteException {
        this.zzb();
        zzij zzij0 = this.zza.zzq().zza;
        if(zzij0 != null) {
            this.zza.zzq().zzB();
            zzij0.onActivityResumed(((Activity)ObjectWrapper.unwrap(iObjectWrapper0)));
        }
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public void onActivitySaveInstanceState(IObjectWrapper iObjectWrapper0, zzcf zzcf0, long v) throws RemoteException {
        this.zzb();
        zzij zzij0 = this.zza.zzq().zza;
        Bundle bundle0 = new Bundle();
        if(zzij0 != null) {
            this.zza.zzq().zzB();
            zzij0.onActivitySaveInstanceState(((Activity)ObjectWrapper.unwrap(iObjectWrapper0)), bundle0);
        }
        try {
            zzcf0.zze(bundle0);
        }
        catch(RemoteException remoteException0) {
            this.zza.zzaA().zzk().zzb("Error returning bundle value to wrapper", remoteException0);
        }
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public void onActivityStarted(IObjectWrapper iObjectWrapper0, long v) throws RemoteException {
        this.zzb();
        if(this.zza.zzq().zza != null) {
            this.zza.zzq().zzB();
            Activity activity0 = (Activity)ObjectWrapper.unwrap(iObjectWrapper0);
        }
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public void onActivityStopped(IObjectWrapper iObjectWrapper0, long v) throws RemoteException {
        this.zzb();
        if(this.zza.zzq().zza != null) {
            this.zza.zzq().zzB();
            Activity activity0 = (Activity)ObjectWrapper.unwrap(iObjectWrapper0);
        }
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public void performAction(Bundle bundle0, zzcf zzcf0, long v) throws RemoteException {
        this.zzb();
        zzcf0.zze(null);
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public void registerOnMeasurementEventListener(zzci zzci0) throws RemoteException {
        zzhg zzhg0;
        this.zzb();
        synchronized(this.zzb) {
            Integer integer0 = zzci0.zzd();
            zzhg0 = (zzhg)this.zzb.get(integer0);
            if(zzhg0 == null) {
                zzhg0 = new zzp(this, zzci0);
                Integer integer1 = zzci0.zzd();
                this.zzb.put(integer1, zzhg0);
            }
        }
        this.zza.zzq().zzJ(zzhg0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public void resetAnalyticsData(long v) throws RemoteException {
        this.zzb();
        this.zza.zzq().zzK(v);
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public void setConditionalUserProperty(Bundle bundle0, long v) throws RemoteException {
        this.zzb();
        if(bundle0 == null) {
            this.zza.zzaA().zzd().zza("Conditional user property must not be null");
            return;
        }
        this.zza.zzq().zzQ(bundle0, v);
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public void setConsent(Bundle bundle0, long v) throws RemoteException {
        this.zzb();
        zzik zzik0 = this.zza.zzq();
        zzik0.zzt.zzaB().zzq(new zzhj(zzik0, bundle0, v));
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public void setConsentThirdParty(Bundle bundle0, long v) throws RemoteException {
        this.zzb();
        this.zza.zzq().zzS(bundle0, -20, v);
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public void setCurrentScreen(IObjectWrapper iObjectWrapper0, String s, String s1, long v) throws RemoteException {
        this.zzb();
        this.zza.zzs().zzw(((Activity)ObjectWrapper.unwrap(iObjectWrapper0)), s, s1);
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public void setDataCollectionEnabled(boolean z) throws RemoteException {
        this.zzb();
        zzik zzik0 = this.zza.zzq();
        zzik0.zza();
        zzik0.zzt.zzaB().zzp(new zzih(zzik0, z));
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public void setDefaultEventParameters(Bundle bundle0) {
        this.zzb();
        zzik zzik0 = this.zza.zzq();
        Bundle bundle1 = bundle0 == null ? null : new Bundle(bundle0);
        zzik0.zzt.zzaB().zzp(new zzhk(zzik0, bundle1));
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public void setEventInterceptor(zzci zzci0) throws RemoteException {
        this.zzb();
        zzo zzo0 = new zzo(this, zzci0);
        if(this.zza.zzaB().zzs()) {
            this.zza.zzq().zzT(zzo0);
            return;
        }
        this.zza.zzaB().zzp(new zzl(this, zzo0));
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public void setInstanceIdProvider(zzck zzck0) throws RemoteException {
        this.zzb();
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public void setMeasurementEnabled(boolean z, long v) throws RemoteException {
        this.zzb();
        this.zza.zzq().zzU(Boolean.valueOf(z));
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public void setMinimumSessionDuration(long v) throws RemoteException {
        this.zzb();
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public void setSessionTimeoutDuration(long v) throws RemoteException {
        this.zzb();
        zzik zzik0 = this.zza.zzq();
        zzik0.zzt.zzaB().zzp(new zzho(zzik0, v));
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public void setUserId(String s, long v) throws RemoteException {
        this.zzb();
        zzik zzik0 = this.zza.zzq();
        if(s != null && TextUtils.isEmpty(s)) {
            zzik0.zzt.zzaA().zzk().zza("User ID must be non-empty or null");
            return;
        }
        zzik0.zzt.zzaB().zzp(new zzhl(zzik0, s));
        zzik0.zzX(null, "_id", s, true, v);
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public void setUserProperty(String s, String s1, IObjectWrapper iObjectWrapper0, boolean z, long v) throws RemoteException {
        this.zzb();
        Object object0 = ObjectWrapper.unwrap(iObjectWrapper0);
        this.zza.zzq().zzX(s, s1, object0, z, v);
    }

    @Override  // com.google.android.gms.internal.measurement.zzcc
    public void unregisterOnMeasurementEventListener(zzci zzci0) throws RemoteException {
        zzhg zzhg0;
        this.zzb();
        synchronized(this.zzb) {
            Integer integer0 = zzci0.zzd();
            zzhg0 = (zzhg)this.zzb.remove(integer0);
        }
        if(zzhg0 == null) {
            zzhg0 = new zzp(this, zzci0);
        }
        this.zza.zzq().zzZ(zzhg0);
    }

    @EnsuresNonNull({"scion"})
    private final void zzb() {
        if(this.zza == null) {
            throw new IllegalStateException("Attempting to perform action before initialize.");
        }
    }

    private final void zzc(zzcf zzcf0, String s) {
        this.zzb();
        this.zza.zzv().zzW(zzcf0, s);
    }
}

