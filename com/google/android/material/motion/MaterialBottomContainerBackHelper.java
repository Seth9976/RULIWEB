package com.google.android.material.motion;

import android.animation.Animator.AnimatorListener;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.BackEventCompat;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import com.google.android.material.R.dimen;
import com.google.android.material.animation.AnimationUtils;

public class MaterialBottomContainerBackHelper extends MaterialBackAnimationHelper {
    private final float maxScaleXDistance;
    private final float maxScaleYDistance;

    public MaterialBottomContainerBackHelper(View view0) {
        super(view0);
        Resources resources0 = view0.getResources();
        this.maxScaleXDistance = resources0.getDimension(dimen.m3_back_progress_bottom_container_max_scale_x_distance);
        this.maxScaleYDistance = resources0.getDimension(dimen.m3_back_progress_bottom_container_max_scale_y_distance);
    }

    public void cancelBackProgress() {
        if(super.onCancelBackProgress() == null) {
            return;
        }
        Animator animator0 = this.createResetScaleAnimator();
        animator0.setDuration(((long)this.cancelDuration));
        animator0.start();
    }

    private Animator createResetScaleAnimator() {
        Animator animator0 = new AnimatorSet();
        ((AnimatorSet)animator0).playTogether(new Animator[]{ObjectAnimator.ofFloat(this.view, View.SCALE_X, new float[]{1.0f}), ObjectAnimator.ofFloat(this.view, View.SCALE_Y, new float[]{1.0f})});
        if(this.view instanceof ViewGroup) {
            ViewGroup viewGroup0 = (ViewGroup)this.view;
            for(int v = 0; v < viewGroup0.getChildCount(); ++v) {
                ((AnimatorSet)animator0).playTogether(new Animator[]{ObjectAnimator.ofFloat(viewGroup0.getChildAt(v), View.SCALE_Y, new float[]{1.0f})});
            }
        }
        ((AnimatorSet)animator0).setInterpolator(new FastOutSlowInInterpolator());
        return animator0;
    }

    public void finishBackProgressNotPersistent(BackEventCompat backEventCompat0, Animator.AnimatorListener animator$AnimatorListener0) {
        float[] arr_f = {((float)this.view.getHeight()) * this.view.getScaleY()};
        ObjectAnimator objectAnimator0 = ObjectAnimator.ofFloat(this.view, View.TRANSLATION_Y, arr_f);
        objectAnimator0.setInterpolator(new FastOutSlowInInterpolator());
        objectAnimator0.setDuration(((long)AnimationUtils.lerp(this.hideDurationMax, this.hideDurationMin, backEventCompat0.getProgress())));
        objectAnimator0.addListener(new AnimatorListenerAdapter() {
            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationEnd(Animator animator0) {
                MaterialBottomContainerBackHelper.this.view.setTranslationY(0.0f);
                MaterialBottomContainerBackHelper.this.updateBackProgress(0.0f);
            }
        });
        if(animator$AnimatorListener0 != null) {
            objectAnimator0.addListener(animator$AnimatorListener0);
        }
        objectAnimator0.start();
    }

    public void finishBackProgressPersistent(BackEventCompat backEventCompat0, Animator.AnimatorListener animator$AnimatorListener0) {
        Animator animator0 = this.createResetScaleAnimator();
        animator0.setDuration(((long)AnimationUtils.lerp(this.hideDurationMax, this.hideDurationMin, backEventCompat0.getProgress())));
        if(animator$AnimatorListener0 != null) {
            animator0.addListener(animator$AnimatorListener0);
        }
        animator0.start();
    }

    public void startBackProgress(BackEventCompat backEventCompat0) {
        super.onStartBackProgress(backEventCompat0);
    }

    public void updateBackProgress(float f) {
        float f1 = this.interpolateProgress(f);
        float f2 = (float)this.view.getWidth();
        float f3 = (float)this.view.getHeight();
        if(f2 > 0.0f && f3 > 0.0f) {
            float f4 = AnimationUtils.lerp(0.0f, this.maxScaleXDistance / f2, f1);
            float f5 = AnimationUtils.lerp(0.0f, this.maxScaleYDistance / f3, f1);
            this.view.setScaleX(1.0f - f4);
            this.view.setPivotY(f3);
            this.view.setScaleY(1.0f - f5);
            if(this.view instanceof ViewGroup) {
                ViewGroup viewGroup0 = (ViewGroup)this.view;
                for(int v = 0; v < viewGroup0.getChildCount(); ++v) {
                    View view0 = viewGroup0.getChildAt(v);
                    view0.setPivotY(((float)(-view0.getTop())));
                    view0.setScaleY((1.0f - f5 == 0.0f ? 1.0f : (1.0f - f4) / (1.0f - f5)));
                }
            }
        }
    }

    public void updateBackProgress(BackEventCompat backEventCompat0) {
        if(super.onUpdateBackProgress(backEventCompat0) == null) {
            return;
        }
        this.updateBackProgress(backEventCompat0.getProgress());
    }
}

