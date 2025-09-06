package com.google.android.material.navigation;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.ViewGroup;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuPresenter.Callback;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.view.menu.SubMenuBuilder;
import com.google.android.material.badge.BadgeUtils;
import com.google.android.material.internal.ParcelableSparseArray;

public class NavigationBarPresenter implements MenuPresenter {
    static class SavedState implements Parcelable {
        public static final Parcelable.Creator CREATOR;
        ParcelableSparseArray badgeSavedStates;
        int selectedItemId;

        static {
            SavedState.CREATOR = new Parcelable.Creator() {
                public SavedState createFromParcel(Parcel parcel0) {
                    return new SavedState(parcel0);
                }

                @Override  // android.os.Parcelable$Creator
                public Object createFromParcel(Parcel parcel0) {
                    return this.createFromParcel(parcel0);
                }

                public SavedState[] newArray(int v) {
                    return new SavedState[v];
                }

                @Override  // android.os.Parcelable$Creator
                public Object[] newArray(int v) {
                    return this.newArray(v);
                }
            };
        }

        SavedState() {
        }

        SavedState(Parcel parcel0) {
            this.selectedItemId = parcel0.readInt();
            this.badgeSavedStates = (ParcelableSparseArray)parcel0.readParcelable(this.getClass().getClassLoader());
        }

        @Override  // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override  // android.os.Parcelable
        public void writeToParcel(Parcel parcel0, int v) {
            parcel0.writeInt(this.selectedItemId);
            parcel0.writeParcelable(this.badgeSavedStates, 0);
        }
    }

    private int id;
    private MenuBuilder menu;
    private NavigationBarMenuView menuView;
    private boolean updateSuspended;

    public NavigationBarPresenter() {
        this.updateSuspended = false;
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

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public int getId() {
        return this.id;
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public MenuView getMenuView(ViewGroup viewGroup0) {
        return this.menuView;
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public void initForMenu(Context context0, MenuBuilder menuBuilder0) {
        this.menu = menuBuilder0;
        this.menuView.initialize(menuBuilder0);
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public void onCloseMenu(MenuBuilder menuBuilder0, boolean z) {
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public void onRestoreInstanceState(Parcelable parcelable0) {
        if(parcelable0 instanceof SavedState) {
            this.menuView.tryRestoreSelectedItemId(((SavedState)parcelable0).selectedItemId);
            SparseArray sparseArray0 = BadgeUtils.createBadgeDrawablesFromSavedStates(this.menuView.getContext(), ((SavedState)parcelable0).badgeSavedStates);
            this.menuView.restoreBadgeDrawables(sparseArray0);
        }
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public Parcelable onSaveInstanceState() {
        Parcelable parcelable0 = new SavedState();
        parcelable0.selectedItemId = this.menuView.getSelectedItemId();
        parcelable0.badgeSavedStates = BadgeUtils.createParcelableBadgeStates(this.menuView.getBadgeDrawables());
        return parcelable0;
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder0) {
        return false;
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public void setCallback(Callback menuPresenter$Callback0) {
    }

    public void setId(int v) {
        this.id = v;
    }

    public void setMenuView(NavigationBarMenuView navigationBarMenuView0) {
        this.menuView = navigationBarMenuView0;
    }

    public void setUpdateSuspended(boolean z) {
        this.updateSuspended = z;
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public void updateMenuView(boolean z) {
        if(this.updateSuspended) {
            return;
        }
        if(z) {
            this.menuView.buildMenuView();
            return;
        }
        this.menuView.updateMenuView();
    }
}

