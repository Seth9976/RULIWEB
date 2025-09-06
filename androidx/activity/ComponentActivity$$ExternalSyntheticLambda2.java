package androidx.activity;

import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

public final class ComponentActivity..ExternalSyntheticLambda2 implements LifecycleEventObserver {
    public final ComponentActivity f$0;

    public ComponentActivity..ExternalSyntheticLambda2(ComponentActivity componentActivity0) {
        this.f$0 = componentActivity0;
    }

    @Override  // androidx.lifecycle.LifecycleEventObserver
    public final void onStateChanged(LifecycleOwner lifecycleOwner0, Event lifecycle$Event0) {
        ComponentActivity._init_$lambda$3(this.f$0, lifecycleOwner0, lifecycle$Event0);
    }
}

