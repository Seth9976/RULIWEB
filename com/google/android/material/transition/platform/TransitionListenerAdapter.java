package com.google.android.material.transition.platform;

import android.transition.Transition.TransitionListener;
import android.transition.Transition;

abstract class TransitionListenerAdapter implements Transition.TransitionListener {
    @Override  // android.transition.Transition$TransitionListener
    public void onTransitionCancel(Transition transition0) {
    }

    @Override  // android.transition.Transition$TransitionListener
    public void onTransitionEnd(Transition transition0) {
    }

    @Override  // android.transition.Transition$TransitionListener
    public void onTransitionPause(Transition transition0) {
    }

    @Override  // android.transition.Transition$TransitionListener
    public void onTransitionResume(Transition transition0) {
    }

    @Override  // android.transition.Transition$TransitionListener
    public void onTransitionStart(Transition transition0) {
    }
}

