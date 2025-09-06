package com.google.android.material.shape;

public final class MarkerEdgeTreatment extends EdgeTreatment {
    private final float radius;

    public MarkerEdgeTreatment(float f) {
        this.radius = f - 0.001f;
    }

    @Override  // com.google.android.material.shape.EdgeTreatment
    boolean forceIntersection() {
        return true;
    }

    @Override  // com.google.android.material.shape.EdgeTreatment
    public void getEdgePath(float f, float f1, float f2, ShapePath shapePath0) {
        float f3 = (float)(((double)this.radius) * 1.414214 / 2.0);
        float f4 = (float)Math.sqrt(Math.pow(this.radius, 2.0) - Math.pow(f3, 2.0));
        shapePath0.reset(f1 - f3, ((float)(-(((double)this.radius) * 1.414214 - ((double)this.radius)))) + f4);
        shapePath0.lineTo(f1, ((float)(-(((double)this.radius) * 1.414214 - ((double)this.radius)))));
        shapePath0.lineTo(f1 + f3, ((float)(-(((double)this.radius) * 1.414214 - ((double)this.radius)))) + f4);
    }
}

