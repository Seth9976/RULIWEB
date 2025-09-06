package com.google.android.material.internal;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class BaselineLayout extends ViewGroup {
    private int baseline;

    public BaselineLayout(Context context0) {
        super(context0, null, 0);
        this.baseline = -1;
    }

    public BaselineLayout(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0, 0);
        this.baseline = -1;
    }

    public BaselineLayout(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        this.baseline = -1;
    }

    @Override  // android.view.View
    public int getBaseline() {
        return this.baseline;
    }

    @Override  // android.view.ViewGroup
    protected void onLayout(boolean z, int v, int v1, int v2, int v3) {
        int v4 = this.getChildCount();
        int v5 = this.getPaddingLeft();
        int v6 = this.getPaddingRight();
        int v7 = this.getPaddingTop();
        for(int v8 = 0; v8 < v4; ++v8) {
            View view0 = this.getChildAt(v8);
            if(view0.getVisibility() != 8) {
                int v9 = view0.getMeasuredWidth();
                int v10 = view0.getMeasuredHeight();
                int v11 = (v2 - v - v6 - v5 - v9) / 2 + v5;
                int v12 = this.baseline == -1 || view0.getBaseline() == -1 ? v7 : this.baseline + v7 - view0.getBaseline();
                view0.layout(v11, v12, v9 + v11, v10 + v12);
            }
        }
    }

    @Override  // android.view.View
    protected void onMeasure(int v, int v1) {
        int v2 = this.getChildCount();
        int v4 = 0;
        int v5 = 0;
        int v6 = 0;
        int v7 = -1;
        int v8 = -1;
        for(int v3 = 0; v3 < v2; ++v3) {
            View view0 = this.getChildAt(v3);
            if(view0.getVisibility() != 8) {
                this.measureChild(view0, v, v1);
                int v9 = view0.getBaseline();
                if(v9 != -1) {
                    v7 = Math.max(v7, v9);
                    v8 = Math.max(v8, view0.getMeasuredHeight() - v9);
                }
                v5 = Math.max(v5, view0.getMeasuredWidth());
                v4 = Math.max(v4, view0.getMeasuredHeight());
                v6 = View.combineMeasuredStates(v6, view0.getMeasuredState());
            }
        }
        if(v7 != -1) {
            v4 = Math.max(v4, Math.max(v8, this.getPaddingBottom()) + v7);
            this.baseline = v7;
        }
        int v10 = Math.max(v4, this.getSuggestedMinimumHeight());
        this.setMeasuredDimension(View.resolveSizeAndState(Math.max(v5, this.getSuggestedMinimumWidth()), v, v6), View.resolveSizeAndState(v10, v1, v6 << 16));
    }
}

