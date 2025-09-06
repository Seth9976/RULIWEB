package com.airbnb.lottie.model.animatable;

import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.content.Content;
import com.airbnb.lottie.animation.content.ModifierContent;
import com.airbnb.lottie.animation.keyframe.TransformKeyframeAnimation;
import com.airbnb.lottie.model.content.ContentModel;
import com.airbnb.lottie.model.layer.BaseLayer;

public class AnimatableTransform implements ModifierContent, ContentModel {
    private final AnimatablePathValue anchorPoint;
    private final AnimatableFloatValue endOpacity;
    private final AnimatableIntegerValue opacity;
    private final AnimatableValue position;
    private final AnimatableFloatValue rotation;
    private final AnimatableScaleValue scale;
    private final AnimatableFloatValue skew;
    private final AnimatableFloatValue skewAngle;
    private final AnimatableFloatValue startOpacity;

    public AnimatableTransform() {
        this(null, null, null, null, null, null, null, null, null);
    }

    public AnimatableTransform(AnimatablePathValue animatablePathValue0, AnimatableValue animatableValue0, AnimatableScaleValue animatableScaleValue0, AnimatableFloatValue animatableFloatValue0, AnimatableIntegerValue animatableIntegerValue0, AnimatableFloatValue animatableFloatValue1, AnimatableFloatValue animatableFloatValue2, AnimatableFloatValue animatableFloatValue3, AnimatableFloatValue animatableFloatValue4) {
        this.anchorPoint = animatablePathValue0;
        this.position = animatableValue0;
        this.scale = animatableScaleValue0;
        this.rotation = animatableFloatValue0;
        this.opacity = animatableIntegerValue0;
        this.startOpacity = animatableFloatValue1;
        this.endOpacity = animatableFloatValue2;
        this.skew = animatableFloatValue3;
        this.skewAngle = animatableFloatValue4;
    }

    public TransformKeyframeAnimation createAnimation() {
        return new TransformKeyframeAnimation(this);
    }

    public AnimatablePathValue getAnchorPoint() {
        return this.anchorPoint;
    }

    public AnimatableFloatValue getEndOpacity() {
        return this.endOpacity;
    }

    public AnimatableIntegerValue getOpacity() {
        return this.opacity;
    }

    public AnimatableValue getPosition() {
        return this.position;
    }

    public AnimatableFloatValue getRotation() {
        return this.rotation;
    }

    public AnimatableScaleValue getScale() {
        return this.scale;
    }

    public AnimatableFloatValue getSkew() {
        return this.skew;
    }

    public AnimatableFloatValue getSkewAngle() {
        return this.skewAngle;
    }

    public AnimatableFloatValue getStartOpacity() {
        return this.startOpacity;
    }

    @Override  // com.airbnb.lottie.model.content.ContentModel
    public Content toContent(LottieDrawable lottieDrawable0, BaseLayer baseLayer0) {
        return null;
    }
}

