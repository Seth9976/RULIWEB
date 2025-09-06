package com.google.firebase.components;

import com.google.firebase.inject.Provider;

public final class ComponentDiscovery..ExternalSyntheticLambda0 implements Provider {
    public final String f$0;

    public ComponentDiscovery..ExternalSyntheticLambda0(String s) {
        this.f$0 = s;
    }

    @Override  // com.google.firebase.inject.Provider
    public final Object get() {
        return ComponentDiscovery.lambda$discoverLazy$0(this.f$0);
    }
}

