package com.airbnb.lottie.model.animatable;

import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.PointKeyframeAnimation;
import java.util.List;

public class AnimatablePointValue extends BaseAnimatableValue {
    public AnimatablePointValue(List list0) {
        super(list0);
    }

    @Override  // com.airbnb.lottie.model.animatable.AnimatableValue
    public BaseKeyframeAnimation createAnimation() {
        return new PointKeyframeAnimation(this.keyframes);
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

