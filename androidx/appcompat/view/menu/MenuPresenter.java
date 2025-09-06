package androidx.appcompat.view.menu;

import android.content.Context;
import android.os.Parcelable;
import android.view.ViewGroup;

public interface MenuPresenter {
    public interface Callback {
        void onCloseMenu(MenuBuilder arg1, boolean arg2);

        boolean onOpenSubMenu(MenuBuilder arg1);
    }

    boolean collapseItemActionView(MenuBuilder arg1, MenuItemImpl arg2);

    boolean expandItemActionView(MenuBuilder arg1, MenuItemImpl arg2);

    boolean flagActionItems();

    int getId();

    MenuView getMenuView(ViewGroup arg1);

    void initForMenu(Context arg1, MenuBuilder arg2);

    void onCloseMenu(MenuBuilder arg1, boolean arg2);

    void onRestoreInstanceState(Parcelable arg1);

    Parcelable onSaveInstanceState();

    boolean onSubMenuSelected(SubMenuBuilder arg1);

    void setCallback(Callback arg1);

    void updateMenuView(boolean arg1);
}

