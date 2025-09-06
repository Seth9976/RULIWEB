package com.google.android.material.shape;

import android.graphics.RectF;
import java.util.Arrays;

public final class AdjustedCornerSize implements CornerSize {
    private final float adjustment;
    private final CornerSize other;

    public AdjustedCornerSize(float f, CornerSize cornerSize0) {
        while(cornerSize0 instanceof AdjustedCornerSize) {
            cornerSize0 = ((AdjustedCornerSize)cornerSize0).other;
            f += ((AdjustedCornerSize)cornerSize0).adjustment;
        }
        this.other = cornerSize0;
        this.adjustment = f;
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        return object0 instanceof AdjustedCornerSize ? this.other.equals(((AdjustedCornerSize)object0).other) && this.adjustment == ((AdjustedCornerSize)object0).adjustment : false;
    }

    @Override  // com.google.android.material.shape.CornerSize
    public float getCornerSize(RectF rectF0) {
        return Math.max(0.0f, this.other.getCornerSize(rectF0) + this.adjustment);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.other, this.adjustment});
    }
}

