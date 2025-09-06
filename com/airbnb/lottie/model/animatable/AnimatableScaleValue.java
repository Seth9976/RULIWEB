package com.airbnb.lottie.model.animatable;

import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.ScaleKeyframeAnimation;
import com.airbnb.lottie.value.ScaleXY;
import java.util.List;

public class AnimatableScaleValue extends BaseAnimatableValue {
    AnimatableScaleValue() {
        this(new ScaleXY(1.0f, 1.0f));
    }

    public AnimatableScaleValue(ScaleXY scaleXY0) {
        super(scaleXY0);
    }

    public AnimatableScaleValue(List list0) {
        super(list0);
    }

    @Override  // com.airbnb.lottie.model.animatable.AnimatableValue
    public BaseKeyframeAnimation createAnimation() {
        return new ScaleKeyframeAnimation(this.keyframes);
    }

    @Override  // com.airbnb.lottie.model.animatable.BaseAnimatableValue
    public List getKeyframes() {
        return super.getKeyframes();
    }

    @Override  // com.airbnb.lottie.model.animatable.BaseAnimatableValue
    public boolean isStatic() {
        return super.isStatic();
    }

    @Override  // com.airbnb.lottie.model.animatable.BaseAnimatableValue
    public String toString() {
        return super.toString();
    }
}

