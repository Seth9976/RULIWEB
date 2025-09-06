package androidx.appcompat.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.SpinnerAdapter;
import androidx.appcompat.R.attr;
import androidx.appcompat.R.id;
import androidx.appcompat.R.styleable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.view.ActionBarPolicy;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.view.SupportMenuInflater;
import androidx.appcompat.view.ViewPropertyAnimatorCompatSet;
import androidx.appcompat.view.menu.MenuBuilder.Callback;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.appcompat.view.menu.SubMenuBuilder;
import androidx.appcompat.widget.ActionBarContainer;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.ActionBarOverlayLayout.ActionBarVisibilityCallback;
import androidx.appcompat.widget.ActionBarOverlayLayout;
import androidx.appcompat.widget.DecorToolbar;
import androidx.appcompat.widget.ScrollingTabContainerView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.core.view.ViewPropertyAnimatorListener;
import androidx.core.view.ViewPropertyAnimatorListenerAdapter;
import androidx.core.view.ViewPropertyAnimatorUpdateListener;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class WindowDecorActionBar extends ActionBar implements ActionBarVisibilityCallback {
    public class ActionModeImpl extends ActionMode implements Callback {
        private final Context mActionModeContext;
        private androidx.appcompat.view.ActionMode.Callback mCallback;
        private WeakReference mCustomView;
        private final MenuBuilder mMenu;

        public ActionModeImpl(Context context0, androidx.appcompat.view.ActionMode.Callback actionMode$Callback0) {
            this.mActionModeContext = context0;
            this.mCallback = actionMode$Callback0;
            MenuBuilder menuBuilder0 = new MenuBuilder(context0).setDefaultShowAsAction(1);
            this.mMenu = menuBuilder0;
            menuBuilder0.setCallback(this);
        }

        public boolean dispatchOnCreate() {
            this.mMenu.stopDispatchingItemsChanged();
            try {
                return this.mCallback.onCreateActionMode(this, this.mMenu);
            }
            finally {
                this.mMenu.startDispatchingItemsChanged();
            }
        }

        @Override  // androidx.appcompat.view.ActionMode
        public void finish() {
            if(WindowDecorActionBar.this.mActionMode != this) {
                return;
            }
            if(WindowDecorActionBar.checkShowingFlags(WindowDecorActionBar.this.mHiddenByApp, WindowDecorActionBar.this.mHiddenBySystem, false)) {
                this.mCallback.onDestroyActionMode(this);
            }
            else {
                WindowDecorActionBar.this.mDeferredDestroyActionMode = this;
                WindowDecorActionBar.this.mDeferredModeDestroyCallback = this.mCallback;
            }
            this.mCallback = null;
            WindowDecorActionBar.this.animateToMode(false);
            WindowDecorActionBar.this.mContextView.closeMode();
            WindowDecorActionBar.this.mOverlayLayout.setHideOnContentScrollEnabled(WindowDecorActionBar.this.mHideOnContentScroll);
            WindowDecorActionBar.this.mActionMode = null;
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
            return new SupportMenuInflater(this.mActionModeContext);
        }

        @Override  // androidx.appcompat.view.ActionMode
        public CharSequence getSubtitle() {
            return WindowDecorActionBar.this.mContextView.getSubtitle();
        }

        @Override  // androidx.appcompat.view.ActionMode
        public CharSequence getTitle() {
            return WindowDecorActionBar.this.mContextView.getTitle();
        }

        @Override  // androidx.appcompat.view.ActionMode
        public void invalidate() {
            if(WindowDecorActionBar.this.mActionMode != this) {
                return;
            }
            this.mMenu.stopDispatchingItemsChanged();
            try {
                this.mCallback.onPrepareActionMode(this, this.mMenu);
            }
            finally {
                this.mMenu.startDispatchingItemsChanged();
            }
        }

        @Override  // androidx.appcompat.view.ActionMode
        public boolean isTitleOptional() {
            return WindowDecorActionBar.this.mContextView.isTitleOptional();
        }

        public void onCloseMenu(MenuBuilder menuBuilder0, boolean z) {
        }

        public void onCloseSubMenu(SubMenuBuilder subMenuBuilder0) {
        }

        @Override  // androidx.appcompat.view.menu.MenuBuilder$Callback
        public boolean onMenuItemSelected(MenuBuilder menuBuilder0, MenuItem menuItem0) {
            return this.mCallback == null ? false : this.mCallback.onActionItemClicked(this, menuItem0);
        }

        @Override  // androidx.appcompat.view.menu.MenuBuilder$Callback
        public void onMenuModeChange(MenuBuilder menuBuilder0) {
            if(this.mCallback == null) {
                return;
            }
            this.invalidate();
            WindowDecorActionBar.this.mContextView.showOverflowMenu();
        }

        public boolean onSubMenuSelected(SubMenuBuilder subMenuBuilder0) {
            if(this.mCallback == null) {
                return false;
            }
            if(!subMenuBuilder0.hasVisibleItems()) {
                return true;
            }
            new MenuPopupHelper(WindowDecorActionBar.this.getThemedContext(), subMenuBuilder0).show();
            return true;
        }

        @Override  // androidx.appcompat.view.ActionMode
        public void setCustomView(View view0) {
            WindowDecorActionBar.this.mContextView.setCustomView(view0);
            this.mCustomView = new WeakReference(view0);
        }

        @Override  // androidx.appcompat.view.ActionMode
        public void setSubtitle(int v) {
            this.setSubtitle(WindowDecorActionBar.this.mContext.getResources().getString(v));
        }

        @Override  // androidx.appcompat.view.ActionMode
        public void setSubtitle(CharSequence charSequence0) {
            WindowDecorActionBar.this.mContextView.setSubtitle(charSequence0);
        }

        @Override  // androidx.appcompat.view.ActionMode
        public void setTitle(int v) {
            this.setTitle(WindowDecorActionBar.this.mContext.getResources().getString(v));
        }

        @Override  // androidx.appcompat.view.ActionMode
        public void setTitle(CharSequence charSequence0) {
            WindowDecorActionBar.this.mContextView.setTitle(charSequence0);
        }

        @Override  // androidx.appcompat.view.ActionMode
        public void setTitleOptionalHint(boolean z) {
            super.setTitleOptionalHint(z);
            WindowDecorActionBar.this.mContextView.setTitleOptional(z);
        }
    }

    public class TabImpl extends Tab {
        private TabListener mCallback;
        private CharSequence mContentDesc;
        private View mCustomView;
        private Drawable mIcon;
        private int mPosition;
        private Object mTag;
        private CharSequence mText;

        public TabImpl() {
            this.mPosition = -1;
        }

        public TabListener getCallback() {
            return this.mCallback;
        }

        @Override  // androidx.appcompat.app.ActionBar$Tab
        public CharSequence getContentDescription() {
            return this.mContentDesc;
        }

        @Override  // androidx.appcompat.app.ActionBar$Tab
        public View getCustomView() {
            return this.mCustomView;
        }

        @Override  // androidx.appcompat.app.ActionBar$Tab
        public Drawable getIcon() {
            return this.mIcon;
        }

        @Override  // androidx.appcompat.app.ActionBar$Tab
        public int getPosition() {
            return this.mPosition;
        }

        @Override  // androidx.appcompat.app.ActionBar$Tab
        public Object getTag() {
            return this.mTag;
        }

        @Override  // androidx.appcompat.app.ActionBar$Tab
        public CharSequence getText() {
            return this.mText;
        }

        @Override  // androidx.appcompat.app.ActionBar$Tab
        public void select() {
            WindowDecorActionBar.this.selectTab(this);
        }

        @Override  // androidx.appcompat.app.ActionBar$Tab
        public Tab setContentDescription(int v) {
            return this.setContentDescription(WindowDecorActionBar.this.mContext.getResources().getText(v));
        }

        @Override  // androidx.appcompat.app.ActionBar$Tab
        public Tab setContentDescription(CharSequence charSequence0) {
            this.mContentDesc = charSequence0;
            if(this.mPosition >= 0) {
                WindowDecorActionBar.this.mTabScrollView.updateTab(this.mPosition);
            }
            return this;
        }

        @Override  // androidx.appcompat.app.ActionBar$Tab
        public Tab setCustomView(int v) {
            return this.setCustomView(LayoutInflater.from(WindowDecorActionBar.this.getThemedContext()).inflate(v, null));
        }

        @Override  // androidx.appcompat.app.ActionBar$Tab
        public Tab setCustomView(View view0) {
            this.mCustomView = view0;
            if(this.mPosition >= 0) {
                WindowDecorActionBar.this.mTabScrollView.updateTab(this.mPosition);
            }
            return this;
        }

        @Override  // androidx.appcompat.app.ActionBar$Tab
        public Tab setIcon(int v) {
            return this.setIcon(AppCompatResources.getDrawable(WindowDecorActionBar.this.mContext, v));
        }

        @Override  // androidx.appcompat.app.ActionBar$Tab
        public Tab setIcon(Drawable drawable0) {
            this.mIcon = drawable0;
            if(this.mPosition >= 0) {
                WindowDecorActionBar.this.mTabScrollView.updateTab(this.mPosition);
            }
            return this;
        }

        public void setPosition(int v) {
            this.mPosition = v;
        }

        @Override  // androidx.appcompat.app.ActionBar$Tab
        public Tab setTabListener(TabListener actionBar$TabListener0) {
            this.mCallback = actionBar$TabListener0;
            return this;
        }

        @Override  // androidx.appcompat.app.ActionBar$Tab
        public Tab setTag(Object object0) {
            this.mTag = object0;
            return this;
        }

        @Override  // androidx.appcompat.app.ActionBar$Tab
        public Tab setText(int v) {
            return this.setText(WindowDecorActionBar.this.mContext.getResources().getText(v));
        }

        @Override  // androidx.appcompat.app.ActionBar$Tab
        public Tab setText(CharSequence charSequence0) {
            this.mText = charSequence0;
            if(this.mPosition >= 0) {
                WindowDecorActionBar.this.mTabScrollView.updateTab(this.mPosition);
            }
            return this;
        }
    }

    private static final long FADE_IN_DURATION_MS = 200L;
    private static final long FADE_OUT_DURATION_MS = 100L;
    private static final int INVALID_POSITION = -1;
    private static final String TAG = "WindowDecorActionBar";
    ActionModeImpl mActionMode;
    private Activity mActivity;
    ActionBarContainer mContainerView;
    boolean mContentAnimations;
    View mContentView;
    Context mContext;
    ActionBarContextView mContextView;
    private int mCurWindowVisibility;
    ViewPropertyAnimatorCompatSet mCurrentShowAnim;
    DecorToolbar mDecorToolbar;
    ActionMode mDeferredDestroyActionMode;
    androidx.appcompat.view.ActionMode.Callback mDeferredModeDestroyCallback;
    private boolean mDisplayHomeAsUpSet;
    private boolean mHasEmbeddedTabs;
    boolean mHiddenByApp;
    boolean mHiddenBySystem;
    final ViewPropertyAnimatorListener mHideListener;
    boolean mHideOnContentScroll;
    private boolean mLastMenuVisibility;
    private ArrayList mMenuVisibilityListeners;
    private boolean mNowShowing;
    ActionBarOverlayLayout mOverlayLayout;
    private int mSavedTabPosition;
    private TabImpl mSelectedTab;
    private boolean mShowHideAnimationEnabled;
    final ViewPropertyAnimatorListener mShowListener;
    private boolean mShowingForMode;
    ScrollingTabContainerView mTabScrollView;
    private ArrayList mTabs;
    private Context mThemedContext;
    final ViewPropertyAnimatorUpdateListener mUpdateListener;
    private static final Interpolator sHideInterpolator;
    private static final Interpolator sShowInterpolator;

    static {
        WindowDecorActionBar.sHideInterpolator = new AccelerateInterpolator();
        WindowDecorActionBar.sShowInterpolator = new DecelerateInterpolator();
    }

    public WindowDecorActionBar(Activity activity0, boolean z) {
        this.mTabs = new ArrayList();
        this.mSavedTabPosition = -1;
        this.mMenuVisibilityListeners = new ArrayList();
        this.mCurWindowVisibility = 0;
        this.mContentAnimations = true;
        this.mNowShowing = true;
        this.mHideListener = new ViewPropertyAnimatorListenerAdapter() {
            @Override  // androidx.core.view.ViewPropertyAnimatorListenerAdapter
            public void onAnimationEnd(View view0) {
                if(WindowDecorActionBar.this.mContentAnimations && WindowDecorActionBar.this.mContentView != null) {
                    WindowDecorActionBar.this.mContentView.setTranslationY(0.0f);
                    WindowDecorActionBar.this.mContainerView.setTranslationY(0.0f);
                }
                WindowDecorActionBar.this.mContainerView.setVisibility(8);
                WindowDecorActionBar.this.mContainerView.setTransitioning(false);
                WindowDecorActionBar.this.mCurrentShowAnim = null;
                WindowDecorActionBar.this.completeDeferredDestroyActionMode();
                if(WindowDecorActionBar.this.mOverlayLayout != null) {
                    ViewCompat.requestApplyInsets(WindowDecorActionBar.this.mOverlayLayout);
                }
            }
        };
        this.mShowListener = new ViewPropertyAnimatorListenerAdapter() {
            @Override  // androidx.core.view.ViewPropertyAnimatorListenerAdapter
            public void onAnimationEnd(View view0) {
                WindowDecorActionBar.this.mCurrentShowAnim = null;
                WindowDecorActionBar.this.mContainerView.requestLayout();
            }
        };
        this.mUpdateListener = new ViewPropertyAnimatorUpdateListener() {
            @Override  // androidx.core.view.ViewPropertyAnimatorUpdateListener
            public void onAnimationUpdate(View view0) {
                ((View)WindowDecorActionBar.this.mContainerView.getParent()).invalidate();
            }
        };
        this.mActivity = activity0;
        View view0 = activity0.getWindow().getDecorView();
        this.init(view0);
        if(!z) {
            this.mContentView = view0.findViewById(0x1020002);
        }
    }

    public WindowDecorActionBar(Dialog dialog0) {
        this.mTabs = new ArrayList();
        this.mSavedTabPosition = -1;
        this.mMenuVisibilityListeners = new ArrayList();
        this.mCurWindowVisibility = 0;
        this.mContentAnimations = true;
        this.mNowShowing = true;
        this.mHideListener = new ViewPropertyAnimatorListenerAdapter() {
            @Override  // androidx.core.view.ViewPropertyAnimatorListenerAdapter
            public void onAnimationEnd(View view0) {
                if(WindowDecorActionBar.this.mContentAnimations && WindowDecorActionBar.this.mContentView != null) {
                    WindowDecorActionBar.this.mContentView.setTranslationY(0.0f);
                    WindowDecorActionBar.this.mContainerView.setTranslationY(0.0f);
                }
                WindowDecorActionBar.this.mContainerView.setVisibility(8);
                WindowDecorActionBar.this.mContainerView.setTransitioning(false);
                WindowDecorActionBar.this.mCurrentShowAnim = null;
                WindowDecorActionBar.this.completeDeferredDestroyActionMode();
                if(WindowDecorActionBar.this.mOverlayLayout != null) {
                    ViewCompat.requestApplyInsets(WindowDecorActionBar.this.mOverlayLayout);
                }
            }
        };
        this.mShowListener = new ViewPropertyAnimatorListenerAdapter() {
            @Override  // androidx.core.view.ViewPropertyAnimatorListenerAdapter
            public void onAnimationEnd(View view0) {
                WindowDecorActionBar.this.mCurrentShowAnim = null;
                WindowDecorActionBar.this.mContainerView.requestLayout();
            }
        };
        this.mUpdateListener = new ViewPropertyAnimatorUpdateListener() {
            @Override  // androidx.core.view.ViewPropertyAnimatorUpdateListener
            public void onAnimationUpdate(View view0) {
                ((View)WindowDecorActionBar.this.mContainerView.getParent()).invalidate();
            }
        };
        this.init(dialog0.getWindow().getDecorView());
    }

    public WindowDecorActionBar(View view0) {
        this.mTabs = new ArrayList();
        this.mSavedTabPosition = -1;
        this.mMenuVisibilityListeners = new ArrayList();
        this.mCurWindowVisibility = 0;
        this.mContentAnimations = true;
        this.mNowShowing = true;
        this.mHideListener = new ViewPropertyAnimatorListenerAdapter() {
            @Override  // androidx.core.view.ViewPropertyAnimatorListenerAdapter
            public void onAnimationEnd(View view0) {
                if(WindowDecorActionBar.this.mContentAnimations && WindowDecorActionBar.this.mContentView != null) {
                    WindowDecorActionBar.this.mContentView.setTranslationY(0.0f);
                    WindowDecorActionBar.this.mContainerView.setTranslationY(0.0f);
                }
                WindowDecorActionBar.this.mContainerView.setVisibility(8);
                WindowDecorActionBar.this.mContainerView.setTransitioning(false);
                WindowDecorActionBar.this.mCurrentShowAnim = null;
                WindowDecorActionBar.this.completeDeferredDestroyActionMode();
                if(WindowDecorActionBar.this.mOverlayLayout != null) {
                    ViewCompat.requestApplyInsets(WindowDecorActionBar.this.mOverlayLayout);
                }
            }
        };
        this.mShowListener = new ViewPropertyAnimatorListenerAdapter() {
            @Override  // androidx.core.view.ViewPropertyAnimatorListenerAdapter
            public void onAnimationEnd(View view0) {
                WindowDecorActionBar.this.mCurrentShowAnim = null;
                WindowDecorActionBar.this.mContainerView.requestLayout();
            }
        };
        this.mUpdateListener = new ViewPropertyAnimatorUpdateListener() {
            @Override  // androidx.core.view.ViewPropertyAnimatorUpdateListener
            public void onAnimationUpdate(View view0) {
                ((View)WindowDecorActionBar.this.mContainerView.getParent()).invalidate();
            }
        };
        this.init(view0);
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void addOnMenuVisibilityListener(OnMenuVisibilityListener actionBar$OnMenuVisibilityListener0) {
        this.mMenuVisibilityListeners.add(actionBar$OnMenuVisibilityListener0);
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void addTab(Tab actionBar$Tab0) {
        this.addTab(actionBar$Tab0, this.mTabs.isEmpty());
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void addTab(Tab actionBar$Tab0, int v) {
        this.addTab(actionBar$Tab0, v, this.mTabs.isEmpty());
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void addTab(Tab actionBar$Tab0, int v, boolean z) {
        this.ensureTabsExist();
        this.mTabScrollView.addTab(actionBar$Tab0, v, z);
        this.configureTab(actionBar$Tab0, v);
        if(z) {
            this.selectTab(actionBar$Tab0);
        }
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void addTab(Tab actionBar$Tab0, boolean z) {
        this.ensureTabsExist();
        this.mTabScrollView.addTab(actionBar$Tab0, z);
        this.configureTab(actionBar$Tab0, this.mTabs.size());
        if(z) {
            this.selectTab(actionBar$Tab0);
        }
    }

    public void animateToMode(boolean z) {
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat1;
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat0;
        if(z) {
            this.showForActionMode();
        }
        else {
            this.hideForActionMode();
        }
        if(this.shouldAnimateContextView()) {
            if(z) {
                viewPropertyAnimatorCompat0 = this.mDecorToolbar.setupAnimatorToVisibility(4, 100L);
                viewPropertyAnimatorCompat1 = this.mContextView.setupAnimatorToVisibility(0, 200L);
            }
            else {
                viewPropertyAnimatorCompat1 = this.mDecorToolbar.setupAnimatorToVisibility(0, 200L);
                viewPropertyAnimatorCompat0 = this.mContextView.setupAnimatorToVisibility(8, 100L);
            }
            ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet0 = new ViewPropertyAnimatorCompatSet();
            viewPropertyAnimatorCompatSet0.playSequentially(viewPropertyAnimatorCompat0, viewPropertyAnimatorCompat1);
            viewPropertyAnimatorCompatSet0.start();
            return;
        }
        if(z) {
            this.mDecorToolbar.setVisibility(4);
            this.mContextView.setVisibility(0);
            return;
        }
        this.mDecorToolbar.setVisibility(0);
        this.mContextView.setVisibility(8);
    }

    // 去混淆评级： 低(40)
    static boolean checkShowingFlags(boolean z, boolean z1, boolean z2) {
        return z2 ? true : !z && !z1;
    }

    private void cleanupTabs() {
        if(this.mSelectedTab != null) {
            this.selectTab(null);
        }
        this.mTabs.clear();
        ScrollingTabContainerView scrollingTabContainerView0 = this.mTabScrollView;
        if(scrollingTabContainerView0 != null) {
            scrollingTabContainerView0.removeAllTabs();
        }
        this.mSavedTabPosition = -1;
    }

    @Override  // androidx.appcompat.app.ActionBar
    public boolean collapseActionView() {
        if(this.mDecorToolbar != null && this.mDecorToolbar.hasExpandedActionView()) {
            this.mDecorToolbar.collapseActionView();
            return true;
        }
        return false;
    }

    void completeDeferredDestroyActionMode() {
        androidx.appcompat.view.ActionMode.Callback actionMode$Callback0 = this.mDeferredModeDestroyCallback;
        if(actionMode$Callback0 != null) {
            actionMode$Callback0.onDestroyActionMode(this.mDeferredDestroyActionMode);
            this.mDeferredDestroyActionMode = null;
            this.mDeferredModeDestroyCallback = null;
        }
    }

    private void configureTab(Tab actionBar$Tab0, int v) {
        if(((TabImpl)actionBar$Tab0).getCallback() == null) {
            throw new IllegalStateException("Action Bar Tab must have a Callback");
        }
        ((TabImpl)actionBar$Tab0).setPosition(v);
        this.mTabs.add(v, ((TabImpl)actionBar$Tab0));
        int v1 = this.mTabs.size();
        while(true) {
            ++v;
            if(v >= v1) {
                break;
            }
            ((TabImpl)this.mTabs.get(v)).setPosition(v);
        }
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

    public void doHide(boolean z) {
        ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet0 = this.mCurrentShowAnim;
        if(viewPropertyAnimatorCompatSet0 != null) {
            viewPropertyAnimatorCompatSet0.cancel();
        }
        if(this.mCurWindowVisibility == 0 && (this.mShowHideAnimationEnabled || z)) {
            this.mContainerView.setAlpha(1.0f);
            this.mContainerView.setTransitioning(true);
            ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet1 = new ViewPropertyAnimatorCompatSet();
            float f = (float)(-this.mContainerView.getHeight());
            if(z) {
                int[] arr_v = {0, 0};
                this.mContainerView.getLocationInWindow(arr_v);
                f -= (float)arr_v[1];
            }
            ViewPropertyAnimatorCompat viewPropertyAnimatorCompat0 = ViewCompat.animate(this.mContainerView).translationY(f);
            viewPropertyAnimatorCompat0.setUpdateListener(this.mUpdateListener);
            viewPropertyAnimatorCompatSet1.play(viewPropertyAnimatorCompat0);
            if(this.mContentAnimations) {
                View view0 = this.mContentView;
                if(view0 != null) {
                    viewPropertyAnimatorCompatSet1.play(ViewCompat.animate(view0).translationY(f));
                }
            }
            viewPropertyAnimatorCompatSet1.setInterpolator(WindowDecorActionBar.sHideInterpolator);
            viewPropertyAnimatorCompatSet1.setDuration(0xFAL);
            viewPropertyAnimatorCompatSet1.setListener(this.mHideListener);
            this.mCurrentShowAnim = viewPropertyAnimatorCompatSet1;
            viewPropertyAnimatorCompatSet1.start();
            return;
        }
        this.mHideListener.onAnimationEnd(null);
    }

    public void doShow(boolean z) {
        ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet0 = this.mCurrentShowAnim;
        if(viewPropertyAnimatorCompatSet0 != null) {
            viewPropertyAnimatorCompatSet0.cancel();
        }
        this.mContainerView.setVisibility(0);
        if(this.mCurWindowVisibility != 0 || !this.mShowHideAnimationEnabled && !z) {
            this.mContainerView.setAlpha(1.0f);
            this.mContainerView.setTranslationY(0.0f);
            if(this.mContentAnimations) {
                View view1 = this.mContentView;
                if(view1 != null) {
                    view1.setTranslationY(0.0f);
                }
            }
            this.mShowListener.onAnimationEnd(null);
        }
        else {
            this.mContainerView.setTranslationY(0.0f);
            float f = (float)(-this.mContainerView.getHeight());
            if(z) {
                int[] arr_v = {0, 0};
                this.mContainerView.getLocationInWindow(arr_v);
                f -= (float)arr_v[1];
            }
            this.mContainerView.setTranslationY(f);
            ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet1 = new ViewPropertyAnimatorCompatSet();
            ViewPropertyAnimatorCompat viewPropertyAnimatorCompat0 = ViewCompat.animate(this.mContainerView).translationY(0.0f);
            viewPropertyAnimatorCompat0.setUpdateListener(this.mUpdateListener);
            viewPropertyAnimatorCompatSet1.play(viewPropertyAnimatorCompat0);
            if(this.mContentAnimations) {
                View view0 = this.mContentView;
                if(view0 != null) {
                    view0.setTranslationY(f);
                    viewPropertyAnimatorCompatSet1.play(ViewCompat.animate(this.mContentView).translationY(0.0f));
                }
            }
            viewPropertyAnimatorCompatSet1.setInterpolator(WindowDecorActionBar.sShowInterpolator);
            viewPropertyAnimatorCompatSet1.setDuration(0xFAL);
            viewPropertyAnimatorCompatSet1.setListener(this.mShowListener);
            this.mCurrentShowAnim = viewPropertyAnimatorCompatSet1;
            viewPropertyAnimatorCompatSet1.start();
        }
        ActionBarOverlayLayout actionBarOverlayLayout0 = this.mOverlayLayout;
        if(actionBarOverlayLayout0 != null) {
            ViewCompat.requestApplyInsets(actionBarOverlayLayout0);
        }
    }

    @Override  // androidx.appcompat.widget.ActionBarOverlayLayout$ActionBarVisibilityCallback
    public void enableContentAnimations(boolean z) {
        this.mContentAnimations = z;
    }

    private void ensureTabsExist() {
        if(this.mTabScrollView != null) {
            return;
        }
        ScrollingTabContainerView scrollingTabContainerView0 = new ScrollingTabContainerView(this.mContext);
        if(this.mHasEmbeddedTabs) {
            scrollingTabContainerView0.setVisibility(0);
            this.mDecorToolbar.setEmbeddedTabView(scrollingTabContainerView0);
        }
        else {
            if(this.getNavigationMode() == 2) {
                scrollingTabContainerView0.setVisibility(0);
                ActionBarOverlayLayout actionBarOverlayLayout0 = this.mOverlayLayout;
                if(actionBarOverlayLayout0 != null) {
                    ViewCompat.requestApplyInsets(actionBarOverlayLayout0);
                }
            }
            else {
                scrollingTabContainerView0.setVisibility(8);
            }
            this.mContainerView.setTabContainer(scrollingTabContainerView0);
        }
        this.mTabScrollView = scrollingTabContainerView0;
    }

    @Override  // androidx.appcompat.app.ActionBar
    public View getCustomView() {
        return this.mDecorToolbar.getCustomView();
    }

    private DecorToolbar getDecorToolbar(View view0) {
        if(view0 instanceof DecorToolbar) {
            return (DecorToolbar)view0;
        }
        if(!(view0 instanceof Toolbar)) {
            throw new IllegalStateException("Can\'t make a decor toolbar out of " + (view0 == null ? "null" : view0.getClass().getSimpleName()));
        }
        return ((Toolbar)view0).getWrapper();
    }

    @Override  // androidx.appcompat.app.ActionBar
    public int getDisplayOptions() {
        return this.mDecorToolbar.getDisplayOptions();
    }

    @Override  // androidx.appcompat.app.ActionBar
    public float getElevation() {
        return ViewCompat.getElevation(this.mContainerView);
    }

    @Override  // androidx.appcompat.app.ActionBar
    public int getHeight() {
        return this.mContainerView.getHeight();
    }

    @Override  // androidx.appcompat.app.ActionBar
    public int getHideOffset() {
        return this.mOverlayLayout.getActionBarHideOffset();
    }

    @Override  // androidx.appcompat.app.ActionBar
    public int getNavigationItemCount() {
        switch(this.mDecorToolbar.getNavigationMode()) {
            case 1: {
                return this.mDecorToolbar.getDropdownItemCount();
            }
            case 2: {
                return this.mTabs.size();
            }
            default: {
                return 0;
            }
        }
    }

    @Override  // androidx.appcompat.app.ActionBar
    public int getNavigationMode() {
        return this.mDecorToolbar.getNavigationMode();
    }

    @Override  // androidx.appcompat.app.ActionBar
    public int getSelectedNavigationIndex() {
        switch(this.mDecorToolbar.getNavigationMode()) {
            case 1: {
                return this.mDecorToolbar.getDropdownSelectedPosition();
            }
            case 2: {
                return this.mSelectedTab == null ? -1 : this.mSelectedTab.getPosition();
            }
            default: {
                return -1;
            }
        }
    }

    @Override  // androidx.appcompat.app.ActionBar
    public Tab getSelectedTab() {
        return this.mSelectedTab;
    }

    @Override  // androidx.appcompat.app.ActionBar
    public CharSequence getSubtitle() {
        return this.mDecorToolbar.getSubtitle();
    }

    @Override  // androidx.appcompat.app.ActionBar
    public Tab getTabAt(int v) {
        return (Tab)this.mTabs.get(v);
    }

    @Override  // androidx.appcompat.app.ActionBar
    public int getTabCount() {
        return this.mTabs.size();
    }

    @Override  // androidx.appcompat.app.ActionBar
    public Context getThemedContext() {
        if(this.mThemedContext == null) {
            TypedValue typedValue0 = new TypedValue();
            this.mContext.getTheme().resolveAttribute(attr.actionBarWidgetTheme, typedValue0, true);
            int v = typedValue0.resourceId;
            if(v != 0) {
                this.mThemedContext = new ContextThemeWrapper(this.mContext, v);
                return this.mThemedContext;
            }
            this.mThemedContext = this.mContext;
        }
        return this.mThemedContext;
    }

    @Override  // androidx.appcompat.app.ActionBar
    public CharSequence getTitle() {
        return this.mDecorToolbar.getTitle();
    }

    public boolean hasIcon() {
        return this.mDecorToolbar.hasIcon();
    }

    public boolean hasLogo() {
        return this.mDecorToolbar.hasLogo();
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void hide() {
        if(!this.mHiddenByApp) {
            this.mHiddenByApp = true;
            this.updateVisibility(false);
        }
    }

    private void hideForActionMode() {
        if(this.mShowingForMode) {
            this.mShowingForMode = false;
            this.updateVisibility(false);
        }
    }

    @Override  // androidx.appcompat.widget.ActionBarOverlayLayout$ActionBarVisibilityCallback
    public void hideForSystem() {
        if(!this.mHiddenBySystem) {
            this.mHiddenBySystem = true;
            this.updateVisibility(true);
        }
    }

    private void init(View view0) {
        ActionBarOverlayLayout actionBarOverlayLayout0 = (ActionBarOverlayLayout)view0.findViewById(id.decor_content_parent);
        this.mOverlayLayout = actionBarOverlayLayout0;
        if(actionBarOverlayLayout0 != null) {
            actionBarOverlayLayout0.setActionBarVisibilityCallback(this);
        }
        this.mDecorToolbar = this.getDecorToolbar(view0.findViewById(id.action_bar));
        this.mContextView = (ActionBarContextView)view0.findViewById(id.action_context_bar);
        ActionBarContainer actionBarContainer0 = (ActionBarContainer)view0.findViewById(id.action_bar_container);
        this.mContainerView = actionBarContainer0;
        DecorToolbar decorToolbar0 = this.mDecorToolbar;
        if(decorToolbar0 == null || this.mContextView == null || actionBarContainer0 == null) {
            throw new IllegalStateException(this.getClass().getSimpleName() + " can only be used with a compatible window decor layout");
        }
        this.mContext = decorToolbar0.getContext();
        boolean z = (this.mDecorToolbar.getDisplayOptions() & 4) != 0;
        if(z) {
            this.mDisplayHomeAsUpSet = true;
        }
        ActionBarPolicy actionBarPolicy0 = ActionBarPolicy.get(this.mContext);
        this.setHomeButtonEnabled(actionBarPolicy0.enableHomeButtonByDefault() || z);
        this.setHasEmbeddedTabs(actionBarPolicy0.hasEmbeddedTabs());
        TypedArray typedArray0 = this.mContext.obtainStyledAttributes(null, styleable.ActionBar, attr.actionBarStyle, 0);
        if(typedArray0.getBoolean(styleable.ActionBar_hideOnContentScroll, false)) {
            this.setHideOnContentScrollEnabled(true);
        }
        int v = typedArray0.getDimensionPixelSize(styleable.ActionBar_elevation, 0);
        if(v != 0) {
            this.setElevation(((float)v));
        }
        typedArray0.recycle();
    }

    @Override  // androidx.appcompat.app.ActionBar
    public boolean isHideOnContentScrollEnabled() {
        return this.mOverlayLayout.isHideOnContentScrollEnabled();
    }

    @Override  // androidx.appcompat.app.ActionBar
    public boolean isShowing() {
        int v = this.getHeight();
        return this.mNowShowing && (v == 0 || this.getHideOffset() < v);
    }

    @Override  // androidx.appcompat.app.ActionBar
    public boolean isTitleTruncated() {
        return this.mDecorToolbar != null && this.mDecorToolbar.isTitleTruncated();
    }

    @Override  // androidx.appcompat.app.ActionBar
    public Tab newTab() {
        return new TabImpl(this);
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void onConfigurationChanged(Configuration configuration0) {
        this.setHasEmbeddedTabs(ActionBarPolicy.get(this.mContext).hasEmbeddedTabs());
    }

    @Override  // androidx.appcompat.widget.ActionBarOverlayLayout$ActionBarVisibilityCallback
    public void onContentScrollStarted() {
        ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet0 = this.mCurrentShowAnim;
        if(viewPropertyAnimatorCompatSet0 != null) {
            viewPropertyAnimatorCompatSet0.cancel();
            this.mCurrentShowAnim = null;
        }
    }

    @Override  // androidx.appcompat.widget.ActionBarOverlayLayout$ActionBarVisibilityCallback
    public void onContentScrollStopped() {
    }

    @Override  // androidx.appcompat.app.ActionBar
    public boolean onKeyShortcut(int v, KeyEvent keyEvent0) {
        ActionModeImpl windowDecorActionBar$ActionModeImpl0 = this.mActionMode;
        if(windowDecorActionBar$ActionModeImpl0 == null) {
            return false;
        }
        Menu menu0 = windowDecorActionBar$ActionModeImpl0.getMenu();
        if(menu0 != null) {
            menu0.setQwertyMode(KeyCharacterMap.load((keyEvent0 == null ? -1 : keyEvent0.getDeviceId())).getKeyboardType() != 1);
            return menu0.performShortcut(v, keyEvent0, 0);
        }
        return false;
    }

    @Override  // androidx.appcompat.widget.ActionBarOverlayLayout$ActionBarVisibilityCallback
    public void onWindowVisibilityChanged(int v) {
        this.mCurWindowVisibility = v;
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void removeAllTabs() {
        this.cleanupTabs();
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void removeOnMenuVisibilityListener(OnMenuVisibilityListener actionBar$OnMenuVisibilityListener0) {
        this.mMenuVisibilityListeners.remove(actionBar$OnMenuVisibilityListener0);
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void removeTab(Tab actionBar$Tab0) {
        this.removeTabAt(actionBar$Tab0.getPosition());
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void removeTabAt(int v) {
        if(this.mTabScrollView != null) {
            int v1 = this.mSelectedTab == null ? this.mSavedTabPosition : this.mSelectedTab.getPosition();
            this.mTabScrollView.removeTabAt(v);
            TabImpl windowDecorActionBar$TabImpl0 = (TabImpl)this.mTabs.remove(v);
            if(windowDecorActionBar$TabImpl0 != null) {
                windowDecorActionBar$TabImpl0.setPosition(-1);
            }
            int v2 = this.mTabs.size();
            for(int v3 = v; v3 < v2; ++v3) {
                ((TabImpl)this.mTabs.get(v3)).setPosition(v3);
            }
            if(v1 == v) {
                this.selectTab((this.mTabs.isEmpty() ? null : ((Tab)this.mTabs.get(Math.max(0, v - 1)))));
            }
        }
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
        int v = -1;
        if(this.getNavigationMode() != 2) {
            if(actionBar$Tab0 != null) {
                v = actionBar$Tab0.getPosition();
            }
            this.mSavedTabPosition = v;
            return;
        }
        FragmentTransaction fragmentTransaction0 = !(this.mActivity instanceof FragmentActivity) || this.mDecorToolbar.getViewGroup().isInEditMode() ? null : ((FragmentActivity)this.mActivity).getSupportFragmentManager().beginTransaction().disallowAddToBackStack();
        TabImpl windowDecorActionBar$TabImpl0 = this.mSelectedTab;
        if(windowDecorActionBar$TabImpl0 != actionBar$Tab0) {
            ScrollingTabContainerView scrollingTabContainerView0 = this.mTabScrollView;
            if(actionBar$Tab0 != null) {
                v = actionBar$Tab0.getPosition();
            }
            scrollingTabContainerView0.setTabSelected(v);
            TabImpl windowDecorActionBar$TabImpl1 = this.mSelectedTab;
            if(windowDecorActionBar$TabImpl1 != null) {
                windowDecorActionBar$TabImpl1.getCallback().onTabUnselected(this.mSelectedTab, fragmentTransaction0);
            }
            this.mSelectedTab = (TabImpl)actionBar$Tab0;
            if(((TabImpl)actionBar$Tab0) != null) {
                ((TabImpl)actionBar$Tab0).getCallback().onTabSelected(this.mSelectedTab, fragmentTransaction0);
            }
        }
        else if(windowDecorActionBar$TabImpl0 != null) {
            windowDecorActionBar$TabImpl0.getCallback().onTabReselected(this.mSelectedTab, fragmentTransaction0);
            this.mTabScrollView.animateToTab(actionBar$Tab0.getPosition());
        }
        if(fragmentTransaction0 != null && !fragmentTransaction0.isEmpty()) {
            fragmentTransaction0.commit();
        }
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void setBackgroundDrawable(Drawable drawable0) {
        this.mContainerView.setPrimaryBackground(drawable0);
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void setCustomView(int v) {
        this.setCustomView(LayoutInflater.from(this.getThemedContext()).inflate(v, this.mDecorToolbar.getViewGroup(), false));
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void setCustomView(View view0) {
        this.mDecorToolbar.setCustomView(view0);
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void setCustomView(View view0, LayoutParams actionBar$LayoutParams0) {
        view0.setLayoutParams(actionBar$LayoutParams0);
        this.mDecorToolbar.setCustomView(view0);
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void setDefaultDisplayHomeAsUpEnabled(boolean z) {
        if(!this.mDisplayHomeAsUpSet) {
            this.setDisplayHomeAsUpEnabled(z);
        }
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void setDisplayHomeAsUpEnabled(boolean z) {
        this.setDisplayOptions((z ? 4 : 0), 4);
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void setDisplayOptions(int v) {
        if((v & 4) != 0) {
            this.mDisplayHomeAsUpSet = true;
        }
        this.mDecorToolbar.setDisplayOptions(v);
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void setDisplayOptions(int v, int v1) {
        int v2 = this.mDecorToolbar.getDisplayOptions();
        if((v1 & 4) != 0) {
            this.mDisplayHomeAsUpSet = true;
        }
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
        ViewCompat.setElevation(this.mContainerView, f);
    }

    private void setHasEmbeddedTabs(boolean z) {
        this.mHasEmbeddedTabs = z;
        if(z) {
            this.mContainerView.setTabContainer(null);
            this.mDecorToolbar.setEmbeddedTabView(this.mTabScrollView);
        }
        else {
            this.mDecorToolbar.setEmbeddedTabView(null);
            this.mContainerView.setTabContainer(this.mTabScrollView);
        }
        boolean z1 = true;
        boolean z2 = this.getNavigationMode() == 2;
        ScrollingTabContainerView scrollingTabContainerView0 = this.mTabScrollView;
        if(scrollingTabContainerView0 != null) {
            if(z2) {
                scrollingTabContainerView0.setVisibility(0);
                ActionBarOverlayLayout actionBarOverlayLayout0 = this.mOverlayLayout;
                if(actionBarOverlayLayout0 != null) {
                    ViewCompat.requestApplyInsets(actionBarOverlayLayout0);
                }
            }
            else {
                scrollingTabContainerView0.setVisibility(8);
            }
        }
        this.mDecorToolbar.setCollapsible(!this.mHasEmbeddedTabs && z2);
        ActionBarOverlayLayout actionBarOverlayLayout1 = this.mOverlayLayout;
        if(this.mHasEmbeddedTabs || !z2) {
            z1 = false;
        }
        actionBarOverlayLayout1.setHasNonEmbeddedTabs(z1);
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void setHideOffset(int v) {
        if(v != 0 && !this.mOverlayLayout.isInOverlayMode()) {
            throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to set a non-zero hide offset");
        }
        this.mOverlayLayout.setActionBarHideOffset(v);
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void setHideOnContentScrollEnabled(boolean z) {
        if(z && !this.mOverlayLayout.isInOverlayMode()) {
            throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
        }
        this.mHideOnContentScroll = z;
        this.mOverlayLayout.setHideOnContentScrollEnabled(z);
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
        this.mDecorToolbar.setHomeButtonEnabled(z);
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
        this.mDecorToolbar.setDropdownParams(spinnerAdapter0, new NavItemSelectedListener(actionBar$OnNavigationListener0));
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
        int v1 = this.mDecorToolbar.getNavigationMode();
        if(v1 == 2) {
            this.mSavedTabPosition = this.getSelectedNavigationIndex();
            this.selectTab(null);
            this.mTabScrollView.setVisibility(8);
        }
        if(v1 != v && !this.mHasEmbeddedTabs) {
            ActionBarOverlayLayout actionBarOverlayLayout0 = this.mOverlayLayout;
            if(actionBarOverlayLayout0 != null) {
                ViewCompat.requestApplyInsets(actionBarOverlayLayout0);
            }
        }
        this.mDecorToolbar.setNavigationMode(v);
        boolean z = false;
        if(v == 2) {
            this.ensureTabsExist();
            this.mTabScrollView.setVisibility(0);
            int v2 = this.mSavedTabPosition;
            if(v2 != -1) {
                this.setSelectedNavigationItem(v2);
                this.mSavedTabPosition = -1;
            }
        }
        this.mDecorToolbar.setCollapsible(v == 2 && !this.mHasEmbeddedTabs);
        ActionBarOverlayLayout actionBarOverlayLayout1 = this.mOverlayLayout;
        if(v == 2 && !this.mHasEmbeddedTabs) {
            z = true;
        }
        actionBarOverlayLayout1.setHasNonEmbeddedTabs(z);
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void setSelectedNavigationItem(int v) {
        switch(this.mDecorToolbar.getNavigationMode()) {
            case 1: {
                this.mDecorToolbar.setDropdownSelectedPosition(v);
                return;
            }
            case 2: {
                this.selectTab(((Tab)this.mTabs.get(v)));
                return;
            }
            default: {
                throw new IllegalStateException("setSelectedNavigationIndex not valid for current navigation mode");
            }
        }
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void setShowHideAnimationEnabled(boolean z) {
        this.mShowHideAnimationEnabled = z;
        if(!z) {
            ViewPropertyAnimatorCompatSet viewPropertyAnimatorCompatSet0 = this.mCurrentShowAnim;
            if(viewPropertyAnimatorCompatSet0 != null) {
                viewPropertyAnimatorCompatSet0.cancel();
            }
        }
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void setSplitBackgroundDrawable(Drawable drawable0) {
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void setStackedBackgroundDrawable(Drawable drawable0) {
        this.mContainerView.setStackedBackground(drawable0);
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void setSubtitle(int v) {
        this.setSubtitle(this.mContext.getString(v));
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void setSubtitle(CharSequence charSequence0) {
        this.mDecorToolbar.setSubtitle(charSequence0);
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void setTitle(int v) {
        this.setTitle(this.mContext.getString(v));
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void setTitle(CharSequence charSequence0) {
        this.mDecorToolbar.setTitle(charSequence0);
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void setWindowTitle(CharSequence charSequence0) {
        this.mDecorToolbar.setWindowTitle(charSequence0);
    }

    private boolean shouldAnimateContextView() {
        return this.mContainerView.isLaidOut();
    }

    @Override  // androidx.appcompat.app.ActionBar
    public void show() {
        if(this.mHiddenByApp) {
            this.mHiddenByApp = false;
            this.updateVisibility(false);
        }
    }

    private void showForActionMode() {
        if(!this.mShowingForMode) {
            this.mShowingForMode = true;
            this.updateVisibility(false);
        }
    }

    @Override  // androidx.appcompat.widget.ActionBarOverlayLayout$ActionBarVisibilityCallback
    public void showForSystem() {
        if(this.mHiddenBySystem) {
            this.mHiddenBySystem = false;
            this.updateVisibility(true);
        }
    }

    @Override  // androidx.appcompat.app.ActionBar
    public ActionMode startActionMode(androidx.appcompat.view.ActionMode.Callback actionMode$Callback0) {
        ActionModeImpl windowDecorActionBar$ActionModeImpl0 = this.mActionMode;
        if(windowDecorActionBar$ActionModeImpl0 != null) {
            windowDecorActionBar$ActionModeImpl0.finish();
        }
        this.mOverlayLayout.setHideOnContentScrollEnabled(false);
        this.mContextView.killMode();
        ActionModeImpl windowDecorActionBar$ActionModeImpl1 = new ActionModeImpl(this, this.mContextView.getContext(), actionMode$Callback0);
        if(windowDecorActionBar$ActionModeImpl1.dispatchOnCreate()) {
            this.mActionMode = windowDecorActionBar$ActionModeImpl1;
            windowDecorActionBar$ActionModeImpl1.invalidate();
            this.mContextView.initForMode(windowDecorActionBar$ActionModeImpl1);
            this.animateToMode(true);
            return windowDecorActionBar$ActionModeImpl1;
        }
        return null;
    }

    private void updateVisibility(boolean z) {
        if(WindowDecorActionBar.checkShowingFlags(this.mHiddenByApp, this.mHiddenBySystem, this.mShowingForMode)) {
            if(!this.mNowShowing) {
                this.mNowShowing = true;
                this.doShow(z);
            }
        }
        else if(this.mNowShowing) {
            this.mNowShowing = false;
            this.doHide(z);
        }
    }
}

