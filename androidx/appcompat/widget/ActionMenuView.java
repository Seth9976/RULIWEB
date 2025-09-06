package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.MeasureSpec;
import android.view.View;
import android.view.ViewDebug.ExportedProperty;
import android.view.ViewGroup.LayoutParams;
import android.view.accessibility.AccessibilityEvent;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.view.menu.MenuBuilder.ItemInvoker;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuPresenter.Callback;
import androidx.appcompat.view.menu.MenuView;

public class ActionMenuView extends LinearLayoutCompat implements ItemInvoker, MenuView {
    public interface ActionMenuChildView {
        boolean needsDividerAfter();

        boolean needsDividerBefore();
    }

    static class ActionMenuPresenterCallback implements Callback {
        @Override  // androidx.appcompat.view.menu.MenuPresenter$Callback
        public void onCloseMenu(MenuBuilder menuBuilder0, boolean z) {
        }

        @Override  // androidx.appcompat.view.menu.MenuPresenter$Callback
        public boolean onOpenSubMenu(MenuBuilder menuBuilder0) {
            return false;
        }
    }

    public static class LayoutParams extends androidx.appcompat.widget.LinearLayoutCompat.LayoutParams {
        @ViewDebug.ExportedProperty
        public int cellsUsed;
        @ViewDebug.ExportedProperty
        public boolean expandable;
        boolean expanded;
        @ViewDebug.ExportedProperty
        public int extraPixels;
        @ViewDebug.ExportedProperty
        public boolean isOverflowButton;
        @ViewDebug.ExportedProperty
        public boolean preventEdgeOffset;

        public LayoutParams(int v, int v1) {
            super(v, v1);
            this.isOverflowButton = false;
        }

        LayoutParams(int v, int v1, boolean z) {
            super(v, v1);
            this.isOverflowButton = z;
        }

        public LayoutParams(Context context0, AttributeSet attributeSet0) {
            super(context0, attributeSet0);
        }

        public LayoutParams(ViewGroup.LayoutParams viewGroup$LayoutParams0) {
            super(viewGroup$LayoutParams0);
        }

        public LayoutParams(LayoutParams actionMenuView$LayoutParams0) {
            super(actionMenuView$LayoutParams0);
            this.isOverflowButton = actionMenuView$LayoutParams0.isOverflowButton;
        }
    }

    class MenuBuilderCallback implements androidx.appcompat.view.menu.MenuBuilder.Callback {
        @Override  // androidx.appcompat.view.menu.MenuBuilder$Callback
        public boolean onMenuItemSelected(MenuBuilder menuBuilder0, MenuItem menuItem0) {
            return ActionMenuView.this.mOnMenuItemClickListener != null && ActionMenuView.this.mOnMenuItemClickListener.onMenuItemClick(menuItem0);
        }

        @Override  // androidx.appcompat.view.menu.MenuBuilder$Callback
        public void onMenuModeChange(MenuBuilder menuBuilder0) {
            if(ActionMenuView.this.mMenuBuilderCallback != null) {
                ActionMenuView.this.mMenuBuilderCallback.onMenuModeChange(menuBuilder0);
            }
        }
    }

    public interface OnMenuItemClickListener {
        boolean onMenuItemClick(MenuItem arg1);
    }

    static final int GENERATED_ITEM_PADDING = 4;
    static final int MIN_CELL_SIZE = 56;
    private static final String TAG = "ActionMenuView";
    private Callback mActionMenuPresenterCallback;
    private boolean mFormatItems;
    private int mFormatItemsWidth;
    private int mGeneratedItemPadding;
    private MenuBuilder mMenu;
    androidx.appcompat.view.menu.MenuBuilder.Callback mMenuBuilderCallback;
    private int mMinCellSize;
    OnMenuItemClickListener mOnMenuItemClickListener;
    private Context mPopupContext;
    private int mPopupTheme;
    private ActionMenuPresenter mPresenter;
    private boolean mReserveOverflow;

    public ActionMenuView(Context context0) {
        this(context0, null);
    }

    public ActionMenuView(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
        this.setBaselineAligned(false);
        DisplayMetrics displayMetrics0 = context0.getResources().getDisplayMetrics();
        this.mMinCellSize = (int)(56.0f * displayMetrics0.density);
        this.mGeneratedItemPadding = (int)(displayMetrics0.density * 4.0f);
        this.mPopupContext = context0;
        this.mPopupTheme = 0;
    }

    @Override  // androidx.appcompat.widget.LinearLayoutCompat
    protected boolean checkLayoutParams(ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        return viewGroup$LayoutParams0 instanceof LayoutParams;
    }

    public void dismissPopupMenus() {
        ActionMenuPresenter actionMenuPresenter0 = this.mPresenter;
        if(actionMenuPresenter0 != null) {
            actionMenuPresenter0.dismissPopupMenus();
        }
    }

    @Override  // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent0) {
        return false;
    }

    @Override  // androidx.appcompat.widget.LinearLayoutCompat
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return this.generateDefaultLayoutParams();
    }

    protected LayoutParams generateDefaultLayoutParams() {
        LayoutParams actionMenuView$LayoutParams0 = new LayoutParams(-2, -2);
        actionMenuView$LayoutParams0.gravity = 16;
        return actionMenuView$LayoutParams0;
    }

    @Override  // androidx.appcompat.widget.LinearLayoutCompat
    protected androidx.appcompat.widget.LinearLayoutCompat.LayoutParams generateDefaultLayoutParams() {
        return this.generateDefaultLayoutParams();
    }

    @Override  // androidx.appcompat.widget.LinearLayoutCompat
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet0) {
        return this.generateLayoutParams(attributeSet0);
    }

    @Override  // androidx.appcompat.widget.LinearLayoutCompat
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        return this.generateLayoutParams(viewGroup$LayoutParams0);
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet0) {
        return new LayoutParams(this.getContext(), attributeSet0);
    }

    protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        if(viewGroup$LayoutParams0 != null) {
            LayoutParams actionMenuView$LayoutParams0 = viewGroup$LayoutParams0 instanceof LayoutParams ? new LayoutParams(((LayoutParams)viewGroup$LayoutParams0)) : new LayoutParams(viewGroup$LayoutParams0);
            if(actionMenuView$LayoutParams0.gravity <= 0) {
                actionMenuView$LayoutParams0.gravity = 16;
            }
            return actionMenuView$LayoutParams0;
        }
        return this.generateDefaultLayoutParams();
    }

    @Override  // androidx.appcompat.widget.LinearLayoutCompat
    public androidx.appcompat.widget.LinearLayoutCompat.LayoutParams generateLayoutParams(AttributeSet attributeSet0) {
        return this.generateLayoutParams(attributeSet0);
    }

    @Override  // androidx.appcompat.widget.LinearLayoutCompat
    protected androidx.appcompat.widget.LinearLayoutCompat.LayoutParams generateLayoutParams(ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        return this.generateLayoutParams(viewGroup$LayoutParams0);
    }

    public LayoutParams generateOverflowButtonLayoutParams() {
        LayoutParams actionMenuView$LayoutParams0 = this.generateDefaultLayoutParams();
        actionMenuView$LayoutParams0.isOverflowButton = true;
        return actionMenuView$LayoutParams0;
    }

    public Menu getMenu() {
        if(this.mMenu == null) {
            Context context0 = this.getContext();
            MenuBuilder menuBuilder0 = new MenuBuilder(context0);
            this.mMenu = menuBuilder0;
            menuBuilder0.setCallback(new MenuBuilderCallback(this));
            ActionMenuPresenter actionMenuPresenter0 = new ActionMenuPresenter(context0);
            this.mPresenter = actionMenuPresenter0;
            actionMenuPresenter0.setReserveOverflow(true);
            ActionMenuPresenter actionMenuPresenter1 = this.mPresenter;
            Callback menuPresenter$Callback0 = this.mActionMenuPresenterCallback;
            if(menuPresenter$Callback0 == null) {
                menuPresenter$Callback0 = new ActionMenuPresenterCallback();
            }
            actionMenuPresenter1.setCallback(menuPresenter$Callback0);
            this.mMenu.addMenuPresenter(this.mPresenter, this.mPopupContext);
            this.mPresenter.setMenuView(this);
        }
        return this.mMenu;
    }

    public Drawable getOverflowIcon() {
        this.getMenu();
        return this.mPresenter.getOverflowIcon();
    }

    public int getPopupTheme() {
        return this.mPopupTheme;
    }

    @Override  // androidx.appcompat.view.menu.MenuView
    public int getWindowAnimations() {
        return 0;
    }

    protected boolean hasSupportDividerBeforeChildAt(int v) {
        boolean z = false;
        if(v == 0) {
            return false;
        }
        View view0 = this.getChildAt(v - 1);
        View view1 = this.getChildAt(v);
        if(v < this.getChildCount() && view0 instanceof ActionMenuChildView) {
            z = ((ActionMenuChildView)view0).needsDividerAfter();
        }
        return v > 0 && view1 instanceof ActionMenuChildView ? ((ActionMenuChildView)view1).needsDividerBefore() | z : z;
    }

    public boolean hideOverflowMenu() {
        return this.mPresenter != null && this.mPresenter.hideOverflowMenu();
    }

    @Override  // androidx.appcompat.view.menu.MenuView
    public void initialize(MenuBuilder menuBuilder0) {
        this.mMenu = menuBuilder0;
    }

    @Override  // androidx.appcompat.view.menu.MenuBuilder$ItemInvoker
    public boolean invokeItem(MenuItemImpl menuItemImpl0) {
        return this.mMenu.performItemAction(menuItemImpl0, 0);
    }

    public boolean isOverflowMenuShowPending() {
        return this.mPresenter != null && this.mPresenter.isOverflowMenuShowPending();
    }

    public boolean isOverflowMenuShowing() {
        return this.mPresenter != null && this.mPresenter.isOverflowMenuShowing();
    }

    public boolean isOverflowReserved() {
        return this.mReserveOverflow;
    }

    static int measureChildForCells(View view0, int v, int v1, int v2, int v3) {
        LayoutParams actionMenuView$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
        int v4 = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(v2) - v3, View.MeasureSpec.getMode(v2));
        ActionMenuItemView actionMenuItemView0 = view0 instanceof ActionMenuItemView ? ((ActionMenuItemView)view0) : null;
        boolean z = true;
        boolean z1 = actionMenuItemView0 != null && actionMenuItemView0.hasText();
        int v5 = 2;
        if(v1 <= 0 || z1 && v1 < 2) {
            v5 = 0;
        }
        else {
            view0.measure(View.MeasureSpec.makeMeasureSpec(v1 * v, 0x80000000), v4);
            int v6 = view0.getMeasuredWidth();
            int v7 = v6 / v;
            if(v6 % v != 0) {
                ++v7;
            }
            if(!z1 || v7 >= 2) {
                v5 = v7;
            }
        }
        if(actionMenuView$LayoutParams0.isOverflowButton || !z1) {
            z = false;
        }
        actionMenuView$LayoutParams0.expandable = z;
        actionMenuView$LayoutParams0.cellsUsed = v5;
        view0.measure(View.MeasureSpec.makeMeasureSpec(v * v5, 0x40000000), v4);
        return v5;
    }

    @Override  // android.view.View
    public void onConfigurationChanged(Configuration configuration0) {
        super.onConfigurationChanged(configuration0);
        ActionMenuPresenter actionMenuPresenter0 = this.mPresenter;
        if(actionMenuPresenter0 != null) {
            actionMenuPresenter0.updateMenuView(false);
            if(this.mPresenter.isOverflowMenuShowing()) {
                this.mPresenter.hideOverflowMenu();
                this.mPresenter.showOverflowMenu();
            }
        }
    }

    @Override  // android.view.ViewGroup
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.dismissPopupMenus();
    }

    @Override  // androidx.appcompat.widget.LinearLayoutCompat
    protected void onLayout(boolean z, int v, int v1, int v2, int v3) {
        int v15;
        int v14;
        if(!this.mFormatItems) {
            super.onLayout(z, v, v1, v2, v3);
            return;
        }
        int v4 = this.getChildCount();
        int v5 = (v3 - v1) / 2;
        int v6 = this.getDividerWidth();
        int v7 = v2 - v;
        int v8 = v7 - this.getPaddingRight() - this.getPaddingLeft();
        boolean z1 = ViewUtils.isLayoutRtl(this);
        int v10 = 0;
        int v11 = 0;
        for(int v9 = 0; v9 < v4; ++v9) {
            View view0 = this.getChildAt(v9);
            if(view0.getVisibility() != 8) {
                LayoutParams actionMenuView$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
                if(actionMenuView$LayoutParams0.isOverflowButton) {
                    int v12 = view0.getMeasuredWidth();
                    if(this.hasSupportDividerBeforeChildAt(v9)) {
                        v12 += v6;
                    }
                    int v13 = view0.getMeasuredHeight();
                    if(z1) {
                        v14 = this.getPaddingLeft() + actionMenuView$LayoutParams0.leftMargin;
                        v15 = v14 + v12;
                    }
                    else {
                        v15 = this.getWidth() - this.getPaddingRight() - actionMenuView$LayoutParams0.rightMargin;
                        v14 = v15 - v12;
                    }
                    int v16 = v5 - v13 / 2;
                    view0.layout(v14, v16, v15, v13 + v16);
                    v8 -= v12;
                    v10 = 1;
                }
                else {
                    v8 -= view0.getMeasuredWidth() + actionMenuView$LayoutParams0.leftMargin + actionMenuView$LayoutParams0.rightMargin;
                    this.hasSupportDividerBeforeChildAt(v9);
                    ++v11;
                }
            }
        }
        if(v4 == 1 && v10 == 0) {
            View view1 = this.getChildAt(0);
            int v17 = view1.getMeasuredWidth();
            int v18 = view1.getMeasuredHeight();
            int v19 = v7 / 2 - v17 / 2;
            int v20 = v5 - v18 / 2;
            view1.layout(v19, v20, v17 + v19, v18 + v20);
            return;
        }
        int v21 = v11 - (v10 ^ 1);
        int v22 = Math.max(0, (v21 <= 0 ? 0 : v8 / v21));
        if(z1) {
            int v23 = this.getWidth() - this.getPaddingRight();
            for(int v24 = 0; v24 < v4; ++v24) {
                View view2 = this.getChildAt(v24);
                LayoutParams actionMenuView$LayoutParams1 = (LayoutParams)view2.getLayoutParams();
                if(view2.getVisibility() != 8 && !actionMenuView$LayoutParams1.isOverflowButton) {
                    int v25 = v23 - actionMenuView$LayoutParams1.rightMargin;
                    int v26 = view2.getMeasuredWidth();
                    int v27 = view2.getMeasuredHeight();
                    int v28 = v5 - v27 / 2;
                    view2.layout(v25 - v26, v28, v25, v27 + v28);
                    v23 = v25 - (v26 + actionMenuView$LayoutParams1.leftMargin + v22);
                }
            }
            return;
        }
        int v29 = this.getPaddingLeft();
        for(int v30 = 0; v30 < v4; ++v30) {
            View view3 = this.getChildAt(v30);
            LayoutParams actionMenuView$LayoutParams2 = (LayoutParams)view3.getLayoutParams();
            if(view3.getVisibility() != 8 && !actionMenuView$LayoutParams2.isOverflowButton) {
                int v31 = v29 + actionMenuView$LayoutParams2.leftMargin;
                int v32 = view3.getMeasuredWidth();
                int v33 = view3.getMeasuredHeight();
                int v34 = v5 - v33 / 2;
                view3.layout(v31, v34, v31 + v32, v33 + v34);
                v29 = v31 + (v32 + actionMenuView$LayoutParams2.rightMargin + v22);
            }
        }
    }

    @Override  // androidx.appcompat.widget.LinearLayoutCompat
    protected void onMeasure(int v, int v1) {
        boolean z = this.mFormatItems;
        boolean z1 = View.MeasureSpec.getMode(v) == 0x40000000;
        this.mFormatItems = z1;
        if(z != z1) {
            this.mFormatItemsWidth = 0;
        }
        int v2 = View.MeasureSpec.getSize(v);
        if(this.mFormatItems) {
            MenuBuilder menuBuilder0 = this.mMenu;
            if(menuBuilder0 != null && v2 != this.mFormatItemsWidth) {
                this.mFormatItemsWidth = v2;
                menuBuilder0.onItemsChanged(true);
            }
        }
        int v3 = this.getChildCount();
        if(this.mFormatItems && v3 > 0) {
            this.onMeasureExactFormat(v, v1);
            return;
        }
        for(int v4 = 0; v4 < v3; ++v4) {
            LayoutParams actionMenuView$LayoutParams0 = (LayoutParams)this.getChildAt(v4).getLayoutParams();
            actionMenuView$LayoutParams0.rightMargin = 0;
            actionMenuView$LayoutParams0.leftMargin = 0;
        }
        super.onMeasure(v, v1);
    }

    private void onMeasureExactFormat(int v, int v1) {
        int v29;
        int v28;
        boolean z1;
        int v20;
        int v2 = View.MeasureSpec.getMode(v1);
        int v3 = View.MeasureSpec.getSize(v);
        int v4 = View.MeasureSpec.getSize(v1);
        int v5 = this.getPaddingLeft();
        int v6 = this.getPaddingRight();
        int v7 = this.getPaddingTop() + this.getPaddingBottom();
        int v8 = ActionMenuView.getChildMeasureSpec(v1, v7, -2);
        int v9 = v3 - (v5 + v6);
        int v10 = this.mMinCellSize;
        int v11 = v9 / v10;
        if(v11 == 0) {
            this.setMeasuredDimension(v9, 0);
            return;
        }
        int v12 = v10 + v9 % v10 / v11;
        int v13 = this.getChildCount();
        int v14 = 0;
        int v15 = 0;
        boolean z = false;
        int v16 = 0;
        int v17 = 0;
        int v18 = 0;
        long v19 = 0L;
        while(v15 < v13) {
            View view0 = this.getChildAt(v15);
            if(view0.getVisibility() == 8) {
                v20 = v12;
            }
            else {
                ++v16;
                if(view0 instanceof ActionMenuItemView) {
                    z1 = view0 instanceof ActionMenuItemView;
                    view0.setPadding(this.mGeneratedItemPadding, 0, this.mGeneratedItemPadding, 0);
                }
                else {
                    z1 = view0 instanceof ActionMenuItemView;
                }
                LayoutParams actionMenuView$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
                actionMenuView$LayoutParams0.expanded = false;
                actionMenuView$LayoutParams0.extraPixels = 0;
                actionMenuView$LayoutParams0.cellsUsed = 0;
                actionMenuView$LayoutParams0.expandable = false;
                actionMenuView$LayoutParams0.leftMargin = 0;
                actionMenuView$LayoutParams0.rightMargin = 0;
                actionMenuView$LayoutParams0.preventEdgeOffset = z1 && ((ActionMenuItemView)view0).hasText();
                int v21 = ActionMenuView.measureChildForCells(view0, v12, (actionMenuView$LayoutParams0.isOverflowButton ? 1 : v11), v8, v7);
                v17 = Math.max(v17, v21);
                v20 = v12;
                if(actionMenuView$LayoutParams0.expandable) {
                    ++v18;
                }
                if(actionMenuView$LayoutParams0.isOverflowButton) {
                    z = true;
                }
                v11 -= v21;
                v14 = Math.max(v14, view0.getMeasuredHeight());
                if(v21 == 1) {
                    v19 |= (long)(1 << v15);
                }
            }
            ++v15;
            v12 = v20;
        }
        boolean z2;
        for(z2 = false; v18 > 0 && v11 > 0; z2 = true) {
            int v22 = 0x7FFFFFFF;
            long v23 = 0L;
            int v24 = 0;
            for(int v25 = 0; v25 < v13; ++v25) {
                LayoutParams actionMenuView$LayoutParams1 = (LayoutParams)this.getChildAt(v25).getLayoutParams();
                if(actionMenuView$LayoutParams1.expandable) {
                    if(actionMenuView$LayoutParams1.cellsUsed < v22) {
                        v23 = 1L << v25;
                        v22 = actionMenuView$LayoutParams1.cellsUsed;
                        v24 = 1;
                    }
                    else if(actionMenuView$LayoutParams1.cellsUsed == v22) {
                        v23 |= 1L << v25;
                        ++v24;
                    }
                }
            }
            v19 |= v23;
            if(v24 > v11) {
                break;
            }
            int v26 = v22 + 1;
            for(int v27 = 0; v27 < v13; v27 = v28 + 1) {
                View view1 = this.getChildAt(v27);
                LayoutParams actionMenuView$LayoutParams2 = (LayoutParams)view1.getLayoutParams();
                if((v23 & ((long)(1 << v27))) == 0L) {
                    if(actionMenuView$LayoutParams2.cellsUsed == v26) {
                        v19 |= (long)(1 << v27);
                    }
                    v28 = v27;
                }
                else {
                    if(!z || v16 != 2 || !actionMenuView$LayoutParams2.preventEdgeOffset || v11 != 1) {
                        v28 = v27;
                    }
                    else {
                        v28 = v27;
                        view1.setPadding(this.mGeneratedItemPadding + v12, 0, this.mGeneratedItemPadding, 0);
                    }
                    ++actionMenuView$LayoutParams2.cellsUsed;
                    actionMenuView$LayoutParams2.expanded = true;
                    --v11;
                }
            }
        }
        boolean z3 = !z && v16 == 1;
        if(v11 <= 0 || v19 == 0L || v11 >= v16 - 1 && !z3 && v17 <= 1) {
            v29 = 0;
        }
        else {
            v29 = 0;
            float f = (float)Long.bitCount(v19);
            if(!z3) {
                if((v19 & 1L) != 0L && !((LayoutParams)this.getChildAt(0).getLayoutParams()).preventEdgeOffset) {
                    f -= 0.5f;
                }
                if((v19 & ((long)(1 << v13 - 1))) != 0L && !((LayoutParams)this.getChildAt(v13 - 1).getLayoutParams()).preventEdgeOffset) {
                    f -= 0.5f;
                }
            }
            int v30 = f > 0.0f ? ((int)(((float)(v11 * v12)) / f)) : 0;
            for(int v31 = 0; v31 < v13; ++v31) {
                if((v19 & ((long)(1 << v31))) != 0L) {
                    View view2 = this.getChildAt(v31);
                    LayoutParams actionMenuView$LayoutParams3 = (LayoutParams)view2.getLayoutParams();
                    if(view2 instanceof ActionMenuItemView) {
                        actionMenuView$LayoutParams3.extraPixels = v30;
                        actionMenuView$LayoutParams3.expanded = true;
                        if(v31 == 0 && !actionMenuView$LayoutParams3.preventEdgeOffset) {
                            actionMenuView$LayoutParams3.leftMargin = -v30 / 2;
                        }
                        z2 = true;
                    }
                    else if(actionMenuView$LayoutParams3.isOverflowButton) {
                        actionMenuView$LayoutParams3.extraPixels = v30;
                        actionMenuView$LayoutParams3.expanded = true;
                        actionMenuView$LayoutParams3.rightMargin = -v30 / 2;
                        z2 = true;
                    }
                    else {
                        if(v31 != 0) {
                            actionMenuView$LayoutParams3.leftMargin = v30 / 2;
                        }
                        if(v31 != v13 - 1) {
                            actionMenuView$LayoutParams3.rightMargin = v30 / 2;
                        }
                    }
                }
            }
        }
        if(z2) {
            while(v29 < v13) {
                View view3 = this.getChildAt(v29);
                LayoutParams actionMenuView$LayoutParams4 = (LayoutParams)view3.getLayoutParams();
                if(actionMenuView$LayoutParams4.expanded) {
                    view3.measure(View.MeasureSpec.makeMeasureSpec(actionMenuView$LayoutParams4.cellsUsed * v12 + actionMenuView$LayoutParams4.extraPixels, 0x40000000), v8);
                }
                ++v29;
            }
        }
        this.setMeasuredDimension(v9, (v2 == 0x40000000 ? v4 : v14));
    }

    public MenuBuilder peekMenu() {
        return this.mMenu;
    }

    public void setExpandedActionViewsExclusive(boolean z) {
        this.mPresenter.setExpandedActionViewsExclusive(z);
    }

    public void setMenuCallbacks(Callback menuPresenter$Callback0, androidx.appcompat.view.menu.MenuBuilder.Callback menuBuilder$Callback0) {
        this.mActionMenuPresenterCallback = menuPresenter$Callback0;
        this.mMenuBuilderCallback = menuBuilder$Callback0;
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener actionMenuView$OnMenuItemClickListener0) {
        this.mOnMenuItemClickListener = actionMenuView$OnMenuItemClickListener0;
    }

    public void setOverflowIcon(Drawable drawable0) {
        this.getMenu();
        this.mPresenter.setOverflowIcon(drawable0);
    }

    public void setOverflowReserved(boolean z) {
        this.mReserveOverflow = z;
    }

    public void setPopupTheme(int v) {
        if(this.mPopupTheme != v) {
            this.mPopupTheme = v;
            if(v == 0) {
                this.mPopupContext = this.getContext();
                return;
            }
            this.mPopupContext = new ContextThemeWrapper(this.getContext(), v);
        }
    }

    public void setPresenter(ActionMenuPresenter actionMenuPresenter0) {
        this.mPresenter = actionMenuPresenter0;
        actionMenuPresenter0.setMenuView(this);
    }

    public boolean showOverflowMenu() {
        return this.mPresenter != null && this.mPresenter.showOverflowMenu();
    }
}

