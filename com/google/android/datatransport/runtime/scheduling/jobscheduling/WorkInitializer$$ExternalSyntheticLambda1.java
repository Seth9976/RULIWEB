package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard.CriticalSection;

public final class WorkInitializer..ExternalSyntheticLambda1 implements CriticalSection {
    public final WorkInitializer f$0;

    public WorkInitializer..ExternalSyntheticLambda1(WorkInitializer workInitializer0) {
        this.f$0 = workInitializer0;
    }

    @Override  // com.google.android.datatransport.runtime.synchronization.SynchronizationGuard$CriticalSection
    public final Object execute() {
        return this.f$0.lambda$ensureContextsScheduled$0$com-google-android-datatransport-runtime-scheduling-jobscheduling-WorkInitializer();
    }
}

