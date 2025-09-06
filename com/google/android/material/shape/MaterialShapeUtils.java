package com.google.android.material.shape;

import android.graphics.drawable.Drawable;
import android.view.View;
import com.google.android.material.internal.ViewUtils;

public class MaterialShapeUtils {
    static CornerTreatment createCornerTreatment(int v) {
        switch(v) {
            case 0: {
                return new RoundedCornerTreatment();
            }
            case 1: {
                return new CutCornerTreatment();
            }
            default: {
                return MaterialShapeUtils.createDefaultCornerTreatment();
            }
        }
    }

    static CornerTreatment createDefaultCornerTreatment() {
        return new RoundedCornerTreatment();
    }

    static EdgeTreatment createDefaultEdgeTreatment() {
        return new EdgeTreatment();
    }

    public static void setElevation(View view0, float f) {
        Drawable drawable0 = view0.getBackground();
        if(drawable0 instanceof MaterialShapeDrawable) {
            ((MaterialShapeDrawable)drawable0).setElevation(f);
        }
    }

    public static void setParentAbsoluteElevation(View view0) {
        Drawable drawable0 = view0.getBackground();
        if(drawable0 instanceof MaterialShapeDrawable) {
            MaterialShapeUtils.setParentAbsoluteElevation(view0, ((MaterialShapeDrawable)drawable0));
        }
    }

    public static void setParentAbsoluteElevation(View view0, MaterialShapeDrawable materialShapeDrawable0) {
        if(materialShapeDrawable0.isElevationOverlayEnabled()) {
            materialShapeDrawable0.setParentAbsoluteElevation(ViewUtils.getParentAbsoluteElevation(view0));
        }
    }
}

