package com.google.android.material.appbar;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.math.MathUtils;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.List;

abstract class HeaderScrollingViewBehavior extends ViewOffsetBehavior {
    private int overlayTop;
    final Rect tempRect1;
    final Rect tempRect2;
    private int verticalLayoutGap;

    public HeaderScrollingViewBehavior() {
        this.tempRect1 = new Rect();
        this.tempRect2 = new Rect();
        this.verticalLayoutGap = 0;
    }

    public HeaderScrollingViewBehavior(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
        this.tempRect1 = new Rect();
        this.tempRect2 = new Rect();
        this.verticalLayoutGap = 0;
    }

    abstract View findFirstDependency(List arg1);

    final int getOverlapPixelsForOffset(View view0) {
        return this.overlayTop == 0 ? 0 : MathUtils.clamp(((int)(this.getOverlapRatioForOffset(view0) * ((float)this.overlayTop))), 0, this.overlayTop);
    }

    float getOverlapRatioForOffset(View view0) {
        return 1.0f;
    }

    public final int getOverlayTop() {
        return this.overlayTop;
    }

    int getScrollRange(View view0) {
        return view0.getMeasuredHeight();
    }

    final int getVerticalLayoutGap() {
        return this.verticalLayoutGap;
    }

    @Override  // com.google.android.material.appbar.ViewOffsetBehavior
    protected void layoutChild(CoordinatorLayout coordinatorLayout0, View view0, int v) {
        View view1 = this.findFirstDependency(coordinatorLayout0.getDependencies(view0));
        if(view1 != null) {
            LayoutParams coordinatorLayout$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
            Rect rect0 = this.tempRect1;
            rect0.set(coordinatorLayout0.getPaddingLeft() + coordinatorLayout$LayoutParams0.leftMargin, view1.getBottom() + coordinatorLayout$LayoutParams0.topMargin, coordinatorLayout0.getWidth() - coordinatorLayout0.getPaddingRight() - coordinatorLayout$LayoutParams0.rightMargin, coordinatorLayout0.getHeight() + view1.getBottom() - coordinatorLayout0.getPaddingBottom() - coordinatorLayout$LayoutParams0.bottomMargin);
            WindowInsetsCompat windowInsetsCompat0 = coordinatorLayout0.getLastWindowInsets();
            if(windowInsetsCompat0 != null && ViewCompat.getFitsSystemWindows(coordinatorLayout0) && !ViewCompat.getFitsSystemWindows(view0)) {
                rect0.left += windowInsetsCompat0.getSystemWindowInsetLeft();
                rect0.right -= windowInsetsCompat0.getSystemWindowInsetRight();
            }
            GravityCompat.apply(HeaderScrollingViewBehavior.resolveGravity(coordinatorLayout$LayoutParams0.gravity), view0.getMeasuredWidth(), view0.getMeasuredHeight(), rect0, this.tempRect2, v);
            int v1 = this.getOverlapPixelsForOffset(view1);
            view0.layout(this.tempRect2.left, this.tempRect2.top - v1, this.tempRect2.right, this.tempRect2.bottom - v1);
            this.verticalLayoutGap = this.tempRect2.top - view1.getBottom();
            return;
        }
        super.layoutChild(coordinatorLayout0, view0, v);
        this.verticalLayoutGap = 0;
    }

    @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior
    public boolean onMeasureChild(CoordinatorLayout coordinatorLayout0, View view0, int v, int v1, int v2, int v3) {
        int v4 = view0.getLayoutParams().height;
        if(v4 == -2 || v4 == -1) {
            View view1 = this.findFirstDependency(coordinatorLayout0.getDependencies(view0));
            if(view1 != null) {
                int v5 = View.MeasureSpec.getSize(v2);
                if(v5 <= 0) {
                    v5 = coordinatorLayout0.getHeight();
                }
                else if(ViewCompat.getFitsSystemWindows(view1)) {
                    WindowInsetsCompat windowInsetsCompat0 = coordinatorLayout0.getLastWindowInsets();
                    if(windowInsetsCompat0 != null) {
                        v5 += windowInsetsCompat0.getSystemWindowInsetTop() + windowInsetsCompat0.getSystemWindowInsetBottom();
                    }
                }
                int v6 = v5 + this.getScrollRange(view1);
                int v7 = view1.getMeasuredHeight();
                if(this.shouldHeaderOverlapScrollingChild()) {
                    view0.setTranslationY(((float)(-v7)));
                }
                else {
                    view0.setTranslationY(0.0f);
                    v6 -= v7;
                }
                coordinatorLayout0.onMeasureChild(view0, v, v1, View.MeasureSpec.makeMeasureSpec(v6, (v4 == -1 ? 0x40000000 : 0x80000000)), v3);
                return true;
            }
        }
        return false;
    }

    private static int resolveGravity(int v) {
        return v == 0 ? 0x800033 : v;
    }

    public final void setOverlayTop(int v) {
        this.overlayTop = v;
    }

    protected boolean shouldHeaderOverlapScrollingChild() {
        return false;
    }
}

