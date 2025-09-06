package com.google.firebase.analytics.connector;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzef;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.gms.measurement.internal.zzgz;
import com.google.android.gms.measurement.internal.zziq;
import com.google.firebase.DataCollectionDefaultChange;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.connector.internal.zzc;
import com.google.firebase.analytics.connector.internal.zze;
import com.google.firebase.analytics.connector.internal.zzg;
import com.google.firebase.events.Event;
import com.google.firebase.events.Subscriber;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class AnalyticsConnectorImpl implements AnalyticsConnector {
    final AppMeasurementSdk zza;
    final Map zzb;
    private static volatile AnalyticsConnector zzc;

    AnalyticsConnectorImpl(AppMeasurementSdk appMeasurementSdk0) {
        Preconditions.checkNotNull(appMeasurementSdk0);
        this.zza = appMeasurementSdk0;
        this.zzb = new ConcurrentHashMap();
    }

    @Override  // com.google.firebase.analytics.connector.AnalyticsConnector
    public void clearConditionalUserProperty(String s, String s1, Bundle bundle0) {
        if(s1 != null && !zzc.zzb(s1, bundle0)) {
            return;
        }
        this.zza.clearConditionalUserProperty(s, s1, bundle0);
    }

    @Override  // com.google.firebase.analytics.connector.AnalyticsConnector
    public List getConditionalUserProperties(String s, String s1) {
        List list0 = new ArrayList();
        for(Object object0: this.zza.getConditionalUserProperties(s, s1)) {
            Preconditions.checkNotNull(((Bundle)object0));
            ConditionalUserProperty analyticsConnector$ConditionalUserProperty0 = new ConditionalUserProperty();
            analyticsConnector$ConditionalUserProperty0.origin = (String)Preconditions.checkNotNull(((String)zzgz.zza(((Bundle)object0), "origin", String.class, null)));
            analyticsConnector$ConditionalUserProperty0.name = (String)Preconditions.checkNotNull(((String)zzgz.zza(((Bundle)object0), "name", String.class, null)));
            analyticsConnector$ConditionalUserProperty0.value = zzgz.zza(((Bundle)object0), "value", Object.class, null);
            analyticsConnector$ConditionalUserProperty0.triggerEventName = (String)zzgz.zza(((Bundle)object0), "trigger_event_name", String.class, null);
            analyticsConnector$ConditionalUserProperty0.triggerTimeout = (long)(((Long)zzgz.zza(((Bundle)object0), "trigger_timeout", Long.class, 0L)));
            analyticsConnector$ConditionalUserProperty0.timedOutEventName = (String)zzgz.zza(((Bundle)object0), "timed_out_event_name", String.class, null);
            analyticsConnector$ConditionalUserProperty0.timedOutEventParams = (Bundle)zzgz.zza(((Bundle)object0), "timed_out_event_params", Bundle.class, null);
            analyticsConnector$ConditionalUserProperty0.triggeredEventName = (String)zzgz.zza(((Bundle)object0), "triggered_event_name", String.class, null);
            analyticsConnector$ConditionalUserProperty0.triggeredEventParams = (Bundle)zzgz.zza(((Bundle)object0), "triggered_event_params", Bundle.class, null);
            analyticsConnector$ConditionalUserProperty0.timeToLive = (long)(((Long)zzgz.zza(((Bundle)object0), "time_to_live", Long.class, 0L)));
            analyticsConnector$ConditionalUserProperty0.expiredEventName = (String)zzgz.zza(((Bundle)object0), "expired_event_name", String.class, null);
            analyticsConnector$ConditionalUserProperty0.expiredEventParams = (Bundle)zzgz.zza(((Bundle)object0), "expired_event_params", Bundle.class, null);
            analyticsConnector$ConditionalUserProperty0.active = ((Boolean)zzgz.zza(((Bundle)object0), "active", Boolean.class, Boolean.FALSE)).booleanValue();
            analyticsConnector$ConditionalUserProperty0.creationTimestamp = (long)(((Long)zzgz.zza(((Bundle)object0), "creation_timestamp", Long.class, 0L)));
            analyticsConnector$ConditionalUserProperty0.triggeredTimestamp = (long)(((Long)zzgz.zza(((Bundle)object0), "triggered_timestamp", Long.class, 0L)));
            list0.add(analyticsConnector$ConditionalUserProperty0);
        }
        return list0;
    }

    public static AnalyticsConnector getInstance() {
        return AnalyticsConnectorImpl.getInstance(FirebaseApp.getInstance());
    }

    public static AnalyticsConnector getInstance(FirebaseApp firebaseApp0) {
        return (AnalyticsConnector)firebaseApp0.get(AnalyticsConnector.class);
    }

    public static AnalyticsConnector getInstance(FirebaseApp firebaseApp0, Context context0, Subscriber subscriber0) {
        Preconditions.checkNotNull(firebaseApp0);
        Preconditions.checkNotNull(context0);
        Preconditions.checkNotNull(subscriber0);
        Preconditions.checkNotNull(context0.getApplicationContext());
        if(AnalyticsConnectorImpl.zzc == null) {
            synchronized(AnalyticsConnectorImpl.class) {
                if(AnalyticsConnectorImpl.zzc == null) {
                    Bundle bundle0 = new Bundle(1);
                    if(firebaseApp0.isDefaultApp()) {
                        subscriber0.subscribe(DataCollectionDefaultChange.class, zza.zza, zzb.zza);
                        bundle0.putBoolean("dataCollectionDefaultEnabled", firebaseApp0.isDataCollectionDefaultEnabled());
                    }
                    AnalyticsConnectorImpl.zzc = new AnalyticsConnectorImpl(zzef.zzg(context0, null, null, null, bundle0).zzd());
                }
                return AnalyticsConnectorImpl.zzc;
            }
        }
        return AnalyticsConnectorImpl.zzc;
    }

    @Override  // com.google.firebase.analytics.connector.AnalyticsConnector
    public int getMaxUserProperties(String s) {
        return this.zza.getMaxUserProperties(s);
    }

    @Override  // com.google.firebase.analytics.connector.AnalyticsConnector
    public Map getUserProperties(boolean z) {
        return this.zza.getUserProperties(null, null, z);
    }

    @Override  // com.google.firebase.analytics.connector.AnalyticsConnector
    public void logEvent(String s, String s1, Bundle bundle0) {
        if(bundle0 == null) {
            bundle0 = new Bundle();
        }
        if(zzc.zzd(s) && zzc.zzb(s1, bundle0) && zzc.zza(s, s1, bundle0)) {
            if("clx".equals(s) && "_ae".equals(s1)) {
                bundle0.putLong("_r", 1L);
            }
            this.zza.logEvent(s, s1, bundle0);
        }
    }

    @Override  // com.google.firebase.analytics.connector.AnalyticsConnector
    public AnalyticsConnectorHandle registerAnalyticsConnectorListener(String s, AnalyticsConnectorListener analyticsConnector$AnalyticsConnectorListener0) {
        zze zze0;
        Preconditions.checkNotNull(analyticsConnector$AnalyticsConnectorListener0);
        if(!zzc.zzd(s)) {
            return null;
        }
        if(this.zzc(s)) {
            return null;
        }
        AppMeasurementSdk appMeasurementSdk0 = this.zza;
        if("fiam".equals(s)) {
            zze0 = new zze(appMeasurementSdk0, analyticsConnector$AnalyticsConnectorListener0);
        }
        else if("clx".equals(s)) {
            zze0 = new zzg(appMeasurementSdk0, analyticsConnector$AnalyticsConnectorListener0);
        }
        else {
            zze0 = null;
        }
        if(zze0 != null) {
            this.zzb.put(s, zze0);
            return new AnalyticsConnectorHandle() {
                final AnalyticsConnectorImpl zzb;

                @Override  // com.google.firebase.analytics.connector.AnalyticsConnector$AnalyticsConnectorHandle
                public void registerEventNames(Set set0) {
                    if(AnalyticsConnectorImpl.this.zzc(s) && s.equals("fiam") && set0 != null && !set0.isEmpty()) {
                        ((com.google.firebase.analytics.connector.internal.zza)AnalyticsConnectorImpl.this.zzb.get(s)).zzb(set0);
                    }
                }

                @Override  // com.google.firebase.analytics.connector.AnalyticsConnector$AnalyticsConnectorHandle
                public final void unregister() {
                    if(!AnalyticsConnectorImpl.this.zzc(s)) {
                        return;
                    }
                    AnalyticsConnectorListener analyticsConnector$AnalyticsConnectorListener0 = ((com.google.firebase.analytics.connector.internal.zza)AnalyticsConnectorImpl.this.zzb.get(s)).zza();
                    if(analyticsConnector$AnalyticsConnectorListener0 != null) {
                        analyticsConnector$AnalyticsConnectorListener0.onMessageTriggered(0, null);
                    }
                    AnalyticsConnectorImpl.this.zzb.remove(s);
                }

                @Override  // com.google.firebase.analytics.connector.AnalyticsConnector$AnalyticsConnectorHandle
                public void unregisterEventNames() {
                    if(AnalyticsConnectorImpl.this.zzc(s) && s.equals("fiam")) {
                        ((com.google.firebase.analytics.connector.internal.zza)AnalyticsConnectorImpl.this.zzb.get(s)).zzc();
                    }
                }
            };
        }
        return null;
    }

    @Override  // com.google.firebase.analytics.connector.AnalyticsConnector
    public void setConditionalUserProperty(ConditionalUserProperty analyticsConnector$ConditionalUserProperty0) {
        if(analyticsConnector$ConditionalUserProperty0 != null) {
            String s = analyticsConnector$ConditionalUserProperty0.origin;
            if(s != null && !s.isEmpty() && (analyticsConnector$ConditionalUserProperty0.value == null || zziq.zza(analyticsConnector$ConditionalUserProperty0.value) != null) && zzc.zzd(s) && zzc.zze(s, analyticsConnector$ConditionalUserProperty0.name) && (analyticsConnector$ConditionalUserProperty0.expiredEventName == null || zzc.zzb(analyticsConnector$ConditionalUserProperty0.expiredEventName, analyticsConnector$ConditionalUserProperty0.expiredEventParams) && zzc.zza(s, analyticsConnector$ConditionalUserProperty0.expiredEventName, analyticsConnector$ConditionalUserProperty0.expiredEventParams)) && (analyticsConnector$ConditionalUserProperty0.triggeredEventName == null || zzc.zzb(analyticsConnector$ConditionalUserProperty0.triggeredEventName, analyticsConnector$ConditionalUserProperty0.triggeredEventParams) && zzc.zza(s, analyticsConnector$ConditionalUserProperty0.triggeredEventName, analyticsConnector$ConditionalUserProperty0.triggeredEventParams)) && (analyticsConnector$ConditionalUserProperty0.timedOutEventName == null || zzc.zzb(analyticsConnector$ConditionalUserProperty0.timedOutEventName, analyticsConnector$ConditionalUserProperty0.timedOutEventParams) && zzc.zza(s, analyticsConnector$ConditionalUserProperty0.timedOutEventName, analyticsConnector$ConditionalUserProperty0.timedOutEventParams))) {
                AppMeasurementSdk appMeasurementSdk0 = this.zza;
                Bundle bundle0 = new Bundle();
                if(analyticsConnector$ConditionalUserProperty0.origin != null) {
                    bundle0.putString("origin", analyticsConnector$ConditionalUserProperty0.origin);
                }
                if(analyticsConnector$ConditionalUserProperty0.name != null) {
                    bundle0.putString("name", analyticsConnector$ConditionalUserProperty0.name);
                }
                if(analyticsConnector$ConditionalUserProperty0.value != null) {
                    zzgz.zzb(bundle0, analyticsConnector$ConditionalUserProperty0.value);
                }
                if(analyticsConnector$ConditionalUserProperty0.triggerEventName != null) {
                    bundle0.putString("trigger_event_name", analyticsConnector$ConditionalUserProperty0.triggerEventName);
                }
                bundle0.putLong("trigger_timeout", analyticsConnector$ConditionalUserProperty0.triggerTimeout);
                if(analyticsConnector$ConditionalUserProperty0.timedOutEventName != null) {
                    bundle0.putString("timed_out_event_name", analyticsConnector$ConditionalUserProperty0.timedOutEventName);
                }
                if(analyticsConnector$ConditionalUserProperty0.timedOutEventParams != null) {
                    bundle0.putBundle("timed_out_event_params", analyticsConnector$ConditionalUserProperty0.timedOutEventParams);
                }
                if(analyticsConnector$ConditionalUserProperty0.triggeredEventName != null) {
                    bundle0.putString("triggered_event_name", analyticsConnector$ConditionalUserProperty0.triggeredEventName);
                }
                if(analyticsConnector$ConditionalUserProperty0.triggeredEventParams != null) {
                    bundle0.putBundle("triggered_event_params", analyticsConnector$ConditionalUserProperty0.triggeredEventParams);
                }
                bundle0.putLong("time_to_live", analyticsConnector$ConditionalUserProperty0.timeToLive);
                if(analyticsConnector$ConditionalUserProperty0.expiredEventName != null) {
                    bundle0.putString("expired_event_name", analyticsConnector$ConditionalUserProperty0.expiredEventName);
                }
                if(analyticsConnector$ConditionalUserProperty0.expiredEventParams != null) {
                    bundle0.putBundle("expired_event_params", analyticsConnector$ConditionalUserProperty0.expiredEventParams);
                }
                bundle0.putLong("creation_timestamp", analyticsConnector$ConditionalUserProperty0.creationTimestamp);
                bundle0.putBoolean("active", analyticsConnector$ConditionalUserProperty0.active);
                bundle0.putLong("triggered_timestamp", analyticsConnector$ConditionalUserProperty0.triggeredTimestamp);
                appMeasurementSdk0.setConditionalUserProperty(bundle0);
            }
        }
    }

    @Override  // com.google.firebase.analytics.connector.AnalyticsConnector
    public void setUserProperty(String s, String s1, Object object0) {
        if(!zzc.zzd(s) || !zzc.zze(s, s1)) {
            return;
        }
        this.zza.setUserProperty(s, s1, object0);
    }

    static void zza(Event event0) {
        synchronized(AnalyticsConnectorImpl.class) {
            ((AnalyticsConnectorImpl)Preconditions.checkNotNull(AnalyticsConnectorImpl.zzc)).zza.zza(((DataCollectionDefaultChange)event0.getPayload()).enabled);
        }
    }

    // 去混淆评级： 低(20)
    private final boolean zzc(String s) {
        return !s.isEmpty() && this.zzb.containsKey(s) && this.zzb.get(s) != null;
    }
}

