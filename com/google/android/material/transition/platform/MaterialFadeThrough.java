package com.google.android.material.transition.platform;

import android.animation.Animator;
import android.transition.TransitionValues;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.R.attr;

public final class MaterialFadeThrough extends MaterialVisibility {
    private static final float DEFAULT_START_SCALE = 0.92f;
    private static final int DEFAULT_THEMED_DURATION_ATTR;
    private static final int DEFAULT_THEMED_EASING_ATTR;

    static {
        MaterialFadeThrough.DEFAULT_THEMED_DURATION_ATTR = attr.motionDurationLong1;
        MaterialFadeThrough.DEFAULT_THEMED_EASING_ATTR = attr.motionEasingEmphasizedInterpolator;
    }

    public MaterialFadeThrough() {
        super(MaterialFadeThrough.createPrimaryAnimatorProvider(), MaterialFadeThrough.createSecondaryAnimatorProvider());
    }

    @Override  // com.google.android.material.transition.platform.MaterialVisibility
    public void addAdditionalAnimatorProvider(VisibilityAnimatorProvider visibilityAnimatorProvider0) {
        super.addAdditionalAnimatorProvider(visibilityAnimatorProvider0);
    }

    @Override  // com.google.android.material.transition.platform.MaterialVisibility
    public void clearAdditionalAnimatorProvider() {
        super.clearAdditionalAnimatorProvider();
    }

    private static FadeThroughProvider createPrimaryAnimatorProvider() {
        return new FadeThroughProvider();
    }

    private static VisibilityAnimatorProvider createSecondaryAnimatorProvider() {
        VisibilityAnimatorProvider visibilityAnimatorProvider0 = new ScaleProvider();
        ((ScaleProvider)visibilityAnimatorProvider0).setScaleOnDisappear(false);
        ((ScaleProvider)visibilityAnimatorProvider0).setIncomingStartScale(0.92f);
        return visibilityAnimatorProvider0;
    }

    @Override  // com.google.android.material.transition.platform.MaterialVisibility
    int getDurationThemeAttrResId(boolean z) {
        return MaterialFadeThrough.DEFAULT_THEMED_DURATION_ATTR;
    }

    @Override  // com.google.android.material.transition.platform.MaterialVisibility
    int getEasingThemeAttrResId(boolean z) {
        return MaterialFadeThrough.DEFAULT_THEMED_EASING_ATTR;
    }

    @Override  // com.google.android.material.transition.platform.MaterialVisibility
    public VisibilityAnimatorProvider getPrimaryAnimatorProvider() {
        return super.getPrimaryAnimatorProvider();
    }

    @Override  // com.google.android.material.transition.platform.MaterialVisibility
    public VisibilityAnimatorProvider getSecondaryAnimatorProvider() {
        return super.getSecondaryAnimatorProvider();
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

