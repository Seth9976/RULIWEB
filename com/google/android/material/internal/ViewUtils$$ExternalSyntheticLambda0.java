package com.google.android.material.internal;

import android.view.View;

public final class ViewUtils..ExternalSyntheticLambda0 implements Runnable {
    public final View f$0;
    public final boolean f$1;

    public ViewUtils..ExternalSyntheticLambda0(View view0, boolean z) {
        this.f$0 = view0;
        this.f$1 = z;
    }

    @Override
    public final void run() {
        ViewUtils.lambda$requestFocusAndShowKeyboard$0(this.f$0, this.f$1);
    }
}

