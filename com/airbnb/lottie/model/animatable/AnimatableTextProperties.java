package com.airbnb.lottie.model.animatable;

public class AnimatableTextProperties {
    public final AnimatableColorValue color;
    public final AnimatableColorValue stroke;
    public final AnimatableFloatValue strokeWidth;
    public final AnimatableFloatValue tracking;

    public AnimatableTextProperties(AnimatableColorValue animatableColorValue0, AnimatableColorValue animatableColorValue1, AnimatableFloatValue animatableFloatValue0, AnimatableFloatValue animatableFloatValue1) {
        this.color = animatableColorValue0;
        this.stroke = animatableColorValue1;
        this.strokeWidth = animatableFloatValue0;
        this.tracking = animatableFloatValue1;
    }
}

