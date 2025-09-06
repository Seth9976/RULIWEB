package com.google.android.material.floatingactionbutton;

import android.animation.Animator.AnimatorListener;
import android.animation.Animator;
import android.animation.AnimatorSet;
import com.google.android.material.animation.MotionSpec;
import java.util.List;

interface MotionStrategy {
    void addAnimationListener(Animator.AnimatorListener arg1);

    AnimatorSet createAnimator();

    MotionSpec getCurrentMotionSpec();

    int getDefaultMotionSpecResource();

    List getListeners();

    MotionSpec getMotionSpec();

    void onAnimationCancel();

    void onAnimationEnd();

    void onAnimationStart(Animator arg1);

    void onChange(OnChangedCallback arg1);

    void performNow();

    void removeAnimationListener(Animator.AnimatorListener arg1);

    void setMotionSpec(MotionSpec arg1);

    boolean shouldCancel();
}

