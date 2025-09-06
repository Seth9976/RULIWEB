package androidx.appcompat.app;

import android.window.OnBackInvokedCallback;

public final class AppCompatDelegateImpl.Api33Impl..ExternalSyntheticLambda5 implements OnBackInvokedCallback {
    public final AppCompatDelegateImpl f$0;

    public AppCompatDelegateImpl.Api33Impl..ExternalSyntheticLambda5(AppCompatDelegateImpl appCompatDelegateImpl0) {
        this.f$0 = appCompatDelegateImpl0;
    }

    @Override  // android.window.OnBackInvokedCallback
    public final void onBackInvoked() {
        this.f$0.onBackPressed();
    }
}

