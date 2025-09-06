package com.google.android.material.search;

import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.animation.ValueAnimator;
import android.view.View;

public final class SearchBarAnimationHelper..ExternalSyntheticLambda0 implements ValueAnimator.AnimatorUpdateListener {
    public final View f$0;

    public SearchBarAnimationHelper..ExternalSyntheticLambda0(View view0) {
        this.f$0 = view0;
    }

    @Override  // android.animation.ValueAnimator$AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator0) {
        SearchBarAnimationHelper.lambda$getFadeOutChildrenAnimator$2(this.f$0, valueAnimator0);
    }
}

