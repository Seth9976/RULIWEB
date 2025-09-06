package com.google.android.material.transition.platform;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.transition.TransitionValues;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.R.attr;
import com.google.android.material.animation.AnimationUtils;

public final class MaterialFade extends MaterialVisibility {
    private static final float DEFAULT_FADE_END_THRESHOLD_ENTER = 0.3f;
    private static final float DEFAULT_START_SCALE = 0.8f;
    private static final int DEFAULT_THEMED_INCOMING_DURATION_ATTR;
    private static final int DEFAULT_THEMED_INCOMING_EASING_ATTR;
    private static final int DEFAULT_THEMED_OUTGOING_DURATION_ATTR;
    private static final int DEFAULT_THEMED_OUTGOING_EASING_ATTR;

    static {
        MaterialFade.DEFAULT_THEMED_INCOMING_DURATION_ATTR = attr.motionDurationMedium4;
        MaterialFade.DEFAULT_THEMED_OUTGOING_DURATION_ATTR = attr.motionDurationShort3;
        MaterialFade.DEFAULT_THEMED_INCOMING_EASING_ATTR = attr.motionEasingEmphasizedDecelerateInterpolator;
        MaterialFade.DEFAULT_THEMED_OUTGOING_EASING_ATTR = attr.motionEasingEmphasizedAccelerateInterpolator;
    }

    public MaterialFade() {
        super(MaterialFade.createPrimaryAnimatorProvider(), MaterialFade.createSecondaryAnimatorProvider());
    }

    @Override  // com.google.android.material.transition.platform.MaterialVisibility
    public void addAdditionalAnimatorProvider(VisibilityAnimatorProvider visibilityAnimatorProvider0) {
        super.addAdditionalAnimatorProvider(visibilityAnimatorProvider0);
    }

    @Override  // com.google.android.material.transition.platform.MaterialVisibility
    public void clearAdditionalAnimatorProvider() {
        super.clearAdditionalAnimatorProvider();
    }

    private static FadeProvider createPrimaryAnimatorProvider() {
        FadeProvider fadeProvider0 = new FadeProvider();
        fadeProvider0.setIncomingEndThreshold(0.3f);
        return fadeProvider0;
    }

    private static VisibilityAnimatorProvider createSecondaryAnimatorProvider() {
        VisibilityAnimatorProvider visibilityAnimatorProvider0 = new ScaleProvider();
        ((ScaleProvider)visibilityAnimatorProvider0).setScaleOnDisappear(false);
        ((ScaleProvider)visibilityAnimatorProvider0).setIncomingStartScale(0.8f);
        return visibilityAnimatorProvider0;
    }

    @Override  // com.google.android.material.transition.platform.MaterialVisibility
    TimeInterpolator getDefaultEasingInterpolator(boolean z) {
        return AnimationUtils.LINEAR_INTERPOLATOR;
    }

    // 去混淆评级： 低(20)
    @Override  // com.google.android.material.transition.platform.MaterialVisibility
    int getDurationThemeAttrResId(boolean z) {
        return z ? MaterialFade.DEFAULT_THEMED_INCOMING_DURATION_ATTR : MaterialFade.DEFAULT_THEMED_OUTGOING_DURATION_ATTR;
    }

    // 去混淆评级： 低(20)
    @Override  // com.google.android.material.transition.platform.MaterialVisibility
    int getEasingThemeAttrResId(boolean z) {
        return z ? MaterialFade.DEFAULT_THEMED_INCOMING_EASING_ATTR : MaterialFade.DEFAULT_THEMED_OUTGOING_EASING_ATTR;
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

