package com.google.firebase.heartbeatinfo;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.firebase.components.Qualified;

public final class DefaultHeartBeatController..ExternalSyntheticLambda2 implements ComponentFactory {
    public final Qualified f$0;

    public DefaultHeartBeatController..ExternalSyntheticLambda2(Qualified qualified0) {
        this.f$0 = qualified0;
    }

    @Override  // com.google.firebase.components.ComponentFactory
    public final Object create(ComponentContainer componentContainer0) {
        return DefaultHeartBeatController.lambda$component$3(this.f$0, componentContainer0);
    }
}

