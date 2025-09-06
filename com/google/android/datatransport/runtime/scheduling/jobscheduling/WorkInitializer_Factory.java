package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import java.util.concurrent.Executor;
import javax.inject.Provider;

public final class WorkInitializer_Factory implements Factory {
    private final Provider executorProvider;
    private final Provider guardProvider;
    private final Provider schedulerProvider;
    private final Provider storeProvider;

    public WorkInitializer_Factory(Provider provider0, Provider provider1, Provider provider2, Provider provider3) {
        this.executorProvider = provider0;
        this.storeProvider = provider1;
        this.schedulerProvider = provider2;
        this.guardProvider = provider3;
    }

    public static WorkInitializer_Factory create(Provider provider0, Provider provider1, Provider provider2, Provider provider3) {
        return new WorkInitializer_Factory(provider0, provider1, provider2, provider3);
    }

    public WorkInitializer get() {
        return WorkInitializer_Factory.newInstance(((Executor)this.executorProvider.get()), ((EventStore)this.storeProvider.get()), ((WorkScheduler)this.schedulerProvider.get()), ((SynchronizationGuard)this.guardProvider.get()));
    }

    @Override  // javax.inject.Provider
    public Object get() {
        return this.get();
    }

    public static WorkInitializer newInstance(Executor executor0, EventStore eventStore0, WorkScheduler workScheduler0, SynchronizationGuard synchronizationGuard0) {
        return new WorkInitializer(executor0, eventStore0, workScheduler0, synchronizationGuard0);
    }
}

