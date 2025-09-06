package com.airbnb.lottie.value;

import android.view.animation.Interpolator;
import com.airbnb.lottie.utils.MiscUtils;

public class LottieInterpolatedFloatValue extends LottieInterpolatedValue {
    public LottieInterpolatedFloatValue(Float float0, Float float1) {
        super(float0, float1);
    }

    public LottieInterpolatedFloatValue(Float float0, Float float1, Interpolator interpolator0) {
        super(float0, float1, interpolator0);
    }

    Float interpolateValue(Float float0, Float float1, float f) {
        return MiscUtils.lerp(((float)float0), ((float)float1), f);
    }

    @Override  // com.airbnb.lottie.value.LottieInterpolatedValue
    Object interpolateValue(Object object0, Object object1, float f) {
        return this.interpolateValue(((Float)object0), ((Float)object1), f);
    }
}

