package com.google.android.datatransport.runtime.scheduling;

import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard.CriticalSection;

public final class DefaultScheduler..ExternalSyntheticLambda1 implements CriticalSection {
    public final DefaultScheduler f$0;
    public final TransportContext f$1;
    public final EventInternal f$2;

    public DefaultScheduler..ExternalSyntheticLambda1(DefaultScheduler defaultScheduler0, TransportContext transportContext0, EventInternal eventInternal0) {
        this.f$0 = defaultScheduler0;
        this.f$1 = transportContext0;
        this.f$2 = eventInternal0;
    }

    @Override  // com.google.android.datatransport.runtime.synchronization.SynchronizationGuard$CriticalSection
    public final Object execute() {
        return this.f$0.lambda$schedule$0$com-google-android-datatransport-runtime-scheduling-DefaultScheduler(this.f$1, this.f$2);
    }
}

