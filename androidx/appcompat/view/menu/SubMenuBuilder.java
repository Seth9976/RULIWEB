package androidx.appcompat.view.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

public class SubMenuBuilder extends MenuBuilder implements SubMenu {
    private MenuItemImpl mItem;
    private MenuBuilder mParentMenu;

    public SubMenuBuilder(Context context0, MenuBuilder menuBuilder0, MenuItemImpl menuItemImpl0) {
        super(context0);
        this.mParentMenu = menuBuilder0;
        this.mItem = menuItemImpl0;
    }

    @Override  // androidx.appcompat.view.menu.MenuBuilder
    public boolean collapseItemActionView(MenuItemImpl menuItemImpl0) {
        return this.mParentMenu.collapseItemActionView(menuItemImpl0);
    }

    // 去混淆评级： 低(20)
    @Override  // androidx.appcompat.view.menu.MenuBuilder
    boolean dispatchMenuItemSelected(MenuBuilder menuBuilder0, MenuItem menuItem0) {
        return super.dispatchMenuItemSelected(menuBuilder0, menuItem0) || this.mParentMenu.dispatchMenuItemSelected(menuBuilder0, menuItem0);
    }

    @Override  // androidx.appcompat.view.menu.MenuBuilder
    public boolean expandItemActionView(MenuItemImpl menuItemImpl0) {
        return this.mParentMenu.expandItemActionView(menuItemImpl0);
    }

    // 去混淆评级： 低(25)
    @Override  // androidx.appcompat.view.menu.MenuBuilder
    public String getActionViewStatesKey() {
        int v = this.mItem == null ? 0 : this.mItem.getItemId();
        return v == 0 ? null : "android:menu:actionviewstates:" + v;
    }

    @Override  // android.view.SubMenu
    public MenuItem getItem() {
        return this.mItem;
    }

    public Menu getParentMenu() {
        return this.mParentMenu;
    }

    @Override  // androidx.appcompat.view.menu.MenuBuilder
    public MenuBuilder getRootMenu() {
        return this.mParentMenu.getRootMenu();
    }

    @Override  // androidx.appcompat.view.menu.MenuBuilder
    public boolean isGroupDividerEnabled() {
        return this.mParentMenu.isGroupDividerEnabled();
    }

    @Override  // androidx.appcompat.view.menu.MenuBuilder
    public boolean isQwertyMode() {
        return this.mParentMenu.isQwertyMode();
    }

    @Override  // androidx.appcompat.view.menu.MenuBuilder
    public boolean isShortcutsVisible() {
        return this.mParentMenu.isShortcutsVisible();
    }

    @Override  // androidx.appcompat.view.menu.MenuBuilder
    public void setCallback(Callback menuBuilder$Callback0) {
        this.mParentMenu.setCallback(menuBuilder$Callback0);
    }

    @Override  // androidx.appcompat.view.menu.MenuBuilder, android.view.Menu
    public void setGroupDividerEnabled(boolean z) {
        this.mParentMenu.setGroupDividerEnabled(z);
    }

    @Override  // android.view.SubMenu
    public SubMenu setHeaderIcon(int v) {
        return (SubMenu)super.setHeaderIconInt(v);
    }

    @Override  // android.view.SubMenu
    public SubMenu setHeaderIcon(Drawable drawable0) {
        return (SubMenu)super.setHeaderIconInt(drawable0);
    }

    @Override  // android.view.SubMenu
    public SubMenu setHeaderTitle(int v) {
        return (SubMenu)super.setHeaderTitleInt(v);
    }

    @Override  // android.view.SubMenu
    public SubMenu setHeaderTitle(CharSequence charSequence0) {
        return (SubMenu)super.setHeaderTitleInt(charSequence0);
    }

    @Override  // android.view.SubMenu
    public SubMenu setHeaderView(View view0) {
        return (SubMenu)super.setHeaderViewInt(view0);
    }

    @Override  // android.view.SubMenu
    public SubMenu setIcon(int v) {
        this.mItem.setIcon(v);
        return this;
    }

    @Override  // android.view.SubMenu
    public SubMenu setIcon(Drawable drawable0) {
        this.mItem.setIcon(drawable0);
        return this;
    }

    @Override  // androidx.appcompat.view.menu.MenuBuilder, android.view.Menu
    public void setQwertyMode(boolean z) {
        this.mParentMenu.setQwertyMode(z);
    }

    @Override  // androidx.appcompat.view.menu.MenuBuilder
    public void setShortcutsVisible(boolean z) {
        this.mParentMenu.setShortcutsVisible(z);
    }
}

