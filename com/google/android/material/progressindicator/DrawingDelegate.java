package com.google.android.material.progressindicator;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

abstract class DrawingDelegate {
    public static class ActiveIndicator {
        int color;
        float endFraction;
        int gapSize;
        float startFraction;

    }

    BaseProgressIndicatorSpec spec;

    public DrawingDelegate(BaseProgressIndicatorSpec baseProgressIndicatorSpec0) {
        this.spec = baseProgressIndicatorSpec0;
    }

    abstract void adjustCanvas(Canvas arg1, Rect arg2, float arg3, boolean arg4, boolean arg5);

    abstract void drawStopIndicator(Canvas arg1, Paint arg2, int arg3, int arg4);

    abstract void fillIndicator(Canvas arg1, Paint arg2, ActiveIndicator arg3, int arg4);

    abstract void fillTrack(Canvas arg1, Paint arg2, float arg3, float arg4, int arg5, int arg6, int arg7);

    abstract int getPreferredHeight();

    abstract int getPreferredWidth();

    void validateSpecAndAdjustCanvas(Canvas canvas0, Rect rect0, float f, boolean z, boolean z1) {
        this.spec.validateSpec();
        this.adjustCanvas(canvas0, rect0, f, z, z1);
    }
}

