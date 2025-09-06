package com.google.android.gms.measurement.api;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.internal.measurement.zzef;
import com.google.android.gms.measurement.internal.zzhf;
import com.google.android.gms.measurement.internal.zzhg;
import java.util.List;
import java.util.Map;

public class AppMeasurementSdk {
    public static final class ConditionalUserProperty {
        public static final String ACTIVE = "active";
        public static final String CREATION_TIMESTAMP = "creation_timestamp";
        public static final String EXPIRED_EVENT_NAME = "expired_event_name";
        public static final String EXPIRED_EVENT_PARAMS = "expired_event_params";
        public static final String NAME = "name";
        public static final String ORIGIN = "origin";
        public static final String TIMED_OUT_EVENT_NAME = "timed_out_event_name";
        public static final String TIMED_OUT_EVENT_PARAMS = "timed_out_event_params";
        public static final String TIME_TO_LIVE = "time_to_live";
        public static final String TRIGGERED_EVENT_NAME = "triggered_event_name";
        public static final String TRIGGERED_EVENT_PARAMS = "triggered_event_params";
        public static final String TRIGGERED_TIMESTAMP = "triggered_timestamp";
        public static final String TRIGGER_EVENT_NAME = "trigger_event_name";
        public static final String TRIGGER_TIMEOUT = "trigger_timeout";
        public static final String VALUE = "value";

    }

    public interface EventInterceptor extends zzhf {
        @Override  // com.google.android.gms.measurement.internal.zzhf
        void interceptEvent(String arg1, String arg2, Bundle arg3, long arg4);
    }

    public interface OnEventListener extends zzhg {
        @Override  // com.google.android.gms.measurement.internal.zzhg
        void onEvent(String arg1, String arg2, Bundle arg3, long arg4);
    }

    private final zzef zza;

    public AppMeasurementSdk(zzef zzef0) {
        this.zza = zzef0;
    }

    public void beginAdUnitExposure(String s) {
        this.zza.zzv(s);
    }

    public void clearConditionalUserProperty(String s, String s1, Bundle bundle0) {
        this.zza.zzw(s, s1, bundle0);
    }

    public void endAdUnitExposure(String s) {
        this.zza.zzx(s);
    }

    public long generateEventId() {
        return this.zza.zzb();
    }

    public String getAppIdOrigin() {
        return this.zza.zzk();
    }

    public String getAppInstanceId() {
        return this.zza.zzm();
    }

    public List getConditionalUserProperties(String s, String s1) {
        return this.zza.zzq(s, s1);
    }

    public String getCurrentScreenClass() {
        return this.zza.zzn();
    }

    public String getCurrentScreenName() {
        return this.zza.zzo();
    }

    public String getGmpAppId() {
        return this.zza.zzp();
    }

    public static AppMeasurementSdk getInstance(Context context0) {
        return zzef.zzg(context0, null, null, null, null).zzd();
    }

    public static AppMeasurementSdk getInstance(Context context0, String s, String s1, String s2, Bundle bundle0) {
        return zzef.zzg(context0, s, s1, s2, bundle0).zzd();
    }

    public int getMaxUserProperties(String s) {
        return this.zza.zza(s);
    }

    public Map getUserProperties(String s, String s1, boolean z) {
        return this.zza.zzr(s, s1, z);
    }

    public void logEvent(String s, String s1, Bundle bundle0) {
        this.zza.zzz(s, s1, bundle0);
    }

    public void logEventNoInterceptor(String s, String s1, Bundle bundle0, long v) {
        this.zza.zzA(s, s1, bundle0, v);
    }

    public void performAction(Bundle bundle0) {
        this.zza.zzc(bundle0, false);
    }

    public Bundle performActionWithResponse(Bundle bundle0) {
        return this.zza.zzc(bundle0, true);
    }

    public void registerOnMeasurementEventListener(OnEventListener appMeasurementSdk$OnEventListener0) {
        this.zza.zzC(appMeasurementSdk$OnEventListener0);
    }

    public void setConditionalUserProperty(Bundle bundle0) {
        this.zza.zzE(bundle0);
    }

    public void setConsent(Bundle bundle0) {
        this.zza.zzF(bundle0);
    }

    public void setCurrentScreen(Activity activity0, String s, String s1) {
        this.zza.zzH(activity0, s, s1);
    }

    public void setEventInterceptor(EventInterceptor appMeasurementSdk$EventInterceptor0) {
        this.zza.zzK(appMeasurementSdk$EventInterceptor0);
    }

    public void setMeasurementEnabled(Boolean boolean0) {
        this.zza.zzL(boolean0);
    }

    public void setMeasurementEnabled(boolean z) {
        this.zza.zzL(Boolean.valueOf(z));
    }

    public void setUserProperty(String s, String s1, Object object0) {
        this.zza.zzO(s, s1, object0, true);
    }

    public void unregisterOnMeasurementEventListener(OnEventListener appMeasurementSdk$OnEventListener0) {
        this.zza.zzP(appMeasurementSdk$OnEventListener0);
    }

    public final void zza(boolean z) {
        this.zza.zzI(z);
    }
}

