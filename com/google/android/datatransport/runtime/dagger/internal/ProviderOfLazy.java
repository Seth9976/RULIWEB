package com.google.android.datatransport.runtime.dagger.internal;

import com.google.android.datatransport.runtime.dagger.Lazy;
import javax.inject.Provider;

public final class ProviderOfLazy implements Provider {
    static final boolean $assertionsDisabled;
    private final Provider provider;

    static {
    }

    private ProviderOfLazy(Provider provider0) {
        this.provider = provider0;
    }

    public static Provider create(Provider provider0) {
        return new ProviderOfLazy(((Provider)Preconditions.checkNotNull(provider0)));
    }

    public Lazy get() {
        return DoubleCheck.lazy(this.provider);
    }

    @Override  // javax.inject.Provider
    public Object get() {
        return this.get();
    }
}

