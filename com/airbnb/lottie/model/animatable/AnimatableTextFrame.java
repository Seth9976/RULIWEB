package com.airbnb.lottie.model.animatable;

import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
import com.airbnb.lottie.animation.keyframe.TextKeyframeAnimation;
import java.util.List;

public class AnimatableTextFrame extends BaseAnimatableValue {
    public AnimatableTextFrame(List list0) {
        super(list0);
    }

    @Override  // com.airbnb.lottie.model.animatable.AnimatableValue
    public BaseKeyframeAnimation createAnimation() {
        return this.createAnimation();
    }

    public TextKeyframeAnimation createAnimation() {
        return new TextKeyframeAnimation(this.keyframes);
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

