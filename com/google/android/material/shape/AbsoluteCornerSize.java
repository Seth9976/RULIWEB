package com.google.android.material.shape;

import android.graphics.RectF;
import java.util.Arrays;

public final class AbsoluteCornerSize implements CornerSize {
    private final float size;

    public AbsoluteCornerSize(float f) {
        this.size = f;
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        return object0 instanceof AbsoluteCornerSize ? this.size == ((AbsoluteCornerSize)object0).size : false;
    }

    public float getCornerSize() {
        return this.size;
    }

    @Override  // com.google.android.material.shape.CornerSize
    public float getCornerSize(RectF rectF0) {
        return this.size;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.size});
    }
}

