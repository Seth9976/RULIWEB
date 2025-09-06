package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Parcelable;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View.OnAttachStateChangeListener;
import android.view.View.OnKeyListener;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import androidx.appcompat.R.dimen;
import androidx.appcompat.R.layout;
import androidx.appcompat.widget.MenuPopupWindow;

final class StandardMenuPopup extends MenuPopup implements View.OnKeyListener, AdapterView.OnItemClickListener, PopupWindow.OnDismissListener, MenuPresenter {
    private static final int ITEM_LAYOUT;
    private final MenuAdapter mAdapter;
    private View mAnchorView;
    private final View.OnAttachStateChangeListener mAttachStateChangeListener;
    private int mContentWidth;
    private final Context mContext;
    private int mDropDownGravity;
    final ViewTreeObserver.OnGlobalLayoutListener mGlobalLayoutListener;
    private boolean mHasContentWidth;
    private final MenuBuilder mMenu;
    private PopupWindow.OnDismissListener mOnDismissListener;
    private final boolean mOverflowOnly;
    final MenuPopupWindow mPopup;
    private final int mPopupMaxWidth;
    private final int mPopupStyleAttr;
    private final int mPopupStyleRes;
    private Callback mPresenterCallback;
    private boolean mShowTitle;
    View mShownAnchorView;
    ViewTreeObserver mTreeObserver;
    private boolean mWasDismissed;

    static {
        StandardMenuPopup.ITEM_LAYOUT = layout.abc_popup_menu_item_layout;
    }

    public StandardMenuPopup(Context context0, MenuBuilder menuBuilder0, View view0, int v, int v1, boolean z) {
        this.mGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override  // android.view.ViewTreeObserver$OnGlobalLayoutListener
            public void onGlobalLayout() {
                if(StandardMenuPopup.this.isShowing() && !StandardMenuPopup.this.mPopup.isModal()) {
                    View view0 = StandardMenuPopup.this.mShownAnchorView;
                    if(view0 != null && view0.isShown()) {
                        StandardMenuPopup.this.mPopup.show();
                        return;
                    }
                    StandardMenuPopup.this.dismiss();
                }
            }
        };
        this.mAttachStateChangeListener = new View.OnAttachStateChangeListener() {
            @Override  // android.view.View$OnAttachStateChangeListener
            public void onViewAttachedToWindow(View view0) {
            }

            @Override  // android.view.View$OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View view0) {
                if(StandardMenuPopup.this.mTreeObserver != null) {
                    if(!StandardMenuPopup.this.mTreeObserver.isAlive()) {
                        StandardMenuPopup.this.mTreeObserver = view0.getViewTreeObserver();
                    }
                    StandardMenuPopup.this.mTreeObserver.removeGlobalOnLayoutListener(StandardMenuPopup.this.mGlobalLayoutListener);
                }
                view0.removeOnAttachStateChangeListener(this);
            }
        };
        this.mDropDownGravity = 0;
        this.mContext = context0;
        this.mMenu = menuBuilder0;
        this.mOverflowOnly = z;
        this.mAdapter = new MenuAdapter(menuBuilder0, LayoutInflater.from(context0), z, StandardMenuPopup.ITEM_LAYOUT);
        this.mPopupStyleAttr = v;
        this.mPopupStyleRes = v1;
        Resources resources0 = context0.getResources();
        this.mPopupMaxWidth = Math.max(resources0.getDisplayMetrics().widthPixels / 2, resources0.getDimensionPixelSize(dimen.abc_config_prefDialogWidth));
        this.mAnchorView = view0;
        this.mPopup = new MenuPopupWindow(context0, null, v, v1);
        menuBuilder0.addMenuPresenter(this, context0);
    }

    @Override  // androidx.appcompat.view.menu.MenuPopup
    public void addMenu(MenuBuilder menuBuilder0) {
    }

    @Override  // androidx.appcompat.view.menu.ShowableListMenu
    public void dismiss() {
        if(this.isShowing()) {
            this.mPopup.dismiss();
        }
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public boolean flagActionItems() {
        return false;
    }

    @Override  // androidx.appcompat.view.menu.ShowableListMenu
    public ListView getListView() {
        return this.mPopup.getListView();
    }

    // 去混淆评级： 低(20)
    @Override  // androidx.appcompat.view.menu.ShowableListMenu
    public boolean isShowing() {
        return !this.mWasDismissed && this.mPopup.isShowing();
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public void onCloseMenu(MenuBuilder menuBuilder0, boolean z) {
        if(menuBuilder0 == this.mMenu) {
            this.dismiss();
            Callback menuPresenter$Callback0 = this.mPresenterCallback;
            if(menuPresenter$Callback0 != null) {
                menuPresenter$Callback0.onCloseMenu(menuBuilder0, z);
            }
        }
    }

    @Override  // android.widget.PopupWindow$OnDismissListener
    public void onDismiss() {
        this.mWasDismissed = true;
        this.mMenu.close();
        ViewTreeObserver viewTreeObserver0 = this.mTreeObserver;
        if(viewTreeObserver0 != null) {
            if(!viewTreeObserver0.isAlive()) {
                this.mTreeObserver = this.mShownAnchorView.getViewTreeObserver();
            }
            this.mTreeObserver.removeGlobalOnLayoutListener(this.mGlobalLayoutListener);
            this.mTreeObserver = null;
        }
        this.mShownAnchorView.removeOnAttachStateChangeListener(this.mAttachStateChangeListener);
        PopupWindow.OnDismissListener popupWindow$OnDismissListener0 = this.mOnDismissListener;
        if(popupWindow$OnDismissListener0 != null) {
            popupWindow$OnDismissListener0.onDismiss();
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
        if(subMenuBuilder0.hasVisibleItems()) {
            MenuPopupHelper menuPopupHelper0 = new MenuPopupHelper(this.mContext, subMenuBuilder0, this.mShownAnchorView, this.mOverflowOnly, this.mPopupStyleAttr, this.mPopupStyleRes);
            menuPopupHelper0.setPresenterCallback(this.mPresenterCallback);
            menuPopupHelper0.setForceShowIcon(MenuPopup.shouldPreserveIconSpacing(subMenuBuilder0));
            menuPopupHelper0.setOnDismissListener(this.mOnDismissListener);
            this.mOnDismissListener = null;
            this.mMenu.close(false);
            int v = this.mPopup.getHorizontalOffset();
            int v1 = this.mPopup.getVerticalOffset();
            if((Gravity.getAbsoluteGravity(this.mDropDownGravity, this.mAnchorView.getLayoutDirection()) & 7) == 5) {
                v += this.mAnchorView.getWidth();
            }
            if(menuPopupHelper0.tryShow(v, v1)) {
                Callback menuPresenter$Callback0 = this.mPresenterCallback;
                if(menuPresenter$Callback0 != null) {
                    menuPresenter$Callback0.onOpenSubMenu(subMenuBuilder0);
                }
                return true;
            }
        }
        return false;
    }

    @Override  // androidx.appcompat.view.menu.MenuPopup
    public void setAnchorView(View view0) {
        this.mAnchorView = view0;
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public void setCallback(Callback menuPresenter$Callback0) {
        this.mPresenterCallback = menuPresenter$Callback0;
    }

    @Override  // androidx.appcompat.view.menu.MenuPopup
    public void setForceShowIcon(boolean z) {
        this.mAdapter.setForceShowIcon(z);
    }

    @Override  // androidx.appcompat.view.menu.MenuPopup
    public void setGravity(int v) {
        this.mDropDownGravity = v;
    }

    @Override  // androidx.appcompat.view.menu.MenuPopup
    public void setHorizontalOffset(int v) {
        this.mPopup.setHorizontalOffset(v);
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
        this.mPopup.setVerticalOffset(v);
    }

    @Override  // androidx.appcompat.view.menu.ShowableListMenu
    public void show() {
        if(!this.tryShow()) {
            throw new IllegalStateException("StandardMenuPopup cannot be used without an anchor");
        }
    }

    private boolean tryShow() {
        if(this.isShowing()) {
            return true;
        }
        if(!this.mWasDismissed) {
            View view0 = this.mAnchorView;
            if(view0 != null) {
                this.mShownAnchorView = view0;
                this.mPopup.setOnDismissListener(this);
                this.mPopup.setOnItemClickListener(this);
                this.mPopup.setModal(true);
                View view1 = this.mShownAnchorView;
                boolean z = this.mTreeObserver == null;
                ViewTreeObserver viewTreeObserver0 = view1.getViewTreeObserver();
                this.mTreeObserver = viewTreeObserver0;
                if(z) {
                    viewTreeObserver0.addOnGlobalLayoutListener(this.mGlobalLayoutListener);
                }
                view1.addOnAttachStateChangeListener(this.mAttachStateChangeListener);
                this.mPopup.setAnchorView(view1);
                this.mPopup.setDropDownGravity(this.mDropDownGravity);
                if(!this.mHasContentWidth) {
                    this.mContentWidth = StandardMenuPopup.measureIndividualMenuWidth(this.mAdapter, null, this.mContext, this.mPopupMaxWidth);
                    this.mHasContentWidth = true;
                }
                this.mPopup.setContentWidth(this.mContentWidth);
                this.mPopup.setInputMethodMode(2);
                Rect rect0 = this.getEpicenterBounds();
                this.mPopup.setEpicenterBounds(rect0);
                this.mPopup.show();
                ListView listView0 = this.mPopup.getListView();
                listView0.setOnKeyListener(this);
                if(this.mShowTitle && this.mMenu.getHeaderTitle() != null) {
                    FrameLayout frameLayout0 = (FrameLayout)LayoutInflater.from(this.mContext).inflate(layout.abc_popup_menu_header_item_layout, listView0, false);
                    TextView textView0 = (TextView)frameLayout0.findViewById(0x1020016);
                    if(textView0 != null) {
                        textView0.setText(this.mMenu.getHeaderTitle());
                    }
                    frameLayout0.setEnabled(false);
                    listView0.addHeaderView(frameLayout0, null, false);
                }
                this.mPopup.setAdapter(this.mAdapter);
                this.mPopup.show();
                return true;
            }
        }
        return false;
    }

    @Override  // androidx.appcompat.view.menu.MenuPresenter
    public void updateMenuView(boolean z) {
        this.mHasContentWidth = false;
        MenuAdapter menuAdapter0 = this.mAdapter;
        if(menuAdapter0 != null) {
            menuAdapter0.notifyDataSetChanged();
        }
    }
}

