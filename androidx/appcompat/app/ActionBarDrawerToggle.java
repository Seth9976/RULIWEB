package androidx.appcompat.app;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.view.View;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout.DrawerListener;
import androidx.drawerlayout.widget.DrawerLayout;

public class ActionBarDrawerToggle implements DrawerListener {
    public interface Delegate {
        Context getActionBarThemedContext();

        Drawable getThemeUpIndicator();

        boolean isNavigationVisible();

        void setActionBarDescription(int arg1);

        void setActionBarUpIndicator(Drawable arg1, int arg2);
    }

    public interface DelegateProvider {
        Delegate getDrawerToggleDelegate();
    }

    static class FrameworkActionBarDelegate implements Delegate {
        private final Activity mActivity;

        FrameworkActionBarDelegate(Activity activity0) {
            this.mActivity = activity0;
        }

        @Override  // androidx.appcompat.app.ActionBarDrawerToggle$Delegate
        public Context getActionBarThemedContext() {
            ActionBar actionBar0 = this.mActivity.getActionBar();
            return actionBar0 != null ? actionBar0.getThemedContext() : this.mActivity;
        }

        @Override  // androidx.appcompat.app.ActionBarDrawerToggle$Delegate
        public Drawable getThemeUpIndicator() {
            TypedArray typedArray0 = this.getActionBarThemedContext().obtainStyledAttributes(null, new int[]{0x101030B}, 0x10102CE, 0);
            Drawable drawable0 = typedArray0.getDrawable(0);
            typedArray0.recycle();
            return drawable0;
        }

        @Override  // androidx.appcompat.app.ActionBarDrawerToggle$Delegate
        public boolean isNavigationVisible() {
            ActionBar actionBar0 = this.mActivity.getActionBar();
            return actionBar0 != null && (actionBar0.getDisplayOptions() & 4) != 0;
        }

        @Override  // androidx.appcompat.app.ActionBarDrawerToggle$Delegate
        public void setActionBarDescription(int v) {
            ActionBar actionBar0 = this.mActivity.getActionBar();
            if(actionBar0 != null) {
                actionBar0.setHomeActionContentDescription(v);
            }
        }

        @Override  // androidx.appcompat.app.ActionBarDrawerToggle$Delegate
        public void setActionBarUpIndicator(Drawable drawable0, int v) {
            ActionBar actionBar0 = this.mActivity.getActionBar();
            if(actionBar0 != null) {
                actionBar0.setHomeAsUpIndicator(drawable0);
                actionBar0.setHomeActionContentDescription(v);
            }
        }
    }

    static class ToolbarCompatDelegate implements Delegate {
        final CharSequence mDefaultContentDescription;
        final Drawable mDefaultUpIndicator;
        final Toolbar mToolbar;

        ToolbarCompatDelegate(Toolbar toolbar0) {
            this.mToolbar = toolbar0;
            this.mDefaultUpIndicator = toolbar0.getNavigationIcon();
            this.mDefaultContentDescription = toolbar0.getNavigationContentDescription();
        }

        @Override  // androidx.appcompat.app.ActionBarDrawerToggle$Delegate
        public Context getActionBarThemedContext() {
            return this.mToolbar.getContext();
        }

        @Override  // androidx.appcompat.app.ActionBarDrawerToggle$Delegate
        public Drawable getThemeUpIndicator() {
            return this.mDefaultUpIndicator;
        }

        @Override  // androidx.appcompat.app.ActionBarDrawerToggle$Delegate
        public boolean isNavigationVisible() {
            return true;
        }

        @Override  // androidx.appcompat.app.ActionBarDrawerToggle$Delegate
        public void setActionBarDescription(int v) {
            if(v == 0) {
                this.mToolbar.setNavigationContentDescription(this.mDefaultContentDescription);
                return;
            }
            this.mToolbar.setNavigationContentDescription(v);
        }

        @Override  // androidx.appcompat.app.ActionBarDrawerToggle$Delegate
        public void setActionBarUpIndicator(Drawable drawable0, int v) {
            this.mToolbar.setNavigationIcon(drawable0);
            this.setActionBarDescription(v);
        }
    }

    private final Delegate mActivityImpl;
    private final int mCloseDrawerContentDescRes;
    boolean mDrawerIndicatorEnabled;
    private final DrawerLayout mDrawerLayout;
    private boolean mDrawerSlideAnimationEnabled;
    private boolean mHasCustomUpIndicator;
    private Drawable mHomeAsUpIndicator;
    private final int mOpenDrawerContentDescRes;
    private DrawerArrowDrawable mSlider;
    View.OnClickListener mToolbarNavigationClickListener;
    private boolean mWarnedForDisplayHomeAsUp;

    ActionBarDrawerToggle(Activity activity0, Toolbar toolbar0, DrawerLayout drawerLayout0, DrawerArrowDrawable drawerArrowDrawable0, int v, int v1) {
        this.mDrawerSlideAnimationEnabled = true;
        this.mDrawerIndicatorEnabled = true;
        this.mWarnedForDisplayHomeAsUp = false;
        if(toolbar0 != null) {
            this.mActivityImpl = new ToolbarCompatDelegate(toolbar0);
            toolbar0.setNavigationOnClickListener(new View.OnClickListener() {
                @Override  // android.view.View$OnClickListener
                public void onClick(View view0) {
                    if(ActionBarDrawerToggle.this.mDrawerIndicatorEnabled) {
                        ActionBarDrawerToggle.this.toggle();
                        return;
                    }
                    if(ActionBarDrawerToggle.this.mToolbarNavigationClickListener != null) {
                        ActionBarDrawerToggle.this.mToolbarNavigationClickListener.onClick(view0);
                    }
                }
            });
        }
        else if(activity0 instanceof DelegateProvider) {
            this.mActivityImpl = ((DelegateProvider)activity0).getDrawerToggleDelegate();
        }
        else {
            this.mActivityImpl = new FrameworkActionBarDelegate(activity0);
        }
        this.mDrawerLayout = drawerLayout0;
        this.mOpenDrawerContentDescRes = v;
        this.mCloseDrawerContentDescRes = v1;
        this.mSlider = drawerArrowDrawable0 == null ? new DrawerArrowDrawable(this.mActivityImpl.getActionBarThemedContext()) : drawerArrowDrawable0;
        this.mHomeAsUpIndicator = this.getThemeUpIndicator();
    }

    public ActionBarDrawerToggle(Activity activity0, DrawerLayout drawerLayout0, int v, int v1) {
        this(activity0, null, drawerLayout0, null, v, v1);
    }

    public ActionBarDrawerToggle(Activity activity0, DrawerLayout drawerLayout0, Toolbar toolbar0, int v, int v1) {
        this(activity0, toolbar0, drawerLayout0, null, v, v1);
    }

    public DrawerArrowDrawable getDrawerArrowDrawable() {
        return this.mSlider;
    }

    Drawable getThemeUpIndicator() {
        return this.mActivityImpl.getThemeUpIndicator();
    }

    public View.OnClickListener getToolbarNavigationClickListener() {
        return this.mToolbarNavigationClickListener;
    }

    public boolean isDrawerIndicatorEnabled() {
        return this.mDrawerIndicatorEnabled;
    }

    public boolean isDrawerSlideAnimationEnabled() {
        return this.mDrawerSlideAnimationEnabled;
    }

    public void onConfigurationChanged(Configuration configuration0) {
        if(!this.mHasCustomUpIndicator) {
            this.mHomeAsUpIndicator = this.getThemeUpIndicator();
        }
        this.syncState();
    }

    @Override  // androidx.drawerlayout.widget.DrawerLayout$DrawerListener
    public void onDrawerClosed(View view0) {
        this.setPosition(0.0f);
        if(this.mDrawerIndicatorEnabled) {
            this.setActionBarDescription(this.mOpenDrawerContentDescRes);
        }
    }

    @Override  // androidx.drawerlayout.widget.DrawerLayout$DrawerListener
    public void onDrawerOpened(View view0) {
        this.setPosition(1.0f);
        if(this.mDrawerIndicatorEnabled) {
            this.setActionBarDescription(this.mCloseDrawerContentDescRes);
        }
    }

    @Override  // androidx.drawerlayout.widget.DrawerLayout$DrawerListener
    public void onDrawerSlide(View view0, float f) {
        if(this.mDrawerSlideAnimationEnabled) {
            this.setPosition(Math.min(1.0f, Math.max(0.0f, f)));
            return;
        }
        this.setPosition(0.0f);
    }

    @Override  // androidx.drawerlayout.widget.DrawerLayout$DrawerListener
    public void onDrawerStateChanged(int v) {
    }

    public boolean onOptionsItemSelected(MenuItem menuItem0) {
        if(menuItem0 != null && menuItem0.getItemId() == 0x102002C && this.mDrawerIndicatorEnabled) {
            this.toggle();
            return true;
        }
        return false;
    }

    void setActionBarDescription(int v) {
        this.mActivityImpl.setActionBarDescription(v);
    }

    void setActionBarUpIndicator(Drawable drawable0, int v) {
        if(!this.mWarnedForDisplayHomeAsUp && !this.mActivityImpl.isNavigationVisible()) {
            Log.w("ActionBarDrawerToggle", "DrawerToggle may not show up because NavigationIcon is not visible. You may need to call actionbar.setDisplayHomeAsUpEnabled(true);");
            this.mWarnedForDisplayHomeAsUp = true;
        }
        this.mActivityImpl.setActionBarUpIndicator(drawable0, v);
    }

    public void setDrawerArrowDrawable(DrawerArrowDrawable drawerArrowDrawable0) {
        this.mSlider = drawerArrowDrawable0;
        this.syncState();
    }

    public void setDrawerIndicatorEnabled(boolean z) {
        if(z != this.mDrawerIndicatorEnabled) {
            if(z) {
                this.setActionBarUpIndicator(this.mSlider, (this.mDrawerLayout.isDrawerOpen(0x800003) ? this.mCloseDrawerContentDescRes : this.mOpenDrawerContentDescRes));
            }
            else {
                this.setActionBarUpIndicator(this.mHomeAsUpIndicator, 0);
            }
            this.mDrawerIndicatorEnabled = z;
        }
    }

    public void setDrawerSlideAnimationEnabled(boolean z) {
        this.mDrawerSlideAnimationEnabled = z;
        if(!z) {
            this.setPosition(0.0f);
        }
    }

    public void setHomeAsUpIndicator(int v) {
        this.setHomeAsUpIndicator((v == 0 ? null : this.mDrawerLayout.getResources().getDrawable(v)));
    }

    public void setHomeAsUpIndicator(Drawable drawable0) {
        if(drawable0 == null) {
            this.mHomeAsUpIndicator = this.getThemeUpIndicator();
            this.mHasCustomUpIndicator = false;
        }
        else {
            this.mHomeAsUpIndicator = drawable0;
            this.mHasCustomUpIndicator = true;
        }
        if(!this.mDrawerIndicatorEnabled) {
            this.setActionBarUpIndicator(this.mHomeAsUpIndicator, 0);
        }
    }

    private void setPosition(float f) {
        switch(f) {
            case 0: {
                this.mSlider.setVerticalMirror(false);
                break;
            }
            case 0x3F800000: {
                this.mSlider.setVerticalMirror(true);
            }
        }
        this.mSlider.setProgress(f);
    }

    public void setToolbarNavigationClickListener(View.OnClickListener view$OnClickListener0) {
        this.mToolbarNavigationClickListener = view$OnClickListener0;
    }

    public void syncState() {
        if(this.mDrawerLayout.isDrawerOpen(0x800003)) {
            this.setPosition(1.0f);
        }
        else {
            this.setPosition(0.0f);
        }
        if(this.mDrawerIndicatorEnabled) {
            this.setActionBarUpIndicator(this.mSlider, (this.mDrawerLayout.isDrawerOpen(0x800003) ? this.mCloseDrawerContentDescRes : this.mOpenDrawerContentDescRes));
        }
    }

    void toggle() {
        int v = this.mDrawerLayout.getDrawerLockMode(0x800003);
        if(this.mDrawerLayout.isDrawerVisible(0x800003) && v != 2) {
            this.mDrawerLayout.closeDrawer(0x800003);
            return;
        }
        if(v != 1) {
            this.mDrawerLayout.openDrawer(0x800003);
        }
    }
}

