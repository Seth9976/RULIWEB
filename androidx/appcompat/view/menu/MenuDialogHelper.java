package androidx.appcompat.view.menu;

import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnKeyListener;
import android.content.DialogInterface;
import android.os.IBinder;
import android.view.KeyEvent.DispatcherState;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import androidx.appcompat.R.layout;
import androidx.appcompat.app.AlertDialog.Builder;
import androidx.appcompat.app.AlertDialog;

class MenuDialogHelper implements DialogInterface.OnClickListener, DialogInterface.OnDismissListener, DialogInterface.OnKeyListener, Callback {
    private AlertDialog mDialog;
    private MenuBuilder mMenu;
    ListMenuPresenter mPresenter;
    private Callback mPresenterCallback;

    public MenuDialogHelper(MenuBuilder menuBuilder0) {
        this.mMenu = menuBuilder0;
    }

    public void dismiss() {
        AlertDialog alertDialog0 = this.mDialog;
        if(alertDialog0 != null) {
            alertDialog0.dismiss();
        }
    }

    @Override  // android.content.DialogInterface$OnClickListener
    public void onClick(DialogInterface dialogInterface0, int v) {
        this.mMenu.performItemAction(((MenuItemImpl)this.mPresenter.getAdapter().getItem(v)), 0);
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter$Callback
    public void onCloseMenu(MenuBuilder menuBuilder0, boolean z) {
        if(z || menuBuilder0 == this.mMenu) {
            this.dismiss();
        }
        Callback menuPresenter$Callback0 = this.mPresenterCallback;
        if(menuPresenter$Callback0 != null) {
            menuPresenter$Callback0.onCloseMenu(menuBuilder0, z);
        }
    }

    @Override  // android.content.DialogInterface$OnDismissListener
    public void onDismiss(DialogInterface dialogInterface0) {
        this.mPresenter.onCloseMenu(this.mMenu, true);
    }

    @Override  // android.content.DialogInterface$OnKeyListener
    public boolean onKey(DialogInterface dialogInterface0, int v, KeyEvent keyEvent0) {
        if(v == 4 || v == 82) {
            if(keyEvent0.getAction() == 0 && keyEvent0.getRepeatCount() == 0) {
                Window window0 = this.mDialog.getWindow();
                if(window0 != null) {
                    View view0 = window0.getDecorView();
                    if(view0 != null) {
                        KeyEvent.DispatcherState keyEvent$DispatcherState0 = view0.getKeyDispatcherState();
                        if(keyEvent$DispatcherState0 != null) {
                            keyEvent$DispatcherState0.startTracking(keyEvent0, this);
                            return true;
                        }
                    }
                }
            }
            else if(keyEvent0.getAction() == 1 && !keyEvent0.isCanceled()) {
                Window window1 = this.mDialog.getWindow();
                if(window1 != null) {
                    View view1 = window1.getDecorView();
                    if(view1 != null) {
                        KeyEvent.DispatcherState keyEvent$DispatcherState1 = view1.getKeyDispatcherState();
                        if(keyEvent$DispatcherState1 != null && keyEvent$DispatcherState1.isTracking(keyEvent0)) {
                            this.mMenu.close(true);
                            dialogInterface0.dismiss();
                            return true;
                        }
                    }
                }
            }
        }
        return this.mMenu.performShortcut(v, keyEvent0, 0);
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter$Callback
    public boolean onOpenSubMenu(MenuBuilder menuBuilder0) {
        return this.mPresenterCallback == null ? false : this.mPresenterCallback.onOpenSubMenu(menuBuilder0);
    }

    public void setPresenterCallback(Callback menuPresenter$Callback0) {
        this.mPresenterCallback = menuPresenter$Callback0;
    }

    public void show(IBinder iBinder0) {
        MenuBuilder menuBuilder0 = this.mMenu;
        Builder alertDialog$Builder0 = new Builder(menuBuilder0.getContext());
        ListMenuPresenter listMenuPresenter0 = new ListMenuPresenter(alertDialog$Builder0.getContext(), layout.abc_list_menu_item_layout);
        this.mPresenter = listMenuPresenter0;
        listMenuPresenter0.setCallback(this);
        this.mMenu.addMenuPresenter(this.mPresenter);
        alertDialog$Builder0.setAdapter(this.mPresenter.getAdapter(), this);
        View view0 = menuBuilder0.getHeaderView();
        if(view0 == null) {
            alertDialog$Builder0.setIcon(menuBuilder0.getHeaderIcon()).setTitle(menuBuilder0.getHeaderTitle());
        }
        else {
            alertDialog$Builder0.setCustomTitle(view0);
        }
        alertDialog$Builder0.setOnKeyListener(this);
        AlertDialog alertDialog0 = alertDialog$Builder0.create();
        this.mDialog = alertDialog0;
        alertDialog0.setOnDismissListener(this);
        WindowManager.LayoutParams windowManager$LayoutParams0 = this.mDialog.getWindow().getAttributes();
        windowManager$LayoutParams0.type = 1003;
        if(iBinder0 != null) {
            windowManager$LayoutParams0.token = iBinder0;
        }
        windowManager$LayoutParams0.flags |= 0x20000;
        this.mDialog.show();
    }
}

