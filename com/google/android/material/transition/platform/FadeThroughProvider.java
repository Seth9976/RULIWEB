package com.google.android.material.transition.platform;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;

public final class FadeThroughProvider implements VisibilityAnimatorProvider {
    static final float FADE_THROUGH_THRESHOLD = 0.35f;
    private float progressThreshold;

    public FadeThroughProvider() {
        this.progressThreshold = 0.35f;
    }

    @Override  // com.google.android.material.transition.platform.VisibilityAnimatorProvider
    public Animator createAppear(ViewGroup viewGroup0, View view0) {
        if(view0.getAlpha() == 0.0f) {
            return FadeThroughProvider.createFadeThroughAnimator(view0, 0.0f, 1.0f, this.progressThreshold, 1.0f, 1.0f);
        }
        float f = view0.getAlpha();
        return FadeThroughProvider.createFadeThroughAnimator(view0, 0.0f, f, this.progressThreshold, 1.0f, f);
    }

    @Override  // com.google.android.material.transition.platform.VisibilityAnimatorProvider
    public Animator createDisappear(ViewGroup viewGroup0, View view0) {
        if(view0.getAlpha() == 0.0f) {
            return FadeThroughProvider.createFadeThroughAnimator(view0, 1.0f, 0.0f, 0.0f, this.progressThreshold, 1.0f);
        }
        float f = view0.getAlpha();
        return FadeThroughProvider.createFadeThroughAnimator(view0, f, 0.0f, 0.0f, this.progressThreshold, f);
    }

    private static Animator createFadeThroughAnimator(View view0, float f, float f1, float f2, float f3, float f4) {
        Animator animator0 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        ((ValueAnimator)animator0).addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override  // android.animation.ValueAnimator$AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator0) {
                float f = (float)(((Float)valueAnimator0.getAnimatedValue()));
                view0.setAlpha(TransitionUtils.lerp(f, f1, f2, f3, f));
            }
        });
        ((ValueAnimator)animator0).addListener(new AnimatorListenerAdapter() {
            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationEnd(Animator animator0) {
                view0.setAlpha(f4);
            }
        });
        return animator0;
    }

    public float getProgressThreshold() {
        return this.progressThreshold;
    }

    public void setProgressThreshold(float f) {
        this.progressThreshold = f;
    }
}

