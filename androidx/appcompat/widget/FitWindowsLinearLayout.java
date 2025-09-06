package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class FitWindowsLinearLayout extends LinearLayout implements FitWindowsViewGroup {
    private OnFitSystemWindowsListener mListener;

    public FitWindowsLinearLayout(Context context0) {
        super(context0);
    }

    public FitWindowsLinearLayout(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
    }

    @Override  // android.view.View
    protected boolean fitSystemWindows(Rect rect0) {
        OnFitSystemWindowsListener fitWindowsViewGroup$OnFitSystemWindowsListener0 = this.mListener;
        if(fitWindowsViewGroup$OnFitSystemWindowsListener0 != null) {
            fitWindowsViewGroup$OnFitSystemWindowsListener0.onFitSystemWindows(rect0);
        }
        return super.fitSystemWindows(rect0);
    }

    @Override  // androidx.appcompat.widget.FitWindowsViewGroup
    public void setOnFitSystemWindowsListener(OnFitSystemWindowsListener fitWindowsViewGroup$OnFitSystemWindowsListener0) {
        this.mListener = fitWindowsViewGroup$OnFitSystemWindowsListener0;
    }
}

