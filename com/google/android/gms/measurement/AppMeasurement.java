package com.google.android.gms.measurement;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzcl;
import com.google.android.gms.measurement.internal.zzgd;
import com.google.android.gms.measurement.internal.zzgz;
import com.google.android.gms.measurement.internal.zzhf;
import com.google.android.gms.measurement.internal.zzhg;
import com.google.android.gms.measurement.internal.zzil;
import com.google.android.gms.measurement.internal.zziq;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Deprecated
public class AppMeasurement {
    public static class ConditionalUserProperty {
        public boolean mActive;
        public String mAppId;
        public long mCreationTimestamp;
        public String mExpiredEventName;
        public Bundle mExpiredEventParams;
        public String mName;
        public String mOrigin;
        public long mTimeToLive;
        public String mTimedOutEventName;
        public Bundle mTimedOutEventParams;
        public String mTriggerEventName;
        public long mTriggerTimeout;
        public String mTriggeredEventName;
        public Bundle mTriggeredEventParams;
        public long mTriggeredTimestamp;
        public Object mValue;

        public ConditionalUserProperty() {
        }

        ConditionalUserProperty(Bundle bundle0) {
            Preconditions.checkNotNull(bundle0);
            this.mAppId = (String)zzgz.zza(bundle0, "app_id", String.class, null);
            this.mOrigin = (String)zzgz.zza(bundle0, "origin", String.class, null);
            this.mName = (String)zzgz.zza(bundle0, "name", String.class, null);
            this.mValue = zzgz.zza(bundle0, "value", Object.class, null);
            this.mTriggerEventName = (String)zzgz.zza(bundle0, "trigger_event_name", String.class, null);
            this.mTriggerTimeout = (long)(((Long)zzgz.zza(bundle0, "trigger_timeout", Long.class, 0L)));
            this.mTimedOutEventName = (String)zzgz.zza(bundle0, "timed_out_event_name", String.class, null);
            this.mTimedOutEventParams = (Bundle)zzgz.zza(bundle0, "timed_out_event_params", Bundle.class, null);
            this.mTriggeredEventName = (String)zzgz.zza(bundle0, "triggered_event_name", String.class, null);
            this.mTriggeredEventParams = (Bundle)zzgz.zza(bundle0, "triggered_event_params", Bundle.class, null);
            this.mTimeToLive = (long)(((Long)zzgz.zza(bundle0, "time_to_live", Long.class, 0L)));
            this.mExpiredEventName = (String)zzgz.zza(bundle0, "expired_event_name", String.class, null);
            this.mExpiredEventParams = (Bundle)zzgz.zza(bundle0, "expired_event_params", Bundle.class, null);
            this.mActive = ((Boolean)zzgz.zza(bundle0, "active", Boolean.class, Boolean.FALSE)).booleanValue();
            this.mCreationTimestamp = (long)(((Long)zzgz.zza(bundle0, "creation_timestamp", Long.class, 0L)));
            this.mTriggeredTimestamp = (long)(((Long)zzgz.zza(bundle0, "triggered_timestamp", Long.class, 0L)));
        }

        public ConditionalUserProperty(ConditionalUserProperty appMeasurement$ConditionalUserProperty0) {
            Preconditions.checkNotNull(appMeasurement$ConditionalUserProperty0);
            this.mAppId = appMeasurement$ConditionalUserProperty0.mAppId;
            this.mOrigin = appMeasurement$ConditionalUserProperty0.mOrigin;
            this.mCreationTimestamp = appMeasurement$ConditionalUserProperty0.mCreationTimestamp;
            this.mName = appMeasurement$ConditionalUserProperty0.mName;
            Object object0 = appMeasurement$ConditionalUserProperty0.mValue;
            if(object0 != null) {
                Object object1 = zziq.zza(object0);
                this.mValue = object1;
                if(object1 == null) {
                    this.mValue = appMeasurement$ConditionalUserProperty0.mValue;
                }
            }
            this.mActive = appMeasurement$ConditionalUserProperty0.mActive;
            this.mTriggerEventName = appMeasurement$ConditionalUserProperty0.mTriggerEventName;
            this.mTriggerTimeout = appMeasurement$ConditionalUserProperty0.mTriggerTimeout;
            this.mTimedOutEventName = appMeasurement$ConditionalUserProperty0.mTimedOutEventName;
            Bundle bundle0 = appMeasurement$ConditionalUserProperty0.mTimedOutEventParams;
            if(bundle0 != null) {
                this.mTimedOutEventParams = new Bundle(bundle0);
            }
            this.mTriggeredEventName = appMeasurement$ConditionalUserProperty0.mTriggeredEventName;
            Bundle bundle1 = appMeasurement$ConditionalUserProperty0.mTriggeredEventParams;
            if(bundle1 != null) {
                this.mTriggeredEventParams = new Bundle(bundle1);
            }
            this.mTriggeredTimestamp = appMeasurement$ConditionalUserProperty0.mTriggeredTimestamp;
            this.mTimeToLive = appMeasurement$ConditionalUserProperty0.mTimeToLive;
            this.mExpiredEventName = appMeasurement$ConditionalUserProperty0.mExpiredEventName;
            Bundle bundle2 = appMeasurement$ConditionalUserProperty0.mExpiredEventParams;
            if(bundle2 != null) {
                this.mExpiredEventParams = new Bundle(bundle2);
            }
        }
    }

    public interface EventInterceptor extends zzhf {
        @Override  // com.google.android.gms.measurement.internal.zzhf
        void interceptEvent(String arg1, String arg2, Bundle arg3, long arg4);
    }

    public interface OnEventListener extends zzhg {
        @Override  // com.google.android.gms.measurement.internal.zzhg
        void onEvent(String arg1, String arg2, Bundle arg3, long arg4);
    }

    public static final String CRASH_ORIGIN = "crash";
    public static final String FCM_ORIGIN = "fcm";
    public static final String FIAM_ORIGIN = "fiam";
    private static volatile AppMeasurement zza;
    private final zzd zzb;

    public AppMeasurement(zzgd zzgd0) {
        this.zzb = new zza(zzgd0);
    }

    public AppMeasurement(zzil zzil0) {
        this.zzb = new zzb(zzil0);
    }

    public void beginAdUnitExposure(String s) {
        this.zzb.zzp(s);
    }

    public void clearConditionalUserProperty(String s, String s1, Bundle bundle0) {
        this.zzb.zzq(s, s1, bundle0);
    }

    public void endAdUnitExposure(String s) {
        this.zzb.zzr(s);
    }

    public long generateEventId() {
        return this.zzb.zzb();
    }

    public String getAppInstanceId() {
        return this.zzb.zzh();
    }

    public Boolean getBoolean() {
        return this.zzb.zzc();
    }

    public List getConditionalUserProperties(String s, String s1) {
        List list0 = this.zzb.zzm(s, s1);
        List list1 = new ArrayList((list0 == null ? 0 : list0.size()));
        for(Object object0: list0) {
            ((ArrayList)list1).add(new ConditionalUserProperty(((Bundle)object0)));
        }
        return list1;
    }

    public String getCurrentScreenClass() {
        return this.zzb.zzi();
    }

    public String getCurrentScreenName() {
        return this.zzb.zzj();
    }

    public Double getDouble() {
        return this.zzb.zzd();
    }

    public String getGmpAppId() {
        return this.zzb.zzk();
    }

    @Deprecated
    public static AppMeasurement getInstance(Context context0) {
        zzil zzil0;
        if(AppMeasurement.zza == null) {
            Class class0 = AppMeasurement.class;
            __monitor_enter(class0);
            if(AppMeasurement.zza == null) {
                try {
                    zzil0 = null;
                    zzil0 = FirebaseAnalytics.getScionFrontendApiImplementation(context0, null);
                }
                catch(Exception unused_ex) {
                }
                AppMeasurement.zza = zzil0 == null ? new AppMeasurement(zzgd.zzp(context0, new zzcl(0L, 0L, true, null, null, null, null, null), null)) : new AppMeasurement(zzil0);
            }
            __monitor_exit(class0);
            return AppMeasurement.zza;
        }
        return AppMeasurement.zza;
    }

    public Integer getInteger() {
        return this.zzb.zze();
    }

    public Long getLong() {
        return this.zzb.zzf();
    }

    public int getMaxUserProperties(String s) {
        return this.zzb.zza(s);
    }

    public String getString() {
        return this.zzb.zzl();
    }

    protected Map getUserProperties(String s, String s1, boolean z) {
        return this.zzb.zzo(s, s1, z);
    }

    public Map getUserProperties(boolean z) {
        return this.zzb.zzn(z);
    }

    public void logEventInternal(String s, String s1, Bundle bundle0) {
        this.zzb.zzs(s, s1, bundle0);
    }

    public void logEventInternalNoInterceptor(String s, String s1, Bundle bundle0, long v) {
        this.zzb.zzt(s, s1, bundle0, v);
    }

    public void registerOnMeasurementEventListener(OnEventListener appMeasurement$OnEventListener0) {
        this.zzb.zzu(appMeasurement$OnEventListener0);
    }

    public void setConditionalUserProperty(ConditionalUserProperty appMeasurement$ConditionalUserProperty0) {
        Preconditions.checkNotNull(appMeasurement$ConditionalUserProperty0);
        zzd zzd0 = this.zzb;
        Bundle bundle0 = new Bundle();
        String s = appMeasurement$ConditionalUserProperty0.mAppId;
        if(s != null) {
            bundle0.putString("app_id", s);
        }
        String s1 = appMeasurement$ConditionalUserProperty0.mOrigin;
        if(s1 != null) {
            bundle0.putString("origin", s1);
        }
        String s2 = appMeasurement$ConditionalUserProperty0.mName;
        if(s2 != null) {
            bundle0.putString("name", s2);
        }
        Object object0 = appMeasurement$ConditionalUserProperty0.mValue;
        if(object0 != null) {
            zzgz.zzb(bundle0, object0);
        }
        String s3 = appMeasurement$ConditionalUserProperty0.mTriggerEventName;
        if(s3 != null) {
            bundle0.putString("trigger_event_name", s3);
        }
        bundle0.putLong("trigger_timeout", appMeasurement$ConditionalUserProperty0.mTriggerTimeout);
        String s4 = appMeasurement$ConditionalUserProperty0.mTimedOutEventName;
        if(s4 != null) {
            bundle0.putString("timed_out_event_name", s4);
        }
        Bundle bundle1 = appMeasurement$ConditionalUserProperty0.mTimedOutEventParams;
        if(bundle1 != null) {
            bundle0.putBundle("timed_out_event_params", bundle1);
        }
        String s5 = appMeasurement$ConditionalUserProperty0.mTriggeredEventName;
        if(s5 != null) {
            bundle0.putString("triggered_event_name", s5);
        }
        Bundle bundle2 = appMeasurement$ConditionalUserProperty0.mTriggeredEventParams;
        if(bundle2 != null) {
            bundle0.putBundle("triggered_event_params", bundle2);
        }
        bundle0.putLong("time_to_live", appMeasurement$ConditionalUserProperty0.mTimeToLive);
        String s6 = appMeasurement$ConditionalUserProperty0.mExpiredEventName;
        if(s6 != null) {
            bundle0.putString("expired_event_name", s6);
        }
        Bundle bundle3 = appMeasurement$ConditionalUserProperty0.mExpiredEventParams;
        if(bundle3 != null) {
            bundle0.putBundle("expired_event_params", bundle3);
        }
        bundle0.putLong("creation_timestamp", appMeasurement$ConditionalUserProperty0.mCreationTimestamp);
        bundle0.putBoolean("active", appMeasurement$ConditionalUserProperty0.mActive);
        bundle0.putLong("triggered_timestamp", appMeasurement$ConditionalUserProperty0.mTriggeredTimestamp);
        zzd0.zzv(bundle0);
    }

    public void setEventInterceptor(EventInterceptor appMeasurement$EventInterceptor0) {
        this.zzb.zzw(appMeasurement$EventInterceptor0);
    }

    public void unregisterOnMeasurementEventListener(OnEventListener appMeasurement$OnEventListener0) {
        this.zzb.zzx(appMeasurement$OnEventListener0);
    }
}

