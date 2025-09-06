package com.google.android.material.transition;

import android.animation.Animator;
import android.view.View;
import android.view.ViewGroup;
import androidx.transition.TransitionValues;
import com.google.android.material.R.attr;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class MaterialSharedAxis extends MaterialVisibility {
    @Retention(RetentionPolicy.SOURCE)
    public @interface Axis {
    }

    private static final int DEFAULT_THEMED_DURATION_ATTR = 0;
    private static final int DEFAULT_THEMED_EASING_ATTR = 0;
    public static final int X = 0;
    public static final int Y = 1;
    public static final int Z = 2;
    private final int axis;
    private final boolean forward;

    static {
        MaterialSharedAxis.DEFAULT_THEMED_DURATION_ATTR = attr.motionDurationLong1;
        MaterialSharedAxis.DEFAULT_THEMED_EASING_ATTR = attr.motionEasingEmphasizedInterpolator;
    }

    public MaterialSharedAxis(int v, boolean z) {
        super(MaterialSharedAxis.createPrimaryAnimatorProvider(v, z), MaterialSharedAxis.createSecondaryAnimatorProvider());
        this.axis = v;
        this.forward = z;
    }

    @Override  // com.google.android.material.transition.MaterialVisibility
    public void addAdditionalAnimatorProvider(VisibilityAnimatorProvider visibilityAnimatorProvider0) {
        super.addAdditionalAnimatorProvider(visibilityAnimatorProvider0);
    }

    @Override  // com.google.android.material.transition.MaterialVisibility
    public void clearAdditionalAnimatorProvider() {
        super.clearAdditionalAnimatorProvider();
    }

    private static VisibilityAnimatorProvider createPrimaryAnimatorProvider(int v, boolean z) {
        switch(v) {
            case 0: {
                return z ? new SlideDistanceProvider(0x800005) : new SlideDistanceProvider(0x800003);
            }
            case 1: {
                return z ? new SlideDistanceProvider(80) : new SlideDistanceProvider(0x30);
            }
            case 2: {
                return new ScaleProvider(z);
            }
            default: {
                throw new IllegalArgumentException("Invalid axis: " + v);
            }
        }
    }

    private static VisibilityAnimatorProvider createSecondaryAnimatorProvider() {
        return new FadeThroughProvider();
    }

    public int getAxis() {
        return this.axis;
    }

    @Override  // com.google.android.material.transition.MaterialVisibility
    int getDurationThemeAttrResId(boolean z) {
        return MaterialSharedAxis.DEFAULT_THEMED_DURATION_ATTR;
    }

    @Override  // com.google.android.material.transition.MaterialVisibility
    int getEasingThemeAttrResId(boolean z) {
        return MaterialSharedAxis.DEFAULT_THEMED_EASING_ATTR;
    }

    @Override  // com.google.android.material.transition.MaterialVisibility
    public VisibilityAnimatorProvider getPrimaryAnimatorProvider() {
        return super.getPrimaryAnimatorProvider();
    }

    @Override  // com.google.android.material.transition.MaterialVisibility
    public VisibilityAnimatorProvider getSecondaryAnimatorProvider() {
        return super.getSecondaryAnimatorProvider();
    }

    public boolean isForward() {
        return this.forward;
    }

    @Override  // com.google.android.material.transition.MaterialVisibility
    public boolean isSeekingSupported() {
        return super.isSeekingSupported();
    }

    @Override  // com.google.android.material.transition.MaterialVisibility
    public Animator onAppear(ViewGroup viewGroup0, View view0, TransitionValues transitionValues0, TransitionValues transitionValues1) {
        return super.onAppear(viewGroup0, view0, transitionValues0, transitionValues1);
    }

    @Override  // com.google.android.material.transition.MaterialVisibility
    public Animator onDisappear(ViewGroup viewGroup0, View view0, TransitionValues transitionValues0, TransitionValues transitionValues1) {
        return super.onDisappear(viewGroup0, view0, transitionValues0, transitionValues1);
    }

    @Override  // com.google.android.material.transition.MaterialVisibility
    public boolean removeAdditionalAnimatorProvider(VisibilityAnimatorProvider visibilityAnimatorProvider0) {
        return super.removeAdditionalAnimatorProvider(visibilityAnimatorProvider0);
    }

    @Override  // com.google.android.material.transition.MaterialVisibility
    public void setSecondaryAnimatorProvider(VisibilityAnimatorProvider visibilityAnimatorProvider0) {
        super.setSecondaryAnimatorProvider(visibilityAnimatorProvider0);
    }
}

