package com.google.android.material.navigation;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuView;
import androidx.core.util.Pools.Pool;
import androidx.core.util.Pools.SynchronizedPool;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat.CollectionInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;
import androidx.transition.TransitionSet;
import com.google.android.material.R.attr;
import com.google.android.material.R.integer;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.internal.TextScale;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import java.util.HashSet;

public abstract class NavigationBarMenuView extends ViewGroup implements MenuView {
    private static final int[] CHECKED_STATE_SET = null;
    private static final int[] DISABLED_STATE_SET = null;
    private static final int ITEM_POOL_SIZE = 5;
    private static final int NO_PADDING = -1;
    private final SparseArray badgeDrawables;
    private NavigationBarItemView[] buttons;
    private ColorStateList itemActiveIndicatorColor;
    private boolean itemActiveIndicatorEnabled;
    private int itemActiveIndicatorHeight;
    private int itemActiveIndicatorLabelPadding;
    private int itemActiveIndicatorMarginHorizontal;
    private boolean itemActiveIndicatorResizeable;
    private ShapeAppearanceModel itemActiveIndicatorShapeAppearance;
    private int itemActiveIndicatorWidth;
    private Drawable itemBackground;
    private int itemBackgroundRes;
    private int itemIconSize;
    private ColorStateList itemIconTint;
    private int itemPaddingBottom;
    private int itemPaddingTop;
    private final Pool itemPool;
    private ColorStateList itemRippleColor;
    private int itemTextAppearanceActive;
    private boolean itemTextAppearanceActiveBoldEnabled;
    private int itemTextAppearanceInactive;
    private final ColorStateList itemTextColorDefault;
    private ColorStateList itemTextColorFromUser;
    private int labelVisibilityMode;
    private MenuBuilder menu;
    private final View.OnClickListener onClickListener;
    private final SparseArray onTouchListeners;
    private NavigationBarPresenter presenter;
    private int selectedItemId;
    private int selectedItemPosition;
    private final TransitionSet set;

    static {
        NavigationBarMenuView.CHECKED_STATE_SET = new int[]{0x10100A0};
        NavigationBarMenuView.DISABLED_STATE_SET = new int[]{0xFEFEFF62};
    }

    public NavigationBarMenuView(Context context0) {
        super(context0);
        this.itemPool = new SynchronizedPool(5);
        this.onTouchListeners = new SparseArray(5);
        this.selectedItemId = 0;
        this.selectedItemPosition = 0;
        this.badgeDrawables = new SparseArray(5);
        this.itemPaddingTop = -1;
        this.itemPaddingBottom = -1;
        this.itemActiveIndicatorLabelPadding = -1;
        this.itemActiveIndicatorResizeable = false;
        this.itemTextColorDefault = this.createDefaultColorStateList(0x1010038);
        if(this.isInEditMode()) {
            this.set = null;
        }
        else {
            AutoTransition autoTransition0 = new AutoTransition();
            this.set = autoTransition0;
            autoTransition0.setOrdering(0);
            Context context1 = this.getContext();
            int v = this.getResources().getInteger(integer.material_motion_duration_long_1);
            autoTransition0.setDuration(((long)MotionUtils.resolveThemeDuration(context1, attr.motionDurationMedium4, v)));
            autoTransition0.setInterpolator(MotionUtils.resolveThemeInterpolator(this.getContext(), attr.motionEasingStandard, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
            autoTransition0.addTransition(new TextScale());
        }
        this.onClickListener = new View.OnClickListener() {
            @Override  // android.view.View$OnClickListener
            public void onClick(View view0) {
                MenuItemImpl menuItemImpl0 = ((NavigationBarItemView)view0).getItemData();
                if(!NavigationBarMenuView.this.menu.performItemAction(menuItemImpl0, NavigationBarMenuView.this.presenter, 0)) {
                    menuItemImpl0.setChecked(true);
                }
            }
        };
        ViewCompat.setImportantForAccessibility(this, 1);
    }

    public void buildMenuView() {
        this.removeAllViews();
        NavigationBarItemView[] arr_navigationBarItemView = this.buttons;
        if(arr_navigationBarItemView != null) {
            for(int v = 0; v < arr_navigationBarItemView.length; ++v) {
                NavigationBarItemView navigationBarItemView0 = arr_navigationBarItemView[v];
                if(navigationBarItemView0 != null) {
                    this.itemPool.release(navigationBarItemView0);
                    navigationBarItemView0.clear();
                }
            }
        }
        if(this.menu.size() == 0) {
            this.selectedItemId = 0;
            this.selectedItemPosition = 0;
            this.buttons = null;
            return;
        }
        this.removeUnusedBadges();
        this.buttons = new NavigationBarItemView[this.menu.size()];
        boolean z = this.isShifting(this.labelVisibilityMode, this.menu.getVisibleItems().size());
        for(int v1 = 0; v1 < this.menu.size(); ++v1) {
            this.presenter.setUpdateSuspended(true);
            this.menu.getItem(v1).setCheckable(true);
            this.presenter.setUpdateSuspended(false);
            NavigationBarItemView navigationBarItemView1 = this.getNewItem();
            this.buttons[v1] = navigationBarItemView1;
            navigationBarItemView1.setIconTintList(this.itemIconTint);
            navigationBarItemView1.setIconSize(this.itemIconSize);
            navigationBarItemView1.setTextColor(this.itemTextColorDefault);
            navigationBarItemView1.setTextAppearanceInactive(this.itemTextAppearanceInactive);
            navigationBarItemView1.setTextAppearanceActive(this.itemTextAppearanceActive);
            navigationBarItemView1.setTextAppearanceActiveBoldEnabled(this.itemTextAppearanceActiveBoldEnabled);
            navigationBarItemView1.setTextColor(this.itemTextColorFromUser);
            int v2 = this.itemPaddingTop;
            if(v2 != -1) {
                navigationBarItemView1.setItemPaddingTop(v2);
            }
            int v3 = this.itemPaddingBottom;
            if(v3 != -1) {
                navigationBarItemView1.setItemPaddingBottom(v3);
            }
            int v4 = this.itemActiveIndicatorLabelPadding;
            if(v4 != -1) {
                navigationBarItemView1.setActiveIndicatorLabelPadding(v4);
            }
            navigationBarItemView1.setActiveIndicatorWidth(this.itemActiveIndicatorWidth);
            navigationBarItemView1.setActiveIndicatorHeight(this.itemActiveIndicatorHeight);
            navigationBarItemView1.setActiveIndicatorMarginHorizontal(this.itemActiveIndicatorMarginHorizontal);
            navigationBarItemView1.setActiveIndicatorDrawable(this.createItemActiveIndicatorDrawable());
            navigationBarItemView1.setActiveIndicatorResizeable(this.itemActiveIndicatorResizeable);
            navigationBarItemView1.setActiveIndicatorEnabled(this.itemActiveIndicatorEnabled);
            Drawable drawable0 = this.itemBackground;
            if(drawable0 == null) {
                navigationBarItemView1.setItemBackground(this.itemBackgroundRes);
            }
            else {
                navigationBarItemView1.setItemBackground(drawable0);
            }
            navigationBarItemView1.setItemRippleColor(this.itemRippleColor);
            navigationBarItemView1.setShifting(z);
            navigationBarItemView1.setLabelVisibilityMode(this.labelVisibilityMode);
            MenuItemImpl menuItemImpl0 = (MenuItemImpl)this.menu.getItem(v1);
            navigationBarItemView1.initialize(menuItemImpl0, 0);
            navigationBarItemView1.setItemPosition(v1);
            int v5 = menuItemImpl0.getItemId();
            navigationBarItemView1.setOnTouchListener(((View.OnTouchListener)this.onTouchListeners.get(v5)));
            navigationBarItemView1.setOnClickListener(this.onClickListener);
            if(this.selectedItemId != 0 && v5 == this.selectedItemId) {
                this.selectedItemPosition = v1;
            }
            this.setBadgeIfNeeded(navigationBarItemView1);
            this.addView(navigationBarItemView1);
        }
        int v6 = Math.min(this.menu.size() - 1, this.selectedItemPosition);
        this.selectedItemPosition = v6;
        this.menu.getItem(v6).setChecked(true);
    }

    public ColorStateList createDefaultColorStateList(int v) {
        TypedValue typedValue0 = new TypedValue();
        if(!this.getContext().getTheme().resolveAttribute(v, typedValue0, true)) {
            return null;
        }
        ColorStateList colorStateList0 = AppCompatResources.getColorStateList(this.getContext(), typedValue0.resourceId);
        if(!this.getContext().getTheme().resolveAttribute(androidx.appcompat.R.attr.colorPrimary, typedValue0, true)) {
            return null;
        }
        int v1 = typedValue0.data;
        int v2 = colorStateList0.getDefaultColor();
        return new ColorStateList(new int[][]{NavigationBarMenuView.DISABLED_STATE_SET, NavigationBarMenuView.CHECKED_STATE_SET, NavigationBarMenuView.EMPTY_STATE_SET}, new int[]{colorStateList0.getColorForState(NavigationBarMenuView.DISABLED_STATE_SET, v2), v1, v2});
    }

    private Drawable createItemActiveIndicatorDrawable() {
        if(this.itemActiveIndicatorShapeAppearance != null && this.itemActiveIndicatorColor != null) {
            Drawable drawable0 = new MaterialShapeDrawable(this.itemActiveIndicatorShapeAppearance);
            ((MaterialShapeDrawable)drawable0).setFillColor(this.itemActiveIndicatorColor);
            return drawable0;
        }
        return null;
    }

    protected abstract NavigationBarItemView createNavigationBarItemView(Context arg1);

    public NavigationBarItemView findItemView(int v) {
        this.validateMenuItemId(v);
        NavigationBarItemView[] arr_navigationBarItemView = this.buttons;
        if(arr_navigationBarItemView != null) {
            for(int v1 = 0; v1 < arr_navigationBarItemView.length; ++v1) {
                NavigationBarItemView navigationBarItemView0 = arr_navigationBarItemView[v1];
                if(navigationBarItemView0.getId() == v) {
                    return navigationBarItemView0;
                }
            }
        }
        return null;
    }

    public int getActiveIndicatorLabelPadding() {
        return this.itemActiveIndicatorLabelPadding;
    }

    public BadgeDrawable getBadge(int v) {
        return (BadgeDrawable)this.badgeDrawables.get(v);
    }

    SparseArray getBadgeDrawables() {
        return this.badgeDrawables;
    }

    public ColorStateList getIconTintList() {
        return this.itemIconTint;
    }

    public ColorStateList getItemActiveIndicatorColor() {
        return this.itemActiveIndicatorColor;
    }

    public boolean getItemActiveIndicatorEnabled() {
        return this.itemActiveIndicatorEnabled;
    }

    public int getItemActiveIndicatorHeight() {
        return this.itemActiveIndicatorHeight;
    }

    public int getItemActiveIndicatorMarginHorizontal() {
        return this.itemActiveIndicatorMarginHorizontal;
    }

    public ShapeAppearanceModel getItemActiveIndicatorShapeAppearance() {
        return this.itemActiveIndicatorShapeAppearance;
    }

    public int getItemActiveIndicatorWidth() {
        return this.itemActiveIndicatorWidth;
    }

    public Drawable getItemBackground() {
        return this.buttons == null || this.buttons.length <= 0 ? this.itemBackground : this.buttons[0].getBackground();
    }

    @Deprecated
    public int getItemBackgroundRes() {
        return this.itemBackgroundRes;
    }

    public int getItemIconSize() {
        return this.itemIconSize;
    }

    public int getItemPaddingBottom() {
        return this.itemPaddingBottom;
    }

    public int getItemPaddingTop() {
        return this.itemPaddingTop;
    }

    public ColorStateList getItemRippleColor() {
        return this.itemRippleColor;
    }

    public int getItemTextAppearanceActive() {
        return this.itemTextAppearanceActive;
    }

    public int getItemTextAppearanceInactive() {
        return this.itemTextAppearanceInactive;
    }

    public ColorStateList getItemTextColor() {
        return this.itemTextColorFromUser;
    }

    public int getLabelVisibilityMode() {
        return this.labelVisibilityMode;
    }

    protected MenuBuilder getMenu() {
        return this.menu;
    }

    private NavigationBarItemView getNewItem() {
        NavigationBarItemView navigationBarItemView0 = (NavigationBarItemView)this.itemPool.acquire();
        return navigationBarItemView0 == null ? this.createNavigationBarItemView(this.getContext()) : navigationBarItemView0;
    }

    BadgeDrawable getOrCreateBadge(int v) {
        this.validateMenuItemId(v);
        BadgeDrawable badgeDrawable0 = (BadgeDrawable)this.badgeDrawables.get(v);
        if(badgeDrawable0 == null) {
            badgeDrawable0 = BadgeDrawable.create(this.getContext());
            this.badgeDrawables.put(v, badgeDrawable0);
        }
        NavigationBarItemView navigationBarItemView0 = this.findItemView(v);
        if(navigationBarItemView0 != null) {
            navigationBarItemView0.setBadge(badgeDrawable0);
        }
        return badgeDrawable0;
    }

    public int getSelectedItemId() {
        return this.selectedItemId;
    }

    protected int getSelectedItemPosition() {
        return this.selectedItemPosition;
    }

    @Override  // androidx.appcompat.view.menu.MenuView
    public int getWindowAnimations() {
        return 0;
    }

    @Override  // androidx.appcompat.view.menu.MenuView
    public void initialize(MenuBuilder menuBuilder0) {
        this.menu = menuBuilder0;
    }

    protected boolean isItemActiveIndicatorResizeable() {
        return this.itemActiveIndicatorResizeable;
    }

    protected boolean isShifting(int v, int v1) {
        switch(v) {
            case -1: {
                return v1 > 3;
            }
            case 0: {
                return true;
            }
            default: {
                return false;
            }
        }
    }

    private boolean isValidId(int v) {
        return v != -1;
    }

    @Override  // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo0) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo0);
        AccessibilityNodeInfoCompat.wrap(accessibilityNodeInfo0).setCollectionInfo(CollectionInfoCompat.obtain(1, this.menu.getVisibleItems().size(), false, 1));
    }

    void removeBadge(int v) {
        this.validateMenuItemId(v);
        NavigationBarItemView navigationBarItemView0 = this.findItemView(v);
        if(navigationBarItemView0 != null) {
            navigationBarItemView0.removeBadge();
        }
        this.badgeDrawables.put(v, null);
    }

    private void removeUnusedBadges() {
        HashSet hashSet0 = new HashSet();
        for(int v1 = 0; v1 < this.menu.size(); ++v1) {
            hashSet0.add(this.menu.getItem(v1).getItemId());
        }
        for(int v = 0; v < this.badgeDrawables.size(); ++v) {
            int v2 = this.badgeDrawables.keyAt(v);
            if(!hashSet0.contains(v2)) {
                this.badgeDrawables.delete(v2);
            }
        }
    }

    void restoreBadgeDrawables(SparseArray sparseArray0) {
        for(int v1 = 0; v1 < sparseArray0.size(); ++v1) {
            int v2 = sparseArray0.keyAt(v1);
            if(this.badgeDrawables.indexOfKey(v2) < 0) {
                BadgeDrawable badgeDrawable0 = (BadgeDrawable)sparseArray0.get(v2);
                this.badgeDrawables.append(v2, badgeDrawable0);
            }
        }
        NavigationBarItemView[] arr_navigationBarItemView = this.buttons;
        if(arr_navigationBarItemView != null) {
            for(int v = 0; v < arr_navigationBarItemView.length; ++v) {
                NavigationBarItemView navigationBarItemView0 = arr_navigationBarItemView[v];
                int v3 = navigationBarItemView0.getId();
                BadgeDrawable badgeDrawable1 = (BadgeDrawable)this.badgeDrawables.get(v3);
                if(badgeDrawable1 != null) {
                    navigationBarItemView0.setBadge(badgeDrawable1);
                }
            }
        }
    }

    public void setActiveIndicatorLabelPadding(int v) {
        this.itemActiveIndicatorLabelPadding = v;
        NavigationBarItemView[] arr_navigationBarItemView = this.buttons;
        if(arr_navigationBarItemView != null) {
            for(int v1 = 0; v1 < arr_navigationBarItemView.length; ++v1) {
                arr_navigationBarItemView[v1].setActiveIndicatorLabelPadding(v);
            }
        }
    }

    private void setBadgeIfNeeded(NavigationBarItemView navigationBarItemView0) {
        int v = navigationBarItemView0.getId();
        if(this.isValidId(v)) {
            BadgeDrawable badgeDrawable0 = (BadgeDrawable)this.badgeDrawables.get(v);
            if(badgeDrawable0 != null) {
                navigationBarItemView0.setBadge(badgeDrawable0);
            }
        }
    }

    public void setIconTintList(ColorStateList colorStateList0) {
        this.itemIconTint = colorStateList0;
        NavigationBarItemView[] arr_navigationBarItemView = this.buttons;
        if(arr_navigationBarItemView != null) {
            for(int v = 0; v < arr_navigationBarItemView.length; ++v) {
                arr_navigationBarItemView[v].setIconTintList(colorStateList0);
            }
        }
    }

    public void setItemActiveIndicatorColor(ColorStateList colorStateList0) {
        this.itemActiveIndicatorColor = colorStateList0;
        NavigationBarItemView[] arr_navigationBarItemView = this.buttons;
        if(arr_navigationBarItemView != null) {
            for(int v = 0; v < arr_navigationBarItemView.length; ++v) {
                arr_navigationBarItemView[v].setActiveIndicatorDrawable(this.createItemActiveIndicatorDrawable());
            }
        }
    }

    public void setItemActiveIndicatorEnabled(boolean z) {
        this.itemActiveIndicatorEnabled = z;
        NavigationBarItemView[] arr_navigationBarItemView = this.buttons;
        if(arr_navigationBarItemView != null) {
            for(int v = 0; v < arr_navigationBarItemView.length; ++v) {
                arr_navigationBarItemView[v].setActiveIndicatorEnabled(z);
            }
        }
    }

    public void setItemActiveIndicatorHeight(int v) {
        this.itemActiveIndicatorHeight = v;
        NavigationBarItemView[] arr_navigationBarItemView = this.buttons;
        if(arr_navigationBarItemView != null) {
            for(int v1 = 0; v1 < arr_navigationBarItemView.length; ++v1) {
                arr_navigationBarItemView[v1].setActiveIndicatorHeight(v);
            }
        }
    }

    public void setItemActiveIndicatorMarginHorizontal(int v) {
        this.itemActiveIndicatorMarginHorizontal = v;
        NavigationBarItemView[] arr_navigationBarItemView = this.buttons;
        if(arr_navigationBarItemView != null) {
            for(int v1 = 0; v1 < arr_navigationBarItemView.length; ++v1) {
                arr_navigationBarItemView[v1].setActiveIndicatorMarginHorizontal(v);
            }
        }
    }

    protected void setItemActiveIndicatorResizeable(boolean z) {
        this.itemActiveIndicatorResizeable = z;
        NavigationBarItemView[] arr_navigationBarItemView = this.buttons;
        if(arr_navigationBarItemView != null) {
            for(int v = 0; v < arr_navigationBarItemView.length; ++v) {
                arr_navigationBarItemView[v].setActiveIndicatorResizeable(z);
            }
        }
    }

    public void setItemActiveIndicatorShapeAppearance(ShapeAppearanceModel shapeAppearanceModel0) {
        this.itemActiveIndicatorShapeAppearance = shapeAppearanceModel0;
        NavigationBarItemView[] arr_navigationBarItemView = this.buttons;
        if(arr_navigationBarItemView != null) {
            for(int v = 0; v < arr_navigationBarItemView.length; ++v) {
                arr_navigationBarItemView[v].setActiveIndicatorDrawable(this.createItemActiveIndicatorDrawable());
            }
        }
    }

    public void setItemActiveIndicatorWidth(int v) {
        this.itemActiveIndicatorWidth = v;
        NavigationBarItemView[] arr_navigationBarItemView = this.buttons;
        if(arr_navigationBarItemView != null) {
            for(int v1 = 0; v1 < arr_navigationBarItemView.length; ++v1) {
                arr_navigationBarItemView[v1].setActiveIndicatorWidth(v);
            }
        }
    }

    public void setItemBackground(Drawable drawable0) {
        this.itemBackground = drawable0;
        NavigationBarItemView[] arr_navigationBarItemView = this.buttons;
        if(arr_navigationBarItemView != null) {
            for(int v = 0; v < arr_navigationBarItemView.length; ++v) {
                arr_navigationBarItemView[v].setItemBackground(drawable0);
            }
        }
    }

    public void setItemBackgroundRes(int v) {
        this.itemBackgroundRes = v;
        NavigationBarItemView[] arr_navigationBarItemView = this.buttons;
        if(arr_navigationBarItemView != null) {
            for(int v1 = 0; v1 < arr_navigationBarItemView.length; ++v1) {
                arr_navigationBarItemView[v1].setItemBackground(v);
            }
        }
    }

    public void setItemIconSize(int v) {
        this.itemIconSize = v;
        NavigationBarItemView[] arr_navigationBarItemView = this.buttons;
        if(arr_navigationBarItemView != null) {
            for(int v1 = 0; v1 < arr_navigationBarItemView.length; ++v1) {
                arr_navigationBarItemView[v1].setIconSize(v);
            }
        }
    }

    public void setItemOnTouchListener(int v, View.OnTouchListener view$OnTouchListener0) {
        if(view$OnTouchListener0 == null) {
            this.onTouchListeners.remove(v);
        }
        else {
            this.onTouchListeners.put(v, view$OnTouchListener0);
        }
        NavigationBarItemView[] arr_navigationBarItemView = this.buttons;
        if(arr_navigationBarItemView != null) {
            for(int v1 = 0; v1 < arr_navigationBarItemView.length; ++v1) {
                NavigationBarItemView navigationBarItemView0 = arr_navigationBarItemView[v1];
                if(navigationBarItemView0.getItemData().getItemId() == v) {
                    navigationBarItemView0.setOnTouchListener(view$OnTouchListener0);
                }
            }
        }
    }

    public void setItemPaddingBottom(int v) {
        this.itemPaddingBottom = v;
        NavigationBarItemView[] arr_navigationBarItemView = this.buttons;
        if(arr_navigationBarItemView != null) {
            for(int v1 = 0; v1 < arr_navigationBarItemView.length; ++v1) {
                arr_navigationBarItemView[v1].setItemPaddingBottom(v);
            }
        }
    }

    public void setItemPaddingTop(int v) {
        this.itemPaddingTop = v;
        NavigationBarItemView[] arr_navigationBarItemView = this.buttons;
        if(arr_navigationBarItemView != null) {
            for(int v1 = 0; v1 < arr_navigationBarItemView.length; ++v1) {
                arr_navigationBarItemView[v1].setItemPaddingTop(v);
            }
        }
    }

    public void setItemRippleColor(ColorStateList colorStateList0) {
        this.itemRippleColor = colorStateList0;
        NavigationBarItemView[] arr_navigationBarItemView = this.buttons;
        if(arr_navigationBarItemView != null) {
            for(int v = 0; v < arr_navigationBarItemView.length; ++v) {
                arr_navigationBarItemView[v].setItemRippleColor(colorStateList0);
            }
        }
    }

    public void setItemTextAppearanceActive(int v) {
        this.itemTextAppearanceActive = v;
        NavigationBarItemView[] arr_navigationBarItemView = this.buttons;
        if(arr_navigationBarItemView != null) {
            for(int v1 = 0; v1 < arr_navigationBarItemView.length; ++v1) {
                NavigationBarItemView navigationBarItemView0 = arr_navigationBarItemView[v1];
                navigationBarItemView0.setTextAppearanceActive(v);
                ColorStateList colorStateList0 = this.itemTextColorFromUser;
                if(colorStateList0 != null) {
                    navigationBarItemView0.setTextColor(colorStateList0);
                }
            }
        }
    }

    public void setItemTextAppearanceActiveBoldEnabled(boolean z) {
        this.itemTextAppearanceActiveBoldEnabled = z;
        NavigationBarItemView[] arr_navigationBarItemView = this.buttons;
        if(arr_navigationBarItemView != null) {
            for(int v = 0; v < arr_navigationBarItemView.length; ++v) {
                arr_navigationBarItemView[v].setTextAppearanceActiveBoldEnabled(z);
            }
        }
    }

    public void setItemTextAppearanceInactive(int v) {
        this.itemTextAppearanceInactive = v;
        NavigationBarItemView[] arr_navigationBarItemView = this.buttons;
        if(arr_navigationBarItemView != null) {
            for(int v1 = 0; v1 < arr_navigationBarItemView.length; ++v1) {
                NavigationBarItemView navigationBarItemView0 = arr_navigationBarItemView[v1];
                navigationBarItemView0.setTextAppearanceInactive(v);
                ColorStateList colorStateList0 = this.itemTextColorFromUser;
                if(colorStateList0 != null) {
                    navigationBarItemView0.setTextColor(colorStateList0);
                }
            }
        }
    }

    public void setItemTextColor(ColorStateList colorStateList0) {
        this.itemTextColorFromUser = colorStateList0;
        NavigationBarItemView[] arr_navigationBarItemView = this.buttons;
        if(arr_navigationBarItemView != null) {
            for(int v = 0; v < arr_navigationBarItemView.length; ++v) {
                arr_navigationBarItemView[v].setTextColor(colorStateList0);
            }
        }
    }

    public void setLabelVisibilityMode(int v) {
        this.labelVisibilityMode = v;
    }

    public void setPresenter(NavigationBarPresenter navigationBarPresenter0) {
        this.presenter = navigationBarPresenter0;
    }

    void tryRestoreSelectedItemId(int v) {
        int v1 = this.menu.size();
        for(int v2 = 0; v2 < v1; ++v2) {
            MenuItem menuItem0 = this.menu.getItem(v2);
            if(v == menuItem0.getItemId()) {
                this.selectedItemId = v;
                this.selectedItemPosition = v2;
                menuItem0.setChecked(true);
                return;
            }
        }
    }

    public void updateMenuView() {
        MenuBuilder menuBuilder0 = this.menu;
        if(menuBuilder0 != null && this.buttons != null) {
            int v = menuBuilder0.size();
            if(v != this.buttons.length) {
                this.buildMenuView();
                return;
            }
            int v1 = this.selectedItemId;
            for(int v2 = 0; v2 < v; ++v2) {
                MenuItem menuItem0 = this.menu.getItem(v2);
                if(menuItem0.isChecked()) {
                    this.selectedItemId = menuItem0.getItemId();
                    this.selectedItemPosition = v2;
                }
            }
            if(v1 != this.selectedItemId) {
                TransitionSet transitionSet0 = this.set;
                if(transitionSet0 != null) {
                    TransitionManager.beginDelayedTransition(this, transitionSet0);
                }
            }
            boolean z = this.isShifting(this.labelVisibilityMode, this.menu.getVisibleItems().size());
            for(int v3 = 0; v3 < v; ++v3) {
                this.presenter.setUpdateSuspended(true);
                this.buttons[v3].setLabelVisibilityMode(this.labelVisibilityMode);
                this.buttons[v3].setShifting(z);
                this.buttons[v3].initialize(((MenuItemImpl)this.menu.getItem(v3)), 0);
                this.presenter.setUpdateSuspended(false);
            }
        }
    }

    private void validateMenuItemId(int v) {
        if(!this.isValidId(v)) {
            throw new IllegalArgumentException(v + " is not a valid view id");
        }
    }
}

