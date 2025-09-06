package com.google.android.material.search;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.activity.BackEventCompat;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.internal.ClippableRoundedCornerLayout;
import com.google.android.material.internal.FadeThroughDrawable;
import com.google.android.material.internal.FadeThroughUpdateListener;
import com.google.android.material.internal.MultiViewUpdateListener;
import com.google.android.material.internal.RectEvaluator;
import com.google.android.material.internal.ReversableAnimatedValueInterpolator;
import com.google.android.material.internal.ToolbarUtils;
import com.google.android.material.internal.TouchObserverFrameLayout;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.motion.MaterialMainContainerBackHelper;
import java.util.Objects;
import kotlin.io.path.LinkFollowing..ExternalSyntheticApiModelOutline0;

class SearchViewAnimationHelper {
    private static final float CONTENT_FROM_SCALE = 0.95f;
    private static final long HIDE_CLEAR_BUTTON_ALPHA_DURATION_MS = 42L;
    private static final long HIDE_CLEAR_BUTTON_ALPHA_START_DELAY_MS = 0L;
    private static final long HIDE_CONTENT_ALPHA_DURATION_MS = 83L;
    private static final long HIDE_CONTENT_ALPHA_START_DELAY_MS = 0L;
    private static final long HIDE_CONTENT_SCALE_DURATION_MS = 0xFAL;
    private static final long HIDE_DURATION_MS = 0xFAL;
    private static final long HIDE_TRANSLATE_DURATION_MS = 300L;
    private static final long SHOW_CLEAR_BUTTON_ALPHA_DURATION_MS = 50L;
    private static final long SHOW_CLEAR_BUTTON_ALPHA_START_DELAY_MS = 0xFAL;
    private static final long SHOW_CONTENT_ALPHA_DURATION_MS = 150L;
    private static final long SHOW_CONTENT_ALPHA_START_DELAY_MS = 75L;
    private static final long SHOW_CONTENT_SCALE_DURATION_MS = 300L;
    private static final long SHOW_DURATION_MS = 300L;
    private static final long SHOW_TRANSLATE_DURATION_MS = 350L;
    private static final long SHOW_TRANSLATE_KEYBOARD_START_DELAY_MS = 150L;
    private final MaterialMainContainerBackHelper backHelper;
    private AnimatorSet backProgressAnimatorSet;
    private final ImageButton clearButton;
    private final TouchObserverFrameLayout contentContainer;
    private final View divider;
    private final Toolbar dummyToolbar;
    private final EditText editText;
    private final FrameLayout headerContainer;
    private final ClippableRoundedCornerLayout rootView;
    private final View scrim;
    private SearchBar searchBar;
    private final TextView searchPrefix;
    private final SearchView searchView;
    private final Toolbar toolbar;
    private final FrameLayout toolbarContainer;

    SearchViewAnimationHelper(SearchView searchView0) {
        this.searchView = searchView0;
        this.scrim = searchView0.scrim;
        this.rootView = searchView0.rootView;
        this.headerContainer = searchView0.headerContainer;
        this.toolbarContainer = searchView0.toolbarContainer;
        this.toolbar = searchView0.toolbar;
        this.dummyToolbar = searchView0.dummyToolbar;
        this.searchPrefix = searchView0.searchPrefix;
        this.editText = searchView0.editText;
        this.clearButton = searchView0.clearButton;
        this.divider = searchView0.divider;
        this.contentContainer = searchView0.contentContainer;
        this.backHelper = new MaterialMainContainerBackHelper(searchView0.rootView);
    }

    private void addActionMenuViewAnimatorIfNeeded(AnimatorSet animatorSet0) {
        ActionMenuView actionMenuView0 = ToolbarUtils.getActionMenuView(this.toolbar);
        if(actionMenuView0 == null) {
            return;
        }
        ValueAnimator valueAnimator0 = ValueAnimator.ofFloat(new float[]{((float)this.getFromTranslationXEnd(actionMenuView0)), 0.0f});
        valueAnimator0.addUpdateListener(MultiViewUpdateListener.translationXListener(new View[]{actionMenuView0}));
        ValueAnimator valueAnimator1 = ValueAnimator.ofFloat(new float[]{((float)this.getFromTranslationY()), 0.0f});
        valueAnimator1.addUpdateListener(MultiViewUpdateListener.translationYListener(new View[]{actionMenuView0}));
        animatorSet0.playTogether(new Animator[]{valueAnimator0, valueAnimator1});
    }

    private void addBackButtonProgressAnimatorIfNeeded(AnimatorSet animatorSet0) {
        ImageButton imageButton0 = ToolbarUtils.getNavigationIconButton(this.toolbar);
        if(imageButton0 == null) {
            return;
        }
        Drawable drawable0 = DrawableCompat.unwrap(imageButton0.getDrawable());
        if(this.searchView.isAnimatedNavigationIcon()) {
            this.addDrawerArrowDrawableAnimatorIfNeeded(animatorSet0, drawable0);
            this.addFadeThroughDrawableAnimatorIfNeeded(animatorSet0, drawable0);
            return;
        }
        this.setFullDrawableProgressIfNeeded(drawable0);
    }

    private void addBackButtonTranslationAnimatorIfNeeded(AnimatorSet animatorSet0) {
        ImageButton imageButton0 = ToolbarUtils.getNavigationIconButton(this.toolbar);
        if(imageButton0 == null) {
            return;
        }
        ValueAnimator valueAnimator0 = ValueAnimator.ofFloat(new float[]{((float)this.getFromTranslationXStart(imageButton0)), 0.0f});
        valueAnimator0.addUpdateListener(MultiViewUpdateListener.translationXListener(new View[]{imageButton0}));
        ValueAnimator valueAnimator1 = ValueAnimator.ofFloat(new float[]{((float)this.getFromTranslationY()), 0.0f});
        valueAnimator1.addUpdateListener(MultiViewUpdateListener.translationYListener(new View[]{imageButton0}));
        animatorSet0.playTogether(new Animator[]{valueAnimator0, valueAnimator1});
    }

    private void addDrawerArrowDrawableAnimatorIfNeeded(AnimatorSet animatorSet0, Drawable drawable0) {
        if(drawable0 instanceof DrawerArrowDrawable) {
            ValueAnimator valueAnimator0 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            valueAnimator0.addUpdateListener((ValueAnimator valueAnimator0) -> ((DrawerArrowDrawable)drawable0).setProgress(((float)(((Float)valueAnimator0.getAnimatedValue())))));
            animatorSet0.playTogether(new Animator[]{valueAnimator0});
        }
    }

    private void addFadeThroughDrawableAnimatorIfNeeded(AnimatorSet animatorSet0, Drawable drawable0) {
        if(drawable0 instanceof FadeThroughDrawable) {
            ValueAnimator valueAnimator0 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            valueAnimator0.addUpdateListener((ValueAnimator valueAnimator0) -> ((FadeThroughDrawable)drawable0).setProgress(((float)(((Float)valueAnimator0.getAnimatedValue())))));
            animatorSet0.playTogether(new Animator[]{valueAnimator0});
        }
    }

    public void cancelBackProgress() {
        this.backHelper.cancelBackProgress(this.searchBar);
        AnimatorSet animatorSet0 = this.backProgressAnimatorSet;
        if(animatorSet0 != null) {
            LinkFollowing..ExternalSyntheticApiModelOutline0.m(animatorSet0);
        }
        this.backProgressAnimatorSet = null;
    }

    public void finishBackProgress() {
        long v = LinkFollowing..ExternalSyntheticApiModelOutline0.m(this.hide());
        this.backHelper.finishBackProgress(v, this.searchBar);
        if(this.backProgressAnimatorSet != null) {
            this.getButtonsTranslationAnimator(false).start();
            this.backProgressAnimatorSet.resume();
        }
        this.backProgressAnimatorSet = null;
    }

    private Animator getActionMenuViewsAlphaAnimator(boolean z) {
        Animator animator0 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        ((ValueAnimator)animator0).setDuration((z ? 300L : 0xFAL));
        ((ValueAnimator)animator0).setInterpolator(ReversableAnimatedValueInterpolator.of(z, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
        if(this.searchView.isMenuItemsAnimated()) {
            ((ValueAnimator)animator0).addUpdateListener(new FadeThroughUpdateListener(ToolbarUtils.getActionMenuView(this.dummyToolbar), ToolbarUtils.getActionMenuView(this.toolbar)));
        }
        return animator0;
    }

    MaterialMainContainerBackHelper getBackHelper() {
        return this.backHelper;
    }

    private AnimatorSet getButtonsProgressAnimator(boolean z) {
        AnimatorSet animatorSet0 = new AnimatorSet();
        this.addBackButtonProgressAnimatorIfNeeded(animatorSet0);
        animatorSet0.setDuration((z ? 300L : 0xFAL));
        animatorSet0.setInterpolator(ReversableAnimatedValueInterpolator.of(z, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
        return animatorSet0;
    }

    private AnimatorSet getButtonsTranslationAnimator(boolean z) {
        AnimatorSet animatorSet0 = new AnimatorSet();
        this.addBackButtonTranslationAnimatorIfNeeded(animatorSet0);
        this.addActionMenuViewAnimatorIfNeeded(animatorSet0);
        animatorSet0.setDuration((z ? 300L : 0xFAL));
        animatorSet0.setInterpolator(ReversableAnimatedValueInterpolator.of(z, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
        return animatorSet0;
    }

    private Animator getClearButtonAnimator(boolean z) {
        Animator animator0 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        ((ValueAnimator)animator0).setDuration((z ? 50L : 42L));
        ((ValueAnimator)animator0).setStartDelay((z ? 0xFAL : 0L));
        ((ValueAnimator)animator0).setInterpolator(ReversableAnimatedValueInterpolator.of(z, AnimationUtils.LINEAR_INTERPOLATOR));
        ((ValueAnimator)animator0).addUpdateListener(MultiViewUpdateListener.alphaListener(new View[]{this.clearButton}));
        return animator0;
    }

    private Animator getContentAlphaAnimator(boolean z) {
        Animator animator0 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        ((ValueAnimator)animator0).setDuration((z ? 150L : 83L));
        ((ValueAnimator)animator0).setStartDelay((z ? 75L : 0L));
        ((ValueAnimator)animator0).setInterpolator(ReversableAnimatedValueInterpolator.of(z, AnimationUtils.LINEAR_INTERPOLATOR));
        ((ValueAnimator)animator0).addUpdateListener(MultiViewUpdateListener.alphaListener(new View[]{this.divider, this.contentContainer}));
        return animator0;
    }

    private Animator getContentAnimator(boolean z) {
        Animator animator0 = new AnimatorSet();
        ((AnimatorSet)animator0).playTogether(new Animator[]{this.getContentAlphaAnimator(z), this.getDividerAnimator(z), this.getContentScaleAnimator(z)});
        return animator0;
    }

    private Animator getContentScaleAnimator(boolean z) {
        Animator animator0 = ValueAnimator.ofFloat(new float[]{0.95f, 1.0f});
        ((ValueAnimator)animator0).setDuration((z ? 300L : 0xFAL));
        ((ValueAnimator)animator0).setInterpolator(ReversableAnimatedValueInterpolator.of(z, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
        ((ValueAnimator)animator0).addUpdateListener(MultiViewUpdateListener.scaleListener(new View[]{this.contentContainer}));
        return animator0;
    }

    private Animator getDividerAnimator(boolean z) {
        Animator animator0 = ValueAnimator.ofFloat(new float[]{((float)this.contentContainer.getHeight()) * 0.05f / 2.0f, 0.0f});
        ((ValueAnimator)animator0).setDuration((z ? 300L : 0xFAL));
        ((ValueAnimator)animator0).setInterpolator(ReversableAnimatedValueInterpolator.of(z, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
        ((ValueAnimator)animator0).addUpdateListener(MultiViewUpdateListener.translationYListener(new View[]{this.divider}));
        return animator0;
    }

    private Animator getDummyToolbarAnimator(boolean z) {
        return this.getTranslationAnimator(z, false, this.dummyToolbar);
    }

    private Animator getEditTextAnimator(boolean z) {
        return this.getTranslationAnimator(z, true, this.editText);
    }

    private AnimatorSet getExpandCollapseAnimatorSet(boolean z) {
        AnimatorSet animatorSet0 = new AnimatorSet();
        if(this.backProgressAnimatorSet == null) {
            animatorSet0.playTogether(new Animator[]{this.getButtonsProgressAnimator(z), this.getButtonsTranslationAnimator(z)});
        }
        animatorSet0.playTogether(new Animator[]{this.getScrimAlphaAnimator(z), this.getRootViewAnimator(z), this.getClearButtonAnimator(z), this.getContentAnimator(z), this.getHeaderContainerAnimator(z), this.getDummyToolbarAnimator(z), this.getActionMenuViewsAlphaAnimator(z), this.getEditTextAnimator(z), this.getSearchPrefixAnimator(z)});
        animatorSet0.addListener(new AnimatorListenerAdapter() {
            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationEnd(Animator animator0) {
                SearchViewAnimationHelper.this.setContentViewsAlpha((z ? 1.0f : 0.0f));
                SearchViewAnimationHelper.this.rootView.resetClipBoundsAndCornerRadius();
            }

            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationStart(Animator animator0) {
                SearchViewAnimationHelper.this.setContentViewsAlpha((z ? 0.0f : 1.0f));
            }
        });
        return animatorSet0;
    }

    private int getFromTranslationXEnd(View view0) {
        int v = MarginLayoutParamsCompat.getMarginEnd(((ViewGroup.MarginLayoutParams)view0.getLayoutParams()));
        return ViewUtils.isLayoutRtl(this.searchBar) ? this.searchBar.getLeft() - v : this.searchBar.getRight() - this.searchView.getWidth() + v;
    }

    private int getFromTranslationXStart(View view0) {
        int v = MarginLayoutParamsCompat.getMarginStart(((ViewGroup.MarginLayoutParams)view0.getLayoutParams()));
        int v1 = ViewCompat.getPaddingStart(this.searchBar);
        return ViewUtils.isLayoutRtl(this.searchBar) ? this.searchBar.getWidth() - this.searchBar.getRight() + v - v1 : this.searchBar.getLeft() - v + v1;
    }

    private int getFromTranslationY() {
        int v = this.toolbarContainer.getTop();
        int v1 = this.toolbarContainer.getBottom();
        return (this.searchBar.getTop() + this.searchBar.getBottom()) / 2 - (v + v1) / 2;
    }

    private Animator getHeaderContainerAnimator(boolean z) {
        return this.getTranslationAnimator(z, false, this.headerContainer);
    }

    private Animator getRootViewAnimator(boolean z) {
        Rect rect0 = this.backHelper.getInitialHideToClipBounds();
        Rect rect1 = this.backHelper.getInitialHideFromClipBounds();
        if(rect0 == null) {
            rect0 = ViewUtils.calculateRectFromBounds(this.searchView);
        }
        if(rect1 == null) {
            rect1 = ViewUtils.calculateOffsetRectFromBounds(this.rootView, this.searchBar);
        }
        Rect rect2 = new Rect(rect1);
        float f = this.searchBar.getCornerSize();
        float f1 = Math.max(this.rootView.getCornerRadius(), this.backHelper.getExpandedCornerSize());
        Animator animator0 = ValueAnimator.ofObject(new RectEvaluator(rect2), new Object[]{rect1, rect0});
        ((ValueAnimator)animator0).addUpdateListener((ValueAnimator valueAnimator0) -> {
            float f2 = AnimationUtils.lerp(f, f1, valueAnimator0.getAnimatedFraction());
            this.rootView.updateClipBoundsAndCornerRadius(rect2, f2);
        });
        ((ValueAnimator)animator0).setDuration((z ? 300L : 0xFAL));
        ((ValueAnimator)animator0).setInterpolator(ReversableAnimatedValueInterpolator.of(z, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
        return animator0;
    }

    private Animator getScrimAlphaAnimator(boolean z) {
        Animator animator0 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        ((ValueAnimator)animator0).setDuration((z ? 300L : 0xFAL));
        ((ValueAnimator)animator0).setInterpolator(ReversableAnimatedValueInterpolator.of(z, (z ? AnimationUtils.LINEAR_INTERPOLATOR : AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR)));
        ((ValueAnimator)animator0).addUpdateListener(MultiViewUpdateListener.alphaListener(new View[]{this.scrim}));
        return animator0;
    }

    private Animator getSearchPrefixAnimator(boolean z) {
        return this.getTranslationAnimator(z, true, this.searchPrefix);
    }

    private AnimatorSet getTranslateAnimatorSet(boolean z) {
        AnimatorSet animatorSet0 = new AnimatorSet();
        animatorSet0.playTogether(new Animator[]{this.getTranslationYAnimator()});
        this.addBackButtonProgressAnimatorIfNeeded(animatorSet0);
        animatorSet0.setInterpolator(ReversableAnimatedValueInterpolator.of(z, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
        animatorSet0.setDuration((z ? 350L : 300L));
        return animatorSet0;
    }

    private Animator getTranslationAnimator(boolean z, boolean z1, View view0) {
        ValueAnimator valueAnimator0 = ValueAnimator.ofFloat(new float[]{((float)(z1 ? this.getFromTranslationXStart(view0) : this.getFromTranslationXEnd(view0))), 0.0f});
        valueAnimator0.addUpdateListener(MultiViewUpdateListener.translationXListener(new View[]{view0}));
        ValueAnimator valueAnimator1 = ValueAnimator.ofFloat(new float[]{((float)this.getFromTranslationY()), 0.0f});
        valueAnimator1.addUpdateListener(MultiViewUpdateListener.translationYListener(new View[]{view0}));
        Animator animator0 = new AnimatorSet();
        ((AnimatorSet)animator0).playTogether(new Animator[]{valueAnimator0, valueAnimator1});
        ((AnimatorSet)animator0).setDuration((z ? 300L : 0xFAL));
        ((AnimatorSet)animator0).setInterpolator(ReversableAnimatedValueInterpolator.of(z, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
        return animator0;
    }

    private Animator getTranslationYAnimator() {
        Animator animator0 = ValueAnimator.ofFloat(new float[]{((float)this.rootView.getHeight()), 0.0f});
        ((ValueAnimator)animator0).addUpdateListener(MultiViewUpdateListener.translationYListener(new View[]{this.rootView}));
        return animator0;
    }

    AnimatorSet hide() {
        return this.searchBar == null ? this.startHideAnimationTranslate() : this.startHideAnimationCollapse();
    }

    // 检测为 Lambda 实现
    static void lambda$addDrawerArrowDrawableAnimatorIfNeeded$3(DrawerArrowDrawable drawerArrowDrawable0, ValueAnimator valueAnimator0) [...]

    // 检测为 Lambda 实现
    static void lambda$addFadeThroughDrawableAnimatorIfNeeded$4(FadeThroughDrawable fadeThroughDrawable0, ValueAnimator valueAnimator0) [...]

    // 检测为 Lambda 实现
    void lambda$getRootViewAnimator$2$com-google-android-material-search-SearchViewAnimationHelper(float f, float f1, Rect rect0, ValueAnimator valueAnimator0) [...]

    // 检测为 Lambda 实现
    void lambda$startShowAnimationExpand$0$com-google-android-material-search-SearchViewAnimationHelper() [...]

    // 检测为 Lambda 实现
    void lambda$startShowAnimationTranslate$1$com-google-android-material-search-SearchViewAnimationHelper() [...]

    public BackEventCompat onHandleBackInvoked() {
        return this.backHelper.onHandleBackInvoked();
    }

    private void setActionMenuViewAlphaIfNeeded(float f) {
        if(this.searchView.isMenuItemsAnimated()) {
            ActionMenuView actionMenuView0 = ToolbarUtils.getActionMenuView(this.toolbar);
            if(actionMenuView0 != null) {
                actionMenuView0.setAlpha(f);
            }
        }
    }

    private void setContentViewsAlpha(float f) {
        this.clearButton.setAlpha(f);
        this.divider.setAlpha(f);
        this.contentContainer.setAlpha(f);
        this.setActionMenuViewAlphaIfNeeded(f);
    }

    private void setFullDrawableProgressIfNeeded(Drawable drawable0) {
        if(drawable0 instanceof DrawerArrowDrawable) {
            ((DrawerArrowDrawable)drawable0).setProgress(1.0f);
        }
        if(drawable0 instanceof FadeThroughDrawable) {
            ((FadeThroughDrawable)drawable0).setProgress(1.0f);
        }
    }

    private void setMenuItemsNotClickable(Toolbar toolbar0) {
        ActionMenuView actionMenuView0 = ToolbarUtils.getActionMenuView(toolbar0);
        if(actionMenuView0 != null) {
            for(int v = 0; v < actionMenuView0.getChildCount(); ++v) {
                View view0 = actionMenuView0.getChildAt(v);
                view0.setClickable(false);
                view0.setFocusable(false);
                view0.setFocusableInTouchMode(false);
            }
        }
    }

    void setSearchBar(SearchBar searchBar0) {
        this.searchBar = searchBar0;
    }

    private void setUpDummyToolbarIfNeeded() {
        Menu menu0 = this.dummyToolbar.getMenu();
        if(menu0 != null) {
            menu0.clear();
        }
        if(this.searchBar.getMenuResId() != -1 && this.searchView.isMenuItemsAnimated()) {
            this.dummyToolbar.inflateMenu(this.searchBar.getMenuResId());
            this.setMenuItemsNotClickable(this.dummyToolbar);
            this.dummyToolbar.setVisibility(0);
            return;
        }
        this.dummyToolbar.setVisibility(8);
    }

    void show() {
        if(this.searchBar != null) {
            this.startShowAnimationExpand();
            return;
        }
        this.startShowAnimationTranslate();
    }

    void startBackProgress(BackEventCompat backEventCompat0) {
        this.backHelper.startBackProgress(backEventCompat0, this.searchBar);
    }

    private AnimatorSet startHideAnimationCollapse() {
        if(this.searchView.isAdjustNothingSoftInputMode()) {
            this.searchView.clearFocusAndHideKeyboard();
        }
        AnimatorSet animatorSet0 = this.getExpandCollapseAnimatorSet(false);
        animatorSet0.addListener(new AnimatorListenerAdapter() {
            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationEnd(Animator animator0) {
                SearchViewAnimationHelper.this.rootView.setVisibility(8);
                if(!SearchViewAnimationHelper.this.searchView.isAdjustNothingSoftInputMode()) {
                    SearchViewAnimationHelper.this.searchView.clearFocusAndHideKeyboard();
                }
                SearchViewAnimationHelper.this.searchView.setTransitionState(TransitionState.HIDDEN);
            }

            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationStart(Animator animator0) {
                SearchViewAnimationHelper.this.searchView.setTransitionState(TransitionState.HIDING);
            }
        });
        animatorSet0.start();
        return animatorSet0;
    }

    private AnimatorSet startHideAnimationTranslate() {
        if(this.searchView.isAdjustNothingSoftInputMode()) {
            this.searchView.clearFocusAndHideKeyboard();
        }
        AnimatorSet animatorSet0 = this.getTranslateAnimatorSet(false);
        animatorSet0.addListener(new AnimatorListenerAdapter() {
            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationEnd(Animator animator0) {
                SearchViewAnimationHelper.this.rootView.setVisibility(8);
                if(!SearchViewAnimationHelper.this.searchView.isAdjustNothingSoftInputMode()) {
                    SearchViewAnimationHelper.this.searchView.clearFocusAndHideKeyboard();
                }
                SearchViewAnimationHelper.this.searchView.setTransitionState(TransitionState.HIDDEN);
            }

            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationStart(Animator animator0) {
                SearchViewAnimationHelper.this.searchView.setTransitionState(TransitionState.HIDING);
            }
        });
        animatorSet0.start();
        return animatorSet0;
    }

    private void startShowAnimationExpand() {
        if(this.searchView.isAdjustNothingSoftInputMode()) {
            this.searchView.requestFocusAndShowKeyboardIfNeeded();
        }
        this.searchView.setTransitionState(TransitionState.SHOWING);
        this.setUpDummyToolbarIfNeeded();
        CharSequence charSequence0 = this.searchBar.getText();
        this.editText.setText(charSequence0);
        int v = this.editText.getText().length();
        this.editText.setSelection(v);
        this.rootView.setVisibility(4);
        SearchViewAnimationHelper..ExternalSyntheticLambda4 searchViewAnimationHelper$$ExternalSyntheticLambda40 = () -> {
            AnimatorSet animatorSet0 = this.getExpandCollapseAnimatorSet(true);
            animatorSet0.addListener(new AnimatorListenerAdapter() {
                @Override  // android.animation.AnimatorListenerAdapter
                public void onAnimationEnd(Animator animator0) {
                    if(!SearchViewAnimationHelper.this.searchView.isAdjustNothingSoftInputMode()) {
                        SearchViewAnimationHelper.this.searchView.requestFocusAndShowKeyboardIfNeeded();
                    }
                    SearchViewAnimationHelper.this.searchView.setTransitionState(TransitionState.SHOWN);
                }

                @Override  // android.animation.AnimatorListenerAdapter
                public void onAnimationStart(Animator animator0) {
                    SearchViewAnimationHelper.this.rootView.setVisibility(0);
                    SearchViewAnimationHelper.this.searchBar.stopOnLoadAnimation();
                }
            });
            animatorSet0.start();
        };
        this.rootView.post(searchViewAnimationHelper$$ExternalSyntheticLambda40);
    }

    private void startShowAnimationTranslate() {
        if(this.searchView.isAdjustNothingSoftInputMode()) {
            Objects.requireNonNull(this.searchView);
            SearchViewAnimationHelper..ExternalSyntheticLambda6 searchViewAnimationHelper$$ExternalSyntheticLambda60 = new SearchViewAnimationHelper..ExternalSyntheticLambda6(this.searchView);
            this.searchView.postDelayed(searchViewAnimationHelper$$ExternalSyntheticLambda60, 150L);
        }
        this.rootView.setVisibility(4);
        SearchViewAnimationHelper..ExternalSyntheticLambda7 searchViewAnimationHelper$$ExternalSyntheticLambda70 = () -> {
            float f = (float)this.rootView.getHeight();
            this.rootView.setTranslationY(f);
            AnimatorSet animatorSet0 = this.getTranslateAnimatorSet(true);
            animatorSet0.addListener(new AnimatorListenerAdapter() {
                @Override  // android.animation.AnimatorListenerAdapter
                public void onAnimationEnd(Animator animator0) {
                    if(!SearchViewAnimationHelper.this.searchView.isAdjustNothingSoftInputMode()) {
                        SearchViewAnimationHelper.this.searchView.requestFocusAndShowKeyboardIfNeeded();
                    }
                    SearchViewAnimationHelper.this.searchView.setTransitionState(TransitionState.SHOWN);
                }

                @Override  // android.animation.AnimatorListenerAdapter
                public void onAnimationStart(Animator animator0) {
                    SearchViewAnimationHelper.this.rootView.setVisibility(0);
                    SearchViewAnimationHelper.this.searchView.setTransitionState(TransitionState.SHOWING);
                }
            });
            animatorSet0.start();
        };
        this.rootView.post(searchViewAnimationHelper$$ExternalSyntheticLambda70);
    }

    public void updateBackProgress(BackEventCompat backEventCompat0) {
        if(backEventCompat0.getProgress() > 0.0f) {
            SearchBar searchBar0 = this.searchBar;
            float f = searchBar0.getCornerSize();
            this.backHelper.updateBackProgress(backEventCompat0, searchBar0, f);
            AnimatorSet animatorSet0 = this.backProgressAnimatorSet;
            if(animatorSet0 != null) {
                animatorSet0.setCurrentPlayTime(((long)(backEventCompat0.getProgress() * ((float)this.backProgressAnimatorSet.getDuration()))));
                return;
            }
            if(this.searchView.isAdjustNothingSoftInputMode()) {
                this.searchView.clearFocusAndHideKeyboard();
            }
            if(this.searchView.isAnimatedNavigationIcon()) {
                AnimatorSet animatorSet1 = this.getButtonsProgressAnimator(false);
                this.backProgressAnimatorSet = animatorSet1;
                animatorSet1.start();
                this.backProgressAnimatorSet.pause();
            }
        }
    }
}

