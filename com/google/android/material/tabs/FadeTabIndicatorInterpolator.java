package com.google.android.material.tabs;

import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.google.android.material.animation.AnimationUtils;

class FadeTabIndicatorInterpolator extends TabIndicatorInterpolator {
    private static final float FADE_THRESHOLD = 0.5f;

    @Override  // com.google.android.material.tabs.TabIndicatorInterpolator
    void updateIndicatorForOffset(TabLayout tabLayout0, View view0, View view1, float f, Drawable drawable0) {
        int v = Float.compare(f, 0.5f);
        if(v >= 0) {
            view0 = view1;
        }
        RectF rectF0 = FadeTabIndicatorInterpolator.calculateIndicatorWidthForTab(tabLayout0, view0);
        drawable0.setBounds(((int)rectF0.left), drawable0.getBounds().top, ((int)rectF0.right), drawable0.getBounds().bottom);
        drawable0.setAlpha(((int)((v >= 0 ? AnimationUtils.lerp(0.0f, 1.0f, 0.5f, 1.0f, f) : AnimationUtils.lerp(1.0f, 0.0f, 0.0f, 0.5f, f)) * 255.0f)));
    }
}

