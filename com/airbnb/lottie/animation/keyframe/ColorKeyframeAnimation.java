package com.airbnb.lottie.animation.keyframe;

import com.airbnb.lottie.utils.GammaEvaluator;
import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.List;

public class ColorKeyframeAnimation extends KeyframeAnimation {
    public ColorKeyframeAnimation(List list0) {
        super(list0);
    }

    public int getIntValue() {
        return this.getIntValue(this.getCurrentKeyframe(), this.getInterpolatedCurrentKeyframeProgress());
    }

    public int getIntValue(Keyframe keyframe0, float f) {
        if(keyframe0.startValue == null || keyframe0.endValue == null) {
            throw new IllegalStateException("Missing values for keyframe.");
        }
        Integer integer0 = (Integer)keyframe0.startValue;
        int v = (int)integer0;
        Integer integer1 = (Integer)keyframe0.endValue;
        int v1 = (int)integer1;
        if(this.valueCallback != null) {
            LottieValueCallback lottieValueCallback0 = this.valueCallback;
            float f1 = (float)keyframe0.endFrame;
            float f2 = this.getLinearCurrentKeyframeProgress();
            float f3 = this.getProgress();
            Integer integer2 = (Integer)lottieValueCallback0.getValueInternal(keyframe0.startFrame, f1, integer0, integer1, f, f2, f3);
            return integer2 == null ? GammaEvaluator.evaluate(MiscUtils.clamp(f, 0.0f, 1.0f), v, v1) : ((int)integer2);
        }
        return GammaEvaluator.evaluate(MiscUtils.clamp(f, 0.0f, 1.0f), v, v1);
    }

    Integer getValue(Keyframe keyframe0, float f) {
        return this.getIntValue(keyframe0, f);
    }

    @Override  // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    Object getValue(Keyframe keyframe0, float f) {
        return this.getValue(keyframe0, f);
    }
}

