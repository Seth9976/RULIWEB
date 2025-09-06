package com.google.android.datatransport.runtime.scheduling.persistence;

import android.content.Context;
import com.google.android.datatransport.runtime.dagger.Binds;
import com.google.android.datatransport.runtime.dagger.Module;
import com.google.android.datatransport.runtime.dagger.Provides;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import javax.inject.Named;
import javax.inject.Singleton;

@Module
public abstract class EventStoreModule {
    @Binds
    abstract ClientHealthMetricsStore clientHealthMetricsStore(SQLiteEventStore arg1);

    @Provides
    @Named("SQLITE_DB_NAME")
    static String dbName() [...] // Inlined contents

    @Binds
    abstract EventStore eventStore(SQLiteEventStore arg1);

    @Provides
    @Named("PACKAGE_NAME")
    @Singleton
    static String packageName(Context context0) {
        return "com.ruliweb.www.ruliapp";
    }

    @Provides
    @Named("SCHEMA_VERSION")
    static int schemaVersion() [...] // 潜在的解密器

    @Provides
    static EventStoreConfig storeConfig() {
        return EventStoreConfig.DEFAULT;
    }

    @Binds
    abstract SynchronizationGuard synchronizationGuard(SQLiteEventStore arg1);
}

