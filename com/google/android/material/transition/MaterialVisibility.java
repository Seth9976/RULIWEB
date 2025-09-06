package com.google.android.material.transition;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.transition.TransitionValues;
import androidx.transition.Visibility;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.animation.AnimatorSetCompat;
import java.util.ArrayList;
import java.util.List;

abstract class MaterialVisibility extends Visibility {
    private final List additionalAnimatorProviders;
    private final VisibilityAnimatorProvider primaryAnimatorProvider;
    private VisibilityAnimatorProvider secondaryAnimatorProvider;

    protected MaterialVisibility(VisibilityAnimatorProvider visibilityAnimatorProvider0, VisibilityAnimatorProvider visibilityAnimatorProvider1) {
        this.additionalAnimatorProviders = new ArrayList();
        this.primaryAnimatorProvider = visibilityAnimatorProvider0;
        this.secondaryAnimatorProvider = visibilityAnimatorProvider1;
    }

    public void addAdditionalAnimatorProvider(VisibilityAnimatorProvider visibilityAnimatorProvider0) {
        this.additionalAnimatorProviders.add(visibilityAnimatorProvider0);
    }

    private static void addAnimatorIfNeeded(List list0, VisibilityAnimatorProvider visibilityAnimatorProvider0, ViewGroup viewGroup0, View view0, boolean z) {
        if(visibilityAnimatorProvider0 != null) {
            Animator animator0 = z ? visibilityAnimatorProvider0.createAppear(viewGroup0, view0) : visibilityAnimatorProvider0.createDisappear(viewGroup0, view0);
            if(animator0 != null) {
                list0.add(animator0);
            }
        }
    }

    public void clearAdditionalAnimatorProvider() {
        this.additionalAnimatorProviders.clear();
    }

    private Animator createAnimator(ViewGroup viewGroup0, View view0, boolean z) {
        Animator animator0 = new AnimatorSet();
        ArrayList arrayList0 = new ArrayList();
        MaterialVisibility.addAnimatorIfNeeded(arrayList0, this.primaryAnimatorProvider, viewGroup0, view0, z);
        MaterialVisibility.addAnimatorIfNeeded(arrayList0, this.secondaryAnimatorProvider, viewGroup0, view0, z);
        for(Object object0: this.additionalAnimatorProviders) {
            MaterialVisibility.addAnimatorIfNeeded(arrayList0, ((VisibilityAnimatorProvider)object0), viewGroup0, view0, z);
        }
        this.maybeApplyThemeValues(viewGroup0.getContext(), z);
        AnimatorSetCompat.playTogether(((AnimatorSet)animator0), arrayList0);
        return animator0;
    }

    TimeInterpolator getDefaultEasingInterpolator(boolean z) {
        return AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR;
    }

    int getDurationThemeAttrResId(boolean z) {
        return 0;
    }

    int getEasingThemeAttrResId(boolean z) {
        return 0;
    }

    public VisibilityAnimatorProvider getPrimaryAnimatorProvider() {
        return this.primaryAnimatorProvider;
    }

    public VisibilityAnimatorProvider getSecondaryAnimatorProvider() {
        return this.secondaryAnimatorProvider;
    }

    @Override  // androidx.transition.Transition
    public boolean isSeekingSupported() {
        return true;
    }

    private void maybeApplyThemeValues(Context context0, boolean z) {
        TransitionUtils.maybeApplyThemeDuration(this, context0, this.getDurationThemeAttrResId(z));
        TimeInterpolator timeInterpolator0 = this.getDefaultEasingInterpolator(z);
        TransitionUtils.maybeApplyThemeInterpolator(this, context0, this.getEasingThemeAttrResId(z), timeInterpolator0);
    }

    @Override  // androidx.transition.Visibility
    public Animator onAppear(ViewGroup viewGroup0, View view0, TransitionValues transitionValues0, TransitionValues transitionValues1) {
        return this.createAnimator(viewGroup0, view0, true);
    }

    @Override  // androidx.transition.Visibility
    public Animator onDisappear(ViewGroup viewGroup0, View view0, TransitionValues transitionValues0, TransitionValues transitionValues1) {
        return this.createAnimator(viewGroup0, view0, false);
    }

    public boolean removeAdditionalAnimatorProvider(VisibilityAnimatorProvider visibilityAnimatorProvider0) {
        return this.additionalAnimatorProviders.remove(visibilityAnimatorProvider0);
    }

    public void setSecondaryAnimatorProvider(VisibilityAnimatorProvider visibilityAnimatorProvider0) {
        this.secondaryAnimatorProvider = visibilityAnimatorProvider0;
    }
}

