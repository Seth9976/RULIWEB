package com.google.firebase.analytics.connector.internal;

import android.os.Bundle;
import com.google.android.gms.measurement.api.AppMeasurementSdk.OnEventListener;
import com.google.android.gms.measurement.internal.zzhc;

final class zzd implements OnEventListener {
    final zze zza;

    public zzd(zze zze0) {
        this.zza = zze0;
        super();
    }

    @Override  // com.google.android.gms.measurement.api.AppMeasurementSdk$OnEventListener
    public final void onEvent(String s, String s1, Bundle bundle0, long v) {
        if(!this.zza.zza.contains(s1)) {
            return;
        }
        Bundle bundle1 = new Bundle();
        String s2 = zzhc.zza(s1);
        if(s2 != null) {
            s1 = s2;
        }
        bundle1.putString("events", s1);
        this.zza.zzb.onMessageTriggered(2, bundle1);
    }
}

