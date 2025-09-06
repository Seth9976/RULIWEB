package com.google.android.material.search;

import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.animation.ValueAnimator;
import android.graphics.Rect;

public final class SearchViewAnimationHelper..ExternalSyntheticLambda2 implements ValueAnimator.AnimatorUpdateListener {
    public final SearchViewAnimationHelper f$0;
    public final float f$1;
    public final float f$2;
    public final Rect f$3;

    public SearchViewAnimationHelper..ExternalSyntheticLambda2(SearchViewAnimationHelper searchViewAnimationHelper0, float f, float f1, Rect rect0) {
        this.f$0 = searchViewAnimationHelper0;
        this.f$1 = f;
        this.f$2 = f1;
        this.f$3 = rect0;
    }

    @Override  // android.animation.ValueAnimator$AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator0) {
        this.f$0.lambda$getRootViewAnimator$2$com-google-android-material-search-SearchViewAnimationHelper(this.f$1, this.f$2, this.f$3, valueAnimator0);
    }
}

