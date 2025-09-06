package com.airbnb.lottie.animation.keyframe;

import com.airbnb.lottie.model.content.GradientColor;
import com.airbnb.lottie.value.Keyframe;
import java.util.List;

public class GradientColorKeyframeAnimation extends KeyframeAnimation {
    private final GradientColor gradientColor;

    public GradientColorKeyframeAnimation(List list0) {
        super(list0);
        int v = 0;
        GradientColor gradientColor0 = (GradientColor)((Keyframe)list0.get(0)).startValue;
        if(gradientColor0 != null) {
            v = gradientColor0.getSize();
        }
        this.gradientColor = new GradientColor(new float[v], new int[v]);
    }

    GradientColor getValue(Keyframe keyframe0, float f) {
        this.gradientColor.lerp(((GradientColor)keyframe0.startValue), ((GradientColor)keyframe0.endValue), f);
        return this.gradientColor;
    }

    @Override  // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    Object getValue(Keyframe keyframe0, float f) {
        return this.getValue(keyframe0, f);
    }
}

