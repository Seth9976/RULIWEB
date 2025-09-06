package com.google.firebase.components;

import com.google.firebase.inject.Provider;

public final class ComponentRuntime..ExternalSyntheticLambda4 implements Runnable {
    public final LazySet f$0;
    public final Provider f$1;

    public ComponentRuntime..ExternalSyntheticLambda4(LazySet lazySet0, Provider provider0) {
        this.f$0 = lazySet0;
        this.f$1 = provider0;
    }

    @Override
    public final void run() {
        ComponentRuntime.lambda$processSetComponents$3(this.f$0, this.f$1);
    }
}

