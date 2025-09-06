package com.google.android.material.motion;

import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.animation.ValueAnimator;
import com.google.android.material.internal.ClippableRoundedCornerLayout;

public final class MaterialMainContainerBackHelper..ExternalSyntheticLambda0 implements ValueAnimator.AnimatorUpdateListener {
    public final ClippableRoundedCornerLayout f$0;

    public MaterialMainContainerBackHelper..ExternalSyntheticLambda0(ClippableRoundedCornerLayout clippableRoundedCornerLayout0) {
        this.f$0 = clippableRoundedCornerLayout0;
    }

    @Override  // android.animation.ValueAnimator$AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator0) {
        MaterialMainContainerBackHelper.lambda$createCornerAnimator$0(this.f$0, valueAnimator0);
    }
}

