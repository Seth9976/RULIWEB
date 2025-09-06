package com.google.android.material.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.View;
import android.view.ViewGroup;

public final class ScaleProvider implements VisibilityAnimatorProvider {
    private boolean growing;
    private float incomingEndScale;
    private float incomingStartScale;
    private float outgoingEndScale;
    private float outgoingStartScale;
    private boolean scaleOnDisappear;

    public ScaleProvider() {
        this(true);
    }

    public ScaleProvider(boolean z) {
        this.outgoingStartScale = 1.0f;
        this.outgoingEndScale = 1.1f;
        this.incomingStartScale = 0.8f;
        this.incomingEndScale = 1.0f;
        this.scaleOnDisappear = true;
        this.growing = z;
    }

    // 去混淆评级： 低(20)
    @Override  // com.google.android.material.transition.VisibilityAnimatorProvider
    public Animator createAppear(ViewGroup viewGroup0, View view0) {
        return this.growing ? ScaleProvider.createScaleAnimator(view0, this.incomingStartScale, this.incomingEndScale) : ScaleProvider.createScaleAnimator(view0, this.outgoingEndScale, this.outgoingStartScale);
    }

    @Override  // com.google.android.material.transition.VisibilityAnimatorProvider
    public Animator createDisappear(ViewGroup viewGroup0, View view0) {
        if(!this.scaleOnDisappear) {
            return null;
        }
        return this.growing ? ScaleProvider.createScaleAnimator(view0, this.outgoingStartScale, this.outgoingEndScale) : ScaleProvider.createScaleAnimator(view0, this.incomingEndScale, this.incomingStartScale);
    }

    private static Animator createScaleAnimator(View view0, float f, float f1) {
        float f2 = view0.getScaleX();
        float f3 = view0.getScaleY();
        Animator animator0 = ObjectAnimator.ofPropertyValuesHolder(view0, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat(View.SCALE_X, new float[]{f2 * f, f2 * f1}), PropertyValuesHolder.ofFloat(View.SCALE_Y, new float[]{f * f3, f1 * f3})});
        ((ObjectAnimator)animator0).addListener(new AnimatorListenerAdapter() {
            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationEnd(Animator animator0) {
                view0.setScaleX(f2);
                view0.setScaleY(f3);
            }
        });
        return animator0;
    }

    public float getIncomingEndScale() {
        return this.incomingEndScale;
    }

    public float getIncomingStartScale() {
        return this.incomingStartScale;
    }

    public float getOutgoingEndScale() {
        return this.outgoingEndScale;
    }

    public float getOutgoingStartScale() {
        return this.outgoingStartScale;
    }

    public boolean isGrowing() {
        return this.growing;
    }

    public boolean isScaleOnDisappear() {
        return this.scaleOnDisappear;
    }

    public void setGrowing(boolean z) {
        this.growing = z;
    }

    public void setIncomingEndScale(float f) {
        this.incomingEndScale = f;
    }

    public void setIncomingStartScale(float f) {
        this.incomingStartScale = f;
    }

    public void setOutgoingEndScale(float f) {
        this.outgoingEndScale = f;
    }

    public void setOutgoingStartScale(float f) {
        this.outgoingStartScale = f;
    }

    public void setScaleOnDisappear(boolean z) {
        this.scaleOnDisappear = z;
    }
}

