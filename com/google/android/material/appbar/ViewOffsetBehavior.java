package com.google.android.material.appbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

class ViewOffsetBehavior extends Behavior {
    private int tempLeftRightOffset;
    private int tempTopBottomOffset;
    private ViewOffsetHelper viewOffsetHelper;

    public ViewOffsetBehavior() {
        this.tempTopBottomOffset = 0;
        this.tempLeftRightOffset = 0;
    }

    public ViewOffsetBehavior(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
        this.tempTopBottomOffset = 0;
        this.tempLeftRightOffset = 0;
    }

    public int getLeftAndRightOffset() {
        return this.viewOffsetHelper == null ? 0 : this.viewOffsetHelper.getLeftAndRightOffset();
    }

    public int getTopAndBottomOffset() {
        return this.viewOffsetHelper == null ? 0 : this.viewOffsetHelper.getTopAndBottomOffset();
    }

    public boolean isHorizontalOffsetEnabled() {
        return this.viewOffsetHelper != null && this.viewOffsetHelper.isHorizontalOffsetEnabled();
    }

    public boolean isVerticalOffsetEnabled() {
        return this.viewOffsetHelper != null && this.viewOffsetHelper.isVerticalOffsetEnabled();
    }

    protected void layoutChild(CoordinatorLayout coordinatorLayout0, View view0, int v) {
        coordinatorLayout0.onLayoutChild(view0, v);
    }

    @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior
    public boolean onLayoutChild(CoordinatorLayout coordinatorLayout0, View view0, int v) {
        this.layoutChild(coordinatorLayout0, view0, v);
        if(this.viewOffsetHelper == null) {
            this.viewOffsetHelper = new ViewOffsetHelper(view0);
        }
        this.viewOffsetHelper.onViewLayout();
        this.viewOffsetHelper.applyOffsets();
        int v1 = this.tempTopBottomOffset;
        if(v1 != 0) {
            this.viewOffsetHelper.setTopAndBottomOffset(v1);
            this.tempTopBottomOffset = 0;
        }
        int v2 = this.tempLeftRightOffset;
        if(v2 != 0) {
            this.viewOffsetHelper.setLeftAndRightOffset(v2);
            this.tempLeftRightOffset = 0;
        }
        return true;
    }

    public void setHorizontalOffsetEnabled(boolean z) {
        ViewOffsetHelper viewOffsetHelper0 = this.viewOffsetHelper;
        if(viewOffsetHelper0 != null) {
            viewOffsetHelper0.setHorizontalOffsetEnabled(z);
        }
    }

    public boolean setLeftAndRightOffset(int v) {
        ViewOffsetHelper viewOffsetHelper0 = this.viewOffsetHelper;
        if(viewOffsetHelper0 != null) {
            return viewOffsetHelper0.setLeftAndRightOffset(v);
        }
        this.tempLeftRightOffset = v;
        return false;
    }

    public boolean setTopAndBottomOffset(int v) {
        ViewOffsetHelper viewOffsetHelper0 = this.viewOffsetHelper;
        if(viewOffsetHelper0 != null) {
            return viewOffsetHelper0.setTopAndBottomOffset(v);
        }
        this.tempTopBottomOffset = v;
        return false;
    }

    public void setVerticalOffsetEnabled(boolean z) {
        ViewOffsetHelper viewOffsetHelper0 = this.viewOffsetHelper;
        if(viewOffsetHelper0 != null) {
            viewOffsetHelper0.setVerticalOffsetEnabled(z);
        }
    }
}

