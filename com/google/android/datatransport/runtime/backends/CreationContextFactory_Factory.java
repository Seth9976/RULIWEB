package com.google.android.datatransport.runtime.backends;

import android.content.Context;
import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.time.Clock;
import javax.inject.Provider;

public final class CreationContextFactory_Factory implements Factory {
    private final Provider applicationContextProvider;
    private final Provider monotonicClockProvider;
    private final Provider wallClockProvider;

    public CreationContextFactory_Factory(Provider provider0, Provider provider1, Provider provider2) {
        this.applicationContextProvider = provider0;
        this.wallClockProvider = provider1;
        this.monotonicClockProvider = provider2;
    }

    public static CreationContextFactory_Factory create(Provider provider0, Provider provider1, Provider provider2) {
        return new CreationContextFactory_Factory(provider0, provider1, provider2);
    }

    public CreationContextFactory get() {
        return CreationContextFactory_Factory.newInstance(((Context)this.applicationContextProvider.get()), ((Clock)this.wallClockProvider.get()), ((Clock)this.monotonicClockProvider.get()));
    }

    @Override  // javax.inject.Provider
    public Object get() {
        return this.get();
    }

    public static CreationContextFactory newInstance(Context context0, Clock clock0, Clock clock1) {
        return new CreationContextFactory(context0, clock0, clock1);
    }
}

