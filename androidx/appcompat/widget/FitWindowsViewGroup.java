package androidx.appcompat.widget;

import android.graphics.Rect;

public interface FitWindowsViewGroup {
    public interface OnFitSystemWindowsListener {
        void onFitSystemWindows(Rect arg1);
    }

    void setOnFitSystemWindowsListener(OnFitSystemWindowsListener arg1);
}

