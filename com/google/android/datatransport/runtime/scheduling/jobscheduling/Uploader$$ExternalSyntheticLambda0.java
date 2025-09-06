package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard.CriticalSection;

public final class Uploader..ExternalSyntheticLambda0 implements CriticalSection {
    public final EventStore f$0;

    public Uploader..ExternalSyntheticLambda0(EventStore eventStore0) {
        this.f$0 = eventStore0;
    }

    @Override  // com.google.android.datatransport.runtime.synchronization.SynchronizationGuard$CriticalSection
    public final Object execute() {
        return this.f$0.cleanUp();
    }
}

