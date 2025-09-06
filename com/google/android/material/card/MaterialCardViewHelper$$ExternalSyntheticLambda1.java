package com.google.android.material.card;

import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.animation.ValueAnimator;

public final class MaterialCardViewHelper..ExternalSyntheticLambda1 implements ValueAnimator.AnimatorUpdateListener {
    public final MaterialCardViewHelper f$0;

    public MaterialCardViewHelper..ExternalSyntheticLambda1(MaterialCardViewHelper materialCardViewHelper0) {
        this.f$0 = materialCardViewHelper0;
    }

    @Override  // android.animation.ValueAnimator$AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator0) {
        this.f$0.lambda$animateCheckedIcon$0$com-google-android-material-card-MaterialCardViewHelper(valueAnimator0);
    }
}

