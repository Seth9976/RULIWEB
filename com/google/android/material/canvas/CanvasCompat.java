package com.google.android.material.canvas;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.os.Build.VERSION;

public class CanvasCompat {
    public interface CanvasOperation {
        void run(Canvas arg1);
    }

    public static int saveLayerAlpha(Canvas canvas0, float f, float f1, float f2, float f3, int v) {
        return Build.VERSION.SDK_INT <= 21 ? canvas0.saveLayerAlpha(f, f1, f2, f3, v, 0x1F) : canvas0.saveLayerAlpha(f, f1, f2, f3, v);
    }

    public static int saveLayerAlpha(Canvas canvas0, RectF rectF0, int v) {
        return Build.VERSION.SDK_INT <= 21 ? canvas0.saveLayerAlpha(rectF0, v, 0x1F) : canvas0.saveLayerAlpha(rectF0, v);
    }
}

