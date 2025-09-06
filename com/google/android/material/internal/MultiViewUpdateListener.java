package com.google.android.material.internal;

import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.animation.ValueAnimator;
import android.view.View;
import java.util.Collection;

public class MultiViewUpdateListener implements ValueAnimator.AnimatorUpdateListener {
    interface Listener {
        void onAnimationUpdate(ValueAnimator arg1, View arg2);
    }

    private final Listener listener;
    private final View[] views;

    // 检测为 Lambda 实现
    public static void $r8$lambda$WkRITrIg1eLk2gv__5iwocHDqeg(ValueAnimator valueAnimator0, View view0) [...]

    // 检测为 Lambda 实现
    public static void $r8$lambda$kNR6Np3RAVUjjKtQJR0ljE_6vq8(ValueAnimator valueAnimator0, View view0) [...]

    // 检测为 Lambda 实现
    public static void $r8$lambda$l3sYajCeVVvEXOGqzrBJyGlXVps(ValueAnimator valueAnimator0, View view0) [...]

    // 检测为 Lambda 实现
    public static void $r8$lambda$tf5oGWUNf6tQQxMTGg7FpP3COoI(ValueAnimator valueAnimator0, View view0) [...]

    public MultiViewUpdateListener(Listener multiViewUpdateListener$Listener0, Collection collection0) {
        this.listener = multiViewUpdateListener$Listener0;
        this.views = (View[])collection0.toArray(new View[0]);
    }

    public MultiViewUpdateListener(Listener multiViewUpdateListener$Listener0, View[] arr_view) {
        this.listener = multiViewUpdateListener$Listener0;
        this.views = arr_view;
    }

    public static MultiViewUpdateListener alphaListener(Collection collection0) {
        return new MultiViewUpdateListener((ValueAnimator valueAnimator0, View view0) -> MultiViewUpdateListener.setAlpha(valueAnimator0, view0), collection0);
    }

    public static MultiViewUpdateListener alphaListener(View[] arr_view) {
        return new MultiViewUpdateListener((ValueAnimator valueAnimator0, View view0) -> MultiViewUpdateListener.setAlpha(valueAnimator0, view0), arr_view);
    }

    @Override  // android.animation.ValueAnimator$AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator valueAnimator0) {
        View[] arr_view = this.views;
        for(int v = 0; v < arr_view.length; ++v) {
            this.listener.onAnimationUpdate(valueAnimator0, arr_view[v]);
        }
    }

    public static MultiViewUpdateListener scaleListener(Collection collection0) {
        return new MultiViewUpdateListener((ValueAnimator valueAnimator0, View view0) -> MultiViewUpdateListener.setScale(valueAnimator0, view0), collection0);
    }

    public static MultiViewUpdateListener scaleListener(View[] arr_view) {
        return new MultiViewUpdateListener((ValueAnimator valueAnimator0, View view0) -> MultiViewUpdateListener.setScale(valueAnimator0, view0), arr_view);
    }

    private static void setAlpha(ValueAnimator valueAnimator0, View view0) {
        view0.setAlpha(((float)(((Float)valueAnimator0.getAnimatedValue()))));
    }

    private static void setScale(ValueAnimator valueAnimator0, View view0) {
        Float float0 = (Float)valueAnimator0.getAnimatedValue();
        view0.setScaleX(((float)float0));
        view0.setScaleY(((float)float0));
    }

    private static void setTranslationX(ValueAnimator valueAnimator0, View view0) {
        view0.setTranslationX(((float)(((Float)valueAnimator0.getAnimatedValue()))));
    }

    private static void setTranslationY(ValueAnimator valueAnimator0, View view0) {
        view0.setTranslationY(((float)(((Float)valueAnimator0.getAnimatedValue()))));
    }

    public static MultiViewUpdateListener translationXListener(Collection collection0) {
        return new MultiViewUpdateListener((ValueAnimator valueAnimator0, View view0) -> MultiViewUpdateListener.setTranslationX(valueAnimator0, view0), collection0);
    }

    public static MultiViewUpdateListener translationXListener(View[] arr_view) {
        return new MultiViewUpdateListener((ValueAnimator valueAnimator0, View view0) -> MultiViewUpdateListener.setTranslationX(valueAnimator0, view0), arr_view);
    }

    public static MultiViewUpdateListener translationYListener(Collection collection0) {
        return new MultiViewUpdateListener((ValueAnimator valueAnimator0, View view0) -> MultiViewUpdateListener.setTranslationY(valueAnimator0, view0), collection0);
    }

    public static MultiViewUpdateListener translationYListener(View[] arr_view) {
        return new MultiViewUpdateListener((ValueAnimator valueAnimator0, View view0) -> MultiViewUpdateListener.setTranslationY(valueAnimator0, view0), arr_view);
    }
}

