package com.google.android.datatransport.runtime.scheduling.persistence;

import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.time.Clock;
import javax.inject.Provider;

public final class SQLiteEventStore_Factory implements Factory {
    private final Provider clockProvider;
    private final Provider configProvider;
    private final Provider packageNameProvider;
    private final Provider schemaManagerProvider;
    private final Provider wallClockProvider;

    public SQLiteEventStore_Factory(Provider provider0, Provider provider1, Provider provider2, Provider provider3, Provider provider4) {
        this.wallClockProvider = provider0;
        this.clockProvider = provider1;
        this.configProvider = provider2;
        this.schemaManagerProvider = provider3;
        this.packageNameProvider = provider4;
    }

    public static SQLiteEventStore_Factory create(Provider provider0, Provider provider1, Provider provider2, Provider provider3, Provider provider4) {
        return new SQLiteEventStore_Factory(provider0, provider1, provider2, provider3, provider4);
    }

    public SQLiteEventStore get() {
        return SQLiteEventStore_Factory.newInstance(((Clock)this.wallClockProvider.get()), ((Clock)this.clockProvider.get()), this.configProvider.get(), this.schemaManagerProvider.get(), this.packageNameProvider);
    }

    @Override  // javax.inject.Provider
    public Object get() {
        return this.get();
    }

    public static SQLiteEventStore newInstance(Clock clock0, Clock clock1, Object object0, Object object1, Provider provider0) {
        return new SQLiteEventStore(clock0, clock1, ((EventStoreConfig)object0), ((SchemaManager)object1), provider0);
    }
}

