package com.google.android.material.internal;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.view.View;
import com.google.android.material.animation.AnimationUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ExpandCollapseAnimationHelper {
    private ValueAnimator.AnimatorUpdateListener additionalUpdateListener;
    private final View collapsedView;
    private int collapsedViewOffsetY;
    private long duration;
    private final List endAnchoredViews;
    private final View expandedView;
    private int expandedViewOffsetY;
    private final List listeners;

    public ExpandCollapseAnimationHelper(View view0, View view1) {
        this.collapsedView = view0;
        this.expandedView = view1;
        this.listeners = new ArrayList();
        this.endAnchoredViews = new ArrayList();
    }

    public ExpandCollapseAnimationHelper addEndAnchoredViews(Collection collection0) {
        this.endAnchoredViews.addAll(collection0);
        return this;
    }

    public ExpandCollapseAnimationHelper addEndAnchoredViews(View[] arr_view) {
        Collections.addAll(this.endAnchoredViews, arr_view);
        return this;
    }

    public ExpandCollapseAnimationHelper addListener(AnimatorListenerAdapter animatorListenerAdapter0) {
        this.listeners.add(animatorListenerAdapter0);
        return this;
    }

    private void addListeners(Animator animator0, List list0) {
        for(Object object0: list0) {
            animator0.addListener(((AnimatorListenerAdapter)object0));
        }
    }

    private AnimatorSet getAnimatorSet(boolean z) {
        AnimatorSet animatorSet0 = new AnimatorSet();
        animatorSet0.playTogether(new Animator[]{this.getExpandCollapseAnimator(z), this.getExpandedViewChildrenAlphaAnimator(z), this.getEndAnchoredViewsTranslateAnimator(z)});
        return animatorSet0;
    }

    public Animator getCollapseAnimator() {
        Animator animator0 = this.getAnimatorSet(false);
        animator0.addListener(new AnimatorListenerAdapter() {
            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationEnd(Animator animator0) {
                ExpandCollapseAnimationHelper.this.expandedView.setVisibility(8);
            }
        });
        this.addListeners(animator0, this.listeners);
        return animator0;
    }

    private Animator getEndAnchoredViewsTranslateAnimator(boolean z) {
        Animator animator0 = ValueAnimator.ofFloat(new float[]{((float)(this.expandedView.getLeft() - this.collapsedView.getLeft() + (this.collapsedView.getRight() - this.expandedView.getRight()))), 0.0f});
        ((ValueAnimator)animator0).addUpdateListener(MultiViewUpdateListener.translationXListener(this.endAnchoredViews));
        ((ValueAnimator)animator0).setDuration(this.duration);
        ((ValueAnimator)animator0).setInterpolator(ReversableAnimatedValueInterpolator.of(z, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
        return animator0;
    }

    public Animator getExpandAnimator() {
        Animator animator0 = this.getAnimatorSet(true);
        animator0.addListener(new AnimatorListenerAdapter() {
            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationStart(Animator animator0) {
                ExpandCollapseAnimationHelper.this.expandedView.setVisibility(0);
            }
        });
        this.addListeners(animator0, this.listeners);
        return animator0;
    }

    private Animator getExpandCollapseAnimator(boolean z) {
        Rect rect0 = ViewUtils.calculateRectFromBounds(this.collapsedView, this.collapsedViewOffsetY);
        Rect rect1 = ViewUtils.calculateRectFromBounds(this.expandedView, this.expandedViewOffsetY);
        Rect rect2 = new Rect(rect0);
        Animator animator0 = ValueAnimator.ofObject(new RectEvaluator(rect2), new Object[]{rect0, rect1});
        ((ValueAnimator)animator0).addUpdateListener((ValueAnimator valueAnimator0) -> ViewUtils.setBoundsFromRect(this.expandedView, rect2));
        ValueAnimator.AnimatorUpdateListener valueAnimator$AnimatorUpdateListener0 = this.additionalUpdateListener;
        if(valueAnimator$AnimatorUpdateListener0 != null) {
            ((ValueAnimator)animator0).addUpdateListener(valueAnimator$AnimatorUpdateListener0);
        }
        ((ValueAnimator)animator0).setDuration(this.duration);
        ((ValueAnimator)animator0).setInterpolator(ReversableAnimatedValueInterpolator.of(z, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
        return animator0;
    }

    private Animator getExpandedViewChildrenAlphaAnimator(boolean z) {
        List list0 = ViewUtils.getChildren(this.expandedView);
        Animator animator0 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        ((ValueAnimator)animator0).addUpdateListener(MultiViewUpdateListener.alphaListener(list0));
        ((ValueAnimator)animator0).setDuration(this.duration);
        ((ValueAnimator)animator0).setInterpolator(ReversableAnimatedValueInterpolator.of(z, AnimationUtils.LINEAR_INTERPOLATOR));
        return animator0;
    }

    // 检测为 Lambda 实现
    void lambda$getExpandCollapseAnimator$0$com-google-android-material-internal-ExpandCollapseAnimationHelper(Rect rect0, ValueAnimator valueAnimator0) [...]

    public ExpandCollapseAnimationHelper setAdditionalUpdateListener(ValueAnimator.AnimatorUpdateListener valueAnimator$AnimatorUpdateListener0) {
        this.additionalUpdateListener = valueAnimator$AnimatorUpdateListener0;
        return this;
    }

    public ExpandCollapseAnimationHelper setCollapsedViewOffsetY(int v) {
        this.collapsedViewOffsetY = v;
        return this;
    }

    public ExpandCollapseAnimationHelper setDuration(long v) {
        this.duration = v;
        return this;
    }

    public ExpandCollapseAnimationHelper setExpandedViewOffsetY(int v) {
        this.expandedViewOffsetY = v;
        return this;
    }
}

