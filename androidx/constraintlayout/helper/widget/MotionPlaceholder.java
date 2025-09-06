package androidx.constraintlayout.helper.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View.MeasureSpec;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.core.widgets.Helper;
import androidx.constraintlayout.core.widgets.Placeholder;
import androidx.constraintlayout.widget.VirtualLayout;

public class MotionPlaceholder extends VirtualLayout {
    private static final String TAG = "MotionPlaceholder";
    Placeholder mPlaceholder;

    public MotionPlaceholder(Context context0) {
        super(context0);
    }

    public MotionPlaceholder(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
    }

    public MotionPlaceholder(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
    }

    public MotionPlaceholder(Context context0, AttributeSet attributeSet0, int v, int v1) {
        super(context0, attributeSet0, v);
    }

    @Override  // androidx.constraintlayout.widget.VirtualLayout
    protected void init(AttributeSet attributeSet0) {
        super.init(attributeSet0);
        this.mHelperWidget = new Placeholder();
        this.validateParams();
    }

    @Override  // androidx.constraintlayout.widget.ConstraintHelper
    protected void onMeasure(int v, int v1) {
        this.onMeasure(this.mPlaceholder, v, v1);
    }

    @Override  // androidx.constraintlayout.widget.VirtualLayout
    public void onMeasure(androidx.constraintlayout.core.widgets.VirtualLayout virtualLayout0, int v, int v1) {
        int v2 = View.MeasureSpec.getMode(v);
        int v3 = View.MeasureSpec.getSize(v);
        int v4 = View.MeasureSpec.getMode(v1);
        int v5 = View.MeasureSpec.getSize(v1);
        if(virtualLayout0 != null) {
            virtualLayout0.measure(v2, v3, v4, v5);
            this.setMeasuredDimension(virtualLayout0.getMeasuredWidth(), virtualLayout0.getMeasuredHeight());
            return;
        }
        this.setMeasuredDimension(0, 0);
    }

    @Override  // androidx.constraintlayout.widget.ConstraintHelper
    public void updatePreLayout(ConstraintWidgetContainer constraintWidgetContainer0, Helper helper0, SparseArray sparseArray0) {
    }
}

