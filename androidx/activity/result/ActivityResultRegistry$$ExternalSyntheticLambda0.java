package androidx.activity.result;

import androidx.activity.result.contract.ActivityResultContract;
import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

public final class ActivityResultRegistry..ExternalSyntheticLambda0 implements LifecycleEventObserver {
    public final ActivityResultRegistry f$0;
    public final String f$1;
    public final ActivityResultCallback f$2;
    public final ActivityResultContract f$3;

    public ActivityResultRegistry..ExternalSyntheticLambda0(ActivityResultRegistry activityResultRegistry0, String s, ActivityResultCallback activityResultCallback0, ActivityResultContract activityResultContract0) {
        this.f$0 = activityResultRegistry0;
        this.f$1 = s;
        this.f$2 = activityResultCallback0;
        this.f$3 = activityResultContract0;
    }

    @Override  // androidx.lifecycle.LifecycleEventObserver
    public final void onStateChanged(LifecycleOwner lifecycleOwner0, Event lifecycle$Event0) {
        ActivityResultRegistry.register$lambda$1(this.f$0, this.f$1, this.f$2, this.f$3, lifecycleOwner0, lifecycle$Event0);
    }
}

