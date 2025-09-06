package com.airbnb.lottie.value;

import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;

public class LottieValueCallback {
    private BaseKeyframeAnimation animation;
    private final LottieFrameInfo frameInfo;
    protected Object value;

    public LottieValueCallback() {
        this.frameInfo = new LottieFrameInfo();
        this.value = null;
    }

    public LottieValueCallback(Object object0) {
        this.frameInfo = new LottieFrameInfo();
        this.value = object0;
    }

    public Object getValue(LottieFrameInfo lottieFrameInfo0) {
        return this.value;
    }

    public final Object getValueInternal(float f, float f1, Object object0, Object object1, float f2, float f3, float f4) {
        return this.getValue(this.frameInfo.set(f, f1, object0, object1, f2, f3, f4));
    }

    public final void setAnimation(BaseKeyframeAnimation baseKeyframeAnimation0) {
        this.animation = baseKeyframeAnimation0;
    }

    public final void setValue(Object object0) {
        this.value = object0;
        BaseKeyframeAnimation baseKeyframeAnimation0 = this.animation;
        if(baseKeyframeAnimation0 != null) {
            baseKeyframeAnimation0.notifyListeners();
        }
    }
}

