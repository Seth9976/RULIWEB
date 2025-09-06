package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window.Callback;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import androidx.appcompat.R.attr;
import androidx.appcompat.R.drawable;
import androidx.appcompat.R.id;
import androidx.appcompat.R.string;
import androidx.appcompat.R.styleable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.view.menu.ActionMenuItem;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPresenter.Callback;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.core.view.ViewPropertyAnimatorListenerAdapter;

public class ToolbarWidgetWrapper implements DecorToolbar {
    private static final int AFFECTS_LOGO_MASK = 3;
    private static final long DEFAULT_FADE_DURATION_MS = 200L;
    private static final String TAG = "ToolbarWidgetWrapper";
    private ActionMenuPresenter mActionMenuPresenter;
    private View mCustomView;
    private int mDefaultNavigationContentDescription;
    private Drawable mDefaultNavigationIcon;
    private int mDisplayOpts;
    private CharSequence mHomeDescription;
    private Drawable mIcon;
    private Drawable mLogo;
    boolean mMenuPrepared;
    private Drawable mNavIcon;
    private int mNavigationMode;
    private Spinner mSpinner;
    private CharSequence mSubtitle;
    private View mTabView;
    CharSequence mTitle;
    private boolean mTitleSet;
    Toolbar mToolbar;
    Window.Callback mWindowCallback;

    public ToolbarWidgetWrapper(Toolbar toolbar0, boolean z) {
        this(toolbar0, z, string.abc_action_bar_up_description, drawable.abc_ic_ab_back_material);
    }

    public ToolbarWidgetWrapper(Toolbar toolbar0, boolean z, int v, int v1) {
        this.mNavigationMode = 0;
        this.mDefaultNavigationContentDescription = 0;
        this.mToolbar = toolbar0;
        this.mTitle = toolbar0.getTitle();
        this.mSubtitle = toolbar0.getSubtitle();
        this.mTitleSet = this.mTitle != null;
        this.mNavIcon = toolbar0.getNavigationIcon();
        TintTypedArray tintTypedArray0 = TintTypedArray.obtainStyledAttributes(toolbar0.getContext(), null, styleable.ActionBar, attr.actionBarStyle, 0);
        this.mDefaultNavigationIcon = tintTypedArray0.getDrawable(styleable.ActionBar_homeAsUpIndicator);
        if(z) {
            CharSequence charSequence0 = tintTypedArray0.getText(styleable.ActionBar_title);
            if(!TextUtils.isEmpty(charSequence0)) {
                this.setTitle(charSequence0);
            }
            CharSequence charSequence1 = tintTypedArray0.getText(styleable.ActionBar_subtitle);
            if(!TextUtils.isEmpty(charSequence1)) {
                this.setSubtitle(charSequence1);
            }
            Drawable drawable0 = tintTypedArray0.getDrawable(styleable.ActionBar_logo);
            if(drawable0 != null) {
                this.setLogo(drawable0);
            }
            Drawable drawable1 = tintTypedArray0.getDrawable(styleable.ActionBar_icon);
            if(drawable1 != null) {
                this.setIcon(drawable1);
            }
            if(this.mNavIcon == null) {
                Drawable drawable2 = this.mDefaultNavigationIcon;
                if(drawable2 != null) {
                    this.setNavigationIcon(drawable2);
                }
            }
            this.setDisplayOptions(tintTypedArray0.getInt(styleable.ActionBar_displayOptions, 0));
            int v2 = tintTypedArray0.getResourceId(styleable.ActionBar_customNavigationLayout, 0);
            if(v2 != 0) {
                this.setCustomView(LayoutInflater.from(this.mToolbar.getContext()).inflate(v2, this.mToolbar, false));
                this.setDisplayOptions(this.mDisplayOpts | 16);
            }
            int v3 = tintTypedArray0.getLayoutDimension(styleable.ActionBar_height, 0);
            if(v3 > 0) {
                ViewGroup.LayoutParams viewGroup$LayoutParams0 = this.mToolbar.getLayoutParams();
                viewGroup$LayoutParams0.height = v3;
                this.mToolbar.setLayoutParams(viewGroup$LayoutParams0);
            }
            int v4 = tintTypedArray0.getDimensionPixelOffset(styleable.ActionBar_contentInsetStart, -1);
            int v5 = tintTypedArray0.getDimensionPixelOffset(styleable.ActionBar_contentInsetEnd, -1);
            if(v4 >= 0 || v5 >= 0) {
                this.mToolbar.setContentInsetsRelative(Math.max(v4, 0), Math.max(v5, 0));
            }
            int v6 = tintTypedArray0.getResourceId(styleable.ActionBar_titleTextStyle, 0);
            if(v6 != 0) {
                this.mToolbar.setTitleTextAppearance(this.mToolbar.getContext(), v6);
            }
            int v7 = tintTypedArray0.getResourceId(styleable.ActionBar_subtitleTextStyle, 0);
            if(v7 != 0) {
                this.mToolbar.setSubtitleTextAppearance(this.mToolbar.getContext(), v7);
            }
            int v8 = tintTypedArray0.getResourceId(styleable.ActionBar_popupTheme, 0);
            if(v8 != 0) {
                this.mToolbar.setPopupTheme(v8);
            }
        }
        else {
            this.mDisplayOpts = this.detectDisplayOptions();
        }
        tintTypedArray0.recycle();
        this.setDefaultNavigationContentDescription(v);
        this.mHomeDescription = this.mToolbar.getNavigationContentDescription();
        this.mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            final ActionMenuItem mNavItem;

            {
                this.mNavItem = new ActionMenuItem(toolbarWidgetWrapper0.mToolbar.getContext(), 0, 0x102002C, 0, 0, toolbarWidgetWrapper0.mTitle);
            }

            @Override  // android.view.View$OnClickListener
            public void onClick(View view0) {
                if(ToolbarWidgetWrapper.this.mWindowCallback != null && ToolbarWidgetWrapper.this.mMenuPrepared) {
                    ToolbarWidgetWrapper.this.mWindowCallback.onMenuItemSelected(0, this.mNavItem);
                }
            }
        });
    }

    @Override  // androidx.appcompat.widget.DecorToolbar
    public void animateToVisibility(int v) {
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat0 = this.setupAnimatorToVisibility(v, 200L);
        if(viewPropertyAnimatorCompat0 != null) {
            viewPropertyAnimatorCompat0.start();
        }
    }

    @Override  // androidx.appcompat.widget.DecorToolbar
    public boolean canShowOverflowMenu() {
        return this.mToolbar.canShowOverflowMenu();
    }

    @Override  // androidx.appcompat.widget.DecorToolbar
    public void collapseActionView() {
        this.mToolbar.collapseActionView();
    }

    private int detectDisplayOptions() {
        if(this.mToolbar.getNavigationIcon() != null) {
            this.mDefaultNavigationIcon = this.mToolbar.getNavigationIcon();
            return 15;
        }
        return 11;
    }

    @Override  // androidx.appcompat.widget.DecorToolbar
    public void dismissPopupMenus() {
        this.mToolbar.dismissPopupMenus();
    }

    private void ensureSpinner() {
        if(this.mSpinner == null) {
            this.mSpinner = new AppCompatSpinner(this.getContext(), null, attr.actionDropDownStyle);
            LayoutParams toolbar$LayoutParams0 = new LayoutParams(-2, -2, 0x800013);
            this.mSpinner.setLayoutParams(toolbar$LayoutParams0);
        }
    }

    @Override  // androidx.appcompat.widget.DecorToolbar
    public Context getContext() {
        return this.mToolbar.getContext();
    }

    @Override  // androidx.appcompat.widget.DecorToolbar
    public View getCustomView() {
        return this.mCustomView;
    }

    @Override  // androidx.appcompat.widget.DecorToolbar
    public int getDisplayOptions() {
        return this.mDisplayOpts;
    }

    @Override  // androidx.appcompat.widget.DecorToolbar
    public int getDropdownItemCount() {
        return this.mSpinner == null ? 0 : this.mSpinner.getCount();
    }

    @Override  // androidx.appcompat.widget.DecorToolbar
    public int getDropdownSelectedPosition() {
        return this.mSpinner == null ? 0 : this.mSpinner.getSelectedItemPosition();
    }

    @Override  // androidx.appcompat.widget.DecorToolbar
    public int getHeight() {
        return this.mToolbar.getHeight();
    }

    @Override  // androidx.appcompat.widget.DecorToolbar
    public Menu getMenu() {
        return this.mToolbar.getMenu();
    }

    @Override  // androidx.appcompat.widget.DecorToolbar
    public int getNavigationMode() {
        return this.mNavigationMode;
    }

    @Override  // androidx.appcompat.widget.DecorToolbar
    public CharSequence getSubtitle() {
        return this.mToolbar.getSubtitle();
    }

    @Override  // androidx.appcompat.widget.DecorToolbar
    public CharSequence getTitle() {
        return this.mToolbar.getTitle();
    }

    @Override  // androidx.appcompat.widget.DecorToolbar
    public ViewGroup getViewGroup() {
        return this.mToolbar;
    }

    @Override  // androidx.appcompat.widget.DecorToolbar
    public int getVisibility() {
        return this.mToolbar.getVisibility();
    }

    @Override  // androidx.appcompat.widget.DecorToolbar
    public boolean hasEmbeddedTabs() {
        return this.mTabView != null;
    }

    @Override  // androidx.appcompat.widget.DecorToolbar
    public boolean hasExpandedActionView() {
        return this.mToolbar.hasExpandedActionView();
    }

    @Override  // androidx.appcompat.widget.DecorToolbar
    public boolean hasIcon() {
        return this.mIcon != null;
    }

    @Override  // androidx.appcompat.widget.DecorToolbar
    public boolean hasLogo() {
        return this.mLogo != null;
    }

    @Override  // androidx.appcompat.widget.DecorToolbar
    public boolean hideOverflowMenu() {
        return this.mToolbar.hideOverflowMenu();
    }

    @Override  // androidx.appcompat.widget.DecorToolbar
    public void initIndeterminateProgress() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    @Override  // androidx.appcompat.widget.DecorToolbar
    public void initProgress() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    @Override  // androidx.appcompat.widget.DecorToolbar
    public boolean isOverflowMenuShowPending() {
        return this.mToolbar.isOverflowMenuShowPending();
    }

    @Override  // androidx.appcompat.widget.DecorToolbar
    public boolean isOverflowMenuShowing() {
        return this.mToolbar.isOverflowMenuShowing();
    }

    @Override  // androidx.appcompat.widget.DecorToolbar
    public boolean isTitleTruncated() {
        return this.mToolbar.isTitleTruncated();
    }

    @Override  // androidx.appcompat.widget.DecorToolbar
    public void restoreHierarchyState(SparseArray sparseArray0) {
        this.mToolbar.restoreHierarchyState(sparseArray0);
    }

    @Override  // androidx.appcompat.widget.DecorToolbar
    public void saveHierarchyState(SparseArray sparseArray0) {
        this.mToolbar.saveHierarchyState(sparseArray0);
    }

    @Override  // androidx.appcompat.widget.DecorToolbar
    public void setBackgroundDrawable(Drawable drawable0) {
        this.mToolbar.setBackground(drawable0);
    }

    @Override  // androidx.appcompat.widget.DecorToolbar
    public void setCollapsible(boolean z) {
        this.mToolbar.setCollapsible(z);
    }

    @Override  // androidx.appcompat.widget.DecorToolbar
    public void setCustomView(View view0) {
        View view1 = this.mCustomView;
        if(view1 != null && (this.mDisplayOpts & 16) != 0) {
            this.mToolbar.removeView(view1);
        }
        this.mCustomView = view0;
        if(view0 != null && (this.mDisplayOpts & 16) != 0) {
            this.mToolbar.addView(view0);
        }
    }

    @Override  // androidx.appcompat.widget.DecorToolbar
    public void setDefaultNavigationContentDescription(int v) {
        if(v != this.mDefaultNavigationContentDescription) {
            this.mDefaultNavigationContentDescription = v;
            if(TextUtils.isEmpty(this.mToolbar.getNavigationContentDescription())) {
                this.setNavigationContentDescription(this.mDefaultNavigationContentDescription);
            }
        }
    }

    @Override  // androidx.appcompat.widget.DecorToolbar
    public void setDefaultNavigationIcon(Drawable drawable0) {
        if(this.mDefaultNavigationIcon != drawable0) {
            this.mDefaultNavigationIcon = drawable0;
            this.updateNavigationIcon();
        }
    }

    @Override  // androidx.appcompat.widget.DecorToolbar
    public void setDisplayOptions(int v) {
        int v1 = this.mDisplayOpts ^ v;
        this.mDisplayOpts = v;
        if(v1 != 0) {
            if((v1 & 4) != 0) {
                if((v & 4) != 0) {
                    this.updateHomeAccessibility();
                }
                this.updateNavigationIcon();
            }
            if((v1 & 3) != 0) {
                this.updateToolbarLogo();
            }
            if((v1 & 8) != 0) {
                if((v & 8) == 0) {
                    this.mToolbar.setTitle(null);
                    this.mToolbar.setSubtitle(null);
                }
                else {
                    this.mToolbar.setTitle(this.mTitle);
                    this.mToolbar.setSubtitle(this.mSubtitle);
                }
            }
            if((v1 & 16) != 0) {
                View view0 = this.mCustomView;
                if(view0 != null) {
                    if((v & 16) != 0) {
                        this.mToolbar.addView(view0);
                        return;
                    }
                    this.mToolbar.removeView(view0);
                }
            }
        }
    }

    @Override  // androidx.appcompat.widget.DecorToolbar
    public void setDropdownParams(SpinnerAdapter spinnerAdapter0, AdapterView.OnItemSelectedListener adapterView$OnItemSelectedListener0) {
        this.ensureSpinner();
        this.mSpinner.setAdapter(spinnerAdapter0);
        this.mSpinner.setOnItemSelectedListener(adapterView$OnItemSelectedListener0);
    }

    @Override  // androidx.appcompat.widget.DecorToolbar
    public void setDropdownSelectedPosition(int v) {
        Spinner spinner0 = this.mSpinner;
        if(spinner0 == null) {
            throw new IllegalStateException("Can\'t set dropdown selected position without an adapter");
        }
        spinner0.setSelection(v);
    }

    @Override  // androidx.appcompat.widget.DecorToolbar
    public void setEmbeddedTabView(ScrollingTabContainerView scrollingTabContainerView0) {
        View view0 = this.mTabView;
        if(view0 != null) {
            ViewParent viewParent0 = view0.getParent();
            Toolbar toolbar0 = this.mToolbar;
            if(viewParent0 == toolbar0) {
                toolbar0.removeView(this.mTabView);
            }
        }
        this.mTabView = scrollingTabContainerView0;
        if(scrollingTabContainerView0 != null && this.mNavigationMode == 2) {
            this.mToolbar.addView(scrollingTabContainerView0, 0);
            LayoutParams toolbar$LayoutParams0 = (LayoutParams)this.mTabView.getLayoutParams();
            toolbar$LayoutParams0.width = -2;
            toolbar$LayoutParams0.height = -2;
            toolbar$LayoutParams0.gravity = 0x800053;
            scrollingTabContainerView0.setAllowCollapse(true);
        }
    }

    @Override  // androidx.appcompat.widget.DecorToolbar
    public void setHomeButtonEnabled(boolean z) {
    }

    @Override  // androidx.appcompat.widget.DecorToolbar
    public void setIcon(int v) {
        this.setIcon((v == 0 ? null : AppCompatResources.getDrawable(this.getContext(), v)));
    }

    @Override  // androidx.appcompat.widget.DecorToolbar
    public void setIcon(Drawable drawable0) {
        this.mIcon = drawable0;
        this.updateToolbarLogo();
    }

    @Override  // androidx.appcompat.widget.DecorToolbar
    public void setLogo(int v) {
        this.setLogo((v == 0 ? null : AppCompatResources.getDrawable(this.getContext(), v)));
    }

    @Override  // androidx.appcompat.widget.DecorToolbar
    public void setLogo(Drawable drawable0) {
        this.mLogo = drawable0;
        this.updateToolbarLogo();
    }

    @Override  // androidx.appcompat.widget.DecorToolbar
    public void setMenu(Menu menu0, Callback menuPresenter$Callback0) {
        if(this.mActionMenuPresenter == null) {
            ActionMenuPresenter actionMenuPresenter0 = new ActionMenuPresenter(this.mToolbar.getContext());
            this.mActionMenuPresenter = actionMenuPresenter0;
            actionMenuPresenter0.setId(id.action_menu_presenter);
        }
        this.mActionMenuPresenter.setCallback(menuPresenter$Callback0);
        this.mToolbar.setMenu(((MenuBuilder)menu0), this.mActionMenuPresenter);
    }

    @Override  // androidx.appcompat.widget.DecorToolbar
    public void setMenuCallbacks(Callback menuPresenter$Callback0, androidx.appcompat.view.menu.MenuBuilder.Callback menuBuilder$Callback0) {
        this.mToolbar.setMenuCallbacks(menuPresenter$Callback0, menuBuilder$Callback0);
    }

    @Override  // androidx.appcompat.widget.DecorToolbar
    public void setMenuPrepared() {
        this.mMenuPrepared = true;
    }

    @Override  // androidx.appcompat.widget.DecorToolbar
    public void setNavigationContentDescription(int v) {
        this.setNavigationContentDescription((v == 0 ? null : this.getContext().getString(v)));
    }

    @Override  // androidx.appcompat.widget.DecorToolbar
    public void setNavigationContentDescription(CharSequence charSequence0) {
        this.mHomeDescription = charSequence0;
        this.updateHomeAccessibility();
    }

    @Override  // androidx.appcompat.widget.DecorToolbar
    public void setNavigationIcon(int v) {
        this.setNavigationIcon((v == 0 ? null : AppCompatResources.getDrawable(this.getContext(), v)));
    }

    @Override  // androidx.appcompat.widget.DecorToolbar
    public void setNavigationIcon(Drawable drawable0) {
        this.mNavIcon = drawable0;
        this.updateNavigationIcon();
    }

    @Override  // androidx.appcompat.widget.DecorToolbar
    public void setNavigationMode(int v) {
        int v1 = this.mNavigationMode;
        if(v != v1) {
            switch(v1) {
                case 1: {
                    Spinner spinner0 = this.mSpinner;
                    if(spinner0 != null) {
                        ViewParent viewParent0 = spinner0.getParent();
                        Toolbar toolbar0 = this.mToolbar;
                        if(viewParent0 == toolbar0) {
                            toolbar0.removeView(this.mSpinner);
                        }
                    }
                    break;
                }
                case 2: {
                    View view0 = this.mTabView;
                    if(view0 != null) {
                        ViewParent viewParent1 = view0.getParent();
                        Toolbar toolbar1 = this.mToolbar;
                        if(viewParent1 == toolbar1) {
                            toolbar1.removeView(this.mTabView);
                        }
                    }
                }
            }
            this.mNavigationMode = v;
            if(v != 0) {
                switch(v) {
                    case 1: {
                        this.ensureSpinner();
                        this.mToolbar.addView(this.mSpinner, 0);
                        return;
                    }
                    case 2: {
                        View view1 = this.mTabView;
                        if(view1 != null) {
                            this.mToolbar.addView(view1, 0);
                            LayoutParams toolbar$LayoutParams0 = (LayoutParams)this.mTabView.getLayoutParams();
                            toolbar$LayoutParams0.width = -2;
                            toolbar$LayoutParams0.height = -2;
                            toolbar$LayoutParams0.gravity = 0x800053;
                            return;
                        }
                        break;
                    }
                    default: {
                        throw new IllegalArgumentException("Invalid navigation mode " + v);
                    }
                }
            }
        }
    }

    @Override  // androidx.appcompat.widget.DecorToolbar
    public void setSubtitle(CharSequence charSequence0) {
        this.mSubtitle = charSequence0;
        if((this.mDisplayOpts & 8) != 0) {
            this.mToolbar.setSubtitle(charSequence0);
        }
    }

    @Override  // androidx.appcompat.widget.DecorToolbar
    public void setTitle(CharSequence charSequence0) {
        this.mTitleSet = true;
        this.setTitleInt(charSequence0);
    }

    private void setTitleInt(CharSequence charSequence0) {
        this.mTitle = charSequence0;
        if((this.mDisplayOpts & 8) != 0) {
            this.mToolbar.setTitle(charSequence0);
            if(this.mTitleSet) {
                ViewCompat.setAccessibilityPaneTitle(this.mToolbar.getRootView(), charSequence0);
            }
        }
    }

    @Override  // androidx.appcompat.widget.DecorToolbar
    public void setVisibility(int v) {
        this.mToolbar.setVisibility(v);
    }

    @Override  // androidx.appcompat.widget.DecorToolbar
    public void setWindowCallback(Window.Callback window$Callback0) {
        this.mWindowCallback = window$Callback0;
    }

    @Override  // androidx.appcompat.widget.DecorToolbar
    public void setWindowTitle(CharSequence charSequence0) {
        if(!this.mTitleSet) {
            this.setTitleInt(charSequence0);
        }
    }

    @Override  // androidx.appcompat.widget.DecorToolbar
    public ViewPropertyAnimatorCompat setupAnimatorToVisibility(int v, long v1) {
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat0 = ViewCompat.animate(this.mToolbar);
        return v == 0 ? viewPropertyAnimatorCompat0.alpha(1.0f).setDuration(v1).setListener(new ViewPropertyAnimatorListenerAdapter() {
            private boolean mCanceled;

            {
                int v = 0;  // 捕获的参数
                this.mCanceled = false;
            }

            @Override  // androidx.core.view.ViewPropertyAnimatorListenerAdapter
            public void onAnimationCancel(View view0) {
                this.mCanceled = true;
            }

            @Override  // androidx.core.view.ViewPropertyAnimatorListenerAdapter
            public void onAnimationEnd(View view0) {
                if(!this.mCanceled) {
                    ToolbarWidgetWrapper.this.mToolbar.setVisibility(0);
                }
            }

            @Override  // androidx.core.view.ViewPropertyAnimatorListenerAdapter
            public void onAnimationStart(View view0) {
                ToolbarWidgetWrapper.this.mToolbar.setVisibility(0);
            }
        }) : viewPropertyAnimatorCompat0.alpha(0.0f).setDuration(v1).setListener(new ViewPropertyAnimatorListenerAdapter() {
            private boolean mCanceled;

            {
                int v = v;  // 捕获的参数 （可能与外部方法变量命名冲突；考虑手动重命名）
                this.mCanceled = false;
            }

            @Override  // androidx.core.view.ViewPropertyAnimatorListenerAdapter
            public void onAnimationCancel(View view0) {
                this.mCanceled = true;
            }

            @Override  // androidx.core.view.ViewPropertyAnimatorListenerAdapter
            public void onAnimationEnd(View view0) {
                if(!this.mCanceled) {
                    ToolbarWidgetWrapper.this.mToolbar.setVisibility(v);
                }
            }

            @Override  // androidx.core.view.ViewPropertyAnimatorListenerAdapter
            public void onAnimationStart(View view0) {
                ToolbarWidgetWrapper.this.mToolbar.setVisibility(0);
            }
        });
    }

    @Override  // androidx.appcompat.widget.DecorToolbar
    public boolean showOverflowMenu() {
        return this.mToolbar.showOverflowMenu();
    }

    private void updateHomeAccessibility() {
        if((this.mDisplayOpts & 4) != 0) {
            if(TextUtils.isEmpty(this.mHomeDescription)) {
                this.mToolbar.setNavigationContentDescription(this.mDefaultNavigationContentDescription);
                return;
            }
            this.mToolbar.setNavigationContentDescription(this.mHomeDescription);
        }
    }

    private void updateNavigationIcon() {
        if((this.mDisplayOpts & 4) != 0) {
            this.mToolbar.setNavigationIcon((this.mNavIcon == null ? this.mDefaultNavigationIcon : this.mNavIcon));
            return;
        }
        this.mToolbar.setNavigationIcon(null);
    }

    private void updateToolbarLogo() {
        Drawable drawable0;
        int v = this.mDisplayOpts;
        if((v & 2) == 0) {
            drawable0 = null;
        }
        else if((v & 1) != 0) {
            drawable0 = this.mLogo;
            if(drawable0 == null) {
                drawable0 = this.mIcon;
            }
        }
        else {
            drawable0 = this.mIcon;
        }
        this.mToolbar.setLogo(drawable0);
    }
}

