package com.airbnb.lottie.animation.keyframe;

import android.graphics.PointF;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.List;

public class PointKeyframeAnimation extends KeyframeAnimation {
    private final PointF point;

    public PointKeyframeAnimation(List list0) {
        super(list0);
        this.point = new PointF();
    }

    public PointF getValue(Keyframe keyframe0, float f) {
        if(keyframe0.startValue == null || keyframe0.endValue == null) {
            throw new IllegalStateException("Missing values for keyframe.");
        }
        PointF pointF0 = (PointF)keyframe0.startValue;
        PointF pointF1 = (PointF)keyframe0.endValue;
        if(this.valueCallback != null) {
            LottieValueCallback lottieValueCallback0 = this.valueCallback;
            float f1 = (float)keyframe0.endFrame;
            float f2 = this.getLinearCurrentKeyframeProgress();
            float f3 = this.getProgress();
            PointF pointF2 = (PointF)lottieValueCallback0.getValueInternal(keyframe0.startFrame, f1, pointF0, pointF1, f, f2, f3);
            if(pointF2 != null) {
                return pointF2;
            }
        }
        this.point.set(pointF0.x + (pointF1.x - pointF0.x) * f, pointF0.y + (pointF1.y - pointF0.y) * f);
        return this.point;
    }

    @Override  // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    public Object getValue(Keyframe keyframe0, float f) {
        return this.getValue(keyframe0, f);
    }
}

