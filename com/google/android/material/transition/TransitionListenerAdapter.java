package com.google.android.material.transition;

import androidx.transition.Transition.TransitionListener.-CC;
import androidx.transition.Transition.TransitionListener;
import androidx.transition.Transition;

abstract class TransitionListenerAdapter implements TransitionListener {
    @Override  // androidx.transition.Transition$TransitionListener
    public void onTransitionCancel(Transition transition0) {
    }

    @Override  // androidx.transition.Transition$TransitionListener
    public void onTransitionEnd(Transition transition0) {
    }

    @Override  // androidx.transition.Transition$TransitionListener
    public void onTransitionEnd(Transition transition0, boolean z) {
        Transition.TransitionListener.-CC.$default$onTransitionEnd(this, transition0, z);
    }

    @Override  // androidx.transition.Transition$TransitionListener
    public void onTransitionPause(Transition transition0) {
    }

    @Override  // androidx.transition.Transition$TransitionListener
    public void onTransitionResume(Transition transition0) {
    }

    @Override  // androidx.transition.Transition$TransitionListener
    public void onTransitionStart(Transition transition0) {
    }

    @Override  // androidx.transition.Transition$TransitionListener
    public void onTransitionStart(Transition transition0, boolean z) {
        Transition.TransitionListener.-CC.$default$onTransitionStart(this, transition0, z);
    }
}

