package com.google.android.datatransport.runtime.scheduling.persistence;

import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.dagger.internal.Preconditions;

public final class EventStoreModule_StoreConfigFactory implements Factory {
    static final class InstanceHolder {
        private static final EventStoreModule_StoreConfigFactory INSTANCE;

        static {
            InstanceHolder.INSTANCE = new EventStoreModule_StoreConfigFactory();
        }

        static EventStoreModule_StoreConfigFactory access$000() {
            return InstanceHolder.INSTANCE;
        }
    }

    public static EventStoreModule_StoreConfigFactory create() {
        return InstanceHolder.access$000();
    }

    public EventStoreConfig get() {
        return EventStoreModule_StoreConfigFactory.storeConfig();
    }

    @Override  // javax.inject.Provider
    public Object get() {
        return this.get();
    }

    public static EventStoreConfig storeConfig() {
        return (EventStoreConfig)Preconditions.checkNotNull(EventStoreModule.storeConfig(), "Cannot return null from a non-@Nullable @Provides method");
    }
}

