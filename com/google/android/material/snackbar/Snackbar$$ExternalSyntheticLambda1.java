package com.google.android.material.snackbar;

import android.view.View.OnClickListener;
import android.view.View;

public final class Snackbar..ExternalSyntheticLambda1 implements View.OnClickListener {
    public final Snackbar f$0;
    public final View.OnClickListener f$1;

    public Snackbar..ExternalSyntheticLambda1(Snackbar snackbar0, View.OnClickListener view$OnClickListener0) {
        this.f$0 = snackbar0;
        this.f$1 = view$OnClickListener0;
    }

    @Override  // android.view.View$OnClickListener
    public final void onClick(View view0) {
        this.f$0.lambda$setAction$0$com-google-android-material-snackbar-Snackbar(this.f$1, view0);
    }
}

