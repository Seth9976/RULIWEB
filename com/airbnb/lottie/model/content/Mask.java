package com.airbnb.lottie.model.content;

import com.airbnb.lottie.model.animatable.AnimatableIntegerValue;
import com.airbnb.lottie.model.animatable.AnimatableShapeValue;

public class Mask {
    public static enum MaskMode {
        MASK_MODE_ADD,
        MASK_MODE_SUBTRACT,
        MASK_MODE_INTERSECT;

    }

    private final boolean inverted;
    private final MaskMode maskMode;
    private final AnimatableShapeValue maskPath;
    private final AnimatableIntegerValue opacity;

    public Mask(MaskMode mask$MaskMode0, AnimatableShapeValue animatableShapeValue0, AnimatableIntegerValue animatableIntegerValue0, boolean z) {
        this.maskMode = mask$MaskMode0;
        this.maskPath = animatableShapeValue0;
        this.opacity = animatableIntegerValue0;
        this.inverted = z;
    }

    public MaskMode getMaskMode() {
        return this.maskMode;
    }

    public AnimatableShapeValue getMaskPath() {
        return this.maskPath;
    }

    public AnimatableIntegerValue getOpacity() {
        return this.opacity;
    }

    public boolean isInverted() {
        return this.inverted;
    }
}

