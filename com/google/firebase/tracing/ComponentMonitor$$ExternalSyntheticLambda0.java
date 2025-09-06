package com.google.firebase.tracing;

import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;

public final class ComponentMonitor..ExternalSyntheticLambda0 implements ComponentFactory {
    public final String f$0;
    public final Component f$1;

    public ComponentMonitor..ExternalSyntheticLambda0(String s, Component component0) {
        this.f$0 = s;
        this.f$1 = component0;
    }

    @Override  // com.google.firebase.components.ComponentFactory
    public final Object create(ComponentContainer componentContainer0) {
        return ComponentMonitor.lambda$processRegistrar$0(this.f$0, this.f$1, componentContainer0);
    }
}

