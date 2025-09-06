package com.google.android.material.motion;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.view.RoundedCorner;
import android.view.View;
import android.view.WindowInsets;
import androidx.activity.BackEventCompat;
import com.google.android.material.R.dimen;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.internal.ClippableRoundedCornerLayout;
import com.google.android.material.internal.ViewUtils;

public class MaterialMainContainerBackHelper extends MaterialBackAnimationHelper {
    private static final float MIN_SCALE = 0.9f;
    private Integer expandedCornerSize;
    private Rect initialHideFromClipBounds;
    private Rect initialHideToClipBounds;
    private float initialTouchY;
    private final float maxTranslationY;
    private final float minEdgeGap;

    public MaterialMainContainerBackHelper(View view0) {
        super(view0);
        Resources resources0 = view0.getResources();
        this.minEdgeGap = resources0.getDimension(dimen.m3_back_progress_main_container_min_edge_gap);
        this.maxTranslationY = resources0.getDimension(dimen.m3_back_progress_main_container_max_translation_y);
    }

    public void cancelBackProgress(View view0) {
        if(super.onCancelBackProgress() == null) {
            return;
        }
        AnimatorSet animatorSet0 = this.createResetScaleAndTranslationAnimator(view0);
        if(this.view instanceof ClippableRoundedCornerLayout) {
            animatorSet0.playTogether(new Animator[]{this.createCornerAnimator(((ClippableRoundedCornerLayout)this.view))});
        }
        animatorSet0.setDuration(((long)this.cancelDuration));
        animatorSet0.start();
        this.resetInitialValues();
    }

    private ValueAnimator createCornerAnimator(ClippableRoundedCornerLayout clippableRoundedCornerLayout0) {
        ValueAnimator valueAnimator0 = ValueAnimator.ofFloat(new float[]{clippableRoundedCornerLayout0.getCornerRadius(), ((float)this.getExpandedCornerSize())});
        valueAnimator0.addUpdateListener((ValueAnimator valueAnimator0) -> clippableRoundedCornerLayout0.updateCornerRadius(((float)(((Float)valueAnimator0.getAnimatedValue())))));
        return valueAnimator0;
    }

    private AnimatorSet createResetScaleAndTranslationAnimator(View view0) {
        AnimatorSet animatorSet0 = new AnimatorSet();
        animatorSet0.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.view, View.SCALE_X, new float[]{1.0f}), ObjectAnimator.ofFloat(this.view, View.SCALE_Y, new float[]{1.0f}), ObjectAnimator.ofFloat(this.view, View.TRANSLATION_X, new float[]{0.0f}), ObjectAnimator.ofFloat(this.view, View.TRANSLATION_Y, new float[]{0.0f})});
        animatorSet0.addListener(new AnimatorListenerAdapter() {
            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationEnd(Animator animator0) {
                View view0 = view0;
                if(view0 != null) {
                    view0.setVisibility(0);
                }
            }
        });
        return animatorSet0;
    }

    public void finishBackProgress(long v, View view0) {
        AnimatorSet animatorSet0 = this.createResetScaleAndTranslationAnimator(view0);
        animatorSet0.setDuration(v);
        animatorSet0.start();
        this.resetInitialValues();
    }

    public int getExpandedCornerSize() {
        if(this.expandedCornerSize == null) {
            this.expandedCornerSize = (int)(this.isAtTopOfScreen() ? this.getMaxDeviceCornerRadius() : 0);
        }
        return (int)this.expandedCornerSize;
    }

    public Rect getInitialHideFromClipBounds() {
        return this.initialHideFromClipBounds;
    }

    public Rect getInitialHideToClipBounds() {
        return this.initialHideToClipBounds;
    }

    private int getMaxDeviceCornerRadius() {
        if(Build.VERSION.SDK_INT >= 0x1F) {
            WindowInsets windowInsets0 = this.view.getRootWindowInsets();
            return windowInsets0 == null ? 0 : Math.max(Math.max(this.getRoundedCornerRadius(windowInsets0, 0), this.getRoundedCornerRadius(windowInsets0, 1)), Math.max(this.getRoundedCornerRadius(windowInsets0, 3), this.getRoundedCornerRadius(windowInsets0, 2)));
        }
        return 0;
    }

    private int getRoundedCornerRadius(WindowInsets windowInsets0, int v) {
        RoundedCorner roundedCorner0 = windowInsets0.getRoundedCorner(v);
        return roundedCorner0 == null ? 0 : roundedCorner0.getRadius();
    }

    private boolean isAtTopOfScreen() {
        int[] arr_v = new int[2];
        this.view.getLocationOnScreen(arr_v);
        return arr_v[1] == 0;
    }

    // 检测为 Lambda 实现
    static void lambda$createCornerAnimator$0(ClippableRoundedCornerLayout clippableRoundedCornerLayout0, ValueAnimator valueAnimator0) [...]

    private void resetInitialValues() {
        this.initialTouchY = 0.0f;
        this.initialHideToClipBounds = null;
        this.initialHideFromClipBounds = null;
    }

    public void startBackProgress(float f, View view0) {
        this.initialHideToClipBounds = ViewUtils.calculateRectFromBounds(this.view);
        if(view0 != null) {
            this.initialHideFromClipBounds = ViewUtils.calculateOffsetRectFromBounds(this.view, view0);
        }
        this.initialTouchY = f;
    }

    public void startBackProgress(BackEventCompat backEventCompat0, View view0) {
        super.onStartBackProgress(backEventCompat0);
        this.startBackProgress(backEventCompat0.getTouchY(), view0);
    }

    public void updateBackProgress(float f, boolean z, float f1, float f2) {
        float f3 = this.interpolateProgress(f);
        float f4 = (float)this.view.getWidth();
        float f5 = (float)this.view.getHeight();
        if(f4 > 0.0f && f5 > 0.0f) {
            float f6 = 1.0f + f3 * -0.1f;
            float f7 = f1 - this.initialTouchY;
            this.view.setScaleX(f6);
            this.view.setScaleY(f6);
            this.view.setTranslationX((0.0f + f3 * (Math.max(0.0f, (f4 - 0.9f * f4) / 2.0f - this.minEdgeGap) - 0.0f)) * ((float)(z ? 1 : -1)));
            this.view.setTranslationY(AnimationUtils.lerp(0.0f, Math.min(Math.max(0.0f, (f5 - f6 * f5) / 2.0f - this.minEdgeGap), this.maxTranslationY), Math.abs(f7) / f5) * Math.signum(f7));
            if(this.view instanceof ClippableRoundedCornerLayout) {
                ((ClippableRoundedCornerLayout)this.view).updateCornerRadius(AnimationUtils.lerp(this.getExpandedCornerSize(), f2, f3));
            }
        }
    }

    public void updateBackProgress(BackEventCompat backEventCompat0, View view0, float f) {
        if(super.onUpdateBackProgress(backEventCompat0) == null) {
            return;
        }
        if(view0 != null && view0.getVisibility() != 4) {
            view0.setVisibility(4);
        }
        this.updateBackProgress(backEventCompat0.getProgress(), backEventCompat0.getSwipeEdge() == 0, backEventCompat0.getTouchY(), f);
    }
}

