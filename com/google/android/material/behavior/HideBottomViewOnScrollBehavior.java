package com.google.android.material.behavior;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewPropertyAnimator;
import androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.R.attr;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.motion.MotionUtils;
import java.util.LinkedHashSet;

public class HideBottomViewOnScrollBehavior extends Behavior {
    public interface OnScrollStateChangedListener {
        void onStateChanged(View arg1, int arg2);
    }

    public @interface ScrollState {
    }

    private static final int DEFAULT_ENTER_ANIMATION_DURATION_MS = 0xE1;
    private static final int DEFAULT_EXIT_ANIMATION_DURATION_MS = 0xAF;
    private static final int ENTER_ANIM_DURATION_ATTR = 0;
    private static final int ENTER_EXIT_ANIM_EASING_ATTR = 0;
    private static final int EXIT_ANIM_DURATION_ATTR = 0;
    public static final int STATE_SCROLLED_DOWN = 1;
    public static final int STATE_SCROLLED_UP = 2;
    private int additionalHiddenOffsetY;
    private ViewPropertyAnimator currentAnimator;
    private int currentState;
    private int enterAnimDuration;
    private TimeInterpolator enterAnimInterpolator;
    private int exitAnimDuration;
    private TimeInterpolator exitAnimInterpolator;
    private int height;
    private final LinkedHashSet onScrollStateChangedListeners;

    static {
        HideBottomViewOnScrollBehavior.ENTER_ANIM_DURATION_ATTR = attr.motionDurationLong2;
        HideBottomViewOnScrollBehavior.EXIT_ANIM_DURATION_ATTR = attr.motionDurationMedium4;
        HideBottomViewOnScrollBehavior.ENTER_EXIT_ANIM_EASING_ATTR = attr.motionEasingEmphasizedInterpolator;
    }

    public HideBottomViewOnScrollBehavior() {
        this.onScrollStateChangedListeners = new LinkedHashSet();
        this.height = 0;
        this.currentState = 2;
        this.additionalHiddenOffsetY = 0;
    }

    public HideBottomViewOnScrollBehavior(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
        this.onScrollStateChangedListeners = new LinkedHashSet();
        this.height = 0;
        this.currentState = 2;
        this.additionalHiddenOffsetY = 0;
    }

    public void addOnScrollStateChangedListener(OnScrollStateChangedListener hideBottomViewOnScrollBehavior$OnScrollStateChangedListener0) {
        this.onScrollStateChangedListeners.add(hideBottomViewOnScrollBehavior$OnScrollStateChangedListener0);
    }

    private void animateChildTo(View view0, int v, long v1, TimeInterpolator timeInterpolator0) {
        this.currentAnimator = view0.animate().translationY(((float)v)).setInterpolator(timeInterpolator0).setDuration(v1).setListener(new AnimatorListenerAdapter() {
            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationEnd(Animator animator0) {
                HideBottomViewOnScrollBehavior.this.currentAnimator = null;
            }
        });
    }

    public void clearOnScrollStateChangedListeners() {
        this.onScrollStateChangedListeners.clear();
    }

    public boolean isScrolledDown() {
        return this.currentState == 1;
    }

    public boolean isScrolledUp() {
        return this.currentState == 2;
    }

    @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior
    public boolean onLayoutChild(CoordinatorLayout coordinatorLayout0, View view0, int v) {
        ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0 = (ViewGroup.MarginLayoutParams)view0.getLayoutParams();
        this.height = view0.getMeasuredHeight() + viewGroup$MarginLayoutParams0.bottomMargin;
        this.enterAnimDuration = MotionUtils.resolveThemeDuration(view0.getContext(), HideBottomViewOnScrollBehavior.ENTER_ANIM_DURATION_ATTR, 0xE1);
        this.exitAnimDuration = MotionUtils.resolveThemeDuration(view0.getContext(), HideBottomViewOnScrollBehavior.EXIT_ANIM_DURATION_ATTR, 0xAF);
        this.enterAnimInterpolator = MotionUtils.resolveThemeInterpolator(view0.getContext(), HideBottomViewOnScrollBehavior.ENTER_EXIT_ANIM_EASING_ATTR, AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR);
        this.exitAnimInterpolator = MotionUtils.resolveThemeInterpolator(view0.getContext(), HideBottomViewOnScrollBehavior.ENTER_EXIT_ANIM_EASING_ATTR, AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR);
        return super.onLayoutChild(coordinatorLayout0, view0, v);
    }

    @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior
    public void onNestedScroll(CoordinatorLayout coordinatorLayout0, View view0, View view1, int v, int v1, int v2, int v3, int v4, int[] arr_v) {
        if(v1 > 0) {
            this.slideDown(view0);
            return;
        }
        if(v1 < 0) {
            this.slideUp(view0);
        }
    }

    @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout0, View view0, View view1, View view2, int v, int v1) {
        return v == 2;
    }

    public void removeOnScrollStateChangedListener(OnScrollStateChangedListener hideBottomViewOnScrollBehavior$OnScrollStateChangedListener0) {
        this.onScrollStateChangedListeners.remove(hideBottomViewOnScrollBehavior$OnScrollStateChangedListener0);
    }

    public void setAdditionalHiddenOffsetY(View view0, int v) {
        this.additionalHiddenOffsetY = v;
        if(this.currentState == 1) {
            view0.setTranslationY(((float)(this.height + v)));
        }
    }

    public void slideDown(View view0) {
        this.slideDown(view0, true);
    }

    public void slideDown(View view0, boolean z) {
        if(this.isScrolledDown()) {
            return;
        }
        ViewPropertyAnimator viewPropertyAnimator0 = this.currentAnimator;
        if(viewPropertyAnimator0 != null) {
            viewPropertyAnimator0.cancel();
            view0.clearAnimation();
        }
        this.updateCurrentState(view0, 1);
        int v = this.height + this.additionalHiddenOffsetY;
        if(z) {
            this.animateChildTo(view0, v, ((long)this.exitAnimDuration), this.exitAnimInterpolator);
            return;
        }
        view0.setTranslationY(((float)v));
    }

    public void slideUp(View view0) {
        this.slideUp(view0, true);
    }

    public void slideUp(View view0, boolean z) {
        if(this.isScrolledUp()) {
            return;
        }
        ViewPropertyAnimator viewPropertyAnimator0 = this.currentAnimator;
        if(viewPropertyAnimator0 != null) {
            viewPropertyAnimator0.cancel();
            view0.clearAnimation();
        }
        this.updateCurrentState(view0, 2);
        if(z) {
            this.animateChildTo(view0, 0, ((long)this.enterAnimDuration), this.enterAnimInterpolator);
            return;
        }
        view0.setTranslationY(0.0f);
    }

    private void updateCurrentState(View view0, int v) {
        this.currentState = v;
        for(Object object0: this.onScrollStateChangedListeners) {
            ((OnScrollStateChangedListener)object0).onStateChanged(view0, this.currentState);
        }
    }
}

