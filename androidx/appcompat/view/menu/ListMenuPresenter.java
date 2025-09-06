package androidx.appcompat.view.menu;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import androidx.appcompat.R.layout;
import java.util.ArrayList;

public class ListMenuPresenter implements AdapterView.OnItemClickListener, MenuPresenter {
    class MenuAdapter extends BaseAdapter {
        private int mExpandedIndex;

        public MenuAdapter() {
            this.mExpandedIndex = -1;
            this.findExpandedIndex();
        }

        void findExpandedIndex() {
            MenuItemImpl menuItemImpl0 = ListMenuPresenter.this.mMenu.getExpandedItem();
            if(menuItemImpl0 != null) {
                ArrayList arrayList0 = ListMenuPresenter.this.mMenu.getNonActionItems();
                int v = arrayList0.size();
                for(int v1 = 0; v1 < v; ++v1) {
                    if(((MenuItemImpl)arrayList0.get(v1)) == menuItemImpl0) {
                        this.mExpandedIndex = v1;
                        return;
                    }
                }
            }
            this.mExpandedIndex = -1;
        }

        @Override  // android.widget.Adapter
        public int getCount() {
            int v = ListMenuPresenter.this.mMenu.getNonActionItems().size() - ListMenuPresenter.this.mItemIndexOffset;
            return this.mExpandedIndex >= 0 ? v - 1 : v;
        }

        public MenuItemImpl getItem(int v) {
            ArrayList arrayList0 = ListMenuPresenter.this.mMenu.getNonActionItems();
            int v1 = v + ListMenuPresenter.this.mItemIndexOffset;
            return (MenuItemImpl)arrayList0.get((this.mExpandedIndex < 0 || v1 < this.mExpandedIndex ? v + ListMenuPresenter.this.mItemIndexOffset : v1 + 1));
        }

        @Override  // android.widget.Adapter
        public Object getItem(int v) {
            return this.getItem(v);
        }

        @Override  // android.widget.Adapter
        public long getItemId(int v) {
            return (long)v;
        }

        @Override  // android.widget.Adapter
        public View getView(int v, View view0, ViewGroup viewGroup0) {
            if(view0 == null) {
                view0 = ListMenuPresenter.this.mInflater.inflate(ListMenuPresenter.this.mItemLayoutRes, viewGroup0, false);
            }
            ((ItemView)view0).initialize(this.getItem(v), 0);
            return view0;
        }

        @Override  // android.widget.BaseAdapter
        public void notifyDataSetChanged() {
            this.findExpandedIndex();
            super.notifyDataSetChanged();
        }
    }

    private static final String TAG = "ListMenuPresenter";
    public static final String VIEWS_TAG = "android:menu:list";
    MenuAdapter mAdapter;
    private Callback mCallback;
    Context mContext;
    private int mId;
    LayoutInflater mInflater;
    int mItemIndexOffset;
    int mItemLayoutRes;
    MenuBuilder mMenu;
    ExpandedMenuView mMenuView;
    int mThemeRes;

    public ListMenuPresenter(int v, int v1) {
        this.mItemLayoutRes = v;
        this.mThemeRes = v1;
    }

    public ListMenuPresenter(Context context0, int v) {
        this(v, 0);
        this.mContext = context0;
        this.mInflater = LayoutInflater.from(context0);
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public boolean collapseItemActionView(MenuBuilder menuBuilder0, MenuItemImpl menuItemImpl0) {
        return false;
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public boolean expandItemActionView(MenuBuilder menuBuilder0, MenuItemImpl menuItemImpl0) {
        return false;
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public boolean flagActionItems() {
        return false;
    }

    public ListAdapter getAdapter() {
        if(this.mAdapter == null) {
            this.mAdapter = new MenuAdapter(this);
        }
        return this.mAdapter;
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public int getId() {
        return this.mId;
    }

    int getItemIndexOffset() {
        return this.mItemIndexOffset;
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public MenuView getMenuView(ViewGroup viewGroup0) {
        if(this.mMenuView == null) {
            this.mMenuView = (ExpandedMenuView)this.mInflater.inflate(layout.abc_expanded_menu_layout, viewGroup0, false);
            if(this.mAdapter == null) {
                this.mAdapter = new MenuAdapter(this);
            }
            this.mMenuView.setAdapter(this.mAdapter);
            this.mMenuView.setOnItemClickListener(this);
        }
        return this.mMenuView;
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public void initForMenu(Context context0, MenuBuilder menuBuilder0) {
        if(this.mThemeRes != 0) {
            ContextThemeWrapper contextThemeWrapper0 = new ContextThemeWrapper(context0, this.mThemeRes);
            this.mContext = contextThemeWrapper0;
            this.mInflater = LayoutInflater.from(contextThemeWrapper0);
        }
        else if(this.mContext != null) {
            this.mContext = context0;
            if(this.mInflater == null) {
                this.mInflater = LayoutInflater.from(context0);
            }
        }
        this.mMenu = menuBuilder0;
        MenuAdapter listMenuPresenter$MenuAdapter0 = this.mAdapter;
        if(listMenuPresenter$MenuAdapter0 != null) {
            listMenuPresenter$MenuAdapter0.notifyDataSetChanged();
        }
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public void onCloseMenu(MenuBuilder menuBuilder0, boolean z) {
        Callback menuPresenter$Callback0 = this.mCallback;
        if(menuPresenter$Callback0 != null) {
            menuPresenter$Callback0.onCloseMenu(menuBuilder0, z);
        }
    }

    @Override  // android.widget.AdapterView$OnItemClickListener
    public void onItemClick(AdapterView adapterView0, View view0, int v, long v1) {
        this.mMenu.performItemAction(this.mAdapter.getItem(v), this, 0);
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public void onRestoreInstanceState(Parcelable parcelable0) {
        this.restoreHierarchyState(((Bundle)parcelable0));
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public Parcelable onSaveInstanceState() {
        if(this.mMenuView == null) {
            return null;
        }
        Parcelable parcelable0 = new Bundle();
        this.saveHierarchyState(((Bundle)parcelable0));
        return parcelable0;
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder0) {
        if(!subMenuBuilder0.hasVisibleItems()) {
            return false;
        }
        new MenuDialogHelper(subMenuBuilder0).show(null);
        Callback menuPresenter$Callback0 = this.mCallback;
        if(menuPresenter$Callback0 != null) {
            menuPresenter$Callback0.onOpenSubMenu(subMenuBuilder0);
        }
        return true;
    }

    public void restoreHierarchyState(Bundle bundle0) {
        SparseArray sparseArray0 = bundle0.getSparseParcelableArray("android:menu:list");
        if(sparseArray0 != null) {
            this.mMenuView.restoreHierarchyState(sparseArray0);
        }
    }

    public void saveHierarchyState(Bundle bundle0) {
        SparseArray sparseArray0 = new SparseArray();
        ExpandedMenuView expandedMenuView0 = this.mMenuView;
        if(expandedMenuView0 != null) {
            expandedMenuView0.saveHierarchyState(sparseArray0);
        }
        bundle0.putSparseParcelableArray("android:menu:list", sparseArray0);
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public void setCallback(Callback menuPresenter$Callback0) {
        this.mCallback = menuPresenter$Callback0;
    }

    public void setId(int v) {
        this.mId = v;
    }

    public void setItemIndexOffset(int v) {
        this.mItemIndexOffset = v;
        if(this.mMenuView != null) {
            this.updateMenuView(false);
        }
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public void updateMenuView(boolean z) {
        MenuAdapter listMenuPresenter$MenuAdapter0 = this.mAdapter;
        if(listMenuPresenter$MenuAdapter0 != null) {
            listMenuPresenter$MenuAdapter0.notifyDataSetChanged();
        }
    }
}

