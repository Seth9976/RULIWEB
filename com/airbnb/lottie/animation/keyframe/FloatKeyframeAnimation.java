package com.airbnb.lottie.animation.keyframe;

import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.List;

public class FloatKeyframeAnimation extends KeyframeAnimation {
    public FloatKeyframeAnimation(List list0) {
        super(list0);
    }

    public float getFloatValue() {
        return this.getFloatValue(this.getCurrentKeyframe(), this.getInterpolatedCurrentKeyframeProgress());
    }

    float getFloatValue(Keyframe keyframe0, float f) {
        if(keyframe0.startValue == null || keyframe0.endValue == null) {
            throw new IllegalStateException("Missing values for keyframe.");
        }
        if(this.valueCallback != null) {
            LottieValueCallback lottieValueCallback0 = this.valueCallback;
            float f1 = (float)keyframe0.endFrame;
            Object object0 = keyframe0.endValue;
            float f2 = this.getLinearCurrentKeyframeProgress();
            float f3 = this.getProgress();
            Float float0 = (Float)lottieValueCallback0.getValueInternal(keyframe0.startFrame, f1, keyframe0.startValue, object0, f, f2, f3);
            return float0 == null ? MiscUtils.lerp(keyframe0.getStartValueFloat(), keyframe0.getEndValueFloat(), f) : ((float)float0);
        }
        return MiscUtils.lerp(keyframe0.getStartValueFloat(), keyframe0.getEndValueFloat(), f);
    }

    Float getValue(Keyframe keyframe0, float f) {
        return this.getFloatValue(keyframe0, f);
    }

    @Override  // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    Object getValue(Keyframe keyframe0, float f) {
        return this.getValue(keyframe0, f);
    }
}

