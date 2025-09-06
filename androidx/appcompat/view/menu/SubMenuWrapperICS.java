package androidx.appcompat.view.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import androidx.core.internal.view.SupportSubMenu;

class SubMenuWrapperICS extends MenuWrapperICS implements SubMenu {
    private final SupportSubMenu mSubMenu;

    SubMenuWrapperICS(Context context0, SupportSubMenu supportSubMenu0) {
        super(context0, supportSubMenu0);
        this.mSubMenu = supportSubMenu0;
    }

    @Override  // android.view.SubMenu
    public void clearHeader() {
        this.mSubMenu.clearHeader();
    }

    @Override  // android.view.SubMenu
    public MenuItem getItem() {
        return this.getMenuItemWrapper(this.mSubMenu.getItem());
    }

    @Override  // android.view.SubMenu
    public SubMenu setHeaderIcon(int v) {
        this.mSubMenu.setHeaderIcon(v);
        return this;
    }

    @Override  // android.view.SubMenu
    public SubMenu setHeaderIcon(Drawable drawable0) {
        this.mSubMenu.setHeaderIcon(drawable0);
        return this;
    }

    @Override  // android.view.SubMenu
    public SubMenu setHeaderTitle(int v) {
        this.mSubMenu.setHeaderTitle(v);
        return this;
    }

    @Override  // android.view.SubMenu
    public SubMenu setHeaderTitle(CharSequence charSequence0) {
        this.mSubMenu.setHeaderTitle(charSequence0);
        return this;
    }

    @Override  // android.view.SubMenu
    public SubMenu setHeaderView(View view0) {
        this.mSubMenu.setHeaderView(view0);
        return this;
    }

    @Override  // android.view.SubMenu
    public SubMenu setIcon(int v) {
        this.mSubMenu.setIcon(v);
        return this;
    }

    @Override  // android.view.SubMenu
    public SubMenu setIcon(Drawable drawable0) {
        this.mSubMenu.setIcon(drawable0);
        return this;
    }
}

