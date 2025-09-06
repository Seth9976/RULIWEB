package androidx.appcompat.view.menu;

import android.content.Context;
import android.graphics.Rect;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow.OnDismissListener;

abstract class MenuPopup implements AdapterView.OnItemClickListener, MenuPresenter, ShowableListMenu {
    private Rect mEpicenterBounds;

    public abstract void addMenu(MenuBuilder arg1);

    protected boolean closeMenuOnSubMenuOpened() {
        return true;
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public boolean collapseItemActionView(MenuBuilder menuBuilder0, MenuItemImpl menuItemImpl0) {
        return false;
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public boolean expandItemActionView(MenuBuilder menuBuilder0, MenuItemImpl menuItemImpl0) {
        return false;
    }

    public Rect getEpicenterBounds() {
        return this.mEpicenterBounds;
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public int getId() {
        return 0;
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public MenuView getMenuView(ViewGroup viewGroup0) {
        throw new UnsupportedOperationException("MenuPopups manage their own views");
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public void initForMenu(Context context0, MenuBuilder menuBuilder0) {
    }

    protected static int measureIndividualMenuWidth(ListAdapter listAdapter0, ViewGroup viewGroup0, Context context0, int v) {
        int v2 = listAdapter0.getCount();
        View view0 = null;
        int v3 = 0;
        int v4 = 0;
        for(int v1 = 0; v1 < v2; ++v1) {
            int v5 = listAdapter0.getItemViewType(v1);
            if(v5 != v4) {
                view0 = null;
                v4 = v5;
            }
            if(viewGroup0 == null) {
                viewGroup0 = new FrameLayout(context0);
            }
            view0 = listAdapter0.getView(v1, view0, viewGroup0);
            view0.measure(0, 0);
            int v6 = view0.getMeasuredWidth();
            if(v6 >= v) {
                return v;
            }
            if(v6 > v3) {
                v3 = v6;
            }
        }
        return v3;
    }

    @Override  // android.widget.AdapterView$OnItemClickListener
    public void onItemClick(AdapterView adapterView0, View view0, int v, long v1) {
        ListAdapter listAdapter0 = (ListAdapter)adapterView0.getAdapter();
        MenuPopup.toMenuAdapter(listAdapter0).mAdapterMenu.performItemAction(((MenuItem)listAdapter0.getItem(v)), this, (this.closeMenuOnSubMenuOpened() ? 0 : 4));
    }

    public abstract void setAnchorView(View arg1);

    public void setEpicenterBounds(Rect rect0) {
        this.mEpicenterBounds = rect0;
    }

    public abstract void setForceShowIcon(boolean arg1);

    public abstract void setGravity(int arg1);

    public abstract void setHorizontalOffset(int arg1);

    public abstract void setOnDismissListener(PopupWindow.OnDismissListener arg1);

    public abstract void setShowTitle(boolean arg1);

    public abstract void setVerticalOffset(int arg1);

    protected static boolean shouldPreserveIconSpacing(MenuBuilder menuBuilder0) {
        int v = menuBuilder0.size();
        for(int v1 = 0; v1 < v; ++v1) {
            MenuItem menuItem0 = menuBuilder0.getItem(v1);
            if(menuItem0.isVisible() && menuItem0.getIcon() != null) {
                return true;
            }
        }
        return false;
    }

    // 去混淆评级： 低(20)
    protected static MenuAdapter toMenuAdapter(ListAdapter listAdapter0) {
        return listAdapter0 instanceof HeaderViewListAdapter ? ((MenuAdapter)((HeaderViewListAdapter)listAdapter0).getWrappedAdapter()) : ((MenuAdapter)listAdapter0);
    }
}

