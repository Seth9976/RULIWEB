package com.google.android.material.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources.Theme;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.CheckedTextView;
import android.widget.FrameLayout;
import androidx.appcompat.R.attr;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuView.ItemView;
import androidx.appcompat.widget.LinearLayoutCompat.LayoutParams;
import androidx.appcompat.widget.TooltipCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.widget.TextViewCompat;
import com.google.android.material.R.dimen;
import com.google.android.material.R.drawable;
import com.google.android.material.R.id;
import com.google.android.material.R.layout;

public class NavigationMenuItemView extends ForegroundLinearLayout implements ItemView {
    private static final int[] CHECKED_STATE_SET;
    private final AccessibilityDelegateCompat accessibilityDelegate;
    private FrameLayout actionArea;
    boolean checkable;
    private Drawable emptyDrawable;
    private boolean hasIconTintList;
    private int iconSize;
    private ColorStateList iconTintList;
    boolean isBold;
    private MenuItemImpl itemData;
    private boolean needsEmptyIcon;
    private final CheckedTextView textView;

    static {
        NavigationMenuItemView.CHECKED_STATE_SET = new int[]{0x10100A0};
    }

    public NavigationMenuItemView(Context context0) {
        this(context0, null);
    }

    public NavigationMenuItemView(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, 0);
    }

    public NavigationMenuItemView(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        this.isBold = true;
        com.google.android.material.internal.NavigationMenuItemView.1 navigationMenuItemView$10 = new AccessibilityDelegateCompat() {
            @Override  // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(View view0, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
                super.onInitializeAccessibilityNodeInfo(view0, accessibilityNodeInfoCompat0);
                accessibilityNodeInfoCompat0.setCheckable(NavigationMenuItemView.this.checkable);
            }
        };
        this.accessibilityDelegate = navigationMenuItemView$10;
        this.setOrientation(0);
        LayoutInflater.from(context0).inflate(layout.design_navigation_menu_item, this, true);
        this.setIconSize(context0.getResources().getDimensionPixelSize(dimen.design_navigation_icon_size));
        CheckedTextView checkedTextView0 = (CheckedTextView)this.findViewById(id.design_menu_item_text);
        this.textView = checkedTextView0;
        checkedTextView0.setDuplicateParentStateEnabled(true);
        ViewCompat.setAccessibilityDelegate(checkedTextView0, navigationMenuItemView$10);
    }

    private void adjustAppearance() {
        if(this.shouldExpandActionArea()) {
            this.textView.setVisibility(8);
            FrameLayout frameLayout0 = this.actionArea;
            if(frameLayout0 != null) {
                LayoutParams linearLayoutCompat$LayoutParams0 = (LayoutParams)frameLayout0.getLayoutParams();
                linearLayoutCompat$LayoutParams0.width = -1;
                this.actionArea.setLayoutParams(linearLayoutCompat$LayoutParams0);
            }
        }
        else {
            this.textView.setVisibility(0);
            FrameLayout frameLayout1 = this.actionArea;
            if(frameLayout1 != null) {
                LayoutParams linearLayoutCompat$LayoutParams1 = (LayoutParams)frameLayout1.getLayoutParams();
                linearLayoutCompat$LayoutParams1.width = -2;
                this.actionArea.setLayoutParams(linearLayoutCompat$LayoutParams1);
            }
        }
    }

    private StateListDrawable createDefaultBackground() {
        TypedValue typedValue0 = new TypedValue();
        if(this.getContext().getTheme().resolveAttribute(attr.colorControlHighlight, typedValue0, true)) {
            StateListDrawable stateListDrawable0 = new StateListDrawable();
            ColorDrawable colorDrawable0 = new ColorDrawable(typedValue0.data);
            stateListDrawable0.addState(NavigationMenuItemView.CHECKED_STATE_SET, colorDrawable0);
            stateListDrawable0.addState(NavigationMenuItemView.EMPTY_STATE_SET, new ColorDrawable(0));
            return stateListDrawable0;
        }
        return null;
    }

    @Override  // androidx.appcompat.view.menu.MenuView$ItemView
    public MenuItemImpl getItemData() {
        return this.itemData;
    }

    @Override  // androidx.appcompat.view.menu.MenuView$ItemView
    public void initialize(MenuItemImpl menuItemImpl0, int v) {
        this.itemData = menuItemImpl0;
        if(menuItemImpl0.getItemId() > 0) {
            this.setId(menuItemImpl0.getItemId());
        }
        this.setVisibility((menuItemImpl0.isVisible() ? 0 : 8));
        if(this.getBackground() == null) {
            ViewCompat.setBackground(this, this.createDefaultBackground());
        }
        this.setCheckable(menuItemImpl0.isCheckable());
        this.setChecked(menuItemImpl0.isChecked());
        this.setEnabled(menuItemImpl0.isEnabled());
        this.setTitle(menuItemImpl0.getTitle());
        this.setIcon(menuItemImpl0.getIcon());
        this.setActionView(menuItemImpl0.getActionView());
        this.setContentDescription(menuItemImpl0.getContentDescription());
        TooltipCompat.setTooltipText(this, menuItemImpl0.getTooltipText());
        this.adjustAppearance();
    }

    public void initialize(MenuItemImpl menuItemImpl0, boolean z) {
        this.isBold = z;
        this.initialize(menuItemImpl0, 0);
    }

    @Override  // android.view.ViewGroup
    protected int[] onCreateDrawableState(int v) {
        int[] arr_v = super.onCreateDrawableState(v + 1);
        if(this.itemData != null && this.itemData.isCheckable() && this.itemData.isChecked()) {
            NavigationMenuItemView.mergeDrawableStates(arr_v, NavigationMenuItemView.CHECKED_STATE_SET);
        }
        return arr_v;
    }

    @Override  // androidx.appcompat.view.menu.MenuView$ItemView
    public boolean prefersCondensedTitle() {
        return false;
    }

    public void recycle() {
        FrameLayout frameLayout0 = this.actionArea;
        if(frameLayout0 != null) {
            frameLayout0.removeAllViews();
        }
        this.textView.setCompoundDrawables(null, null, null, null);
    }

    private void setActionView(View view0) {
        if(view0 != null) {
            if(this.actionArea == null) {
                this.actionArea = (FrameLayout)((ViewStub)this.findViewById(id.design_menu_item_action_area_stub)).inflate();
            }
            if(view0.getParent() != null) {
                ((ViewGroup)view0.getParent()).removeView(view0);
            }
            this.actionArea.removeAllViews();
            this.actionArea.addView(view0);
        }
    }

    @Override  // androidx.appcompat.view.menu.MenuView$ItemView
    public void setCheckable(boolean z) {
        this.refreshDrawableState();
        if(this.checkable != z) {
            this.checkable = z;
            this.accessibilityDelegate.sendAccessibilityEvent(this.textView, 0x800);
        }
    }

    @Override  // androidx.appcompat.view.menu.MenuView$ItemView
    public void setChecked(boolean z) {
        this.refreshDrawableState();
        this.textView.setChecked(z);
        Typeface typeface0 = this.textView.getTypeface();
        this.textView.setTypeface(typeface0, (!z || !this.isBold ? 0 : 1));
    }

    public void setHorizontalPadding(int v) {
        this.setPadding(v, this.getPaddingTop(), v, this.getPaddingBottom());
    }

    @Override  // androidx.appcompat.view.menu.MenuView$ItemView
    public void setIcon(Drawable drawable0) {
        if(drawable0 != null) {
            if(this.hasIconTintList) {
                Drawable.ConstantState drawable$ConstantState0 = drawable0.getConstantState();
                if(drawable$ConstantState0 != null) {
                    drawable0 = drawable$ConstantState0.newDrawable();
                }
                drawable0 = DrawableCompat.wrap(drawable0).mutate();
                DrawableCompat.setTintList(drawable0, this.iconTintList);
            }
            drawable0.setBounds(0, 0, this.iconSize, this.iconSize);
        }
        else if(this.needsEmptyIcon) {
            if(this.emptyDrawable == null) {
                Resources resources0 = this.getResources();
                Resources.Theme resources$Theme0 = this.getContext().getTheme();
                Drawable drawable1 = ResourcesCompat.getDrawable(resources0, drawable.navigation_empty_icon, resources$Theme0);
                this.emptyDrawable = drawable1;
                if(drawable1 != null) {
                    drawable1.setBounds(0, 0, this.iconSize, this.iconSize);
                }
            }
            drawable0 = this.emptyDrawable;
        }
        TextViewCompat.setCompoundDrawablesRelative(this.textView, drawable0, null, null, null);
    }

    public void setIconPadding(int v) {
        this.textView.setCompoundDrawablePadding(v);
    }

    public void setIconSize(int v) {
        this.iconSize = v;
    }

    void setIconTintList(ColorStateList colorStateList0) {
        this.iconTintList = colorStateList0;
        this.hasIconTintList = colorStateList0 != null;
        MenuItemImpl menuItemImpl0 = this.itemData;
        if(menuItemImpl0 != null) {
            this.setIcon(menuItemImpl0.getIcon());
        }
    }

    public void setMaxLines(int v) {
        this.textView.setMaxLines(v);
    }

    public void setNeedsEmptyIcon(boolean z) {
        this.needsEmptyIcon = z;
    }

    @Override  // androidx.appcompat.view.menu.MenuView$ItemView
    public void setShortcut(boolean z, char c) {
    }

    public void setTextAppearance(int v) {
        TextViewCompat.setTextAppearance(this.textView, v);
    }

    public void setTextColor(ColorStateList colorStateList0) {
        this.textView.setTextColor(colorStateList0);
    }

    @Override  // androidx.appcompat.view.menu.MenuView$ItemView
    public void setTitle(CharSequence charSequence0) {
        this.textView.setText(charSequence0);
    }

    private boolean shouldExpandActionArea() {
        return this.itemData.getTitle() == null && this.itemData.getIcon() == null && this.itemData.getActionView() != null;
    }

    @Override  // androidx.appcompat.view.menu.MenuView$ItemView
    public boolean showsIcon() {
        return true;
    }
}

