package androidx.appcompat.app;

import android.view.KeyEvent;
import androidx.core.view.KeyEventDispatcher.Component;

public final class AppCompatDialog..ExternalSyntheticLambda0 implements Component {
    public final AppCompatDialog f$0;

    public AppCompatDialog..ExternalSyntheticLambda0(AppCompatDialog appCompatDialog0) {
        this.f$0 = appCompatDialog0;
    }

    @Override  // androidx.core.view.KeyEventDispatcher$Component
    public final boolean superDispatchKeyEvent(KeyEvent keyEvent0) {
        return this.f$0.superDispatchKeyEvent(keyEvent0);
    }
}

