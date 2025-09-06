package com.google.android.material.search;

import android.animation.Animator;
import com.google.android.material.animation.AnimatableView.Listener;

public final class SearchBarAnimationHelper..ExternalSyntheticLambda2 implements Listener {
    public final Animator f$0;

    public SearchBarAnimationHelper..ExternalSyntheticLambda2(Animator animator0) {
        this.f$0 = animator0;
    }

    @Override  // com.google.android.material.animation.AnimatableView$Listener
    public final void onAnimationEnd() {
        this.f$0.start();
    }
}

