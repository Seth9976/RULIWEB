package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard.CriticalSection;

public final class Uploader..ExternalSyntheticLambda8 implements CriticalSection {
    public final Uploader f$0;
    public final Iterable f$1;

    public Uploader..ExternalSyntheticLambda8(Uploader uploader0, Iterable iterable0) {
        this.f$0 = uploader0;
        this.f$1 = iterable0;
    }

    @Override  // com.google.android.datatransport.runtime.synchronization.SynchronizationGuard$CriticalSection
    public final Object execute() {
        return this.f$0.lambda$logAndUpdateState$5$com-google-android-datatransport-runtime-scheduling-jobscheduling-Uploader(this.f$1);
    }
}

