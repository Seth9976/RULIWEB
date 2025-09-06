package com.google.android.material.timepicker;

import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.animation.ValueAnimator;

public final class ClockHandView..ExternalSyntheticLambda0 implements ValueAnimator.AnimatorUpdateListener {
    public final ClockHandView f$0;

    public ClockHandView..ExternalSyntheticLambda0(ClockHandView clockHandView0) {
        this.f$0 = clockHandView0;
    }

    @Override  // android.animation.ValueAnimator$AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator0) {
        this.f$0.lambda$setHandRotation$0$com-google-android-material-timepicker-ClockHandView(valueAnimator0);
    }
}

