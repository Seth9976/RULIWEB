package androidx.appcompat.view;

import android.content.Context;
import android.view.ActionMode.Callback;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.view.menu.MenuItemWrapperICS;
import androidx.appcompat.view.menu.MenuWrapperICS;
import androidx.collection.SimpleArrayMap;
import androidx.core.internal.view.SupportMenu;
import androidx.core.internal.view.SupportMenuItem;
import java.util.ArrayList;

public class SupportActionModeWrapper extends ActionMode {
    public static class CallbackWrapper implements Callback {
        final ArrayList mActionModes;
        final Context mContext;
        final SimpleArrayMap mMenus;
        final ActionMode.Callback mWrappedCallback;

        public CallbackWrapper(Context context0, ActionMode.Callback actionMode$Callback0) {
            this.mContext = context0;
            this.mWrappedCallback = actionMode$Callback0;
            this.mActionModes = new ArrayList();
            this.mMenus = new SimpleArrayMap();
        }

        public ActionMode getActionModeWrapper(androidx.appcompat.view.ActionMode actionMode0) {
            int v = this.mActionModes.size();
            for(int v1 = 0; v1 < v; ++v1) {
                ActionMode actionMode1 = (SupportActionModeWrapper)this.mActionModes.get(v1);
                if(actionMode1 != null && actionMode1.mWrappedObject == actionMode0) {
                    return actionMode1;
                }
            }
            ActionMode actionMode2 = new SupportActionModeWrapper(this.mContext, actionMode0);
            this.mActionModes.add(actionMode2);
            return actionMode2;
        }

        private Menu getMenuWrapper(Menu menu0) {
            Menu menu1 = (Menu)this.mMenus.get(menu0);
            if(menu1 == null) {
                menu1 = new MenuWrapperICS(this.mContext, ((SupportMenu)menu0));
                this.mMenus.put(menu0, menu1);
            }
            return menu1;
        }

        @Override  // androidx.appcompat.view.ActionMode$Callback
        public boolean onActionItemClicked(androidx.appcompat.view.ActionMode actionMode0, MenuItem menuItem0) {
            ActionMode actionMode1 = this.getActionModeWrapper(actionMode0);
            MenuItemWrapperICS menuItemWrapperICS0 = new MenuItemWrapperICS(this.mContext, ((SupportMenuItem)menuItem0));
            return this.mWrappedCallback.onActionItemClicked(actionMode1, menuItemWrapperICS0);
        }

        @Override  // androidx.appcompat.view.ActionMode$Callback
        public boolean onCreateActionMode(androidx.appcompat.view.ActionMode actionMode0, Menu menu0) {
            ActionMode actionMode1 = this.getActionModeWrapper(actionMode0);
            Menu menu1 = this.getMenuWrapper(menu0);
            return this.mWrappedCallback.onCreateActionMode(actionMode1, menu1);
        }

        @Override  // androidx.appcompat.view.ActionMode$Callback
        public void onDestroyActionMode(androidx.appcompat.view.ActionMode actionMode0) {
            ActionMode actionMode1 = this.getActionModeWrapper(actionMode0);
            this.mWrappedCallback.onDestroyActionMode(actionMode1);
        }

        @Override  // androidx.appcompat.view.ActionMode$Callback
        public boolean onPrepareActionMode(androidx.appcompat.view.ActionMode actionMode0, Menu menu0) {
            ActionMode actionMode1 = this.getActionModeWrapper(actionMode0);
            Menu menu1 = this.getMenuWrapper(menu0);
            return this.mWrappedCallback.onPrepareActionMode(actionMode1, menu1);
        }
    }

    final Context mContext;
    final androidx.appcompat.view.ActionMode mWrappedObject;

    public SupportActionModeWrapper(Context context0, androidx.appcompat.view.ActionMode actionMode0) {
        this.mContext = context0;
        this.mWrappedObject = actionMode0;
    }

    @Override  // android.view.ActionMode
    public void finish() {
        this.mWrappedObject.finish();
    }

    @Override  // android.view.ActionMode
    public View getCustomView() {
        return this.mWrappedObject.getCustomView();
    }

    @Override  // android.view.ActionMode
    public Menu getMenu() {
        SupportMenu supportMenu0 = (SupportMenu)this.mWrappedObject.getMenu();
        return new MenuWrapperICS(this.mContext, supportMenu0);
    }

    @Override  // android.view.ActionMode
    public MenuInflater getMenuInflater() {
        return this.mWrappedObject.getMenuInflater();
    }

    @Override  // android.view.ActionMode
    public CharSequence getSubtitle() {
        return this.mWrappedObject.getSubtitle();
    }

    @Override  // android.view.ActionMode
    public Object getTag() {
        return this.mWrappedObject.getTag();
    }

    @Override  // android.view.ActionMode
    public CharSequence getTitle() {
        return this.mWrappedObject.getTitle();
    }

    @Override  // android.view.ActionMode
    public boolean getTitleOptionalHint() {
        return this.mWrappedObject.getTitleOptionalHint();
    }

    @Override  // android.view.ActionMode
    public void invalidate() {
        this.mWrappedObject.invalidate();
    }

    @Override  // android.view.ActionMode
    public boolean isTitleOptional() {
        return this.mWrappedObject.isTitleOptional();
    }

    @Override  // android.view.ActionMode
    public void setCustomView(View view0) {
        this.mWrappedObject.setCustomView(view0);
    }

    @Override  // android.view.ActionMode
    public void setSubtitle(int v) {
        this.mWrappedObject.setSubtitle(v);
    }

    @Override  // android.view.ActionMode
    public void setSubtitle(CharSequence charSequence0) {
        this.mWrappedObject.setSubtitle(charSequence0);
    }

    @Override  // android.view.ActionMode
    public void setTag(Object object0) {
        this.mWrappedObject.setTag(object0);
    }

    @Override  // android.view.ActionMode
    public void setTitle(int v) {
        this.mWrappedObject.setTitle(v);
    }

    @Override  // android.view.ActionMode
    public void setTitle(CharSequence charSequence0) {
        this.mWrappedObject.setTitle(charSequence0);
    }

    @Override  // android.view.ActionMode
    public void setTitleOptionalHint(boolean z) {
        this.mWrappedObject.setTitleOptionalHint(z);
    }
}

