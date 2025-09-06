package androidx.activity;

import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

public final class ComponentActivity..ExternalSyntheticLambda5 implements LifecycleEventObserver {
    public final OnBackPressedDispatcher f$0;
    public final ComponentActivity f$1;

    public ComponentActivity..ExternalSyntheticLambda5(OnBackPressedDispatcher onBackPressedDispatcher0, ComponentActivity componentActivity0) {
        this.f$0 = onBackPressedDispatcher0;
        this.f$1 = componentActivity0;
    }

    @Override  // androidx.lifecycle.LifecycleEventObserver
    public final void onStateChanged(LifecycleOwner lifecycleOwner0, Event lifecycle$Event0) {
        ComponentActivity.addObserverForBackInvoker$lambda$7(this.f$0, this.f$1, lifecycleOwner0, lifecycle$Event0);
    }
}

