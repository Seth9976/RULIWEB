package androidx.core.view;

import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

public final class MenuHostHelper..ExternalSyntheticLambda1 implements LifecycleEventObserver {
    public final MenuHostHelper f$0;
    public final MenuProvider f$1;

    public MenuHostHelper..ExternalSyntheticLambda1(MenuHostHelper menuHostHelper0, MenuProvider menuProvider0) {
        this.f$0 = menuHostHelper0;
        this.f$1 = menuProvider0;
    }

    @Override  // androidx.lifecycle.LifecycleEventObserver
    public final void onStateChanged(LifecycleOwner lifecycleOwner0, Event lifecycle$Event0) {
        this.f$0.lambda$addMenuProvider$0$androidx-core-view-MenuHostHelper(this.f$1, lifecycleOwner0, lifecycle$Event0);
    }
}

