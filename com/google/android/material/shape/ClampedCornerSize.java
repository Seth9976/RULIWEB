package com.google.android.material.shape;

import android.graphics.RectF;
import java.util.Arrays;

public final class ClampedCornerSize implements CornerSize {
    private final float target;

    public ClampedCornerSize(float f) {
        this.target = f;
    }

    public static ClampedCornerSize createFromCornerSize(AbsoluteCornerSize absoluteCornerSize0) {
        return new ClampedCornerSize(absoluteCornerSize0.getCornerSize());
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        return object0 instanceof ClampedCornerSize ? this.target == ((ClampedCornerSize)object0).target : false;
    }

    @Override  // com.google.android.material.shape.CornerSize
    public float getCornerSize(RectF rectF0) {
        float f = ClampedCornerSize.getMaxCornerSize(rectF0);
        return Math.min(this.target, f);
    }

    private static float getMaxCornerSize(RectF rectF0) {
        return Math.min(rectF0.width() / 2.0f, rectF0.height() / 2.0f);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.target});
    }
}

