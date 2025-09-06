package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.transition.Transition;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import androidx.appcompat.view.menu.ListMenuItemView;
import androidx.appcompat.view.menu.MenuAdapter;
import androidx.appcompat.view.menu.MenuBuilder;
import java.lang.reflect.Method;

public class MenuPopupWindow extends ListPopupWindow implements MenuItemHoverListener {
    static class Api23Impl {
        static void setEnterTransition(PopupWindow popupWindow0, Transition transition0) {
            popupWindow0.setEnterTransition(transition0);
        }

        static void setExitTransition(PopupWindow popupWindow0, Transition transition0) {
            popupWindow0.setExitTransition(transition0);
        }
    }

    static class Api29Impl {
        static void setTouchModal(PopupWindow popupWindow0, boolean z) {
            popupWindow0.setTouchModal(z);
        }
    }

    public static class MenuDropDownListView extends DropDownListView {
        final int mAdvanceKey;
        private MenuItemHoverListener mHoverListener;
        private MenuItem mHoveredMenuItem;
        final int mRetreatKey;

        public MenuDropDownListView(Context context0, boolean z) {
            super(context0, z);
            if(1 == context0.getResources().getConfiguration().getLayoutDirection()) {
                this.mAdvanceKey = 21;
                this.mRetreatKey = 22;
                return;
            }
            this.mAdvanceKey = 22;
            this.mRetreatKey = 21;
        }

        public void clearSelection() {
            this.setSelection(-1);
        }

        @Override  // androidx.appcompat.widget.DropDownListView
        public boolean hasFocus() {
            return super.hasFocus();
        }

        @Override  // androidx.appcompat.widget.DropDownListView
        public boolean hasWindowFocus() {
            return super.hasWindowFocus();
        }

        @Override  // androidx.appcompat.widget.DropDownListView
        public boolean isFocused() {
            return super.isFocused();
        }

        @Override  // androidx.appcompat.widget.DropDownListView
        public boolean isInTouchMode() {
            return super.isInTouchMode();
        }

        @Override  // androidx.appcompat.widget.DropDownListView
        public int lookForSelectablePosition(int v, boolean z) {
            return super.lookForSelectablePosition(v, z);
        }

        @Override  // androidx.appcompat.widget.DropDownListView
        public int measureHeightOfChildrenCompat(int v, int v1, int v2, int v3, int v4) {
            return super.measureHeightOfChildrenCompat(v, v1, v2, v3, v4);
        }

        @Override  // androidx.appcompat.widget.DropDownListView
        public boolean onForwardedEvent(MotionEvent motionEvent0, int v) {
            return super.onForwardedEvent(motionEvent0, v);
        }

        @Override  // androidx.appcompat.widget.DropDownListView
        public boolean onHoverEvent(MotionEvent motionEvent0) {
            MenuItem menuItem0;
            MenuAdapter menuAdapter0;
            int v;
            if(this.mHoverListener != null) {
                ListAdapter listAdapter0 = this.getAdapter();
                if(listAdapter0 instanceof HeaderViewListAdapter) {
                    v = ((HeaderViewListAdapter)listAdapter0).getHeadersCount();
                    menuAdapter0 = (MenuAdapter)((HeaderViewListAdapter)listAdapter0).getWrappedAdapter();
                }
                else {
                    menuAdapter0 = (MenuAdapter)listAdapter0;
                    v = 0;
                }
                if(motionEvent0.getAction() == 10) {
                    menuItem0 = null;
                }
                else {
                    int v1 = this.pointToPosition(((int)motionEvent0.getX()), ((int)motionEvent0.getY()));
                    if(v1 == -1) {
                        menuItem0 = null;
                    }
                    else {
                        int v2 = v1 - v;
                        menuItem0 = v2 < 0 || v2 >= menuAdapter0.getCount() ? null : menuAdapter0.getItem(v2);
                    }
                }
                MenuItem menuItem1 = this.mHoveredMenuItem;
                if(menuItem1 != menuItem0) {
                    MenuBuilder menuBuilder0 = menuAdapter0.getAdapterMenu();
                    if(menuItem1 != null) {
                        this.mHoverListener.onItemHoverExit(menuBuilder0, menuItem1);
                    }
                    this.mHoveredMenuItem = menuItem0;
                    if(menuItem0 != null) {
                        this.mHoverListener.onItemHoverEnter(menuBuilder0, menuItem0);
                    }
                }
            }
            return super.onHoverEvent(motionEvent0);
        }

        @Override  // android.widget.ListView
        public boolean onKeyDown(int v, KeyEvent keyEvent0) {
            ListMenuItemView listMenuItemView0 = (ListMenuItemView)this.getSelectedView();
            if(listMenuItemView0 != null && v == this.mAdvanceKey) {
                if(listMenuItemView0.isEnabled() && listMenuItemView0.getItemData().hasSubMenu()) {
                    this.performItemClick(listMenuItemView0, this.getSelectedItemPosition(), this.getSelectedItemId());
                }
                return true;
            }
            if(listMenuItemView0 != null && v == this.mRetreatKey) {
                this.setSelection(-1);
                ListAdapter listAdapter0 = this.getAdapter();
                (listAdapter0 instanceof HeaderViewListAdapter ? ((MenuAdapter)((HeaderViewListAdapter)listAdapter0).getWrappedAdapter()) : ((MenuAdapter)listAdapter0)).getAdapterMenu().close(false);
                return true;
            }
            return super.onKeyDown(v, keyEvent0);
        }

        @Override  // androidx.appcompat.widget.DropDownListView
        public boolean onTouchEvent(MotionEvent motionEvent0) {
            return super.onTouchEvent(motionEvent0);
        }

        public void setHoverListener(MenuItemHoverListener menuItemHoverListener0) {
            this.mHoverListener = menuItemHoverListener0;
        }

        @Override  // androidx.appcompat.widget.DropDownListView
        public void setSelector(Drawable drawable0) {
            super.setSelector(drawable0);
        }
    }

    private static final String TAG = "MenuPopupWindow";
    private MenuItemHoverListener mHoverListener;
    private static Method sSetTouchModalMethod;

    static {
        if(Build.VERSION.SDK_INT <= 28) {
            try {
                MenuPopupWindow.sSetTouchModalMethod = PopupWindow.class.getDeclaredMethod("setTouchModal", Boolean.TYPE);
            }
            catch(NoSuchMethodException unused_ex) {
                Log.i("MenuPopupWindow", "Could not find method setTouchModal() on PopupWindow. Oh well.");
            }
        }
    }

    public MenuPopupWindow(Context context0, AttributeSet attributeSet0, int v, int v1) {
        super(context0, attributeSet0, v, v1);
    }

    @Override  // androidx.appcompat.widget.ListPopupWindow
    DropDownListView createDropDownListView(Context context0, boolean z) {
        DropDownListView dropDownListView0 = new MenuDropDownListView(context0, z);
        ((MenuDropDownListView)dropDownListView0).setHoverListener(this);
        return dropDownListView0;
    }

    @Override  // androidx.appcompat.widget.MenuItemHoverListener
    public void onItemHoverEnter(MenuBuilder menuBuilder0, MenuItem menuItem0) {
        MenuItemHoverListener menuItemHoverListener0 = this.mHoverListener;
        if(menuItemHoverListener0 != null) {
            menuItemHoverListener0.onItemHoverEnter(menuBuilder0, menuItem0);
        }
    }

    @Override  // androidx.appcompat.widget.MenuItemHoverListener
    public void onItemHoverExit(MenuBuilder menuBuilder0, MenuItem menuItem0) {
        MenuItemHoverListener menuItemHoverListener0 = this.mHoverListener;
        if(menuItemHoverListener0 != null) {
            menuItemHoverListener0.onItemHoverExit(menuBuilder0, menuItem0);
        }
    }

    public void setEnterTransition(Object object0) {
        if(Build.VERSION.SDK_INT >= 23) {
            Api23Impl.setEnterTransition(this.mPopup, ((Transition)object0));
        }
    }

    public void setExitTransition(Object object0) {
        if(Build.VERSION.SDK_INT >= 23) {
            Api23Impl.setExitTransition(this.mPopup, ((Transition)object0));
        }
    }

    public void setHoverListener(MenuItemHoverListener menuItemHoverListener0) {
        this.mHoverListener = menuItemHoverListener0;
    }

    public void setTouchModal(boolean z) {
        if(Build.VERSION.SDK_INT <= 28) {
            Method method0 = MenuPopupWindow.sSetTouchModalMethod;
            if(method0 != null) {
                try {
                    method0.invoke(this.mPopup, Boolean.valueOf(z));
                    return;
                }
                catch(Exception unused_ex) {
                    Log.i("MenuPopupWindow", "Could not invoke setTouchModal() on PopupWindow. Oh well.");
                }
            }
            return;
        }
        Api29Impl.setTouchModal(this.mPopup, z);
    }
}

