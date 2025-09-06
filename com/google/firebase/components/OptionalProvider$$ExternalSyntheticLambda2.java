package com.google.firebase.components;

import com.google.firebase.inject.Deferred.DeferredHandler;
import com.google.firebase.inject.Provider;

public final class OptionalProvider..ExternalSyntheticLambda2 implements DeferredHandler {
    public final DeferredHandler f$0;
    public final DeferredHandler f$1;

    public OptionalProvider..ExternalSyntheticLambda2(DeferredHandler deferred$DeferredHandler0, DeferredHandler deferred$DeferredHandler1) {
        this.f$0 = deferred$DeferredHandler0;
        this.f$1 = deferred$DeferredHandler1;
    }

    @Override  // com.google.firebase.inject.Deferred$DeferredHandler
    public final void handle(Provider provider0) {
        OptionalProvider.lambda$whenAvailable$2(this.f$0, this.f$1, provider0);
    }
}

