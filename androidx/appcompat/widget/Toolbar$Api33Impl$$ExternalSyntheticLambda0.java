package androidx.appcompat.widget;

import android.window.OnBackInvokedCallback;

public final class Toolbar.Api33Impl..ExternalSyntheticLambda0 implements OnBackInvokedCallback {
    public final Runnable f$0;

    public Toolbar.Api33Impl..ExternalSyntheticLambda0(Runnable runnable0) {
        this.f$0 = runnable0;
    }

    @Override  // android.window.OnBackInvokedCallback
    public final void onBackInvoked() {
        this.f$0.run();
    }
}

