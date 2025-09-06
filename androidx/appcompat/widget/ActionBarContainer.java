package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ActionMode.Callback;
import android.view.ActionMode;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout.LayoutParams;
import android.widget.FrameLayout;
import androidx.appcompat.R.id;
import androidx.appcompat.R.styleable;

public class ActionBarContainer extends FrameLayout {
    static class Api21Impl {
        public static void invalidateOutline(ActionBarContainer actionBarContainer0) {
            actionBarContainer0.invalidateOutline();
        }
    }

    private View mActionBarView;
    Drawable mBackground;
    private View mContextView;
    private int mHeight;
    boolean mIsSplit;
    boolean mIsStacked;
    private boolean mIsTransitioning;
    Drawable mSplitBackground;
    Drawable mStackedBackground;
    private View mTabContainer;

    public ActionBarContainer(Context context0) {
        this(context0, null);
    }

    public ActionBarContainer(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
        this.setBackground(new ActionBarBackgroundDrawable(this));
        TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, styleable.ActionBar);
        this.mBackground = typedArray0.getDrawable(styleable.ActionBar_background);
        this.mStackedBackground = typedArray0.getDrawable(styleable.ActionBar_backgroundStacked);
        this.mHeight = typedArray0.getDimensionPixelSize(styleable.ActionBar_height, -1);
        boolean z = true;
        if(this.getId() == id.split_action_bar) {
            this.mIsSplit = true;
            this.mSplitBackground = typedArray0.getDrawable(styleable.ActionBar_backgroundSplit);
        }
        typedArray0.recycle();
        if(!(this.mIsSplit ? this.mSplitBackground == null : this.mBackground == null && this.mStackedBackground == null)) {
            z = false;
        }
        this.setWillNotDraw(z);
    }

    @Override  // android.view.ViewGroup
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if(this.mBackground != null && this.mBackground.isStateful()) {
            this.mBackground.setState(this.getDrawableState());
        }
        if(this.mStackedBackground != null && this.mStackedBackground.isStateful()) {
            this.mStackedBackground.setState(this.getDrawableState());
        }
        if(this.mSplitBackground != null && this.mSplitBackground.isStateful()) {
            this.mSplitBackground.setState(this.getDrawableState());
        }
    }

    private int getMeasuredHeightWithMargins(View view0) {
        FrameLayout.LayoutParams frameLayout$LayoutParams0 = (FrameLayout.LayoutParams)view0.getLayoutParams();
        return view0.getMeasuredHeight() + frameLayout$LayoutParams0.topMargin + frameLayout$LayoutParams0.bottomMargin;
    }

    public View getTabContainer() {
        return this.mTabContainer;
    }

    private boolean isCollapsed(View view0) {
        return view0 == null || view0.getVisibility() == 8 || view0.getMeasuredHeight() == 0;
    }

    @Override  // android.view.ViewGroup
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable0 = this.mBackground;
        if(drawable0 != null) {
            drawable0.jumpToCurrentState();
        }
        Drawable drawable1 = this.mStackedBackground;
        if(drawable1 != null) {
            drawable1.jumpToCurrentState();
        }
        Drawable drawable2 = this.mSplitBackground;
        if(drawable2 != null) {
            drawable2.jumpToCurrentState();
        }
    }

    @Override  // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mActionBarView = this.findViewById(id.action_bar);
        this.mContextView = this.findViewById(id.action_context_bar);
    }

    @Override  // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent0) {
        super.onHoverEvent(motionEvent0);
        return true;
    }

    // 去混淆评级： 低(20)
    @Override  // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent0) {
        return this.mIsTransitioning || super.onInterceptTouchEvent(motionEvent0);
    }

    @Override  // android.widget.FrameLayout
    public void onLayout(boolean z, int v, int v1, int v2, int v3) {
        super.onLayout(z, v, v1, v2, v3);
        View view0 = this.mTabContainer;
        int v4 = 1;
        int v5 = 0;
        boolean z1 = view0 != null && view0.getVisibility() != 8;
        if(view0 != null && view0.getVisibility() != 8) {
            int v6 = this.getMeasuredHeight();
            FrameLayout.LayoutParams frameLayout$LayoutParams0 = (FrameLayout.LayoutParams)view0.getLayoutParams();
            view0.layout(v, v6 - view0.getMeasuredHeight() - frameLayout$LayoutParams0.bottomMargin, v2, v6 - frameLayout$LayoutParams0.bottomMargin);
        }
        if(this.mIsSplit) {
            Drawable drawable0 = this.mSplitBackground;
            if(drawable0 == null) {
                v4 = 0;
            }
            else {
                drawable0.setBounds(0, 0, this.getMeasuredWidth(), this.getMeasuredHeight());
            }
        }
        else {
            if(this.mBackground != null) {
                if(this.mActionBarView.getVisibility() == 0) {
                    this.mBackground.setBounds(this.mActionBarView.getLeft(), this.mActionBarView.getTop(), this.mActionBarView.getRight(), this.mActionBarView.getBottom());
                }
                else if(this.mContextView == null || this.mContextView.getVisibility() != 0) {
                    this.mBackground.setBounds(0, 0, 0, 0);
                }
                else {
                    this.mBackground.setBounds(this.mContextView.getLeft(), this.mContextView.getTop(), this.mContextView.getRight(), this.mContextView.getBottom());
                }
                v5 = 1;
            }
            this.mIsStacked = z1;
            if(z1) {
                Drawable drawable1 = this.mStackedBackground;
                if(drawable1 == null) {
                    v4 = v5;
                }
                else {
                    drawable1.setBounds(view0.getLeft(), view0.getTop(), view0.getRight(), view0.getBottom());
                }
            }
            else {
                v4 = v5;
            }
        }
        if(v4 != 0) {
            this.invalidate();
        }
    }

    @Override  // android.widget.FrameLayout
    public void onMeasure(int v, int v1) {
        int v4;
        if(this.mActionBarView == null && View.MeasureSpec.getMode(v1) == 0x80000000) {
            int v2 = this.mHeight;
            if(v2 >= 0) {
                v1 = View.MeasureSpec.makeMeasureSpec(Math.min(v2, View.MeasureSpec.getSize(v1)), 0x80000000);
            }
        }
        super.onMeasure(v, v1);
        if(this.mActionBarView != null) {
            int v3 = View.MeasureSpec.getMode(v1);
            if(this.mTabContainer != null && this.mTabContainer.getVisibility() != 8 && v3 != 0x40000000) {
                if(this.isCollapsed(this.mActionBarView)) {
                    v4 = this.isCollapsed(this.mContextView) ? 0 : this.getMeasuredHeightWithMargins(this.mContextView);
                }
                else {
                    v4 = this.getMeasuredHeightWithMargins(this.mActionBarView);
                }
                int v5 = v3 == 0x80000000 ? View.MeasureSpec.getSize(v1) : 0x7FFFFFFF;
                this.setMeasuredDimension(this.getMeasuredWidth(), Math.min(v4 + this.getMeasuredHeightWithMargins(this.mTabContainer), v5));
            }
        }
    }

    @Override  // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent0) {
        super.onTouchEvent(motionEvent0);
        return true;
    }

    public void setPrimaryBackground(Drawable drawable0) {
        Drawable drawable1 = this.mBackground;
        if(drawable1 != null) {
            drawable1.setCallback(null);
            this.unscheduleDrawable(this.mBackground);
        }
        this.mBackground = drawable0;
        if(drawable0 != null) {
            drawable0.setCallback(this);
            View view0 = this.mActionBarView;
            if(view0 != null) {
                this.mBackground.setBounds(view0.getLeft(), this.mActionBarView.getTop(), this.mActionBarView.getRight(), this.mActionBarView.getBottom());
            }
        }
        this.setWillNotDraw((this.mIsSplit ? this.mSplitBackground == null : this.mBackground == null && this.mStackedBackground == null));
        this.invalidate();
        Api21Impl.invalidateOutline(this);
    }

    public void setSplitBackground(Drawable drawable0) {
        Drawable drawable1 = this.mSplitBackground;
        if(drawable1 != null) {
            drawable1.setCallback(null);
            this.unscheduleDrawable(this.mSplitBackground);
        }
        this.mSplitBackground = drawable0;
        boolean z = false;
        if(drawable0 != null) {
            drawable0.setCallback(this);
            if(this.mIsSplit) {
                Drawable drawable2 = this.mSplitBackground;
                if(drawable2 != null) {
                    drawable2.setBounds(0, 0, this.getMeasuredWidth(), this.getMeasuredHeight());
                }
            }
        }
        if(!this.mIsSplit) {
            if(this.mBackground == null && this.mStackedBackground == null) {
                z = true;
            }
        }
        else if(this.mSplitBackground == null) {
            z = true;
        }
        this.setWillNotDraw(z);
        this.invalidate();
        Api21Impl.invalidateOutline(this);
    }

    public void setStackedBackground(Drawable drawable0) {
        Drawable drawable1 = this.mStackedBackground;
        if(drawable1 != null) {
            drawable1.setCallback(null);
            this.unscheduleDrawable(this.mStackedBackground);
        }
        this.mStackedBackground = drawable0;
        if(drawable0 != null) {
            drawable0.setCallback(this);
            if(this.mIsStacked) {
                Drawable drawable2 = this.mStackedBackground;
                if(drawable2 != null) {
                    drawable2.setBounds(this.mTabContainer.getLeft(), this.mTabContainer.getTop(), this.mTabContainer.getRight(), this.mTabContainer.getBottom());
                }
            }
        }
        this.setWillNotDraw((this.mIsSplit ? this.mSplitBackground == null : this.mBackground == null && this.mStackedBackground == null));
        this.invalidate();
        Api21Impl.invalidateOutline(this);
    }

    public void setTabContainer(ScrollingTabContainerView scrollingTabContainerView0) {
        View view0 = this.mTabContainer;
        if(view0 != null) {
            this.removeView(view0);
        }
        this.mTabContainer = scrollingTabContainerView0;
        if(scrollingTabContainerView0 != null) {
            this.addView(scrollingTabContainerView0);
            ViewGroup.LayoutParams viewGroup$LayoutParams0 = scrollingTabContainerView0.getLayoutParams();
            viewGroup$LayoutParams0.width = -1;
            viewGroup$LayoutParams0.height = -2;
            scrollingTabContainerView0.setAllowCollapse(false);
        }
    }

    public void setTransitioning(boolean z) {
        this.mIsTransitioning = z;
        this.setDescendantFocusability((z ? 0x60000 : 0x40000));
    }

    @Override  // android.view.View
    public void setVisibility(int v) {
        super.setVisibility(v);
        Drawable drawable0 = this.mBackground;
        if(drawable0 != null) {
            drawable0.setVisible(v == 0, false);
        }
        Drawable drawable1 = this.mStackedBackground;
        if(drawable1 != null) {
            drawable1.setVisible(v == 0, false);
        }
        Drawable drawable2 = this.mSplitBackground;
        if(drawable2 != null) {
            drawable2.setVisible(v == 0, false);
        }
    }

    @Override  // android.view.ViewGroup
    public ActionMode startActionModeForChild(View view0, ActionMode.Callback actionMode$Callback0) {
        return null;
    }

    @Override  // android.view.ViewGroup
    public ActionMode startActionModeForChild(View view0, ActionMode.Callback actionMode$Callback0, int v) {
        return v == 0 ? null : super.startActionModeForChild(view0, actionMode$Callback0, v);
    }

    // 去混淆评级： 中等(70)
    @Override  // android.view.View
    protected boolean verifyDrawable(Drawable drawable0) {
        return drawable0 == this.mBackground && !this.mIsSplit || drawable0 == this.mStackedBackground && this.mIsStacked || drawable0 == this.mSplitBackground && this.mIsSplit || super.verifyDrawable(drawable0);
    }
}

