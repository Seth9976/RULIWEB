package com.google.android.gms.common.internal.service;

import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.internal.TelemetryData;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zam implements RemoteCall {
    public final TelemetryData zaa;

    public zam(TelemetryData telemetryData0) {
        this.zaa = telemetryData0;
    }

    @Override  // com.google.android.gms.common.api.internal.RemoteCall
    public final void accept(Object object0, Object object1) {
        ((zai)((zap)object0).getService()).zae(this.zaa);
        ((TaskCompletionSource)object1).setResult(null);
    }
}

