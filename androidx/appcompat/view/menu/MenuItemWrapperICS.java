package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.ActionProvider.VisibilityListener;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem.OnActionExpandListener;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.FrameLayout;
import androidx.appcompat.view.CollapsibleActionView;
import androidx.core.internal.view.SupportMenuItem;
import androidx.core.view.ActionProvider.VisibilityListener;
import androidx.core.view.ActionProvider;
import java.lang.reflect.Method;

public class MenuItemWrapperICS extends BaseMenuWrapper implements MenuItem {
    class ActionProviderWrapper extends ActionProvider implements ActionProvider.VisibilityListener {
        private final android.view.ActionProvider mInner;
        private VisibilityListener mListener;

        ActionProviderWrapper(Context context0, android.view.ActionProvider actionProvider0) {
            super(context0);
            this.mInner = actionProvider0;
        }

        @Override  // androidx.core.view.ActionProvider
        public boolean hasSubMenu() {
            return this.mInner.hasSubMenu();
        }

        @Override  // androidx.core.view.ActionProvider
        public boolean isVisible() {
            return this.mInner.isVisible();
        }

        @Override  // android.view.ActionProvider$VisibilityListener
        public void onActionProviderVisibilityChanged(boolean z) {
            VisibilityListener actionProvider$VisibilityListener0 = this.mListener;
            if(actionProvider$VisibilityListener0 != null) {
                actionProvider$VisibilityListener0.onActionProviderVisibilityChanged(z);
            }
        }

        @Override  // androidx.core.view.ActionProvider
        public View onCreateActionView() {
            return this.mInner.onCreateActionView();
        }

        @Override  // androidx.core.view.ActionProvider
        public View onCreateActionView(MenuItem menuItem0) {
            return this.mInner.onCreateActionView(menuItem0);
        }

        @Override  // androidx.core.view.ActionProvider
        public boolean onPerformDefaultAction() {
            return this.mInner.onPerformDefaultAction();
        }

        @Override  // androidx.core.view.ActionProvider
        public void onPrepareSubMenu(SubMenu subMenu0) {
            SubMenu subMenu1 = MenuItemWrapperICS.this.getSubMenuWrapper(subMenu0);
            this.mInner.onPrepareSubMenu(subMenu1);
        }

        @Override  // androidx.core.view.ActionProvider
        public boolean overridesItemVisibility() {
            return this.mInner.overridesItemVisibility();
        }

        @Override  // androidx.core.view.ActionProvider
        public void refreshVisibility() {
            this.mInner.refreshVisibility();
        }

        @Override  // androidx.core.view.ActionProvider
        public void setVisibilityListener(VisibilityListener actionProvider$VisibilityListener0) {
            this.mListener = actionProvider$VisibilityListener0;
            this.mInner.setVisibilityListener((actionProvider$VisibilityListener0 == null ? null : this));
        }
    }

    static class CollapsibleActionViewWrapper extends FrameLayout implements CollapsibleActionView {
        final android.view.CollapsibleActionView mWrappedView;

        CollapsibleActionViewWrapper(View view0) {
            super(view0.getContext());
            this.mWrappedView = (android.view.CollapsibleActionView)view0;
            this.addView(view0);
        }

        View getWrappedView() {
            return (View)this.mWrappedView;
        }

        @Override  // androidx.appcompat.view.CollapsibleActionView
        public void onActionViewCollapsed() {
            this.mWrappedView.onActionViewCollapsed();
        }

        @Override  // androidx.appcompat.view.CollapsibleActionView
        public void onActionViewExpanded() {
            this.mWrappedView.onActionViewExpanded();
        }
    }

    class OnActionExpandListenerWrapper implements MenuItem.OnActionExpandListener {
        private final MenuItem.OnActionExpandListener mObject;

        OnActionExpandListenerWrapper(MenuItem.OnActionExpandListener menuItem$OnActionExpandListener0) {
            this.mObject = menuItem$OnActionExpandListener0;
        }

        @Override  // android.view.MenuItem$OnActionExpandListener
        public boolean onMenuItemActionCollapse(MenuItem menuItem0) {
            MenuItem menuItem1 = MenuItemWrapperICS.this.getMenuItemWrapper(menuItem0);
            return this.mObject.onMenuItemActionCollapse(menuItem1);
        }

        @Override  // android.view.MenuItem$OnActionExpandListener
        public boolean onMenuItemActionExpand(MenuItem menuItem0) {
            MenuItem menuItem1 = MenuItemWrapperICS.this.getMenuItemWrapper(menuItem0);
            return this.mObject.onMenuItemActionExpand(menuItem1);
        }
    }

    class OnMenuItemClickListenerWrapper implements MenuItem.OnMenuItemClickListener {
        private final MenuItem.OnMenuItemClickListener mObject;

        OnMenuItemClickListenerWrapper(MenuItem.OnMenuItemClickListener menuItem$OnMenuItemClickListener0) {
            this.mObject = menuItem$OnMenuItemClickListener0;
        }

        @Override  // android.view.MenuItem$OnMenuItemClickListener
        public boolean onMenuItemClick(MenuItem menuItem0) {
            MenuItem menuItem1 = MenuItemWrapperICS.this.getMenuItemWrapper(menuItem0);
            return this.mObject.onMenuItemClick(menuItem1);
        }
    }

    static final String LOG_TAG = "MenuItemWrapper";
    private Method mSetExclusiveCheckableMethod;
    private final SupportMenuItem mWrappedObject;

    public MenuItemWrapperICS(Context context0, SupportMenuItem supportMenuItem0) {
        super(context0);
        if(supportMenuItem0 == null) {
            throw new IllegalArgumentException("Wrapped Object can not be null.");
        }
        this.mWrappedObject = supportMenuItem0;
    }

    @Override  // android.view.MenuItem
    public boolean collapseActionView() {
        return this.mWrappedObject.collapseActionView();
    }

    @Override  // android.view.MenuItem
    public boolean expandActionView() {
        return this.mWrappedObject.expandActionView();
    }

    @Override  // android.view.MenuItem
    public android.view.ActionProvider getActionProvider() {
        ActionProvider actionProvider0 = this.mWrappedObject.getSupportActionProvider();
        return actionProvider0 instanceof ActionProviderWrapper ? ((ActionProviderWrapper)actionProvider0).mInner : null;
    }

    @Override  // android.view.MenuItem
    public View getActionView() {
        View view0 = this.mWrappedObject.getActionView();
        return view0 instanceof CollapsibleActionViewWrapper ? ((CollapsibleActionViewWrapper)view0).getWrappedView() : view0;
    }

    @Override  // android.view.MenuItem
    public int getAlphabeticModifiers() {
        return this.mWrappedObject.getAlphabeticModifiers();
    }

    @Override  // android.view.MenuItem
    public char getAlphabeticShortcut() {
        return this.mWrappedObject.getAlphabeticShortcut();
    }

    @Override  // android.view.MenuItem
    public CharSequence getContentDescription() {
        return this.mWrappedObject.getContentDescription();
    }

    @Override  // android.view.MenuItem
    public int getGroupId() {
        return this.mWrappedObject.getGroupId();
    }

    @Override  // android.view.MenuItem
    public Drawable getIcon() {
        return this.mWrappedObject.getIcon();
    }

    @Override  // android.view.MenuItem
    public ColorStateList getIconTintList() {
        return this.mWrappedObject.getIconTintList();
    }

    @Override  // android.view.MenuItem
    public PorterDuff.Mode getIconTintMode() {
        return this.mWrappedObject.getIconTintMode();
    }

    @Override  // android.view.MenuItem
    public Intent getIntent() {
        return this.mWrappedObject.getIntent();
    }

    @Override  // android.view.MenuItem
    public int getItemId() {
        return this.mWrappedObject.getItemId();
    }

    @Override  // android.view.MenuItem
    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return this.mWrappedObject.getMenuInfo();
    }

    @Override  // android.view.MenuItem
    public int getNumericModifiers() {
        return this.mWrappedObject.getNumericModifiers();
    }

    @Override  // android.view.MenuItem
    public char getNumericShortcut() {
        return this.mWrappedObject.getNumericShortcut();
    }

    @Override  // android.view.MenuItem
    public int getOrder() {
        return this.mWrappedObject.getOrder();
    }

    @Override  // android.view.MenuItem
    public SubMenu getSubMenu() {
        return this.getSubMenuWrapper(this.mWrappedObject.getSubMenu());
    }

    @Override  // android.view.MenuItem
    public CharSequence getTitle() {
        return this.mWrappedObject.getTitle();
    }

    @Override  // android.view.MenuItem
    public CharSequence getTitleCondensed() {
        return this.mWrappedObject.getTitleCondensed();
    }

    @Override  // android.view.MenuItem
    public CharSequence getTooltipText() {
        return this.mWrappedObject.getTooltipText();
    }

    @Override  // android.view.MenuItem
    public boolean hasSubMenu() {
        return this.mWrappedObject.hasSubMenu();
    }

    @Override  // android.view.MenuItem
    public boolean isActionViewExpanded() {
        return this.mWrappedObject.isActionViewExpanded();
    }

    @Override  // android.view.MenuItem
    public boolean isCheckable() {
        return this.mWrappedObject.isCheckable();
    }

    @Override  // android.view.MenuItem
    public boolean isChecked() {
        return this.mWrappedObject.isChecked();
    }

    @Override  // android.view.MenuItem
    public boolean isEnabled() {
        return this.mWrappedObject.isEnabled();
    }

    @Override  // android.view.MenuItem
    public boolean isVisible() {
        return this.mWrappedObject.isVisible();
    }

    @Override  // android.view.MenuItem
    public MenuItem setActionProvider(android.view.ActionProvider actionProvider0) {
        ActionProviderWrapper menuItemWrapperICS$ActionProviderWrapper0 = new ActionProviderWrapper(this, this.mContext, actionProvider0);
        SupportMenuItem supportMenuItem0 = this.mWrappedObject;
        if(actionProvider0 == null) {
            menuItemWrapperICS$ActionProviderWrapper0 = null;
        }
        supportMenuItem0.setSupportActionProvider(menuItemWrapperICS$ActionProviderWrapper0);
        return this;
    }

    @Override  // android.view.MenuItem
    public MenuItem setActionView(int v) {
        this.mWrappedObject.setActionView(v);
        View view0 = this.mWrappedObject.getActionView();
        if(view0 instanceof android.view.CollapsibleActionView) {
            CollapsibleActionViewWrapper menuItemWrapperICS$CollapsibleActionViewWrapper0 = new CollapsibleActionViewWrapper(view0);
            this.mWrappedObject.setActionView(menuItemWrapperICS$CollapsibleActionViewWrapper0);
        }
        return this;
    }

    @Override  // android.view.MenuItem
    public MenuItem setActionView(View view0) {
        if(view0 instanceof android.view.CollapsibleActionView) {
            view0 = new CollapsibleActionViewWrapper(view0);
        }
        this.mWrappedObject.setActionView(view0);
        return this;
    }

    @Override  // android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char c) {
        this.mWrappedObject.setAlphabeticShortcut(c);
        return this;
    }

    @Override  // android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char c, int v) {
        this.mWrappedObject.setAlphabeticShortcut(c, v);
        return this;
    }

    @Override  // android.view.MenuItem
    public MenuItem setCheckable(boolean z) {
        this.mWrappedObject.setCheckable(z);
        return this;
    }

    @Override  // android.view.MenuItem
    public MenuItem setChecked(boolean z) {
        this.mWrappedObject.setChecked(z);
        return this;
    }

    @Override  // android.view.MenuItem
    public MenuItem setContentDescription(CharSequence charSequence0) {
        this.mWrappedObject.setContentDescription(charSequence0);
        return this;
    }

    @Override  // android.view.MenuItem
    public MenuItem setEnabled(boolean z) {
        this.mWrappedObject.setEnabled(z);
        return this;
    }

    public void setExclusiveCheckable(boolean z) {
        try {
            if(this.mSetExclusiveCheckableMethod == null) {
                this.mSetExclusiveCheckableMethod = this.mWrappedObject.getClass().getDeclaredMethod("setExclusiveCheckable", Boolean.TYPE);
            }
            this.mSetExclusiveCheckableMethod.invoke(this.mWrappedObject, Boolean.valueOf(z));
        }
        catch(Exception exception0) {
            Log.w("MenuItemWrapper", "Error while calling setExclusiveCheckable", exception0);
        }
    }

    @Override  // android.view.MenuItem
    public MenuItem setIcon(int v) {
        this.mWrappedObject.setIcon(v);
        return this;
    }

    @Override  // android.view.MenuItem
    public MenuItem setIcon(Drawable drawable0) {
        this.mWrappedObject.setIcon(drawable0);
        return this;
    }

    @Override  // android.view.MenuItem
    public MenuItem setIconTintList(ColorStateList colorStateList0) {
        this.mWrappedObject.setIconTintList(colorStateList0);
        return this;
    }

    @Override  // android.view.MenuItem
    public MenuItem setIconTintMode(PorterDuff.Mode porterDuff$Mode0) {
        this.mWrappedObject.setIconTintMode(porterDuff$Mode0);
        return this;
    }

    @Override  // android.view.MenuItem
    public MenuItem setIntent(Intent intent0) {
        this.mWrappedObject.setIntent(intent0);
        return this;
    }

    @Override  // android.view.MenuItem
    public MenuItem setNumericShortcut(char c) {
        this.mWrappedObject.setNumericShortcut(c);
        return this;
    }

    @Override  // android.view.MenuItem
    public MenuItem setNumericShortcut(char c, int v) {
        this.mWrappedObject.setNumericShortcut(c, v);
        return this;
    }

    @Override  // android.view.MenuItem
    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener menuItem$OnActionExpandListener0) {
        OnActionExpandListenerWrapper menuItemWrapperICS$OnActionExpandListenerWrapper0 = menuItem$OnActionExpandListener0 == null ? null : new OnActionExpandListenerWrapper(this, menuItem$OnActionExpandListener0);
        this.mWrappedObject.setOnActionExpandListener(menuItemWrapperICS$OnActionExpandListenerWrapper0);
        return this;
    }

    @Override  // android.view.MenuItem
    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener menuItem$OnMenuItemClickListener0) {
        OnMenuItemClickListenerWrapper menuItemWrapperICS$OnMenuItemClickListenerWrapper0 = menuItem$OnMenuItemClickListener0 == null ? null : new OnMenuItemClickListenerWrapper(this, menuItem$OnMenuItemClickListener0);
        this.mWrappedObject.setOnMenuItemClickListener(menuItemWrapperICS$OnMenuItemClickListenerWrapper0);
        return this;
    }

    @Override  // android.view.MenuItem
    public MenuItem setShortcut(char c, char c1) {
        this.mWrappedObject.setShortcut(c, c1);
        return this;
    }

    @Override  // android.view.MenuItem
    public MenuItem setShortcut(char c, char c1, int v, int v1) {
        this.mWrappedObject.setShortcut(c, c1, v, v1);
        return this;
    }

    @Override  // android.view.MenuItem
    public void setShowAsAction(int v) {
        this.mWrappedObject.setShowAsAction(v);
    }

    @Override  // android.view.MenuItem
    public MenuItem setShowAsActionFlags(int v) {
        this.mWrappedObject.setShowAsActionFlags(v);
        return this;
    }

    @Override  // android.view.MenuItem
    public MenuItem setTitle(int v) {
        this.mWrappedObject.setTitle(v);
        return this;
    }

    @Override  // android.view.MenuItem
    public MenuItem setTitle(CharSequence charSequence0) {
        this.mWrappedObject.setTitle(charSequence0);
        return this;
    }

    @Override  // android.view.MenuItem
    public MenuItem setTitleCondensed(CharSequence charSequence0) {
        this.mWrappedObject.setTitleCondensed(charSequence0);
        return this;
    }

    @Override  // android.view.MenuItem
    public MenuItem setTooltipText(CharSequence charSequence0) {
        this.mWrappedObject.setTooltipText(charSequence0);
        return this;
    }

    @Override  // android.view.MenuItem
    public MenuItem setVisible(boolean z) {
        return this.mWrappedObject.setVisible(z);
    }
}

