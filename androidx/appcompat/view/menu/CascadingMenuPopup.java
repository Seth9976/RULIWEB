package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Parcelable;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View.OnAttachStateChangeListener;
import android.view.View.OnKeyListener;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import androidx.appcompat.R.dimen;
import androidx.appcompat.R.layout;
import androidx.appcompat.widget.MenuItemHoverListener;
import androidx.appcompat.widget.MenuPopupWindow;
import androidx.core.view.GravityCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

final class CascadingMenuPopup extends MenuPopup implements View.OnKeyListener, PopupWindow.OnDismissListener, MenuPresenter {
    static class CascadingMenuInfo {
        public final MenuBuilder menu;
        public final int position;
        public final MenuPopupWindow window;

        public CascadingMenuInfo(MenuPopupWindow menuPopupWindow0, MenuBuilder menuBuilder0, int v) {
            this.window = menuPopupWindow0;
            this.menu = menuBuilder0;
            this.position = v;
        }

        public ListView getListView() {
            return this.window.getListView();
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface HorizPosition {
    }

    static final int HORIZ_POSITION_LEFT = 0;
    static final int HORIZ_POSITION_RIGHT = 1;
    private static final int ITEM_LAYOUT = 0;
    static final int SUBMENU_TIMEOUT_MS = 200;
    private View mAnchorView;
    private final View.OnAttachStateChangeListener mAttachStateChangeListener;
    private final Context mContext;
    private int mDropDownGravity;
    private boolean mForceShowIcon;
    final ViewTreeObserver.OnGlobalLayoutListener mGlobalLayoutListener;
    private boolean mHasXOffset;
    private boolean mHasYOffset;
    private int mLastPosition;
    private final MenuItemHoverListener mMenuItemHoverListener;
    private final int mMenuMaxWidth;
    private PopupWindow.OnDismissListener mOnDismissListener;
    private final boolean mOverflowOnly;
    private final List mPendingMenus;
    private final int mPopupStyleAttr;
    private final int mPopupStyleRes;
    private Callback mPresenterCallback;
    private int mRawDropDownGravity;
    boolean mShouldCloseImmediately;
    private boolean mShowTitle;
    final List mShowingMenus;
    View mShownAnchorView;
    final Handler mSubMenuHoverHandler;
    ViewTreeObserver mTreeObserver;
    private int mXOffset;
    private int mYOffset;

    static {
        CascadingMenuPopup.ITEM_LAYOUT = layout.abc_cascading_menu_item_layout;
    }

    public CascadingMenuPopup(Context context0, View view0, int v, int v1, boolean z) {
        this.mPendingMenus = new ArrayList();
        this.mShowingMenus = new ArrayList();
        this.mGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override  // android.view.ViewTreeObserver$OnGlobalLayoutListener
            public void onGlobalLayout() {
                if(CascadingMenuPopup.this.isShowing() && CascadingMenuPopup.this.mShowingMenus.size() > 0 && !((CascadingMenuInfo)CascadingMenuPopup.this.mShowingMenus.get(0)).window.isModal()) {
                    View view0 = CascadingMenuPopup.this.mShownAnchorView;
                    if(view0 != null && view0.isShown()) {
                        for(Object object0: CascadingMenuPopup.this.mShowingMenus) {
                            ((CascadingMenuInfo)object0).window.show();
                        }
                        return;
                    }
                    CascadingMenuPopup.this.dismiss();
                }
            }
        };
        this.mAttachStateChangeListener = new View.OnAttachStateChangeListener() {
            @Override  // android.view.View$OnAttachStateChangeListener
            public void onViewAttachedToWindow(View view0) {
            }

            @Override  // android.view.View$OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View view0) {
                if(CascadingMenuPopup.this.mTreeObserver != null) {
                    if(!CascadingMenuPopup.this.mTreeObserver.isAlive()) {
                        CascadingMenuPopup.this.mTreeObserver = view0.getViewTreeObserver();
                    }
                    CascadingMenuPopup.this.mTreeObserver.removeGlobalOnLayoutListener(CascadingMenuPopup.this.mGlobalLayoutListener);
                }
                view0.removeOnAttachStateChangeListener(this);
            }
        };
        this.mMenuItemHoverListener = new MenuItemHoverListener() {
            @Override  // androidx.appcompat.widget.MenuItemHoverListener
            public void onItemHoverEnter(MenuBuilder menuBuilder0, MenuItem menuItem0) {
                CascadingMenuInfo cascadingMenuPopup$CascadingMenuInfo0 = null;
                CascadingMenuPopup.this.mSubMenuHoverHandler.removeCallbacksAndMessages(null);
                int v = CascadingMenuPopup.this.mShowingMenus.size();
                int v1;
                for(v1 = 0; true; ++v1) {
                    if(v1 >= v) {
                        v1 = -1;
                        break;
                    }
                    if(menuBuilder0 == ((CascadingMenuInfo)CascadingMenuPopup.this.mShowingMenus.get(v1)).menu) {
                        break;
                    }
                }
                if(v1 == -1) {
                    return;
                }
                if(v1 + 1 < CascadingMenuPopup.this.mShowingMenus.size()) {
                    cascadingMenuPopup$CascadingMenuInfo0 = (CascadingMenuInfo)CascadingMenuPopup.this.mShowingMenus.get(v1 + 1);
                }
                androidx.appcompat.view.menu.CascadingMenuPopup.3.1 cascadingMenuPopup$3$10 = new Runnable() {
                    @Override
                    public void run() {
                        if(cascadingMenuPopup$CascadingMenuInfo0 != null) {
                            CascadingMenuPopup.this.mShouldCloseImmediately = true;
                            cascadingMenuPopup$CascadingMenuInfo0.menu.close(false);
                            CascadingMenuPopup.this.mShouldCloseImmediately = false;
                        }
                        if(menuItem0.isEnabled() && menuItem0.hasSubMenu()) {
                            menuBuilder0.performItemAction(menuItem0, 4);
                        }
                    }
                };
                long v2 = SystemClock.uptimeMillis();
                CascadingMenuPopup.this.mSubMenuHoverHandler.postAtTime(cascadingMenuPopup$3$10, menuBuilder0, v2 + 200L);
            }

            @Override  // androidx.appcompat.widget.MenuItemHoverListener
            public void onItemHoverExit(MenuBuilder menuBuilder0, MenuItem menuItem0) {
                CascadingMenuPopup.this.mSubMenuHoverHandler.removeCallbacksAndMessages(menuBuilder0);
            }
        };
        this.mRawDropDownGravity = 0;
        this.mDropDownGravity = 0;
        this.mContext = context0;
        this.mAnchorView = view0;
        this.mPopupStyleAttr = v;
        this.mPopupStyleRes = v1;
        this.mOverflowOnly = z;
        this.mForceShowIcon = false;
        this.mLastPosition = this.getInitialMenuPosition();
        Resources resources0 = context0.getResources();
        this.mMenuMaxWidth = Math.max(resources0.getDisplayMetrics().widthPixels / 2, resources0.getDimensionPixelSize(dimen.abc_config_prefDialogWidth));
        this.mSubMenuHoverHandler = new Handler();
    }

    @Override  // androidx.appcompat.view.menu.MenuPopup
    public void addMenu(MenuBuilder menuBuilder0) {
        menuBuilder0.addMenuPresenter(this, this.mContext);
        if(this.isShowing()) {
            this.showMenu(menuBuilder0);
            return;
        }
        this.mPendingMenus.add(menuBuilder0);
    }

    @Override  // androidx.appcompat.view.menu.MenuPopup
    protected boolean closeMenuOnSubMenuOpened() {
        return false;
    }

    private MenuPopupWindow createPopupWindow() {
        MenuPopupWindow menuPopupWindow0 = new MenuPopupWindow(this.mContext, null, this.mPopupStyleAttr, this.mPopupStyleRes);
        menuPopupWindow0.setHoverListener(this.mMenuItemHoverListener);
        menuPopupWindow0.setOnItemClickListener(this);
        menuPopupWindow0.setOnDismissListener(this);
        menuPopupWindow0.setAnchorView(this.mAnchorView);
        menuPopupWindow0.setDropDownGravity(this.mDropDownGravity);
        menuPopupWindow0.setModal(true);
        menuPopupWindow0.setInputMethodMode(2);
        return menuPopupWindow0;
    }

    @Override  // androidx.appcompat.view.menu.ShowableListMenu
    public void dismiss() {
        int v = this.mShowingMenus.size();
        if(v > 0) {
            CascadingMenuInfo[] arr_cascadingMenuPopup$CascadingMenuInfo = (CascadingMenuInfo[])this.mShowingMenus.toArray(new CascadingMenuInfo[v]);
            for(int v1 = v - 1; v1 >= 0; --v1) {
                CascadingMenuInfo cascadingMenuPopup$CascadingMenuInfo0 = arr_cascadingMenuPopup$CascadingMenuInfo[v1];
                if(cascadingMenuPopup$CascadingMenuInfo0.window.isShowing()) {
                    cascadingMenuPopup$CascadingMenuInfo0.window.dismiss();
                }
            }
        }
    }

    private int findIndexOfAddedMenu(MenuBuilder menuBuilder0) {
        int v = this.mShowingMenus.size();
        for(int v1 = 0; v1 < v; ++v1) {
            if(menuBuilder0 == ((CascadingMenuInfo)this.mShowingMenus.get(v1)).menu) {
                return v1;
            }
        }
        return -1;
    }

    private MenuItem findMenuItemForSubmenu(MenuBuilder menuBuilder0, MenuBuilder menuBuilder1) {
        int v = menuBuilder0.size();
        for(int v1 = 0; v1 < v; ++v1) {
            MenuItem menuItem0 = menuBuilder0.getItem(v1);
            if(menuItem0.hasSubMenu() && menuBuilder1 == menuItem0.getSubMenu()) {
                return menuItem0;
            }
        }
        return null;
    }

    private View findParentViewForSubmenu(CascadingMenuInfo cascadingMenuPopup$CascadingMenuInfo0, MenuBuilder menuBuilder0) {
        MenuAdapter menuAdapter0;
        int v1;
        MenuItem menuItem0 = this.findMenuItemForSubmenu(cascadingMenuPopup$CascadingMenuInfo0.menu, menuBuilder0);
        if(menuItem0 == null) {
            return null;
        }
        ListView listView0 = cascadingMenuPopup$CascadingMenuInfo0.getListView();
        ListAdapter listAdapter0 = listView0.getAdapter();
        int v = 0;
        if(listAdapter0 instanceof HeaderViewListAdapter) {
            v1 = ((HeaderViewListAdapter)listAdapter0).getHeadersCount();
            menuAdapter0 = (MenuAdapter)((HeaderViewListAdapter)listAdapter0).getWrappedAdapter();
        }
        else {
            menuAdapter0 = (MenuAdapter)listAdapter0;
            v1 = 0;
        }
        int v2 = menuAdapter0.getCount();
        while(true) {
            if(v >= v2) {
                v = -1;
                break;
            }
            if(menuItem0 == menuAdapter0.getItem(v)) {
                break;
            }
            ++v;
        }
        if(v == -1) {
            return null;
        }
        int v3 = v + v1 - listView0.getFirstVisiblePosition();
        return v3 < 0 || v3 >= listView0.getChildCount() ? null : listView0.getChildAt(v3);
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public boolean flagActionItems() {
        return false;
    }

    private int getInitialMenuPosition() {
        return this.mAnchorView.getLayoutDirection() == 1 ? 0 : 1;
    }

    // 去混淆评级： 低(20)
    @Override  // androidx.appcompat.view.menu.ShowableListMenu
    public ListView getListView() {
        return this.mShowingMenus.isEmpty() ? null : ((CascadingMenuInfo)this.mShowingMenus.get(this.mShowingMenus.size() - 1)).getListView();
    }

    private int getNextMenuPosition(int v) {
        ListView listView0 = ((CascadingMenuInfo)this.mShowingMenus.get(this.mShowingMenus.size() - 1)).getListView();
        int[] arr_v = new int[2];
        listView0.getLocationOnScreen(arr_v);
        Rect rect0 = new Rect();
        this.mShownAnchorView.getWindowVisibleDisplayFrame(rect0);
        if(this.mLastPosition == 1) {
            return arr_v[0] + listView0.getWidth() + v <= rect0.right ? 1 : 0;
        }
        return arr_v[0] - v >= 0 ? 0 : 1;
    }

    @Override  // androidx.appcompat.view.menu.ShowableListMenu
    public boolean isShowing() {
        return this.mShowingMenus.size() > 0 && ((CascadingMenuInfo)this.mShowingMenus.get(0)).window.isShowing();
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public void onCloseMenu(MenuBuilder menuBuilder0, boolean z) {
        int v = this.findIndexOfAddedMenu(menuBuilder0);
        if(v >= 0) {
            if(v + 1 < this.mShowingMenus.size()) {
                ((CascadingMenuInfo)this.mShowingMenus.get(v + 1)).menu.close(false);
            }
            CascadingMenuInfo cascadingMenuPopup$CascadingMenuInfo0 = (CascadingMenuInfo)this.mShowingMenus.remove(v);
            cascadingMenuPopup$CascadingMenuInfo0.menu.removeMenuPresenter(this);
            if(this.mShouldCloseImmediately) {
                cascadingMenuPopup$CascadingMenuInfo0.window.setExitTransition(null);
                cascadingMenuPopup$CascadingMenuInfo0.window.setAnimationStyle(0);
            }
            cascadingMenuPopup$CascadingMenuInfo0.window.dismiss();
            int v1 = this.mShowingMenus.size();
            this.mLastPosition = v1 > 0 ? ((CascadingMenuInfo)this.mShowingMenus.get(v1 - 1)).position : this.getInitialMenuPosition();
            if(v1 == 0) {
                this.dismiss();
                Callback menuPresenter$Callback0 = this.mPresenterCallback;
                if(menuPresenter$Callback0 != null) {
                    menuPresenter$Callback0.onCloseMenu(menuBuilder0, true);
                }
                ViewTreeObserver viewTreeObserver0 = this.mTreeObserver;
                if(viewTreeObserver0 != null) {
                    if(viewTreeObserver0.isAlive()) {
                        this.mTreeObserver.removeGlobalOnLayoutListener(this.mGlobalLayoutListener);
                    }
                    this.mTreeObserver = null;
                }
                this.mShownAnchorView.removeOnAttachStateChangeListener(this.mAttachStateChangeListener);
                this.mOnDismissListener.onDismiss();
                return;
            }
            if(z) {
                ((CascadingMenuInfo)this.mShowingMenus.get(0)).menu.close(false);
            }
        }
    }

    @Override  // android.widget.PopupWindow$OnDismissListener
    public void onDismiss() {
        CascadingMenuInfo cascadingMenuPopup$CascadingMenuInfo0;
        int v = this.mShowingMenus.size();
        for(int v1 = 0; true; ++v1) {
            cascadingMenuPopup$CascadingMenuInfo0 = null;
            if(v1 >= v) {
                break;
            }
            CascadingMenuInfo cascadingMenuPopup$CascadingMenuInfo1 = (CascadingMenuInfo)this.mShowingMenus.get(v1);
            if(!cascadingMenuPopup$CascadingMenuInfo1.window.isShowing()) {
                cascadingMenuPopup$CascadingMenuInfo0 = cascadingMenuPopup$CascadingMenuInfo1;
                break;
            }
        }
        if(cascadingMenuPopup$CascadingMenuInfo0 != null) {
            cascadingMenuPopup$CascadingMenuInfo0.menu.close(false);
        }
    }

    @Override  // android.view.View$OnKeyListener
    public boolean onKey(View view0, int v, KeyEvent keyEvent0) {
        if(keyEvent0.getAction() == 1 && v == 82) {
            this.dismiss();
            return true;
        }
        return false;
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public void onRestoreInstanceState(Parcelable parcelable0) {
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public Parcelable onSaveInstanceState() {
        return null;
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder0) {
        for(Object object0: this.mShowingMenus) {
            CascadingMenuInfo cascadingMenuPopup$CascadingMenuInfo0 = (CascadingMenuInfo)object0;
            if(subMenuBuilder0 == cascadingMenuPopup$CascadingMenuInfo0.menu) {
                cascadingMenuPopup$CascadingMenuInfo0.getListView().requestFocus();
                return true;
            }
            if(false) {
                break;
            }
        }
        if(subMenuBuilder0.hasVisibleItems()) {
            this.addMenu(subMenuBuilder0);
            Callback menuPresenter$Callback0 = this.mPresenterCallback;
            if(menuPresenter$Callback0 != null) {
                menuPresenter$Callback0.onOpenSubMenu(subMenuBuilder0);
            }
            return true;
        }
        return false;
    }

    @Override  // androidx.appcompat.view.menu.MenuPopup
    public void setAnchorView(View view0) {
        if(this.mAnchorView != view0) {
            this.mAnchorView = view0;
            this.mDropDownGravity = GravityCompat.getAbsoluteGravity(this.mRawDropDownGravity, view0.getLayoutDirection());
        }
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public void setCallback(Callback menuPresenter$Callback0) {
        this.mPresenterCallback = menuPresenter$Callback0;
    }

    @Override  // androidx.appcompat.view.menu.MenuPopup
    public void setForceShowIcon(boolean z) {
        this.mForceShowIcon = z;
    }

    @Override  // androidx.appcompat.view.menu.MenuPopup
    public void setGravity(int v) {
        if(this.mRawDropDownGravity != v) {
            this.mRawDropDownGravity = v;
            this.mDropDownGravity = GravityCompat.getAbsoluteGravity(v, this.mAnchorView.getLayoutDirection());
        }
    }

    @Override  // androidx.appcompat.view.menu.MenuPopup
    public void setHorizontalOffset(int v) {
        this.mHasXOffset = true;
        this.mXOffset = v;
    }

    @Override  // androidx.appcompat.view.menu.MenuPopup
    public void setOnDismissListener(PopupWindow.OnDismissListener popupWindow$OnDismissListener0) {
        this.mOnDismissListener = popupWindow$OnDismissListener0;
    }

    @Override  // androidx.appcompat.view.menu.MenuPopup
    public void setShowTitle(boolean z) {
        this.mShowTitle = z;
    }

    @Override  // androidx.appcompat.view.menu.MenuPopup
    public void setVerticalOffset(int v) {
        this.mHasYOffset = true;
        this.mYOffset = v;
    }

    @Override  // androidx.appcompat.view.menu.ShowableListMenu
    public void show() {
        if(!this.isShowing()) {
            for(Object object0: this.mPendingMenus) {
                this.showMenu(((MenuBuilder)object0));
            }
            this.mPendingMenus.clear();
            View view0 = this.mAnchorView;
            this.mShownAnchorView = view0;
            if(view0 != null) {
                boolean z = this.mTreeObserver == null;
                ViewTreeObserver viewTreeObserver0 = view0.getViewTreeObserver();
                this.mTreeObserver = viewTreeObserver0;
                if(z) {
                    viewTreeObserver0.addOnGlobalLayoutListener(this.mGlobalLayoutListener);
                }
                this.mShownAnchorView.addOnAttachStateChangeListener(this.mAttachStateChangeListener);
            }
        }
    }

    private void showMenu(MenuBuilder menuBuilder0) {
        int v4;
        int v3;
        int v2;
        View view0;
        CascadingMenuInfo cascadingMenuPopup$CascadingMenuInfo0;
        LayoutInflater layoutInflater0 = LayoutInflater.from(this.mContext);
        MenuAdapter menuAdapter0 = new MenuAdapter(menuBuilder0, layoutInflater0, this.mOverflowOnly, CascadingMenuPopup.ITEM_LAYOUT);
        if(!this.isShowing() && this.mForceShowIcon) {
            menuAdapter0.setForceShowIcon(true);
        }
        else if(this.isShowing()) {
            menuAdapter0.setForceShowIcon(MenuPopup.shouldPreserveIconSpacing(menuBuilder0));
        }
        int v = CascadingMenuPopup.measureIndividualMenuWidth(menuAdapter0, null, this.mContext, this.mMenuMaxWidth);
        MenuPopupWindow menuPopupWindow0 = this.createPopupWindow();
        menuPopupWindow0.setAdapter(menuAdapter0);
        menuPopupWindow0.setContentWidth(v);
        menuPopupWindow0.setDropDownGravity(this.mDropDownGravity);
        if(this.mShowingMenus.size() > 0) {
            cascadingMenuPopup$CascadingMenuInfo0 = (CascadingMenuInfo)this.mShowingMenus.get(this.mShowingMenus.size() - 1);
            view0 = this.findParentViewForSubmenu(cascadingMenuPopup$CascadingMenuInfo0, menuBuilder0);
        }
        else {
            cascadingMenuPopup$CascadingMenuInfo0 = null;
            view0 = null;
        }
        if(view0 == null) {
            if(this.mHasXOffset) {
                menuPopupWindow0.setHorizontalOffset(this.mXOffset);
            }
            if(this.mHasYOffset) {
                menuPopupWindow0.setVerticalOffset(this.mYOffset);
            }
            menuPopupWindow0.setEpicenterBounds(this.getEpicenterBounds());
        }
        else {
            menuPopupWindow0.setTouchModal(false);
            menuPopupWindow0.setEnterTransition(null);
            int v1 = this.getNextMenuPosition(v);
            this.mLastPosition = v1;
            if(Build.VERSION.SDK_INT >= 26) {
                menuPopupWindow0.setAnchorView(view0);
                v2 = 0;
                v3 = 0;
            }
            else {
                int[] arr_v = new int[2];
                this.mAnchorView.getLocationOnScreen(arr_v);
                int[] arr_v1 = new int[2];
                view0.getLocationOnScreen(arr_v1);
                if((this.mDropDownGravity & 7) == 5) {
                    arr_v[0] += this.mAnchorView.getWidth();
                    arr_v1[0] += view0.getWidth();
                }
                v3 = arr_v1[0] - arr_v[0];
                v2 = arr_v1[1] - arr_v[1];
            }
            if((this.mDropDownGravity & 5) != 5) {
                v4 = v1 == 1 ? v3 + view0.getWidth() : v3 - v;
            }
            else if(v1 != 1) {
                v4 = v3 - view0.getWidth();
            }
            else {
                v4 = v3 + v;
            }
            menuPopupWindow0.setHorizontalOffset(v4);
            menuPopupWindow0.setOverlapAnchor(true);
            menuPopupWindow0.setVerticalOffset(v2);
        }
        CascadingMenuInfo cascadingMenuPopup$CascadingMenuInfo1 = new CascadingMenuInfo(menuPopupWindow0, menuBuilder0, this.mLastPosition);
        this.mShowingMenus.add(cascadingMenuPopup$CascadingMenuInfo1);
        menuPopupWindow0.show();
        ListView listView0 = menuPopupWindow0.getListView();
        listView0.setOnKeyListener(this);
        if(cascadingMenuPopup$CascadingMenuInfo0 == null && this.mShowTitle && menuBuilder0.getHeaderTitle() != null) {
            FrameLayout frameLayout0 = (FrameLayout)layoutInflater0.inflate(layout.abc_popup_menu_header_item_layout, listView0, false);
            TextView textView0 = (TextView)frameLayout0.findViewById(0x1020016);
            frameLayout0.setEnabled(false);
            textView0.setText(menuBuilder0.getHeaderTitle());
            listView0.addHeaderView(frameLayout0, null, false);
            menuPopupWindow0.show();
        }
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public void updateMenuView(boolean z) {
        for(Object object0: this.mShowingMenus) {
            CascadingMenuPopup.toMenuAdapter(((CascadingMenuInfo)object0).getListView().getAdapter()).notifyDataSetChanged();
        }
    }
}

