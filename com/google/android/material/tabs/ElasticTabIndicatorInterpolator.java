package com.google.android.material.tabs;

import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.google.android.material.animation.AnimationUtils;

class ElasticTabIndicatorInterpolator extends TabIndicatorInterpolator {
    private static float accInterp(float f) {
        return (float)(1.0 - Math.cos(((double)f) * 3.141593 / 2.0));
    }

    private static float decInterp(float f) {
        return (float)Math.sin(((double)f) * 3.141593 / 2.0);
    }

    @Override  // com.google.android.material.tabs.TabIndicatorInterpolator
    void updateIndicatorForOffset(TabLayout tabLayout0, View view0, View view1, float f, Drawable drawable0) {
        float f2;
        float f1;
        RectF rectF0 = ElasticTabIndicatorInterpolator.calculateIndicatorWidthForTab(tabLayout0, view0);
        RectF rectF1 = ElasticTabIndicatorInterpolator.calculateIndicatorWidthForTab(tabLayout0, view1);
        if(rectF0.left < rectF1.left) {
            f1 = ElasticTabIndicatorInterpolator.accInterp(f);
            f2 = ElasticTabIndicatorInterpolator.decInterp(f);
        }
        else {
            f1 = ElasticTabIndicatorInterpolator.decInterp(f);
            f2 = ElasticTabIndicatorInterpolator.accInterp(f);
        }
        drawable0.setBounds(AnimationUtils.lerp(((int)rectF0.left), ((int)rectF1.left), f1), drawable0.getBounds().top, AnimationUtils.lerp(((int)rectF0.right), ((int)rectF1.right), f2), drawable0.getBounds().bottom);
    }
}

