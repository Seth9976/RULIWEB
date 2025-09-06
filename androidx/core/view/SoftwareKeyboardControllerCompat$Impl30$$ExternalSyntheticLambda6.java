package androidx.core.view;

import android.view.WindowInsetsController.OnControllableInsetsChangedListener;
import android.view.WindowInsetsController;
import java.util.concurrent.atomic.AtomicBoolean;

public final class SoftwareKeyboardControllerCompat.Impl30..ExternalSyntheticLambda6 implements WindowInsetsController.OnControllableInsetsChangedListener {
    public final AtomicBoolean f$0;

    public SoftwareKeyboardControllerCompat.Impl30..ExternalSyntheticLambda6(AtomicBoolean atomicBoolean0) {
        this.f$0 = atomicBoolean0;
    }

    @Override  // android.view.WindowInsetsController$OnControllableInsetsChangedListener
    public final void onControllableInsetsChanged(WindowInsetsController windowInsetsController0, int v) {
        Impl30.lambda$hide$0(this.f$0, windowInsetsController0, v);
    }
}

