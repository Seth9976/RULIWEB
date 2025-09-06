package com.airbnb.lottie.model.content;

import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.content.Content;
import com.airbnb.lottie.animation.content.TrimPathContent;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.layer.BaseLayer;

public class ShapeTrimPath implements ContentModel {
    public static enum Type {
        SIMULTANEOUSLY,
        INDIVIDUALLY;

        public static Type forId(int v) {
            switch(v) {
                case 1: {
                    return Type.SIMULTANEOUSLY;
                }
                case 2: {
                    return Type.INDIVIDUALLY;
                }
                default: {
                    throw new IllegalArgumentException("Unknown trim path type " + v);
                }
            }
        }
    }

    private final AnimatableFloatValue end;
    private final boolean hidden;
    private final String name;
    private final AnimatableFloatValue offset;
    private final AnimatableFloatValue start;
    private final Type type;

    public ShapeTrimPath(String s, Type shapeTrimPath$Type0, AnimatableFloatValue animatableFloatValue0, AnimatableFloatValue animatableFloatValue1, AnimatableFloatValue animatableFloatValue2, boolean z) {
        this.name = s;
        this.type = shapeTrimPath$Type0;
        this.start = animatableFloatValue0;
        this.end = animatableFloatValue1;
        this.offset = animatableFloatValue2;
        this.hidden = z;
    }

    public AnimatableFloatValue getEnd() {
        return this.end;
    }

    public String getName() {
        return this.name;
    }

    public AnimatableFloatValue getOffset() {
        return this.offset;
    }

    public AnimatableFloatValue getStart() {
        return this.start;
    }

    public Type getType() {
        return this.type;
    }

    public boolean isHidden() {
        return this.hidden;
    }

    @Override  // com.airbnb.lottie.model.content.ContentModel
    public Content toContent(LottieDrawable lottieDrawable0, BaseLayer baseLayer0) {
        return new TrimPathContent(baseLayer0, this);
    }

    @Override
    public String toString() {
        return "Trim Path: {start: " + this.start + ", end: " + this.end + ", offset: " + this.offset + "}";
    }
}

