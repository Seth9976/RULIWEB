package com.google.android.datatransport.runtime;

import android.content.Context;
import com.google.android.datatransport.runtime.backends.CreationContextFactory_Factory;
import com.google.android.datatransport.runtime.backends.MetadataBackendRegistry_Factory;
import com.google.android.datatransport.runtime.dagger.internal.DoubleCheck;
import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.dagger.internal.InstanceFactory;
import com.google.android.datatransport.runtime.dagger.internal.Preconditions;
import com.google.android.datatransport.runtime.scheduling.DefaultScheduler_Factory;
import com.google.android.datatransport.runtime.scheduling.SchedulingConfigModule_ConfigFactory;
import com.google.android.datatransport.runtime.scheduling.SchedulingModule_WorkSchedulerFactory;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.Uploader_Factory;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkInitializer_Factory;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStoreModule_DbNameFactory;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStoreModule_PackageNameFactory;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStoreModule_SchemaVersionFactory;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStoreModule_StoreConfigFactory;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore_Factory;
import com.google.android.datatransport.runtime.scheduling.persistence.SchemaManager_Factory;
import com.google.android.datatransport.runtime.time.TimeModule_EventClockFactory;
import com.google.android.datatransport.runtime.time.TimeModule_UptimeClockFactory;
import javax.inject.Provider;

final class DaggerTransportRuntimeComponent extends TransportRuntimeComponent {
    static final class Builder implements com.google.android.datatransport.runtime.TransportRuntimeComponent.Builder {
        private Context setApplicationContext;

        private Builder() {
        }

        Builder(com.google.android.datatransport.runtime.DaggerTransportRuntimeComponent.1 daggerTransportRuntimeComponent$10) {
        }

        @Override  // com.google.android.datatransport.runtime.TransportRuntimeComponent$Builder
        public TransportRuntimeComponent build() {
            Preconditions.checkBuilderRequirement(this.setApplicationContext, Context.class);
            return new DaggerTransportRuntimeComponent(this.setApplicationContext, null);
        }

        public Builder setApplicationContext(Context context0) {
            this.setApplicationContext = (Context)Preconditions.checkNotNull(context0);
            return this;
        }

        @Override  // com.google.android.datatransport.runtime.TransportRuntimeComponent$Builder
        public com.google.android.datatransport.runtime.TransportRuntimeComponent.Builder setApplicationContext(Context context0) {
            return this.setApplicationContext(context0);
        }
    }

    private Provider configProvider;
    private Provider creationContextFactoryProvider;
    private Provider defaultSchedulerProvider;
    private Provider executorProvider;
    private Provider metadataBackendRegistryProvider;
    private Provider packageNameProvider;
    private Provider sQLiteEventStoreProvider;
    private Provider schemaManagerProvider;
    private Provider setApplicationContextProvider;
    private Provider transportRuntimeProvider;
    private Provider uploaderProvider;
    private Provider workInitializerProvider;
    private Provider workSchedulerProvider;

    private DaggerTransportRuntimeComponent(Context context0) {
        this.initialize(context0);
    }

    DaggerTransportRuntimeComponent(Context context0, com.google.android.datatransport.runtime.DaggerTransportRuntimeComponent.1 daggerTransportRuntimeComponent$10) {
        this(context0);
    }

    public static com.google.android.datatransport.runtime.TransportRuntimeComponent.Builder builder() {
        return new Builder(null);
    }

    @Override  // com.google.android.datatransport.runtime.TransportRuntimeComponent
    EventStore getEventStore() {
        return (EventStore)this.sQLiteEventStoreProvider.get();
    }

    @Override  // com.google.android.datatransport.runtime.TransportRuntimeComponent
    TransportRuntime getTransportRuntime() {
        return (TransportRuntime)this.transportRuntimeProvider.get();
    }

    private void initialize(Context context0) {
        this.executorProvider = DoubleCheck.provider(ExecutionModule_ExecutorFactory.create());
        Factory factory0 = InstanceFactory.create(context0);
        this.setApplicationContextProvider = factory0;
        CreationContextFactory_Factory creationContextFactory_Factory0 = CreationContextFactory_Factory.create(factory0, TimeModule_EventClockFactory.create(), TimeModule_UptimeClockFactory.create());
        this.creationContextFactoryProvider = creationContextFactory_Factory0;
        this.metadataBackendRegistryProvider = DoubleCheck.provider(MetadataBackendRegistry_Factory.create(this.setApplicationContextProvider, creationContextFactory_Factory0));
        this.schemaManagerProvider = SchemaManager_Factory.create(this.setApplicationContextProvider, EventStoreModule_DbNameFactory.create(), EventStoreModule_SchemaVersionFactory.create());
        this.packageNameProvider = DoubleCheck.provider(EventStoreModule_PackageNameFactory.create(this.setApplicationContextProvider));
        this.sQLiteEventStoreProvider = DoubleCheck.provider(SQLiteEventStore_Factory.create(TimeModule_EventClockFactory.create(), TimeModule_UptimeClockFactory.create(), EventStoreModule_StoreConfigFactory.create(), this.schemaManagerProvider, this.packageNameProvider));
        SchedulingConfigModule_ConfigFactory schedulingConfigModule_ConfigFactory0 = SchedulingConfigModule_ConfigFactory.create(TimeModule_EventClockFactory.create());
        this.configProvider = schedulingConfigModule_ConfigFactory0;
        SchedulingModule_WorkSchedulerFactory schedulingModule_WorkSchedulerFactory0 = SchedulingModule_WorkSchedulerFactory.create(this.setApplicationContextProvider, this.sQLiteEventStoreProvider, schedulingConfigModule_ConfigFactory0, TimeModule_UptimeClockFactory.create());
        this.workSchedulerProvider = schedulingModule_WorkSchedulerFactory0;
        this.defaultSchedulerProvider = DefaultScheduler_Factory.create(this.executorProvider, this.metadataBackendRegistryProvider, schedulingModule_WorkSchedulerFactory0, this.sQLiteEventStoreProvider, this.sQLiteEventStoreProvider);
        this.uploaderProvider = Uploader_Factory.create(this.setApplicationContextProvider, this.metadataBackendRegistryProvider, this.sQLiteEventStoreProvider, this.workSchedulerProvider, this.executorProvider, this.sQLiteEventStoreProvider, TimeModule_EventClockFactory.create(), TimeModule_UptimeClockFactory.create(), this.sQLiteEventStoreProvider);
        this.workInitializerProvider = WorkInitializer_Factory.create(this.executorProvider, this.sQLiteEventStoreProvider, this.workSchedulerProvider, this.sQLiteEventStoreProvider);
        this.transportRuntimeProvider = DoubleCheck.provider(TransportRuntime_Factory.create(TimeModule_EventClockFactory.create(), TimeModule_UptimeClockFactory.create(), this.defaultSchedulerProvider, this.uploaderProvider, this.workInitializerProvider));
    }

    class com.google.android.datatransport.runtime.DaggerTransportRuntimeComponent.1 {
    }

}

