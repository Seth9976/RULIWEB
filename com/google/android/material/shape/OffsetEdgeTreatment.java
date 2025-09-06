package com.google.android.material.shape;

public final class OffsetEdgeTreatment extends EdgeTreatment {
    private final float offset;
    private final EdgeTreatment other;

    public OffsetEdgeTreatment(EdgeTreatment edgeTreatment0, float f) {
        this.other = edgeTreatment0;
        this.offset = f;
    }

    @Override  // com.google.android.material.shape.EdgeTreatment
    boolean forceIntersection() {
        return this.other.forceIntersection();
    }

    @Override  // com.google.android.material.shape.EdgeTreatment
    public void getEdgePath(float f, float f1, float f2, ShapePath shapePath0) {
        this.other.getEdgePath(f, f1 - this.offset, f2, shapePath0);
    }
}

