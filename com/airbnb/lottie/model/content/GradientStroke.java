package com.airbnb.lottie.model.content;

import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.content.Content;
import com.airbnb.lottie.animation.content.GradientStrokeContent;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableGradientColorValue;
import com.airbnb.lottie.model.animatable.AnimatableIntegerValue;
import com.airbnb.lottie.model.animatable.AnimatablePointValue;
import com.airbnb.lottie.model.layer.BaseLayer;
import java.util.List;

public class GradientStroke implements ContentModel {
    private final LineCapType capType;
    private final AnimatableFloatValue dashOffset;
    private final AnimatablePointValue endPoint;
    private final AnimatableGradientColorValue gradientColor;
    private final GradientType gradientType;
    private final boolean hidden;
    private final LineJoinType joinType;
    private final List lineDashPattern;
    private final float miterLimit;
    private final String name;
    private final AnimatableIntegerValue opacity;
    private final AnimatablePointValue startPoint;
    private final AnimatableFloatValue width;

    public GradientStroke(String s, GradientType gradientType0, AnimatableGradientColorValue animatableGradientColorValue0, AnimatableIntegerValue animatableIntegerValue0, AnimatablePointValue animatablePointValue0, AnimatablePointValue animatablePointValue1, AnimatableFloatValue animatableFloatValue0, LineCapType shapeStroke$LineCapType0, LineJoinType shapeStroke$LineJoinType0, float f, List list0, AnimatableFloatValue animatableFloatValue1, boolean z) {
        this.name = s;
        this.gradientType = gradientType0;
        this.gradientColor = animatableGradientColorValue0;
        this.opacity = animatableIntegerValue0;
        this.startPoint = animatablePointValue0;
        this.endPoint = animatablePointValue1;
        this.width = animatableFloatValue0;
        this.capType = shapeStroke$LineCapType0;
        this.joinType = shapeStroke$LineJoinType0;
        this.miterLimit = f;
        this.lineDashPattern = list0;
        this.dashOffset = animatableFloatValue1;
        this.hidden = z;
    }

    public LineCapType getCapType() {
        return this.capType;
    }

    public AnimatableFloatValue getDashOffset() {
        return this.dashOffset;
    }

    public AnimatablePointValue getEndPoint() {
        return this.endPoint;
    }

    public AnimatableGradientColorValue getGradientColor() {
        return this.gradientColor;
    }

    public GradientType getGradientType() {
        return this.gradientType;
    }

    public LineJoinType getJoinType() {
        return this.joinType;
    }

    public List getLineDashPattern() {
        return this.lineDashPattern;
    }

    public float getMiterLimit() {
        return this.miterLimit;
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

    public AnimatableFloatValue getWidth() {
        return this.width;
    }

    public boolean isHidden() {
        return this.hidden;
    }

    @Override  // com.airbnb.lottie.model.content.ContentModel
    public Content toContent(LottieDrawable lottieDrawable0, BaseLayer baseLayer0) {
        return new GradientStrokeContent(lottieDrawable0, baseLayer0, this);
    }
}

