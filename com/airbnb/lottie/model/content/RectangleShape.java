package com.airbnb.lottie.model.content;

import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.content.Content;
import com.airbnb.lottie.animation.content.RectangleContent;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatablePointValue;
import com.airbnb.lottie.model.animatable.AnimatableValue;
import com.airbnb.lottie.model.layer.BaseLayer;

public class RectangleShape implements ContentModel {
    private final AnimatableFloatValue cornerRadius;
    private final boolean hidden;
    private final String name;
    private final AnimatableValue position;
    private final AnimatablePointValue size;

    public RectangleShape(String s, AnimatableValue animatableValue0, AnimatablePointValue animatablePointValue0, AnimatableFloatValue animatableFloatValue0, boolean z) {
        this.name = s;
        this.position = animatableValue0;
        this.size = animatablePointValue0;
        this.cornerRadius = animatableFloatValue0;
        this.hidden = z;
    }

    public AnimatableFloatValue getCornerRadius() {
        return this.cornerRadius;
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

    @Override  // com.airbnb.lottie.model.content.ContentModel
    public Content toContent(LottieDrawable lottieDrawable0, BaseLayer baseLayer0) {
        return new RectangleContent(lottieDrawable0, baseLayer0, this);
    }

    @Override
    public String toString() {
        return "RectangleShape{position=" + this.position + ", size=" + this.size + '}';
    }
}

