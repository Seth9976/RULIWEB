package com.google.android.material.appbar;

import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import com.google.android.material.shape.MaterialShapeDrawable;

public final class AppBarLayout..ExternalSyntheticLambda1 implements ValueAnimator.AnimatorUpdateListener {
    public final AppBarLayout f$0;
    public final ColorStateList f$1;
    public final ColorStateList f$2;
    public final MaterialShapeDrawable f$3;
    public final Integer f$4;

    public AppBarLayout..ExternalSyntheticLambda1(AppBarLayout appBarLayout0, ColorStateList colorStateList0, ColorStateList colorStateList1, MaterialShapeDrawable materialShapeDrawable0, Integer integer0) {
        this.f$0 = appBarLayout0;
        this.f$1 = colorStateList0;
        this.f$2 = colorStateList1;
        this.f$3 = materialShapeDrawable0;
        this.f$4 = integer0;
    }

    @Override  // android.animation.ValueAnimator$AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator0) {
        this.f$0.lambda$initializeLiftOnScrollWithColor$0$com-google-android-material-appbar-AppBarLayout(this.f$1, this.f$2, this.f$3, this.f$4, valueAnimator0);
    }
}

