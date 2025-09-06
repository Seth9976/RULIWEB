package androidx.core.view;

import android.view.KeyEvent;
import android.view.View.OnUnhandledKeyEventListener;
import android.view.View;

public final class ViewCompat.Api28Impl..ExternalSyntheticLambda0 implements View.OnUnhandledKeyEventListener {
    public final OnUnhandledKeyEventListenerCompat f$0;

    public ViewCompat.Api28Impl..ExternalSyntheticLambda0(OnUnhandledKeyEventListenerCompat viewCompat$OnUnhandledKeyEventListenerCompat0) {
        this.f$0 = viewCompat$OnUnhandledKeyEventListenerCompat0;
    }

    @Override  // android.view.View$OnUnhandledKeyEventListener
    public final boolean onUnhandledKeyEvent(View view0, KeyEvent keyEvent0) {
        return this.f$0.onUnhandledKeyEvent(view0, keyEvent0);
    }
}

