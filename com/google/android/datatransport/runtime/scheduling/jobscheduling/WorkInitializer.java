package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import java.util.concurrent.Executor;
import javax.inject.Inject;

public class WorkInitializer {
    private final Executor executor;
    private final SynchronizationGuard guard;
    private final WorkScheduler scheduler;
    private final EventStore store;

    @Inject
    WorkInitializer(Executor executor0, EventStore eventStore0, WorkScheduler workScheduler0, SynchronizationGuard synchronizationGuard0) {
        this.executor = executor0;
        this.store = eventStore0;
        this.scheduler = workScheduler0;
        this.guard = synchronizationGuard0;
    }

    public void ensureContextsScheduled() {
        WorkInitializer..ExternalSyntheticLambda0 workInitializer$$ExternalSyntheticLambda00 = () -> {
            WorkInitializer..ExternalSyntheticLambda1 workInitializer$$ExternalSyntheticLambda10 = () -> {
                for(Object object0: this.store.loadActiveContexts()) {
                    this.scheduler.schedule(((TransportContext)object0), 1);
                }
                return null;
            };
            this.guard.runCriticalSection(workInitializer$$ExternalSyntheticLambda10);
        };
        this.executor.execute(workInitializer$$ExternalSyntheticLambda00);
    }

    // 检测为 Lambda 实现
    Object lambda$ensureContextsScheduled$0$com-google-android-datatransport-runtime-scheduling-jobscheduling-WorkInitializer() [...]

    // 检测为 Lambda 实现
    void lambda$ensureContextsScheduled$1$com-google-android-datatransport-runtime-scheduling-jobscheduling-WorkInitializer() [...]
}

