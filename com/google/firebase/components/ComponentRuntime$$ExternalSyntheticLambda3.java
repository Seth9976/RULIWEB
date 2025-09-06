package com.google.firebase.components;

import com.google.firebase.inject.Provider;

public final class ComponentRuntime..ExternalSyntheticLambda3 implements Runnable {
    public final OptionalProvider f$0;
    public final Provider f$1;

    public ComponentRuntime..ExternalSyntheticLambda3(OptionalProvider optionalProvider0, Provider provider0) {
        this.f$0 = optionalProvider0;
        this.f$1 = provider0;
    }

    @Override
    public final void run() {
        ComponentRuntime.lambda$processInstanceComponents$2(this.f$0, this.f$1);
    }
}

