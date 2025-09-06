package com.google.android.datatransport.runtime.scheduling.persistence;

import android.content.Context;
import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.dagger.internal.Preconditions;
import javax.inject.Provider;

public final class EventStoreModule_PackageNameFactory implements Factory {
    private final Provider contextProvider;

    public EventStoreModule_PackageNameFactory(Provider provider0) {
        this.contextProvider = provider0;
    }

    public static EventStoreModule_PackageNameFactory create(Provider provider0) {
        return new EventStoreModule_PackageNameFactory(provider0);
    }

    @Override  // javax.inject.Provider
    public Object get() {
        return this.get();
    }

    public String get() {
        return EventStoreModule_PackageNameFactory.packageName(((Context)this.contextProvider.get()));
    }

    public static String packageName(Context context0) {
        return (String)Preconditions.checkNotNull(EventStoreModule.packageName(context0), "Cannot return null from a non-@Nullable @Provides method");
    }
}

