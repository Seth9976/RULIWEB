package com.google.android.material.shape;

public class CutCornerTreatment extends CornerTreatment {
    float size;

    public CutCornerTreatment() {
        this.size = -1.0f;
    }

    @Deprecated
    public CutCornerTreatment(float f) {
        this.size = f;
    }

    @Override  // com.google.android.material.shape.CornerTreatment
    public void getCornerPath(ShapePath shapePath0, float f, float f1, float f2) {
        shapePath0.reset(0.0f, f2 * f1, 180.0f, 180.0f - f);
        shapePath0.lineTo(((float)(Math.sin(Math.toRadians(f)) * ((double)f2) * ((double)f1))), ((float)(Math.sin(Math.toRadians(90.0f - f)) * ((double)f2) * ((double)f1))));
    }
}

