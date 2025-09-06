package com.google.android.material.shape;

import android.graphics.RectF;
import java.util.Arrays;

public final class RelativeCornerSize implements CornerSize {
    private final float percent;

    public RelativeCornerSize(float f) {
        this.percent = f;
    }

    // 去混淆评级： 低(20)
    public static RelativeCornerSize createFromCornerSize(RectF rectF0, CornerSize cornerSize0) {
        return cornerSize0 instanceof RelativeCornerSize ? ((RelativeCornerSize)cornerSize0) : new RelativeCornerSize(cornerSize0.getCornerSize(rectF0) / RelativeCornerSize.getMaxCornerSize(rectF0));
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        return object0 instanceof RelativeCornerSize ? this.percent == ((RelativeCornerSize)object0).percent : false;
    }

    @Override  // com.google.android.material.shape.CornerSize
    public float getCornerSize(RectF rectF0) {
        float f = RelativeCornerSize.getMaxCornerSize(rectF0);
        return this.percent * f;
    }

    private static float getMaxCornerSize(RectF rectF0) {
        return Math.min(rectF0.width(), rectF0.height());
    }

    public float getRelativePercent() {
        return this.percent;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.percent});
    }
}

