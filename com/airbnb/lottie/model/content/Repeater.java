package com.airbnb.lottie.model.content;

import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.content.Content;
import com.airbnb.lottie.animation.content.RepeaterContent;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableTransform;
import com.airbnb.lottie.model.layer.BaseLayer;

public class Repeater implements ContentModel {
    private final AnimatableFloatValue copies;
    private final boolean hidden;
    private final String name;
    private final AnimatableFloatValue offset;
    private final AnimatableTransform transform;

    public Repeater(String s, AnimatableFloatValue animatableFloatValue0, AnimatableFloatValue animatableFloatValue1, AnimatableTransform animatableTransform0, boolean z) {
        this.name = s;
        this.copies = animatableFloatValue0;
        this.offset = animatableFloatValue1;
        this.transform = animatableTransform0;
        this.hidden = z;
    }

    public AnimatableFloatValue getCopies() {
        return this.copies;
    }

    public String getName() {
        return this.name;
    }

    public AnimatableFloatValue getOffset() {
        return this.offset;
    }

    public AnimatableTransform getTransform() {
        return this.transform;
    }

    public boolean isHidden() {
        return this.hidden;
    }

    @Override  // com.airbnb.lottie.model.content.ContentModel
    public Content toContent(LottieDrawable lottieDrawable0, BaseLayer baseLayer0) {
        return new RepeaterContent(lottieDrawable0, baseLayer0, this);
    }
}

