package androidx.core.view;

import android.view.ScaleGestureDetector;
import androidx.annotation.ReplaceWith;

public final class ScaleGestureDetectorCompat {
    @ReplaceWith(expression = "scaleGestureDetector.isQuickScaleEnabled()")
    @Deprecated
    public static boolean isQuickScaleEnabled(ScaleGestureDetector scaleGestureDetector0) {
        return scaleGestureDetector0.isQuickScaleEnabled();
    }

    @Deprecated
    public static boolean isQuickScaleEnabled(Object object0) {
        return ScaleGestureDetectorCompat.isQuickScaleEnabled(((ScaleGestureDetector)object0));
    }

    @ReplaceWith(expression = "scaleGestureDetector.setQuickScaleEnabled(enabled)")
    @Deprecated
    public static void setQuickScaleEnabled(ScaleGestureDetector scaleGestureDetector0, boolean z) {
        scaleGestureDetector0.setQuickScaleEnabled(z);
    }

    @Deprecated
    public static void setQuickScaleEnabled(Object object0, boolean z) {
        ScaleGestureDetectorCompat.setQuickScaleEnabled(((ScaleGestureDetector)object0), z);
    }
}

