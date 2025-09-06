package com.google.android.material.search;

import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.animation.ValueAnimator;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;

public final class SearchViewAnimationHelper..ExternalSyntheticLambda3 implements ValueAnimator.AnimatorUpdateListener {
    public final DrawerArrowDrawable f$0;

    public SearchViewAnimationHelper..ExternalSyntheticLambda3(DrawerArrowDrawable drawerArrowDrawable0) {
        this.f$0 = drawerArrowDrawable0;
    }

    @Override  // android.animation.ValueAnimator$AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator0) {
        SearchViewAnimationHelper.lambda$addDrawerArrowDrawableAnimatorIfNeeded$3(this.f$0, valueAnimator0);
    }
}

