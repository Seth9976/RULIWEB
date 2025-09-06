package com.google.android.material.internal;

import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.animation.ValueAnimator;
import android.graphics.Rect;

public final class ExpandCollapseAnimationHelper..ExternalSyntheticLambda0 implements ValueAnimator.AnimatorUpdateListener {
    public final ExpandCollapseAnimationHelper f$0;
    public final Rect f$1;

    public ExpandCollapseAnimationHelper..ExternalSyntheticLambda0(ExpandCollapseAnimationHelper expandCollapseAnimationHelper0, Rect rect0) {
        this.f$0 = expandCollapseAnimationHelper0;
        this.f$1 = rect0;
    }

    @Override  // android.animation.ValueAnimator$AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator0) {
        this.f$0.lambda$getExpandCollapseAnimator$0$com-google-android-material-internal-ExpandCollapseAnimationHelper(this.f$1, valueAnimator0);
    }
}

