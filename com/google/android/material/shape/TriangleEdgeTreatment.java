package com.google.android.material.shape;

public class TriangleEdgeTreatment extends EdgeTreatment {
    private final boolean inside;
    private final float size;

    public TriangleEdgeTreatment(float f, boolean z) {
        this.size = f;
        this.inside = z;
    }

    @Override  // com.google.android.material.shape.EdgeTreatment
    public void getEdgePath(float f, float f1, float f2, ShapePath shapePath0) {
        if(this.inside) {
            shapePath0.lineTo(f1 - this.size * f2, 0.0f);
            shapePath0.lineTo(f1, this.size * f2, this.size * f2 + f1, 0.0f);
            shapePath0.lineTo(f, 0.0f);
            return;
        }
        shapePath0.lineTo(f1 - this.size * f2, 0.0f, f1, -this.size * f2);
        shapePath0.lineTo(f1 + this.size * f2, 0.0f, f, 0.0f);
    }
}

