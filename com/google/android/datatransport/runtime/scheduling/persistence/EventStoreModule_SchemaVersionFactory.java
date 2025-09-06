package com.google.android.datatransport.runtime.scheduling.persistence;

import com.google.android.datatransport.runtime.dagger.internal.Factory;

public final class EventStoreModule_SchemaVersionFactory implements Factory {
    static final class InstanceHolder {
        private static final EventStoreModule_SchemaVersionFactory INSTANCE;

        static {
            InstanceHolder.INSTANCE = new EventStoreModule_SchemaVersionFactory();
        }

        static EventStoreModule_SchemaVersionFactory access$000() {
            return InstanceHolder.INSTANCE;
        }
    }

    public static EventStoreModule_SchemaVersionFactory create() {
        return InstanceHolder.access$000();
    }

    // 去混淆评级： 低(20)
    public Integer get() {
        return 5;
    }

    @Override  // javax.inject.Provider
    public Object get() {
        return this.get();
    }

    // 去混淆评级： 低(20)
    public static int schemaVersion() [...] // 潜在的解密器
}

