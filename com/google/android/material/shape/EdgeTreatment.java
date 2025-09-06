package com.google.android.material.shape;

public class EdgeTreatment {
    boolean forceIntersection() {
        return false;
    }

    public void getEdgePath(float f, float f1, float f2, ShapePath shapePath0) {
        shapePath0.lineTo(f, 0.0f);
    }

    @Deprecated
    public void getEdgePath(float f, float f1, ShapePath shapePath0) {
        this.getEdgePath(f, f / 2.0f, f1, shapePath0);
    }
}

