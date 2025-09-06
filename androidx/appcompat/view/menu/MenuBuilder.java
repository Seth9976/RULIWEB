package androidx.appcompat.view.menu;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyCharacterMap.KeyData;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewConfiguration;
import androidx.core.content.ContextCompat;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.ActionProvider;
import androidx.core.view.ViewConfigurationCompat;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MenuBuilder implements SupportMenu {
    public interface Callback {
        boolean onMenuItemSelected(MenuBuilder arg1, MenuItem arg2);

        void onMenuModeChange(MenuBuilder arg1);
    }

    public interface ItemInvoker {
        boolean invokeItem(MenuItemImpl arg1);
    }

    private static final String ACTION_VIEW_STATES_KEY = "android:menu:actionviewstates";
    private static final String EXPANDED_ACTION_VIEW_ID = "android:menu:expandedactionview";
    private static final String PRESENTER_KEY = "android:menu:presenters";
    private static final String TAG = "MenuBuilder";
    private ArrayList mActionItems;
    private Callback mCallback;
    private final Context mContext;
    private ContextMenu.ContextMenuInfo mCurrentMenuInfo;
    private int mDefaultShowAsAction;
    private MenuItemImpl mExpandedItem;
    private boolean mGroupDividerEnabled;
    Drawable mHeaderIcon;
    CharSequence mHeaderTitle;
    View mHeaderView;
    private boolean mIsActionItemsStale;
    private boolean mIsClosing;
    private boolean mIsVisibleItemsStale;
    private ArrayList mItems;
    private boolean mItemsChangedWhileDispatchPrevented;
    private ArrayList mNonActionItems;
    private boolean mOptionalIconsVisible;
    private boolean mOverrideVisibleItems;
    private CopyOnWriteArrayList mPresenters;
    private boolean mPreventDispatchingItemsChanged;
    private boolean mQwertyMode;
    private final Resources mResources;
    private boolean mShortcutsVisible;
    private boolean mStructureChangedWhileDispatchPrevented;
    private ArrayList mTempShortcutItemList;
    private ArrayList mVisibleItems;
    private static final int[] sCategoryToOrder;

    static {
        MenuBuilder.sCategoryToOrder = new int[]{1, 4, 5, 3, 2, 0};
    }

    public MenuBuilder(Context context0) {
        this.mDefaultShowAsAction = 0;
        this.mPreventDispatchingItemsChanged = false;
        this.mItemsChangedWhileDispatchPrevented = false;
        this.mStructureChangedWhileDispatchPrevented = false;
        this.mOptionalIconsVisible = false;
        this.mIsClosing = false;
        this.mTempShortcutItemList = new ArrayList();
        this.mPresenters = new CopyOnWriteArrayList();
        this.mGroupDividerEnabled = false;
        this.mContext = context0;
        this.mResources = context0.getResources();
        this.mItems = new ArrayList();
        this.mVisibleItems = new ArrayList();
        this.mIsVisibleItemsStale = true;
        this.mActionItems = new ArrayList();
        this.mNonActionItems = new ArrayList();
        this.mIsActionItemsStale = true;
        this.setShortcutsVisibleInner(true);
    }

    @Override  // android.view.Menu
    public MenuItem add(int v) {
        return this.addInternal(0, 0, 0, this.mResources.getString(v));
    }

    @Override  // android.view.Menu
    public MenuItem add(int v, int v1, int v2, int v3) {
        return this.addInternal(v, v1, v2, this.mResources.getString(v3));
    }

    @Override  // android.view.Menu
    public MenuItem add(int v, int v1, int v2, CharSequence charSequence0) {
        return this.addInternal(v, v1, v2, charSequence0);
    }

    @Override  // android.view.Menu
    public MenuItem add(CharSequence charSequence0) {
        return this.addInternal(0, 0, 0, charSequence0);
    }

    @Override  // android.view.Menu
    public int addIntentOptions(int v, int v1, int v2, ComponentName componentName0, Intent[] arr_intent, Intent intent0, int v3, MenuItem[] arr_menuItem) {
        PackageManager packageManager0 = this.mContext.getPackageManager();
        List list0 = packageManager0.queryIntentActivityOptions(componentName0, arr_intent, intent0, 0);
        int v5 = list0 == null ? 0 : list0.size();
        if((v3 & 1) == 0) {
            this.removeGroup(v);
        }
        for(int v4 = 0; v4 < v5; ++v4) {
            ResolveInfo resolveInfo0 = (ResolveInfo)list0.get(v4);
            Intent intent1 = new Intent((resolveInfo0.specificIndex >= 0 ? arr_intent[resolveInfo0.specificIndex] : intent0));
            intent1.setComponent(new ComponentName(resolveInfo0.activityInfo.applicationInfo.packageName, resolveInfo0.activityInfo.name));
            MenuItem menuItem0 = this.add(v, v1, v2, resolveInfo0.loadLabel(packageManager0)).setIcon(resolveInfo0.loadIcon(packageManager0)).setIntent(intent1);
            if(arr_menuItem != null && resolveInfo0.specificIndex >= 0) {
                arr_menuItem[resolveInfo0.specificIndex] = menuItem0;
            }
        }
        return v5;
    }

    protected MenuItem addInternal(int v, int v1, int v2, CharSequence charSequence0) {
        int v3 = MenuBuilder.getOrdering(v2);
        MenuItem menuItem0 = this.createNewMenuItem(v, v1, v2, v3, charSequence0, this.mDefaultShowAsAction);
        ContextMenu.ContextMenuInfo contextMenu$ContextMenuInfo0 = this.mCurrentMenuInfo;
        if(contextMenu$ContextMenuInfo0 != null) {
            ((MenuItemImpl)menuItem0).setMenuInfo(contextMenu$ContextMenuInfo0);
        }
        this.mItems.add(MenuBuilder.findInsertIndex(this.mItems, v3), menuItem0);
        this.onItemsChanged(true);
        return menuItem0;
    }

    public void addMenuPresenter(MenuPresenter menuPresenter0) {
        this.addMenuPresenter(menuPresenter0, this.mContext);
    }

    public void addMenuPresenter(MenuPresenter menuPresenter0, Context context0) {
        this.mPresenters.add(new WeakReference(menuPresenter0));
        menuPresenter0.initForMenu(context0, this);
        this.mIsActionItemsStale = true;
    }

    @Override  // android.view.Menu
    public SubMenu addSubMenu(int v) {
        return this.addSubMenu(0, 0, 0, this.mResources.getString(v));
    }

    @Override  // android.view.Menu
    public SubMenu addSubMenu(int v, int v1, int v2, int v3) {
        return this.addSubMenu(v, v1, v2, this.mResources.getString(v3));
    }

    @Override  // android.view.Menu
    public SubMenu addSubMenu(int v, int v1, int v2, CharSequence charSequence0) {
        MenuItemImpl menuItemImpl0 = (MenuItemImpl)this.addInternal(v, v1, v2, charSequence0);
        SubMenu subMenu0 = new SubMenuBuilder(this.mContext, this, menuItemImpl0);
        menuItemImpl0.setSubMenu(((SubMenuBuilder)subMenu0));
        return subMenu0;
    }

    @Override  // android.view.Menu
    public SubMenu addSubMenu(CharSequence charSequence0) {
        return this.addSubMenu(0, 0, 0, charSequence0);
    }

    public void changeMenuMode() {
        Callback menuBuilder$Callback0 = this.mCallback;
        if(menuBuilder$Callback0 != null) {
            menuBuilder$Callback0.onMenuModeChange(this);
        }
    }

    @Override  // android.view.Menu
    public void clear() {
        MenuItemImpl menuItemImpl0 = this.mExpandedItem;
        if(menuItemImpl0 != null) {
            this.collapseItemActionView(menuItemImpl0);
        }
        this.mItems.clear();
        this.onItemsChanged(true);
    }

    public void clearAll() {
        this.mPreventDispatchingItemsChanged = true;
        this.clear();
        this.clearHeader();
        this.mPresenters.clear();
        this.mPreventDispatchingItemsChanged = false;
        this.mItemsChangedWhileDispatchPrevented = false;
        this.mStructureChangedWhileDispatchPrevented = false;
        this.onItemsChanged(true);
    }

    public void clearHeader() {
        this.mHeaderIcon = null;
        this.mHeaderTitle = null;
        this.mHeaderView = null;
        this.onItemsChanged(false);
    }

    @Override  // android.view.Menu
    public void close() {
        this.close(true);
    }

    public final void close(boolean z) {
        if(this.mIsClosing) {
            return;
        }
        this.mIsClosing = true;
        for(Object object0: this.mPresenters) {
            WeakReference weakReference0 = (WeakReference)object0;
            MenuPresenter menuPresenter0 = (MenuPresenter)weakReference0.get();
            if(menuPresenter0 == null) {
                this.mPresenters.remove(weakReference0);
            }
            else {
                menuPresenter0.onCloseMenu(this, z);
            }
        }
        this.mIsClosing = false;
    }

    public boolean collapseItemActionView(MenuItemImpl menuItemImpl0) {
        boolean z = false;
        if(!this.mPresenters.isEmpty() && this.mExpandedItem == menuItemImpl0) {
            this.stopDispatchingItemsChanged();
            for(Object object0: this.mPresenters) {
                WeakReference weakReference0 = (WeakReference)object0;
                MenuPresenter menuPresenter0 = (MenuPresenter)weakReference0.get();
                if(menuPresenter0 == null) {
                    this.mPresenters.remove(weakReference0);
                }
                else {
                    z = menuPresenter0.collapseItemActionView(this, menuItemImpl0);
                    if(z) {
                        break;
                    }
                }
            }
            this.startDispatchingItemsChanged();
            if(z) {
                this.mExpandedItem = null;
            }
        }
        return z;
    }

    private MenuItemImpl createNewMenuItem(int v, int v1, int v2, int v3, CharSequence charSequence0, int v4) {
        return new MenuItemImpl(this, v, v1, v2, v3, charSequence0, v4);
    }

    boolean dispatchMenuItemSelected(MenuBuilder menuBuilder0, MenuItem menuItem0) {
        return this.mCallback != null && this.mCallback.onMenuItemSelected(menuBuilder0, menuItem0);
    }

    private void dispatchPresenterUpdate(boolean z) {
        if(this.mPresenters.isEmpty()) {
            return;
        }
        this.stopDispatchingItemsChanged();
        for(Object object0: this.mPresenters) {
            WeakReference weakReference0 = (WeakReference)object0;
            MenuPresenter menuPresenter0 = (MenuPresenter)weakReference0.get();
            if(menuPresenter0 == null) {
                this.mPresenters.remove(weakReference0);
            }
            else {
                menuPresenter0.updateMenuView(z);
            }
        }
        this.startDispatchingItemsChanged();
    }

    private void dispatchRestoreInstanceState(Bundle bundle0) {
        SparseArray sparseArray0 = bundle0.getSparseParcelableArray("android:menu:presenters");
        if(sparseArray0 != null && !this.mPresenters.isEmpty()) {
            for(Object object0: this.mPresenters) {
                WeakReference weakReference0 = (WeakReference)object0;
                MenuPresenter menuPresenter0 = (MenuPresenter)weakReference0.get();
                if(menuPresenter0 == null) {
                    this.mPresenters.remove(weakReference0);
                }
                else {
                    int v = menuPresenter0.getId();
                    if(v > 0) {
                        Parcelable parcelable0 = (Parcelable)sparseArray0.get(v);
                        if(parcelable0 != null) {
                            menuPresenter0.onRestoreInstanceState(parcelable0);
                        }
                    }
                }
            }
        }
    }

    private void dispatchSaveInstanceState(Bundle bundle0) {
        if(this.mPresenters.isEmpty()) {
            return;
        }
        SparseArray sparseArray0 = new SparseArray();
        for(Object object0: this.mPresenters) {
            WeakReference weakReference0 = (WeakReference)object0;
            MenuPresenter menuPresenter0 = (MenuPresenter)weakReference0.get();
            if(menuPresenter0 == null) {
                this.mPresenters.remove(weakReference0);
            }
            else {
                int v = menuPresenter0.getId();
                if(v > 0) {
                    Parcelable parcelable0 = menuPresenter0.onSaveInstanceState();
                    if(parcelable0 != null) {
                        sparseArray0.put(v, parcelable0);
                    }
                }
            }
        }
        bundle0.putSparseParcelableArray("android:menu:presenters", sparseArray0);
    }

    private boolean dispatchSubMenuSelected(SubMenuBuilder subMenuBuilder0, MenuPresenter menuPresenter0) {
        boolean z = false;
        if(this.mPresenters.isEmpty()) {
            return false;
        }
        if(menuPresenter0 != null) {
            z = menuPresenter0.onSubMenuSelected(subMenuBuilder0);
        }
        for(Object object0: this.mPresenters) {
            WeakReference weakReference0 = (WeakReference)object0;
            MenuPresenter menuPresenter1 = (MenuPresenter)weakReference0.get();
            if(menuPresenter1 == null) {
                this.mPresenters.remove(weakReference0);
            }
            else if(!z) {
                z = menuPresenter1.onSubMenuSelected(subMenuBuilder0);
            }
        }
        return z;
    }

    public boolean expandItemActionView(MenuItemImpl menuItemImpl0) {
        boolean z = false;
        if(this.mPresenters.isEmpty()) {
            return false;
        }
        this.stopDispatchingItemsChanged();
        for(Object object0: this.mPresenters) {
            WeakReference weakReference0 = (WeakReference)object0;
            MenuPresenter menuPresenter0 = (MenuPresenter)weakReference0.get();
            if(menuPresenter0 == null) {
                this.mPresenters.remove(weakReference0);
            }
            else {
                z = menuPresenter0.expandItemActionView(this, menuItemImpl0);
                if(z) {
                    break;
                }
            }
        }
        this.startDispatchingItemsChanged();
        if(z) {
            this.mExpandedItem = menuItemImpl0;
        }
        return z;
    }

    public int findGroupIndex(int v) {
        return this.findGroupIndex(v, 0);
    }

    public int findGroupIndex(int v, int v1) {
        int v2 = this.size();
        if(v1 < 0) {
            v1 = 0;
        }
        while(v1 < v2) {
            if(((MenuItemImpl)this.mItems.get(v1)).getGroupId() == v) {
                return v1;
            }
            ++v1;
        }
        return -1;
    }

    private static int findInsertIndex(ArrayList arrayList0, int v) {
        for(int v1 = arrayList0.size() - 1; v1 >= 0; --v1) {
            if(((MenuItemImpl)arrayList0.get(v1)).getOrdering() <= v) {
                return v1 + 1;
            }
        }
        return 0;
    }

    @Override  // android.view.Menu
    public MenuItem findItem(int v) {
        int v1 = this.size();
        for(int v2 = 0; v2 < v1; ++v2) {
            MenuItem menuItem0 = (MenuItemImpl)this.mItems.get(v2);
            if(((MenuItemImpl)menuItem0).getItemId() == v) {
                return menuItem0;
            }
            if(((MenuItemImpl)menuItem0).hasSubMenu()) {
                MenuItem menuItem1 = ((MenuItemImpl)menuItem0).getSubMenu().findItem(v);
                if(menuItem1 != null) {
                    return menuItem1;
                }
            }
        }
        return null;
    }

    public int findItemIndex(int v) {
        int v1 = this.size();
        for(int v2 = 0; v2 < v1; ++v2) {
            if(((MenuItemImpl)this.mItems.get(v2)).getItemId() == v) {
                return v2;
            }
        }
        return -1;
    }

    MenuItemImpl findItemWithShortcutForKey(int v, KeyEvent keyEvent0) {
        ArrayList arrayList0 = this.mTempShortcutItemList;
        arrayList0.clear();
        this.findItemsWithShortcutForKey(arrayList0, v, keyEvent0);
        if(arrayList0.isEmpty()) {
            return null;
        }
        int v1 = keyEvent0.getMetaState();
        KeyCharacterMap.KeyData keyCharacterMap$KeyData0 = new KeyCharacterMap.KeyData();
        keyEvent0.getKeyData(keyCharacterMap$KeyData0);
        int v2 = arrayList0.size();
        if(v2 == 1) {
            return (MenuItemImpl)arrayList0.get(0);
        }
        boolean z = this.isQwertyMode();
        for(int v3 = 0; v3 < v2; ++v3) {
            MenuItemImpl menuItemImpl0 = (MenuItemImpl)arrayList0.get(v3);
            int v4 = z ? menuItemImpl0.getAlphabeticShortcut() : menuItemImpl0.getNumericShortcut();
            if(v4 == keyCharacterMap$KeyData0.meta[0] && (v1 & 2) == 0 || v4 == keyCharacterMap$KeyData0.meta[2] && (v1 & 2) != 0 || z && v4 == 8 && v == 67) {
                return menuItemImpl0;
            }
        }
        return null;
    }

    void findItemsWithShortcutForKey(List list0, int v, KeyEvent keyEvent0) {
        boolean z = this.isQwertyMode();
        int v1 = keyEvent0.getModifiers();
        KeyCharacterMap.KeyData keyCharacterMap$KeyData0 = new KeyCharacterMap.KeyData();
        if(keyEvent0.getKeyData(keyCharacterMap$KeyData0) || v == 67) {
            int v2 = this.mItems.size();
            for(int v3 = 0; v3 < v2; ++v3) {
                MenuItemImpl menuItemImpl0 = (MenuItemImpl)this.mItems.get(v3);
                if(menuItemImpl0.hasSubMenu()) {
                    ((MenuBuilder)menuItemImpl0.getSubMenu()).findItemsWithShortcutForKey(list0, v, keyEvent0);
                }
                int v4 = z ? menuItemImpl0.getAlphabeticShortcut() : menuItemImpl0.getNumericShortcut();
                if((v1 & 0x1100F) == ((z ? menuItemImpl0.getAlphabeticModifiers() : menuItemImpl0.getNumericModifiers()) & 0x1100F) && v4 != 0 && (v4 == keyCharacterMap$KeyData0.meta[0] || v4 == keyCharacterMap$KeyData0.meta[2] || z && v4 == 8 && v == 67) && menuItemImpl0.isEnabled()) {
                    list0.add(menuItemImpl0);
                }
            }
        }
    }

    public void flagActionItems() {
        ArrayList arrayList0 = this.getVisibleItems();
        if(!this.mIsActionItemsStale) {
            return;
        }
        boolean z = false;
        for(Object object0: this.mPresenters) {
            WeakReference weakReference0 = (WeakReference)object0;
            MenuPresenter menuPresenter0 = (MenuPresenter)weakReference0.get();
            if(menuPresenter0 == null) {
                this.mPresenters.remove(weakReference0);
            }
            else {
                z |= menuPresenter0.flagActionItems();
            }
        }
        if(z) {
            this.mActionItems.clear();
            this.mNonActionItems.clear();
            int v = arrayList0.size();
            for(int v1 = 0; v1 < v; ++v1) {
                MenuItemImpl menuItemImpl0 = (MenuItemImpl)arrayList0.get(v1);
                if(menuItemImpl0.isActionButton()) {
                    this.mActionItems.add(menuItemImpl0);
                }
                else {
                    this.mNonActionItems.add(menuItemImpl0);
                }
            }
        }
        else {
            this.mActionItems.clear();
            this.mNonActionItems.clear();
            this.mNonActionItems.addAll(this.getVisibleItems());
        }
        this.mIsActionItemsStale = false;
    }

    public ArrayList getActionItems() {
        this.flagActionItems();
        return this.mActionItems;
    }

    protected String getActionViewStatesKey() [...] // 潜在的解密器

    public Context getContext() {
        return this.mContext;
    }

    public MenuItemImpl getExpandedItem() {
        return this.mExpandedItem;
    }

    public Drawable getHeaderIcon() {
        return this.mHeaderIcon;
    }

    public CharSequence getHeaderTitle() {
        return this.mHeaderTitle;
    }

    public View getHeaderView() {
        return this.mHeaderView;
    }

    @Override  // android.view.Menu
    public MenuItem getItem(int v) {
        return (MenuItem)this.mItems.get(v);
    }

    public ArrayList getNonActionItems() {
        this.flagActionItems();
        return this.mNonActionItems;
    }

    boolean getOptionalIconsVisible() {
        return this.mOptionalIconsVisible;
    }

    private static int getOrdering(int v) {
        int v1 = (0xFFFF0000 & v) >> 16;
        if(v1 >= 0) {
            int[] arr_v = MenuBuilder.sCategoryToOrder;
            if(v1 < arr_v.length) {
                return v & 0xFFFF | arr_v[v1] << 16;
            }
        }
        throw new IllegalArgumentException("order does not contain a valid category.");
    }

    Resources getResources() {
        return this.mResources;
    }

    public MenuBuilder getRootMenu() {
        return this;
    }

    public ArrayList getVisibleItems() {
        if(!this.mIsVisibleItemsStale) {
            return this.mVisibleItems;
        }
        this.mVisibleItems.clear();
        int v = this.mItems.size();
        for(int v1 = 0; v1 < v; ++v1) {
            MenuItemImpl menuItemImpl0 = (MenuItemImpl)this.mItems.get(v1);
            if(menuItemImpl0.isVisible()) {
                this.mVisibleItems.add(menuItemImpl0);
            }
        }
        this.mIsVisibleItemsStale = false;
        this.mIsActionItemsStale = true;
        return this.mVisibleItems;
    }

    @Override  // android.view.Menu
    public boolean hasVisibleItems() {
        if(this.mOverrideVisibleItems) {
            return true;
        }
        int v = this.size();
        for(int v1 = 0; v1 < v; ++v1) {
            if(((MenuItemImpl)this.mItems.get(v1)).isVisible()) {
                return true;
            }
        }
        return false;
    }

    public boolean isDispatchingItemsChanged() {
        return !this.mPreventDispatchingItemsChanged;
    }

    public boolean isGroupDividerEnabled() {
        return this.mGroupDividerEnabled;
    }

    boolean isQwertyMode() {
        return this.mQwertyMode;
    }

    @Override  // android.view.Menu
    public boolean isShortcutKey(int v, KeyEvent keyEvent0) {
        return this.findItemWithShortcutForKey(v, keyEvent0) != null;
    }

    public boolean isShortcutsVisible() {
        return this.mShortcutsVisible;
    }

    void onItemActionRequestChanged(MenuItemImpl menuItemImpl0) {
        this.mIsActionItemsStale = true;
        this.onItemsChanged(true);
    }

    void onItemVisibleChanged(MenuItemImpl menuItemImpl0) {
        this.mIsVisibleItemsStale = true;
        this.onItemsChanged(true);
    }

    public void onItemsChanged(boolean z) {
        if(!this.mPreventDispatchingItemsChanged) {
            if(z) {
                this.mIsVisibleItemsStale = true;
                this.mIsActionItemsStale = true;
            }
            this.dispatchPresenterUpdate(z);
            return;
        }
        this.mItemsChangedWhileDispatchPrevented = true;
        if(z) {
            this.mStructureChangedWhileDispatchPrevented = true;
        }
    }

    @Override  // android.view.Menu
    public boolean performIdentifierAction(int v, int v1) {
        return this.performItemAction(this.findItem(v), v1);
    }

    public boolean performItemAction(MenuItem menuItem0, int v) {
        return this.performItemAction(menuItem0, null, v);
    }

    public boolean performItemAction(MenuItem menuItem0, MenuPresenter menuPresenter0, int v) {
        if(((MenuItemImpl)menuItem0) != null && ((MenuItemImpl)menuItem0).isEnabled()) {
            boolean z = ((MenuItemImpl)menuItem0).invoke();
            ActionProvider actionProvider0 = ((MenuItemImpl)menuItem0).getSupportActionProvider();
            boolean z1 = actionProvider0 != null && actionProvider0.hasSubMenu();
            if(((MenuItemImpl)menuItem0).hasCollapsibleActionView()) {
                boolean z2 = ((MenuItemImpl)menuItem0).expandActionView() | z;
                if(z2) {
                    this.close(true);
                }
                return z2;
            }
            if(!((MenuItemImpl)menuItem0).hasSubMenu() && !z1) {
                if((v & 1) == 0) {
                    this.close(true);
                }
                return z;
            }
            if((v & 4) == 0) {
                this.close(false);
            }
            if(!((MenuItemImpl)menuItem0).hasSubMenu()) {
                ((MenuItemImpl)menuItem0).setSubMenu(new SubMenuBuilder(this.getContext(), this, ((MenuItemImpl)menuItem0)));
            }
            SubMenuBuilder subMenuBuilder0 = (SubMenuBuilder)((MenuItemImpl)menuItem0).getSubMenu();
            if(z1) {
                actionProvider0.onPrepareSubMenu(subMenuBuilder0);
            }
            boolean z3 = this.dispatchSubMenuSelected(subMenuBuilder0, menuPresenter0) | z;
            if(!z3) {
                this.close(true);
            }
            return z3;
        }
        return false;
    }

    @Override  // android.view.Menu
    public boolean performShortcut(int v, KeyEvent keyEvent0, int v1) {
        MenuItemImpl menuItemImpl0 = this.findItemWithShortcutForKey(v, keyEvent0);
        boolean z = menuItemImpl0 == null ? false : this.performItemAction(menuItemImpl0, v1);
        if((v1 & 2) != 0) {
            this.close(true);
        }
        return z;
    }

    @Override  // android.view.Menu
    public void removeGroup(int v) {
        int v1 = this.findGroupIndex(v);
        if(v1 >= 0) {
            int v2 = this.mItems.size();
            for(int v3 = 0; v3 < v2 - v1 && ((MenuItemImpl)this.mItems.get(v1)).getGroupId() == v; ++v3) {
                this.removeItemAtInt(v1, false);
            }
            this.onItemsChanged(true);
        }
    }

    @Override  // android.view.Menu
    public void removeItem(int v) {
        this.removeItemAtInt(this.findItemIndex(v), true);
    }

    public void removeItemAt(int v) {
        this.removeItemAtInt(v, true);
    }

    private void removeItemAtInt(int v, boolean z) {
        if(v >= 0 && v < this.mItems.size()) {
            this.mItems.remove(v);
            if(z) {
                this.onItemsChanged(true);
            }
        }
    }

    public void removeMenuPresenter(MenuPresenter menuPresenter0) {
        for(Object object0: this.mPresenters) {
            WeakReference weakReference0 = (WeakReference)object0;
            MenuPresenter menuPresenter1 = (MenuPresenter)weakReference0.get();
            if(menuPresenter1 == null || menuPresenter1 == menuPresenter0) {
                this.mPresenters.remove(weakReference0);
            }
        }
    }

    public void restoreActionViewStates(Bundle bundle0) {
        if(bundle0 != null) {
            SparseArray sparseArray0 = bundle0.getSparseParcelableArray("android:menu:actionviewstates");
            int v = this.size();
            for(int v1 = 0; v1 < v; ++v1) {
                MenuItem menuItem0 = this.getItem(v1);
                View view0 = menuItem0.getActionView();
                if(view0 != null && view0.getId() != -1) {
                    view0.restoreHierarchyState(sparseArray0);
                }
                if(menuItem0.hasSubMenu()) {
                    ((SubMenuBuilder)menuItem0.getSubMenu()).restoreActionViewStates(bundle0);
                }
            }
            int v2 = bundle0.getInt("android:menu:expandedactionview");
            if(v2 > 0) {
                MenuItem menuItem1 = this.findItem(v2);
                if(menuItem1 != null) {
                    menuItem1.expandActionView();
                }
            }
        }
    }

    public void restorePresenterStates(Bundle bundle0) {
        this.dispatchRestoreInstanceState(bundle0);
    }

    public void saveActionViewStates(Bundle bundle0) {
        int v = this.size();
        SparseArray sparseArray0 = null;
        for(int v1 = 0; v1 < v; ++v1) {
            MenuItem menuItem0 = this.getItem(v1);
            View view0 = menuItem0.getActionView();
            if(view0 != null && view0.getId() != -1) {
                if(sparseArray0 == null) {
                    sparseArray0 = new SparseArray();
                }
                view0.saveHierarchyState(sparseArray0);
                if(menuItem0.isActionViewExpanded()) {
                    bundle0.putInt("android:menu:expandedactionview", menuItem0.getItemId());
                }
            }
            if(menuItem0.hasSubMenu()) {
                ((SubMenuBuilder)menuItem0.getSubMenu()).saveActionViewStates(bundle0);
            }
        }
        if(sparseArray0 != null) {
            bundle0.putSparseParcelableArray("android:menu:actionviewstates", sparseArray0);
        }
    }

    public void savePresenterStates(Bundle bundle0) {
        this.dispatchSaveInstanceState(bundle0);
    }

    public void setCallback(Callback menuBuilder$Callback0) {
        this.mCallback = menuBuilder$Callback0;
    }

    public void setCurrentMenuInfo(ContextMenu.ContextMenuInfo contextMenu$ContextMenuInfo0) {
        this.mCurrentMenuInfo = contextMenu$ContextMenuInfo0;
    }

    public MenuBuilder setDefaultShowAsAction(int v) {
        this.mDefaultShowAsAction = v;
        return this;
    }

    void setExclusiveItemChecked(MenuItem menuItem0) {
        int v = menuItem0.getGroupId();
        int v1 = this.mItems.size();
        this.stopDispatchingItemsChanged();
        for(int v2 = 0; v2 < v1; ++v2) {
            MenuItemImpl menuItemImpl0 = (MenuItemImpl)this.mItems.get(v2);
            if(menuItemImpl0.getGroupId() == v && menuItemImpl0.isExclusiveCheckable() && menuItemImpl0.isCheckable()) {
                menuItemImpl0.setCheckedInt(menuItemImpl0 == menuItem0);
            }
        }
        this.startDispatchingItemsChanged();
    }

    @Override  // android.view.Menu
    public void setGroupCheckable(int v, boolean z, boolean z1) {
        int v1 = this.mItems.size();
        for(int v2 = 0; v2 < v1; ++v2) {
            MenuItemImpl menuItemImpl0 = (MenuItemImpl)this.mItems.get(v2);
            if(menuItemImpl0.getGroupId() == v) {
                menuItemImpl0.setExclusiveCheckable(z1);
                menuItemImpl0.setCheckable(z);
            }
        }
    }

    @Override  // androidx.core.internal.view.SupportMenu
    public void setGroupDividerEnabled(boolean z) {
        this.mGroupDividerEnabled = z;
    }

    @Override  // android.view.Menu
    public void setGroupEnabled(int v, boolean z) {
        int v1 = this.mItems.size();
        for(int v2 = 0; v2 < v1; ++v2) {
            MenuItemImpl menuItemImpl0 = (MenuItemImpl)this.mItems.get(v2);
            if(menuItemImpl0.getGroupId() == v) {
                menuItemImpl0.setEnabled(z);
            }
        }
    }

    @Override  // android.view.Menu
    public void setGroupVisible(int v, boolean z) {
        int v1 = this.mItems.size();
        boolean z1 = false;
        for(int v2 = 0; v2 < v1; ++v2) {
            MenuItemImpl menuItemImpl0 = (MenuItemImpl)this.mItems.get(v2);
            if(menuItemImpl0.getGroupId() == v && menuItemImpl0.setVisibleInt(z)) {
                z1 = true;
            }
        }
        if(z1) {
            this.onItemsChanged(true);
        }
    }

    protected MenuBuilder setHeaderIconInt(int v) {
        this.setHeaderInternal(0, null, v, null, null);
        return this;
    }

    protected MenuBuilder setHeaderIconInt(Drawable drawable0) {
        this.setHeaderInternal(0, null, 0, drawable0, null);
        return this;
    }

    private void setHeaderInternal(int v, CharSequence charSequence0, int v1, Drawable drawable0, View view0) {
        Resources resources0 = this.getResources();
        if(view0 == null) {
            if(v > 0) {
                this.mHeaderTitle = resources0.getText(v);
            }
            else if(charSequence0 != null) {
                this.mHeaderTitle = charSequence0;
            }
            if(v1 > 0) {
                this.mHeaderIcon = ContextCompat.getDrawable(this.getContext(), v1);
            }
            else if(drawable0 != null) {
                this.mHeaderIcon = drawable0;
            }
            this.mHeaderView = null;
        }
        else {
            this.mHeaderView = view0;
            this.mHeaderTitle = null;
            this.mHeaderIcon = null;
        }
        this.onItemsChanged(false);
    }

    protected MenuBuilder setHeaderTitleInt(int v) {
        this.setHeaderInternal(v, null, 0, null, null);
        return this;
    }

    protected MenuBuilder setHeaderTitleInt(CharSequence charSequence0) {
        this.setHeaderInternal(0, charSequence0, 0, null, null);
        return this;
    }

    protected MenuBuilder setHeaderViewInt(View view0) {
        this.setHeaderInternal(0, null, 0, null, view0);
        return this;
    }

    public void setOptionalIconsVisible(boolean z) {
        this.mOptionalIconsVisible = z;
    }

    public void setOverrideVisibleItems(boolean z) {
        this.mOverrideVisibleItems = z;
    }

    @Override  // android.view.Menu
    public void setQwertyMode(boolean z) {
        this.mQwertyMode = z;
        this.onItemsChanged(false);
    }

    public void setShortcutsVisible(boolean z) {
        if(this.mShortcutsVisible == z) {
            return;
        }
        this.setShortcutsVisibleInner(z);
        this.onItemsChanged(false);
    }

    private void setShortcutsVisibleInner(boolean z) {
        this.mShortcutsVisible = z && this.mResources.getConfiguration().keyboard != 1 && ViewConfigurationCompat.shouldShowMenuShortcutsWhenKeyboardPresent(ViewConfiguration.get(this.mContext), this.mContext);
    }

    @Override  // android.view.Menu
    public int size() {
        return this.mItems.size();
    }

    public void startDispatchingItemsChanged() {
        this.mPreventDispatchingItemsChanged = false;
        if(this.mItemsChangedWhileDispatchPrevented) {
            this.mItemsChangedWhileDispatchPrevented = false;
            this.onItemsChanged(this.mStructureChangedWhileDispatchPrevented);
        }
    }

    public void stopDispatchingItemsChanged() {
        if(!this.mPreventDispatchingItemsChanged) {
            this.mPreventDispatchingItemsChanged = true;
            this.mItemsChangedWhileDispatchPrevented = false;
            this.mStructureChangedWhileDispatchPrevented = false;
        }
    }
}

