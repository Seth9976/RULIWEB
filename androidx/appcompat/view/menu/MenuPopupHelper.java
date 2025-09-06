package androidx.appcompat.view.menu;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.PopupWindow.OnDismissListener;
import androidx.appcompat.R.attr;
import androidx.appcompat.R.dimen;
import androidx.core.view.GravityCompat;

public class MenuPopupHelper implements MenuHelper {
    private static final int TOUCH_EPICENTER_SIZE_DP = 0x30;
    private View mAnchorView;
    private final Context mContext;
    private int mDropDownGravity;
    private boolean mForceShowIcon;
    private final PopupWindow.OnDismissListener mInternalOnDismissListener;
    private final MenuBuilder mMenu;
    private PopupWindow.OnDismissListener mOnDismissListener;
    private final boolean mOverflowOnly;
    private MenuPopup mPopup;
    private final int mPopupStyleAttr;
    private final int mPopupStyleRes;
    private Callback mPresenterCallback;

    public MenuPopupHelper(Context context0, MenuBuilder menuBuilder0) {
        this(context0, menuBuilder0, null, false, attr.popupMenuStyle, 0);
    }

    public MenuPopupHelper(Context context0, MenuBuilder menuBuilder0, View view0) {
        this(context0, menuBuilder0, view0, false, attr.popupMenuStyle, 0);
    }

    public MenuPopupHelper(Context context0, MenuBuilder menuBuilder0, View view0, boolean z, int v) {
        this(context0, menuBuilder0, view0, z, v, 0);
    }

    public MenuPopupHelper(Context context0, MenuBuilder menuBuilder0, View view0, boolean z, int v, int v1) {
        this.mDropDownGravity = 0x800003;
        this.mInternalOnDismissListener = () -> {
            MenuPopupHelper.this.mPopup = null;
            PopupWindow.OnDismissListener popupWindow$OnDismissListener0 = MenuPopupHelper.this.mOnDismissListener;
            if(popupWindow$OnDismissListener0 != null) {
                popupWindow$OnDismissListener0.onDismiss();
            }
        };
        this.mContext = context0;
        this.mMenu = menuBuilder0;
        this.mAnchorView = view0;
        this.mOverflowOnly = z;
        this.mPopupStyleAttr = v;
        this.mPopupStyleRes = v1;
    }

    private MenuPopup createPopup() {
        Display display0 = ((WindowManager)this.mContext.getSystemService("window")).getDefaultDisplay();
        Point point0 = new Point();
        display0.getRealSize(point0);
        MenuPopup menuPopup0 = Math.min(point0.x, point0.y) >= this.mContext.getResources().getDimensionPixelSize(dimen.abc_cascading_menus_min_smallest_width) ? new CascadingMenuPopup(this.mContext, this.mAnchorView, this.mPopupStyleAttr, this.mPopupStyleRes, this.mOverflowOnly) : new StandardMenuPopup(this.mContext, this.mMenu, this.mAnchorView, this.mPopupStyleAttr, this.mPopupStyleRes, this.mOverflowOnly);
        menuPopup0.addMenu(this.mMenu);
        menuPopup0.setOnDismissListener(this.mInternalOnDismissListener);
        menuPopup0.setAnchorView(this.mAnchorView);
        menuPopup0.setCallback(this.mPresenterCallback);
        menuPopup0.setForceShowIcon(this.mForceShowIcon);
        menuPopup0.setGravity(this.mDropDownGravity);
        return menuPopup0;
    }

    @Override  // androidx.appcompat.view.menu.MenuHelper
    public void dismiss() {
        if(this.isShowing()) {
            this.mPopup.dismiss();
        }
    }

    public int getGravity() {
        return this.mDropDownGravity;
    }

    public ListView getListView() {
        return this.getPopup().getListView();
    }

    public MenuPopup getPopup() {
        if(this.mPopup == null) {
            this.mPopup = this.createPopup();
        }
        return this.mPopup;
    }

    public boolean isShowing() {
        return this.mPopup != null && this.mPopup.isShowing();
    }

    // 检测为 Lambda 实现
    protected void onDismiss() [...]

    public void setAnchorView(View view0) {
        this.mAnchorView = view0;
    }

    public void setForceShowIcon(boolean z) {
        this.mForceShowIcon = z;
        MenuPopup menuPopup0 = this.mPopup;
        if(menuPopup0 != null) {
            menuPopup0.setForceShowIcon(z);
        }
    }

    public void setGravity(int v) {
        this.mDropDownGravity = v;
    }

    public void setOnDismissListener(PopupWindow.OnDismissListener popupWindow$OnDismissListener0) {
        this.mOnDismissListener = popupWindow$OnDismissListener0;
    }

    @Override  // androidx.appcompat.view.menu.MenuHelper
    public void setPresenterCallback(Callback menuPresenter$Callback0) {
        this.mPresenterCallback = menuPresenter$Callback0;
        MenuPopup menuPopup0 = this.mPopup;
        if(menuPopup0 != null) {
            menuPopup0.setCallback(menuPresenter$Callback0);
        }
    }

    public void show() {
        if(!this.tryShow()) {
            throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
        }
    }

    public void show(int v, int v1) {
        if(!this.tryShow(v, v1)) {
            throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
        }
    }

    private void showPopup(int v, int v1, boolean z, boolean z1) {
        MenuPopup menuPopup0 = this.getPopup();
        menuPopup0.setShowTitle(z1);
        if(z) {
            if((GravityCompat.getAbsoluteGravity(this.mDropDownGravity, this.mAnchorView.getLayoutDirection()) & 7) == 5) {
                v -= this.mAnchorView.getWidth();
            }
            menuPopup0.setHorizontalOffset(v);
            menuPopup0.setVerticalOffset(v1);
            int v2 = (int)(this.mContext.getResources().getDisplayMetrics().density * 48.0f / 2.0f);
            menuPopup0.setEpicenterBounds(new Rect(v - v2, v1 - v2, v + v2, v1 + v2));
        }
        menuPopup0.show();
    }

    public boolean tryShow() {
        if(this.isShowing()) {
            return true;
        }
        if(this.mAnchorView == null) {
            return false;
        }
        this.showPopup(0, 0, false, false);
        return true;
    }

    public boolean tryShow(int v, int v1) {
        if(this.isShowing()) {
            return true;
        }
        if(this.mAnchorView == null) {
            return false;
        }
        this.showPopup(v, v1, true, true);
        return true;
    }

    class androidx.appcompat.view.menu.MenuPopupHelper.1 implements PopupWindow.OnDismissListener {
        @Override  // android.widget.PopupWindow$OnDismissListener
        public void onDismiss() {
            MenuPopupHelper.this.onDismiss();
        }
    }

}

