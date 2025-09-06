package com.google.android.material.internal;

import android.animation.TimeInterpolator;

public class ReversableAnimatedValueInterpolator implements TimeInterpolator {
    private final TimeInterpolator sourceInterpolator;

    public ReversableAnimatedValueInterpolator(TimeInterpolator timeInterpolator0) {
        this.sourceInterpolator = timeInterpolator0;
    }

    @Override  // android.animation.TimeInterpolator
    public float getInterpolation(float f) {
        return 1.0f - this.sourceInterpolator.getInterpolation(f);
    }

    public static TimeInterpolator of(boolean z, TimeInterpolator timeInterpolator0) {
        return z ? timeInterpolator0 : new ReversableAnimatedValueInterpolator(timeInterpolator0);
    }
}

