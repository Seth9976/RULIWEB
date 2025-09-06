package com.google.android.datatransport.runtime.backends;

import android.content.Context;
import com.google.android.datatransport.runtime.dagger.internal.Factory;
import javax.inject.Provider;

public final class MetadataBackendRegistry_Factory implements Factory {
    private final Provider applicationContextProvider;
    private final Provider creationContextFactoryProvider;

    public MetadataBackendRegistry_Factory(Provider provider0, Provider provider1) {
        this.applicationContextProvider = provider0;
        this.creationContextFactoryProvider = provider1;
    }

    public static MetadataBackendRegistry_Factory create(Provider provider0, Provider provider1) {
        return new MetadataBackendRegistry_Factory(provider0, provider1);
    }

    public MetadataBackendRegistry get() {
        return MetadataBackendRegistry_Factory.newInstance(((Context)this.applicationContextProvider.get()), this.creationContextFactoryProvider.get());
    }

    @Override  // javax.inject.Provider
    public Object get() {
        return this.get();
    }

    public static MetadataBackendRegistry newInstance(Context context0, Object object0) {
        return new MetadataBackendRegistry(context0, ((CreationContextFactory)object0));
    }
}

