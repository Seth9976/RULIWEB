package com.google.android.material.transformation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

@Deprecated
public abstract class ExpandableTransformationBehavior extends ExpandableBehavior {
    private AnimatorSet currentAnimation;

    public ExpandableTransformationBehavior() {
    }

    public ExpandableTransformationBehavior(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
    }

    protected abstract AnimatorSet onCreateExpandedStateChangeAnimation(View arg1, View arg2, boolean arg3, boolean arg4);

    @Override  // com.google.android.material.transformation.ExpandableBehavior
    protected boolean onExpandedStateChange(View view0, View view1, boolean z, boolean z1) {
        AnimatorSet animatorSet0 = this.currentAnimation;
        if(animatorSet0 != null) {
            animatorSet0.cancel();
        }
        AnimatorSet animatorSet1 = this.onCreateExpandedStateChangeAnimation(view0, view1, z, animatorSet0 != null);
        this.currentAnimation = animatorSet1;
        animatorSet1.addListener(new AnimatorListenerAdapter() {
            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationEnd(Animator animator0) {
                ExpandableTransformationBehavior.this.currentAnimation = null;
            }
        });
        this.currentAnimation.start();
        if(!z1) {
            this.currentAnimation.end();
        }
        return true;
    }
}

