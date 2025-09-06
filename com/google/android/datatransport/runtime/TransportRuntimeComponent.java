package com.google.android.datatransport.runtime;

import android.content.Context;
import com.google.android.datatransport.runtime.backends.BackendRegistryModule;
import com.google.android.datatransport.runtime.dagger.BindsInstance;
import com.google.android.datatransport.runtime.dagger.Component.Builder;
import com.google.android.datatransport.runtime.dagger.Component;
import com.google.android.datatransport.runtime.scheduling.SchedulingConfigModule;
import com.google.android.datatransport.runtime.scheduling.SchedulingModule;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStoreModule;
import com.google.android.datatransport.runtime.time.TimeModule;
import java.io.Closeable;
import java.io.IOException;
import javax.inject.Singleton;

@Component(modules = {BackendRegistryModule.class, EventStoreModule.class, ExecutionModule.class, SchedulingModule.class, SchedulingConfigModule.class, TimeModule.class})
@Singleton
abstract class TransportRuntimeComponent implements Closeable, AutoCloseable {
    @Builder
    interface com.google.android.datatransport.runtime.TransportRuntimeComponent.Builder {
        TransportRuntimeComponent build();

        @BindsInstance
        com.google.android.datatransport.runtime.TransportRuntimeComponent.Builder setApplicationContext(Context arg1);
    }

    @Override
    public void close() throws IOException {
        this.getEventStore().close();
    }

    abstract EventStore getEventStore();

    abstract TransportRuntime getTransportRuntime();
}

