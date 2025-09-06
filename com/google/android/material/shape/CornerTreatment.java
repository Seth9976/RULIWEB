package com.google.android.material.shape;

import android.graphics.RectF;

public class CornerTreatment {
    @Deprecated
    public void getCornerPath(float f, float f1, ShapePath shapePath0) {
    }

    public void getCornerPath(ShapePath shapePath0, float f, float f1, float f2) {
    }

    public void getCornerPath(ShapePath shapePath0, float f, float f1, RectF rectF0, CornerSize cornerSize0) {
        this.getCornerPath(shapePath0, f, f1, cornerSize0.getCornerSize(rectF0));
    }
}

