package com.google.android.material.transition;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import androidx.transition.TransitionValues;
import androidx.transition.Visibility;

public final class Hold extends Visibility {
    @Override  // androidx.transition.Visibility
    public Animator onAppear(ViewGroup viewGroup0, View view0, TransitionValues transitionValues0, TransitionValues transitionValues1) {
        return ValueAnimator.ofFloat(new float[]{0.0f});
    }

    @Override  // androidx.transition.Visibility
    public Animator onDisappear(ViewGroup viewGroup0, View view0, TransitionValues transitionValues0, TransitionValues transitionValues1) {
        return ValueAnimator.ofFloat(new float[]{0.0f});
    }
}

