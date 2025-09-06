package com.google.firebase.components;

import com.google.firebase.inject.Provider;

public final class ComponentRuntime..ExternalSyntheticLambda2 implements Provider {
    public final ComponentRuntime f$0;
    public final Component f$1;

    public ComponentRuntime..ExternalSyntheticLambda2(ComponentRuntime componentRuntime0, Component component0) {
        this.f$0 = componentRuntime0;
        this.f$1 = component0;
    }

    @Override  // com.google.firebase.inject.Provider
    public final Object get() {
        return this.f$0.lambda$discoverComponents$0$com-google-firebase-components-ComponentRuntime(this.f$1);
    }
}

