package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard.CriticalSection;

public final class Uploader..ExternalSyntheticLambda6 implements CriticalSection {
    public final Uploader f$0;
    public final TransportContext f$1;

    public Uploader..ExternalSyntheticLambda6(Uploader uploader0, TransportContext transportContext0) {
        this.f$0 = uploader0;
        this.f$1 = transportContext0;
    }

    @Override  // com.google.android.datatransport.runtime.synchronization.SynchronizationGuard$CriticalSection
    public final Object execute() {
        return this.f$0.lambda$logAndUpdateState$3$com-google-android-datatransport-runtime-scheduling-jobscheduling-Uploader(this.f$1);
    }
}

