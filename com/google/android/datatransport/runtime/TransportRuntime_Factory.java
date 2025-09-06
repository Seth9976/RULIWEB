package com.google.android.datatransport.runtime;

import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.scheduling.Scheduler;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.Uploader;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkInitializer;
import com.google.android.datatransport.runtime.time.Clock;
import javax.inject.Provider;

public final class TransportRuntime_Factory implements Factory {
    private final Provider eventClockProvider;
    private final Provider initializerProvider;
    private final Provider schedulerProvider;
    private final Provider uploaderProvider;
    private final Provider uptimeClockProvider;

    public TransportRuntime_Factory(Provider provider0, Provider provider1, Provider provider2, Provider provider3, Provider provider4) {
        this.eventClockProvider = provider0;
        this.uptimeClockProvider = provider1;
        this.schedulerProvider = provider2;
        this.uploaderProvider = provider3;
        this.initializerProvider = provider4;
    }

    public static TransportRuntime_Factory create(Provider provider0, Provider provider1, Provider provider2, Provider provider3, Provider provider4) {
        return new TransportRuntime_Factory(provider0, provider1, provider2, provider3, provider4);
    }

    public TransportRuntime get() {
        return TransportRuntime_Factory.newInstance(((Clock)this.eventClockProvider.get()), ((Clock)this.uptimeClockProvider.get()), ((Scheduler)this.schedulerProvider.get()), ((Uploader)this.uploaderProvider.get()), ((WorkInitializer)this.initializerProvider.get()));
    }

    @Override  // javax.inject.Provider
    public Object get() {
        return this.get();
    }

    public static TransportRuntime newInstance(Clock clock0, Clock clock1, Scheduler scheduler0, Uploader uploader0, WorkInitializer workInitializer0) {
        return new TransportRuntime(clock0, clock1, scheduler0, uploader0, workInitializer0);
    }
}

