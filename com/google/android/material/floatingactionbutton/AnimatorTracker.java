package com.google.android.material.floatingactionbutton;

import android.animation.Animator;

class AnimatorTracker {
    private Animator currentAnimator;

    public void cancelCurrent() {
        Animator animator0 = this.currentAnimator;
        if(animator0 != null) {
            animator0.cancel();
        }
    }

    public void clear() {
        this.currentAnimator = null;
    }

    public void onNextAnimationStart(Animator animator0) {
        this.cancelCurrent();
        this.currentAnimator = animator0;
    }
}

