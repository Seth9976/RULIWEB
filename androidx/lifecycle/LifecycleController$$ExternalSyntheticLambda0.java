package androidx.lifecycle;

import kotlinx.coroutines.Job;

public final class LifecycleController..ExternalSyntheticLambda0 implements LifecycleEventObserver {
    public final LifecycleController f$0;
    public final Job f$1;

    public LifecycleController..ExternalSyntheticLambda0(LifecycleController lifecycleController0, Job job0) {
        this.f$0 = lifecycleController0;
        this.f$1 = job0;
    }

    @Override  // androidx.lifecycle.LifecycleEventObserver
    public final void onStateChanged(LifecycleOwner lifecycleOwner0, Event lifecycle$Event0) {
        LifecycleController.observer$lambda$0(this.f$0, this.f$1, lifecycleOwner0, lifecycle$Event0);
    }
}

