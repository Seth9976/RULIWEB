package com.airbnb.lottie.animation.keyframe;

import android.graphics.PointF;
import com.airbnb.lottie.value.Keyframe;
import java.util.Collections;

public class SplitDimensionPathKeyframeAnimation extends BaseKeyframeAnimation {
    private final PointF point;
    private final BaseKeyframeAnimation xAnimation;
    private final BaseKeyframeAnimation yAnimation;

    public SplitDimensionPathKeyframeAnimation(BaseKeyframeAnimation baseKeyframeAnimation0, BaseKeyframeAnimation baseKeyframeAnimation1) {
        super(Collections.EMPTY_LIST);
        this.point = new PointF();
        this.xAnimation = baseKeyframeAnimation0;
        this.yAnimation = baseKeyframeAnimation1;
        this.setProgress(this.getProgress());
    }

    public PointF getValue() {
        return this.getValue(null, 0.0f);
    }

    PointF getValue(Keyframe keyframe0, float f) {
        return this.point;
    }

    @Override  // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    public Object getValue() {
        return this.getValue();
    }

    @Override  // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    Object getValue(Keyframe keyframe0, float f) {
        return this.getValue(keyframe0, f);
    }

    @Override  // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    public void setProgress(float f) {
        this.xAnimation.setProgress(f);
        this.yAnimation.setProgress(f);
        float f1 = (float)(((Float)this.xAnimation.getValue()));
        float f2 = (float)(((Float)this.yAnimation.getValue()));
        this.point.set(f1, f2);
        for(int v = 0; v < this.listeners.size(); ++v) {
            ((AnimationListener)this.listeners.get(v)).onValueChanged();
        }
    }
}

