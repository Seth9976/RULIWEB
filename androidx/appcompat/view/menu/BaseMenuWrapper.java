package androidx.appcompat.view.menu;

import android.content.Context;
import android.view.MenuItem;
import android.view.SubMenu;
import androidx.collection.SimpleArrayMap;
import androidx.core.internal.view.SupportMenuItem;
import androidx.core.internal.view.SupportSubMenu;

abstract class BaseMenuWrapper {
    final Context mContext;
    private SimpleArrayMap mMenuItems;
    private SimpleArrayMap mSubMenus;

    BaseMenuWrapper(Context context0) {
        this.mContext = context0;
    }

    final MenuItem getMenuItemWrapper(MenuItem menuItem0) {
        if(menuItem0 instanceof SupportMenuItem) {
            if(this.mMenuItems == null) {
                this.mMenuItems = new SimpleArrayMap();
            }
            MenuItem menuItem1 = (MenuItem)this.mMenuItems.get(((SupportMenuItem)menuItem0));
            if(menuItem1 == null) {
                menuItem1 = new MenuItemWrapperICS(this.mContext, ((SupportMenuItem)menuItem0));
                this.mMenuItems.put(((SupportMenuItem)menuItem0), menuItem1);
            }
            return menuItem1;
        }
        return menuItem0;
    }

    final SubMenu getSubMenuWrapper(SubMenu subMenu0) {
        if(subMenu0 instanceof SupportSubMenu) {
            if(this.mSubMenus == null) {
                this.mSubMenus = new SimpleArrayMap();
            }
            SubMenu subMenu1 = (SubMenu)this.mSubMenus.get(((SupportSubMenu)subMenu0));
            if(subMenu1 == null) {
                subMenu1 = new SubMenuWrapperICS(this.mContext, ((SupportSubMenu)subMenu0));
                this.mSubMenus.put(((SupportSubMenu)subMenu0), subMenu1);
            }
            return subMenu1;
        }
        return subMenu0;
    }

    final void internalClear() {
        SimpleArrayMap simpleArrayMap0 = this.mMenuItems;
        if(simpleArrayMap0 != null) {
            simpleArrayMap0.clear();
        }
        SimpleArrayMap simpleArrayMap1 = this.mSubMenus;
        if(simpleArrayMap1 != null) {
            simpleArrayMap1.clear();
        }
    }

    final void internalRemoveGroup(int v) {
        if(this.mMenuItems != null) {
            for(int v1 = 0; v1 < this.mMenuItems.size(); ++v1) {
                if(((SupportMenuItem)this.mMenuItems.keyAt(v1)).getGroupId() == v) {
                    this.mMenuItems.removeAt(v1);
                    --v1;
                }
            }
        }
    }

    final void internalRemoveItem(int v) {
        if(this.mMenuItems != null) {
            for(int v1 = 0; v1 < this.mMenuItems.size(); ++v1) {
                if(((SupportMenuItem)this.mMenuItems.keyAt(v1)).getItemId() == v) {
                    this.mMenuItems.removeAt(v1);
                    return;
                }
            }
        }
    }
}

