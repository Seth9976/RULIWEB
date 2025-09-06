package com.google.android.material.internal;

import android.content.Context;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.SubMenuBuilder;

public class NavigationSubMenu extends SubMenuBuilder {
    public NavigationSubMenu(Context context0, NavigationMenu navigationMenu0, MenuItemImpl menuItemImpl0) {
        super(context0, navigationMenu0, menuItemImpl0);
    }

    @Override  // androidx.appcompat.view.menu.MenuBuilder
    public void onItemsChanged(boolean z) {
        super.onItemsChanged(z);
        ((MenuBuilder)this.getParentMenu()).onItemsChanged(z);
    }
}

