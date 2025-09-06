package androidx.appcompat.app;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window.Callback;
import android.widget.SpinnerAdapter;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPresenter.Callback;
import androidx.appcompat.widget.DecorToolbar;
import androidx.appcompat.widget.Toolbar.OnMenuItemClickListener;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.ToolbarWidgetWrapper;
import androidx.core.util.Preconditions;
import androidx.core.view.ViewCompat;
import java.util.ArrayList;

class ToolbarActionBar extends ActionBar {
    final class ActionMenuPresenterCallback implements Callback {
        private boolean mClosingActionMenu;

        @Override  // androidx.appcompat.view.menu.MenuPresenter$Callback
        public void onCloseMenu(MenuBuilder menuBuilder0, boolean z) {
            if(this.mClosingActionMenu) {
                return;
            }
            this.mClosingActionMenu = true;
            ToolbarActionBar.this.mDecorToolbar.dismissPopupMenus();
            ToolbarActionBar.this.mWindowCallback.onPanelClosed(108, menuBuilder0);
            this.mClosingActionMenu = false;
        }

        @Override  // androidx.appcompat.view.menu.MenuPresenter$Callback
        public boolean onOpenSubMenu(MenuBuilder menuBuilder0) {
            ToolbarActionBar.this.mWindowCallback.onMenuOpened(108, menuBuilder0);
            return true;
        }
    }

    final class MenuBuilderCallback implements androidx.appcompat.view.menu.MenuBuilder.Callback {
        @Override  // androidx.appcompat.view.menu.MenuBuilder$Callback
        public boolean onMenuItemSelected(MenuBuilder menuBuilder0, MenuItem menuItem0) {
            return false;
        }

        @Override  // androidx.appcompat.view.menu.MenuBuilder$Callback
        public void onMenuModeChange(MenuBuilder menuBuilder0) {
            if(ToolbarActionBar.this.mDecorToolbar.isOverflowMenuShowing()) {
                ToolbarActionBar.this.mWindowCallback.onPanelClosed(108, menuBuilder0);
                return;
            }
            if(ToolbarActionBar.this.mWindowCallback.onPreparePanel(0, null, menuBuilder0)) {
                ToolbarActionBar.this.mWindowCallback.onMenuOpened(108, menuBuilder0);
            }
        }
    }

    class ToolbarMenuCallback implements ActionBarMenuCallback {
        @Override  // androidx.appcompat.app.AppCompatDelegateImpl$ActionBarMenuCallback
        public View onCreatePanelView(int v) {
            return v == 0 ? new View(ToolbarActionBar.this.mDecorToolbar.getContext()) : null;
        }

        @Override  // androidx.appcompat.app.AppCompatDelegateImpl$ActionBarMenuCallback
        public boolean onPreparePanel(int v) {
            if(v == 0 && !ToolbarActionBar.this.mToolbarMenuPrepared) {
                ToolbarActionBar.this.mDecorToolbar.setMenuPrepared();
                ToolbarActionBar.this.mToolbarMenuPrepared = true;
            }
            return false;
        }
    }

    final DecorToolbar mDecorToolbar;
    private boolean mLastMenuVisibility;
    final ActionBarMenuCallback mMenuCallback;
    private boolean mMenuCallbackSet;
    private final OnMenuItemClickListener mMenuClicker;
    private final Runnable mMenuInvalidator;
    private ArrayList mMenuVisibilityListeners;
    boolean mToolbarMenuPrepared;
    final Window.Callback mWindowCallback;

    ToolbarActionBar(Toolbar toolbar0, CharSequence charSequence0, Window.Callback window$Callback0) {
        this.mMenuVisibilityListeners = new ArrayList();
        this.mMenuInvalidator = () -> {
            Menu menu0 = ToolbarActionBar.this.getMenu();
            MenuBuilder menuBuilder0 = menu0 instanceof MenuBuilder ? ((MenuBuilder)menu0) : null;
            if(menuBuilder0 != null) {
                menuBuilder0.stopDispatchingItemsChanged();
            }
            try {
                menu0.clear();
                if(!ToolbarActionBar.this.mWindowCallback.onCreatePanelMenu(0, menu0) || !ToolbarActionBar.this.mWindowCallback.onPreparePanel(0, null, menu0)) {
                    menu0.clear();
                }
            }
            finally {
                if(menuBuilder0 != null) {
                    menuBuilder0.startDispatchingItemsChanged();
                }
            }
        };
        androidx.appcompat.app.ToolbarActionBar.2 toolbarActionBar$20 = new OnMenuItemClickListener() {
            @Override  // androidx.appcompat.widget.Toolbar$OnMenuItemClickListener
            public boolean onMenuItemClick(MenuItem menuItem0) {
                return ToolbarActionBar.this.mWindowCallback.onMenuItemSelected(0, menuItem0);
            }
        };
        this.mMenuClicker = toolbarActionBar$20;
        Preconditions.checkNotNull(toolbar0);
        ToolbarWidgetWrapper toolbarWidgetWrapper0 = new ToolbarWidgetWrapper(toolbar0, false);
        this.mDecorToolbar = toolbarWidgetWrapper0;
        this.mWindowCallback = (Window.Callback)Preconditions.checkNotNull(window$Callback0);
        toolbarWidgetWrapper0.setWindowCallback(window$Callback0);
        toolbar0.setOnMenuItemClickListener(toolbarActionBar$20);
        toolbarWidgetWrapper0.setWindowTitle(charSequence0);
        this.mMenuCallback = new ToolbarMenuCallback(this);
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void addOnMenuVisibilityListener(OnMenuVisibilityListener actionBar$OnMenuVisibilityListener0) {
        this.mMenuVisibilityListeners.add(actionBar$OnMenuVisibilityListener0);
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void addTab(Tab actionBar$Tab0) {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void addTab(Tab actionBar$Tab0, int v) {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void addTab(Tab actionBar$Tab0, int v, boolean z) {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void addTab(Tab actionBar$Tab0, boolean z) {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    @Override  // androidx.appcompat.app.ActionBar
    public boolean closeOptionsMenu() {
        return this.mDecorToolbar.hideOverflowMenu();
    }

    @Override  // androidx.appcompat.app.ActionBar
    public boolean collapseActionView() {
        if(this.mDecorToolbar.hasExpandedActionView()) {
            this.mDecorToolbar.collapseActionView();
            return true;
        }
        return false;
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void dispatchMenuVisibilityChanged(boolean z) {
        if(z != this.mLastMenuVisibility) {
            this.mLastMenuVisibility = z;
            int v = this.mMenuVisibilityListeners.size();
            for(int v1 = 0; v1 < v; ++v1) {
                ((OnMenuVisibilityListener)this.mMenuVisibilityListeners.get(v1)).onMenuVisibilityChanged(z);
            }
        }
    }

    @Override  // androidx.appcompat.app.ActionBar
    public View getCustomView() {
        return this.mDecorToolbar.getCustomView();
    }

    @Override  // androidx.appcompat.app.ActionBar
    public int getDisplayOptions() {
        return this.mDecorToolbar.getDisplayOptions();
    }

    @Override  // androidx.appcompat.app.ActionBar
    public float getElevation() {
        return ViewCompat.getElevation(this.mDecorToolbar.getViewGroup());
    }

    @Override  // androidx.appcompat.app.ActionBar
    public int getHeight() {
        return this.mDecorToolbar.getHeight();
    }

    private Menu getMenu() {
        if(!this.mMenuCallbackSet) {
            ActionMenuPresenterCallback toolbarActionBar$ActionMenuPresenterCallback0 = new ActionMenuPresenterCallback(this);
            MenuBuilderCallback toolbarActionBar$MenuBuilderCallback0 = new MenuBuilderCallback(this);
            this.mDecorToolbar.setMenuCallbacks(toolbarActionBar$ActionMenuPresenterCallback0, toolbarActionBar$MenuBuilderCallback0);
            this.mMenuCallbackSet = true;
        }
        return this.mDecorToolbar.getMenu();
    }

    @Override  // androidx.appcompat.app.ActionBar
    public int getNavigationItemCount() {
        return 0;
    }

    @Override  // androidx.appcompat.app.ActionBar
    public int getNavigationMode() {
        return 0;
    }

    @Override  // androidx.appcompat.app.ActionBar
    public int getSelectedNavigationIndex() {
        return -1;
    }

    @Override  // androidx.appcompat.app.ActionBar
    public Tab getSelectedTab() {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    @Override  // androidx.appcompat.app.ActionBar
    public CharSequence getSubtitle() {
        return this.mDecorToolbar.getSubtitle();
    }

    @Override  // androidx.appcompat.app.ActionBar
    public Tab getTabAt(int v) {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    @Override  // androidx.appcompat.app.ActionBar
    public int getTabCount() {
        return 0;
    }

    @Override  // androidx.appcompat.app.ActionBar
    public Context getThemedContext() {
        return this.mDecorToolbar.getContext();
    }

    @Override  // androidx.appcompat.app.ActionBar
    public CharSequence getTitle() {
        return this.mDecorToolbar.getTitle();
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void hide() {
        this.mDecorToolbar.setVisibility(8);
    }

    @Override  // androidx.appcompat.app.ActionBar
    public boolean invalidateOptionsMenu() {
        this.mDecorToolbar.getViewGroup().removeCallbacks(this.mMenuInvalidator);
        ViewCompat.postOnAnimation(this.mDecorToolbar.getViewGroup(), this.mMenuInvalidator);
        return true;
    }

    @Override  // androidx.appcompat.app.ActionBar
    public boolean isShowing() {
        return this.mDecorToolbar.getVisibility() == 0;
    }

    @Override  // androidx.appcompat.app.ActionBar
    public boolean isTitleTruncated() {
        return super.isTitleTruncated();
    }

    @Override  // androidx.appcompat.app.ActionBar
    public Tab newTab() {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void onConfigurationChanged(Configuration configuration0) {
        super.onConfigurationChanged(configuration0);
    }

    @Override  // androidx.appcompat.app.ActionBar
    void onDestroy() {
        this.mDecorToolbar.getViewGroup().removeCallbacks(this.mMenuInvalidator);
    }

    @Override  // androidx.appcompat.app.ActionBar
    public boolean onKeyShortcut(int v, KeyEvent keyEvent0) {
        Menu menu0 = this.getMenu();
        if(menu0 != null) {
            menu0.setQwertyMode(KeyCharacterMap.load((keyEvent0 == null ? -1 : keyEvent0.getDeviceId())).getKeyboardType() != 1);
            return menu0.performShortcut(v, keyEvent0, 0);
        }
        return false;
    }

    @Override  // androidx.appcompat.app.ActionBar
    public boolean onMenuKeyEvent(KeyEvent keyEvent0) {
        if(keyEvent0.getAction() == 1) {
            this.openOptionsMenu();
        }
        return true;
    }

    @Override  // androidx.appcompat.app.ActionBar
    public boolean openOptionsMenu() {
        return this.mDecorToolbar.showOverflowMenu();
    }

    // 检测为 Lambda 实现
    void populateOptionsMenu() [...]

    @Override  // androidx.appcompat.app.ActionBar
    public void removeAllTabs() {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void removeOnMenuVisibilityListener(OnMenuVisibilityListener actionBar$OnMenuVisibilityListener0) {
        this.mMenuVisibilityListeners.remove(actionBar$OnMenuVisibilityListener0);
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void removeTab(Tab actionBar$Tab0) {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void removeTabAt(int v) {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    @Override  // androidx.appcompat.app.ActionBar
    public boolean requestFocus() {
        ViewGroup viewGroup0 = this.mDecorToolbar.getViewGroup();
        if(viewGroup0 != null && !viewGroup0.hasFocus()) {
            viewGroup0.requestFocus();
            return true;
        }
        return false;
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void selectTab(Tab actionBar$Tab0) {
        throw new UnsupportedOperationException("Tabs are not supported in toolbar action bars");
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void setBackgroundDrawable(Drawable drawable0) {
        this.mDecorToolbar.setBackgroundDrawable(drawable0);
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void setCustomView(int v) {
        this.setCustomView(LayoutInflater.from(this.mDecorToolbar.getContext()).inflate(v, this.mDecorToolbar.getViewGroup(), false));
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void setCustomView(View view0) {
        this.setCustomView(view0, new LayoutParams(-2, -2));
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void setCustomView(View view0, LayoutParams actionBar$LayoutParams0) {
        if(view0 != null) {
            view0.setLayoutParams(actionBar$LayoutParams0);
        }
        this.mDecorToolbar.setCustomView(view0);
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void setDefaultDisplayHomeAsUpEnabled(boolean z) {
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void setDisplayHomeAsUpEnabled(boolean z) {
        this.setDisplayOptions((z ? 4 : 0), 4);
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void setDisplayOptions(int v) {
        this.setDisplayOptions(v, -1);
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void setDisplayOptions(int v, int v1) {
        int v2 = this.mDecorToolbar.getDisplayOptions();
        this.mDecorToolbar.setDisplayOptions(v & v1 | ~v1 & v2);
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void setDisplayShowCustomEnabled(boolean z) {
        this.setDisplayOptions((z ? 16 : 0), 16);
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void setDisplayShowHomeEnabled(boolean z) {
        this.setDisplayOptions((z ? 2 : 0), 2);
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void setDisplayShowTitleEnabled(boolean z) {
        this.setDisplayOptions((z ? 8 : 0), 8);
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void setDisplayUseLogoEnabled(boolean z) {
        this.setDisplayOptions(((int)z), 1);
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void setElevation(float f) {
        ViewCompat.setElevation(this.mDecorToolbar.getViewGroup(), f);
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void setHomeActionContentDescription(int v) {
        this.mDecorToolbar.setNavigationContentDescription(v);
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void setHomeActionContentDescription(CharSequence charSequence0) {
        this.mDecorToolbar.setNavigationContentDescription(charSequence0);
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void setHomeAsUpIndicator(int v) {
        this.mDecorToolbar.setNavigationIcon(v);
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void setHomeAsUpIndicator(Drawable drawable0) {
        this.mDecorToolbar.setNavigationIcon(drawable0);
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void setHomeButtonEnabled(boolean z) {
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void setIcon(int v) {
        this.mDecorToolbar.setIcon(v);
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void setIcon(Drawable drawable0) {
        this.mDecorToolbar.setIcon(drawable0);
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void setListNavigationCallbacks(SpinnerAdapter spinnerAdapter0, OnNavigationListener actionBar$OnNavigationListener0) {
        NavItemSelectedListener navItemSelectedListener0 = new NavItemSelectedListener(actionBar$OnNavigationListener0);
        this.mDecorToolbar.setDropdownParams(spinnerAdapter0, navItemSelectedListener0);
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void setLogo(int v) {
        this.mDecorToolbar.setLogo(v);
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void setLogo(Drawable drawable0) {
        this.mDecorToolbar.setLogo(drawable0);
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void setNavigationMode(int v) {
        if(v == 2) {
            throw new IllegalArgumentException("Tabs not supported in this configuration");
        }
        this.mDecorToolbar.setNavigationMode(v);
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void setSelectedNavigationItem(int v) {
        if(this.mDecorToolbar.getNavigationMode() != 1) {
            throw new IllegalStateException("setSelectedNavigationIndex not valid for current navigation mode");
        }
        this.mDecorToolbar.setDropdownSelectedPosition(v);
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void setShowHideAnimationEnabled(boolean z) {
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void setSplitBackgroundDrawable(Drawable drawable0) {
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void setStackedBackgroundDrawable(Drawable drawable0) {
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void setSubtitle(int v) {
        CharSequence charSequence0 = v == 0 ? null : this.mDecorToolbar.getContext().getText(v);
        this.mDecorToolbar.setSubtitle(charSequence0);
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void setSubtitle(CharSequence charSequence0) {
        this.mDecorToolbar.setSubtitle(charSequence0);
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void setTitle(int v) {
        CharSequence charSequence0 = v == 0 ? null : this.mDecorToolbar.getContext().getText(v);
        this.mDecorToolbar.setTitle(charSequence0);
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void setTitle(CharSequence charSequence0) {
        this.mDecorToolbar.setTitle(charSequence0);
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void setWindowTitle(CharSequence charSequence0) {
        this.mDecorToolbar.setWindowTitle(charSequence0);
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void show() {
        this.mDecorToolbar.setVisibility(0);
    }

    class androidx.appcompat.app.ToolbarActionBar.1 implements Runnable {
        @Override
        public void run() {
            ToolbarActionBar.this.populateOptionsMenu();
        }
    }

}

