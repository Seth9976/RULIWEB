package com.google.android.datatransport.runtime.scheduling;

import com.google.android.datatransport.TransportScheduleCallback;
import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.TransportContext;

public final class DefaultScheduler..ExternalSyntheticLambda0 implements Runnable {
    public final DefaultScheduler f$0;
    public final TransportContext f$1;
    public final TransportScheduleCallback f$2;
    public final EventInternal f$3;

    public DefaultScheduler..ExternalSyntheticLambda0(DefaultScheduler defaultScheduler0, TransportContext transportContext0, TransportScheduleCallback transportScheduleCallback0, EventInternal eventInternal0) {
        this.f$0 = defaultScheduler0;
        this.f$1 = transportContext0;
        this.f$2 = transportScheduleCallback0;
        this.f$3 = eventInternal0;
    }

    @Override
    public final void run() {
        this.f$0.lambda$schedule$1$com-google-android-datatransport-runtime-scheduling-DefaultScheduler(this.f$1, this.f$2, this.f$3);
    }
}

