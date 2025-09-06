package com.google.android.material.appbar;

import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.animation.ValueAnimator;
import com.google.android.material.shape.MaterialShapeDrawable;

public final class AppBarLayout..ExternalSyntheticLambda2 implements ValueAnimator.AnimatorUpdateListener {
    public final AppBarLayout f$0;
    public final MaterialShapeDrawable f$1;

    public AppBarLayout..ExternalSyntheticLambda2(AppBarLayout appBarLayout0, MaterialShapeDrawable materialShapeDrawable0) {
        this.f$0 = appBarLayout0;
        this.f$1 = materialShapeDrawable0;
    }

    @Override  // android.animation.ValueAnimator$AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator0) {
        this.f$0.lambda$initializeLiftOnScrollWithElevation$1$com-google-android-material-appbar-AppBarLayout(this.f$1, valueAnimator0);
    }
}

