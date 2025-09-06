package androidx.appcompat.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.R.attr;
import androidx.appcompat.R.id;
import androidx.appcompat.R.layout;
import androidx.appcompat.R.styleable;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;

public class ActionBarContextView extends AbsActionBarView {
    private View mClose;
    private View mCloseButton;
    private int mCloseItemLayout;
    private View mCustomView;
    private CharSequence mSubtitle;
    private int mSubtitleStyleRes;
    private TextView mSubtitleView;
    private CharSequence mTitle;
    private LinearLayout mTitleLayout;
    private boolean mTitleOptional;
    private int mTitleStyleRes;
    private TextView mTitleView;

    public ActionBarContextView(Context context0) {
        this(context0, null);
    }

    public ActionBarContextView(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, attr.actionModeStyle);
    }

    public ActionBarContextView(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        TintTypedArray tintTypedArray0 = TintTypedArray.obtainStyledAttributes(context0, attributeSet0, styleable.ActionMode, v, 0);
        this.setBackground(tintTypedArray0.getDrawable(styleable.ActionMode_background));
        this.mTitleStyleRes = tintTypedArray0.getResourceId(styleable.ActionMode_titleTextStyle, 0);
        this.mSubtitleStyleRes = tintTypedArray0.getResourceId(styleable.ActionMode_subtitleTextStyle, 0);
        this.mContentHeight = tintTypedArray0.getLayoutDimension(styleable.ActionMode_height, 0);
        this.mCloseItemLayout = tintTypedArray0.getResourceId(styleable.ActionMode_closeItemLayout, layout.abc_action_mode_close_item_material);
        tintTypedArray0.recycle();
    }

    @Override  // androidx.appcompat.widget.AbsActionBarView
    public void animateToVisibility(int v) {
        super.animateToVisibility(v);
    }

    @Override  // androidx.appcompat.widget.AbsActionBarView
    public boolean canShowOverflowMenu() {
        return super.canShowOverflowMenu();
    }

    public void closeMode() {
        if(this.mClose == null) {
            this.killMode();
        }
    }

    @Override  // androidx.appcompat.widget.AbsActionBarView
    public void dismissPopupMenus() {
        super.dismissPopupMenus();
    }

    @Override  // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.MarginLayoutParams(-1, -2);
    }

    @Override  // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet0) {
        return new ViewGroup.MarginLayoutParams(this.getContext(), attributeSet0);
    }

    @Override  // androidx.appcompat.widget.AbsActionBarView
    public int getAnimatedVisibility() {
        return super.getAnimatedVisibility();
    }

    @Override  // androidx.appcompat.widget.AbsActionBarView
    public int getContentHeight() {
        return super.getContentHeight();
    }

    public CharSequence getSubtitle() {
        return this.mSubtitle;
    }

    public CharSequence getTitle() {
        return this.mTitle;
    }

    @Override  // androidx.appcompat.widget.AbsActionBarView
    public boolean hideOverflowMenu() {
        return this.mActionMenuPresenter == null ? false : this.mActionMenuPresenter.hideOverflowMenu();
    }

    public void initForMode(ActionMode actionMode0) {
        View view0 = this.mClose;
        if(view0 == null) {
            View view1 = LayoutInflater.from(this.getContext()).inflate(this.mCloseItemLayout, this, false);
            this.mClose = view1;
            this.addView(view1);
        }
        else if(view0.getParent() == null) {
            this.addView(this.mClose);
        }
        View view2 = this.mClose.findViewById(id.action_mode_close_button);
        this.mCloseButton = view2;
        view2.setOnClickListener(new View.OnClickListener() {
            @Override  // android.view.View$OnClickListener
            public void onClick(View view0) {
                actionMode0.finish();
            }
        });
        MenuBuilder menuBuilder0 = (MenuBuilder)actionMode0.getMenu();
        if(this.mActionMenuPresenter != null) {
            this.mActionMenuPresenter.dismissPopupMenus();
        }
        this.mActionMenuPresenter = new ActionMenuPresenter(this.getContext());
        this.mActionMenuPresenter.setReserveOverflow(true);
        ViewGroup.LayoutParams viewGroup$LayoutParams0 = new ViewGroup.LayoutParams(-2, -1);
        menuBuilder0.addMenuPresenter(this.mActionMenuPresenter, this.mPopupContext);
        this.mMenuView = (ActionMenuView)this.mActionMenuPresenter.getMenuView(this);
        this.mMenuView.setBackground(null);
        this.addView(this.mMenuView, viewGroup$LayoutParams0);
    }

    private void initTitle() {
        if(this.mTitleLayout == null) {
            LayoutInflater.from(this.getContext()).inflate(layout.abc_action_bar_title_item, this);
            LinearLayout linearLayout0 = (LinearLayout)this.getChildAt(this.getChildCount() - 1);
            this.mTitleLayout = linearLayout0;
            this.mTitleView = (TextView)linearLayout0.findViewById(id.action_bar_title);
            this.mSubtitleView = (TextView)this.mTitleLayout.findViewById(id.action_bar_subtitle);
            if(this.mTitleStyleRes != 0) {
                this.mTitleView.setTextAppearance(this.getContext(), this.mTitleStyleRes);
            }
            if(this.mSubtitleStyleRes != 0) {
                this.mSubtitleView.setTextAppearance(this.getContext(), this.mSubtitleStyleRes);
            }
        }
        this.mTitleView.setText(this.mTitle);
        this.mSubtitleView.setText(this.mSubtitle);
        boolean z = TextUtils.isEmpty(this.mTitle);
        boolean z1 = TextUtils.isEmpty(this.mSubtitle);
        int v = 0;
        this.mSubtitleView.setVisibility((z1 ? 8 : 0));
        LinearLayout linearLayout1 = this.mTitleLayout;
        if(z && z1) {
            v = 8;
        }
        linearLayout1.setVisibility(v);
        if(this.mTitleLayout.getParent() == null) {
            this.addView(this.mTitleLayout);
        }
    }

    @Override  // androidx.appcompat.widget.AbsActionBarView
    public boolean isOverflowMenuShowPending() {
        return super.isOverflowMenuShowPending();
    }

    @Override  // androidx.appcompat.widget.AbsActionBarView
    public boolean isOverflowMenuShowing() {
        return this.mActionMenuPresenter == null ? false : this.mActionMenuPresenter.isOverflowMenuShowing();
    }

    @Override  // androidx.appcompat.widget.AbsActionBarView
    public boolean isOverflowReserved() {
        return super.isOverflowReserved();
    }

    public boolean isTitleOptional() {
        return this.mTitleOptional;
    }

    public void killMode() {
        this.removeAllViews();
        this.mCustomView = null;
        this.mMenuView = null;
        this.mActionMenuPresenter = null;
        View view0 = this.mCloseButton;
        if(view0 != null) {
            view0.setOnClickListener(null);
        }
    }

    @Override  // android.view.ViewGroup
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if(this.mActionMenuPresenter != null) {
            this.mActionMenuPresenter.hideOverflowMenu();
            this.mActionMenuPresenter.hideSubMenus();
        }
    }

    @Override  // androidx.appcompat.widget.AbsActionBarView
    public boolean onHoverEvent(MotionEvent motionEvent0) {
        return super.onHoverEvent(motionEvent0);
    }

    @Override  // android.view.ViewGroup
    protected void onLayout(boolean z, int v, int v1, int v2, int v3) {
        boolean z1 = ViewUtils.isLayoutRtl(this);
        int v4 = z1 ? v2 - v - this.getPaddingRight() : this.getPaddingLeft();
        int v5 = this.getPaddingTop();
        int v6 = v3 - v1 - this.getPaddingTop() - this.getPaddingBottom();
        if(this.mClose != null && this.mClose.getVisibility() != 8) {
            ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0 = (ViewGroup.MarginLayoutParams)this.mClose.getLayoutParams();
            int v7 = z1 ? viewGroup$MarginLayoutParams0.leftMargin : viewGroup$MarginLayoutParams0.rightMargin;
            int v8 = ActionBarContextView.next(v4, (z1 ? viewGroup$MarginLayoutParams0.rightMargin : viewGroup$MarginLayoutParams0.leftMargin), z1);
            v4 = ActionBarContextView.next(v8 + this.positionChild(this.mClose, v8, v5, v6, z1), v7, z1);
        }
        int v9 = this.mTitleLayout == null || this.mCustomView != null || this.mTitleLayout.getVisibility() == 8 ? v4 : v4 + this.positionChild(this.mTitleLayout, v4, v5, v6, z1);
        View view0 = this.mCustomView;
        if(view0 != null) {
            this.positionChild(view0, v9, v5, v6, z1);
        }
        int v10 = z1 ? this.getPaddingLeft() : v2 - v - this.getPaddingRight();
        if(this.mMenuView != null) {
            this.positionChild(this.mMenuView, v10, v5, v6, !z1);
        }
    }

    @Override  // android.view.View
    protected void onMeasure(int v, int v1) {
        int v2 = 0x40000000;
        if(View.MeasureSpec.getMode(v) != 0x40000000) {
            throw new IllegalStateException(this.getClass().getSimpleName() + " can only be used with android:layout_width=\"match_parent\" (or fill_parent)");
        }
        if(View.MeasureSpec.getMode(v1) == 0) {
            throw new IllegalStateException(this.getClass().getSimpleName() + " can only be used with android:layout_height=\"wrap_content\"");
        }
        int v3 = View.MeasureSpec.getSize(v);
        int v4 = this.mContentHeight <= 0 ? View.MeasureSpec.getSize(v1) : this.mContentHeight;
        int v5 = this.getPaddingTop() + this.getPaddingBottom();
        int v6 = v3 - this.getPaddingLeft() - this.getPaddingRight();
        int v7 = v4 - v5;
        int v8 = View.MeasureSpec.makeMeasureSpec(v7, 0x80000000);
        View view0 = this.mClose;
        if(view0 != null) {
            int v10 = this.measureChildView(view0, v6, v8, 0);
            ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0 = (ViewGroup.MarginLayoutParams)this.mClose.getLayoutParams();
            v6 = v10 - (viewGroup$MarginLayoutParams0.leftMargin + viewGroup$MarginLayoutParams0.rightMargin);
        }
        if(this.mMenuView != null && this.mMenuView.getParent() == this) {
            v6 = this.measureChildView(this.mMenuView, v6, v8, 0);
        }
        LinearLayout linearLayout0 = this.mTitleLayout;
        if(linearLayout0 != null && this.mCustomView == null) {
            if(this.mTitleOptional) {
                this.mTitleLayout.measure(0, v8);
                int v11 = this.mTitleLayout.getMeasuredWidth();
                boolean z = v11 <= v6;
                if(z) {
                    v6 -= v11;
                }
                this.mTitleLayout.setVisibility((z ? 0 : 8));
            }
            else {
                v6 = this.measureChildView(linearLayout0, v6, v8, 0);
            }
        }
        View view1 = this.mCustomView;
        if(view1 != null) {
            ViewGroup.LayoutParams viewGroup$LayoutParams0 = view1.getLayoutParams();
            int v12 = viewGroup$LayoutParams0.width == -2 ? 0x80000000 : 0x40000000;
            if(viewGroup$LayoutParams0.width >= 0) {
                v6 = Math.min(viewGroup$LayoutParams0.width, v6);
            }
            if(viewGroup$LayoutParams0.height == -2) {
                v2 = 0x80000000;
            }
            if(viewGroup$LayoutParams0.height >= 0) {
                v7 = Math.min(viewGroup$LayoutParams0.height, v7);
            }
            this.mCustomView.measure(View.MeasureSpec.makeMeasureSpec(v6, v12), View.MeasureSpec.makeMeasureSpec(v7, v2));
        }
        if(this.mContentHeight <= 0) {
            int v13 = this.getChildCount();
            int v14 = 0;
            for(int v9 = 0; v9 < v13; ++v9) {
                int v15 = this.getChildAt(v9).getMeasuredHeight() + v5;
                if(v15 > v14) {
                    v14 = v15;
                }
            }
            this.setMeasuredDimension(v3, v14);
            return;
        }
        this.setMeasuredDimension(v3, v4);
    }

    @Override  // androidx.appcompat.widget.AbsActionBarView
    public boolean onTouchEvent(MotionEvent motionEvent0) {
        return super.onTouchEvent(motionEvent0);
    }

    @Override  // androidx.appcompat.widget.AbsActionBarView
    public void postShowOverflowMenu() {
        super.postShowOverflowMenu();
    }

    @Override  // androidx.appcompat.widget.AbsActionBarView
    public void setContentHeight(int v) {
        this.mContentHeight = v;
    }

    public void setCustomView(View view0) {
        View view1 = this.mCustomView;
        if(view1 != null) {
            this.removeView(view1);
        }
        this.mCustomView = view0;
        if(view0 != null) {
            LinearLayout linearLayout0 = this.mTitleLayout;
            if(linearLayout0 != null) {
                this.removeView(linearLayout0);
                this.mTitleLayout = null;
            }
        }
        if(view0 != null) {
            this.addView(view0);
        }
        this.requestLayout();
    }

    public void setSubtitle(CharSequence charSequence0) {
        this.mSubtitle = charSequence0;
        this.initTitle();
    }

    public void setTitle(CharSequence charSequence0) {
        this.mTitle = charSequence0;
        this.initTitle();
        ViewCompat.setAccessibilityPaneTitle(this, charSequence0);
    }

    public void setTitleOptional(boolean z) {
        if(z != this.mTitleOptional) {
            this.requestLayout();
        }
        this.mTitleOptional = z;
    }

    @Override  // androidx.appcompat.widget.AbsActionBarView
    public void setVisibility(int v) {
        super.setVisibility(v);
    }

    @Override  // androidx.appcompat.widget.AbsActionBarView
    public ViewPropertyAnimatorCompat setupAnimatorToVisibility(int v, long v1) {
        return super.setupAnimatorToVisibility(v, v1);
    }

    @Override  // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    @Override  // androidx.appcompat.widget.AbsActionBarView
    public boolean showOverflowMenu() {
        return this.mActionMenuPresenter == null ? false : this.mActionMenuPresenter.showOverflowMenu();
    }
}

