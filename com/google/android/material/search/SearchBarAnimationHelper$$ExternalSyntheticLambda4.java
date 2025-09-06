package com.google.android.material.search;

import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.animation.ValueAnimator;
import android.view.View;
import com.google.android.material.shape.MaterialShapeDrawable;

public final class SearchBarAnimationHelper..ExternalSyntheticLambda4 implements ValueAnimator.AnimatorUpdateListener {
    public final MaterialShapeDrawable f$0;
    public final View f$1;

    public SearchBarAnimationHelper..ExternalSyntheticLambda4(MaterialShapeDrawable materialShapeDrawable0, View view0) {
        this.f$0 = materialShapeDrawable0;
        this.f$1 = view0;
    }

    @Override  // android.animation.ValueAnimator$AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator0) {
        SearchBarAnimationHelper.lambda$getExpandedViewBackgroundUpdateListener$1(this.f$0, this.f$1, valueAnimator0);
    }
}

