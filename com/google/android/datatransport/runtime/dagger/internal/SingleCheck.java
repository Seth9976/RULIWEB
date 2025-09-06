package com.google.android.datatransport.runtime.dagger.internal;

import javax.inject.Provider;

public final class SingleCheck implements Provider {
    static final boolean $assertionsDisabled;
    private static final Object UNINITIALIZED;
    private volatile Object instance;
    private volatile Provider provider;

    static {
        SingleCheck.UNINITIALIZED = new Object();
    }

    private SingleCheck(Provider provider0) {
        this.instance = SingleCheck.UNINITIALIZED;
        this.provider = provider0;
    }

    @Override  // javax.inject.Provider
    public Object get() {
        Object object0 = this.instance;
        if(object0 == SingleCheck.UNINITIALIZED) {
            Provider provider0 = this.provider;
            if(provider0 == null) {
                return this.instance;
            }
            object0 = provider0.get();
            this.instance = object0;
            this.provider = null;
        }
        return object0;
    }

    public static Provider provider(Provider provider0) {
        return !(provider0 instanceof SingleCheck) && !(provider0 instanceof DoubleCheck) ? new SingleCheck(((Provider)Preconditions.checkNotNull(provider0))) : provider0;
    }
}

