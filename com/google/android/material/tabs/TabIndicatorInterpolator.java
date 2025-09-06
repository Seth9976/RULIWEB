package com.google.android.material.tabs;

import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.internal.ViewUtils;

class TabIndicatorInterpolator {
    private static final int MIN_INDICATOR_WIDTH = 24;

    static RectF calculateIndicatorWidthForTab(TabLayout tabLayout0, View view0) {
        if(view0 == null) {
            return new RectF();
        }
        return tabLayout0.isTabIndicatorFullWidth() || !(view0 instanceof TabView) ? new RectF(((float)view0.getLeft()), ((float)view0.getTop()), ((float)view0.getRight()), ((float)view0.getBottom())) : TabIndicatorInterpolator.calculateTabViewContentBounds(((TabView)view0), 24);
    }

    static RectF calculateTabViewContentBounds(TabView tabLayout$TabView0, int v) {
        int v1 = tabLayout$TabView0.getContentWidth();
        int v2 = tabLayout$TabView0.getContentHeight();
        int v3 = (int)ViewUtils.dpToPx(tabLayout$TabView0.getContext(), v);
        if(v1 < v3) {
            v1 = v3;
        }
        int v4 = (tabLayout$TabView0.getLeft() + tabLayout$TabView0.getRight()) / 2;
        int v5 = (tabLayout$TabView0.getTop() + tabLayout$TabView0.getBottom()) / 2;
        return new RectF(((float)(v4 - v1 / 2)), ((float)(v5 - v2 / 2)), ((float)(v1 / 2 + v4)), ((float)(v5 + v4 / 2)));
    }

    void setIndicatorBoundsForTab(TabLayout tabLayout0, View view0, Drawable drawable0) {
        RectF rectF0 = TabIndicatorInterpolator.calculateIndicatorWidthForTab(tabLayout0, view0);
        drawable0.setBounds(((int)rectF0.left), drawable0.getBounds().top, ((int)rectF0.right), drawable0.getBounds().bottom);
    }

    void updateIndicatorForOffset(TabLayout tabLayout0, View view0, View view1, float f, Drawable drawable0) {
        RectF rectF0 = TabIndicatorInterpolator.calculateIndicatorWidthForTab(tabLayout0, view0);
        RectF rectF1 = TabIndicatorInterpolator.calculateIndicatorWidthForTab(tabLayout0, view1);
        drawable0.setBounds(AnimationUtils.lerp(((int)rectF0.left), ((int)rectF1.left), f), drawable0.getBounds().top, AnimationUtils.lerp(((int)rectF0.right), ((int)rectF1.right), f), drawable0.getBounds().bottom);
    }
}

