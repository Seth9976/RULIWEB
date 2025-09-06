package com.airbnb.lottie.animation.keyframe;

import com.airbnb.lottie.model.DocumentData;
import com.airbnb.lottie.value.Keyframe;
import java.util.List;

public class TextKeyframeAnimation extends KeyframeAnimation {
    public TextKeyframeAnimation(List list0) {
        super(list0);
    }

    DocumentData getValue(Keyframe keyframe0, float f) {
        return (DocumentData)keyframe0.startValue;
    }

    @Override  // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    Object getValue(Keyframe keyframe0, float f) {
        return this.getValue(keyframe0, f);
    }
}

