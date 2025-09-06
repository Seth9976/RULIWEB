package com.google.android.material.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.SubMenu;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuPresenter.Callback;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.view.menu.SubMenuBuilder;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat.CollectionInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat.CollectionItemInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.widget.TextViewCompat;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate;
import com.google.android.material.R.dimen;
import com.google.android.material.R.layout;
import java.util.ArrayList;

public class NavigationMenuPresenter implements MenuPresenter {
    static class HeaderViewHolder extends ViewHolder {
        public HeaderViewHolder(View view0) {
            super(view0);
        }
    }

    class NavigationMenuAdapter extends Adapter {
        private static final String STATE_ACTION_VIEWS = "android:menu:action_views";
        private static final String STATE_CHECKED_ITEM = "android:menu:checked";
        private static final int VIEW_TYPE_HEADER = 3;
        private static final int VIEW_TYPE_NORMAL = 0;
        private static final int VIEW_TYPE_SEPARATOR = 2;
        private static final int VIEW_TYPE_SUBHEADER = 1;
        private MenuItemImpl checkedItem;
        private final ArrayList items;
        private boolean updateSuspended;

        NavigationMenuAdapter() {
            this.items = new ArrayList();
            this.prepareMenuItems();
        }

        private int adjustItemPositionForA11yDelegate(int v) {
            int v2 = v;
            for(int v1 = 0; v1 < v; ++v1) {
                switch(NavigationMenuPresenter.this.adapter.getItemViewType(v1)) {
                    case 2: 
                    case 3: {
                        --v2;
                    }
                }
            }
            return v2;
        }

        private void appendTransparentIconIfMissing(int v, int v1) {
            while(v < v1) {
                ((NavigationMenuTextItem)this.items.get(v)).needsEmptyIcon = true;
                ++v;
            }
        }

        public Bundle createInstanceState() {
            Bundle bundle0 = new Bundle();
            MenuItemImpl menuItemImpl0 = this.checkedItem;
            if(menuItemImpl0 != null) {
                bundle0.putInt("android:menu:checked", menuItemImpl0.getItemId());
            }
            SparseArray sparseArray0 = new SparseArray();
            int v = this.items.size();
            for(int v1 = 0; v1 < v; ++v1) {
                NavigationMenuItem navigationMenuPresenter$NavigationMenuItem0 = (NavigationMenuItem)this.items.get(v1);
                if(navigationMenuPresenter$NavigationMenuItem0 instanceof NavigationMenuTextItem) {
                    MenuItemImpl menuItemImpl1 = ((NavigationMenuTextItem)navigationMenuPresenter$NavigationMenuItem0).getMenuItem();
                    View view0 = menuItemImpl1 == null ? null : menuItemImpl1.getActionView();
                    if(view0 != null) {
                        ParcelableSparseArray parcelableSparseArray0 = new ParcelableSparseArray();
                        view0.saveHierarchyState(parcelableSparseArray0);
                        sparseArray0.put(menuItemImpl1.getItemId(), parcelableSparseArray0);
                    }
                }
            }
            bundle0.putSparseParcelableArray("android:menu:action_views", sparseArray0);
            return bundle0;
        }

        public MenuItemImpl getCheckedItem() {
            return this.checkedItem;
        }

        @Override  // androidx.recyclerview.widget.RecyclerView$Adapter
        public int getItemCount() {
            return this.items.size();
        }

        @Override  // androidx.recyclerview.widget.RecyclerView$Adapter
        public long getItemId(int v) {
            return (long)v;
        }

        @Override  // androidx.recyclerview.widget.RecyclerView$Adapter
        public int getItemViewType(int v) {
            NavigationMenuItem navigationMenuPresenter$NavigationMenuItem0 = (NavigationMenuItem)this.items.get(v);
            if(navigationMenuPresenter$NavigationMenuItem0 instanceof NavigationMenuSeparatorItem) {
                return 2;
            }
            if(navigationMenuPresenter$NavigationMenuItem0 instanceof NavigationMenuHeaderItem) {
                return 3;
            }
            if(!(navigationMenuPresenter$NavigationMenuItem0 instanceof NavigationMenuTextItem)) {
                throw new RuntimeException("Unknown item type.");
            }
            return ((NavigationMenuTextItem)navigationMenuPresenter$NavigationMenuItem0).getMenuItem().hasSubMenu() ? 1 : 0;
        }

        int getRowCount() {
            int v1 = 0;
            for(int v = 0; v < NavigationMenuPresenter.this.adapter.getItemCount(); ++v) {
                switch(NavigationMenuPresenter.this.adapter.getItemViewType(v)) {
                    case 0: 
                    case 1: {
                        ++v1;
                    }
                }
            }
            return v1;
        }

        @Override  // androidx.recyclerview.widget.RecyclerView$Adapter
        public void onBindViewHolder(androidx.recyclerview.widget.RecyclerView.ViewHolder recyclerView$ViewHolder0, int v) {
            this.onBindViewHolder(((ViewHolder)recyclerView$ViewHolder0), v);
        }

        public void onBindViewHolder(ViewHolder navigationMenuPresenter$ViewHolder0, int v) {
            switch(this.getItemViewType(v)) {
                case 0: {
                    NavigationMenuItemView navigationMenuItemView0 = (NavigationMenuItemView)navigationMenuPresenter$ViewHolder0.itemView;
                    navigationMenuItemView0.setIconTintList(NavigationMenuPresenter.this.iconTintList);
                    navigationMenuItemView0.setTextAppearance(NavigationMenuPresenter.this.textAppearance);
                    if(NavigationMenuPresenter.this.textColor != null) {
                        navigationMenuItemView0.setTextColor(NavigationMenuPresenter.this.textColor);
                    }
                    ViewCompat.setBackground(navigationMenuItemView0, (NavigationMenuPresenter.this.itemBackground == null ? null : NavigationMenuPresenter.this.itemBackground.getConstantState().newDrawable()));
                    if(NavigationMenuPresenter.this.itemForeground != null) {
                        navigationMenuItemView0.setForeground(NavigationMenuPresenter.this.itemForeground.getConstantState().newDrawable());
                    }
                    NavigationMenuTextItem navigationMenuPresenter$NavigationMenuTextItem0 = (NavigationMenuTextItem)this.items.get(v);
                    navigationMenuItemView0.setNeedsEmptyIcon(navigationMenuPresenter$NavigationMenuTextItem0.needsEmptyIcon);
                    navigationMenuItemView0.setPadding(NavigationMenuPresenter.this.itemHorizontalPadding, NavigationMenuPresenter.this.itemVerticalPadding, NavigationMenuPresenter.this.itemHorizontalPadding, NavigationMenuPresenter.this.itemVerticalPadding);
                    navigationMenuItemView0.setIconPadding(NavigationMenuPresenter.this.itemIconPadding);
                    if(NavigationMenuPresenter.this.hasCustomItemIconSize) {
                        navigationMenuItemView0.setIconSize(NavigationMenuPresenter.this.itemIconSize);
                    }
                    navigationMenuItemView0.setMaxLines(NavigationMenuPresenter.this.itemMaxLines);
                    navigationMenuItemView0.initialize(navigationMenuPresenter$NavigationMenuTextItem0.getMenuItem(), NavigationMenuPresenter.this.textAppearanceActiveBoldEnabled);
                    this.setAccessibilityDelegate(navigationMenuItemView0, v, false);
                    return;
                }
                case 1: {
                    TextView textView0 = (TextView)navigationMenuPresenter$ViewHolder0.itemView;
                    textView0.setText(((NavigationMenuTextItem)this.items.get(v)).getMenuItem().getTitle());
                    TextViewCompat.setTextAppearance(textView0, NavigationMenuPresenter.this.subheaderTextAppearance);
                    textView0.setPadding(NavigationMenuPresenter.this.subheaderInsetStart, textView0.getPaddingTop(), NavigationMenuPresenter.this.subheaderInsetEnd, textView0.getPaddingBottom());
                    if(NavigationMenuPresenter.this.subheaderColor != null) {
                        textView0.setTextColor(NavigationMenuPresenter.this.subheaderColor);
                    }
                    this.setAccessibilityDelegate(textView0, v, true);
                    return;
                }
                case 2: {
                    NavigationMenuSeparatorItem navigationMenuPresenter$NavigationMenuSeparatorItem0 = (NavigationMenuSeparatorItem)this.items.get(v);
                    navigationMenuPresenter$ViewHolder0.itemView.setPadding(NavigationMenuPresenter.this.dividerInsetStart, navigationMenuPresenter$NavigationMenuSeparatorItem0.getPaddingTop(), NavigationMenuPresenter.this.dividerInsetEnd, navigationMenuPresenter$NavigationMenuSeparatorItem0.getPaddingBottom());
                }
            }
        }

        @Override  // androidx.recyclerview.widget.RecyclerView$Adapter
        public androidx.recyclerview.widget.RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup0, int v) {
            return this.onCreateViewHolder(viewGroup0, v);
        }

        public ViewHolder onCreateViewHolder(ViewGroup viewGroup0, int v) {
            switch(v) {
                case 0: {
                    return new NormalViewHolder(NavigationMenuPresenter.this.layoutInflater, viewGroup0, NavigationMenuPresenter.this.onClickListener);
                }
                case 1: {
                    return new SubheaderViewHolder(NavigationMenuPresenter.this.layoutInflater, viewGroup0);
                }
                case 2: {
                    return new SeparatorViewHolder(NavigationMenuPresenter.this.layoutInflater, viewGroup0);
                }
                case 3: {
                    return new HeaderViewHolder(NavigationMenuPresenter.this.headerLayout);
                }
                default: {
                    return null;
                }
            }
        }

        @Override  // androidx.recyclerview.widget.RecyclerView$Adapter
        public void onViewRecycled(androidx.recyclerview.widget.RecyclerView.ViewHolder recyclerView$ViewHolder0) {
            this.onViewRecycled(((ViewHolder)recyclerView$ViewHolder0));
        }

        public void onViewRecycled(ViewHolder navigationMenuPresenter$ViewHolder0) {
            if(navigationMenuPresenter$ViewHolder0 instanceof NormalViewHolder) {
                ((NavigationMenuItemView)navigationMenuPresenter$ViewHolder0.itemView).recycle();
            }
        }

        private void prepareMenuItems() {
            if(this.updateSuspended) {
                return;
            }
            this.updateSuspended = true;
            this.items.clear();
            NavigationMenuHeaderItem navigationMenuPresenter$NavigationMenuHeaderItem0 = new NavigationMenuHeaderItem();
            this.items.add(navigationMenuPresenter$NavigationMenuHeaderItem0);
            int v = NavigationMenuPresenter.this.menu.getVisibleItems().size();
            int v1 = -1;
            boolean z = false;
            int v3 = 0;
            for(int v2 = 0; v2 < v; ++v2) {
                MenuItemImpl menuItemImpl0 = (MenuItemImpl)NavigationMenuPresenter.this.menu.getVisibleItems().get(v2);
                if(menuItemImpl0.isChecked()) {
                    this.setCheckedItem(menuItemImpl0);
                }
                if(menuItemImpl0.isCheckable()) {
                    menuItemImpl0.setExclusiveCheckable(false);
                }
                if(menuItemImpl0.hasSubMenu()) {
                    SubMenu subMenu0 = menuItemImpl0.getSubMenu();
                    if(subMenu0.hasVisibleItems()) {
                        if(v2 != 0) {
                            NavigationMenuSeparatorItem navigationMenuPresenter$NavigationMenuSeparatorItem0 = new NavigationMenuSeparatorItem(NavigationMenuPresenter.this.paddingSeparator, 0);
                            this.items.add(navigationMenuPresenter$NavigationMenuSeparatorItem0);
                        }
                        NavigationMenuTextItem navigationMenuPresenter$NavigationMenuTextItem0 = new NavigationMenuTextItem(menuItemImpl0);
                        this.items.add(navigationMenuPresenter$NavigationMenuTextItem0);
                        int v4 = this.items.size();
                        int v5 = subMenu0.size();
                        boolean z1 = false;
                        for(int v6 = 0; v6 < v5; ++v6) {
                            MenuItemImpl menuItemImpl1 = (MenuItemImpl)subMenu0.getItem(v6);
                            if(menuItemImpl1.isVisible()) {
                                if(!z1 && menuItemImpl1.getIcon() != null) {
                                    z1 = true;
                                }
                                if(menuItemImpl1.isCheckable()) {
                                    menuItemImpl1.setExclusiveCheckable(false);
                                }
                                if(menuItemImpl0.isChecked()) {
                                    this.setCheckedItem(menuItemImpl0);
                                }
                                NavigationMenuTextItem navigationMenuPresenter$NavigationMenuTextItem1 = new NavigationMenuTextItem(menuItemImpl1);
                                this.items.add(navigationMenuPresenter$NavigationMenuTextItem1);
                            }
                        }
                        if(z1) {
                            this.appendTransparentIconIfMissing(v4, this.items.size());
                        }
                    }
                }
                else {
                    int v7 = menuItemImpl0.getGroupId();
                    if(v7 != v1) {
                        v3 = this.items.size();
                        z = menuItemImpl0.getIcon() != null;
                        if(v2 != 0) {
                            ++v3;
                            NavigationMenuSeparatorItem navigationMenuPresenter$NavigationMenuSeparatorItem1 = new NavigationMenuSeparatorItem(NavigationMenuPresenter.this.paddingSeparator, NavigationMenuPresenter.this.paddingSeparator);
                            this.items.add(navigationMenuPresenter$NavigationMenuSeparatorItem1);
                        }
                    }
                    else if(!z && menuItemImpl0.getIcon() != null) {
                        this.appendTransparentIconIfMissing(v3, this.items.size());
                        z = true;
                    }
                    NavigationMenuTextItem navigationMenuPresenter$NavigationMenuTextItem2 = new NavigationMenuTextItem(menuItemImpl0);
                    navigationMenuPresenter$NavigationMenuTextItem2.needsEmptyIcon = z;
                    this.items.add(navigationMenuPresenter$NavigationMenuTextItem2);
                    v1 = v7;
                }
            }
            this.updateSuspended = false;
        }

        public void restoreInstanceState(Bundle bundle0) {
            int v1 = bundle0.getInt("android:menu:checked", 0);
            if(v1 != 0) {
                this.updateSuspended = true;
                int v2 = this.items.size();
                for(int v3 = 0; v3 < v2; ++v3) {
                    NavigationMenuItem navigationMenuPresenter$NavigationMenuItem0 = (NavigationMenuItem)this.items.get(v3);
                    if(navigationMenuPresenter$NavigationMenuItem0 instanceof NavigationMenuTextItem) {
                        MenuItemImpl menuItemImpl0 = ((NavigationMenuTextItem)navigationMenuPresenter$NavigationMenuItem0).getMenuItem();
                        if(menuItemImpl0 != null && menuItemImpl0.getItemId() == v1) {
                            this.setCheckedItem(menuItemImpl0);
                            break;
                        }
                    }
                }
                this.updateSuspended = false;
                this.prepareMenuItems();
            }
            SparseArray sparseArray0 = bundle0.getSparseParcelableArray("android:menu:action_views");
            if(sparseArray0 != null) {
                int v4 = this.items.size();
                for(int v = 0; v < v4; ++v) {
                    NavigationMenuItem navigationMenuPresenter$NavigationMenuItem1 = (NavigationMenuItem)this.items.get(v);
                    if(navigationMenuPresenter$NavigationMenuItem1 instanceof NavigationMenuTextItem) {
                        MenuItemImpl menuItemImpl1 = ((NavigationMenuTextItem)navigationMenuPresenter$NavigationMenuItem1).getMenuItem();
                        if(menuItemImpl1 != null) {
                            View view0 = menuItemImpl1.getActionView();
                            if(view0 != null) {
                                ParcelableSparseArray parcelableSparseArray0 = (ParcelableSparseArray)sparseArray0.get(menuItemImpl1.getItemId());
                                if(parcelableSparseArray0 != null) {
                                    view0.restoreHierarchyState(parcelableSparseArray0);
                                }
                            }
                        }
                    }
                }
            }
        }

        private void setAccessibilityDelegate(View view0, int v, boolean z) {
            ViewCompat.setAccessibilityDelegate(view0, new AccessibilityDelegateCompat() {
                @Override  // androidx.core.view.AccessibilityDelegateCompat
                public void onInitializeAccessibilityNodeInfo(View view0, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
                    super.onInitializeAccessibilityNodeInfo(view0, accessibilityNodeInfoCompat0);
                    int v = NavigationMenuAdapter.this.adjustItemPositionForA11yDelegate(v);
                    boolean z = view0.isSelected();
                    accessibilityNodeInfoCompat0.setCollectionItemInfo(CollectionItemInfoCompat.obtain(v, 1, 1, 1, z, z));
                }
            });
        }

        public void setCheckedItem(MenuItemImpl menuItemImpl0) {
            if(this.checkedItem != menuItemImpl0 && menuItemImpl0.isCheckable()) {
                MenuItemImpl menuItemImpl1 = this.checkedItem;
                if(menuItemImpl1 != null) {
                    menuItemImpl1.setChecked(false);
                }
                this.checkedItem = menuItemImpl0;
                menuItemImpl0.setChecked(true);
            }
        }

        public void setUpdateSuspended(boolean z) {
            this.updateSuspended = z;
        }

        public void update() {
            this.prepareMenuItems();
            this.notifyDataSetChanged();
        }
    }

    static class NavigationMenuHeaderItem implements NavigationMenuItem {
    }

    interface NavigationMenuItem {
    }

    static class NavigationMenuSeparatorItem implements NavigationMenuItem {
        private final int paddingBottom;
        private final int paddingTop;

        public NavigationMenuSeparatorItem(int v, int v1) {
            this.paddingTop = v;
            this.paddingBottom = v1;
        }

        public int getPaddingBottom() {
            return this.paddingBottom;
        }

        public int getPaddingTop() {
            return this.paddingTop;
        }
    }

    static class NavigationMenuTextItem implements NavigationMenuItem {
        private final MenuItemImpl menuItem;
        boolean needsEmptyIcon;

        NavigationMenuTextItem(MenuItemImpl menuItemImpl0) {
            this.menuItem = menuItemImpl0;
        }

        public MenuItemImpl getMenuItem() {
            return this.menuItem;
        }
    }

    class NavigationMenuViewAccessibilityDelegate extends RecyclerViewAccessibilityDelegate {
        NavigationMenuViewAccessibilityDelegate(RecyclerView recyclerView0) {
            super(recyclerView0);
        }

        @Override  // androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate
        public void onInitializeAccessibilityNodeInfo(View view0, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
            super.onInitializeAccessibilityNodeInfo(view0, accessibilityNodeInfoCompat0);
            accessibilityNodeInfoCompat0.setCollectionInfo(CollectionInfoCompat.obtain(NavigationMenuPresenter.this.adapter.getRowCount(), 1, false));
        }
    }

    static class NormalViewHolder extends ViewHolder {
        public NormalViewHolder(LayoutInflater layoutInflater0, ViewGroup viewGroup0, View.OnClickListener view$OnClickListener0) {
            super(layoutInflater0.inflate(layout.design_navigation_item, viewGroup0, false));
            this.itemView.setOnClickListener(view$OnClickListener0);
        }
    }

    static class SeparatorViewHolder extends ViewHolder {
        public SeparatorViewHolder(LayoutInflater layoutInflater0, ViewGroup viewGroup0) {
            super(layoutInflater0.inflate(layout.design_navigation_item_separator, viewGroup0, false));
        }
    }

    static class SubheaderViewHolder extends ViewHolder {
        public SubheaderViewHolder(LayoutInflater layoutInflater0, ViewGroup viewGroup0) {
            super(layoutInflater0.inflate(layout.design_navigation_item_subheader, viewGroup0, false));
        }
    }

    static abstract class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        public ViewHolder(View view0) {
            super(view0);
        }
    }

    public static final int NO_TEXT_APPEARANCE_SET = 0;
    private static final String STATE_ADAPTER = "android:menu:adapter";
    private static final String STATE_HEADER = "android:menu:header";
    private static final String STATE_HIERARCHY = "android:menu:list";
    NavigationMenuAdapter adapter;
    private Callback callback;
    int dividerInsetEnd;
    int dividerInsetStart;
    boolean hasCustomItemIconSize;
    LinearLayout headerLayout;
    ColorStateList iconTintList;
    private int id;
    boolean isBehindStatusBar;
    Drawable itemBackground;
    RippleDrawable itemForeground;
    int itemHorizontalPadding;
    int itemIconPadding;
    int itemIconSize;
    private int itemMaxLines;
    int itemVerticalPadding;
    LayoutInflater layoutInflater;
    MenuBuilder menu;
    private NavigationMenuView menuView;
    final View.OnClickListener onClickListener;
    private int overScrollMode;
    int paddingSeparator;
    private int paddingTopDefault;
    ColorStateList subheaderColor;
    int subheaderInsetEnd;
    int subheaderInsetStart;
    int subheaderTextAppearance;
    int textAppearance;
    boolean textAppearanceActiveBoldEnabled;
    ColorStateList textColor;

    public NavigationMenuPresenter() {
        this.subheaderTextAppearance = 0;
        this.textAppearance = 0;
        this.textAppearanceActiveBoldEnabled = true;
        this.isBehindStatusBar = true;
        this.overScrollMode = -1;
        this.onClickListener = new View.OnClickListener() {
            @Override  // android.view.View$OnClickListener
            public void onClick(View view0) {
                boolean z = true;
                NavigationMenuPresenter.this.setUpdateSuspended(true);
                MenuItemImpl menuItemImpl0 = ((NavigationMenuItemView)view0).getItemData();
                if(menuItemImpl0 == null || !menuItemImpl0.isCheckable() || !NavigationMenuPresenter.this.menu.performItemAction(menuItemImpl0, NavigationMenuPresenter.this, 0)) {
                    z = false;
                }
                else {
                    NavigationMenuPresenter.this.adapter.setCheckedItem(menuItemImpl0);
                }
                NavigationMenuPresenter.this.setUpdateSuspended(false);
                if(z) {
                    NavigationMenuPresenter.this.updateMenuView(false);
                }
            }
        };
    }

    public void addHeaderView(View view0) {
        this.headerLayout.addView(view0);
        this.menuView.setPadding(0, 0, 0, this.menuView.getPaddingBottom());
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public boolean collapseItemActionView(MenuBuilder menuBuilder0, MenuItemImpl menuItemImpl0) {
        return false;
    }

    public void dispatchApplyWindowInsets(WindowInsetsCompat windowInsetsCompat0) {
        int v = windowInsetsCompat0.getSystemWindowInsetTop();
        if(this.paddingTopDefault != v) {
            this.paddingTopDefault = v;
            this.updateTopPadding();
        }
        this.menuView.setPadding(0, this.menuView.getPaddingTop(), 0, windowInsetsCompat0.getSystemWindowInsetBottom());
        ViewCompat.dispatchApplyWindowInsets(this.headerLayout, windowInsetsCompat0);
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public boolean expandItemActionView(MenuBuilder menuBuilder0, MenuItemImpl menuItemImpl0) {
        return false;
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public boolean flagActionItems() {
        return false;
    }

    public MenuItemImpl getCheckedItem() {
        return this.adapter.getCheckedItem();
    }

    public int getDividerInsetEnd() {
        return this.dividerInsetEnd;
    }

    public int getDividerInsetStart() {
        return this.dividerInsetStart;
    }

    public int getHeaderCount() {
        return this.headerLayout.getChildCount();
    }

    public View getHeaderView(int v) {
        return this.headerLayout.getChildAt(v);
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public int getId() {
        return this.id;
    }

    public Drawable getItemBackground() {
        return this.itemBackground;
    }

    public int getItemHorizontalPadding() {
        return this.itemHorizontalPadding;
    }

    public int getItemIconPadding() {
        return this.itemIconPadding;
    }

    public int getItemMaxLines() {
        return this.itemMaxLines;
    }

    public ColorStateList getItemTextColor() {
        return this.textColor;
    }

    public ColorStateList getItemTintList() {
        return this.iconTintList;
    }

    public int getItemVerticalPadding() {
        return this.itemVerticalPadding;
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public MenuView getMenuView(ViewGroup viewGroup0) {
        if(this.menuView == null) {
            NavigationMenuView navigationMenuView0 = (NavigationMenuView)this.layoutInflater.inflate(layout.design_navigation_menu, viewGroup0, false);
            this.menuView = navigationMenuView0;
            navigationMenuView0.setAccessibilityDelegateCompat(new NavigationMenuViewAccessibilityDelegate(this, this.menuView));
            if(this.adapter == null) {
                NavigationMenuAdapter navigationMenuPresenter$NavigationMenuAdapter0 = new NavigationMenuAdapter(this);
                this.adapter = navigationMenuPresenter$NavigationMenuAdapter0;
                navigationMenuPresenter$NavigationMenuAdapter0.setHasStableIds(true);
            }
            int v = this.overScrollMode;
            if(v != -1) {
                this.menuView.setOverScrollMode(v);
            }
            LinearLayout linearLayout0 = (LinearLayout)this.layoutInflater.inflate(layout.design_navigation_item_header, this.menuView, false);
            this.headerLayout = linearLayout0;
            ViewCompat.setImportantForAccessibility(linearLayout0, 2);
            this.menuView.setAdapter(this.adapter);
        }
        return this.menuView;
    }

    public int getSubheaderInsetEnd() {
        return this.subheaderInsetEnd;
    }

    public int getSubheaderInsetStart() {
        return this.subheaderInsetStart;
    }

    private boolean hasHeader() {
        return this.getHeaderCount() > 0;
    }

    public View inflateHeaderView(int v) {
        View view0 = this.layoutInflater.inflate(v, this.headerLayout, false);
        this.addHeaderView(view0);
        return view0;
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public void initForMenu(Context context0, MenuBuilder menuBuilder0) {
        this.layoutInflater = LayoutInflater.from(context0);
        this.menu = menuBuilder0;
        this.paddingSeparator = context0.getResources().getDimensionPixelOffset(dimen.design_navigation_separator_vertical_padding);
    }

    public boolean isBehindStatusBar() {
        return this.isBehindStatusBar;
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public void onCloseMenu(MenuBuilder menuBuilder0, boolean z) {
        Callback menuPresenter$Callback0 = this.callback;
        if(menuPresenter$Callback0 != null) {
            menuPresenter$Callback0.onCloseMenu(menuBuilder0, z);
        }
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public void onRestoreInstanceState(Parcelable parcelable0) {
        if(parcelable0 instanceof Bundle) {
            SparseArray sparseArray0 = ((Bundle)parcelable0).getSparseParcelableArray("android:menu:list");
            if(sparseArray0 != null) {
                this.menuView.restoreHierarchyState(sparseArray0);
            }
            Bundle bundle0 = ((Bundle)parcelable0).getBundle("android:menu:adapter");
            if(bundle0 != null) {
                this.adapter.restoreInstanceState(bundle0);
            }
            SparseArray sparseArray1 = ((Bundle)parcelable0).getSparseParcelableArray("android:menu:header");
            if(sparseArray1 != null) {
                this.headerLayout.restoreHierarchyState(sparseArray1);
            }
        }
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public Parcelable onSaveInstanceState() {
        Parcelable parcelable0 = new Bundle();
        if(this.menuView != null) {
            SparseArray sparseArray0 = new SparseArray();
            this.menuView.saveHierarchyState(sparseArray0);
            ((Bundle)parcelable0).putSparseParcelableArray("android:menu:list", sparseArray0);
        }
        NavigationMenuAdapter navigationMenuPresenter$NavigationMenuAdapter0 = this.adapter;
        if(navigationMenuPresenter$NavigationMenuAdapter0 != null) {
            ((Bundle)parcelable0).putBundle("android:menu:adapter", navigationMenuPresenter$NavigationMenuAdapter0.createInstanceState());
        }
        if(this.headerLayout != null) {
            SparseArray sparseArray1 = new SparseArray();
            this.headerLayout.saveHierarchyState(sparseArray1);
            ((Bundle)parcelable0).putSparseParcelableArray("android:menu:header", sparseArray1);
        }
        return parcelable0;
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder0) {
        return false;
    }

    public void removeHeaderView(View view0) {
        this.headerLayout.removeView(view0);
        if(!this.hasHeader()) {
            this.menuView.setPadding(0, this.paddingTopDefault, 0, this.menuView.getPaddingBottom());
        }
    }

    public void setBehindStatusBar(boolean z) {
        if(this.isBehindStatusBar != z) {
            this.isBehindStatusBar = z;
            this.updateTopPadding();
        }
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public void setCallback(Callback menuPresenter$Callback0) {
        this.callback = menuPresenter$Callback0;
    }

    public void setCheckedItem(MenuItemImpl menuItemImpl0) {
        this.adapter.setCheckedItem(menuItemImpl0);
    }

    public void setDividerInsetEnd(int v) {
        this.dividerInsetEnd = v;
        this.updateMenuView(false);
    }

    public void setDividerInsetStart(int v) {
        this.dividerInsetStart = v;
        this.updateMenuView(false);
    }

    public void setId(int v) {
        this.id = v;
    }

    public void setItemBackground(Drawable drawable0) {
        this.itemBackground = drawable0;
        this.updateMenuView(false);
    }

    public void setItemForeground(RippleDrawable rippleDrawable0) {
        this.itemForeground = rippleDrawable0;
        this.updateMenuView(false);
    }

    public void setItemHorizontalPadding(int v) {
        this.itemHorizontalPadding = v;
        this.updateMenuView(false);
    }

    public void setItemIconPadding(int v) {
        this.itemIconPadding = v;
        this.updateMenuView(false);
    }

    public void setItemIconSize(int v) {
        if(this.itemIconSize != v) {
            this.itemIconSize = v;
            this.hasCustomItemIconSize = true;
            this.updateMenuView(false);
        }
    }

    public void setItemIconTintList(ColorStateList colorStateList0) {
        this.iconTintList = colorStateList0;
        this.updateMenuView(false);
    }

    public void setItemMaxLines(int v) {
        this.itemMaxLines = v;
        this.updateMenuView(false);
    }

    public void setItemTextAppearance(int v) {
        this.textAppearance = v;
        this.updateMenuView(false);
    }

    public void setItemTextAppearanceActiveBoldEnabled(boolean z) {
        this.textAppearanceActiveBoldEnabled = z;
        this.updateMenuView(false);
    }

    public void setItemTextColor(ColorStateList colorStateList0) {
        this.textColor = colorStateList0;
        this.updateMenuView(false);
    }

    public void setItemVerticalPadding(int v) {
        this.itemVerticalPadding = v;
        this.updateMenuView(false);
    }

    public void setOverScrollMode(int v) {
        this.overScrollMode = v;
        NavigationMenuView navigationMenuView0 = this.menuView;
        if(navigationMenuView0 != null) {
            navigationMenuView0.setOverScrollMode(v);
        }
    }

    public void setSubheaderColor(ColorStateList colorStateList0) {
        this.subheaderColor = colorStateList0;
        this.updateMenuView(false);
    }

    public void setSubheaderInsetEnd(int v) {
        this.subheaderInsetEnd = v;
        this.updateMenuView(false);
    }

    public void setSubheaderInsetStart(int v) {
        this.subheaderInsetStart = v;
        this.updateMenuView(false);
    }

    public void setSubheaderTextAppearance(int v) {
        this.subheaderTextAppearance = v;
        this.updateMenuView(false);
    }

    public void setUpdateSuspended(boolean z) {
        NavigationMenuAdapter navigationMenuPresenter$NavigationMenuAdapter0 = this.adapter;
        if(navigationMenuPresenter$NavigationMenuAdapter0 != null) {
            navigationMenuPresenter$NavigationMenuAdapter0.setUpdateSuspended(z);
        }
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public void updateMenuView(boolean z) {
        NavigationMenuAdapter navigationMenuPresenter$NavigationMenuAdapter0 = this.adapter;
        if(navigationMenuPresenter$NavigationMenuAdapter0 != null) {
            navigationMenuPresenter$NavigationMenuAdapter0.update();
        }
    }

    private void updateTopPadding() {
        int v = this.hasHeader() || !this.isBehindStatusBar ? 0 : this.paddingTopDefault;
        this.menuView.setPadding(0, v, 0, this.menuView.getPaddingBottom());
    }
}

