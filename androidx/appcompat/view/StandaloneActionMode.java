package androidx.appcompat.view;

import android.content.Context;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.view.menu.MenuBuilder.Callback;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.appcompat.view.menu.SubMenuBuilder;
import androidx.appcompat.widget.ActionBarContextView;
import java.lang.ref.WeakReference;

public class StandaloneActionMode extends ActionMode implements Callback {
    private androidx.appcompat.view.ActionMode.Callback mCallback;
    private Context mContext;
    private ActionBarContextView mContextView;
    private WeakReference mCustomView;
    private boolean mFinished;
    private boolean mFocusable;
    private MenuBuilder mMenu;

    public StandaloneActionMode(Context context0, ActionBarContextView actionBarContextView0, androidx.appcompat.view.ActionMode.Callback actionMode$Callback0, boolean z) {
        this.mContext = context0;
        this.mContextView = actionBarContextView0;
        this.mCallback = actionMode$Callback0;
        MenuBuilder menuBuilder0 = new MenuBuilder(actionBarContextView0.getContext()).setDefaultShowAsAction(1);
        this.mMenu = menuBuilder0;
        menuBuilder0.setCallback(this);
        this.mFocusable = z;
    }

    @Override  // androidx.appcompat.view.ActionMode
    public void finish() {
        if(this.mFinished) {
            return;
        }
        this.mFinished = true;
        this.mCallback.onDestroyActionMode(this);
    }

    @Override  // androidx.appcompat.view.ActionMode
    public View getCustomView() {
        return this.mCustomView == null ? null : ((View)this.mCustomView.get());
    }

    @Override  // androidx.appcompat.view.ActionMode
    public Menu getMenu() {
        return this.mMenu;
    }

    @Override  // androidx.appcompat.view.ActionMode
    public MenuInflater getMenuInflater() {
        return new SupportMenuInflater(this.mContextView.getContext());
    }

    @Override  // androidx.appcompat.view.ActionMode
    public CharSequence getSubtitle() {
        return this.mContextView.getSubtitle();
    }

    @Override  // androidx.appcompat.view.ActionMode
    public CharSequence getTitle() {
        return this.mContextView.getTitle();
    }

    @Override  // androidx.appcompat.view.ActionMode
    public void invalidate() {
        this.mCallback.onPrepareActionMode(this, this.mMenu);
    }

    @Override  // androidx.appcompat.view.ActionMode
    public boolean isTitleOptional() {
        return this.mContextView.isTitleOptional();
    }

    @Override  // androidx.appcompat.view.ActionMode
    public boolean isUiFocusable() {
        return this.mFocusable;
    }

    public void onCloseMenu(MenuBuilder menuBuilder0, boolean z) {
    }

    public void onCloseSubMenu(SubMenuBuilder subMenuBuilder0) {
    }

    @Override  // androidx.appcompat.view.menu.MenuBuilder$Callback
    public boolean onMenuItemSelected(MenuBuilder menuBuilder0, MenuItem menuItem0) {
        return this.mCallback.onActionItemClicked(this, menuItem0);
    }

    @Override  // androidx.appcompat.view.menu.MenuBuilder$Callback
    public void onMenuModeChange(MenuBuilder menuBuilder0) {
        this.invalidate();
        this.mContextView.showOverflowMenu();
    }

    public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder0) {
        if(!subMenuBuilder0.hasVisibleItems()) {
            return true;
        }
        new MenuPopupHelper(this.mContextView.getContext(), subMenuBuilder0).show();
        return true;
    }

    @Override  // androidx.appcompat.view.ActionMode
    public void setCustomView(View view0) {
        this.mContextView.setCustomView(view0);
        this.mCustomView = view0 == null ? null : new WeakReference(view0);
    }

    @Override  // androidx.appcompat.view.ActionMode
    public void setSubtitle(int v) {
        this.setSubtitle(this.mContext.getString(v));
    }

    @Override  // androidx.appcompat.view.ActionMode
    public void setSubtitle(CharSequence charSequence0) {
        this.mContextView.setSubtitle(charSequence0);
    }

    @Override  // androidx.appcompat.view.ActionMode
    public void setTitle(int v) {
        this.setTitle(this.mContext.getString(v));
    }

    @Override  // androidx.appcompat.view.ActionMode
    public void setTitle(CharSequence charSequence0) {
        this.mContextView.setTitle(charSequence0);
    }

    @Override  // androidx.appcompat.view.ActionMode
    public void setTitleOptionalHint(boolean z) {
        super.setTitleOptionalHint(z);
        this.mContextView.setTitleOptional(z);
    }
}

