package com.google.android.material.sidesheet;

import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;

public final class SideSheetBehavior..ExternalSyntheticLambda2 implements ValueAnimator.AnimatorUpdateListener {
    public final SideSheetBehavior f$0;
    public final ViewGroup.MarginLayoutParams f$1;
    public final int f$2;
    public final View f$3;

    public SideSheetBehavior..ExternalSyntheticLambda2(SideSheetBehavior sideSheetBehavior0, ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0, int v, View view0) {
        this.f$0 = sideSheetBehavior0;
        this.f$1 = viewGroup$MarginLayoutParams0;
        this.f$2 = v;
        this.f$3 = view0;
    }

    @Override  // android.animation.ValueAnimator$AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator0) {
        this.f$0.lambda$getCoplanarFinishAnimatorUpdateListener$1$com-google-android-material-sidesheet-SideSheetBehavior(this.f$1, this.f$2, this.f$3, valueAnimator0);
    }
}

