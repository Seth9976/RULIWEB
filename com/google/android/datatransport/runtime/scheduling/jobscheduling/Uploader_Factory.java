package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import android.content.Context;
import com.google.android.datatransport.runtime.backends.BackendRegistry;
import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.scheduling.persistence.ClientHealthMetricsStore;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import com.google.android.datatransport.runtime.time.Clock;
import java.util.concurrent.Executor;
import javax.inject.Provider;

public final class Uploader_Factory implements Factory {
    private final Provider backendRegistryProvider;
    private final Provider clientHealthMetricsStoreProvider;
    private final Provider clockProvider;
    private final Provider contextProvider;
    private final Provider eventStoreProvider;
    private final Provider executorProvider;
    private final Provider guardProvider;
    private final Provider uptimeClockProvider;
    private final Provider workSchedulerProvider;

    public Uploader_Factory(Provider provider0, Provider provider1, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, Provider provider7, Provider provider8) {
        this.contextProvider = provider0;
        this.backendRegistryProvider = provider1;
        this.eventStoreProvider = provider2;
        this.workSchedulerProvider = provider3;
        this.executorProvider = provider4;
        this.guardProvider = provider5;
        this.clockProvider = provider6;
        this.uptimeClockProvider = provider7;
        this.clientHealthMetricsStoreProvider = provider8;
    }

    public static Uploader_Factory create(Provider provider0, Provider provider1, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, Provider provider7, Provider provider8) {
        return new Uploader_Factory(provider0, provider1, provider2, provider3, provider4, provider5, provider6, provider7, provider8);
    }

    public Uploader get() {
        return Uploader_Factory.newInstance(((Context)this.contextProvider.get()), ((BackendRegistry)this.backendRegistryProvider.get()), ((EventStore)this.eventStoreProvider.get()), ((WorkScheduler)this.workSchedulerProvider.get()), ((Executor)this.executorProvider.get()), ((SynchronizationGuard)this.guardProvider.get()), ((Clock)this.clockProvider.get()), ((Clock)this.uptimeClockProvider.get()), ((ClientHealthMetricsStore)this.clientHealthMetricsStoreProvider.get()));
    }

    @Override  // javax.inject.Provider
    public Object get() {
        return this.get();
    }

    public static Uploader newInstance(Context context0, BackendRegistry backendRegistry0, EventStore eventStore0, WorkScheduler workScheduler0, Executor executor0, SynchronizationGuard synchronizationGuard0, Clock clock0, Clock clock1, ClientHealthMetricsStore clientHealthMetricsStore0) {
        return new Uploader(context0, backendRegistry0, eventStore0, workScheduler0, executor0, synchronizationGuard0, clock0, clock1, clientHealthMetricsStore0);
    }
}

