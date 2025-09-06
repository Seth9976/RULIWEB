package com.google.android.material.navigationrail;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View.MeasureSpec;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.R.attr;
import com.google.android.material.R.dimen;
import com.google.android.material.R.style;
import com.google.android.material.R.styleable;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils.OnApplyWindowInsetsListener;
import com.google.android.material.internal.ViewUtils.RelativePadding;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.navigation.NavigationBarMenuView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.resources.MaterialResources;

public class NavigationRailView extends NavigationBarView {
    private static final int DEFAULT_HEADER_GRAVITY = 49;
    static final int DEFAULT_MENU_GRAVITY = 49;
    static final int MAX_ITEM_COUNT = 7;
    static final int NO_ITEM_MINIMUM_HEIGHT = -1;
    private View headerView;
    private Boolean paddingBottomSystemWindowInsets;
    private Boolean paddingStartSystemWindowInsets;
    private Boolean paddingTopSystemWindowInsets;
    private final int topMargin;

    public NavigationRailView(Context context0) {
        this(context0, null);
    }

    public NavigationRailView(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, attr.navigationRailStyle);
    }

    public NavigationRailView(Context context0, AttributeSet attributeSet0, int v) {
        this(context0, attributeSet0, v, style.Widget_MaterialComponents_NavigationRailView);
    }

    public NavigationRailView(Context context0, AttributeSet attributeSet0, int v, int v1) {
        super(context0, attributeSet0, v, v1);
        this.paddingTopSystemWindowInsets = null;
        this.paddingBottomSystemWindowInsets = null;
        this.paddingStartSystemWindowInsets = null;
        this.topMargin = this.getResources().getDimensionPixelSize(dimen.mtrl_navigation_rail_margin);
        Context context1 = this.getContext();
        TintTypedArray tintTypedArray0 = ThemeEnforcement.obtainTintedStyledAttributes(context1, attributeSet0, styleable.NavigationRailView, v, v1, new int[0]);
        int v2 = tintTypedArray0.getResourceId(styleable.NavigationRailView_headerLayout, 0);
        if(v2 != 0) {
            this.addHeaderView(v2);
        }
        this.setMenuGravity(tintTypedArray0.getInt(styleable.NavigationRailView_menuGravity, 49));
        if(tintTypedArray0.hasValue(styleable.NavigationRailView_itemMinHeight)) {
            this.setItemMinimumHeight(tintTypedArray0.getDimensionPixelSize(styleable.NavigationRailView_itemMinHeight, -1));
        }
        if(tintTypedArray0.hasValue(styleable.NavigationRailView_paddingTopSystemWindowInsets)) {
            this.paddingTopSystemWindowInsets = Boolean.valueOf(tintTypedArray0.getBoolean(styleable.NavigationRailView_paddingTopSystemWindowInsets, false));
        }
        if(tintTypedArray0.hasValue(styleable.NavigationRailView_paddingBottomSystemWindowInsets)) {
            this.paddingBottomSystemWindowInsets = Boolean.valueOf(tintTypedArray0.getBoolean(styleable.NavigationRailView_paddingBottomSystemWindowInsets, false));
        }
        if(tintTypedArray0.hasValue(styleable.NavigationRailView_paddingStartSystemWindowInsets)) {
            this.paddingStartSystemWindowInsets = Boolean.valueOf(tintTypedArray0.getBoolean(styleable.NavigationRailView_paddingStartSystemWindowInsets, false));
        }
        int v3 = this.getResources().getDimensionPixelOffset(dimen.m3_navigation_rail_item_padding_top_with_large_font);
        int v4 = this.getResources().getDimensionPixelOffset(dimen.m3_navigation_rail_item_padding_bottom_with_large_font);
        float f = AnimationUtils.lerp(0.0f, 1.0f, 0.3f, 1.0f, MaterialResources.getFontScale(context1) - 1.0f);
        int v5 = this.getItemPaddingTop();
        int v6 = this.getItemPaddingBottom();
        this.setItemPaddingTop(Math.round(AnimationUtils.lerp(v5, v3, f)));
        this.setItemPaddingBottom(Math.round(AnimationUtils.lerp(v6, v4, f)));
        tintTypedArray0.recycle();
        this.applyWindowInsets();
    }

    public void addHeaderView(int v) {
        this.addHeaderView(LayoutInflater.from(this.getContext()).inflate(v, this, false));
    }

    public void addHeaderView(View view0) {
        this.removeHeaderView();
        this.headerView = view0;
        FrameLayout.LayoutParams frameLayout$LayoutParams0 = new FrameLayout.LayoutParams(-2, -2);
        frameLayout$LayoutParams0.gravity = 49;
        frameLayout$LayoutParams0.topMargin = this.topMargin;
        this.addView(view0, 0, frameLayout$LayoutParams0);
    }

    private void applyWindowInsets() {
        ViewUtils.doOnApplyWindowInsets(this, new OnApplyWindowInsetsListener() {
            @Override  // com.google.android.material.internal.ViewUtils$OnApplyWindowInsetsListener
            public WindowInsetsCompat onApplyWindowInsets(View view0, WindowInsetsCompat windowInsetsCompat0, RelativePadding viewUtils$RelativePadding0) {
                Insets insets0 = windowInsetsCompat0.getInsets(0x207);
                if(NavigationRailView.this.shouldApplyWindowInsetPadding(NavigationRailView.this.paddingTopSystemWindowInsets)) {
                    viewUtils$RelativePadding0.top += insets0.top;
                }
                if(NavigationRailView.this.shouldApplyWindowInsetPadding(NavigationRailView.this.paddingBottomSystemWindowInsets)) {
                    viewUtils$RelativePadding0.bottom += insets0.bottom;
                }
                if(NavigationRailView.this.shouldApplyWindowInsetPadding(NavigationRailView.this.paddingStartSystemWindowInsets)) {
                    viewUtils$RelativePadding0.start += (ViewUtils.isLayoutRtl(view0) ? insets0.right : insets0.left);
                }
                viewUtils$RelativePadding0.applyToView(view0);
                return windowInsetsCompat0;
            }
        });
    }

    @Override  // com.google.android.material.navigation.NavigationBarView
    protected NavigationBarMenuView createNavigationBarMenuView(Context context0) {
        return this.createNavigationBarMenuView(context0);
    }

    protected NavigationRailMenuView createNavigationBarMenuView(Context context0) {
        return new NavigationRailMenuView(context0);
    }

    public View getHeaderView() {
        return this.headerView;
    }

    public int getItemMinimumHeight() {
        return ((NavigationRailMenuView)this.getMenuView()).getItemMinimumHeight();
    }

    @Override  // com.google.android.material.navigation.NavigationBarView
    public int getMaxItemCount() {
        return 7;
    }

    public int getMenuGravity() {
        return this.getNavigationRailMenuView().getMenuGravity();
    }

    private NavigationRailMenuView getNavigationRailMenuView() {
        return (NavigationRailMenuView)this.getMenuView();
    }

    private boolean isHeaderViewVisible() {
        return this.headerView != null && this.headerView.getVisibility() != 8;
    }

    private int makeMinWidthSpec(int v) {
        int v1 = this.getSuggestedMinimumWidth();
        if(View.MeasureSpec.getMode(v) != 0x40000000 && v1 > 0) {
            int v2 = this.getPaddingLeft();
            int v3 = this.getPaddingRight();
            return View.MeasureSpec.makeMeasureSpec(Math.min(View.MeasureSpec.getSize(v), v1 + (v2 + v3)), 0x40000000);
        }
        return v;
    }

    @Override  // android.widget.FrameLayout
    protected void onLayout(boolean z, int v, int v1, int v2, int v3) {
        super.onLayout(z, v, v1, v2, v3);
        NavigationRailMenuView navigationRailMenuView0 = this.getNavigationRailMenuView();
        int v4 = 0;
        if(this.isHeaderViewVisible()) {
            int v5 = this.headerView.getBottom() + this.topMargin;
            int v6 = navigationRailMenuView0.getTop();
            if(v6 < v5) {
                v4 = v5 - v6;
            }
        }
        else if(navigationRailMenuView0.isTopGravity()) {
            v4 = this.topMargin;
        }
        if(v4 > 0) {
            navigationRailMenuView0.layout(navigationRailMenuView0.getLeft(), navigationRailMenuView0.getTop() + v4, navigationRailMenuView0.getRight(), navigationRailMenuView0.getBottom() + v4);
        }
    }

    @Override  // android.widget.FrameLayout
    protected void onMeasure(int v, int v1) {
        int v2 = this.makeMinWidthSpec(v);
        super.onMeasure(v2, v1);
        if(this.isHeaderViewVisible()) {
            this.measureChild(this.getNavigationRailMenuView(), v2, View.MeasureSpec.makeMeasureSpec(this.getMeasuredHeight() - this.headerView.getMeasuredHeight() - this.topMargin, 0x80000000));
        }
    }

    public void removeHeaderView() {
        View view0 = this.headerView;
        if(view0 != null) {
            this.removeView(view0);
            this.headerView = null;
        }
    }

    public void setItemMinimumHeight(int v) {
        ((NavigationRailMenuView)this.getMenuView()).setItemMinimumHeight(v);
    }

    public void setMenuGravity(int v) {
        this.getNavigationRailMenuView().setMenuGravity(v);
    }

    private boolean shouldApplyWindowInsetPadding(Boolean boolean0) {
        return boolean0 == null ? ViewCompat.getFitsSystemWindows(this) : boolean0.booleanValue();
    }
}

