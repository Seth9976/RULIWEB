package com.google.android.material.shape;

public class RoundedCornerTreatment extends CornerTreatment {
    float radius;

    public RoundedCornerTreatment() {
        this.radius = -1.0f;
    }

    @Deprecated
    public RoundedCornerTreatment(float f) {
        this.radius = f;
    }

    @Override  // com.google.android.material.shape.CornerTreatment
    public void getCornerPath(ShapePath shapePath0, float f, float f1, float f2) {
        shapePath0.reset(0.0f, f2 * f1, 180.0f, 180.0f - f);
        float f3 = f2 * 2.0f * f1;
        shapePath0.addArc(0.0f, 0.0f, f3, f3, 180.0f, f);
    }
}

