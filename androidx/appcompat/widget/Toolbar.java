package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import android.text.Layout;
import android.text.TextUtils.TruncateAt;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.appcompat.R.attr;
import androidx.appcompat.R.styleable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.view.CollapsibleActionView;
import androidx.appcompat.view.SupportMenuInflater;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuPresenter.Callback;
import androidx.appcompat.view.menu.MenuPresenter;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.view.menu.SubMenuBuilder;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuHost;
import androidx.core.view.MenuHostHelper;
import androidx.core.view.MenuProvider;
import androidx.core.view.ViewCompat;
import androidx.customview.view.AbsSavedState;
import androidx.lifecycle.Lifecycle.State;
import androidx.lifecycle.LifecycleOwner;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Toolbar extends ViewGroup implements MenuHost {
    static class Api33Impl {
        static OnBackInvokedDispatcher findOnBackInvokedDispatcher(View view0) {
            return view0.findOnBackInvokedDispatcher();
        }

        static OnBackInvokedCallback newOnBackInvokedCallback(Runnable runnable0) {
            Objects.requireNonNull(runnable0);
            return new Toolbar.Api33Impl..ExternalSyntheticLambda0(runnable0);
        }

        static void tryRegisterOnBackInvokedCallback(Object object0, Object object1) {
            ((OnBackInvokedDispatcher)object0).registerOnBackInvokedCallback(1000000, ((OnBackInvokedCallback)object1));
        }

        static void tryUnregisterOnBackInvokedCallback(Object object0, Object object1) {
            ((OnBackInvokedDispatcher)object0).unregisterOnBackInvokedCallback(((OnBackInvokedCallback)object1));
        }
    }

    class ExpandedActionViewMenuPresenter implements MenuPresenter {
        MenuItemImpl mCurrentExpandedItem;
        MenuBuilder mMenu;

        @Override  // androidx.appcompat.view.menu.MenuPresenter
        public boolean collapseItemActionView(MenuBuilder menuBuilder0, MenuItemImpl menuItemImpl0) {
            if(Toolbar.this.mExpandedActionView instanceof CollapsibleActionView) {
                ((CollapsibleActionView)Toolbar.this.mExpandedActionView).onActionViewCollapsed();
            }
            Toolbar.this.removeView(Toolbar.this.mExpandedActionView);
            Toolbar.this.removeView(Toolbar.this.mCollapseButtonView);
            Toolbar.this.mExpandedActionView = null;
            Toolbar.this.addChildrenForExpandedActionView();
            this.mCurrentExpandedItem = null;
            Toolbar.this.requestLayout();
            menuItemImpl0.setActionViewExpanded(false);
            Toolbar.this.updateBackInvokedCallbackState();
            return true;
        }

        @Override  // androidx.appcompat.view.menu.MenuPresenter
        public boolean expandItemActionView(MenuBuilder menuBuilder0, MenuItemImpl menuItemImpl0) {
            Toolbar.this.ensureCollapseButtonView();
            ViewParent viewParent0 = Toolbar.this.mCollapseButtonView.getParent();
            Toolbar toolbar0 = Toolbar.this;
            if(viewParent0 != toolbar0) {
                if(viewParent0 instanceof ViewGroup) {
                    ((ViewGroup)viewParent0).removeView(toolbar0.mCollapseButtonView);
                }
                Toolbar.this.addView(Toolbar.this.mCollapseButtonView);
            }
            Toolbar.this.mExpandedActionView = menuItemImpl0.getActionView();
            this.mCurrentExpandedItem = menuItemImpl0;
            ViewParent viewParent1 = Toolbar.this.mExpandedActionView.getParent();
            Toolbar toolbar1 = Toolbar.this;
            if(viewParent1 != toolbar1) {
                if(viewParent1 instanceof ViewGroup) {
                    ((ViewGroup)viewParent1).removeView(toolbar1.mExpandedActionView);
                }
                LayoutParams toolbar$LayoutParams0 = Toolbar.this.generateDefaultLayoutParams();
                toolbar$LayoutParams0.gravity = Toolbar.this.mButtonGravity & 0x70 | 0x800003;
                toolbar$LayoutParams0.mViewType = 2;
                Toolbar.this.mExpandedActionView.setLayoutParams(toolbar$LayoutParams0);
                Toolbar.this.addView(Toolbar.this.mExpandedActionView);
            }
            Toolbar.this.removeChildrenForExpandedActionView();
            Toolbar.this.requestLayout();
            menuItemImpl0.setActionViewExpanded(true);
            if(Toolbar.this.mExpandedActionView instanceof CollapsibleActionView) {
                ((CollapsibleActionView)Toolbar.this.mExpandedActionView).onActionViewExpanded();
            }
            Toolbar.this.updateBackInvokedCallbackState();
            return true;
        }

        @Override  // androidx.appcompat.view.menu.MenuPresenter
        public boolean flagActionItems() {
            return false;
        }

        @Override  // androidx.appcompat.view.menu.MenuPresenter
        public int getId() {
            return 0;
        }

        @Override  // androidx.appcompat.view.menu.MenuPresenter
        public MenuView getMenuView(ViewGroup viewGroup0) {
            return null;
        }

        @Override  // androidx.appcompat.view.menu.MenuPresenter
        public void initForMenu(Context context0, MenuBuilder menuBuilder0) {
            MenuBuilder menuBuilder1 = this.mMenu;
            if(menuBuilder1 != null) {
                MenuItemImpl menuItemImpl0 = this.mCurrentExpandedItem;
                if(menuItemImpl0 != null) {
                    menuBuilder1.collapseItemActionView(menuItemImpl0);
                }
            }
            this.mMenu = menuBuilder0;
        }

        @Override  // androidx.appcompat.view.menu.MenuPresenter
        public void onCloseMenu(MenuBuilder menuBuilder0, boolean z) {
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
            return false;
        }

        @Override  // androidx.appcompat.view.menu.MenuPresenter
        public void setCallback(Callback menuPresenter$Callback0) {
        }

        @Override  // androidx.appcompat.view.menu.MenuPresenter
        public void updateMenuView(boolean z) {
            if(this.mCurrentExpandedItem != null) {
                MenuBuilder menuBuilder0 = this.mMenu;
                if(menuBuilder0 != null) {
                    int v = menuBuilder0.size();
                    for(int v1 = 0; v1 < v; ++v1) {
                        if(this.mMenu.getItem(v1) == this.mCurrentExpandedItem) {
                            return;
                        }
                    }
                }
                this.collapseItemActionView(this.mMenu, this.mCurrentExpandedItem);
            }
        }
    }

    public static class LayoutParams extends androidx.appcompat.app.ActionBar.LayoutParams {
        static final int CUSTOM = 0;
        static final int EXPANDED = 2;
        static final int SYSTEM = 1;
        int mViewType;

        public LayoutParams(int v) {
            this(-2, -1, v);
        }

        public LayoutParams(int v, int v1) {
            super(v, v1);
            this.mViewType = 0;
            this.gravity = 0x800013;
        }

        public LayoutParams(int v, int v1, int v2) {
            super(v, v1);
            this.mViewType = 0;
            this.gravity = v2;
        }

        public LayoutParams(Context context0, AttributeSet attributeSet0) {
            super(context0, attributeSet0);
            this.mViewType = 0;
        }

        public LayoutParams(ViewGroup.LayoutParams viewGroup$LayoutParams0) {
            super(viewGroup$LayoutParams0);
            this.mViewType = 0;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0) {
            super(viewGroup$MarginLayoutParams0);
            this.mViewType = 0;
            this.copyMarginsFromCompat(viewGroup$MarginLayoutParams0);
        }

        public LayoutParams(androidx.appcompat.app.ActionBar.LayoutParams actionBar$LayoutParams0) {
            super(actionBar$LayoutParams0);
            this.mViewType = 0;
        }

        public LayoutParams(LayoutParams toolbar$LayoutParams0) {
            super(toolbar$LayoutParams0);
            this.mViewType = toolbar$LayoutParams0.mViewType;
        }

        void copyMarginsFromCompat(ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0) {
            this.leftMargin = viewGroup$MarginLayoutParams0.leftMargin;
            this.topMargin = viewGroup$MarginLayoutParams0.topMargin;
            this.rightMargin = viewGroup$MarginLayoutParams0.rightMargin;
            this.bottomMargin = viewGroup$MarginLayoutParams0.bottomMargin;
        }
    }

    public interface OnMenuItemClickListener {
        boolean onMenuItemClick(MenuItem arg1);
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator CREATOR;
        int expandedMenuItemId;
        boolean isOverflowOpen;

        static {
            SavedState.CREATOR = new Parcelable.ClassLoaderCreator() {
                public SavedState createFromParcel(Parcel parcel0) {
                    return new SavedState(parcel0, null);
                }

                public SavedState createFromParcel(Parcel parcel0, ClassLoader classLoader0) {
                    return new SavedState(parcel0, classLoader0);
                }

                @Override  // android.os.Parcelable$Creator
                public Object createFromParcel(Parcel parcel0) {
                    return this.createFromParcel(parcel0);
                }

                @Override  // android.os.Parcelable$ClassLoaderCreator
                public Object createFromParcel(Parcel parcel0, ClassLoader classLoader0) {
                    return this.createFromParcel(parcel0, classLoader0);
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

        public SavedState(Parcel parcel0) {
            this(parcel0, null);
        }

        public SavedState(Parcel parcel0, ClassLoader classLoader0) {
            super(parcel0, classLoader0);
            this.expandedMenuItemId = parcel0.readInt();
            this.isOverflowOpen = parcel0.readInt() != 0;
        }

        public SavedState(Parcelable parcelable0) {
            super(parcelable0);
        }

        @Override  // androidx.customview.view.AbsSavedState
        public void writeToParcel(Parcel parcel0, int v) {
            super.writeToParcel(parcel0, v);
            parcel0.writeInt(this.expandedMenuItemId);
            parcel0.writeInt(((int)this.isOverflowOpen));
        }
    }

    private static final String TAG = "Toolbar";
    private Callback mActionMenuPresenterCallback;
    private OnBackInvokedCallback mBackInvokedCallback;
    private boolean mBackInvokedCallbackEnabled;
    private OnBackInvokedDispatcher mBackInvokedDispatcher;
    int mButtonGravity;
    ImageButton mCollapseButtonView;
    private CharSequence mCollapseDescription;
    private Drawable mCollapseIcon;
    private boolean mCollapsible;
    private int mContentInsetEndWithActions;
    private int mContentInsetStartWithNavigation;
    private RtlSpacingHelper mContentInsets;
    private boolean mEatingHover;
    private boolean mEatingTouch;
    View mExpandedActionView;
    private ExpandedActionViewMenuPresenter mExpandedMenuPresenter;
    private int mGravity;
    private final ArrayList mHiddenViews;
    private ImageView mLogoView;
    private int mMaxButtonHeight;
    androidx.appcompat.view.menu.MenuBuilder.Callback mMenuBuilderCallback;
    final MenuHostHelper mMenuHostHelper;
    ActionMenuView mMenuView;
    private final androidx.appcompat.widget.ActionMenuView.OnMenuItemClickListener mMenuViewItemClickListener;
    private ImageButton mNavButtonView;
    OnMenuItemClickListener mOnMenuItemClickListener;
    private ActionMenuPresenter mOuterActionMenuPresenter;
    private Context mPopupContext;
    private int mPopupTheme;
    private ArrayList mProvidedMenuItems;
    private final Runnable mShowOverflowMenuRunnable;
    private CharSequence mSubtitleText;
    private int mSubtitleTextAppearance;
    private ColorStateList mSubtitleTextColor;
    private TextView mSubtitleTextView;
    private final int[] mTempMargins;
    private final ArrayList mTempViews;
    private int mTitleMarginBottom;
    private int mTitleMarginEnd;
    private int mTitleMarginStart;
    private int mTitleMarginTop;
    private CharSequence mTitleText;
    private int mTitleTextAppearance;
    private ColorStateList mTitleTextColor;
    private TextView mTitleTextView;
    private ToolbarWidgetWrapper mWrapper;

    public Toolbar(Context context0) {
        this(context0, null);
    }

    public Toolbar(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, attr.toolbarStyle);
    }

    public Toolbar(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        this.mGravity = 0x800013;
        this.mTempViews = new ArrayList();
        this.mHiddenViews = new ArrayList();
        this.mTempMargins = new int[2];
        this.mMenuHostHelper = new MenuHostHelper(() -> {
            for(Object object0: this.mProvidedMenuItems) {
                this.getMenu().removeItem(((MenuItem)object0).getItemId());
            }
            this.onCreateMenu();
        });
        this.mProvidedMenuItems = new ArrayList();
        this.mMenuViewItemClickListener = new androidx.appcompat.widget.ActionMenuView.OnMenuItemClickListener() {
            @Override  // androidx.appcompat.widget.ActionMenuView$OnMenuItemClickListener
            public boolean onMenuItemClick(MenuItem menuItem0) {
                if(Toolbar.this.mMenuHostHelper.onMenuItemSelected(menuItem0)) {
                    return true;
                }
                return Toolbar.this.mOnMenuItemClickListener == null ? false : Toolbar.this.mOnMenuItemClickListener.onMenuItemClick(menuItem0);
            }
        };
        this.mShowOverflowMenuRunnable = () -> Toolbar.this.mMenuView != null && Toolbar.this.mMenuView.showOverflowMenu();
        TintTypedArray tintTypedArray0 = TintTypedArray.obtainStyledAttributes(this.getContext(), attributeSet0, styleable.Toolbar, v, 0);
        ViewCompat.saveAttributeDataForStyleable(this, context0, styleable.Toolbar, attributeSet0, tintTypedArray0.getWrappedTypeArray(), v, 0);
        this.mTitleTextAppearance = tintTypedArray0.getResourceId(styleable.Toolbar_titleTextAppearance, 0);
        this.mSubtitleTextAppearance = tintTypedArray0.getResourceId(styleable.Toolbar_subtitleTextAppearance, 0);
        this.mGravity = tintTypedArray0.getInteger(styleable.Toolbar_android_gravity, this.mGravity);
        this.mButtonGravity = tintTypedArray0.getInteger(styleable.Toolbar_buttonGravity, 0x30);
        int v1 = tintTypedArray0.getDimensionPixelOffset(styleable.Toolbar_titleMargin, 0);
        if(tintTypedArray0.hasValue(styleable.Toolbar_titleMargins)) {
            v1 = tintTypedArray0.getDimensionPixelOffset(styleable.Toolbar_titleMargins, v1);
        }
        this.mTitleMarginBottom = v1;
        this.mTitleMarginTop = v1;
        this.mTitleMarginEnd = v1;
        this.mTitleMarginStart = v1;
        int v2 = tintTypedArray0.getDimensionPixelOffset(styleable.Toolbar_titleMarginStart, -1);
        if(v2 >= 0) {
            this.mTitleMarginStart = v2;
        }
        int v3 = tintTypedArray0.getDimensionPixelOffset(styleable.Toolbar_titleMarginEnd, -1);
        if(v3 >= 0) {
            this.mTitleMarginEnd = v3;
        }
        int v4 = tintTypedArray0.getDimensionPixelOffset(styleable.Toolbar_titleMarginTop, -1);
        if(v4 >= 0) {
            this.mTitleMarginTop = v4;
        }
        int v5 = tintTypedArray0.getDimensionPixelOffset(styleable.Toolbar_titleMarginBottom, -1);
        if(v5 >= 0) {
            this.mTitleMarginBottom = v5;
        }
        this.mMaxButtonHeight = tintTypedArray0.getDimensionPixelSize(styleable.Toolbar_maxButtonHeight, -1);
        int v6 = tintTypedArray0.getDimensionPixelOffset(styleable.Toolbar_contentInsetStart, 0x80000000);
        int v7 = tintTypedArray0.getDimensionPixelOffset(styleable.Toolbar_contentInsetEnd, 0x80000000);
        int v8 = tintTypedArray0.getDimensionPixelSize(styleable.Toolbar_contentInsetLeft, 0);
        int v9 = tintTypedArray0.getDimensionPixelSize(styleable.Toolbar_contentInsetRight, 0);
        this.ensureContentInsets();
        this.mContentInsets.setAbsolute(v8, v9);
        if(v6 != 0x80000000 || v7 != 0x80000000) {
            this.mContentInsets.setRelative(v6, v7);
        }
        this.mContentInsetStartWithNavigation = tintTypedArray0.getDimensionPixelOffset(styleable.Toolbar_contentInsetStartWithNavigation, 0x80000000);
        this.mContentInsetEndWithActions = tintTypedArray0.getDimensionPixelOffset(styleable.Toolbar_contentInsetEndWithActions, 0x80000000);
        this.mCollapseIcon = tintTypedArray0.getDrawable(styleable.Toolbar_collapseIcon);
        this.mCollapseDescription = tintTypedArray0.getText(styleable.Toolbar_collapseContentDescription);
        CharSequence charSequence0 = tintTypedArray0.getText(styleable.Toolbar_title);
        if(!TextUtils.isEmpty(charSequence0)) {
            this.setTitle(charSequence0);
        }
        CharSequence charSequence1 = tintTypedArray0.getText(styleable.Toolbar_subtitle);
        if(!TextUtils.isEmpty(charSequence1)) {
            this.setSubtitle(charSequence1);
        }
        this.mPopupContext = this.getContext();
        this.setPopupTheme(tintTypedArray0.getResourceId(styleable.Toolbar_popupTheme, 0));
        Drawable drawable0 = tintTypedArray0.getDrawable(styleable.Toolbar_navigationIcon);
        if(drawable0 != null) {
            this.setNavigationIcon(drawable0);
        }
        CharSequence charSequence2 = tintTypedArray0.getText(styleable.Toolbar_navigationContentDescription);
        if(!TextUtils.isEmpty(charSequence2)) {
            this.setNavigationContentDescription(charSequence2);
        }
        Drawable drawable1 = tintTypedArray0.getDrawable(styleable.Toolbar_logo);
        if(drawable1 != null) {
            this.setLogo(drawable1);
        }
        CharSequence charSequence3 = tintTypedArray0.getText(styleable.Toolbar_logoDescription);
        if(!TextUtils.isEmpty(charSequence3)) {
            this.setLogoDescription(charSequence3);
        }
        if(tintTypedArray0.hasValue(styleable.Toolbar_titleTextColor)) {
            this.setTitleTextColor(tintTypedArray0.getColorStateList(styleable.Toolbar_titleTextColor));
        }
        if(tintTypedArray0.hasValue(styleable.Toolbar_subtitleTextColor)) {
            this.setSubtitleTextColor(tintTypedArray0.getColorStateList(styleable.Toolbar_subtitleTextColor));
        }
        if(tintTypedArray0.hasValue(styleable.Toolbar_menu)) {
            this.inflateMenu(tintTypedArray0.getResourceId(styleable.Toolbar_menu, 0));
        }
        tintTypedArray0.recycle();
    }

    void addChildrenForExpandedActionView() {
        for(int v = this.mHiddenViews.size() - 1; v >= 0; --v) {
            this.addView(((View)this.mHiddenViews.get(v)));
        }
        this.mHiddenViews.clear();
    }

    private void addCustomViewsWithGravity(List list0, int v) {
        boolean z = this.getLayoutDirection() == 1;
        int v2 = this.getChildCount();
        int v3 = GravityCompat.getAbsoluteGravity(v, this.getLayoutDirection());
        list0.clear();
        if(z) {
            for(int v4 = v2 - 1; v4 >= 0; --v4) {
                View view0 = this.getChildAt(v4);
                LayoutParams toolbar$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
                if(toolbar$LayoutParams0.mViewType == 0 && this.shouldLayout(view0) && this.getChildHorizontalGravity(toolbar$LayoutParams0.gravity) == v3) {
                    list0.add(view0);
                }
            }
            return;
        }
        for(int v1 = 0; v1 < v2; ++v1) {
            View view1 = this.getChildAt(v1);
            LayoutParams toolbar$LayoutParams1 = (LayoutParams)view1.getLayoutParams();
            if(toolbar$LayoutParams1.mViewType == 0 && this.shouldLayout(view1) && this.getChildHorizontalGravity(toolbar$LayoutParams1.gravity) == v3) {
                list0.add(view1);
            }
        }
    }

    @Override  // androidx.core.view.MenuHost
    public void addMenuProvider(MenuProvider menuProvider0) {
        this.mMenuHostHelper.addMenuProvider(menuProvider0);
    }

    @Override  // androidx.core.view.MenuHost
    public void addMenuProvider(MenuProvider menuProvider0, LifecycleOwner lifecycleOwner0) {
        this.mMenuHostHelper.addMenuProvider(menuProvider0, lifecycleOwner0);
    }

    @Override  // androidx.core.view.MenuHost
    public void addMenuProvider(MenuProvider menuProvider0, LifecycleOwner lifecycleOwner0, State lifecycle$State0) {
        this.mMenuHostHelper.addMenuProvider(menuProvider0, lifecycleOwner0, lifecycle$State0);
    }

    private void addSystemView(View view0, boolean z) {
        LayoutParams toolbar$LayoutParams0;
        ViewGroup.LayoutParams viewGroup$LayoutParams0 = view0.getLayoutParams();
        if(viewGroup$LayoutParams0 == null) {
            toolbar$LayoutParams0 = this.generateDefaultLayoutParams();
        }
        else {
            toolbar$LayoutParams0 = this.checkLayoutParams(viewGroup$LayoutParams0) ? ((LayoutParams)viewGroup$LayoutParams0) : this.generateLayoutParams(viewGroup$LayoutParams0);
        }
        toolbar$LayoutParams0.mViewType = 1;
        if(z && this.mExpandedActionView != null) {
            view0.setLayoutParams(toolbar$LayoutParams0);
            this.mHiddenViews.add(view0);
            return;
        }
        this.addView(view0, toolbar$LayoutParams0);
    }

    public boolean canShowOverflowMenu() {
        return this.getVisibility() == 0 && (this.mMenuView != null && this.mMenuView.isOverflowReserved());
    }

    // 去混淆评级： 低(20)
    @Override  // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        return super.checkLayoutParams(viewGroup$LayoutParams0) && viewGroup$LayoutParams0 instanceof LayoutParams;
    }

    // 检测为 Lambda 实现
    public void collapseActionView() [...]

    public void dismissPopupMenus() {
        ActionMenuView actionMenuView0 = this.mMenuView;
        if(actionMenuView0 != null) {
            actionMenuView0.dismissPopupMenus();
        }
    }

    void ensureCollapseButtonView() {
        if(this.mCollapseButtonView == null) {
            AppCompatImageButton appCompatImageButton0 = new AppCompatImageButton(this.getContext(), null, attr.toolbarNavigationButtonStyle);
            this.mCollapseButtonView = appCompatImageButton0;
            appCompatImageButton0.setImageDrawable(this.mCollapseIcon);
            this.mCollapseButtonView.setContentDescription(this.mCollapseDescription);
            LayoutParams toolbar$LayoutParams0 = this.generateDefaultLayoutParams();
            toolbar$LayoutParams0.gravity = this.mButtonGravity & 0x70 | 0x800003;
            toolbar$LayoutParams0.mViewType = 2;
            this.mCollapseButtonView.setLayoutParams(toolbar$LayoutParams0);
            this.mCollapseButtonView.setOnClickListener((/* 缺少LAMBDA参数 */) -> {
                MenuItemImpl menuItemImpl0 = Toolbar.this.mExpandedMenuPresenter == null ? null : Toolbar.this.mExpandedMenuPresenter.mCurrentExpandedItem;
                if(menuItemImpl0 != null) {
                    menuItemImpl0.collapseActionView();
                }
            });
        }

        class androidx.appcompat.widget.Toolbar.4 implements View.OnClickListener {
            @Override  // android.view.View$OnClickListener
            public void onClick(View view0) {
                Toolbar.this.collapseActionView();
            }
        }

    }

    private void ensureContentInsets() {
        if(this.mContentInsets == null) {
            this.mContentInsets = new RtlSpacingHelper();
        }
    }

    private void ensureLogoView() {
        if(this.mLogoView == null) {
            this.mLogoView = new AppCompatImageView(this.getContext());
        }
    }

    private void ensureMenu() {
        this.ensureMenuView();
        if(this.mMenuView.peekMenu() == null) {
            MenuBuilder menuBuilder0 = (MenuBuilder)this.mMenuView.getMenu();
            if(this.mExpandedMenuPresenter == null) {
                this.mExpandedMenuPresenter = new ExpandedActionViewMenuPresenter(this);
            }
            this.mMenuView.setExpandedActionViewsExclusive(true);
            menuBuilder0.addMenuPresenter(this.mExpandedMenuPresenter, this.mPopupContext);
            this.updateBackInvokedCallbackState();
        }
    }

    private void ensureMenuView() {
        if(this.mMenuView == null) {
            ActionMenuView actionMenuView0 = new ActionMenuView(this.getContext());
            this.mMenuView = actionMenuView0;
            actionMenuView0.setPopupTheme(this.mPopupTheme);
            this.mMenuView.setOnMenuItemClickListener(this.mMenuViewItemClickListener);
            this.mMenuView.setMenuCallbacks(this.mActionMenuPresenterCallback, new androidx.appcompat.view.menu.MenuBuilder.Callback() {
                @Override  // androidx.appcompat.view.menu.MenuBuilder$Callback
                public boolean onMenuItemSelected(MenuBuilder menuBuilder0, MenuItem menuItem0) {
                    return Toolbar.this.mMenuBuilderCallback != null && Toolbar.this.mMenuBuilderCallback.onMenuItemSelected(menuBuilder0, menuItem0);
                }

                @Override  // androidx.appcompat.view.menu.MenuBuilder$Callback
                public void onMenuModeChange(MenuBuilder menuBuilder0) {
                    if(!Toolbar.this.mMenuView.isOverflowMenuShowing()) {
                        Toolbar.this.mMenuHostHelper.onPrepareMenu(menuBuilder0);
                    }
                    if(Toolbar.this.mMenuBuilderCallback != null) {
                        Toolbar.this.mMenuBuilderCallback.onMenuModeChange(menuBuilder0);
                    }
                }
            });
            LayoutParams toolbar$LayoutParams0 = this.generateDefaultLayoutParams();
            toolbar$LayoutParams0.gravity = this.mButtonGravity & 0x70 | 0x800005;
            this.mMenuView.setLayoutParams(toolbar$LayoutParams0);
            this.addSystemView(this.mMenuView, false);
        }
    }

    private void ensureNavButtonView() {
        if(this.mNavButtonView == null) {
            this.mNavButtonView = new AppCompatImageButton(this.getContext(), null, attr.toolbarNavigationButtonStyle);
            LayoutParams toolbar$LayoutParams0 = this.generateDefaultLayoutParams();
            toolbar$LayoutParams0.gravity = this.mButtonGravity & 0x70 | 0x800003;
            this.mNavButtonView.setLayoutParams(toolbar$LayoutParams0);
        }
    }

    @Override  // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return this.generateDefaultLayoutParams();
    }

    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    @Override  // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet0) {
        return this.generateLayoutParams(attributeSet0);
    }

    @Override  // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        return this.generateLayoutParams(viewGroup$LayoutParams0);
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet0) {
        return new LayoutParams(this.getContext(), attributeSet0);
    }

    protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        if(viewGroup$LayoutParams0 instanceof LayoutParams) {
            return new LayoutParams(((LayoutParams)viewGroup$LayoutParams0));
        }
        if(viewGroup$LayoutParams0 instanceof androidx.appcompat.app.ActionBar.LayoutParams) {
            return new LayoutParams(((androidx.appcompat.app.ActionBar.LayoutParams)viewGroup$LayoutParams0));
        }
        return viewGroup$LayoutParams0 instanceof ViewGroup.MarginLayoutParams ? new LayoutParams(((ViewGroup.MarginLayoutParams)viewGroup$LayoutParams0)) : new LayoutParams(viewGroup$LayoutParams0);
    }

    private int getChildHorizontalGravity(int v) {
        int v1 = this.getLayoutDirection();
        int v2 = GravityCompat.getAbsoluteGravity(v, v1);
        if((v2 & 7) != 1 && (v2 & 7) != 3 && (v2 & 7) != 5) {
            return v1 == 1 ? 5 : 3;
        }
        return v2 & 7;
    }

    private int getChildTop(View view0, int v) {
        LayoutParams toolbar$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
        int v1 = view0.getMeasuredHeight();
        int v2 = v <= 0 ? 0 : (v1 - v) / 2;
        switch(this.getChildVerticalGravity(toolbar$LayoutParams0.gravity)) {
            case 0x30: {
                return this.getPaddingTop() - v2;
            }
            case 80: {
                return this.getHeight() - this.getPaddingBottom() - v1 - toolbar$LayoutParams0.bottomMargin - v2;
            }
            default: {
                int v3 = this.getPaddingTop();
                int v4 = this.getPaddingBottom();
                int v5 = this.getHeight();
                int v6 = (v5 - v3 - v4 - v1) / 2;
                if(v6 < toolbar$LayoutParams0.topMargin) {
                    return v3 + toolbar$LayoutParams0.topMargin;
                }
                int v7 = v5 - v4 - v1 - v6 - v3;
                if(v7 < toolbar$LayoutParams0.bottomMargin) {
                    v6 = Math.max(0, v6 - (toolbar$LayoutParams0.bottomMargin - v7));
                }
                return v3 + v6;
            }
        }
    }

    private int getChildVerticalGravity(int v) {
        int v1 = v & 0x70;
        return v1 == 16 || v1 == 0x30 || v1 == 80 ? v1 : this.mGravity & 0x70;
    }

    public CharSequence getCollapseContentDescription() {
        return this.mCollapseButtonView == null ? null : this.mCollapseButtonView.getContentDescription();
    }

    public Drawable getCollapseIcon() {
        return this.mCollapseButtonView == null ? null : this.mCollapseButtonView.getDrawable();
    }

    public int getContentInsetEnd() {
        return this.mContentInsets == null ? 0 : this.mContentInsets.getEnd();
    }

    public int getContentInsetEndWithActions() {
        return this.mContentInsetEndWithActions == 0x80000000 ? this.getContentInsetEnd() : this.mContentInsetEndWithActions;
    }

    public int getContentInsetLeft() {
        return this.mContentInsets == null ? 0 : this.mContentInsets.getLeft();
    }

    public int getContentInsetRight() {
        return this.mContentInsets == null ? 0 : this.mContentInsets.getRight();
    }

    public int getContentInsetStart() {
        return this.mContentInsets == null ? 0 : this.mContentInsets.getStart();
    }

    public int getContentInsetStartWithNavigation() {
        return this.mContentInsetStartWithNavigation == 0x80000000 ? this.getContentInsetStart() : this.mContentInsetStartWithNavigation;
    }

    public int getCurrentContentInsetEnd() {
        ActionMenuView actionMenuView0 = this.mMenuView;
        if(actionMenuView0 != null) {
            MenuBuilder menuBuilder0 = actionMenuView0.peekMenu();
            return menuBuilder0 == null || !menuBuilder0.hasVisibleItems() ? this.getContentInsetEnd() : Math.max(this.getContentInsetEnd(), Math.max(this.mContentInsetEndWithActions, 0));
        }
        return this.getContentInsetEnd();
    }

    public int getCurrentContentInsetLeft() {
        return this.getLayoutDirection() == 1 ? this.getCurrentContentInsetEnd() : this.getCurrentContentInsetStart();
    }

    public int getCurrentContentInsetRight() {
        return this.getLayoutDirection() == 1 ? this.getCurrentContentInsetStart() : this.getCurrentContentInsetEnd();
    }

    public int getCurrentContentInsetStart() {
        return this.getNavigationIcon() == null ? this.getContentInsetStart() : Math.max(this.getContentInsetStart(), Math.max(this.mContentInsetStartWithNavigation, 0));
    }

    private ArrayList getCurrentMenuItems() {
        ArrayList arrayList0 = new ArrayList();
        Menu menu0 = this.getMenu();
        for(int v = 0; v < menu0.size(); ++v) {
            arrayList0.add(menu0.getItem(v));
        }
        return arrayList0;
    }

    private int getHorizontalMargins(View view0) {
        ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0 = (ViewGroup.MarginLayoutParams)view0.getLayoutParams();
        return viewGroup$MarginLayoutParams0.getMarginStart() + viewGroup$MarginLayoutParams0.getMarginEnd();
    }

    public Drawable getLogo() {
        return this.mLogoView == null ? null : this.mLogoView.getDrawable();
    }

    public CharSequence getLogoDescription() {
        return this.mLogoView == null ? null : this.mLogoView.getContentDescription();
    }

    public Menu getMenu() {
        this.ensureMenu();
        return this.mMenuView.getMenu();
    }

    private MenuInflater getMenuInflater() {
        return new SupportMenuInflater(this.getContext());
    }

    View getNavButtonView() {
        return this.mNavButtonView;
    }

    public CharSequence getNavigationContentDescription() {
        return this.mNavButtonView == null ? null : this.mNavButtonView.getContentDescription();
    }

    public Drawable getNavigationIcon() {
        return this.mNavButtonView == null ? null : this.mNavButtonView.getDrawable();
    }

    ActionMenuPresenter getOuterActionMenuPresenter() {
        return this.mOuterActionMenuPresenter;
    }

    public Drawable getOverflowIcon() {
        this.ensureMenu();
        return this.mMenuView.getOverflowIcon();
    }

    Context getPopupContext() {
        return this.mPopupContext;
    }

    public int getPopupTheme() {
        return this.mPopupTheme;
    }

    public CharSequence getSubtitle() {
        return this.mSubtitleText;
    }

    final TextView getSubtitleTextView() {
        return this.mSubtitleTextView;
    }

    public CharSequence getTitle() {
        return this.mTitleText;
    }

    public int getTitleMarginBottom() {
        return this.mTitleMarginBottom;
    }

    public int getTitleMarginEnd() {
        return this.mTitleMarginEnd;
    }

    public int getTitleMarginStart() {
        return this.mTitleMarginStart;
    }

    public int getTitleMarginTop() {
        return this.mTitleMarginTop;
    }

    final TextView getTitleTextView() {
        return this.mTitleTextView;
    }

    private int getVerticalMargins(View view0) {
        ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0 = (ViewGroup.MarginLayoutParams)view0.getLayoutParams();
        return viewGroup$MarginLayoutParams0.topMargin + viewGroup$MarginLayoutParams0.bottomMargin;
    }

    private int getViewListMeasuredWidth(List list0, int[] arr_v) {
        int v = arr_v[0];
        int v1 = arr_v[1];
        int v2 = list0.size();
        int v3 = 0;
        int v4 = 0;
        while(v3 < v2) {
            View view0 = (View)list0.get(v3);
            LayoutParams toolbar$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
            int v5 = toolbar$LayoutParams0.leftMargin - v;
            int v6 = toolbar$LayoutParams0.rightMargin - v1;
            v4 += Math.max(0, v5) + view0.getMeasuredWidth() + Math.max(0, v6);
            ++v3;
            v1 = Math.max(0, -v6);
            v = Math.max(0, -v5);
        }
        return v4;
    }

    public DecorToolbar getWrapper() {
        if(this.mWrapper == null) {
            this.mWrapper = new ToolbarWidgetWrapper(this, true);
        }
        return this.mWrapper;
    }

    public boolean hasExpandedActionView() {
        return this.mExpandedMenuPresenter != null && this.mExpandedMenuPresenter.mCurrentExpandedItem != null;
    }

    public boolean hideOverflowMenu() {
        return this.mMenuView != null && this.mMenuView.hideOverflowMenu();
    }

    public void inflateMenu(int v) {
        this.getMenuInflater().inflate(v, this.getMenu());
    }

    // 检测为 Lambda 实现
    @Override  // androidx.core.view.MenuHost
    public void invalidateMenu() [...]

    public boolean isBackInvokedCallbackEnabled() {
        return this.mBackInvokedCallbackEnabled;
    }

    private boolean isChildOrHidden(View view0) {
        return view0.getParent() == this || this.mHiddenViews.contains(view0);
    }

    public boolean isOverflowMenuShowPending() {
        return this.mMenuView != null && this.mMenuView.isOverflowMenuShowPending();
    }

    public boolean isOverflowMenuShowing() {
        return this.mMenuView != null && this.mMenuView.isOverflowMenuShowing();
    }

    public boolean isTitleTruncated() {
        TextView textView0 = this.mTitleTextView;
        if(textView0 == null) {
            return false;
        }
        Layout layout0 = textView0.getLayout();
        if(layout0 == null) {
            return false;
        }
        int v = layout0.getLineCount();
        for(int v1 = 0; v1 < v; ++v1) {
            if(layout0.getEllipsisCount(v1) > 0) {
                return true;
            }
        }
        return false;
    }

    private int layoutChildLeft(View view0, int v, int[] arr_v, int v1) {
        LayoutParams toolbar$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
        int v2 = toolbar$LayoutParams0.leftMargin - arr_v[0];
        int v3 = v + Math.max(0, v2);
        arr_v[0] = Math.max(0, -v2);
        int v4 = this.getChildTop(view0, v1);
        int v5 = view0.getMeasuredWidth();
        view0.layout(v3, v4, v3 + v5, view0.getMeasuredHeight() + v4);
        return v3 + (v5 + toolbar$LayoutParams0.rightMargin);
    }

    private int layoutChildRight(View view0, int v, int[] arr_v, int v1) {
        LayoutParams toolbar$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
        int v2 = toolbar$LayoutParams0.rightMargin - arr_v[1];
        int v3 = v - Math.max(0, v2);
        arr_v[1] = Math.max(0, -v2);
        int v4 = this.getChildTop(view0, v1);
        int v5 = view0.getMeasuredWidth();
        view0.layout(v3 - v5, v4, v3, view0.getMeasuredHeight() + v4);
        return v3 - (v5 + toolbar$LayoutParams0.leftMargin);
    }

    private int measureChildCollapseMargins(View view0, int v, int v1, int v2, int v3, int[] arr_v) {
        ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0 = (ViewGroup.MarginLayoutParams)view0.getLayoutParams();
        int v4 = viewGroup$MarginLayoutParams0.leftMargin - arr_v[0];
        int v5 = viewGroup$MarginLayoutParams0.rightMargin - arr_v[1];
        int v6 = Math.max(0, v4) + Math.max(0, v5);
        arr_v[0] = Math.max(0, -v4);
        arr_v[1] = Math.max(0, -v5);
        view0.measure(Toolbar.getChildMeasureSpec(v, this.getPaddingLeft() + this.getPaddingRight() + v6 + v1, viewGroup$MarginLayoutParams0.width), Toolbar.getChildMeasureSpec(v2, this.getPaddingTop() + this.getPaddingBottom() + viewGroup$MarginLayoutParams0.topMargin + viewGroup$MarginLayoutParams0.bottomMargin + v3, viewGroup$MarginLayoutParams0.height));
        return view0.getMeasuredWidth() + v6;
    }

    private void measureChildConstrained(View view0, int v, int v1, int v2, int v3, int v4) {
        ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0 = (ViewGroup.MarginLayoutParams)view0.getLayoutParams();
        int v5 = Toolbar.getChildMeasureSpec(v, this.getPaddingLeft() + this.getPaddingRight() + viewGroup$MarginLayoutParams0.leftMargin + viewGroup$MarginLayoutParams0.rightMargin + v1, viewGroup$MarginLayoutParams0.width);
        int v6 = Toolbar.getChildMeasureSpec(v2, this.getPaddingTop() + this.getPaddingBottom() + viewGroup$MarginLayoutParams0.topMargin + viewGroup$MarginLayoutParams0.bottomMargin + v3, viewGroup$MarginLayoutParams0.height);
        int v7 = View.MeasureSpec.getMode(v6);
        if(v7 != 0x40000000 && v4 >= 0) {
            if(v7 != 0) {
                v4 = Math.min(View.MeasureSpec.getSize(v6), v4);
            }
            v6 = View.MeasureSpec.makeMeasureSpec(v4, 0x40000000);
        }
        view0.measure(v5, v6);
    }

    @Override  // android.view.ViewGroup
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.updateBackInvokedCallbackState();
    }

    private void onCreateMenu() {
        Menu menu0 = this.getMenu();
        ArrayList arrayList0 = this.getCurrentMenuItems();
        MenuInflater menuInflater0 = this.getMenuInflater();
        this.mMenuHostHelper.onCreateMenu(menu0, menuInflater0);
        ArrayList arrayList1 = this.getCurrentMenuItems();
        arrayList1.removeAll(arrayList0);
        this.mProvidedMenuItems = arrayList1;
    }

    @Override  // android.view.ViewGroup
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.removeCallbacks(this.mShowOverflowMenuRunnable);
        this.updateBackInvokedCallbackState();
    }

    @Override  // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent0) {
        int v = motionEvent0.getActionMasked();
        if(v == 9) {
            this.mEatingHover = false;
        }
        if(!this.mEatingHover && (v == 9 && !super.onHoverEvent(motionEvent0))) {
            this.mEatingHover = true;
        }
        if(v == 3 || v == 10) {
            this.mEatingHover = false;
        }
        return true;
    }

    @Override  // android.view.ViewGroup
    protected void onLayout(boolean z, int v, int v1, int v2, int v3) {
        int v34;
        int v33;
        int v29;
        int v42;
        int v38;
        int v25;
        int v22;
        int v15;
        int v14;
        int v4 = this.getLayoutDirection() == 1 ? 1 : 0;
        int v5 = this.getWidth();
        int v6 = this.getHeight();
        int v7 = this.getPaddingLeft();
        int v8 = this.getPaddingRight();
        int v9 = this.getPaddingTop();
        int v10 = this.getPaddingBottom();
        int v11 = v5 - v8;
        int[] arr_v = this.mTempMargins;
        arr_v[1] = 0;
        arr_v[0] = 0;
        int v12 = ViewCompat.getMinimumHeight(this);
        int v13 = v12 < 0 ? 0 : Math.min(v12, v3 - v1);
        if(!this.shouldLayout(this.mNavButtonView)) {
            v15 = v7;
            v14 = v11;
        }
        else if(v4 != 0) {
            v14 = this.layoutChildRight(this.mNavButtonView, v11, arr_v, v13);
            v15 = v7;
        }
        else {
            v15 = this.layoutChildLeft(this.mNavButtonView, v7, arr_v, v13);
            v14 = v11;
        }
        if(this.shouldLayout(this.mCollapseButtonView)) {
            if(v4 == 0) {
                v15 = this.layoutChildLeft(this.mCollapseButtonView, v15, arr_v, v13);
            }
            else {
                v14 = this.layoutChildRight(this.mCollapseButtonView, v14, arr_v, v13);
            }
        }
        if(this.shouldLayout(this.mMenuView)) {
            if(v4 == 0) {
                v14 = this.layoutChildRight(this.mMenuView, v14, arr_v, v13);
            }
            else {
                v15 = this.layoutChildLeft(this.mMenuView, v15, arr_v, v13);
            }
        }
        int v16 = this.getCurrentContentInsetLeft();
        int v17 = this.getCurrentContentInsetRight();
        arr_v[0] = Math.max(0, v16 - v15);
        arr_v[1] = Math.max(0, v17 - (v11 - v14));
        int v18 = Math.max(v15, v16);
        int v19 = Math.min(v14, v11 - v17);
        if(this.shouldLayout(this.mExpandedActionView)) {
            if(v4 == 0) {
                v18 = this.layoutChildLeft(this.mExpandedActionView, v18, arr_v, v13);
            }
            else {
                v19 = this.layoutChildRight(this.mExpandedActionView, v19, arr_v, v13);
            }
        }
        if(this.shouldLayout(this.mLogoView)) {
            if(v4 == 0) {
                v18 = this.layoutChildLeft(this.mLogoView, v18, arr_v, v13);
            }
            else {
                v19 = this.layoutChildRight(this.mLogoView, v19, arr_v, v13);
            }
        }
        boolean z1 = this.shouldLayout(this.mTitleTextView);
        boolean z2 = this.shouldLayout(this.mSubtitleTextView);
        if(z1) {
            LayoutParams toolbar$LayoutParams0 = (LayoutParams)this.mTitleTextView.getLayoutParams();
            int v20 = toolbar$LayoutParams0.topMargin;
            int v21 = this.mTitleTextView.getMeasuredHeight();
            v22 = toolbar$LayoutParams0.bottomMargin + (v20 + v21);
        }
        else {
            v22 = 0;
        }
        if(z2) {
            LayoutParams toolbar$LayoutParams1 = (LayoutParams)this.mSubtitleTextView.getLayoutParams();
            v22 += toolbar$LayoutParams1.topMargin + this.mSubtitleTextView.getMeasuredHeight() + toolbar$LayoutParams1.bottomMargin;
        }
        if(z1 || z2) {
            TextView textView0 = z2 ? this.mSubtitleTextView : this.mTitleTextView;
            LayoutParams toolbar$LayoutParams2 = (LayoutParams)(z1 ? this.mTitleTextView : this.mSubtitleTextView).getLayoutParams();
            LayoutParams toolbar$LayoutParams3 = (LayoutParams)textView0.getLayoutParams();
            boolean z3 = z1 && this.mTitleTextView.getMeasuredWidth() > 0 || z2 && this.mSubtitleTextView.getMeasuredWidth() > 0;
            switch(this.mGravity & 0x70) {
                case 0x30: {
                    v25 = this.getPaddingTop() + toolbar$LayoutParams2.topMargin + this.mTitleMarginTop;
                    break;
                }
                case 80: {
                    v25 = v6 - v10 - toolbar$LayoutParams3.bottomMargin - this.mTitleMarginBottom - v22;
                    break;
                }
                default: {
                    int v23 = (v6 - v9 - v10 - v22) / 2;
                    if(v23 < toolbar$LayoutParams2.topMargin + this.mTitleMarginTop) {
                        v23 = toolbar$LayoutParams2.topMargin + this.mTitleMarginTop;
                    }
                    else {
                        int v24 = v6 - v10 - v22 - v23 - v9;
                        if(v24 < toolbar$LayoutParams2.bottomMargin + this.mTitleMarginBottom) {
                            v23 = Math.max(0, v23 - (toolbar$LayoutParams3.bottomMargin + this.mTitleMarginBottom - v24));
                        }
                    }
                    v25 = v9 + v23;
                }
            }
            if(v4 == 0) {
                v34 = 0;
                int v35 = (z3 ? this.mTitleMarginStart : 0) - arr_v[0];
                v18 += Math.max(0, v35);
                arr_v[0] = Math.max(0, -v35);
                if(z1) {
                    LayoutParams toolbar$LayoutParams5 = (LayoutParams)this.mTitleTextView.getLayoutParams();
                    int v36 = this.mTitleTextView.getMeasuredWidth() + v18;
                    int v37 = this.mTitleTextView.getMeasuredHeight() + v25;
                    this.mTitleTextView.layout(v18, v25, v36, v37);
                    v38 = v36 + this.mTitleMarginEnd;
                    v25 = v37 + toolbar$LayoutParams5.bottomMargin;
                }
                else {
                    v38 = v18;
                }
                if(z2) {
                    int v39 = v25 + ((LayoutParams)this.mSubtitleTextView.getLayoutParams()).topMargin;
                    int v40 = this.mSubtitleTextView.getMeasuredWidth() + v18;
                    int v41 = this.mSubtitleTextView.getMeasuredHeight();
                    this.mSubtitleTextView.layout(v18, v39, v40, v41 + v39);
                    v42 = v40 + this.mTitleMarginEnd;
                }
                else {
                    v42 = v18;
                }
                if(z3) {
                    v18 = Math.max(v38, v42);
                }
            }
            else {
                int v26 = (z3 ? this.mTitleMarginStart : 0) - arr_v[1];
                v19 -= Math.max(0, v26);
                arr_v[1] = Math.max(0, -v26);
                if(z1) {
                    LayoutParams toolbar$LayoutParams4 = (LayoutParams)this.mTitleTextView.getLayoutParams();
                    int v27 = v19 - this.mTitleTextView.getMeasuredWidth();
                    int v28 = this.mTitleTextView.getMeasuredHeight() + v25;
                    this.mTitleTextView.layout(v27, v25, v19, v28);
                    v29 = v27 - this.mTitleMarginEnd;
                    v25 = v28 + toolbar$LayoutParams4.bottomMargin;
                }
                else {
                    v29 = v19;
                }
                if(z2) {
                    int v30 = v25 + ((LayoutParams)this.mSubtitleTextView.getLayoutParams()).topMargin;
                    int v31 = this.mSubtitleTextView.getMeasuredWidth();
                    int v32 = this.mSubtitleTextView.getMeasuredHeight();
                    this.mSubtitleTextView.layout(v19 - v31, v30, v19, v32 + v30);
                    v33 = v19 - this.mTitleMarginEnd;
                }
                else {
                    v33 = v19;
                }
                if(z3) {
                    v19 = Math.min(v29, v33);
                }
                v34 = 0;
            }
        }
        else {
            v34 = 0;
        }
        this.addCustomViewsWithGravity(this.mTempViews, 3);
        int v43 = this.mTempViews.size();
        int v44 = v18;
        for(int v45 = 0; v45 < v43; ++v45) {
            v44 = this.layoutChildLeft(((View)this.mTempViews.get(v45)), v44, arr_v, v13);
        }
        this.addCustomViewsWithGravity(this.mTempViews, 5);
        int v46 = this.mTempViews.size();
        for(int v47 = 0; v47 < v46; ++v47) {
            v19 = this.layoutChildRight(((View)this.mTempViews.get(v47)), v19, arr_v, v13);
        }
        this.addCustomViewsWithGravity(this.mTempViews, 1);
        int v48 = this.getViewListMeasuredWidth(this.mTempViews, arr_v);
        int v49 = v7 + (v5 - v7 - v8) / 2 - v48 / 2;
        int v50 = v48 + v49;
        if(v49 >= v44) {
            v44 = v50 <= v19 ? v49 : v49 - (v50 - v19);
        }
        int v51 = this.mTempViews.size();
        while(v34 < v51) {
            v44 = this.layoutChildLeft(((View)this.mTempViews.get(v34)), v44, arr_v, v13);
            ++v34;
        }
        this.mTempViews.clear();
    }

    @Override  // android.view.View
    protected void onMeasure(int v, int v1) {
        int v22;
        int v21;
        int v20;
        int v8;
        int v5;
        int v4;
        int v3;
        int[] arr_v = this.mTempMargins;
        boolean z = ViewUtils.isLayoutRtl(this);
        int v2 = 0;
        if(this.shouldLayout(this.mNavButtonView)) {
            this.measureChildConstrained(this.mNavButtonView, v, 0, v1, 0, this.mMaxButtonHeight);
            v3 = this.mNavButtonView.getMeasuredWidth() + this.getHorizontalMargins(this.mNavButtonView);
            v4 = Math.max(0, this.mNavButtonView.getMeasuredHeight() + this.getVerticalMargins(this.mNavButtonView));
            v5 = View.combineMeasuredStates(0, this.mNavButtonView.getMeasuredState());
        }
        else {
            v3 = 0;
            v4 = 0;
            v5 = 0;
        }
        if(this.shouldLayout(this.mCollapseButtonView)) {
            this.measureChildConstrained(this.mCollapseButtonView, v, 0, v1, 0, this.mMaxButtonHeight);
            v3 = this.mCollapseButtonView.getMeasuredWidth() + this.getHorizontalMargins(this.mCollapseButtonView);
            v4 = Math.max(v4, this.mCollapseButtonView.getMeasuredHeight() + this.getVerticalMargins(this.mCollapseButtonView));
            v5 = View.combineMeasuredStates(v5, this.mCollapseButtonView.getMeasuredState());
        }
        int v6 = this.getCurrentContentInsetStart();
        int v7 = Math.max(v6, v3);
        arr_v[z] = Math.max(0, v6 - v3);
        if(this.shouldLayout(this.mMenuView)) {
            this.measureChildConstrained(this.mMenuView, v, v7, v1, 0, this.mMaxButtonHeight);
            v8 = this.mMenuView.getMeasuredWidth() + this.getHorizontalMargins(this.mMenuView);
            v4 = Math.max(v4, this.mMenuView.getMeasuredHeight() + this.getVerticalMargins(this.mMenuView));
            v5 = View.combineMeasuredStates(v5, this.mMenuView.getMeasuredState());
        }
        else {
            v8 = 0;
        }
        int v9 = this.getCurrentContentInsetEnd();
        int v10 = v7 + Math.max(v9, v8);
        arr_v[!z] = Math.max(0, v9 - v8);
        if(this.shouldLayout(this.mExpandedActionView)) {
            v10 += this.measureChildCollapseMargins(this.mExpandedActionView, v, v10, v1, 0, arr_v);
            v4 = Math.max(v4, this.mExpandedActionView.getMeasuredHeight() + this.getVerticalMargins(this.mExpandedActionView));
            v5 = View.combineMeasuredStates(v5, this.mExpandedActionView.getMeasuredState());
        }
        if(this.shouldLayout(this.mLogoView)) {
            v10 += this.measureChildCollapseMargins(this.mLogoView, v, v10, v1, 0, arr_v);
            v4 = Math.max(v4, this.mLogoView.getMeasuredHeight() + this.getVerticalMargins(this.mLogoView));
            v5 = View.combineMeasuredStates(v5, this.mLogoView.getMeasuredState());
        }
        int v11 = this.getChildCount();
        for(int v12 = 0; v12 < v11; ++v12) {
            View view0 = this.getChildAt(v12);
            if(((LayoutParams)view0.getLayoutParams()).mViewType == 0 && this.shouldLayout(view0)) {
                v10 += this.measureChildCollapseMargins(view0, v, v10, v1, 0, arr_v);
                int v13 = Math.max(v4, view0.getMeasuredHeight() + this.getVerticalMargins(view0));
                v5 = View.combineMeasuredStates(v5, view0.getMeasuredState());
                v4 = v13;
            }
        }
        int v14 = this.mTitleMarginTop + this.mTitleMarginBottom;
        int v15 = this.mTitleMarginStart + this.mTitleMarginEnd;
        if(this.shouldLayout(this.mTitleTextView)) {
            this.measureChildCollapseMargins(this.mTitleTextView, v, v10 + v15, v1, v14, arr_v);
            int v16 = this.mTitleTextView.getMeasuredWidth();
            int v17 = this.getHorizontalMargins(this.mTitleTextView);
            int v18 = this.mTitleTextView.getMeasuredHeight();
            int v19 = this.getVerticalMargins(this.mTitleTextView);
            v20 = v16 + v17;
            v21 = View.combineMeasuredStates(v5, this.mTitleTextView.getMeasuredState());
            v22 = v18 + v19;
        }
        else {
            v21 = v5;
            v20 = 0;
            v22 = 0;
        }
        if(this.shouldLayout(this.mSubtitleTextView)) {
            v20 = Math.max(v20, this.measureChildCollapseMargins(this.mSubtitleTextView, v, v10 + v15, v1, v14 + v22, arr_v));
            v22 += this.mSubtitleTextView.getMeasuredHeight() + this.getVerticalMargins(this.mSubtitleTextView);
            v21 = View.combineMeasuredStates(v21, this.mSubtitleTextView.getMeasuredState());
        }
        int v23 = this.getPaddingLeft();
        int v24 = this.getPaddingRight();
        int v25 = this.getPaddingTop();
        int v26 = this.getPaddingBottom();
        int v27 = View.resolveSizeAndState(Math.max(v10 + v20 + (v23 + v24), this.getSuggestedMinimumWidth()), v, 0xFF000000 & v21);
        int v28 = View.resolveSizeAndState(Math.max(Math.max(v4, v22) + (v25 + v26), this.getSuggestedMinimumHeight()), v1, v21 << 16);
        if(!this.shouldCollapse()) {
            v2 = v28;
        }
        this.setMeasuredDimension(v27, v2);
    }

    @Override  // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable0) {
        if(!(parcelable0 instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable0);
            return;
        }
        super.onRestoreInstanceState(((SavedState)parcelable0).getSuperState());
        MenuBuilder menuBuilder0 = this.mMenuView == null ? null : this.mMenuView.peekMenu();
        if(((SavedState)parcelable0).expandedMenuItemId != 0 && this.mExpandedMenuPresenter != null && menuBuilder0 != null) {
            MenuItem menuItem0 = menuBuilder0.findItem(((SavedState)parcelable0).expandedMenuItemId);
            if(menuItem0 != null) {
                menuItem0.expandActionView();
            }
        }
        if(((SavedState)parcelable0).isOverflowOpen) {
            this.postShowOverflowMenu();
        }
    }

    @Override  // android.view.View
    public void onRtlPropertiesChanged(int v) {
        super.onRtlPropertiesChanged(v);
        this.ensureContentInsets();
        this.mContentInsets.setDirection(v == 1);
    }

    @Override  // android.view.View
    protected Parcelable onSaveInstanceState() {
        Parcelable parcelable0 = new SavedState(super.onSaveInstanceState());
        if(this.mExpandedMenuPresenter != null && this.mExpandedMenuPresenter.mCurrentExpandedItem != null) {
            parcelable0.expandedMenuItemId = this.mExpandedMenuPresenter.mCurrentExpandedItem.getItemId();
        }
        parcelable0.isOverflowOpen = this.isOverflowMenuShowing();
        return parcelable0;
    }

    @Override  // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent0) {
        int v = motionEvent0.getActionMasked();
        if(v == 0) {
            this.mEatingTouch = false;
        }
        if(!this.mEatingTouch && (v == 0 && !super.onTouchEvent(motionEvent0))) {
            this.mEatingTouch = true;
        }
        if(v == 1 || v == 3) {
            this.mEatingTouch = false;
        }
        return true;
    }

    private void postShowOverflowMenu() {
        this.removeCallbacks(this.mShowOverflowMenuRunnable);
        this.post(this.mShowOverflowMenuRunnable);
    }

    void removeChildrenForExpandedActionView() {
        for(int v = this.getChildCount() - 1; v >= 0; --v) {
            View view0 = this.getChildAt(v);
            if(((LayoutParams)view0.getLayoutParams()).mViewType != 2 && view0 != this.mMenuView) {
                this.removeViewAt(v);
                this.mHiddenViews.add(view0);
            }
        }
    }

    @Override  // androidx.core.view.MenuHost
    public void removeMenuProvider(MenuProvider menuProvider0) {
        this.mMenuHostHelper.removeMenuProvider(menuProvider0);
    }

    public void setBackInvokedCallbackEnabled(boolean z) {
        if(this.mBackInvokedCallbackEnabled != z) {
            this.mBackInvokedCallbackEnabled = z;
            this.updateBackInvokedCallbackState();
        }
    }

    public void setCollapseContentDescription(int v) {
        this.setCollapseContentDescription((v == 0 ? null : this.getContext().getText(v)));
    }

    public void setCollapseContentDescription(CharSequence charSequence0) {
        if(!TextUtils.isEmpty(charSequence0)) {
            this.ensureCollapseButtonView();
        }
        ImageButton imageButton0 = this.mCollapseButtonView;
        if(imageButton0 != null) {
            imageButton0.setContentDescription(charSequence0);
        }
    }

    public void setCollapseIcon(int v) {
        this.setCollapseIcon(AppCompatResources.getDrawable(this.getContext(), v));
    }

    public void setCollapseIcon(Drawable drawable0) {
        if(drawable0 != null) {
            this.ensureCollapseButtonView();
            this.mCollapseButtonView.setImageDrawable(drawable0);
            return;
        }
        ImageButton imageButton0 = this.mCollapseButtonView;
        if(imageButton0 != null) {
            imageButton0.setImageDrawable(this.mCollapseIcon);
        }
    }

    public void setCollapsible(boolean z) {
        this.mCollapsible = z;
        this.requestLayout();
    }

    public void setContentInsetEndWithActions(int v) {
        if(v < 0) {
            v = 0x80000000;
        }
        if(v != this.mContentInsetEndWithActions) {
            this.mContentInsetEndWithActions = v;
            if(this.getNavigationIcon() != null) {
                this.requestLayout();
            }
        }
    }

    public void setContentInsetStartWithNavigation(int v) {
        if(v < 0) {
            v = 0x80000000;
        }
        if(v != this.mContentInsetStartWithNavigation) {
            this.mContentInsetStartWithNavigation = v;
            if(this.getNavigationIcon() != null) {
                this.requestLayout();
            }
        }
    }

    public void setContentInsetsAbsolute(int v, int v1) {
        this.ensureContentInsets();
        this.mContentInsets.setAbsolute(v, v1);
    }

    public void setContentInsetsRelative(int v, int v1) {
        this.ensureContentInsets();
        this.mContentInsets.setRelative(v, v1);
    }

    public void setLogo(int v) {
        this.setLogo(AppCompatResources.getDrawable(this.getContext(), v));
    }

    public void setLogo(Drawable drawable0) {
        if(drawable0 != null) {
            this.ensureLogoView();
            if(!this.isChildOrHidden(this.mLogoView)) {
                this.addSystemView(this.mLogoView, true);
            }
        }
        else if(this.mLogoView != null && this.isChildOrHidden(this.mLogoView)) {
            this.removeView(this.mLogoView);
            this.mHiddenViews.remove(this.mLogoView);
        }
        ImageView imageView0 = this.mLogoView;
        if(imageView0 != null) {
            imageView0.setImageDrawable(drawable0);
        }
    }

    public void setLogoDescription(int v) {
        this.setLogoDescription(this.getContext().getText(v));
    }

    public void setLogoDescription(CharSequence charSequence0) {
        if(!TextUtils.isEmpty(charSequence0)) {
            this.ensureLogoView();
        }
        ImageView imageView0 = this.mLogoView;
        if(imageView0 != null) {
            imageView0.setContentDescription(charSequence0);
        }
    }

    public void setMenu(MenuBuilder menuBuilder0, ActionMenuPresenter actionMenuPresenter0) {
        if(menuBuilder0 != null || this.mMenuView != null) {
            this.ensureMenuView();
            MenuBuilder menuBuilder1 = this.mMenuView.peekMenu();
            if(menuBuilder1 != menuBuilder0) {
                if(menuBuilder1 != null) {
                    menuBuilder1.removeMenuPresenter(this.mOuterActionMenuPresenter);
                    menuBuilder1.removeMenuPresenter(this.mExpandedMenuPresenter);
                }
                if(this.mExpandedMenuPresenter == null) {
                    this.mExpandedMenuPresenter = new ExpandedActionViewMenuPresenter(this);
                }
                actionMenuPresenter0.setExpandedActionViewsExclusive(true);
                if(menuBuilder0 == null) {
                    actionMenuPresenter0.initForMenu(this.mPopupContext, null);
                    this.mExpandedMenuPresenter.initForMenu(this.mPopupContext, null);
                    actionMenuPresenter0.updateMenuView(true);
                    this.mExpandedMenuPresenter.updateMenuView(true);
                }
                else {
                    menuBuilder0.addMenuPresenter(actionMenuPresenter0, this.mPopupContext);
                    menuBuilder0.addMenuPresenter(this.mExpandedMenuPresenter, this.mPopupContext);
                }
                this.mMenuView.setPopupTheme(this.mPopupTheme);
                this.mMenuView.setPresenter(actionMenuPresenter0);
                this.mOuterActionMenuPresenter = actionMenuPresenter0;
                this.updateBackInvokedCallbackState();
            }
        }
    }

    public void setMenuCallbacks(Callback menuPresenter$Callback0, androidx.appcompat.view.menu.MenuBuilder.Callback menuBuilder$Callback0) {
        this.mActionMenuPresenterCallback = menuPresenter$Callback0;
        this.mMenuBuilderCallback = menuBuilder$Callback0;
        ActionMenuView actionMenuView0 = this.mMenuView;
        if(actionMenuView0 != null) {
            actionMenuView0.setMenuCallbacks(menuPresenter$Callback0, menuBuilder$Callback0);
        }
    }

    public void setNavigationContentDescription(int v) {
        this.setNavigationContentDescription((v == 0 ? null : this.getContext().getText(v)));
    }

    public void setNavigationContentDescription(CharSequence charSequence0) {
        if(!TextUtils.isEmpty(charSequence0)) {
            this.ensureNavButtonView();
        }
        ImageButton imageButton0 = this.mNavButtonView;
        if(imageButton0 != null) {
            imageButton0.setContentDescription(charSequence0);
            TooltipCompat.setTooltipText(this.mNavButtonView, charSequence0);
        }
    }

    public void setNavigationIcon(int v) {
        this.setNavigationIcon(AppCompatResources.getDrawable(this.getContext(), v));
    }

    public void setNavigationIcon(Drawable drawable0) {
        if(drawable0 != null) {
            this.ensureNavButtonView();
            if(!this.isChildOrHidden(this.mNavButtonView)) {
                this.addSystemView(this.mNavButtonView, true);
            }
        }
        else if(this.mNavButtonView != null && this.isChildOrHidden(this.mNavButtonView)) {
            this.removeView(this.mNavButtonView);
            this.mHiddenViews.remove(this.mNavButtonView);
        }
        ImageButton imageButton0 = this.mNavButtonView;
        if(imageButton0 != null) {
            imageButton0.setImageDrawable(drawable0);
        }
    }

    public void setNavigationOnClickListener(View.OnClickListener view$OnClickListener0) {
        this.ensureNavButtonView();
        this.mNavButtonView.setOnClickListener(view$OnClickListener0);
    }

    public void setOnMenuItemClickListener(OnMenuItemClickListener toolbar$OnMenuItemClickListener0) {
        this.mOnMenuItemClickListener = toolbar$OnMenuItemClickListener0;
    }

    public void setOverflowIcon(Drawable drawable0) {
        this.ensureMenu();
        this.mMenuView.setOverflowIcon(drawable0);
    }

    public void setPopupTheme(int v) {
        if(this.mPopupTheme != v) {
            this.mPopupTheme = v;
            if(v == 0) {
                this.mPopupContext = this.getContext();
                return;
            }
            this.mPopupContext = new ContextThemeWrapper(this.getContext(), v);
        }
    }

    public void setSubtitle(int v) {
        this.setSubtitle(this.getContext().getText(v));
    }

    public void setSubtitle(CharSequence charSequence0) {
        if(!TextUtils.isEmpty(charSequence0)) {
            if(this.mSubtitleTextView == null) {
                Context context0 = this.getContext();
                AppCompatTextView appCompatTextView0 = new AppCompatTextView(context0);
                this.mSubtitleTextView = appCompatTextView0;
                appCompatTextView0.setSingleLine();
                this.mSubtitleTextView.setEllipsize(TextUtils.TruncateAt.END);
                int v = this.mSubtitleTextAppearance;
                if(v != 0) {
                    this.mSubtitleTextView.setTextAppearance(context0, v);
                }
                ColorStateList colorStateList0 = this.mSubtitleTextColor;
                if(colorStateList0 != null) {
                    this.mSubtitleTextView.setTextColor(colorStateList0);
                }
            }
            if(!this.isChildOrHidden(this.mSubtitleTextView)) {
                this.addSystemView(this.mSubtitleTextView, true);
            }
        }
        else if(this.mSubtitleTextView != null && this.isChildOrHidden(this.mSubtitleTextView)) {
            this.removeView(this.mSubtitleTextView);
            this.mHiddenViews.remove(this.mSubtitleTextView);
        }
        TextView textView0 = this.mSubtitleTextView;
        if(textView0 != null) {
            textView0.setText(charSequence0);
        }
        this.mSubtitleText = charSequence0;
    }

    public void setSubtitleTextAppearance(Context context0, int v) {
        this.mSubtitleTextAppearance = v;
        TextView textView0 = this.mSubtitleTextView;
        if(textView0 != null) {
            textView0.setTextAppearance(context0, v);
        }
    }

    public void setSubtitleTextColor(int v) {
        this.setSubtitleTextColor(ColorStateList.valueOf(v));
    }

    public void setSubtitleTextColor(ColorStateList colorStateList0) {
        this.mSubtitleTextColor = colorStateList0;
        TextView textView0 = this.mSubtitleTextView;
        if(textView0 != null) {
            textView0.setTextColor(colorStateList0);
        }
    }

    public void setTitle(int v) {
        this.setTitle(this.getContext().getText(v));
    }

    public void setTitle(CharSequence charSequence0) {
        if(!TextUtils.isEmpty(charSequence0)) {
            if(this.mTitleTextView == null) {
                Context context0 = this.getContext();
                AppCompatTextView appCompatTextView0 = new AppCompatTextView(context0);
                this.mTitleTextView = appCompatTextView0;
                appCompatTextView0.setSingleLine();
                this.mTitleTextView.setEllipsize(TextUtils.TruncateAt.END);
                int v = this.mTitleTextAppearance;
                if(v != 0) {
                    this.mTitleTextView.setTextAppearance(context0, v);
                }
                ColorStateList colorStateList0 = this.mTitleTextColor;
                if(colorStateList0 != null) {
                    this.mTitleTextView.setTextColor(colorStateList0);
                }
            }
            if(!this.isChildOrHidden(this.mTitleTextView)) {
                this.addSystemView(this.mTitleTextView, true);
            }
        }
        else if(this.mTitleTextView != null && this.isChildOrHidden(this.mTitleTextView)) {
            this.removeView(this.mTitleTextView);
            this.mHiddenViews.remove(this.mTitleTextView);
        }
        TextView textView0 = this.mTitleTextView;
        if(textView0 != null) {
            textView0.setText(charSequence0);
        }
        this.mTitleText = charSequence0;
    }

    public void setTitleMargin(int v, int v1, int v2, int v3) {
        this.mTitleMarginStart = v;
        this.mTitleMarginTop = v1;
        this.mTitleMarginEnd = v2;
        this.mTitleMarginBottom = v3;
        this.requestLayout();
    }

    public void setTitleMarginBottom(int v) {
        this.mTitleMarginBottom = v;
        this.requestLayout();
    }

    public void setTitleMarginEnd(int v) {
        this.mTitleMarginEnd = v;
        this.requestLayout();
    }

    public void setTitleMarginStart(int v) {
        this.mTitleMarginStart = v;
        this.requestLayout();
    }

    public void setTitleMarginTop(int v) {
        this.mTitleMarginTop = v;
        this.requestLayout();
    }

    public void setTitleTextAppearance(Context context0, int v) {
        this.mTitleTextAppearance = v;
        TextView textView0 = this.mTitleTextView;
        if(textView0 != null) {
            textView0.setTextAppearance(context0, v);
        }
    }

    public void setTitleTextColor(int v) {
        this.setTitleTextColor(ColorStateList.valueOf(v));
    }

    public void setTitleTextColor(ColorStateList colorStateList0) {
        this.mTitleTextColor = colorStateList0;
        TextView textView0 = this.mTitleTextView;
        if(textView0 != null) {
            textView0.setTextColor(colorStateList0);
        }
    }

    private boolean shouldCollapse() {
        if(!this.mCollapsible) {
            return false;
        }
        int v = this.getChildCount();
        for(int v1 = 0; v1 < v; ++v1) {
            View view0 = this.getChildAt(v1);
            if(this.shouldLayout(view0) && view0.getMeasuredWidth() > 0 && view0.getMeasuredHeight() > 0) {
                return false;
            }
        }
        return true;
    }

    private boolean shouldLayout(View view0) {
        return view0 != null && view0.getParent() == this && view0.getVisibility() != 8;
    }

    // 检测为 Lambda 实现
    public boolean showOverflowMenu() [...]

    void updateBackInvokedCallbackState() {
        if(Build.VERSION.SDK_INT >= 33) {
            OnBackInvokedDispatcher onBackInvokedDispatcher0 = Api33Impl.findOnBackInvokedDispatcher(this);
            boolean z = this.hasExpandedActionView() && onBackInvokedDispatcher0 != null && this.isAttachedToWindow() && this.mBackInvokedCallbackEnabled;
            if(z && this.mBackInvokedDispatcher == null) {
                if(this.mBackInvokedCallback == null) {
                    this.mBackInvokedCallback = Api33Impl.newOnBackInvokedCallback(() -> {
                        MenuItemImpl menuItemImpl0 = this.mExpandedMenuPresenter == null ? null : this.mExpandedMenuPresenter.mCurrentExpandedItem;
                        if(menuItemImpl0 != null) {
                            menuItemImpl0.collapseActionView();
                        }
                    });
                }
                Api33Impl.tryRegisterOnBackInvokedCallback(onBackInvokedDispatcher0, this.mBackInvokedCallback);
                this.mBackInvokedDispatcher = onBackInvokedDispatcher0;
                return;
            }
            if(!z) {
                OnBackInvokedDispatcher onBackInvokedDispatcher1 = this.mBackInvokedDispatcher;
                if(onBackInvokedDispatcher1 != null) {
                    Api33Impl.tryUnregisterOnBackInvokedCallback(onBackInvokedDispatcher1, this.mBackInvokedCallback);
                    this.mBackInvokedDispatcher = null;
                }
            }
        }
    }

    class androidx.appcompat.widget.Toolbar.2 implements Runnable {
        @Override
        public void run() {
            Toolbar.this.showOverflowMenu();
        }
    }

}

