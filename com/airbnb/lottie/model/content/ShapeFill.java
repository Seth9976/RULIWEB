package com.airbnb.lottie.model.content;

import android.graphics.Path.FillType;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.content.Content;
import com.airbnb.lottie.animation.content.FillContent;
import com.airbnb.lottie.model.animatable.AnimatableColorValue;
import com.airbnb.lottie.model.animatable.AnimatableIntegerValue;
import com.airbnb.lottie.model.layer.BaseLayer;

public class ShapeFill implements ContentModel {
    private final AnimatableColorValue color;
    private final boolean fillEnabled;
    private final Path.FillType fillType;
    private final boolean hidden;
    private final String name;
    private final AnimatableIntegerValue opacity;

    public ShapeFill(String s, boolean z, Path.FillType path$FillType0, AnimatableColorValue animatableColorValue0, AnimatableIntegerValue animatableIntegerValue0, boolean z1) {
        this.name = s;
        this.fillEnabled = z;
        this.fillType = path$FillType0;
        this.color = animatableColorValue0;
        this.opacity = animatableIntegerValue0;
        this.hidden = z1;
    }

    public AnimatableColorValue getColor() {
        return this.color;
    }

    public Path.FillType getFillType() {
        return this.fillType;
    }

    public String getName() {
        return this.name;
    }

    public AnimatableIntegerValue getOpacity() {
        return this.opacity;
    }

    public boolean isHidden() {
        return this.hidden;
    }

    @Override  // com.airbnb.lottie.model.content.ContentModel
    public Content toContent(LottieDrawable lottieDrawable0, BaseLayer baseLayer0) {
        return new FillContent(lottieDrawable0, baseLayer0, this);
    }

    @Override
    public String toString() {
        return "ShapeFill{color=, fillEnabled=" + this.fillEnabled + '}';
    }
}

