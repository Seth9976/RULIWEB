package com.airbnb.lottie.animation.keyframe;

import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.LottieFrameInfo;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.Collections;

public class ValueCallbackKeyframeAnimation extends BaseKeyframeAnimation {
    private final LottieFrameInfo frameInfo;
    private final Object valueCallbackValue;

    public ValueCallbackKeyframeAnimation(LottieValueCallback lottieValueCallback0) {
        this(lottieValueCallback0, null);
    }

    public ValueCallbackKeyframeAnimation(LottieValueCallback lottieValueCallback0, Object object0) {
        super(Collections.EMPTY_LIST);
        this.frameInfo = new LottieFrameInfo();
        this.setValueCallback(lottieValueCallback0);
        this.valueCallbackValue = object0;
    }

    @Override  // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    float getEndProgress() {
        return 1.0f;
    }

    @Override  // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    public Object getValue() {
        LottieValueCallback lottieValueCallback0 = this.valueCallback;
        float f = this.getProgress();
        float f1 = this.getProgress();
        float f2 = this.getProgress();
        return lottieValueCallback0.getValueInternal(0.0f, 0.0f, this.valueCallbackValue, this.valueCallbackValue, f, f1, f2);
    }

    @Override  // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    Object getValue(Keyframe keyframe0, float f) {
        return this.getValue();
    }

    @Override  // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    public void notifyListeners() {
        if(this.valueCallback != null) {
            super.notifyListeners();
        }
    }
}

