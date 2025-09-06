package com.google.android.material.transition.platform;

import android.animation.Animator;
import android.view.View;
import android.view.ViewGroup;

public interface VisibilityAnimatorProvider {
    Animator createAppear(ViewGroup arg1, View arg2);

    Animator createDisappear(ViewGroup arg1, View arg2);
}

