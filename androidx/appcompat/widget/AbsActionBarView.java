package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.R.attr;
import androidx.appcompat.R.styleable;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.core.view.ViewPropertyAnimatorListener;

abstract class AbsActionBarView extends ViewGroup {
    public class VisibilityAnimListener implements ViewPropertyAnimatorListener {
        private boolean mCanceled;
        int mFinalVisibility;

        protected VisibilityAnimListener() {
            this.mCanceled = false;
        }

        @Override  // androidx.core.view.ViewPropertyAnimatorListener
        public void onAnimationCancel(View view0) {
            this.mCanceled = true;
        }

        @Override  // androidx.core.view.ViewPropertyAnimatorListener
        public void onAnimationEnd(View view0) {
            if(this.mCanceled) {
                return;
            }
            AbsActionBarView.this.mVisibilityAnim = null;
            AbsActionBarView.this.super.setVisibility(this.mFinalVisibility);
        }

        @Override  // androidx.core.view.ViewPropertyAnimatorListener
        public void onAnimationStart(View view0) {
            AbsActionBarView.this.super.setVisibility(0);
            this.mCanceled = false;
        }

        public VisibilityAnimListener withFinalVisibility(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat0, int v) {
            AbsActionBarView.this.mVisibilityAnim = viewPropertyAnimatorCompat0;
            this.mFinalVisibility = v;
            return this;
        }
    }

    private static final int FADE_DURATION = 200;
    protected ActionMenuPresenter mActionMenuPresenter;
    protected int mContentHeight;
    private boolean mEatingHover;
    private boolean mEatingTouch;
    protected ActionMenuView mMenuView;
    protected final Context mPopupContext;
    protected final VisibilityAnimListener mVisAnimListener;
    protected ViewPropertyAnimatorCompat mVisibilityAnim;

    AbsActionBarView(Context context0) {
        this(context0, null);
    }

    AbsActionBarView(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, 0);
    }

    AbsActionBarView(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        this.mVisAnimListener = new VisibilityAnimListener(this);
        TypedValue typedValue0 = new TypedValue();
        if(context0.getTheme().resolveAttribute(attr.actionBarPopupTheme, typedValue0, true) && typedValue0.resourceId != 0) {
            this.mPopupContext = new ContextThemeWrapper(context0, typedValue0.resourceId);
            return;
        }
        this.mPopupContext = context0;
    }

    public void animateToVisibility(int v) {
        this.setupAnimatorToVisibility(v, 200L).start();
    }

    public boolean canShowOverflowMenu() {
        return this.isOverflowReserved() && this.getVisibility() == 0;
    }

    public void dismissPopupMenus() {
        ActionMenuPresenter actionMenuPresenter0 = this.mActionMenuPresenter;
        if(actionMenuPresenter0 != null) {
            actionMenuPresenter0.dismissPopupMenus();
        }
    }

    public int getAnimatedVisibility() {
        return this.mVisibilityAnim == null ? this.getVisibility() : this.mVisAnimListener.mFinalVisibility;
    }

    public int getContentHeight() {
        return this.mContentHeight;
    }

    public boolean hideOverflowMenu() {
        return this.mActionMenuPresenter == null ? false : this.mActionMenuPresenter.hideOverflowMenu();
    }

    public boolean isOverflowMenuShowPending() {
        return this.mActionMenuPresenter == null ? false : this.mActionMenuPresenter.isOverflowMenuShowPending();
    }

    public boolean isOverflowMenuShowing() {
        return this.mActionMenuPresenter == null ? false : this.mActionMenuPresenter.isOverflowMenuShowing();
    }

    public boolean isOverflowReserved() {
        return this.mActionMenuPresenter != null && this.mActionMenuPresenter.isOverflowReserved();
    }

    protected int measureChildView(View view0, int v, int v1, int v2) {
        view0.measure(View.MeasureSpec.makeMeasureSpec(v, 0x80000000), v1);
        return Math.max(0, v - view0.getMeasuredWidth() - v2);
    }

    // 去混淆评级： 低(20)
    protected static int next(int v, int v1, boolean z) {
        return z ? v - v1 : v + v1;
    }

    @Override  // android.view.View
    protected void onConfigurationChanged(Configuration configuration0) {
        super.onConfigurationChanged(configuration0);
        TypedArray typedArray0 = this.getContext().obtainStyledAttributes(null, styleable.ActionBar, attr.actionBarStyle, 0);
        this.setContentHeight(typedArray0.getLayoutDimension(styleable.ActionBar_height, 0));
        typedArray0.recycle();
        ActionMenuPresenter actionMenuPresenter0 = this.mActionMenuPresenter;
        if(actionMenuPresenter0 != null) {
            actionMenuPresenter0.onConfigurationChanged(configuration0);
        }
    }

    @Override  // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent0) {
        int v = motionEvent0.getActionMasked();
        if(v == 9) {
            this.mEatingHover = false;
        }
        if(!this.mEatingHover && (v == 9 && !super.onHoverEvent(motionEvent0))) {
            this.mEatingHover = true;
        }
        if(v == 3 || v == 10) {
            this.mEatingHover = false;
        }
        return true;
    }

    @Override  // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent0) {
        int v = motionEvent0.getActionMasked();
        if(v == 0) {
            this.mEatingTouch = false;
        }
        if(!this.mEatingTouch && (v == 0 && !super.onTouchEvent(motionEvent0))) {
            this.mEatingTouch = true;
        }
        if(v == 1 || v == 3) {
            this.mEatingTouch = false;
        }
        return true;
    }

    protected int positionChild(View view0, int v, int v1, int v2, boolean z) {
        int v3 = view0.getMeasuredWidth();
        int v4 = view0.getMeasuredHeight();
        int v5 = v1 + (v2 - v4) / 2;
        if(z) {
            view0.layout(v - v3, v5, v, v4 + v5);
            return -v3;
        }
        view0.layout(v, v5, v + v3, v4 + v5);
        return v3;
    }

    public void postShowOverflowMenu() {
        this.post(() -> (AbsActionBarView.this.mActionMenuPresenter == null ? false : AbsActionBarView.this.mActionMenuPresenter.showOverflowMenu()));

        class androidx.appcompat.widget.AbsActionBarView.1 implements Runnable {
            @Override
            public void run() {
                AbsActionBarView.this.showOverflowMenu();
            }
        }

    }

    public void setContentHeight(int v) {
        this.mContentHeight = v;
        this.requestLayout();
    }

    @Override  // android.view.View
    public void setVisibility(int v) {
        if(v != this.getVisibility()) {
            ViewPropertyAnimatorCompat viewPropertyAnimatorCompat0 = this.mVisibilityAnim;
            if(viewPropertyAnimatorCompat0 != null) {
                viewPropertyAnimatorCompat0.cancel();
            }
            super.setVisibility(v);
        }
    }

    public ViewPropertyAnimatorCompat setupAnimatorToVisibility(int v, long v1) {
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat0 = this.mVisibilityAnim;
        if(viewPropertyAnimatorCompat0 != null) {
            viewPropertyAnimatorCompat0.cancel();
        }
        if(v == 0) {
            if(this.getVisibility() != 0) {
                this.setAlpha(0.0f);
            }
            ViewPropertyAnimatorCompat viewPropertyAnimatorCompat1 = ViewCompat.animate(this).alpha(1.0f);
            viewPropertyAnimatorCompat1.setDuration(v1);
            viewPropertyAnimatorCompat1.setListener(this.mVisAnimListener.withFinalVisibility(viewPropertyAnimatorCompat1, 0));
            return viewPropertyAnimatorCompat1;
        }
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat2 = ViewCompat.animate(this).alpha(0.0f);
        viewPropertyAnimatorCompat2.setDuration(v1);
        viewPropertyAnimatorCompat2.setListener(this.mVisAnimListener.withFinalVisibility(viewPropertyAnimatorCompat2, v));
        return viewPropertyAnimatorCompat2;
    }

    // 检测为 Lambda 实现
    public boolean showOverflowMenu() [...]
}

