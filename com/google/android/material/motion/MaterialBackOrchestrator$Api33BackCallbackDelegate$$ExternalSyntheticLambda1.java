package com.google.android.material.motion;

import android.window.OnBackInvokedCallback;

public final class MaterialBackOrchestrator.Api33BackCallbackDelegate..ExternalSyntheticLambda1 implements OnBackInvokedCallback {
    public final MaterialBackHandler f$0;

    public MaterialBackOrchestrator.Api33BackCallbackDelegate..ExternalSyntheticLambda1(MaterialBackHandler materialBackHandler0) {
        this.f$0 = materialBackHandler0;
    }

    @Override  // android.window.OnBackInvokedCallback
    public final void onBackInvoked() {
        this.f$0.handleBackInvoked();
    }
}

