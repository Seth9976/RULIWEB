package androidx.constraintlayout.motion.widget;

import android.view.View;

public final class ViewTransition..ExternalSyntheticLambda0 implements Runnable {
    public final ViewTransition f$0;
    public final View[] f$1;

    public ViewTransition..ExternalSyntheticLambda0(ViewTransition viewTransition0, View[] arr_view) {
        this.f$0 = viewTransition0;
        this.f$1 = arr_view;
    }

    @Override
    public final void run() {
        this.f$0.lambda$applyTransition$0$androidx-constraintlayout-motion-widget-ViewTransition(this.f$1);
    }
}

