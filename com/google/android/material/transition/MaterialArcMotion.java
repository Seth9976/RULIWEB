package com.google.android.material.transition;

import android.graphics.Path;
import android.graphics.PointF;
import androidx.transition.PathMotion;

public final class MaterialArcMotion extends PathMotion {
    private static PointF getControlPoint(float f, float f1, float f2, float f3) {
        return f1 > f3 ? new PointF(f2, f1) : new PointF(f, f3);
    }

    @Override  // androidx.transition.PathMotion
    public Path getPath(float f, float f1, float f2, float f3) {
        Path path0 = new Path();
        path0.moveTo(f, f1);
        PointF pointF0 = MaterialArcMotion.getControlPoint(f, f1, f2, f3);
        path0.quadTo(pointF0.x, pointF0.y, f2, f3);
        return path0;
    }
}

