package com.google.android.datatransport.runtime.scheduling;

import com.google.android.datatransport.runtime.backends.BackendRegistry;
import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkScheduler;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import java.util.concurrent.Executor;
import javax.inject.Provider;

public final class DefaultScheduler_Factory implements Factory {
    private final Provider backendRegistryProvider;
    private final Provider eventStoreProvider;
    private final Provider executorProvider;
    private final Provider guardProvider;
    private final Provider workSchedulerProvider;

    public DefaultScheduler_Factory(Provider provider0, Provider provider1, Provider provider2, Provider provider3, Provider provider4) {
        this.executorProvider = provider0;
        this.backendRegistryProvider = provider1;
        this.workSchedulerProvider = provider2;
        this.eventStoreProvider = provider3;
        this.guardProvider = provider4;
    }

    public static DefaultScheduler_Factory create(Provider provider0, Provider provider1, Provider provider2, Provider provider3, Provider provider4) {
        return new DefaultScheduler_Factory(provider0, provider1, provider2, provider3, provider4);
    }

    public DefaultScheduler get() {
        return DefaultScheduler_Factory.newInstance(((Executor)this.executorProvider.get()), ((BackendRegistry)this.backendRegistryProvider.get()), ((WorkScheduler)this.workSchedulerProvider.get()), ((EventStore)this.eventStoreProvider.get()), ((SynchronizationGuard)this.guardProvider.get()));
    }

    @Override  // javax.inject.Provider
    public Object get() {
        return this.get();
    }

    public static DefaultScheduler newInstance(Executor executor0, BackendRegistry backendRegistry0, WorkScheduler workScheduler0, EventStore eventStore0, SynchronizationGuard synchronizationGuard0) {
        return new DefaultScheduler(executor0, backendRegistry0, workScheduler0, eventStore0, synchronizationGuard0);
    }
}

