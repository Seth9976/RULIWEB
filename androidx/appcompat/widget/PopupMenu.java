package androidx.appcompat.widget;

import android.content.Context;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View.OnTouchListener;
import android.view.View;
import android.widget.ListView;
import android.widget.PopupWindow.OnDismissListener;
import androidx.appcompat.R.attr;
import androidx.appcompat.view.SupportMenuInflater;
import androidx.appcompat.view.menu.MenuBuilder.Callback;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.appcompat.view.menu.ShowableListMenu;

public class PopupMenu {
    public interface OnDismissListener {
        void onDismiss(PopupMenu arg1);
    }

    public interface OnMenuItemClickListener {
        boolean onMenuItemClick(MenuItem arg1);
    }

    private final View mAnchor;
    private final Context mContext;
    private View.OnTouchListener mDragListener;
    private final MenuBuilder mMenu;
    OnMenuItemClickListener mMenuItemClickListener;
    OnDismissListener mOnDismissListener;
    final MenuPopupHelper mPopup;

    public PopupMenu(Context context0, View view0) {
        this(context0, view0, 0);
    }

    public PopupMenu(Context context0, View view0, int v) {
        this(context0, view0, v, attr.popupMenuStyle, 0);
    }

    public PopupMenu(Context context0, View view0, int v, int v1, int v2) {
        this.mContext = context0;
        this.mAnchor = view0;
        MenuBuilder menuBuilder0 = new MenuBuilder(context0);
        this.mMenu = menuBuilder0;
        menuBuilder0.setCallback(new Callback() {
            @Override  // androidx.appcompat.view.menu.MenuBuilder$Callback
            public boolean onMenuItemSelected(MenuBuilder menuBuilder0, MenuItem menuItem0) {
                return PopupMenu.this.mMenuItemClickListener == null ? false : PopupMenu.this.mMenuItemClickListener.onMenuItemClick(menuItem0);
            }

            @Override  // androidx.appcompat.view.menu.MenuBuilder$Callback
            public void onMenuModeChange(MenuBuilder menuBuilder0) {
            }
        });
        MenuPopupHelper menuPopupHelper0 = new MenuPopupHelper(context0, menuBuilder0, view0, false, v1, v2);
        this.mPopup = menuPopupHelper0;
        menuPopupHelper0.setGravity(v);
        menuPopupHelper0.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override  // android.widget.PopupWindow$OnDismissListener
            public void onDismiss() {
                if(PopupMenu.this.mOnDismissListener != null) {
                    PopupMenu.this.mOnDismissListener.onDismiss(PopupMenu.this);
                }
            }
        });
    }

    public void dismiss() {
        this.mPopup.dismiss();
    }

    public View.OnTouchListener getDragToOpenListener() {
        if(this.mDragListener == null) {
            this.mDragListener = new ForwardingListener(this.mAnchor) {
                @Override  // androidx.appcompat.widget.ForwardingListener
                public ShowableListMenu getPopup() {
                    return PopupMenu.this.mPopup.getPopup();
                }

                @Override  // androidx.appcompat.widget.ForwardingListener
                protected boolean onForwardingStarted() {
                    PopupMenu.this.show();
                    return true;
                }

                @Override  // androidx.appcompat.widget.ForwardingListener
                protected boolean onForwardingStopped() {
                    PopupMenu.this.dismiss();
                    return true;
                }
            };
        }
        return this.mDragListener;
    }

    public int getGravity() {
        return this.mPopup.getGravity();
    }

    public Menu getMenu() {
        return this.mMenu;
    }

    public MenuInflater getMenuInflater() {
        return new SupportMenuInflater(this.mContext);
    }

    ListView getMenuListView() {
        return this.mPopup.isShowing() ? this.mPopup.getListView() : null;
    }

    public void inflate(int v) {
        this.getMenuInflater().inflate(v, this.mMenu);
    }

    public void setForceShowIcon(boolean z) {
        this.mPopup.setForceShowIcon(z);
    }

    public void setGravity(int v) {
        this.mPopup.setGravity(v);
    }

    public void setOnDismissListener(OnDismissListener popupMenu$OnDismissListener0) {
        this.mOnDismissListener = popupMenu$OnDismissListener0;
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener popupMenu$OnMenuItemClickListener0) {
        this.mMenuItemClickListener = popupMenu$OnMenuItemClickListener0;
    }

    public void show() {
        this.mPopup.show();
    }
}

