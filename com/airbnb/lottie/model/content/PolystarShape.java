package com.airbnb.lottie.model.content;

import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.content.Content;
import com.airbnb.lottie.animation.content.PolystarContent;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableValue;
import com.airbnb.lottie.model.layer.BaseLayer;

public class PolystarShape implements ContentModel {
    public static enum Type {
        STAR(1),
        POLYGON(2);

        private final int value;

        private Type(int v1) {
            this.value = v1;
        }

        public static Type forValue(int v) {
            Type[] arr_polystarShape$Type = Type.values();
            for(int v1 = 0; v1 < arr_polystarShape$Type.length; ++v1) {
                Type polystarShape$Type0 = arr_polystarShape$Type[v1];
                if(polystarShape$Type0.value == v) {
                    return polystarShape$Type0;
                }
            }
            return null;
        }
    }

    private final boolean hidden;
    private final AnimatableFloatValue innerRadius;
    private final AnimatableFloatValue innerRoundedness;
    private final String name;
    private final AnimatableFloatValue outerRadius;
    private final AnimatableFloatValue outerRoundedness;
    private final AnimatableFloatValue points;
    private final AnimatableValue position;
    private final AnimatableFloatValue rotation;
    private final Type type;

    public PolystarShape(String s, Type polystarShape$Type0, AnimatableFloatValue animatableFloatValue0, AnimatableValue animatableValue0, AnimatableFloatValue animatableFloatValue1, AnimatableFloatValue animatableFloatValue2, AnimatableFloatValue animatableFloatValue3, AnimatableFloatValue animatableFloatValue4, AnimatableFloatValue animatableFloatValue5, boolean z) {
        this.name = s;
        this.type = polystarShape$Type0;
        this.points = animatableFloatValue0;
        this.position = animatableValue0;
        this.rotation = animatableFloatValue1;
        this.innerRadius = animatableFloatValue2;
        this.outerRadius = animatableFloatValue3;
        this.innerRoundedness = animatableFloatValue4;
        this.outerRoundedness = animatableFloatValue5;
        this.hidden = z;
    }

    public AnimatableFloatValue getInnerRadius() {
        return this.innerRadius;
    }

    public AnimatableFloatValue getInnerRoundedness() {
        return this.innerRoundedness;
    }

    public String getName() {
        return this.name;
    }

    public AnimatableFloatValue getOuterRadius() {
        return this.outerRadius;
    }

    public AnimatableFloatValue getOuterRoundedness() {
        return this.outerRoundedness;
    }

    public AnimatableFloatValue getPoints() {
        return this.points;
    }

    public AnimatableValue getPosition() {
        return this.position;
    }

    public AnimatableFloatValue getRotation() {
        return this.rotation;
    }

    public Type getType() {
        return this.type;
    }

    public boolean isHidden() {
        return this.hidden;
    }

    @Override  // com.airbnb.lottie.model.content.ContentModel
    public Content toContent(LottieDrawable lottieDrawable0, BaseLayer baseLayer0) {
        return new PolystarContent(lottieDrawable0, baseLayer0, this);
    }
}

