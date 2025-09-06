package com.google.android.material.search;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.widget.ActionMenuView;
import androidx.core.view.ViewCompat;
import com.google.android.material.animation.AnimatableView;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.internal.ExpandCollapseAnimationHelper;
import com.google.android.material.internal.MultiViewUpdateListener;
import com.google.android.material.internal.ToolbarUtils;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.shape.MaterialShapeDrawable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

class SearchBarAnimationHelper {
    interface OnLoadAnimationInvocation {
        void invoke(OnLoadAnimationCallback arg1);
    }

    private static final long COLLAPSE_DURATION_MS = 0xFAL;
    private static final long COLLAPSE_FADE_IN_CHILDREN_DURATION_MS = 100L;
    private static final long EXPAND_DURATION_MS = 300L;
    private static final long EXPAND_FADE_OUT_CHILDREN_DURATION_MS = 75L;
    private static final long ON_LOAD_ANIM_CENTER_VIEW_DEFAULT_FADE_DURATION_MS = 0xFAL;
    private static final long ON_LOAD_ANIM_CENTER_VIEW_DEFAULT_FADE_IN_START_DELAY_MS = 500L;
    private static final long ON_LOAD_ANIM_CENTER_VIEW_DEFAULT_FADE_OUT_START_DELAY_MS = 750L;
    private static final long ON_LOAD_ANIM_SECONDARY_DURATION_MS = 0xFAL;
    private static final long ON_LOAD_ANIM_SECONDARY_START_DELAY_MS = 0xFAL;
    private final Set collapseAnimationListeners;
    private boolean collapsing;
    private Animator defaultCenterViewAnimator;
    private final Set expandAnimationListeners;
    private boolean expanding;
    private final Set onLoadAnimationCallbacks;
    private boolean onLoadAnimationFadeInEnabled;
    private Animator runningExpandOrCollapseAnimator;
    private Animator secondaryViewAnimator;

    SearchBarAnimationHelper() {
        this.onLoadAnimationCallbacks = new LinkedHashSet();
        this.expandAnimationListeners = new LinkedHashSet();
        this.collapseAnimationListeners = new LinkedHashSet();
        this.onLoadAnimationFadeInEnabled = true;
        this.runningExpandOrCollapseAnimator = null;
    }

    void addCollapseAnimationListener(AnimatorListenerAdapter animatorListenerAdapter0) {
        this.collapseAnimationListeners.add(animatorListenerAdapter0);
    }

    void addExpandAnimationListener(AnimatorListenerAdapter animatorListenerAdapter0) {
        this.expandAnimationListeners.add(animatorListenerAdapter0);
    }

    void addOnLoadAnimationCallback(OnLoadAnimationCallback searchBar$OnLoadAnimationCallback0) {
        this.onLoadAnimationCallbacks.add(searchBar$OnLoadAnimationCallback0);
    }

    private void dispatchOnLoadAnimation(OnLoadAnimationInvocation searchBarAnimationHelper$OnLoadAnimationInvocation0) {
        for(Object object0: this.onLoadAnimationCallbacks) {
            searchBarAnimationHelper$OnLoadAnimationInvocation0.invoke(((OnLoadAnimationCallback)object0));
        }
    }

    private Animator getCollapseAnimator(SearchBar searchBar0, View view0, AppBarLayout appBarLayout0) {
        return this.getExpandCollapseAnimationHelper(searchBar0, view0, appBarLayout0).setDuration(0xFAL).addListener(new AnimatorListenerAdapter() {
            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationEnd(Animator animator0) {
                searchBar0.setVisibility(0);
                SearchBarAnimationHelper.this.collapsing = false;
            }

            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationStart(Animator animator0) {
                searchBar0.stopOnLoadAnimation();
            }
        }).getCollapseAnimator();
    }

    private Animator getDefaultCenterViewAnimator(View view0) {
        ValueAnimator valueAnimator0 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        valueAnimator0.addUpdateListener(MultiViewUpdateListener.alphaListener(new View[]{view0}));
        valueAnimator0.setInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
        long v = 0L;
        valueAnimator0.setDuration((this.onLoadAnimationFadeInEnabled ? 0xFAL : 0L));
        if(this.onLoadAnimationFadeInEnabled) {
            v = 500L;
        }
        valueAnimator0.setStartDelay(v);
        ValueAnimator valueAnimator1 = ValueAnimator.ofFloat(new float[]{1.0f, 0.0f});
        valueAnimator1.addUpdateListener(MultiViewUpdateListener.alphaListener(new View[]{view0}));
        valueAnimator1.setInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
        valueAnimator1.setDuration(0xFAL);
        valueAnimator1.setStartDelay(750L);
        Animator animator0 = new AnimatorSet();
        ((AnimatorSet)animator0).playSequentially(new Animator[]{valueAnimator0, valueAnimator1});
        return animator0;
    }

    private List getEndAnchoredViews(View view0) {
        boolean z = ViewUtils.isLayoutRtl(view0);
        List list0 = new ArrayList();
        if(view0 instanceof ViewGroup) {
            for(int v = 0; v < ((ViewGroup)view0).getChildCount(); ++v) {
                View view1 = ((ViewGroup)view0).getChildAt(v);
                if(!z && view1 instanceof ActionMenuView || z && !(view1 instanceof ActionMenuView)) {
                    list0.add(view1);
                }
            }
        }
        return list0;
    }

    private Animator getExpandAnimator(SearchBar searchBar0, View view0, AppBarLayout appBarLayout0) {
        return this.getExpandCollapseAnimationHelper(searchBar0, view0, appBarLayout0).setDuration(300L).addListener(new AnimatorListenerAdapter() {
            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationEnd(Animator animator0) {
                SearchBarAnimationHelper.this.expanding = false;
            }

            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationStart(Animator animator0) {
                searchBar0.setVisibility(4);
            }
        }).getExpandAnimator();
    }

    private ExpandCollapseAnimationHelper getExpandCollapseAnimationHelper(SearchBar searchBar0, View view0, AppBarLayout appBarLayout0) {
        ExpandCollapseAnimationHelper expandCollapseAnimationHelper0 = new ExpandCollapseAnimationHelper(searchBar0, view0).setAdditionalUpdateListener(this.getExpandedViewBackgroundUpdateListener(searchBar0, view0));
        return appBarLayout0 == null ? expandCollapseAnimationHelper0.setCollapsedViewOffsetY(0).addEndAnchoredViews(this.getEndAnchoredViews(view0)) : expandCollapseAnimationHelper0.setCollapsedViewOffsetY(appBarLayout0.getTop()).addEndAnchoredViews(this.getEndAnchoredViews(view0));
    }

    private ValueAnimator.AnimatorUpdateListener getExpandedViewBackgroundUpdateListener(SearchBar searchBar0, View view0) {
        MaterialShapeDrawable materialShapeDrawable0 = MaterialShapeDrawable.createWithElevationOverlay(view0.getContext());
        materialShapeDrawable0.setCornerSize(searchBar0.getCornerSize());
        materialShapeDrawable0.setElevation(ViewCompat.getElevation(searchBar0));
        return (ValueAnimator valueAnimator0) -> {
            materialShapeDrawable0.setInterpolation(1.0f - valueAnimator0.getAnimatedFraction());
            ViewCompat.setBackground(view0, materialShapeDrawable0);
            view0.setAlpha(1.0f);
        };
    }

    private List getFadeChildren(SearchBar searchBar0) {
        List list0 = ViewUtils.getChildren(searchBar0);
        if(searchBar0.getCenterView() != null) {
            list0.remove(searchBar0.getCenterView());
        }
        return list0;
    }

    private Animator getFadeInChildrenAnimator(SearchBar searchBar0) {
        List list0 = this.getFadeChildren(searchBar0);
        Animator animator0 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        ((ValueAnimator)animator0).addUpdateListener(MultiViewUpdateListener.alphaListener(list0));
        ((ValueAnimator)animator0).setDuration(100L);
        ((ValueAnimator)animator0).setInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
        return animator0;
    }

    private Animator getFadeOutChildrenAnimator(SearchBar searchBar0, View view0) {
        List list0 = this.getFadeChildren(searchBar0);
        Animator animator0 = ValueAnimator.ofFloat(new float[]{1.0f, 0.0f});
        ((ValueAnimator)animator0).addUpdateListener(MultiViewUpdateListener.alphaListener(list0));
        ((ValueAnimator)animator0).addUpdateListener((ValueAnimator valueAnimator0) -> view0.setAlpha(0.0f));
        ((ValueAnimator)animator0).setDuration(75L);
        ((ValueAnimator)animator0).setInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
        return animator0;
    }

    private Animator getSecondaryActionMenuItemAnimator(View view0) {
        Animator animator0 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        ((ValueAnimator)animator0).addUpdateListener(MultiViewUpdateListener.alphaListener(new View[]{view0}));
        ((ValueAnimator)animator0).setInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
        ((ValueAnimator)animator0).setDuration(0xFAL);
        return animator0;
    }

    private Animator getSecondaryViewAnimator(TextView textView0, View view0) {
        Animator animator0 = new AnimatorSet();
        ((AnimatorSet)animator0).setStartDelay(0xFAL);
        ((AnimatorSet)animator0).play(this.getTextViewAnimator(textView0));
        if(view0 != null) {
            ((AnimatorSet)animator0).play(this.getSecondaryActionMenuItemAnimator(view0));
        }
        return animator0;
    }

    private Animator getTextViewAnimator(TextView textView0) {
        Animator animator0 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        ((ValueAnimator)animator0).addUpdateListener(MultiViewUpdateListener.alphaListener(new View[]{textView0}));
        ((ValueAnimator)animator0).setInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
        ((ValueAnimator)animator0).setDuration(0xFAL);
        return animator0;
    }

    boolean isCollapsing() {
        return this.collapsing;
    }

    boolean isExpanding() {
        return this.expanding;
    }

    boolean isOnLoadAnimationFadeInEnabled() {
        return this.onLoadAnimationFadeInEnabled;
    }

    // 检测为 Lambda 实现
    static void lambda$getExpandedViewBackgroundUpdateListener$1(MaterialShapeDrawable materialShapeDrawable0, View view0, ValueAnimator valueAnimator0) [...]

    // 检测为 Lambda 实现
    static void lambda$getFadeOutChildrenAnimator$2(View view0, ValueAnimator valueAnimator0) [...]

    // 检测为 Lambda 实现
    void lambda$startExpandAnimation$0$com-google-android-material-search-SearchBarAnimationHelper(SearchBar searchBar0, View view0, AppBarLayout appBarLayout0, boolean z) [...]

    boolean removeCollapseAnimationListener(AnimatorListenerAdapter animatorListenerAdapter0) {
        return this.collapseAnimationListeners.remove(animatorListenerAdapter0);
    }

    boolean removeExpandAnimationListener(AnimatorListenerAdapter animatorListenerAdapter0) {
        return this.expandAnimationListeners.remove(animatorListenerAdapter0);
    }

    boolean removeOnLoadAnimationCallback(OnLoadAnimationCallback searchBar$OnLoadAnimationCallback0) {
        return this.onLoadAnimationCallbacks.remove(searchBar$OnLoadAnimationCallback0);
    }

    void setOnLoadAnimationFadeInEnabled(boolean z) {
        this.onLoadAnimationFadeInEnabled = z;
    }

    void startCollapseAnimation(SearchBar searchBar0, View view0, AppBarLayout appBarLayout0, boolean z) {
        if(this.isExpanding()) {
            Animator animator0 = this.runningExpandOrCollapseAnimator;
            if(animator0 != null) {
                animator0.cancel();
            }
        }
        this.collapsing = true;
        AnimatorSet animatorSet0 = new AnimatorSet();
        animatorSet0.playSequentially(new Animator[]{this.getCollapseAnimator(searchBar0, view0, appBarLayout0), this.getFadeInChildrenAnimator(searchBar0)});
        animatorSet0.addListener(new AnimatorListenerAdapter() {
            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationEnd(Animator animator0) {
                SearchBarAnimationHelper.this.runningExpandOrCollapseAnimator = null;
            }
        });
        for(Object object0: this.collapseAnimationListeners) {
            animatorSet0.addListener(((AnimatorListenerAdapter)object0));
        }
        if(z) {
            animatorSet0.setDuration(0L);
        }
        animatorSet0.start();
        this.runningExpandOrCollapseAnimator = animatorSet0;
    }

    void startExpandAnimation(SearchBar searchBar0, View view0, AppBarLayout appBarLayout0, boolean z) {
        if(this.isCollapsing()) {
            Animator animator0 = this.runningExpandOrCollapseAnimator;
            if(animator0 != null) {
                animator0.cancel();
            }
        }
        this.expanding = true;
        view0.setVisibility(4);
        view0.post(() -> {
            AnimatorSet animatorSet0 = new AnimatorSet();
            animatorSet0.playSequentially(new Animator[]{this.getFadeOutChildrenAnimator(searchBar0, view0), this.getExpandAnimator(searchBar0, view0, appBarLayout0)});
            animatorSet0.addListener(new AnimatorListenerAdapter() {
                @Override  // android.animation.AnimatorListenerAdapter
                public void onAnimationEnd(Animator animator0) {
                    SearchBarAnimationHelper.this.runningExpandOrCollapseAnimator = null;
                }
            });
            for(Object object0: this.expandAnimationListeners) {
                animatorSet0.addListener(((AnimatorListenerAdapter)object0));
            }
            if(z) {
                animatorSet0.setDuration(0L);
            }
            animatorSet0.start();
            this.runningExpandOrCollapseAnimator = animatorSet0;
        });
    }

    void startOnLoadAnimation(SearchBar searchBar0) {
        this.dispatchOnLoadAnimation(new SearchBarAnimationHelper..ExternalSyntheticLambda1());
        TextView textView0 = searchBar0.getTextView();
        View view0 = searchBar0.getCenterView();
        View view1 = ToolbarUtils.getSecondaryActionMenuItemView(searchBar0);
        Animator animator0 = this.getSecondaryViewAnimator(textView0, view1);
        animator0.addListener(new AnimatorListenerAdapter() {
            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationEnd(Animator animator0) {
                SearchBarAnimationHelper.1..ExternalSyntheticLambda0 searchBarAnimationHelper$1$$ExternalSyntheticLambda00 = new SearchBarAnimationHelper.1..ExternalSyntheticLambda0();
                SearchBarAnimationHelper.this.dispatchOnLoadAnimation(searchBarAnimationHelper$1$$ExternalSyntheticLambda00);
            }
        });
        this.secondaryViewAnimator = animator0;
        textView0.setAlpha(0.0f);
        if(view1 != null) {
            view1.setAlpha(0.0f);
        }
        if(view0 instanceof AnimatableView) {
            Objects.requireNonNull(animator0);
            ((AnimatableView)view0).startAnimation(new SearchBarAnimationHelper..ExternalSyntheticLambda2(animator0));
            return;
        }
        if(view0 != null) {
            view0.setAlpha(0.0f);
            view0.setVisibility(0);
            Animator animator1 = this.getDefaultCenterViewAnimator(view0);
            this.defaultCenterViewAnimator = animator1;
            animator1.addListener(new AnimatorListenerAdapter() {
                @Override  // android.animation.AnimatorListenerAdapter
                public void onAnimationEnd(Animator animator0) {
                    view0.setVisibility(8);
                    animator0.start();
                }
            });
            animator1.start();
            return;
        }
        animator0.start();
    }

    void stopOnLoadAnimation(SearchBar searchBar0) {
        Animator animator0 = this.secondaryViewAnimator;
        if(animator0 != null) {
            animator0.end();
        }
        Animator animator1 = this.defaultCenterViewAnimator;
        if(animator1 != null) {
            animator1.end();
        }
        View view0 = searchBar0.getCenterView();
        if(view0 instanceof AnimatableView) {
            ((AnimatableView)view0).stopAnimation();
        }
        if(view0 != null) {
            view0.setAlpha(0.0f);
        }
    }
}

