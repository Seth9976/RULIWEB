package androidx.appcompat.view.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

public abstract class BaseMenuPresenter implements MenuPresenter {
    private Callback mCallback;
    protected Context mContext;
    private int mId;
    protected LayoutInflater mInflater;
    private int mItemLayoutRes;
    protected MenuBuilder mMenu;
    private int mMenuLayoutRes;
    protected MenuView mMenuView;
    protected Context mSystemContext;
    protected LayoutInflater mSystemInflater;

    public BaseMenuPresenter(Context context0, int v, int v1) {
        this.mSystemContext = context0;
        this.mSystemInflater = LayoutInflater.from(context0);
        this.mMenuLayoutRes = v;
        this.mItemLayoutRes = v1;
    }

    protected void addItemView(View view0, int v) {
        ViewGroup viewGroup0 = (ViewGroup)view0.getParent();
        if(viewGroup0 != null) {
            viewGroup0.removeView(view0);
        }
        ((ViewGroup)this.mMenuView).addView(view0, v);
    }

    public abstract void bindItemView(MenuItemImpl arg1, ItemView arg2);

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public boolean collapseItemActionView(MenuBuilder menuBuilder0, MenuItemImpl menuItemImpl0) {
        return false;
    }

    public ItemView createItemView(ViewGroup viewGroup0) {
        return (ItemView)this.mSystemInflater.inflate(this.mItemLayoutRes, viewGroup0, false);
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public boolean expandItemActionView(MenuBuilder menuBuilder0, MenuItemImpl menuItemImpl0) {
        return false;
    }

    protected boolean filterLeftoverView(ViewGroup viewGroup0, int v) {
        viewGroup0.removeViewAt(v);
        return true;
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public boolean flagActionItems() {
        return false;
    }

    public Callback getCallback() {
        return this.mCallback;
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public int getId() {
        return this.mId;
    }

    public View getItemView(MenuItemImpl menuItemImpl0, View view0, ViewGroup viewGroup0) {
        ItemView menuView$ItemView0 = view0 instanceof ItemView ? ((ItemView)view0) : this.createItemView(viewGroup0);
        this.bindItemView(menuItemImpl0, menuView$ItemView0);
        return (View)menuView$ItemView0;
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public MenuView getMenuView(ViewGroup viewGroup0) {
        if(this.mMenuView == null) {
            MenuView menuView0 = (MenuView)this.mSystemInflater.inflate(this.mMenuLayoutRes, viewGroup0, false);
            this.mMenuView = menuView0;
            menuView0.initialize(this.mMenu);
            this.updateMenuView(true);
        }
        return this.mMenuView;
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public void initForMenu(Context context0, MenuBuilder menuBuilder0) {
        this.mContext = context0;
        this.mInflater = LayoutInflater.from(context0);
        this.mMenu = menuBuilder0;
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public void onCloseMenu(MenuBuilder menuBuilder0, boolean z) {
        Callback menuPresenter$Callback0 = this.mCallback;
        if(menuPresenter$Callback0 != null) {
            menuPresenter$Callback0.onCloseMenu(menuBuilder0, z);
        }
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder0) {
        Callback menuPresenter$Callback0 = this.mCallback;
        if(menuPresenter$Callback0 != null) {
            if(subMenuBuilder0 == null) {
                subMenuBuilder0 = this.mMenu;
            }
            return menuPresenter$Callback0.onOpenSubMenu(subMenuBuilder0);
        }
        return false;
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public void setCallback(Callback menuPresenter$Callback0) {
        this.mCallback = menuPresenter$Callback0;
    }

    public void setId(int v) {
        this.mId = v;
    }

    public boolean shouldIncludeItem(int v, MenuItemImpl menuItemImpl0) {
        return true;
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public void updateMenuView(boolean z) {
        ViewGroup viewGroup0 = (ViewGroup)this.mMenuView;
        if(viewGroup0 != null) {
            MenuBuilder menuBuilder0 = this.mMenu;
            int v = 0;
            if(menuBuilder0 != null) {
                menuBuilder0.flagActionItems();
                ArrayList arrayList0 = this.mMenu.getVisibleItems();
                int v1 = arrayList0.size();
                int v3 = 0;
                for(int v2 = 0; v2 < v1; ++v2) {
                    MenuItemImpl menuItemImpl0 = (MenuItemImpl)arrayList0.get(v2);
                    if(this.shouldIncludeItem(v3, menuItemImpl0)) {
                        View view0 = viewGroup0.getChildAt(v3);
                        MenuItemImpl menuItemImpl1 = view0 instanceof ItemView ? ((ItemView)view0).getItemData() : null;
                        View view1 = this.getItemView(menuItemImpl0, view0, viewGroup0);
                        if(menuItemImpl0 != menuItemImpl1) {
                            view1.setPressed(false);
                            view1.jumpDrawablesToCurrentState();
                        }
                        if(view1 != view0) {
                            this.addItemView(view1, v3);
                        }
                        ++v3;
                    }
                }
                v = v3;
            }
            while(v < viewGroup0.getChildCount()) {
                if(!this.filterLeftoverView(viewGroup0, v)) {
                    ++v;
                }
            }
        }
    }
}

