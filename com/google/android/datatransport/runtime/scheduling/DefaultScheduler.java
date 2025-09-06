package com.google.android.datatransport.runtime.scheduling;

import com.google.android.datatransport.TransportScheduleCallback;
import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.TransportRuntime;
import com.google.android.datatransport.runtime.backends.BackendRegistry;
import com.google.android.datatransport.runtime.backends.TransportBackend;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkScheduler;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import java.util.concurrent.Executor;
import java.util.logging.Logger;
import javax.inject.Inject;

public class DefaultScheduler implements Scheduler {
    private static final Logger LOGGER;
    private final BackendRegistry backendRegistry;
    private final EventStore eventStore;
    private final Executor executor;
    private final SynchronizationGuard guard;
    private final WorkScheduler workScheduler;

    static {
        DefaultScheduler.LOGGER = Logger.getLogger(TransportRuntime.class.getName());
    }

    @Inject
    public DefaultScheduler(Executor executor0, BackendRegistry backendRegistry0, WorkScheduler workScheduler0, EventStore eventStore0, SynchronizationGuard synchronizationGuard0) {
        this.executor = executor0;
        this.backendRegistry = backendRegistry0;
        this.workScheduler = workScheduler0;
        this.eventStore = eventStore0;
        this.guard = synchronizationGuard0;
    }

    // 检测为 Lambda 实现
    Object lambda$schedule$0$com-google-android-datatransport-runtime-scheduling-DefaultScheduler(TransportContext transportContext0, EventInternal eventInternal0) [...]

    // 检测为 Lambda 实现
    void lambda$schedule$1$com-google-android-datatransport-runtime-scheduling-DefaultScheduler(TransportContext transportContext0, TransportScheduleCallback transportScheduleCallback0, EventInternal eventInternal0) [...]

    @Override  // com.google.android.datatransport.runtime.scheduling.Scheduler
    public void schedule(TransportContext transportContext0, EventInternal eventInternal0, TransportScheduleCallback transportScheduleCallback0) {
        DefaultScheduler..ExternalSyntheticLambda0 defaultScheduler$$ExternalSyntheticLambda00 = () -> try {
            String s = transportContext0.getBackendName();
            TransportBackend transportBackend0 = this.backendRegistry.get(s);
            if(transportBackend0 == null) {
                String s1 = String.format("Transport backend \'%s\' is not registered", transportContext0.getBackendName());
                DefaultScheduler.LOGGER.warning(s1);
                transportScheduleCallback0.onSchedule(new IllegalArgumentException(s1));
                return;
            }
            DefaultScheduler..ExternalSyntheticLambda1 defaultScheduler$$ExternalSyntheticLambda10 = () -> {
                this.eventStore.persist(transportContext0, transportBackend0.decorate(eventInternal0));
                this.workScheduler.schedule(transportContext0, 1);
                return null;
            };
            this.guard.runCriticalSection(defaultScheduler$$ExternalSyntheticLambda10);
            transportScheduleCallback0.onSchedule(null);
        }
        catch(Exception exception0) {
            DefaultScheduler.LOGGER.warning("Error scheduling event " + exception0.getMessage());
            transportScheduleCallback0.onSchedule(exception0);
        };
        this.executor.execute(defaultScheduler$$ExternalSyntheticLambda00);
    }
}

