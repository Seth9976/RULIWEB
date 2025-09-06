package androidx.constraintlayout.widget;

import android.content.Context;
import android.util.AttributeSet;

public class Group extends ConstraintHelper {
    public Group(Context context0) {
        super(context0);
    }

    public Group(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
    }

    public Group(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
    }

    @Override  // androidx.constraintlayout.widget.ConstraintHelper
    protected void applyLayoutFeaturesInConstraintSet(ConstraintLayout constraintLayout0) {
        this.applyLayoutFeatures(constraintLayout0);
    }

    @Override  // androidx.constraintlayout.widget.ConstraintHelper
    protected void init(AttributeSet attributeSet0) {
        super.init(attributeSet0);
        this.mUseViewMeasure = false;
    }

    @Override  // androidx.constraintlayout.widget.ConstraintHelper
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.applyLayoutFeatures();
    }

    @Override  // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        this.applyLayoutFeatures();
    }

    @Override  // android.view.View
    public void setVisibility(int v) {
        super.setVisibility(v);
        this.applyLayoutFeatures();
    }

    @Override  // androidx.constraintlayout.widget.ConstraintHelper
    public void updatePostLayout(ConstraintLayout constraintLayout0) {
        LayoutParams constraintLayout$LayoutParams0 = (LayoutParams)this.getLayoutParams();
        constraintLayout$LayoutParams0.mWidget.setWidth(0);
        constraintLayout$LayoutParams0.mWidget.setHeight(0);
    }
}

