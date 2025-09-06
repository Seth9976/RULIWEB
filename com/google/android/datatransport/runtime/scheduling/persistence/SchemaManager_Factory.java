package com.google.android.datatransport.runtime.scheduling.persistence;

import android.content.Context;
import com.google.android.datatransport.runtime.dagger.internal.Factory;
import javax.inject.Provider;

public final class SchemaManager_Factory implements Factory {
    private final Provider contextProvider;
    private final Provider dbNameProvider;
    private final Provider schemaVersionProvider;

    public SchemaManager_Factory(Provider provider0, Provider provider1, Provider provider2) {
        this.contextProvider = provider0;
        this.dbNameProvider = provider1;
        this.schemaVersionProvider = provider2;
    }

    public static SchemaManager_Factory create(Provider provider0, Provider provider1, Provider provider2) {
        return new SchemaManager_Factory(provider0, provider1, provider2);
    }

    public SchemaManager get() {
        return SchemaManager_Factory.newInstance(((Context)this.contextProvider.get()), ((String)this.dbNameProvider.get()), ((int)(((Integer)this.schemaVersionProvider.get()))));
    }

    @Override  // javax.inject.Provider
    public Object get() {
        return this.get();
    }

    public static SchemaManager newInstance(Context context0, String s, int v) {
        return new SchemaManager(context0, s, v);
    }
}

