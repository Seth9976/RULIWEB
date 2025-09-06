package com.airbnb.lottie.model.content;

import android.graphics.Path.FillType;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.content.Content;
import com.airbnb.lottie.animation.content.GradientFillContent;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableGradientColorValue;
import com.airbnb.lottie.model.animatable.AnimatableIntegerValue;
import com.airbnb.lottie.model.animatable.AnimatablePointValue;
import com.airbnb.lottie.model.layer.BaseLayer;

public class GradientFill implements ContentModel {
    private final AnimatablePointValue endPoint;
    private final Path.FillType fillType;
    private final AnimatableGradientColorValue gradientColor;
    private final GradientType gradientType;
    private final boolean hidden;
    private final AnimatableFloatValue highlightAngle;
    private final AnimatableFloatValue highlightLength;
    private final String name;
    private final AnimatableIntegerValue opacity;
    private final AnimatablePointValue startPoint;

    public GradientFill(String s, GradientType gradientType0, Path.FillType path$FillType0, AnimatableGradientColorValue animatableGradientColorValue0, AnimatableIntegerValue animatableIntegerValue0, AnimatablePointValue animatablePointValue0, AnimatablePointValue animatablePointValue1, AnimatableFloatValue animatableFloatValue0, AnimatableFloatValue animatableFloatValue1, boolean z) {
        this.gradientType = gradientType0;
        this.fillType = path$FillType0;
        this.gradientColor = animatableGradientColorValue0;
        this.opacity = animatableIntegerValue0;
        this.startPoint = animatablePointValue0;
        this.endPoint = animatablePointValue1;
        this.name = s;
        this.highlightLength = animatableFloatValue0;
        this.highlightAngle = animatableFloatValue1;
        this.hidden = z;
    }

    public AnimatablePointValue getEndPoint() {
        return this.endPoint;
    }

    public Path.FillType getFillType() {
        return this.fillType;
    }

    public AnimatableGradientColorValue getGradientColor() {
        return this.gradientColor;
    }

    public GradientType getGradientType() {
        return this.gradientType;
    }

    AnimatableFloatValue getHighlightAngle() {
        return this.highlightAngle;
    }

    AnimatableFloatValue getHighlightLength() {
        return this.highlightLength;
    }

    public String getName() {
        return this.name;
    }

    public AnimatableIntegerValue getOpacity() {
        return this.opacity;
    }

    public AnimatablePointValue getStartPoint() {
        return this.startPoint;
    }

    public boolean isHidden() {
        return this.hidden;
    }

    @Override  // com.airbnb.lottie.model.content.ContentModel
    public Content toContent(LottieDrawable lottieDrawable0, BaseLayer baseLayer0) {
        return new GradientFillContent(lottieDrawable0, baseLayer0, this);
    }
}

