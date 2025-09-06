package com.google.android.material.search;

import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.animation.ValueAnimator;
import com.google.android.material.internal.FadeThroughDrawable;

public final class SearchViewAnimationHelper..ExternalSyntheticLambda5 implements ValueAnimator.AnimatorUpdateListener {
    public final FadeThroughDrawable f$0;

    public SearchViewAnimationHelper..ExternalSyntheticLambda5(FadeThroughDrawable fadeThroughDrawable0) {
        this.f$0 = fadeThroughDrawable0;
    }

    @Override  // android.animation.ValueAnimator$AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator0) {
        SearchViewAnimationHelper.lambda$addFadeThroughDrawableAnimatorIfNeeded$4(this.f$0, valueAnimator0);
    }
}

