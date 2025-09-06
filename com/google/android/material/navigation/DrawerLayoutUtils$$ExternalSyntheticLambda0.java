package com.google.android.material.navigation;

import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.animation.ValueAnimator;
import androidx.drawerlayout.widget.DrawerLayout;

public final class DrawerLayoutUtils..ExternalSyntheticLambda0 implements ValueAnimator.AnimatorUpdateListener {
    public final DrawerLayout f$0;

    public DrawerLayoutUtils..ExternalSyntheticLambda0(DrawerLayout drawerLayout0) {
        this.f$0 = drawerLayout0;
    }

    @Override  // android.animation.ValueAnimator$AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator0) {
        DrawerLayoutUtils.lambda$getScrimCloseAnimatorUpdateListener$0(this.f$0, valueAnimator0);
    }
}

