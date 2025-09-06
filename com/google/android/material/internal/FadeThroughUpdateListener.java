package com.google.android.material.internal;

import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.animation.ValueAnimator;
import android.view.View;

public class FadeThroughUpdateListener implements ValueAnimator.AnimatorUpdateListener {
    private final float[] alphas;
    private final View fadeInView;
    private final View fadeOutView;

    public FadeThroughUpdateListener(View view0, View view1) {
        this.fadeOutView = view0;
        this.fadeInView = view1;
        this.alphas = new float[2];
    }

    @Override  // android.animation.ValueAnimator$AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator valueAnimator0) {
        FadeThroughUtils.calculateFadeOutAndInAlphas(((float)(((Float)valueAnimator0.getAnimatedValue()))), this.alphas);
        View view0 = this.fadeOutView;
        if(view0 != null) {
            view0.setAlpha(this.alphas[0]);
        }
        View view1 = this.fadeInView;
        if(view1 != null) {
            view1.setAlpha(this.alphas[1]);
        }
    }
}

