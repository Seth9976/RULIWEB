package com.google.android.datatransport.runtime.scheduling;

import android.content.Context;
import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.dagger.internal.Preconditions;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkScheduler;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.time.Clock;
import javax.inject.Provider;

public final class SchedulingModule_WorkSchedulerFactory implements Factory {
    private final Provider clockProvider;
    private final Provider configProvider;
    private final Provider contextProvider;
    private final Provider eventStoreProvider;

    public SchedulingModule_WorkSchedulerFactory(Provider provider0, Provider provider1, Provider provider2, Provider provider3) {
        this.contextProvider = provider0;
        this.eventStoreProvider = provider1;
        this.configProvider = provider2;
        this.clockProvider = provider3;
    }

    public static SchedulingModule_WorkSchedulerFactory create(Provider provider0, Provider provider1, Provider provider2, Provider provider3) {
        return new SchedulingModule_WorkSchedulerFactory(provider0, provider1, provider2, provider3);
    }

    public WorkScheduler get() {
        return SchedulingModule_WorkSchedulerFactory.workScheduler(((Context)this.contextProvider.get()), ((EventStore)this.eventStoreProvider.get()), ((SchedulerConfig)this.configProvider.get()), ((Clock)this.clockProvider.get()));
    }

    @Override  // javax.inject.Provider
    public Object get() {
        return this.get();
    }

    public static WorkScheduler workScheduler(Context context0, EventStore eventStore0, SchedulerConfig schedulerConfig0, Clock clock0) {
        return (WorkScheduler)Preconditions.checkNotNull(SchedulingModule.workScheduler(context0, eventStore0, schedulerConfig0, clock0), "Cannot return null from a non-@Nullable @Provides method");
    }
}

