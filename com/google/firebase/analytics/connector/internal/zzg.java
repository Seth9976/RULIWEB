package com.google.firebase.analytics.connector.internal;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.connector.AnalyticsConnector.AnalyticsConnectorListener;
import java.util.Set;

public final class zzg implements zza {
    private final AnalyticsConnectorListener zza;
    private final AppMeasurementSdk zzb;
    private final zzf zzc;

    public zzg(AppMeasurementSdk appMeasurementSdk0, AnalyticsConnectorListener analyticsConnector$AnalyticsConnectorListener0) {
        this.zza = analyticsConnector$AnalyticsConnectorListener0;
        this.zzb = appMeasurementSdk0;
        zzf zzf0 = new zzf(this);
        this.zzc = zzf0;
        appMeasurementSdk0.registerOnMeasurementEventListener(zzf0);
    }

    @Override  // com.google.firebase.analytics.connector.internal.zza
    public final AnalyticsConnectorListener zza() {
        return this.zza;
    }

    @Override  // com.google.firebase.analytics.connector.internal.zza
    public final void zzb(Set set0) {
    }

    @Override  // com.google.firebase.analytics.connector.internal.zza
    public final void zzc() {
    }

    static AnalyticsConnectorListener zzd(zzg zzg0) {
        return zzg0.zza;
    }
}

