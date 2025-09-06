package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard.CriticalSection;

public final class Uploader..ExternalSyntheticLambda9 implements CriticalSection {
    public final Uploader f$0;

    public Uploader..ExternalSyntheticLambda9(Uploader uploader0) {
        this.f$0 = uploader0;
    }

    @Override  // com.google.android.datatransport.runtime.synchronization.SynchronizationGuard$CriticalSection
    public final Object execute() {
        return this.f$0.lambda$logAndUpdateState$6$com-google-android-datatransport-runtime-scheduling-jobscheduling-Uploader();
    }
}

