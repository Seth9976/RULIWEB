package com.google.firebase.components;

import com.google.firebase.inject.Deferred.DeferredHandler;
import com.google.firebase.inject.Deferred;
import com.google.firebase.inject.Provider;

class OptionalProvider implements Deferred, Provider {
    private static final Provider EMPTY_PROVIDER;
    private static final DeferredHandler NOOP_HANDLER;
    private volatile Provider delegate;
    private DeferredHandler handler;

    static {
        OptionalProvider.NOOP_HANDLER = new OptionalProvider..ExternalSyntheticLambda0();
        OptionalProvider.EMPTY_PROVIDER = new OptionalProvider..ExternalSyntheticLambda1();
    }

    private OptionalProvider(DeferredHandler deferred$DeferredHandler0, Provider provider0) {
        this.handler = deferred$DeferredHandler0;
        this.delegate = provider0;
    }

    static OptionalProvider empty() {
        return new OptionalProvider(OptionalProvider.NOOP_HANDLER, OptionalProvider.EMPTY_PROVIDER);
    }

    @Override  // com.google.firebase.inject.Provider
    public Object get() {
        return this.delegate.get();
    }

    static void lambda$static$0(Provider provider0) {
    }

    static Object lambda$static$1() [...] // Inlined contents

    // 检测为 Lambda 实现
    static void lambda$whenAvailable$2(DeferredHandler deferred$DeferredHandler0, DeferredHandler deferred$DeferredHandler1, Provider provider0) [...]

    static OptionalProvider of(Provider provider0) {
        return new OptionalProvider(null, provider0);
    }

    void set(Provider provider0) {
        DeferredHandler deferred$DeferredHandler0;
        if(this.delegate != OptionalProvider.EMPTY_PROVIDER) {
            throw new IllegalStateException("provide() can be called only once.");
        }
        synchronized(this) {
            deferred$DeferredHandler0 = this.handler;
            this.handler = null;
            this.delegate = provider0;
        }
        deferred$DeferredHandler0.handle(provider0);
    }

    @Override  // com.google.firebase.inject.Deferred
    public void whenAvailable(DeferredHandler deferred$DeferredHandler0) {
        Provider provider2;
        Provider provider3;
        Provider provider0 = this.delegate;
        Provider provider1 = OptionalProvider.EMPTY_PROVIDER;
        if(provider0 != provider1) {
            deferred$DeferredHandler0.handle(provider0);
            return;
        }
        synchronized(this) {
            provider2 = this.delegate;
            if(provider2 == provider1) {
                this.handler = (Provider provider0) -> {
                    this.handler.handle(provider0);
                    deferred$DeferredHandler0.handle(provider0);
                };
                provider3 = null;
            }
            else {
                provider3 = provider2;
            }
        }
        if(provider3 != null) {
            deferred$DeferredHandler0.handle(provider2);
        }
    }
}

