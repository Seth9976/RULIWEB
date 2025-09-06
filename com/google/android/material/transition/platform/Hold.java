package com.google.android.material.transition.platform;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.transition.TransitionValues;
import android.transition.Visibility;
import android.view.View;
import android.view.ViewGroup;

public final class Hold extends Visibility {
    @Override  // android.transition.Visibility
    public Animator onAppear(ViewGroup viewGroup0, View view0, TransitionValues transitionValues0, TransitionValues transitionValues1) {
        return ValueAnimator.ofFloat(new float[]{0.0f});
    }

    @Override  // android.transition.Visibility
    public Animator onDisappear(ViewGroup viewGroup0, View view0, TransitionValues transitionValues0, TransitionValues transitionValues1) {
        return ValueAnimator.ofFloat(new float[]{0.0f});
    }
}

