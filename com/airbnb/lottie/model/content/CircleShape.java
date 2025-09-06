package com.airbnb.lottie.model.content;

import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.content.Content;
import com.airbnb.lottie.animation.content.EllipseContent;
import com.airbnb.lottie.model.animatable.AnimatablePointValue;
import com.airbnb.lottie.model.animatable.AnimatableValue;
import com.airbnb.lottie.model.layer.BaseLayer;

public class CircleShape implements ContentModel {
    private final boolean hidden;
    private final boolean isReversed;
    private final String name;
    private final AnimatableValue position;
    private final AnimatablePointValue size;

    public CircleShape(String s, AnimatableValue animatableValue0, AnimatablePointValue animatablePointValue0, boolean z, boolean z1) {
        this.name = s;
        this.position = animatableValue0;
        this.size = animatablePointValue0;
        this.isReversed = z;
        this.hidden = z1;
    }

    public String getName() {
        return this.name;
    }

    public AnimatableValue getPosition() {
        return this.position;
    }

    public AnimatablePointValue getSize() {
        return this.size;
    }

    public boolean isHidden() {
        return this.hidden;
    }

    public boolean isReversed() {
        return this.isReversed;
    }

    @Override  // com.airbnb.lottie.model.content.ContentModel
    public Content toContent(LottieDrawable lottieDrawable0, BaseLayer baseLayer0) {
        return new EllipseContent(lottieDrawable0, baseLayer0, this);
    }
}

