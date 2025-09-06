package com.google.android.material.navigation;

import android.content.Context;
import android.view.MenuItem;
import android.view.SubMenu;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;

public final class NavigationBarMenu extends MenuBuilder {
    private final int maxItemCount;
    private final Class viewClass;

    public NavigationBarMenu(Context context0, Class class0, int v) {
        super(context0);
        this.viewClass = class0;
        this.maxItemCount = v;
    }

    @Override  // androidx.appcompat.view.menu.MenuBuilder
    protected MenuItem addInternal(int v, int v1, int v2, CharSequence charSequence0) {
        if(this.size() + 1 <= this.maxItemCount) {
            this.stopDispatchingItemsChanged();
            MenuItem menuItem0 = super.addInternal(v, v1, v2, charSequence0);
            if(menuItem0 instanceof MenuItemImpl) {
                ((MenuItemImpl)menuItem0).setExclusiveCheckable(true);
            }
            this.startDispatchingItemsChanged();
            return menuItem0;
        }
        String s = this.viewClass.getSimpleName();
        throw new IllegalArgumentException("Maximum number of items supported by " + s + " is " + this.maxItemCount + ". Limit can be checked with " + s + "#getMaxItemCount()");
    }

    @Override  // androidx.appcompat.view.menu.MenuBuilder
    public SubMenu addSubMenu(int v, int v1, int v2, CharSequence charSequence0) {
        throw new UnsupportedOperationException(this.viewClass.getSimpleName() + " does not support submenus");
    }

    public int getMaxItemCount() {
        return this.maxItemCount;
    }
}

