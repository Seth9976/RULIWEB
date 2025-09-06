package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard.CriticalSection;
import java.util.Map;

public final class Uploader..ExternalSyntheticLambda10 implements CriticalSection {
    public final Uploader f$0;
    public final Map f$1;

    public Uploader..ExternalSyntheticLambda10(Uploader uploader0, Map map0) {
        this.f$0 = uploader0;
        this.f$1 = map0;
    }

    @Override  // com.google.android.datatransport.runtime.synchronization.SynchronizationGuard$CriticalSection
    public final Object execute() {
        return this.f$0.lambda$logAndUpdateState$7$com-google-android-datatransport-runtime-scheduling-jobscheduling-Uploader(this.f$1);
    }
}

