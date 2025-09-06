package com.google.android.material.internal;

import android.content.Context;
import android.view.SubMenu;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.SubMenuBuilder;

public class NavigationMenu extends MenuBuilder {
    public NavigationMenu(Context context0) {
        super(context0);
    }

    @Override  // androidx.appcompat.view.menu.MenuBuilder
    public SubMenu addSubMenu(int v, int v1, int v2, CharSequence charSequence0) {
        MenuItemImpl menuItemImpl0 = (MenuItemImpl)this.addInternal(v, v1, v2, charSequence0);
        SubMenu subMenu0 = new NavigationSubMenu(this.getContext(), this, menuItemImpl0);
        menuItemImpl0.setSubMenu(((SubMenuBuilder)subMenu0));
        return subMenu0;
    }
}

