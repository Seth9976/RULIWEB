package com.google.android.material.motion;

import android.animation.Animator.AnimatorListener;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.res.Resources;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewGroup;
import androidx.activity.BackEventCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import com.google.android.material.R.dimen;
import com.google.android.material.animation.AnimationUtils;

public class MaterialSideContainerBackHelper extends MaterialBackAnimationHelper {
    private final float maxScaleXDistanceGrow;
    private final float maxScaleXDistanceShrink;
    private final float maxScaleYDistance;

    public MaterialSideContainerBackHelper(View view0) {
        super(view0);
        Resources resources0 = view0.getResources();
        this.maxScaleXDistanceShrink = resources0.getDimension(dimen.m3_back_progress_side_container_max_scale_x_distance_shrink);
        this.maxScaleXDistanceGrow = resources0.getDimension(dimen.m3_back_progress_side_container_max_scale_x_distance_grow);
        this.maxScaleYDistance = resources0.getDimension(dimen.m3_back_progress_side_container_max_scale_y_distance);
    }

    public void cancelBackProgress() {
        if(super.onCancelBackProgress() == null) {
            return;
        }
        AnimatorSet animatorSet0 = new AnimatorSet();
        animatorSet0.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.view, View.SCALE_X, new float[]{1.0f}), ObjectAnimator.ofFloat(this.view, View.SCALE_Y, new float[]{1.0f})});
        if(this.view instanceof ViewGroup) {
            ViewGroup viewGroup0 = (ViewGroup)this.view;
            for(int v = 0; v < viewGroup0.getChildCount(); ++v) {
                animatorSet0.playTogether(new Animator[]{ObjectAnimator.ofFloat(viewGroup0.getChildAt(v), View.SCALE_Y, new float[]{1.0f})});
            }
        }
        animatorSet0.setDuration(((long)this.cancelDuration));
        animatorSet0.start();
    }

    private boolean checkAbsoluteGravity(int v, int v1) {
        return (GravityCompat.getAbsoluteGravity(v, ViewCompat.getLayoutDirection(this.view)) & v1) == v1;
    }

    public void finishBackProgress(BackEventCompat backEventCompat0, int v, Animator.AnimatorListener animator$AnimatorListener0, ValueAnimator.AnimatorUpdateListener valueAnimator$AnimatorUpdateListener0) {
        boolean z = backEventCompat0.getSwipeEdge() == 0;
        boolean z1 = this.checkAbsoluteGravity(v, 3);
        float f = ((float)this.view.getWidth()) * this.view.getScaleX() + ((float)this.getEdgeMargin(z1));
        View view0 = this.view;
        Property property0 = View.TRANSLATION_X;
        if(z1) {
            f = -f;
        }
        ObjectAnimator objectAnimator0 = ObjectAnimator.ofFloat(view0, property0, new float[]{f});
        if(valueAnimator$AnimatorUpdateListener0 != null) {
            objectAnimator0.addUpdateListener(valueAnimator$AnimatorUpdateListener0);
        }
        objectAnimator0.setInterpolator(new FastOutSlowInInterpolator());
        objectAnimator0.setDuration(((long)AnimationUtils.lerp(this.hideDurationMax, this.hideDurationMin, backEventCompat0.getProgress())));
        objectAnimator0.addListener(new AnimatorListenerAdapter() {
            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationEnd(Animator animator0) {
                MaterialSideContainerBackHelper.this.view.setTranslationX(0.0f);
                MaterialSideContainerBackHelper.this.updateBackProgress(0.0f, z, v);
            }
        });
        if(animator$AnimatorListener0 != null) {
            objectAnimator0.addListener(animator$AnimatorListener0);
        }
        objectAnimator0.start();
    }

    private int getEdgeMargin(boolean z) {
        ViewGroup.LayoutParams viewGroup$LayoutParams0 = this.view.getLayoutParams();
        if(viewGroup$LayoutParams0 instanceof ViewGroup.MarginLayoutParams) {
            return z ? ((ViewGroup.MarginLayoutParams)viewGroup$LayoutParams0).leftMargin : ((ViewGroup.MarginLayoutParams)viewGroup$LayoutParams0).rightMargin;
        }
        return 0;
    }

    public void startBackProgress(BackEventCompat backEventCompat0) {
        super.onStartBackProgress(backEventCompat0);
    }

    public void updateBackProgress(float f, boolean z, int v) {
        float f1 = this.interpolateProgress(f);
        boolean z1 = this.checkAbsoluteGravity(v, 3);
        boolean z2 = z == z1;
        int v2 = this.view.getWidth();
        int v3 = this.view.getHeight();
        float f2 = (float)v2;
        if(f2 > 0.0f && ((float)v3) > 0.0f) {
            float f3 = this.maxScaleXDistanceShrink / f2;
            float f4 = this.maxScaleXDistanceGrow / f2;
            float f5 = this.maxScaleYDistance / ((float)v3);
            View view0 = this.view;
            if(z1) {
                f2 = 0.0f;
            }
            view0.setPivotX(f2);
            if(!z2) {
                f4 = -f3;
            }
            float f6 = 0.0f + f1 * (f4 - 0.0f);
            this.view.setScaleX(f6 + 1.0f);
            float f7 = 0.0f + f1 * (f5 - 0.0f);
            this.view.setScaleY(1.0f - f7);
            if(this.view instanceof ViewGroup) {
                ViewGroup viewGroup0 = (ViewGroup)this.view;
                for(int v1 = 0; v1 < viewGroup0.getChildCount(); ++v1) {
                    View view1 = viewGroup0.getChildAt(v1);
                    view1.setPivotX(((float)(z1 ? v2 - view1.getRight() + view1.getWidth() : -view1.getLeft())));
                    view1.setPivotY(((float)(-view1.getTop())));
                    float f8 = z2 ? 1.0f - f6 : 1.0f;
                    view1.setScaleX(f8);
                    view1.setScaleY((1.0f - f7 == 0.0f ? 1.0f : (f6 + 1.0f) / (1.0f - f7) * f8));
                }
            }
        }
    }

    public void updateBackProgress(BackEventCompat backEventCompat0, int v) {
        if(super.onUpdateBackProgress(backEventCompat0) == null) {
            return;
        }
        this.updateBackProgress(backEventCompat0.getProgress(), backEventCompat0.getSwipeEdge() == 0, v);
    }
}

