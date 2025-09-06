package com.google.android.material.bottomappbar;

import com.google.android.material.shape.EdgeTreatment;
import com.google.android.material.shape.ShapePath;

public class BottomAppBarTopEdgeTreatment extends EdgeTreatment implements Cloneable {
    private static final int ANGLE_LEFT = 180;
    private static final int ANGLE_UP = 270;
    private static final int ARC_HALF = 180;
    private static final int ARC_QUARTER = 90;
    private static final float ROUNDED_CORNER_FAB_OFFSET = 1.75f;
    private float cradleVerticalOffset;
    private float fabCornerSize;
    private float fabDiameter;
    private float fabMargin;
    private float horizontalOffset;
    private float roundedCornerRadius;

    public BottomAppBarTopEdgeTreatment(float f, float f1, float f2) {
        this.fabCornerSize = -1.0f;
        this.fabMargin = f;
        this.roundedCornerRadius = f1;
        this.setCradleVerticalOffset(f2);
        this.horizontalOffset = 0.0f;
    }

    float getCradleVerticalOffset() {
        return this.cradleVerticalOffset;
    }

    @Override  // com.google.android.material.shape.EdgeTreatment
    public void getEdgePath(float f, float f1, float f2, ShapePath shapePath0) {
        float f10;
        float f9;
        float f3 = this.fabDiameter;
        if(f3 == 0.0f) {
            shapePath0.lineTo(f, 0.0f);
            return;
        }
        float f4 = (this.fabMargin * 2.0f + f3) / 2.0f;
        float f5 = f2 * this.roundedCornerRadius;
        float f6 = f1 + this.horizontalOffset;
        float f7 = this.cradleVerticalOffset * f2 + (1.0f - f2) * f4;
        if(f7 / f4 >= 1.0f) {
            shapePath0.lineTo(f, 0.0f);
            return;
        }
        float f8 = this.fabCornerSize * f2;
        boolean z = this.fabCornerSize == -1.0f || Math.abs(this.fabCornerSize * 2.0f - f3) < 0.1f;
        if(z) {
            f10 = f7;
            f9 = 0.0f;
        }
        else {
            f9 = 1.75f;
            f10 = 0.0f;
        }
        float f11 = f10 + f5;
        float f12 = (float)Math.sqrt((f4 + f5) * (f4 + f5) - f11 * f11);
        float f13 = f6 - f12;
        float f14 = (float)Math.toDegrees(Math.atan(f12 / f11));
        float f15 = 90.0f - f14 + f9;
        shapePath0.lineTo(f13, 0.0f);
        shapePath0.addArc(f13 - f5, 0.0f, f13 + f5, f5 * 2.0f, 270.0f, f14);
        if(z) {
            shapePath0.addArc(f6 - f4, -f4 - f10, f6 + f4, f4 - f10, 180.0f - f15, f15 * 2.0f - 180.0f);
        }
        else {
            shapePath0.addArc(f6 - f4, -(f8 + this.fabMargin), this.fabMargin + f8 * 2.0f + (f6 - f4), this.fabMargin + f8, 180.0f - f15, (f15 * 2.0f - 180.0f) / 2.0f);
            float f16 = f6 + f4;
            shapePath0.lineTo(f16 - (this.fabMargin / 2.0f + f8), this.fabMargin + f8);
            shapePath0.addArc(f16 - (f8 * 2.0f + this.fabMargin), -(f8 + this.fabMargin), f16, this.fabMargin + f8, 90.0f, f15 - 90.0f);
        }
        shapePath0.addArc(f6 + f12 - f5, 0.0f, f6 + f12 + f5, f5 * 2.0f, 270.0f - f14, f14);
        shapePath0.lineTo(f, 0.0f);
    }

    public float getFabCornerRadius() {
        return this.fabCornerSize;
    }

    float getFabCradleMargin() {
        return this.fabMargin;
    }

    float getFabCradleRoundedCornerRadius() {
        return this.roundedCornerRadius;
    }

    public float getFabDiameter() {
        return this.fabDiameter;
    }

    public float getHorizontalOffset() {
        return this.horizontalOffset;
    }

    void setCradleVerticalOffset(float f) {
        if(f < 0.0f) {
            throw new IllegalArgumentException("cradleVerticalOffset must be positive.");
        }
        this.cradleVerticalOffset = f;
    }

    public void setFabCornerSize(float f) {
        this.fabCornerSize = f;
    }

    void setFabCradleMargin(float f) {
        this.fabMargin = f;
    }

    void setFabCradleRoundedCornerRadius(float f) {
        this.roundedCornerRadius = f;
    }

    public void setFabDiameter(float f) {
        this.fabDiameter = f;
    }

    void setHorizontalOffset(float f) {
        this.horizontalOffset = f;
    }
}

