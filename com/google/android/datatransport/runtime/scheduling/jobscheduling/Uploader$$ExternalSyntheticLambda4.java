package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.scheduling.persistence.ClientHealthMetricsStore;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard.CriticalSection;

public final class Uploader..ExternalSyntheticLambda4 implements CriticalSection {
    public final ClientHealthMetricsStore f$0;

    public Uploader..ExternalSyntheticLambda4(ClientHealthMetricsStore clientHealthMetricsStore0) {
        this.f$0 = clientHealthMetricsStore0;
    }

    @Override  // com.google.android.datatransport.runtime.synchronization.SynchronizationGuard$CriticalSection
    public final Object execute() {
        return this.f$0.loadClientMetrics();
    }
}

