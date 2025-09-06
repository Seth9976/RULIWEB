package com.google.android.material.transition.platform;

import android.animation.Animator;
import android.transition.TransitionValues;
import android.view.View;
import android.view.ViewGroup;

public final class MaterialElevationScale extends MaterialVisibility {
    private static final float DEFAULT_SCALE = 0.85f;
    private final boolean growing;

    public MaterialElevationScale(boolean z) {
        super(MaterialElevationScale.createPrimaryAnimatorProvider(z), MaterialElevationScale.createSecondaryAnimatorProvider());
        this.growing = z;
    }

    @Override  // com.google.android.material.transition.platform.MaterialVisibility
    public void addAdditionalAnimatorProvider(VisibilityAnimatorProvider visibilityAnimatorProvider0) {
        super.addAdditionalAnimatorProvider(visibilityAnimatorProvider0);
    }

    @Override  // com.google.android.material.transition.platform.MaterialVisibility
    public void clearAdditionalAnimatorProvider() {
        super.clearAdditionalAnimatorProvider();
    }

    private static ScaleProvider createPrimaryAnimatorProvider(boolean z) {
        ScaleProvider scaleProvider0 = new ScaleProvider(z);
        scaleProvider0.setOutgoingEndScale(0.85f);
        scaleProvider0.setIncomingStartScale(0.85f);
        return scaleProvider0;
    }

    private static VisibilityAnimatorProvider createSecondaryAnimatorProvider() {
        return new FadeProvider();
    }

    @Override  // com.google.android.material.transition.platform.MaterialVisibility
    public VisibilityAnimatorProvider getPrimaryAnimatorProvider() {
        return super.getPrimaryAnimatorProvider();
    }

    @Override  // com.google.android.material.transition.platform.MaterialVisibility
    public VisibilityAnimatorProvider getSecondaryAnimatorProvider() {
        return super.getSecondaryAnimatorProvider();
    }

    public boolean isGrowing() {
        return this.growing;
    }

    @Override  // com.google.android.material.transition.platform.MaterialVisibility
    public Animator onAppear(ViewGroup viewGroup0, View view0, TransitionValues transitionValues0, TransitionValues transitionValues1) {
        return super.onAppear(viewGroup0, view0, transitionValues0, transitionValues1);
    }

    @Override  // com.google.android.material.transition.platform.MaterialVisibility
    public Animator onDisappear(ViewGroup viewGroup0, View view0, TransitionValues transitionValues0, TransitionValues transitionValues1) {
        return super.onDisappear(viewGroup0, view0, transitionValues0, transitionValues1);
    }

    @Override  // com.google.android.material.transition.platform.MaterialVisibility
    public boolean removeAdditionalAnimatorProvider(VisibilityAnimatorProvider visibilityAnimatorProvider0) {
        return super.removeAdditionalAnimatorProvider(visibilityAnimatorProvider0);
    }

    @Override  // com.google.android.material.transition.platform.MaterialVisibility
    public void setSecondaryAnimatorProvider(VisibilityAnimatorProvider visibilityAnimatorProvider0) {
        super.setSecondaryAnimatorProvider(visibilityAnimatorProvider0);
    }
}

