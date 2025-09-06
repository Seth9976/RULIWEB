package com.google.android.material.navigationrail;

import android.content.Context;
import android.view.View.MeasureSpec;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import com.google.android.material.navigation.NavigationBarItemView;
import com.google.android.material.navigation.NavigationBarMenuView;

public class NavigationRailMenuView extends NavigationBarMenuView {
    private int itemMinimumHeight;
    private final FrameLayout.LayoutParams layoutParams;

    public NavigationRailMenuView(Context context0) {
        super(context0);
        this.itemMinimumHeight = -1;
        FrameLayout.LayoutParams frameLayout$LayoutParams0 = new FrameLayout.LayoutParams(-1, -2);
        this.layoutParams = frameLayout$LayoutParams0;
        frameLayout$LayoutParams0.gravity = 49;
        this.setLayoutParams(frameLayout$LayoutParams0);
        this.setItemActiveIndicatorResizeable(true);
    }

    @Override  // com.google.android.material.navigation.NavigationBarMenuView
    protected NavigationBarItemView createNavigationBarItemView(Context context0) {
        return new NavigationRailItemView(context0);
    }

    public int getItemMinimumHeight() {
        return this.itemMinimumHeight;
    }

    int getMenuGravity() {
        return this.layoutParams.gravity;
    }

    boolean isTopGravity() {
        return (this.layoutParams.gravity & 0x70) == 0x30;
    }

    private int makeSharedHeightSpec(int v, int v1, int v2) {
        return View.MeasureSpec.makeMeasureSpec(Math.min((this.itemMinimumHeight == -1 ? View.MeasureSpec.getSize(v) : this.itemMinimumHeight), v1 / Math.max(1, v2)), 0);
    }

    private int measureChildHeight(View view0, int v, int v1) {
        if(view0.getVisibility() != 8) {
            view0.measure(v, v1);
            return view0.getMeasuredHeight();
        }
        return 0;
    }

    private int measureSharedChildHeights(int v, int v1, int v2, View view0) {
        int v4 = view0 == null ? this.makeSharedHeightSpec(v, v1, v2) : View.MeasureSpec.makeMeasureSpec(view0.getMeasuredHeight(), 0);
        int v5 = this.getChildCount();
        int v6 = 0;
        for(int v3 = 0; v3 < v5; ++v3) {
            View view1 = this.getChildAt(v3);
            if(view1 != view0) {
                v6 += this.measureChildHeight(view1, v, v4);
            }
        }
        return v6;
    }

    private int measureShiftingChildHeights(int v, int v1, int v2) {
        View view0 = this.getChildAt(this.getSelectedItemPosition());
        if(view0 != null) {
            int v3 = this.measureChildHeight(view0, v, this.makeSharedHeightSpec(v, v1, v2));
            return v3 + this.measureSharedChildHeights(v, v1 - v3, v2 - 1, view0);
        }
        return this.measureSharedChildHeights(v, v1, v2, null);
    }

    @Override  // android.view.ViewGroup
    protected void onLayout(boolean z, int v, int v1, int v2, int v3) {
        int v4 = this.getChildCount();
        int v6 = 0;
        for(int v5 = 0; v5 < v4; ++v5) {
            View view0 = this.getChildAt(v5);
            if(view0.getVisibility() != 8) {
                int v7 = view0.getMeasuredHeight() + v6;
                view0.layout(0, v6, v2 - v, v7);
                v6 = v7;
            }
        }
    }

    @Override  // android.view.View
    protected void onMeasure(int v, int v1) {
        int v2 = View.MeasureSpec.getSize(v1);
        int v3 = this.getMenu().getVisibleItems().size();
        int v4 = v3 <= 1 || !this.isShifting(this.getLabelVisibilityMode(), v3) ? this.measureSharedChildHeights(v, v2, v3, null) : this.measureShiftingChildHeights(v, v2, v3);
        this.setMeasuredDimension(View.MeasureSpec.getSize(v), View.resolveSizeAndState(v4, v1, 0));
    }

    public void setItemMinimumHeight(int v) {
        if(this.itemMinimumHeight != v) {
            this.itemMinimumHeight = v;
            this.requestLayout();
        }
    }

    void setMenuGravity(int v) {
        if(this.layoutParams.gravity != v) {
            this.layoutParams.gravity = v;
            this.setLayoutParams(this.layoutParams);
        }
    }
}

