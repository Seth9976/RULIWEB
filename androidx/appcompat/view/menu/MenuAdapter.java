package androidx.appcompat.view.menu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;

public class MenuAdapter extends BaseAdapter {
    MenuBuilder mAdapterMenu;
    private int mExpandedIndex;
    private boolean mForceShowIcon;
    private final LayoutInflater mInflater;
    private final int mItemLayoutRes;
    private final boolean mOverflowOnly;

    public MenuAdapter(MenuBuilder menuBuilder0, LayoutInflater layoutInflater0, boolean z, int v) {
        this.mExpandedIndex = -1;
        this.mOverflowOnly = z;
        this.mInflater = layoutInflater0;
        this.mAdapterMenu = menuBuilder0;
        this.mItemLayoutRes = v;
        this.findExpandedIndex();
    }

    void findExpandedIndex() {
        MenuItemImpl menuItemImpl0 = this.mAdapterMenu.getExpandedItem();
        if(menuItemImpl0 != null) {
            ArrayList arrayList0 = this.mAdapterMenu.getNonActionItems();
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

    public MenuBuilder getAdapterMenu() {
        return this.mAdapterMenu;
    }

    @Override  // android.widget.Adapter
    public int getCount() {
        ArrayList arrayList0 = this.mOverflowOnly ? this.mAdapterMenu.getNonActionItems() : this.mAdapterMenu.getVisibleItems();
        return this.mExpandedIndex >= 0 ? arrayList0.size() - 1 : arrayList0.size();
    }

    public boolean getForceShowIcon() {
        return this.mForceShowIcon;
    }

    public MenuItemImpl getItem(int v) {
        ArrayList arrayList0 = this.mOverflowOnly ? this.mAdapterMenu.getNonActionItems() : this.mAdapterMenu.getVisibleItems();
        if(this.mExpandedIndex >= 0 && v >= this.mExpandedIndex) {
            ++v;
        }
        return (MenuItemImpl)arrayList0.get(v);
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
            view0 = this.mInflater.inflate(this.mItemLayoutRes, viewGroup0, false);
        }
        int v1 = this.getItem(v).getGroupId();
        int v2 = v - 1 < 0 ? v1 : this.getItem(v - 1).getGroupId();
        ((ListMenuItemView)view0).setGroupDividerEnabled(this.mAdapterMenu.isGroupDividerEnabled() && v1 != v2);
        if(this.mForceShowIcon) {
            ((ListMenuItemView)view0).setForceShowIcon(true);
        }
        ((ItemView)view0).initialize(this.getItem(v), 0);
        return view0;
    }

    @Override  // android.widget.BaseAdapter
    public void notifyDataSetChanged() {
        this.findExpandedIndex();
        super.notifyDataSetChanged();
    }

    public void setForceShowIcon(boolean z) {
        this.mForceShowIcon = z;
    }
}

