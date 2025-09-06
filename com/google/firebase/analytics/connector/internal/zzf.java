package com.google.firebase.analytics.connector.internal;

import android.os.Bundle;
import com.google.android.gms.measurement.api.AppMeasurementSdk.OnEventListener;

final class zzf implements OnEventListener {
    final zzg zza;

    public zzf(zzg zzg0) {
        this.zza = zzg0;
        super();
    }

    @Override  // com.google.android.gms.measurement.api.AppMeasurementSdk$OnEventListener
    public final void onEvent(String s, String s1, Bundle bundle0, long v) {
        if(s != null && zzc.zzc(s1)) {
            Bundle bundle1 = new Bundle();
            bundle1.putString("name", s1);
            bundle1.putLong("timestampInMillis", v);
            bundle1.putBundle("params", bundle0);
            zzg.zzd(this.zza).onMessageTriggered(3, bundle1);
        }
    }
}

