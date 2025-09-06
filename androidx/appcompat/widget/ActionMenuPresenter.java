package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import androidx.appcompat.R.attr;
import androidx.appcompat.R.layout;
import androidx.appcompat.view.ActionBarPolicy;
import androidx.appcompat.view.menu.ActionMenuItemView.PopupCallback;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.view.menu.BaseMenuPresenter;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.appcompat.view.menu.MenuPresenter.Callback;
import androidx.appcompat.view.menu.MenuView.ItemView;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.view.menu.ShowableListMenu;
import androidx.appcompat.view.menu.SubMenuBuilder;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ActionProvider.SubUiVisibilityListener;
import androidx.core.view.ActionProvider;
import java.util.ArrayList;

class ActionMenuPresenter extends BaseMenuPresenter implements SubUiVisibilityListener {
    class ActionButtonSubmenu extends MenuPopupHelper {
        public ActionButtonSubmenu(Context context0, SubMenuBuilder subMenuBuilder0, View view0) {
            super(context0, subMenuBuilder0, view0, false, attr.actionOverflowMenuStyle);
            if(!((MenuItemImpl)subMenuBuilder0.getItem()).isActionButton()) {
                View view1 = actionMenuPresenter0.mOverflowButton == null ? ((View)actionMenuPresenter0.mMenuView) : actionMenuPresenter0.mOverflowButton;
                this.setAnchorView(view1);
            }
            this.setPresenterCallback(actionMenuPresenter0.mPopupPresenterCallback);
        }

        @Override  // androidx.appcompat.view.menu.MenuPopupHelper
        protected void onDismiss() {
            ActionMenuPresenter.this.mActionButtonPopup = null;
            ActionMenuPresenter.this.mOpenSubMenuId = 0;
            super.onDismiss();
        }
    }

    class ActionMenuPopupCallback extends PopupCallback {
        @Override  // androidx.appcompat.view.menu.ActionMenuItemView$PopupCallback
        public ShowableListMenu getPopup() {
            return ActionMenuPresenter.this.mActionButtonPopup != null ? ActionMenuPresenter.this.mActionButtonPopup.getPopup() : null;
        }
    }

    class OpenOverflowRunnable implements Runnable {
        private OverflowPopup mPopup;

        public OpenOverflowRunnable(OverflowPopup actionMenuPresenter$OverflowPopup0) {
            this.mPopup = actionMenuPresenter$OverflowPopup0;
        }

        @Override
        public void run() {
            if(ActionMenuPresenter.this.mMenu != null) {
                ActionMenuPresenter.this.mMenu.changeMenuMode();
            }
            View view0 = (View)ActionMenuPresenter.this.mMenuView;
            if(view0 != null && view0.getWindowToken() != null && this.mPopup.tryShow()) {
                ActionMenuPresenter.this.mOverflowPopup = this.mPopup;
            }
            ActionMenuPresenter.this.mPostedOpenRunnable = null;
        }
    }

    class OverflowMenuButton extends AppCompatImageView implements ActionMenuChildView {
        public OverflowMenuButton(Context context0) {
            super(context0, null, attr.actionOverflowButtonStyle);
            this.setClickable(true);
            this.setFocusable(true);
            this.setVisibility(0);
            this.setEnabled(true);
            TooltipCompat.setTooltipText(this, this.getContentDescription());
            this.setOnTouchListener(new ForwardingListener(this) {
                @Override  // androidx.appcompat.widget.ForwardingListener
                public ShowableListMenu getPopup() {
                    return ActionMenuPresenter.this.mOverflowPopup == null ? null : ActionMenuPresenter.this.mOverflowPopup.getPopup();
                }

                @Override  // androidx.appcompat.widget.ForwardingListener
                public boolean onForwardingStarted() {
                    ActionMenuPresenter.this.showOverflowMenu();
                    return true;
                }

                @Override  // androidx.appcompat.widget.ForwardingListener
                public boolean onForwardingStopped() {
                    if(ActionMenuPresenter.this.mPostedOpenRunnable != null) {
                        return false;
                    }
                    ActionMenuPresenter.this.hideOverflowMenu();
                    return true;
                }
            });
        }

        @Override  // androidx.appcompat.widget.ActionMenuView$ActionMenuChildView
        public boolean needsDividerAfter() {
            return false;
        }

        @Override  // androidx.appcompat.widget.ActionMenuView$ActionMenuChildView
        public boolean needsDividerBefore() {
            return false;
        }

        @Override  // android.view.View
        public boolean performClick() {
            if(super.performClick()) {
                return true;
            }
            this.playSoundEffect(0);
            ActionMenuPresenter.this.showOverflowMenu();
            return true;
        }

        @Override  // android.widget.ImageView
        protected boolean setFrame(int v, int v1, int v2, int v3) {
            boolean z = super.setFrame(v, v1, v2, v3);
            Drawable drawable0 = this.getDrawable();
            Drawable drawable1 = this.getBackground();
            if(drawable0 != null && drawable1 != null) {
                int v4 = this.getWidth();
                int v5 = this.getHeight();
                int v6 = Math.max(v4, v5);
                int v7 = (v4 + (this.getPaddingLeft() - this.getPaddingRight())) / 2;
                int v8 = (v5 + (this.getPaddingTop() - this.getPaddingBottom())) / 2;
                DrawableCompat.setHotspotBounds(drawable1, v7 - v6 / 2, v8 - v6 / 2, v7 + v6 / 2, v8 + v6 / 2);
            }
            return z;
        }
    }

    class OverflowPopup extends MenuPopupHelper {
        public OverflowPopup(Context context0, MenuBuilder menuBuilder0, View view0, boolean z) {
            super(context0, menuBuilder0, view0, z, attr.actionOverflowMenuStyle);
            this.setGravity(0x800005);
            this.setPresenterCallback(actionMenuPresenter0.mPopupPresenterCallback);
        }

        @Override  // androidx.appcompat.view.menu.MenuPopupHelper
        protected void onDismiss() {
            if(ActionMenuPresenter.this.mMenu != null) {
                ActionMenuPresenter.this.mMenu.close();
            }
            ActionMenuPresenter.this.mOverflowPopup = null;
            super.onDismiss();
        }
    }

    class PopupPresenterCallback implements Callback {
        @Override  // androidx.appcompat.view.menu.MenuPresenter$Callback
        public void onCloseMenu(MenuBuilder menuBuilder0, boolean z) {
            if(menuBuilder0 instanceof SubMenuBuilder) {
                menuBuilder0.getRootMenu().close(false);
            }
            Callback menuPresenter$Callback0 = ActionMenuPresenter.this.getCallback();
            if(menuPresenter$Callback0 != null) {
                menuPresenter$Callback0.onCloseMenu(menuBuilder0, z);
            }
        }

        @Override  // androidx.appcompat.view.menu.MenuPresenter$Callback
        public boolean onOpenSubMenu(MenuBuilder menuBuilder0) {
            if(menuBuilder0 == ActionMenuPresenter.this.mMenu) {
                return false;
            }
            ActionMenuPresenter.this.mOpenSubMenuId = ((SubMenuBuilder)menuBuilder0).getItem().getItemId();
            Callback menuPresenter$Callback0 = ActionMenuPresenter.this.getCallback();
            return menuPresenter$Callback0 == null ? false : menuPresenter$Callback0.onOpenSubMenu(menuBuilder0);
        }
    }

    static class SavedState implements Parcelable {
        public static final Parcelable.Creator CREATOR;
        public int openSubMenuId;

        static {
            SavedState.CREATOR = new Parcelable.Creator() {
                public SavedState createFromParcel(Parcel parcel0) {
                    return new SavedState(parcel0);
                }

                @Override  // android.os.Parcelable$Creator
                public Object createFromParcel(Parcel parcel0) {
                    return this.createFromParcel(parcel0);
                }

                public SavedState[] newArray(int v) {
                    return new SavedState[v];
                }

                @Override  // android.os.Parcelable$Creator
                public Object[] newArray(int v) {
                    return this.newArray(v);
                }
            };
        }

        SavedState() {
        }

        SavedState(Parcel parcel0) {
            this.openSubMenuId = parcel0.readInt();
        }

        @Override  // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override  // android.os.Parcelable
        public void writeToParcel(Parcel parcel0, int v) {
            parcel0.writeInt(this.openSubMenuId);
        }
    }

    private static final String TAG = "ActionMenuPresenter";
    private final SparseBooleanArray mActionButtonGroups;
    ActionButtonSubmenu mActionButtonPopup;
    private int mActionItemWidthLimit;
    private boolean mExpandedActionViewsExclusive;
    private int mMaxItems;
    private boolean mMaxItemsSet;
    private int mMinCellSize;
    int mOpenSubMenuId;
    OverflowMenuButton mOverflowButton;
    OverflowPopup mOverflowPopup;
    private Drawable mPendingOverflowIcon;
    private boolean mPendingOverflowIconSet;
    private ActionMenuPopupCallback mPopupCallback;
    final PopupPresenterCallback mPopupPresenterCallback;
    OpenOverflowRunnable mPostedOpenRunnable;
    private boolean mReserveOverflow;
    private boolean mReserveOverflowSet;
    private boolean mStrictWidthLimit;
    private int mWidthLimit;
    private boolean mWidthLimitSet;

    public ActionMenuPresenter(Context context0) {
        super(context0, layout.abc_action_menu_layout, layout.abc_action_menu_item_layout);
        this.mActionButtonGroups = new SparseBooleanArray();
        this.mPopupPresenterCallback = new PopupPresenterCallback(this);
    }

    @Override  // androidx.appcompat.view.menu.BaseMenuPresenter
    public void bindItemView(MenuItemImpl menuItemImpl0, ItemView menuView$ItemView0) {
        menuView$ItemView0.initialize(menuItemImpl0, 0);
        ((ActionMenuItemView)menuView$ItemView0).setItemInvoker(((ActionMenuView)this.mMenuView));
        if(this.mPopupCallback == null) {
            this.mPopupCallback = new ActionMenuPopupCallback(this);
        }
        ((ActionMenuItemView)menuView$ItemView0).setPopupCallback(this.mPopupCallback);
    }

    public boolean dismissPopupMenus() {
        return this.hideOverflowMenu() | this.hideSubMenus();
    }

    @Override  // androidx.appcompat.view.menu.BaseMenuPresenter
    public boolean filterLeftoverView(ViewGroup viewGroup0, int v) {
        return viewGroup0.getChildAt(v) == this.mOverflowButton ? false : super.filterLeftoverView(viewGroup0, v);
    }

    private View findViewForItem(MenuItem menuItem0) {
        ViewGroup viewGroup0 = (ViewGroup)this.mMenuView;
        if(viewGroup0 == null) {
            return null;
        }
        int v = viewGroup0.getChildCount();
        for(int v1 = 0; v1 < v; ++v1) {
            View view0 = viewGroup0.getChildAt(v1);
            if(view0 instanceof ItemView && ((ItemView)view0).getItemData() == menuItem0) {
                return view0;
            }
        }
        return null;
    }

    @Override  // androidx.appcompat.view.menu.BaseMenuPresenter
    public boolean flagActionItems() {
        boolean z4;
        int v14;
        int v9;
        int v8;
        int v;
        ArrayList arrayList0;
        if(this.mMenu == null) {
            arrayList0 = null;
            v = 0;
        }
        else {
            arrayList0 = this.mMenu.getVisibleItems();
            v = arrayList0.size();
        }
        int v1 = this.mMaxItems;
        int v2 = this.mActionItemWidthLimit;
        ViewGroup viewGroup0 = (ViewGroup)this.mMenuView;
        boolean z = false;
        int v4 = 0;
        int v5 = 0;
        for(int v3 = 0; v3 < v; ++v3) {
            MenuItemImpl menuItemImpl0 = (MenuItemImpl)arrayList0.get(v3);
            if(menuItemImpl0.requiresActionButton()) {
                ++v4;
            }
            else if(menuItemImpl0.requestsActionButton()) {
                ++v5;
            }
            else {
                z = true;
            }
            if(this.mExpandedActionViewsExclusive && menuItemImpl0.isActionViewExpanded()) {
                v1 = 0;
            }
        }
        if(this.mReserveOverflow && (z || v5 + v4 > v1)) {
            --v1;
        }
        int v6 = v1 - v4;
        SparseBooleanArray sparseBooleanArray0 = this.mActionButtonGroups;
        sparseBooleanArray0.clear();
        if(this.mStrictWidthLimit) {
            int v7 = this.mMinCellSize;
            v8 = v2 / v7;
            v9 = v7 + v2 % v7 / v8;
        }
        else {
            v9 = 0;
            v8 = 0;
        }
        int v10 = 0;
        int v11 = 0;
        while(v10 < v) {
            MenuItemImpl menuItemImpl1 = (MenuItemImpl)arrayList0.get(v10);
            if(menuItemImpl1.requiresActionButton()) {
                View view0 = this.getItemView(menuItemImpl1, null, viewGroup0);
                if(this.mStrictWidthLimit) {
                    v8 -= ActionMenuView.measureChildForCells(view0, v9, v8, 0, 0);
                }
                else {
                    view0.measure(0, 0);
                }
                int v12 = view0.getMeasuredWidth();
                v2 -= v12;
                if(v11 == 0) {
                    v11 = v12;
                }
                int v13 = menuItemImpl1.getGroupId();
                if(v13 != 0) {
                    sparseBooleanArray0.put(v13, true);
                }
                menuItemImpl1.setIsActionButton(true);
                v14 = v;
            }
            else if(menuItemImpl1.requestsActionButton()) {
                int v15 = menuItemImpl1.getGroupId();
                boolean z1 = sparseBooleanArray0.get(v15);
                boolean z2 = (v6 > 0 || z1) && v2 > 0 && (!this.mStrictWidthLimit || v8 > 0);
                boolean z3 = z2;
                v14 = v;
                if(z2) {
                    View view1 = this.getItemView(menuItemImpl1, null, viewGroup0);
                    if(this.mStrictWidthLimit) {
                        int v16 = ActionMenuView.measureChildForCells(view1, v9, v8, 0, 0);
                        v8 -= v16;
                        if(v16 == 0) {
                            z3 = false;
                        }
                    }
                    else {
                        view1.measure(0, 0);
                    }
                    int v17 = view1.getMeasuredWidth();
                    v2 -= v17;
                    if(v11 == 0) {
                        v11 = v17;
                    }
                    if(!this.mStrictWidthLimit) {
                        if(v2 + v11 > 0) {
                            z4 = true;
                        }
                    }
                    else if(v2 >= 0) {
                        z4 = true;
                    }
                    else {
                        z4 = false;
                    }
                    z2 = z3 & z4;
                }
                if(z2 && v15 != 0) {
                    sparseBooleanArray0.put(v15, true);
                }
                else if(z1) {
                    sparseBooleanArray0.put(v15, false);
                    for(int v18 = 0; v18 < v10; ++v18) {
                        MenuItemImpl menuItemImpl2 = (MenuItemImpl)arrayList0.get(v18);
                        if(menuItemImpl2.getGroupId() == v15) {
                            if(menuItemImpl2.isActionButton()) {
                                ++v6;
                            }
                            menuItemImpl2.setIsActionButton(false);
                        }
                    }
                }
                if(z2) {
                    --v6;
                }
                menuItemImpl1.setIsActionButton(z2);
            }
            else {
                v14 = v;
                menuItemImpl1.setIsActionButton(false);
            }
            ++v10;
            v = v14;
        }
        return true;
    }

    @Override  // androidx.appcompat.view.menu.BaseMenuPresenter
    public View getItemView(MenuItemImpl menuItemImpl0, View view0, ViewGroup viewGroup0) {
        View view1 = menuItemImpl0.getActionView();
        if(view1 == null || menuItemImpl0.hasCollapsibleActionView()) {
            view1 = super.getItemView(menuItemImpl0, view0, viewGroup0);
        }
        view1.setVisibility((menuItemImpl0.isActionViewExpanded() ? 8 : 0));
        ViewGroup.LayoutParams viewGroup$LayoutParams0 = view1.getLayoutParams();
        if(!((ActionMenuView)viewGroup0).checkLayoutParams(viewGroup$LayoutParams0)) {
            view1.setLayoutParams(((ActionMenuView)viewGroup0).generateLayoutParams(viewGroup$LayoutParams0));
        }
        return view1;
    }

    @Override  // androidx.appcompat.view.menu.BaseMenuPresenter
    public MenuView getMenuView(ViewGroup viewGroup0) {
        MenuView menuView0 = this.mMenuView;
        MenuView menuView1 = super.getMenuView(viewGroup0);
        if(menuView0 != menuView1) {
            ((ActionMenuView)menuView1).setPresenter(this);
        }
        return menuView1;
    }

    public Drawable getOverflowIcon() {
        OverflowMenuButton actionMenuPresenter$OverflowMenuButton0 = this.mOverflowButton;
        if(actionMenuPresenter$OverflowMenuButton0 != null) {
            return actionMenuPresenter$OverflowMenuButton0.getDrawable();
        }
        return this.mPendingOverflowIconSet ? this.mPendingOverflowIcon : null;
    }

    public boolean hideOverflowMenu() {
        if(this.mPostedOpenRunnable != null && this.mMenuView != null) {
            ((View)this.mMenuView).removeCallbacks(this.mPostedOpenRunnable);
            this.mPostedOpenRunnable = null;
            return true;
        }
        OverflowPopup actionMenuPresenter$OverflowPopup0 = this.mOverflowPopup;
        if(actionMenuPresenter$OverflowPopup0 != null) {
            actionMenuPresenter$OverflowPopup0.dismiss();
            return true;
        }
        return false;
    }

    public boolean hideSubMenus() {
        ActionButtonSubmenu actionMenuPresenter$ActionButtonSubmenu0 = this.mActionButtonPopup;
        if(actionMenuPresenter$ActionButtonSubmenu0 != null) {
            actionMenuPresenter$ActionButtonSubmenu0.dismiss();
            return true;
        }
        return false;
    }

    @Override  // androidx.appcompat.view.menu.BaseMenuPresenter
    public void initForMenu(Context context0, MenuBuilder menuBuilder0) {
        super.initForMenu(context0, menuBuilder0);
        Resources resources0 = context0.getResources();
        ActionBarPolicy actionBarPolicy0 = ActionBarPolicy.get(context0);
        if(!this.mReserveOverflowSet) {
            this.mReserveOverflow = true;
        }
        if(!this.mWidthLimitSet) {
            this.mWidthLimit = actionBarPolicy0.getEmbeddedMenuWidthLimit();
        }
        if(!this.mMaxItemsSet) {
            this.mMaxItems = actionBarPolicy0.getMaxActionButtons();
        }
        int v = this.mWidthLimit;
        if(this.mReserveOverflow) {
            if(this.mOverflowButton == null) {
                OverflowMenuButton actionMenuPresenter$OverflowMenuButton0 = new OverflowMenuButton(this, this.mSystemContext);
                this.mOverflowButton = actionMenuPresenter$OverflowMenuButton0;
                if(this.mPendingOverflowIconSet) {
                    actionMenuPresenter$OverflowMenuButton0.setImageDrawable(this.mPendingOverflowIcon);
                    this.mPendingOverflowIcon = null;
                    this.mPendingOverflowIconSet = false;
                }
                this.mOverflowButton.measure(0, 0);
            }
            v -= this.mOverflowButton.getMeasuredWidth();
        }
        else {
            this.mOverflowButton = null;
        }
        this.mActionItemWidthLimit = v;
        this.mMinCellSize = (int)(resources0.getDisplayMetrics().density * 56.0f);
    }

    public boolean isOverflowMenuShowPending() {
        return this.mPostedOpenRunnable != null || this.isOverflowMenuShowing();
    }

    public boolean isOverflowMenuShowing() {
        return this.mOverflowPopup != null && this.mOverflowPopup.isShowing();
    }

    public boolean isOverflowReserved() {
        return this.mReserveOverflow;
    }

    @Override  // androidx.appcompat.view.menu.BaseMenuPresenter
    public void onCloseMenu(MenuBuilder menuBuilder0, boolean z) {
        this.dismissPopupMenus();
        super.onCloseMenu(menuBuilder0, z);
    }

    public void onConfigurationChanged(Configuration configuration0) {
        if(!this.mMaxItemsSet) {
            this.mMaxItems = ActionBarPolicy.get(this.mContext).getMaxActionButtons();
        }
        if(this.mMenu != null) {
            this.mMenu.onItemsChanged(true);
        }
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public void onRestoreInstanceState(Parcelable parcelable0) {
        if(parcelable0 instanceof SavedState && ((SavedState)parcelable0).openSubMenuId > 0) {
            MenuItem menuItem0 = this.mMenu.findItem(((SavedState)parcelable0).openSubMenuId);
            if(menuItem0 != null) {
                this.onSubMenuSelected(((SubMenuBuilder)menuItem0.getSubMenu()));
            }
        }
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public Parcelable onSaveInstanceState() {
        Parcelable parcelable0 = new SavedState();
        parcelable0.openSubMenuId = this.mOpenSubMenuId;
        return parcelable0;
    }

    @Override  // androidx.appcompat.view.menu.BaseMenuPresenter
    public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder0) {
        boolean z = false;
        if(!subMenuBuilder0.hasVisibleItems()) {
            return false;
        }
        SubMenuBuilder subMenuBuilder1;
        for(subMenuBuilder1 = subMenuBuilder0; subMenuBuilder1.getParentMenu() != this.mMenu; subMenuBuilder1 = (SubMenuBuilder)subMenuBuilder1.getParentMenu()) {
        }
        View view0 = this.findViewForItem(subMenuBuilder1.getItem());
        if(view0 == null) {
            return false;
        }
        this.mOpenSubMenuId = subMenuBuilder0.getItem().getItemId();
        int v = subMenuBuilder0.size();
        for(int v1 = 0; v1 < v; ++v1) {
            MenuItem menuItem0 = subMenuBuilder0.getItem(v1);
            if(menuItem0.isVisible() && menuItem0.getIcon() != null) {
                z = true;
                break;
            }
        }
        ActionButtonSubmenu actionMenuPresenter$ActionButtonSubmenu0 = new ActionButtonSubmenu(this, this.mContext, subMenuBuilder0, view0);
        this.mActionButtonPopup = actionMenuPresenter$ActionButtonSubmenu0;
        actionMenuPresenter$ActionButtonSubmenu0.setForceShowIcon(z);
        this.mActionButtonPopup.show();
        super.onSubMenuSelected(subMenuBuilder0);
        return true;
    }

    @Override  // androidx.core.view.ActionProvider$SubUiVisibilityListener
    public void onSubUiVisibilityChanged(boolean z) {
        if(z) {
            super.onSubMenuSelected(null);
            return;
        }
        if(this.mMenu != null) {
            this.mMenu.close(false);
        }
    }

    public void setExpandedActionViewsExclusive(boolean z) {
        this.mExpandedActionViewsExclusive = z;
    }

    public void setItemLimit(int v) {
        this.mMaxItems = v;
        this.mMaxItemsSet = true;
    }

    public void setMenuView(ActionMenuView actionMenuView0) {
        this.mMenuView = actionMenuView0;
        actionMenuView0.initialize(this.mMenu);
    }

    public void setOverflowIcon(Drawable drawable0) {
        OverflowMenuButton actionMenuPresenter$OverflowMenuButton0 = this.mOverflowButton;
        if(actionMenuPresenter$OverflowMenuButton0 != null) {
            actionMenuPresenter$OverflowMenuButton0.setImageDrawable(drawable0);
            return;
        }
        this.mPendingOverflowIconSet = true;
        this.mPendingOverflowIcon = drawable0;
    }

    public void setReserveOverflow(boolean z) {
        this.mReserveOverflow = z;
        this.mReserveOverflowSet = true;
    }

    public void setWidthLimit(int v, boolean z) {
        this.mWidthLimit = v;
        this.mStrictWidthLimit = z;
        this.mWidthLimitSet = true;
    }

    @Override  // androidx.appcompat.view.menu.BaseMenuPresenter
    public boolean shouldIncludeItem(int v, MenuItemImpl menuItemImpl0) {
        return menuItemImpl0.isActionButton();
    }

    public boolean showOverflowMenu() {
        if(this.mReserveOverflow && !this.isOverflowMenuShowing() && this.mMenu != null && this.mMenuView != null && this.mPostedOpenRunnable == null && !this.mMenu.getNonActionItems().isEmpty()) {
            this.mPostedOpenRunnable = new OpenOverflowRunnable(this, new OverflowPopup(this, this.mContext, this.mMenu, this.mOverflowButton, true));
            ((View)this.mMenuView).post(this.mPostedOpenRunnable);
            return true;
        }
        return false;
    }

    @Override  // androidx.appcompat.view.menu.BaseMenuPresenter
    public void updateMenuView(boolean z) {
        super.updateMenuView(z);
        ((View)this.mMenuView).requestLayout();
        int v = 0;
        if(this.mMenu != null) {
            ArrayList arrayList0 = this.mMenu.getActionItems();
            int v1 = arrayList0.size();
            for(int v2 = 0; v2 < v1; ++v2) {
                ActionProvider actionProvider0 = ((MenuItemImpl)arrayList0.get(v2)).getSupportActionProvider();
                if(actionProvider0 != null) {
                    actionProvider0.setSubUiVisibilityListener(this);
                }
            }
        }
        ArrayList arrayList1 = this.mMenu == null ? null : this.mMenu.getNonActionItems();
        if(this.mReserveOverflow && arrayList1 != null) {
            int v3 = arrayList1.size();
            if(v3 == 1) {
                v = !((MenuItemImpl)arrayList1.get(0)).isActionViewExpanded();
            }
            else if(v3 > 0) {
                v = 1;
            }
        }
        if(v != 0) {
            if(this.mOverflowButton == null) {
                this.mOverflowButton = new OverflowMenuButton(this, this.mSystemContext);
            }
            ViewGroup viewGroup0 = (ViewGroup)this.mOverflowButton.getParent();
            if(viewGroup0 != this.mMenuView) {
                if(viewGroup0 != null) {
                    viewGroup0.removeView(this.mOverflowButton);
                }
                ActionMenuView actionMenuView0 = (ActionMenuView)this.mMenuView;
                actionMenuView0.addView(this.mOverflowButton, actionMenuView0.generateOverflowButtonLayoutParams());
            }
        }
        else if(this.mOverflowButton != null && this.mOverflowButton.getParent() == this.mMenuView) {
            ((ViewGroup)this.mMenuView).removeView(this.mOverflowButton);
        }
        ((ActionMenuView)this.mMenuView).setOverflowReserved(this.mReserveOverflow);
    }
}

