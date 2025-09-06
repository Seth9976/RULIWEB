package com.google.android.material.appbar;

import android.view.View;
import androidx.core.view.ViewCompat;

class ViewOffsetHelper {
    private boolean horizontalOffsetEnabled;
    private int layoutLeft;
    private int layoutTop;
    private int offsetLeft;
    private int offsetTop;
    private boolean verticalOffsetEnabled;
    private final View view;

    public ViewOffsetHelper(View view0) {
        this.verticalOffsetEnabled = true;
        this.horizontalOffsetEnabled = true;
        this.view = view0;
    }

    void applyOffsets() {
        int v = this.offsetTop;
        int v1 = this.view.getTop();
        ViewCompat.offsetTopAndBottom(this.view, v - (v1 - this.layoutTop));
        int v2 = this.offsetLeft;
        int v3 = this.view.getLeft();
        ViewCompat.offsetLeftAndRight(this.view, v2 - (v3 - this.layoutLeft));
    }

    public int getLayoutLeft() {
        return this.layoutLeft;
    }

    public int getLayoutTop() {
        return this.layoutTop;
    }

    public int getLeftAndRightOffset() {
        return this.offsetLeft;
    }

    public int getTopAndBottomOffset() {
        return this.offsetTop;
    }

    public boolean isHorizontalOffsetEnabled() {
        return this.horizontalOffsetEnabled;
    }

    public boolean isVerticalOffsetEnabled() {
        return this.verticalOffsetEnabled;
    }

    void onViewLayout() {
        this.layoutTop = this.view.getTop();
        this.layoutLeft = this.view.getLeft();
    }

    public void setHorizontalOffsetEnabled(boolean z) {
        this.horizontalOffsetEnabled = z;
    }

    public boolean setLeftAndRightOffset(int v) {
        if(this.horizontalOffsetEnabled && this.offsetLeft != v) {
            this.offsetLeft = v;
            this.applyOffsets();
            return true;
        }
        return false;
    }

    public boolean setTopAndBottomOffset(int v) {
        if(this.verticalOffsetEnabled && this.offsetTop != v) {
            this.offsetTop = v;
            this.applyOffsets();
            return true;
        }
        return false;
    }

    public void setVerticalOffsetEnabled(boolean z) {
        this.verticalOffsetEnabled = z;
    }
}

