package androidx.core.view;

import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.Lifecycle.State;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

public final class MenuHostHelper..ExternalSyntheticLambda0 implements LifecycleEventObserver {
    public final MenuHostHelper f$0;
    public final State f$1;
    public final MenuProvider f$2;

    public MenuHostHelper..ExternalSyntheticLambda0(MenuHostHelper menuHostHelper0, State lifecycle$State0, MenuProvider menuProvider0) {
        this.f$0 = menuHostHelper0;
        this.f$1 = lifecycle$State0;
        this.f$2 = menuProvider0;
    }

    @Override  // androidx.lifecycle.LifecycleEventObserver
    public final void onStateChanged(LifecycleOwner lifecycleOwner0, Event lifecycle$Event0) {
        this.f$0.lambda$addMenuProvider$1$androidx-core-view-MenuHostHelper(this.f$1, this.f$2, lifecycleOwner0, lifecycle$Event0);
    }
}

