package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard.CriticalSection;

public final class Uploader..ExternalSyntheticLambda7 implements CriticalSection {
    public final Uploader f$0;
    public final Iterable f$1;
    public final TransportContext f$2;
    public final long f$3;

    public Uploader..ExternalSyntheticLambda7(Uploader uploader0, Iterable iterable0, TransportContext transportContext0, long v) {
        this.f$0 = uploader0;
        this.f$1 = iterable0;
        this.f$2 = transportContext0;
        this.f$3 = v;
    }

    @Override  // com.google.android.datatransport.runtime.synchronization.SynchronizationGuard$CriticalSection
    public final Object execute() {
        return this.f$0.lambda$logAndUpdateState$4$com-google-android-datatransport-runtime-scheduling-jobscheduling-Uploader(this.f$1, this.f$2, this.f$3);
    }
}

