package com.airbnb.lottie.value;

import android.graphics.PointF;
import android.view.animation.Interpolator;

public class LottieInterpolatedPointValue extends LottieInterpolatedValue {
    private final PointF point;

    public LottieInterpolatedPointValue(PointF pointF0, PointF pointF1) {
        super(pointF0, pointF1);
        this.point = new PointF();
    }

    public LottieInterpolatedPointValue(PointF pointF0, PointF pointF1, Interpolator interpolator0) {
        super(pointF0, pointF1, interpolator0);
        this.point = new PointF();
    }

    PointF interpolateValue(PointF pointF0, PointF pointF1, float f) {
        this.point.set(pointF0.x + f * (pointF1.x - pointF0.x), pointF0.y + f * (pointF1.y - pointF0.y));
        return this.point;
    }

    @Override  // com.airbnb.lottie.value.LottieInterpolatedValue
    Object interpolateValue(Object object0, Object object1, float f) {
        return this.interpolateValue(((PointF)object0), ((PointF)object1), f);
    }
}

