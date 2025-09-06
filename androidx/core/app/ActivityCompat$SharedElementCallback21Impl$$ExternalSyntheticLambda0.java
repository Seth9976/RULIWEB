package androidx.core.app;

import android.app.SharedElementCallback.OnSharedElementsReadyListener;

public final class ActivityCompat.SharedElementCallback21Impl..ExternalSyntheticLambda0 implements OnSharedElementsReadyListener {
    public final SharedElementCallback.OnSharedElementsReadyListener f$0;

    public ActivityCompat.SharedElementCallback21Impl..ExternalSyntheticLambda0(SharedElementCallback.OnSharedElementsReadyListener sharedElementCallback$OnSharedElementsReadyListener0) {
        this.f$0 = sharedElementCallback$OnSharedElementsReadyListener0;
    }

    @Override  // androidx.core.app.SharedElementCallback$OnSharedElementsReadyListener
    public final void onSharedElementsReady() {
        Api23Impl.onSharedElementsReady(this.f$0);
    }
}

