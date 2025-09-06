package com.google.android.material.navigation;

import android.animation.Animator.AnimatorListener;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.view.View;
import androidx.core.graphics.ColorUtils;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.animation.AnimationUtils;

public class DrawerLayoutUtils {
    private static final int DEFAULT_SCRIM_ALPHA = 0;
    private static final int DEFAULT_SCRIM_COLOR = 0x99000000;

    static {
        DrawerLayoutUtils.DEFAULT_SCRIM_ALPHA = Color.alpha(0x99000000);
    }

    public static Animator.AnimatorListener getScrimCloseAnimatorListener(DrawerLayout drawerLayout0, View view0) {
        return new AnimatorListenerAdapter() {
            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationEnd(Animator animator0) {
                drawerLayout0.closeDrawer(view0, false);
                drawerLayout0.setScrimColor(0x99000000);
            }
        };
    }

    public static ValueAnimator.AnimatorUpdateListener getScrimCloseAnimatorUpdateListener(DrawerLayout drawerLayout0) {
        return (ValueAnimator valueAnimator0) -> {
            float f = valueAnimator0.getAnimatedFraction();
            drawerLayout0.setScrimColor(ColorUtils.setAlphaComponent(0x99000000, AnimationUtils.lerp(DrawerLayoutUtils.DEFAULT_SCRIM_ALPHA, 0, f)));
        };
    }

    // 检测为 Lambda 实现
    static void lambda$getScrimCloseAnimatorUpdateListener$0(DrawerLayout drawerLayout0, ValueAnimator valueAnimator0) [...]
}

