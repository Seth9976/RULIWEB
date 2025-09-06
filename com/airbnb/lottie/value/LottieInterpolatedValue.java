package com.airbnb.lottie.value;

import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

abstract class LottieInterpolatedValue extends LottieValueCallback {
    private final Object endValue;
    private final Interpolator interpolator;
    private final Object startValue;

    LottieInterpolatedValue(Object object0, Object object1) {
        this(object0, object1, new LinearInterpolator());
    }

    LottieInterpolatedValue(Object object0, Object object1, Interpolator interpolator0) {
        this.startValue = object0;
        this.endValue = object1;
        this.interpolator = interpolator0;
    }

    @Override  // com.airbnb.lottie.value.LottieValueCallback
    public Object getValue(LottieFrameInfo lottieFrameInfo0) {
        float f = this.interpolator.getInterpolation(lottieFrameInfo0.getOverallProgress());
        return this.interpolateValue(this.startValue, this.endValue, f);
    }

    abstract Object interpolateValue(Object arg1, Object arg2, float arg3);
}

