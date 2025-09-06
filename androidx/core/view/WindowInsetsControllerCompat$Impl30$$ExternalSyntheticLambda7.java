package androidx.core.view;

import android.view.WindowInsetsController.OnControllableInsetsChangedListener;
import android.view.WindowInsetsController;

public final class WindowInsetsControllerCompat.Impl30..ExternalSyntheticLambda7 implements WindowInsetsController.OnControllableInsetsChangedListener {
    public final Impl30 f$0;
    public final OnControllableInsetsChangedListener f$1;

    public WindowInsetsControllerCompat.Impl30..ExternalSyntheticLambda7(Impl30 windowInsetsControllerCompat$Impl300, OnControllableInsetsChangedListener windowInsetsControllerCompat$OnControllableInsetsChangedListener0) {
        this.f$0 = windowInsetsControllerCompat$Impl300;
        this.f$1 = windowInsetsControllerCompat$OnControllableInsetsChangedListener0;
    }

    @Override  // android.view.WindowInsetsController$OnControllableInsetsChangedListener
    public final void onControllableInsetsChanged(WindowInsetsController windowInsetsController0, int v) {
        this.f$0.lambda$addOnControllableInsetsChangedListener$0$androidx-core-view-WindowInsetsControllerCompat$Impl30(this.f$1, windowInsetsController0, v);
    }
}

