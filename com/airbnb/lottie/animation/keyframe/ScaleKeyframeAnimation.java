package com.airbnb.lottie.animation.keyframe;

import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.LottieValueCallback;
import com.airbnb.lottie.value.ScaleXY;
import java.util.List;

public class ScaleKeyframeAnimation extends KeyframeAnimation {
    private final ScaleXY scaleXY;

    public ScaleKeyframeAnimation(List list0) {
        super(list0);
        this.scaleXY = new ScaleXY();
    }

    public ScaleXY getValue(Keyframe keyframe0, float f) {
        if(keyframe0.startValue == null || keyframe0.endValue == null) {
            throw new IllegalStateException("Missing values for keyframe.");
        }
        ScaleXY scaleXY0 = (ScaleXY)keyframe0.startValue;
        ScaleXY scaleXY1 = (ScaleXY)keyframe0.endValue;
        if(this.valueCallback != null) {
            LottieValueCallback lottieValueCallback0 = this.valueCallback;
            float f1 = (float)keyframe0.endFrame;
            float f2 = this.getLinearCurrentKeyframeProgress();
            float f3 = this.getProgress();
            ScaleXY scaleXY2 = (ScaleXY)lottieValueCallback0.getValueInternal(keyframe0.startFrame, f1, scaleXY0, scaleXY1, f, f2, f3);
            if(scaleXY2 != null) {
                return scaleXY2;
            }
        }
        this.scaleXY.set(MiscUtils.lerp(scaleXY0.getScaleX(), scaleXY1.getScaleX(), f), scaleXY0.getScaleY() + f * (scaleXY1.getScaleY() - scaleXY0.getScaleY()));
        return this.scaleXY;
    }

    @Override  // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    public Object getValue(Keyframe keyframe0, float f) {
        return this.getValue(keyframe0, f);
    }
}

