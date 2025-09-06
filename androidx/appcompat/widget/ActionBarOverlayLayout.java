package androidx.appcompat.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.Window.Callback;
import android.view.WindowInsets;
import android.widget.OverScroller;
import androidx.appcompat.R.attr;
import androidx.appcompat.R.id;
import androidx.appcompat.view.menu.MenuPresenter.Callback;
import androidx.core.graphics.Insets;
import androidx.core.view.NestedScrollingParent2;
import androidx.core.view.NestedScrollingParent3;
import androidx.core.view.NestedScrollingParent;
import androidx.core.view.NestedScrollingParentHelper;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat.Builder;
import androidx.core.view.WindowInsetsCompat;

public class ActionBarOverlayLayout extends ViewGroup implements DecorContentParent, NestedScrollingParent2, NestedScrollingParent3, NestedScrollingParent {
    public interface ActionBarVisibilityCallback {
        void enableContentAnimations(boolean arg1);

        void hideForSystem();

        void onContentScrollStarted();

        void onContentScrollStopped();

        void onWindowVisibilityChanged(int arg1);

        void showForSystem();
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public LayoutParams(int v, int v1) {
            super(v, v1);
        }

        public LayoutParams(Context context0, AttributeSet attributeSet0) {
            super(context0, attributeSet0);
        }

        public LayoutParams(ViewGroup.LayoutParams viewGroup$LayoutParams0) {
            super(viewGroup$LayoutParams0);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0) {
            super(viewGroup$MarginLayoutParams0);
        }
    }

    static final class NoSystemUiLayoutFlagView extends View {
        NoSystemUiLayoutFlagView(Context context0) {
            super(context0);
            this.setWillNotDraw(true);
        }

        @Override  // android.view.View
        public int getWindowSystemUiVisibility() {
            return 0;
        }
    }

    private static final int ACTION_BAR_ANIMATE_DELAY = 600;
    static final int[] ATTRS = null;
    private static final WindowInsetsCompat NON_EMPTY_SYSTEM_WINDOW_INSETS = null;
    private static final String TAG = "ActionBarOverlayLayout";
    private static final Rect ZERO_INSETS;
    private int mActionBarHeight;
    ActionBarContainer mActionBarTop;
    private ActionBarVisibilityCallback mActionBarVisibilityCallback;
    private final Runnable mAddActionBarHideOffset;
    boolean mAnimatingForFling;
    private final Rect mBaseContentInsets;
    private WindowInsetsCompat mBaseInnerInsets;
    private final Rect mBaseInnerInsetsRect;
    private ContentFrameLayout mContent;
    private final Rect mContentInsets;
    ViewPropertyAnimator mCurrentActionBarTopAnimator;
    private DecorToolbar mDecorToolbar;
    private OverScroller mFlingEstimator;
    private boolean mHasNonEmbeddedTabs;
    private boolean mHideOnContentScroll;
    private int mHideOnContentScrollReference;
    private WindowInsetsCompat mInnerInsets;
    private final Rect mInnerInsetsRect;
    private final Rect mLastBaseContentInsets;
    private WindowInsetsCompat mLastBaseInnerInsets;
    private final Rect mLastBaseInnerInsetsRect;
    private WindowInsetsCompat mLastInnerInsets;
    private final Rect mLastInnerInsetsRect;
    private int mLastSystemUiVisibility;
    private final NoSystemUiLayoutFlagView mNoSystemUiLayoutFlagView;
    private boolean mOverlayMode;
    private final NestedScrollingParentHelper mParentHelper;
    private final Runnable mRemoveActionBarHideOffset;
    private final Rect mTmpRect;
    final AnimatorListenerAdapter mTopAnimatorListener;
    private Drawable mWindowContentOverlay;
    private int mWindowVisibility;

    static {
        ActionBarOverlayLayout.ATTRS = new int[]{attr.actionBarSize, 0x1010059};
        ActionBarOverlayLayout.NON_EMPTY_SYSTEM_WINDOW_INSETS = new Builder().setSystemWindowInsets(Insets.of(0, 1, 0, 1)).build();
        ActionBarOverlayLayout.ZERO_INSETS = new Rect();
    }

    public ActionBarOverlayLayout(Context context0) {
        this(context0, null);
    }

    public ActionBarOverlayLayout(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
        this.mWindowVisibility = 0;
        this.mBaseContentInsets = new Rect();
        this.mLastBaseContentInsets = new Rect();
        this.mContentInsets = new Rect();
        this.mTmpRect = new Rect();
        this.mBaseInnerInsetsRect = new Rect();
        this.mLastBaseInnerInsetsRect = new Rect();
        this.mInnerInsetsRect = new Rect();
        this.mLastInnerInsetsRect = new Rect();
        this.mBaseInnerInsets = WindowInsetsCompat.CONSUMED;
        this.mLastBaseInnerInsets = WindowInsetsCompat.CONSUMED;
        this.mInnerInsets = WindowInsetsCompat.CONSUMED;
        this.mLastInnerInsets = WindowInsetsCompat.CONSUMED;
        this.mTopAnimatorListener = new AnimatorListenerAdapter() {
            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationCancel(Animator animator0) {
                ActionBarOverlayLayout.this.mCurrentActionBarTopAnimator = null;
                ActionBarOverlayLayout.this.mAnimatingForFling = false;
            }

            @Override  // android.animation.AnimatorListenerAdapter
            public void onAnimationEnd(Animator animator0) {
                ActionBarOverlayLayout.this.mCurrentActionBarTopAnimator = null;
                ActionBarOverlayLayout.this.mAnimatingForFling = false;
            }
        };
        this.mRemoveActionBarHideOffset = new Runnable() {
            @Override
            public void run() {
                ActionBarOverlayLayout.this.haltActionBarHideOffsetAnimations();
                ActionBarOverlayLayout.this.mCurrentActionBarTopAnimator = ActionBarOverlayLayout.this.mActionBarTop.animate().translationY(0.0f).setListener(ActionBarOverlayLayout.this.mTopAnimatorListener);
            }
        };
        this.mAddActionBarHideOffset = new Runnable() {
            @Override
            public void run() {
                ActionBarOverlayLayout.this.haltActionBarHideOffsetAnimations();
                ActionBarOverlayLayout.this.mCurrentActionBarTopAnimator = ActionBarOverlayLayout.this.mActionBarTop.animate().translationY(((float)(-ActionBarOverlayLayout.this.mActionBarTop.getHeight()))).setListener(ActionBarOverlayLayout.this.mTopAnimatorListener);
            }
        };
        this.init(context0);
        this.mParentHelper = new NestedScrollingParentHelper(this);
        NoSystemUiLayoutFlagView actionBarOverlayLayout$NoSystemUiLayoutFlagView0 = new NoSystemUiLayoutFlagView(context0);
        this.mNoSystemUiLayoutFlagView = actionBarOverlayLayout$NoSystemUiLayoutFlagView0;
        this.addView(actionBarOverlayLayout$NoSystemUiLayoutFlagView0);
    }

    private void addActionBarHideOffset() {
        this.haltActionBarHideOffsetAnimations();
        this.mAddActionBarHideOffset.run();
    }

    private boolean applyInsets(View view0, Rect rect0, boolean z, boolean z1, boolean z2, boolean z3) {
        boolean z4;
        LayoutParams actionBarOverlayLayout$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
        if(!z || actionBarOverlayLayout$LayoutParams0.leftMargin == rect0.left) {
            z4 = false;
        }
        else {
            actionBarOverlayLayout$LayoutParams0.leftMargin = rect0.left;
            z4 = true;
        }
        if(z1 && actionBarOverlayLayout$LayoutParams0.topMargin != rect0.top) {
            actionBarOverlayLayout$LayoutParams0.topMargin = rect0.top;
            z4 = true;
        }
        if(z3 && actionBarOverlayLayout$LayoutParams0.rightMargin != rect0.right) {
            actionBarOverlayLayout$LayoutParams0.rightMargin = rect0.right;
            z4 = true;
        }
        if(z2 && actionBarOverlayLayout$LayoutParams0.bottomMargin != rect0.bottom) {
            actionBarOverlayLayout$LayoutParams0.bottomMargin = rect0.bottom;
            return true;
        }
        return z4;
    }

    @Override  // androidx.appcompat.widget.DecorContentParent
    public boolean canShowOverflowMenu() {
        this.pullChildren();
        return this.mDecorToolbar.canShowOverflowMenu();
    }

    @Override  // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        return viewGroup$LayoutParams0 instanceof LayoutParams;
    }

    private boolean decorFitsSystemWindows() {
        ViewCompat.computeSystemWindowInsets(this.mNoSystemUiLayoutFlagView, ActionBarOverlayLayout.NON_EMPTY_SYSTEM_WINDOW_INSETS, this.mTmpRect);
        return !this.mTmpRect.equals(ActionBarOverlayLayout.ZERO_INSETS);
    }

    @Override  // androidx.appcompat.widget.DecorContentParent
    public void dismissPopups() {
        this.pullChildren();
        this.mDecorToolbar.dismissPopupMenus();
    }

    @Override  // android.view.View
    public void draw(Canvas canvas0) {
        super.draw(canvas0);
        if(this.mWindowContentOverlay != null) {
            int v = this.mActionBarTop.getVisibility() == 0 ? ((int)(((float)this.mActionBarTop.getBottom()) + this.mActionBarTop.getTranslationY() + 0.5f)) : 0;
            this.mWindowContentOverlay.setBounds(0, v, this.getWidth(), this.mWindowContentOverlay.getIntrinsicHeight() + v);
            this.mWindowContentOverlay.draw(canvas0);
        }
    }

    @Override  // android.view.View
    protected boolean fitSystemWindows(Rect rect0) {
        return super.fitSystemWindows(rect0);
    }

    @Override  // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return this.generateDefaultLayoutParams();
    }

    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1);
    }

    @Override  // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet0) {
        return this.generateLayoutParams(attributeSet0);
    }

    @Override  // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        return new LayoutParams(viewGroup$LayoutParams0);
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet0) {
        return new LayoutParams(this.getContext(), attributeSet0);
    }

    public int getActionBarHideOffset() {
        return this.mActionBarTop == null ? 0 : -((int)this.mActionBarTop.getTranslationY());
    }

    private DecorToolbar getDecorToolbar(View view0) {
        if(view0 instanceof DecorToolbar) {
            return (DecorToolbar)view0;
        }
        if(!(view0 instanceof Toolbar)) {
            throw new IllegalStateException("Can\'t make a decor toolbar out of " + view0.getClass().getSimpleName());
        }
        return ((Toolbar)view0).getWrapper();
    }

    @Override  // android.view.ViewGroup, androidx.core.view.NestedScrollingParent
    public int getNestedScrollAxes() {
        return this.mParentHelper.getNestedScrollAxes();
    }

    @Override  // androidx.appcompat.widget.DecorContentParent
    public CharSequence getTitle() {
        this.pullChildren();
        return this.mDecorToolbar.getTitle();
    }

    void haltActionBarHideOffsetAnimations() {
        this.removeCallbacks(this.mRemoveActionBarHideOffset);
        this.removeCallbacks(this.mAddActionBarHideOffset);
        ViewPropertyAnimator viewPropertyAnimator0 = this.mCurrentActionBarTopAnimator;
        if(viewPropertyAnimator0 != null) {
            viewPropertyAnimator0.cancel();
        }
    }

    @Override  // androidx.appcompat.widget.DecorContentParent
    public boolean hasIcon() {
        this.pullChildren();
        return this.mDecorToolbar.hasIcon();
    }

    @Override  // androidx.appcompat.widget.DecorContentParent
    public boolean hasLogo() {
        this.pullChildren();
        return this.mDecorToolbar.hasLogo();
    }

    @Override  // androidx.appcompat.widget.DecorContentParent
    public boolean hideOverflowMenu() {
        this.pullChildren();
        return this.mDecorToolbar.hideOverflowMenu();
    }

    private void init(Context context0) {
        TypedArray typedArray0 = this.getContext().getTheme().obtainStyledAttributes(ActionBarOverlayLayout.ATTRS);
        boolean z = false;
        this.mActionBarHeight = typedArray0.getDimensionPixelSize(0, 0);
        Drawable drawable0 = typedArray0.getDrawable(1);
        this.mWindowContentOverlay = drawable0;
        if(drawable0 == null) {
            z = true;
        }
        this.setWillNotDraw(z);
        typedArray0.recycle();
        this.mFlingEstimator = new OverScroller(context0);
    }

    @Override  // androidx.appcompat.widget.DecorContentParent
    public void initFeature(int v) {
        this.pullChildren();
        switch(v) {
            case 2: {
                this.mDecorToolbar.initProgress();
                return;
            }
            case 5: {
                this.mDecorToolbar.initIndeterminateProgress();
                return;
            }
            case 109: {
                this.setOverlayMode(true);
            }
        }
    }

    public boolean isHideOnContentScrollEnabled() {
        return this.mHideOnContentScroll;
    }

    public boolean isInOverlayMode() {
        return this.mOverlayMode;
    }

    @Override  // androidx.appcompat.widget.DecorContentParent
    public boolean isOverflowMenuShowPending() {
        this.pullChildren();
        return this.mDecorToolbar.isOverflowMenuShowPending();
    }

    @Override  // androidx.appcompat.widget.DecorContentParent
    public boolean isOverflowMenuShowing() {
        this.pullChildren();
        return this.mDecorToolbar.isOverflowMenuShowing();
    }

    @Override  // android.view.View
    public WindowInsets onApplyWindowInsets(WindowInsets windowInsets0) {
        this.pullChildren();
        WindowInsetsCompat windowInsetsCompat0 = WindowInsetsCompat.toWindowInsetsCompat(windowInsets0, this);
        Rect rect0 = new Rect(windowInsetsCompat0.getSystemWindowInsetLeft(), windowInsetsCompat0.getSystemWindowInsetTop(), windowInsetsCompat0.getSystemWindowInsetRight(), windowInsetsCompat0.getSystemWindowInsetBottom());
        boolean z = this.applyInsets(this.mActionBarTop, rect0, true, true, false, true);
        ViewCompat.computeSystemWindowInsets(this, windowInsetsCompat0, this.mBaseContentInsets);
        WindowInsetsCompat windowInsetsCompat1 = windowInsetsCompat0.inset(this.mBaseContentInsets.left, this.mBaseContentInsets.top, this.mBaseContentInsets.right, this.mBaseContentInsets.bottom);
        this.mBaseInnerInsets = windowInsetsCompat1;
        boolean z1 = true;
        if(!this.mLastBaseInnerInsets.equals(windowInsetsCompat1)) {
            this.mLastBaseInnerInsets = this.mBaseInnerInsets;
            z = true;
        }
        if(this.mLastBaseContentInsets.equals(this.mBaseContentInsets)) {
            z1 = z;
        }
        else {
            this.mLastBaseContentInsets.set(this.mBaseContentInsets);
        }
        if(z1) {
            this.requestLayout();
        }
        return windowInsetsCompat0.consumeDisplayCutout().consumeSystemWindowInsets().consumeStableInsets().toWindowInsets();
    }

    @Override  // android.view.View
    protected void onConfigurationChanged(Configuration configuration0) {
        super.onConfigurationChanged(configuration0);
        this.init(this.getContext());
        ViewCompat.requestApplyInsets(this);
    }

    @Override  // android.view.ViewGroup
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.haltActionBarHideOffsetAnimations();
    }

    @Override  // android.view.ViewGroup
    protected void onLayout(boolean z, int v, int v1, int v2, int v3) {
        int v4 = this.getChildCount();
        int v5 = this.getPaddingLeft();
        int v6 = this.getPaddingTop();
        for(int v7 = 0; v7 < v4; ++v7) {
            View view0 = this.getChildAt(v7);
            if(view0.getVisibility() != 8) {
                LayoutParams actionBarOverlayLayout$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
                int v8 = view0.getMeasuredWidth();
                int v9 = view0.getMeasuredHeight();
                int v10 = actionBarOverlayLayout$LayoutParams0.leftMargin + v5;
                int v11 = actionBarOverlayLayout$LayoutParams0.topMargin + v6;
                view0.layout(v10, v11, v8 + v10, v9 + v11);
            }
        }
    }

    @Override  // android.view.View
    protected void onMeasure(int v, int v1) {
        int v5;
        this.pullChildren();
        this.measureChildWithMargins(this.mActionBarTop, v, 0, v1, 0);
        LayoutParams actionBarOverlayLayout$LayoutParams0 = (LayoutParams)this.mActionBarTop.getLayoutParams();
        int v2 = Math.max(0, this.mActionBarTop.getMeasuredWidth() + actionBarOverlayLayout$LayoutParams0.leftMargin + actionBarOverlayLayout$LayoutParams0.rightMargin);
        int v3 = Math.max(0, this.mActionBarTop.getMeasuredHeight() + actionBarOverlayLayout$LayoutParams0.topMargin + actionBarOverlayLayout$LayoutParams0.bottomMargin);
        int v4 = View.combineMeasuredStates(0, this.mActionBarTop.getMeasuredState());
        boolean z = (ViewCompat.getWindowSystemUiVisibility(this) & 0x100) != 0;
        if(z) {
            v5 = this.mActionBarHeight;
            if(this.mHasNonEmbeddedTabs && this.mActionBarTop.getTabContainer() != null) {
                v5 += this.mActionBarHeight;
            }
        }
        else {
            v5 = this.mActionBarTop.getVisibility() == 8 ? 0 : this.mActionBarTop.getMeasuredHeight();
        }
        this.mContentInsets.set(this.mBaseContentInsets);
        this.mInnerInsets = this.mBaseInnerInsets;
        if(this.mOverlayMode || z || !this.decorFitsSystemWindows()) {
            Insets insets0 = Insets.of(this.mInnerInsets.getSystemWindowInsetLeft(), this.mInnerInsets.getSystemWindowInsetTop() + v5, this.mInnerInsets.getSystemWindowInsetRight(), this.mInnerInsets.getSystemWindowInsetBottom());
            this.mInnerInsets = new Builder(this.mInnerInsets).setSystemWindowInsets(insets0).build();
        }
        else {
            this.mContentInsets.top += v5;
            this.mContentInsets.bottom = this.mContentInsets.bottom;
            this.mInnerInsets = this.mInnerInsets.inset(0, v5, 0, 0);
        }
        this.applyInsets(this.mContent, this.mContentInsets, true, true, true, true);
        if(!this.mLastInnerInsets.equals(this.mInnerInsets)) {
            this.mLastInnerInsets = this.mInnerInsets;
            ViewCompat.dispatchApplyWindowInsets(this.mContent, this.mInnerInsets);
        }
        this.measureChildWithMargins(this.mContent, v, 0, v1, 0);
        LayoutParams actionBarOverlayLayout$LayoutParams1 = (LayoutParams)this.mContent.getLayoutParams();
        int v6 = Math.max(v2, this.mContent.getMeasuredWidth() + actionBarOverlayLayout$LayoutParams1.leftMargin + actionBarOverlayLayout$LayoutParams1.rightMargin);
        int v7 = Math.max(v3, this.mContent.getMeasuredHeight() + actionBarOverlayLayout$LayoutParams1.topMargin + actionBarOverlayLayout$LayoutParams1.bottomMargin);
        int v8 = View.combineMeasuredStates(v4, this.mContent.getMeasuredState());
        int v9 = this.getPaddingLeft();
        int v10 = this.getPaddingRight();
        int v11 = Math.max(v7 + (this.getPaddingTop() + this.getPaddingBottom()), this.getSuggestedMinimumHeight());
        this.setMeasuredDimension(View.resolveSizeAndState(Math.max(v6 + (v9 + v10), this.getSuggestedMinimumWidth()), v, v8), View.resolveSizeAndState(v11, v1, v8 << 16));
    }

    @Override  // android.view.ViewGroup, androidx.core.view.NestedScrollingParent
    public boolean onNestedFling(View view0, float f, float f1, boolean z) {
        if(this.mHideOnContentScroll && z) {
            if(this.shouldHideActionBarOnFling(f1)) {
                this.addActionBarHideOffset();
            }
            else {
                this.removeActionBarHideOffset();
            }
            this.mAnimatingForFling = true;
            return true;
        }
        return false;
    }

    @Override  // android.view.ViewGroup, androidx.core.view.NestedScrollingParent
    public boolean onNestedPreFling(View view0, float f, float f1) {
        return false;
    }

    @Override  // android.view.ViewGroup, androidx.core.view.NestedScrollingParent
    public void onNestedPreScroll(View view0, int v, int v1, int[] arr_v) {
    }

    @Override  // androidx.core.view.NestedScrollingParent2
    public void onNestedPreScroll(View view0, int v, int v1, int[] arr_v, int v2) {
    }

    @Override  // android.view.ViewGroup, androidx.core.view.NestedScrollingParent
    public void onNestedScroll(View view0, int v, int v1, int v2, int v3) {
        int v4 = this.mHideOnContentScrollReference + v1;
        this.mHideOnContentScrollReference = v4;
        this.setActionBarHideOffset(v4);
    }

    @Override  // androidx.core.view.NestedScrollingParent2
    public void onNestedScroll(View view0, int v, int v1, int v2, int v3, int v4) {
        if(v4 == 0) {
            this.onNestedScroll(view0, v, v1, v2, v3);
        }
    }

    @Override  // androidx.core.view.NestedScrollingParent3
    public void onNestedScroll(View view0, int v, int v1, int v2, int v3, int v4, int[] arr_v) {
        this.onNestedScroll(view0, v, v1, v2, v3, v4);
    }

    @Override  // android.view.ViewGroup, androidx.core.view.NestedScrollingParent
    public void onNestedScrollAccepted(View view0, View view1, int v) {
        this.mParentHelper.onNestedScrollAccepted(view0, view1, v);
        this.mHideOnContentScrollReference = this.getActionBarHideOffset();
        this.haltActionBarHideOffsetAnimations();
        ActionBarVisibilityCallback actionBarOverlayLayout$ActionBarVisibilityCallback0 = this.mActionBarVisibilityCallback;
        if(actionBarOverlayLayout$ActionBarVisibilityCallback0 != null) {
            actionBarOverlayLayout$ActionBarVisibilityCallback0.onContentScrollStarted();
        }
    }

    @Override  // androidx.core.view.NestedScrollingParent2
    public void onNestedScrollAccepted(View view0, View view1, int v, int v1) {
        if(v1 == 0) {
            this.onNestedScrollAccepted(view0, view1, v);
        }
    }

    @Override  // android.view.ViewGroup, androidx.core.view.NestedScrollingParent
    public boolean onStartNestedScroll(View view0, View view1, int v) {
        return (v & 2) == 0 || this.mActionBarTop.getVisibility() != 0 ? false : this.mHideOnContentScroll;
    }

    @Override  // androidx.core.view.NestedScrollingParent2
    public boolean onStartNestedScroll(View view0, View view1, int v, int v1) {
        return v1 == 0 && this.onStartNestedScroll(view0, view1, v);
    }

    @Override  // android.view.ViewGroup, androidx.core.view.NestedScrollingParent
    public void onStopNestedScroll(View view0) {
        if(this.mHideOnContentScroll && !this.mAnimatingForFling) {
            if(this.mHideOnContentScrollReference <= this.mActionBarTop.getHeight()) {
                this.postRemoveActionBarHideOffset();
            }
            else {
                this.postAddActionBarHideOffset();
            }
        }
        ActionBarVisibilityCallback actionBarOverlayLayout$ActionBarVisibilityCallback0 = this.mActionBarVisibilityCallback;
        if(actionBarOverlayLayout$ActionBarVisibilityCallback0 != null) {
            actionBarOverlayLayout$ActionBarVisibilityCallback0.onContentScrollStopped();
        }
    }

    @Override  // androidx.core.view.NestedScrollingParent2
    public void onStopNestedScroll(View view0, int v) {
        if(v == 0) {
            this.onStopNestedScroll(view0);
        }
    }

    @Override  // android.view.View
    @Deprecated
    public void onWindowSystemUiVisibilityChanged(int v) {
        super.onWindowSystemUiVisibilityChanged(v);
        this.pullChildren();
        int v1 = this.mLastSystemUiVisibility ^ v;
        this.mLastSystemUiVisibility = v;
        int v2 = (v & 0x100) == 0 ? 0 : 1;
        ActionBarVisibilityCallback actionBarOverlayLayout$ActionBarVisibilityCallback0 = this.mActionBarVisibilityCallback;
        if(actionBarOverlayLayout$ActionBarVisibilityCallback0 != null) {
            actionBarOverlayLayout$ActionBarVisibilityCallback0.enableContentAnimations(((boolean)(v2 ^ 1)));
            if((v & 4) == 0 || v2 == 0) {
                this.mActionBarVisibilityCallback.showForSystem();
            }
            else {
                this.mActionBarVisibilityCallback.hideForSystem();
            }
        }
        if((v1 & 0x100) != 0 && this.mActionBarVisibilityCallback != null) {
            ViewCompat.requestApplyInsets(this);
        }
    }

    @Override  // android.view.View
    protected void onWindowVisibilityChanged(int v) {
        super.onWindowVisibilityChanged(v);
        this.mWindowVisibility = v;
        ActionBarVisibilityCallback actionBarOverlayLayout$ActionBarVisibilityCallback0 = this.mActionBarVisibilityCallback;
        if(actionBarOverlayLayout$ActionBarVisibilityCallback0 != null) {
            actionBarOverlayLayout$ActionBarVisibilityCallback0.onWindowVisibilityChanged(v);
        }
    }

    private void postAddActionBarHideOffset() {
        this.haltActionBarHideOffsetAnimations();
        this.postDelayed(this.mAddActionBarHideOffset, 600L);
    }

    private void postRemoveActionBarHideOffset() {
        this.haltActionBarHideOffsetAnimations();
        this.postDelayed(this.mRemoveActionBarHideOffset, 600L);
    }

    void pullChildren() {
        if(this.mContent == null) {
            this.mContent = (ContentFrameLayout)this.findViewById(id.action_bar_activity_content);
            this.mActionBarTop = (ActionBarContainer)this.findViewById(id.action_bar_container);
            this.mDecorToolbar = this.getDecorToolbar(this.findViewById(id.action_bar));
        }
    }

    private void removeActionBarHideOffset() {
        this.haltActionBarHideOffsetAnimations();
        this.mRemoveActionBarHideOffset.run();
    }

    @Override  // androidx.appcompat.widget.DecorContentParent
    public void restoreToolbarHierarchyState(SparseArray sparseArray0) {
        this.pullChildren();
        this.mDecorToolbar.restoreHierarchyState(sparseArray0);
    }

    @Override  // androidx.appcompat.widget.DecorContentParent
    public void saveToolbarHierarchyState(SparseArray sparseArray0) {
        this.pullChildren();
        this.mDecorToolbar.saveHierarchyState(sparseArray0);
    }

    public void setActionBarHideOffset(int v) {
        this.haltActionBarHideOffsetAnimations();
        int v1 = Math.max(0, Math.min(v, this.mActionBarTop.getHeight()));
        this.mActionBarTop.setTranslationY(((float)(-v1)));
    }

    public void setActionBarVisibilityCallback(ActionBarVisibilityCallback actionBarOverlayLayout$ActionBarVisibilityCallback0) {
        this.mActionBarVisibilityCallback = actionBarOverlayLayout$ActionBarVisibilityCallback0;
        if(this.getWindowToken() != null) {
            this.mActionBarVisibilityCallback.onWindowVisibilityChanged(this.mWindowVisibility);
            int v = this.mLastSystemUiVisibility;
            if(v != 0) {
                this.onWindowSystemUiVisibilityChanged(v);
                ViewCompat.requestApplyInsets(this);
            }
        }
    }

    public void setHasNonEmbeddedTabs(boolean z) {
        this.mHasNonEmbeddedTabs = z;
    }

    public void setHideOnContentScrollEnabled(boolean z) {
        if(z != this.mHideOnContentScroll) {
            this.mHideOnContentScroll = z;
            if(!z) {
                this.haltActionBarHideOffsetAnimations();
                this.setActionBarHideOffset(0);
            }
        }
    }

    @Override  // androidx.appcompat.widget.DecorContentParent
    public void setIcon(int v) {
        this.pullChildren();
        this.mDecorToolbar.setIcon(v);
    }

    @Override  // androidx.appcompat.widget.DecorContentParent
    public void setIcon(Drawable drawable0) {
        this.pullChildren();
        this.mDecorToolbar.setIcon(drawable0);
    }

    @Override  // androidx.appcompat.widget.DecorContentParent
    public void setLogo(int v) {
        this.pullChildren();
        this.mDecorToolbar.setLogo(v);
    }

    @Override  // androidx.appcompat.widget.DecorContentParent
    public void setMenu(Menu menu0, Callback menuPresenter$Callback0) {
        this.pullChildren();
        this.mDecorToolbar.setMenu(menu0, menuPresenter$Callback0);
    }

    @Override  // androidx.appcompat.widget.DecorContentParent
    public void setMenuPrepared() {
        this.pullChildren();
        this.mDecorToolbar.setMenuPrepared();
    }

    public void setOverlayMode(boolean z) {
        this.mOverlayMode = z;
    }

    public void setShowingForActionMode(boolean z) {
    }

    @Override  // androidx.appcompat.widget.DecorContentParent
    public void setUiOptions(int v) {
    }

    @Override  // androidx.appcompat.widget.DecorContentParent
    public void setWindowCallback(Window.Callback window$Callback0) {
        this.pullChildren();
        this.mDecorToolbar.setWindowCallback(window$Callback0);
    }

    @Override  // androidx.appcompat.widget.DecorContentParent
    public void setWindowTitle(CharSequence charSequence0) {
        this.pullChildren();
        this.mDecorToolbar.setWindowTitle(charSequence0);
    }

    @Override  // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    private boolean shouldHideActionBarOnFling(float f) {
        this.mFlingEstimator.fling(0, 0, 0, ((int)f), 0, 0, 0x80000000, 0x7FFFFFFF);
        return this.mFlingEstimator.getFinalY() > this.mActionBarTop.getHeight();
    }

    @Override  // androidx.appcompat.widget.DecorContentParent
    public boolean showOverflowMenu() {
        this.pullChildren();
        return this.mDecorToolbar.showOverflowMenu();
    }
}

