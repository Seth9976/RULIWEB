package androidx.appcompat.view.menu;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import androidx.core.internal.view.SupportMenu;

public class MenuWrapperICS extends BaseMenuWrapper implements Menu {
    private final SupportMenu mWrappedObject;

    public MenuWrapperICS(Context context0, SupportMenu supportMenu0) {
        super(context0);
        if(supportMenu0 == null) {
            throw new IllegalArgumentException("Wrapped Object can not be null.");
        }
        this.mWrappedObject = supportMenu0;
    }

    @Override  // android.view.Menu
    public MenuItem add(int v) {
        return this.getMenuItemWrapper(this.mWrappedObject.add(v));
    }

    @Override  // android.view.Menu
    public MenuItem add(int v, int v1, int v2, int v3) {
        return this.getMenuItemWrapper(this.mWrappedObject.add(v, v1, v2, v3));
    }

    @Override  // android.view.Menu
    public MenuItem add(int v, int v1, int v2, CharSequence charSequence0) {
        return this.getMenuItemWrapper(this.mWrappedObject.add(v, v1, v2, charSequence0));
    }

    @Override  // android.view.Menu
    public MenuItem add(CharSequence charSequence0) {
        return this.getMenuItemWrapper(this.mWrappedObject.add(charSequence0));
    }

    @Override  // android.view.Menu
    public int addIntentOptions(int v, int v1, int v2, ComponentName componentName0, Intent[] arr_intent, Intent intent0, int v3, MenuItem[] arr_menuItem) {
        MenuItem[] arr_menuItem1 = arr_menuItem == null ? null : new MenuItem[arr_menuItem.length];
        int v4 = this.mWrappedObject.addIntentOptions(v, v1, v2, componentName0, arr_intent, intent0, v3, arr_menuItem1);
        if(arr_menuItem1 != null) {
            for(int v5 = 0; v5 < arr_menuItem1.length; ++v5) {
                arr_menuItem[v5] = this.getMenuItemWrapper(arr_menuItem1[v5]);
            }
        }
        return v4;
    }

    @Override  // android.view.Menu
    public SubMenu addSubMenu(int v) {
        return this.getSubMenuWrapper(this.mWrappedObject.addSubMenu(v));
    }

    @Override  // android.view.Menu
    public SubMenu addSubMenu(int v, int v1, int v2, int v3) {
        return this.getSubMenuWrapper(this.mWrappedObject.addSubMenu(v, v1, v2, v3));
    }

    @Override  // android.view.Menu
    public SubMenu addSubMenu(int v, int v1, int v2, CharSequence charSequence0) {
        return this.getSubMenuWrapper(this.mWrappedObject.addSubMenu(v, v1, v2, charSequence0));
    }

    @Override  // android.view.Menu
    public SubMenu addSubMenu(CharSequence charSequence0) {
        return this.getSubMenuWrapper(this.mWrappedObject.addSubMenu(charSequence0));
    }

    @Override  // android.view.Menu
    public void clear() {
        this.internalClear();
        this.mWrappedObject.clear();
    }

    @Override  // android.view.Menu
    public void close() {
        this.mWrappedObject.close();
    }

    @Override  // android.view.Menu
    public MenuItem findItem(int v) {
        return this.getMenuItemWrapper(this.mWrappedObject.findItem(v));
    }

    @Override  // android.view.Menu
    public MenuItem getItem(int v) {
        return this.getMenuItemWrapper(this.mWrappedObject.getItem(v));
    }

    @Override  // android.view.Menu
    public boolean hasVisibleItems() {
        return this.mWrappedObject.hasVisibleItems();
    }

    @Override  // android.view.Menu
    public boolean isShortcutKey(int v, KeyEvent keyEvent0) {
        return this.mWrappedObject.isShortcutKey(v, keyEvent0);
    }

    @Override  // android.view.Menu
    public boolean performIdentifierAction(int v, int v1) {
        return this.mWrappedObject.performIdentifierAction(v, v1);
    }

    @Override  // android.view.Menu
    public boolean performShortcut(int v, KeyEvent keyEvent0, int v1) {
        return this.mWrappedObject.performShortcut(v, keyEvent0, v1);
    }

    @Override  // android.view.Menu
    public void removeGroup(int v) {
        this.internalRemoveGroup(v);
        this.mWrappedObject.removeGroup(v);
    }

    @Override  // android.view.Menu
    public void removeItem(int v) {
        this.internalRemoveItem(v);
        this.mWrappedObject.removeItem(v);
    }

    @Override  // android.view.Menu
    public void setGroupCheckable(int v, boolean z, boolean z1) {
        this.mWrappedObject.setGroupCheckable(v, z, z1);
    }

    @Override  // android.view.Menu
    public void setGroupEnabled(int v, boolean z) {
        this.mWrappedObject.setGroupEnabled(v, z);
    }

    @Override  // android.view.Menu
    public void setGroupVisible(int v, boolean z) {
        this.mWrappedObject.setGroupVisible(v, z);
    }

    @Override  // android.view.Menu
    public void setQwertyMode(boolean z) {
        this.mWrappedObject.setQwertyMode(z);
    }

    @Override  // android.view.Menu
    public int size() {
        return this.mWrappedObject.size();
    }
}

