package androidx.appcompat.app;

import android.app.Activity;
import android.app.Dialog;
import android.app.UiModeManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources.NotFoundException;
import android.content.res.Resources.Theme;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.LocaleList;
import android.os.Parcel;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import android.os.PowerManager;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.ActionMode.Callback;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater.Factory2;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window.Callback;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.view.WindowManager;
import android.widget.FrameLayout.LayoutParams;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.appcompat.R.attr;
import androidx.appcompat.R.color;
import androidx.appcompat.R.id;
import androidx.appcompat.R.layout;
import androidx.appcompat.R.style;
import androidx.appcompat.R.styleable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.view.StandaloneActionMode;
import androidx.appcompat.view.SupportActionModeWrapper.CallbackWrapper;
import androidx.appcompat.view.SupportMenuInflater;
import androidx.appcompat.view.WindowCallbackWrapper;
import androidx.appcompat.view.menu.ListMenuPresenter;
import androidx.appcompat.view.menu.MenuBuilder.Callback;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.AppCompatDrawableManager;
import androidx.appcompat.widget.ContentFrameLayout.OnAttachListener;
import androidx.appcompat.widget.ContentFrameLayout;
import androidx.appcompat.widget.DecorContentParent;
import androidx.appcompat.widget.FitWindowsViewGroup.OnFitSystemWindowsListener;
import androidx.appcompat.widget.TintTypedArray;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.ViewStubCompat;
import androidx.appcompat.widget.ViewUtils;
import androidx.collection.SimpleArrayMap;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NavUtils;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat.ThemeCompat;
import androidx.core.os.LocaleListCompat;
import androidx.core.util.ObjectsCompat;
import androidx.core.view.KeyEventDispatcher.Component;
import androidx.core.view.KeyEventDispatcher;
import androidx.core.view.LayoutInflaterCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.core.view.ViewPropertyAnimatorListenerAdapter;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.widget.PopupWindowCompat;
import androidx.lifecycle.Lifecycle.State;
import androidx.lifecycle.LifecycleOwner;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import org.xmlpull.v1.XmlPullParser;

class AppCompatDelegateImpl extends AppCompatDelegate implements LayoutInflater.Factory2, Callback {
    class ActionBarDrawableToggleImpl implements Delegate {
        @Override  // androidx.appcompat.app.ActionBarDrawerToggle$Delegate
        public Context getActionBarThemedContext() {
            return AppCompatDelegateImpl.this.getActionBarThemedContext();
        }

        @Override  // androidx.appcompat.app.ActionBarDrawerToggle$Delegate
        public Drawable getThemeUpIndicator() {
            TintTypedArray tintTypedArray0 = TintTypedArray.obtainStyledAttributes(this.getActionBarThemedContext(), null, new int[]{attr.homeAsUpIndicator});
            Drawable drawable0 = tintTypedArray0.getDrawable(0);
            tintTypedArray0.recycle();
            return drawable0;
        }

        @Override  // androidx.appcompat.app.ActionBarDrawerToggle$Delegate
        public boolean isNavigationVisible() {
            ActionBar actionBar0 = AppCompatDelegateImpl.this.getSupportActionBar();
            return actionBar0 != null && (actionBar0.getDisplayOptions() & 4) != 0;
        }

        @Override  // androidx.appcompat.app.ActionBarDrawerToggle$Delegate
        public void setActionBarDescription(int v) {
            ActionBar actionBar0 = AppCompatDelegateImpl.this.getSupportActionBar();
            if(actionBar0 != null) {
                actionBar0.setHomeActionContentDescription(v);
            }
        }

        @Override  // androidx.appcompat.app.ActionBarDrawerToggle$Delegate
        public void setActionBarUpIndicator(Drawable drawable0, int v) {
            ActionBar actionBar0 = AppCompatDelegateImpl.this.getSupportActionBar();
            if(actionBar0 != null) {
                actionBar0.setHomeAsUpIndicator(drawable0);
                actionBar0.setHomeActionContentDescription(v);
            }
        }
    }

    interface ActionBarMenuCallback {
        View onCreatePanelView(int arg1);

        boolean onPreparePanel(int arg1);
    }

    final class ActionMenuPresenterCallback implements androidx.appcompat.view.menu.MenuPresenter.Callback {
        @Override  // androidx.appcompat.view.menu.MenuPresenter$Callback
        public void onCloseMenu(MenuBuilder menuBuilder0, boolean z) {
            AppCompatDelegateImpl.this.checkCloseActionMenu(menuBuilder0);
        }

        @Override  // androidx.appcompat.view.menu.MenuPresenter$Callback
        public boolean onOpenSubMenu(MenuBuilder menuBuilder0) {
            Window.Callback window$Callback0 = AppCompatDelegateImpl.this.getWindowCallback();
            if(window$Callback0 != null) {
                window$Callback0.onMenuOpened(108, menuBuilder0);
            }
            return true;
        }
    }

    class ActionModeCallbackWrapperV9 implements androidx.appcompat.view.ActionMode.Callback {
        private androidx.appcompat.view.ActionMode.Callback mWrapped;

        public ActionModeCallbackWrapperV9(androidx.appcompat.view.ActionMode.Callback actionMode$Callback0) {
            this.mWrapped = actionMode$Callback0;
        }

        @Override  // androidx.appcompat.view.ActionMode$Callback
        public boolean onActionItemClicked(ActionMode actionMode0, MenuItem menuItem0) {
            return this.mWrapped.onActionItemClicked(actionMode0, menuItem0);
        }

        @Override  // androidx.appcompat.view.ActionMode$Callback
        public boolean onCreateActionMode(ActionMode actionMode0, Menu menu0) {
            return this.mWrapped.onCreateActionMode(actionMode0, menu0);
        }

        @Override  // androidx.appcompat.view.ActionMode$Callback
        public void onDestroyActionMode(ActionMode actionMode0) {
            this.mWrapped.onDestroyActionMode(actionMode0);
            if(AppCompatDelegateImpl.this.mActionModePopup != null) {
                AppCompatDelegateImpl.this.mWindow.getDecorView().removeCallbacks(AppCompatDelegateImpl.this.mShowActionModePopup);
            }
            if(AppCompatDelegateImpl.this.mActionModeView != null) {
                AppCompatDelegateImpl.this.endOnGoingFadeAnimation();
                AppCompatDelegateImpl.this.mFadeAnim = ViewCompat.animate(AppCompatDelegateImpl.this.mActionModeView).alpha(0.0f);
                AppCompatDelegateImpl.this.mFadeAnim.setListener(new ViewPropertyAnimatorListenerAdapter() {
                    @Override  // androidx.core.view.ViewPropertyAnimatorListenerAdapter
                    public void onAnimationEnd(View view0) {
                        AppCompatDelegateImpl.this.mActionModeView.setVisibility(8);
                        if(AppCompatDelegateImpl.this.mActionModePopup != null) {
                            AppCompatDelegateImpl.this.mActionModePopup.dismiss();
                        }
                        else if(AppCompatDelegateImpl.this.mActionModeView.getParent() instanceof View) {
                            ViewCompat.requestApplyInsets(((View)AppCompatDelegateImpl.this.mActionModeView.getParent()));
                        }
                        AppCompatDelegateImpl.this.mActionModeView.killMode();
                        AppCompatDelegateImpl.this.mFadeAnim.setListener(null);
                        AppCompatDelegateImpl.this.mFadeAnim = null;
                        ViewCompat.requestApplyInsets(AppCompatDelegateImpl.this.mSubDecor);
                    }
                });
            }
            if(AppCompatDelegateImpl.this.mAppCompatCallback != null) {
                AppCompatDelegateImpl.this.mAppCompatCallback.onSupportActionModeFinished(AppCompatDelegateImpl.this.mActionMode);
            }
            AppCompatDelegateImpl.this.mActionMode = null;
            ViewCompat.requestApplyInsets(AppCompatDelegateImpl.this.mSubDecor);
            AppCompatDelegateImpl.this.updateBackInvokedCallbackState();
        }

        @Override  // androidx.appcompat.view.ActionMode$Callback
        public boolean onPrepareActionMode(ActionMode actionMode0, Menu menu0) {
            ViewCompat.requestApplyInsets(AppCompatDelegateImpl.this.mSubDecor);
            return this.mWrapped.onPrepareActionMode(actionMode0, menu0);
        }
    }

    static class Api21Impl {
        static boolean isPowerSaveMode(PowerManager powerManager0) {
            return powerManager0.isPowerSaveMode();
        }

        static String toLanguageTag(Locale locale0) {
            return locale0.toLanguageTag();
        }
    }

    static class Api24Impl {
        static void generateConfigDelta_locale(Configuration configuration0, Configuration configuration1, Configuration configuration2) {
            LocaleList localeList0 = configuration0.getLocales();
            LocaleList localeList1 = configuration1.getLocales();
            if(!localeList0.equals(localeList1)) {
                configuration2.setLocales(localeList1);
                configuration2.locale = configuration1.locale;
            }
        }

        static LocaleListCompat getLocales(Configuration configuration0) {
            return LocaleListCompat.forLanguageTags(configuration0.getLocales().toLanguageTags());
        }

        public static void setDefaultLocales(LocaleListCompat localeListCompat0) {
            LocaleList.setDefault(LocaleList.forLanguageTags(localeListCompat0.toLanguageTags()));
        }

        static void setLocales(Configuration configuration0, LocaleListCompat localeListCompat0) {
            configuration0.setLocales(LocaleList.forLanguageTags(localeListCompat0.toLanguageTags()));
        }
    }

    static class Api26Impl {
        static void generateConfigDelta_colorMode(Configuration configuration0, Configuration configuration1, Configuration configuration2) {
            if((configuration0.colorMode & 3) != (configuration1.colorMode & 3)) {
                configuration2.colorMode |= configuration1.colorMode & 3;
            }
            if((configuration0.colorMode & 12) != (configuration1.colorMode & 12)) {
                configuration2.colorMode |= configuration1.colorMode & 12;
            }
        }
    }

    static class Api33Impl {
        static OnBackInvokedDispatcher getOnBackInvokedDispatcher(Activity activity0) {
            return activity0.getOnBackInvokedDispatcher();
        }

        static OnBackInvokedCallback registerOnBackPressedCallback(Object object0, AppCompatDelegateImpl appCompatDelegateImpl0) {
            Objects.requireNonNull(appCompatDelegateImpl0);
            OnBackInvokedCallback onBackInvokedCallback0 = new AppCompatDelegateImpl.Api33Impl..ExternalSyntheticLambda5(appCompatDelegateImpl0);
            ((OnBackInvokedDispatcher)object0).registerOnBackInvokedCallback(1000000, onBackInvokedCallback0);
            return onBackInvokedCallback0;
        }

        static void unregisterOnBackInvokedCallback(Object object0, Object object1) {
            ((OnBackInvokedDispatcher)object0).unregisterOnBackInvokedCallback(((OnBackInvokedCallback)object1));
        }
    }

    class AppCompatWindowCallback extends WindowCallbackWrapper {
        private ActionBarMenuCallback mActionBarCallback;
        private boolean mDispatchKeyEventBypassEnabled;
        private boolean mOnContentChangedBypassEnabled;
        private boolean mOnPanelClosedBypassEnabled;

        AppCompatWindowCallback(Window.Callback window$Callback0) {
            super(window$Callback0);
        }

        public boolean bypassDispatchKeyEvent(Window.Callback window$Callback0, KeyEvent keyEvent0) {
            try {
                this.mDispatchKeyEventBypassEnabled = true;
                boolean z = window$Callback0.dispatchKeyEvent(keyEvent0);
                this.mDispatchKeyEventBypassEnabled = false;
                return z;
            }
            catch(Throwable throwable0) {
                this.mDispatchKeyEventBypassEnabled = false;
                throw throwable0;
            }
        }

        public void bypassOnContentChanged(Window.Callback window$Callback0) {
            try {
                this.mOnContentChangedBypassEnabled = true;
                window$Callback0.onContentChanged();
                this.mOnContentChangedBypassEnabled = false;
            }
            catch(Throwable throwable0) {
                this.mOnContentChangedBypassEnabled = false;
                throw throwable0;
            }
        }

        public void bypassOnPanelClosed(Window.Callback window$Callback0, int v, Menu menu0) {
            try {
                this.mOnPanelClosedBypassEnabled = true;
                window$Callback0.onPanelClosed(v, menu0);
                this.mOnPanelClosedBypassEnabled = false;
            }
            catch(Throwable throwable0) {
                this.mOnPanelClosedBypassEnabled = false;
                throw throwable0;
            }
        }

        // 去混淆评级： 低(40)
        @Override  // androidx.appcompat.view.WindowCallbackWrapper
        public boolean dispatchKeyEvent(KeyEvent keyEvent0) {
            return this.mDispatchKeyEventBypassEnabled ? this.getWrapped().dispatchKeyEvent(keyEvent0) : AppCompatDelegateImpl.this.dispatchKeyEvent(keyEvent0) || super.dispatchKeyEvent(keyEvent0);
        }

        @Override  // androidx.appcompat.view.WindowCallbackWrapper
        public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent0) {
            if(!super.dispatchKeyShortcutEvent(keyEvent0)) {
                int v = keyEvent0.getKeyCode();
                return AppCompatDelegateImpl.this.onKeyShortcut(v, keyEvent0);
            }
            return true;
        }

        @Override  // androidx.appcompat.view.WindowCallbackWrapper
        public void onContentChanged() {
            if(this.mOnContentChangedBypassEnabled) {
                this.getWrapped().onContentChanged();
            }
        }

        @Override  // androidx.appcompat.view.WindowCallbackWrapper
        public boolean onCreatePanelMenu(int v, Menu menu0) {
            return v != 0 || menu0 instanceof MenuBuilder ? super.onCreatePanelMenu(v, menu0) : false;
        }

        @Override  // androidx.appcompat.view.WindowCallbackWrapper
        public View onCreatePanelView(int v) {
            ActionBarMenuCallback appCompatDelegateImpl$ActionBarMenuCallback0 = this.mActionBarCallback;
            if(appCompatDelegateImpl$ActionBarMenuCallback0 != null) {
                View view0 = appCompatDelegateImpl$ActionBarMenuCallback0.onCreatePanelView(v);
                return view0 == null ? super.onCreatePanelView(v) : view0;
            }
            return super.onCreatePanelView(v);
        }

        @Override  // androidx.appcompat.view.WindowCallbackWrapper
        public boolean onMenuOpened(int v, Menu menu0) {
            super.onMenuOpened(v, menu0);
            AppCompatDelegateImpl.this.onMenuOpened(v);
            return true;
        }

        @Override  // androidx.appcompat.view.WindowCallbackWrapper
        public void onPanelClosed(int v, Menu menu0) {
            if(this.mOnPanelClosedBypassEnabled) {
                this.getWrapped().onPanelClosed(v, menu0);
                return;
            }
            super.onPanelClosed(v, menu0);
            AppCompatDelegateImpl.this.onPanelClosed(v);
        }

        @Override  // androidx.appcompat.view.WindowCallbackWrapper
        public boolean onPreparePanel(int v, View view0, Menu menu0) {
            MenuBuilder menuBuilder0 = menu0 instanceof MenuBuilder ? ((MenuBuilder)menu0) : null;
            boolean z = true;
            if(v == 0 && menuBuilder0 == null) {
                return false;
            }
            if(menuBuilder0 != null) {
                menuBuilder0.setOverrideVisibleItems(true);
            }
            if(this.mActionBarCallback == null || !this.mActionBarCallback.onPreparePanel(v)) {
                z = super.onPreparePanel(v, view0, menu0);
            }
            if(menuBuilder0 != null) {
                menuBuilder0.setOverrideVisibleItems(false);
            }
            return z;
        }

        @Override  // androidx.appcompat.view.WindowCallbackWrapper
        public void onProvideKeyboardShortcuts(List list0, Menu menu0, int v) {
            PanelFeatureState appCompatDelegateImpl$PanelFeatureState0 = AppCompatDelegateImpl.this.getPanelState(0, true);
            if(appCompatDelegateImpl$PanelFeatureState0 != null && appCompatDelegateImpl$PanelFeatureState0.menu != null) {
                super.onProvideKeyboardShortcuts(list0, appCompatDelegateImpl$PanelFeatureState0.menu, v);
                return;
            }
            super.onProvideKeyboardShortcuts(list0, menu0, v);
        }

        @Override  // androidx.appcompat.view.WindowCallbackWrapper
        public android.view.ActionMode onWindowStartingActionMode(ActionMode.Callback actionMode$Callback0) {
            if(Build.VERSION.SDK_INT >= 23) {
                return null;
            }
            return AppCompatDelegateImpl.this.isHandleNativeActionModesEnabled() ? this.startAsSupportActionMode(actionMode$Callback0) : super.onWindowStartingActionMode(actionMode$Callback0);
        }

        @Override  // androidx.appcompat.view.WindowCallbackWrapper
        public android.view.ActionMode onWindowStartingActionMode(ActionMode.Callback actionMode$Callback0, int v) {
            return !AppCompatDelegateImpl.this.isHandleNativeActionModesEnabled() || v != 0 ? super.onWindowStartingActionMode(actionMode$Callback0, v) : this.startAsSupportActionMode(actionMode$Callback0);
        }

        void setActionBarCallback(ActionBarMenuCallback appCompatDelegateImpl$ActionBarMenuCallback0) {
            this.mActionBarCallback = appCompatDelegateImpl$ActionBarMenuCallback0;
        }

        final android.view.ActionMode startAsSupportActionMode(ActionMode.Callback actionMode$Callback0) {
            CallbackWrapper supportActionModeWrapper$CallbackWrapper0 = new CallbackWrapper(AppCompatDelegateImpl.this.mContext, actionMode$Callback0);
            ActionMode actionMode0 = AppCompatDelegateImpl.this.startSupportActionMode(supportActionModeWrapper$CallbackWrapper0);
            return actionMode0 == null ? null : supportActionModeWrapper$CallbackWrapper0.getActionModeWrapper(actionMode0);
        }
    }

    class AutoBatteryNightModeManager extends AutoNightModeManager {
        private final PowerManager mPowerManager;

        AutoBatteryNightModeManager(Context context0) {
            this.mPowerManager = (PowerManager)context0.getApplicationContext().getSystemService("power");
        }

        @Override  // androidx.appcompat.app.AppCompatDelegateImpl$AutoNightModeManager
        IntentFilter createIntentFilterForBroadcastReceiver() {
            IntentFilter intentFilter0 = new IntentFilter();
            intentFilter0.addAction("android.os.action.POWER_SAVE_MODE_CHANGED");
            return intentFilter0;
        }

        // 去混淆评级： 低(20)
        @Override  // androidx.appcompat.app.AppCompatDelegateImpl$AutoNightModeManager
        public int getApplyableNightMode() {
            return Api21Impl.isPowerSaveMode(this.mPowerManager) ? 2 : 1;
        }

        @Override  // androidx.appcompat.app.AppCompatDelegateImpl$AutoNightModeManager
        public void onChange() {
            AppCompatDelegateImpl.this.applyDayNight();
        }
    }

    abstract class AutoNightModeManager {
        private BroadcastReceiver mReceiver;

        void cleanup() {
            if(this.mReceiver != null) {
                try {
                    AppCompatDelegateImpl.this.mContext.unregisterReceiver(this.mReceiver);
                }
                catch(IllegalArgumentException unused_ex) {
                }
                this.mReceiver = null;
            }
        }

        abstract IntentFilter createIntentFilterForBroadcastReceiver();

        abstract int getApplyableNightMode();

        boolean isListening() {
            return this.mReceiver != null;
        }

        abstract void onChange();

        void setup() {
            this.cleanup();
            IntentFilter intentFilter0 = this.createIntentFilterForBroadcastReceiver();
            if(intentFilter0 != null && intentFilter0.countActions() != 0) {
                if(this.mReceiver == null) {
                    this.mReceiver = new BroadcastReceiver() {
                        @Override  // android.content.BroadcastReceiver
                        public void onReceive(Context context0, Intent intent0) {
                            AutoNightModeManager.this.onChange();
                        }
                    };
                }
                AppCompatDelegateImpl.this.mContext.registerReceiver(this.mReceiver, intentFilter0);
            }
        }
    }

    class AutoTimeNightModeManager extends AutoNightModeManager {
        private final TwilightManager mTwilightManager;

        AutoTimeNightModeManager(TwilightManager twilightManager0) {
            this.mTwilightManager = twilightManager0;
        }

        @Override  // androidx.appcompat.app.AppCompatDelegateImpl$AutoNightModeManager
        IntentFilter createIntentFilterForBroadcastReceiver() {
            IntentFilter intentFilter0 = new IntentFilter();
            intentFilter0.addAction("android.intent.action.TIME_SET");
            intentFilter0.addAction("android.intent.action.TIMEZONE_CHANGED");
            intentFilter0.addAction("android.intent.action.TIME_TICK");
            return intentFilter0;
        }

        // 去混淆评级： 低(20)
        @Override  // androidx.appcompat.app.AppCompatDelegateImpl$AutoNightModeManager
        public int getApplyableNightMode() {
            return this.mTwilightManager.isNight() ? 2 : 1;
        }

        @Override  // androidx.appcompat.app.AppCompatDelegateImpl$AutoNightModeManager
        public void onChange() {
            AppCompatDelegateImpl.this.applyDayNight();
        }
    }

    class ListMenuDecorView extends ContentFrameLayout {
        public ListMenuDecorView(Context context0) {
            super(context0);
        }

        // 去混淆评级： 低(20)
        @Override  // android.view.ViewGroup
        public boolean dispatchKeyEvent(KeyEvent keyEvent0) {
            return AppCompatDelegateImpl.this.dispatchKeyEvent(keyEvent0) || super.dispatchKeyEvent(keyEvent0);
        }

        private boolean isOutOfBounds(int v, int v1) {
            return v < -5 || v1 < -5 || v > this.getWidth() + 5 || v1 > this.getHeight() + 5;
        }

        @Override  // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent motionEvent0) {
            if(motionEvent0.getAction() == 0 && this.isOutOfBounds(((int)motionEvent0.getX()), ((int)motionEvent0.getY()))) {
                AppCompatDelegateImpl.this.closePanel(0);
                return true;
            }
            return super.onInterceptTouchEvent(motionEvent0);
        }

        @Override  // android.view.View
        public void setBackgroundResource(int v) {
            this.setBackgroundDrawable(AppCompatResources.getDrawable(this.getContext(), v));
        }
    }

    public static final class PanelFeatureState {
        static class SavedState implements Parcelable {
            public static final Parcelable.Creator CREATOR;
            int featureId;
            boolean isOpen;
            Bundle menuState;

            static {
                SavedState.CREATOR = new Parcelable.ClassLoaderCreator() {
                    public SavedState createFromParcel(Parcel parcel0) {
                        return SavedState.readFromParcel(parcel0, null);
                    }

                    public SavedState createFromParcel(Parcel parcel0, ClassLoader classLoader0) {
                        return SavedState.readFromParcel(parcel0, classLoader0);
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

            @Override  // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            static SavedState readFromParcel(Parcel parcel0, ClassLoader classLoader0) {
                SavedState appCompatDelegateImpl$PanelFeatureState$SavedState0 = new SavedState();
                appCompatDelegateImpl$PanelFeatureState$SavedState0.featureId = parcel0.readInt();
                boolean z = parcel0.readInt() == 1;
                appCompatDelegateImpl$PanelFeatureState$SavedState0.isOpen = z;
                if(z) {
                    appCompatDelegateImpl$PanelFeatureState$SavedState0.menuState = parcel0.readBundle(classLoader0);
                }
                return appCompatDelegateImpl$PanelFeatureState$SavedState0;
            }

            @Override  // android.os.Parcelable
            public void writeToParcel(Parcel parcel0, int v) {
                parcel0.writeInt(this.featureId);
                parcel0.writeInt(((int)this.isOpen));
                if(this.isOpen) {
                    parcel0.writeBundle(this.menuState);
                }
            }
        }

        int background;
        View createdPanelView;
        ViewGroup decorView;
        int featureId;
        Bundle frozenActionViewState;
        Bundle frozenMenuState;
        int gravity;
        boolean isHandled;
        boolean isOpen;
        boolean isPrepared;
        ListMenuPresenter listMenuPresenter;
        Context listPresenterContext;
        MenuBuilder menu;
        public boolean qwertyMode;
        boolean refreshDecorView;
        boolean refreshMenuContent;
        View shownPanelView;
        boolean wasLastOpen;
        int windowAnimations;
        int x;
        int y;

        PanelFeatureState(int v) {
            this.featureId = v;
            this.refreshDecorView = false;
        }

        void applyFrozenState() {
            MenuBuilder menuBuilder0 = this.menu;
            if(menuBuilder0 != null) {
                Bundle bundle0 = this.frozenMenuState;
                if(bundle0 != null) {
                    menuBuilder0.restorePresenterStates(bundle0);
                    this.frozenMenuState = null;
                }
            }
        }

        public void clearMenuPresenters() {
            MenuBuilder menuBuilder0 = this.menu;
            if(menuBuilder0 != null) {
                menuBuilder0.removeMenuPresenter(this.listMenuPresenter);
            }
            this.listMenuPresenter = null;
        }

        MenuView getListMenuView(androidx.appcompat.view.menu.MenuPresenter.Callback menuPresenter$Callback0) {
            if(this.menu == null) {
                return null;
            }
            if(this.listMenuPresenter == null) {
                ListMenuPresenter listMenuPresenter0 = new ListMenuPresenter(this.listPresenterContext, layout.abc_list_menu_item_layout);
                this.listMenuPresenter = listMenuPresenter0;
                listMenuPresenter0.setCallback(menuPresenter$Callback0);
                this.menu.addMenuPresenter(this.listMenuPresenter);
            }
            return this.listMenuPresenter.getMenuView(this.decorView);
        }

        public boolean hasPanelItems() {
            if(this.shownPanelView == null) {
                return false;
            }
            return this.createdPanelView == null ? this.listMenuPresenter.getAdapter().getCount() > 0 : true;
        }

        void onRestoreInstanceState(Parcelable parcelable0) {
            this.featureId = ((SavedState)parcelable0).featureId;
            this.wasLastOpen = ((SavedState)parcelable0).isOpen;
            this.frozenMenuState = ((SavedState)parcelable0).menuState;
            this.shownPanelView = null;
            this.decorView = null;
        }

        Parcelable onSaveInstanceState() {
            Parcelable parcelable0 = new SavedState();
            parcelable0.featureId = this.featureId;
            parcelable0.isOpen = this.isOpen;
            if(this.menu != null) {
                parcelable0.menuState = new Bundle();
                this.menu.savePresenterStates(parcelable0.menuState);
            }
            return parcelable0;
        }

        void setMenu(MenuBuilder menuBuilder0) {
            MenuBuilder menuBuilder1 = this.menu;
            if(menuBuilder0 != menuBuilder1) {
                if(menuBuilder1 != null) {
                    menuBuilder1.removeMenuPresenter(this.listMenuPresenter);
                }
                this.menu = menuBuilder0;
                if(menuBuilder0 != null) {
                    ListMenuPresenter listMenuPresenter0 = this.listMenuPresenter;
                    if(listMenuPresenter0 != null) {
                        menuBuilder0.addMenuPresenter(listMenuPresenter0);
                    }
                }
            }
        }

        void setStyle(Context context0) {
            TypedValue typedValue0 = new TypedValue();
            Resources.Theme resources$Theme0 = context0.getResources().newTheme();
            resources$Theme0.setTo(context0.getTheme());
            resources$Theme0.resolveAttribute(attr.actionBarPopupTheme, typedValue0, true);
            if(typedValue0.resourceId != 0) {
                resources$Theme0.applyStyle(typedValue0.resourceId, true);
            }
            resources$Theme0.resolveAttribute(attr.panelMenuListTheme, typedValue0, true);
            if(typedValue0.resourceId == 0) {
                resources$Theme0.applyStyle(style.Theme_AppCompat_CompactMenu, true);
            }
            else {
                resources$Theme0.applyStyle(typedValue0.resourceId, true);
            }
            ContextThemeWrapper contextThemeWrapper0 = new ContextThemeWrapper(context0, 0);
            contextThemeWrapper0.getTheme().setTo(resources$Theme0);
            this.listPresenterContext = contextThemeWrapper0;
            TypedArray typedArray0 = contextThemeWrapper0.obtainStyledAttributes(styleable.AppCompatTheme);
            this.background = typedArray0.getResourceId(styleable.AppCompatTheme_panelBackground, 0);
            this.windowAnimations = typedArray0.getResourceId(styleable.AppCompatTheme_android_windowAnimationStyle, 0);
            typedArray0.recycle();
        }
    }

    final class PanelMenuPresenterCallback implements androidx.appcompat.view.menu.MenuPresenter.Callback {
        @Override  // androidx.appcompat.view.menu.MenuPresenter$Callback
        public void onCloseMenu(MenuBuilder menuBuilder0, boolean z) {
            MenuBuilder menuBuilder1 = menuBuilder0.getRootMenu();
            boolean z1 = menuBuilder1 != menuBuilder0;
            AppCompatDelegateImpl appCompatDelegateImpl0 = AppCompatDelegateImpl.this;
            if(z1) {
                menuBuilder0 = menuBuilder1;
            }
            PanelFeatureState appCompatDelegateImpl$PanelFeatureState0 = appCompatDelegateImpl0.findMenuPanel(menuBuilder0);
            if(appCompatDelegateImpl$PanelFeatureState0 != null) {
                if(z1) {
                    AppCompatDelegateImpl.this.callOnPanelClosed(appCompatDelegateImpl$PanelFeatureState0.featureId, appCompatDelegateImpl$PanelFeatureState0, menuBuilder1);
                    AppCompatDelegateImpl.this.closePanel(appCompatDelegateImpl$PanelFeatureState0, true);
                    return;
                }
                AppCompatDelegateImpl.this.closePanel(appCompatDelegateImpl$PanelFeatureState0, z);
            }
        }

        @Override  // androidx.appcompat.view.menu.MenuPresenter$Callback
        public boolean onOpenSubMenu(MenuBuilder menuBuilder0) {
            if(menuBuilder0 == menuBuilder0.getRootMenu() && AppCompatDelegateImpl.this.mHasActionBar) {
                Window.Callback window$Callback0 = AppCompatDelegateImpl.this.getWindowCallback();
                if(window$Callback0 != null && !AppCompatDelegateImpl.this.mDestroyed) {
                    window$Callback0.onMenuOpened(108, menuBuilder0);
                }
            }
            return true;
        }
    }

    static final String EXCEPTION_HANDLER_MESSAGE_SUFFIX = ". If the resource you are trying to use is a vector resource, you may be referencing it in an unsupported way. See AppCompatDelegate.setCompatVectorFromResourcesEnabled() for more info.";
    private static final boolean IS_PRE_LOLLIPOP;
    ActionBar mActionBar;
    private ActionMenuPresenterCallback mActionMenuPresenterCallback;
    ActionMode mActionMode;
    PopupWindow mActionModePopup;
    ActionBarContextView mActionModeView;
    private int mActivityHandlesConfigFlags;
    private boolean mActivityHandlesConfigFlagsChecked;
    final AppCompatCallback mAppCompatCallback;
    private AppCompatViewInflater mAppCompatViewInflater;
    private AppCompatWindowCallback mAppCompatWindowCallback;
    private AutoNightModeManager mAutoBatteryNightModeManager;
    private AutoNightModeManager mAutoTimeNightModeManager;
    private OnBackInvokedCallback mBackCallback;
    private boolean mBaseContextAttached;
    private boolean mClosingActionMenu;
    final Context mContext;
    private boolean mCreated;
    private DecorContentParent mDecorContentParent;
    boolean mDestroyed;
    private OnBackInvokedDispatcher mDispatcher;
    private Configuration mEffectiveConfiguration;
    private boolean mEnableDefaultActionBarUp;
    ViewPropertyAnimatorCompat mFadeAnim;
    private boolean mFeatureIndeterminateProgress;
    private boolean mFeatureProgress;
    private boolean mHandleNativeActionModes;
    boolean mHasActionBar;
    final Object mHost;
    int mInvalidatePanelMenuFeatures;
    boolean mInvalidatePanelMenuPosted;
    private final Runnable mInvalidatePanelMenuRunnable;
    boolean mIsFloating;
    private LayoutIncludeDetector mLayoutIncludeDetector;
    private int mLocalNightMode;
    private boolean mLongPressBackDown;
    MenuInflater mMenuInflater;
    boolean mOverlayActionBar;
    boolean mOverlayActionMode;
    private PanelMenuPresenterCallback mPanelMenuPresenterCallback;
    private PanelFeatureState[] mPanels;
    private PanelFeatureState mPreparedPanel;
    Runnable mShowActionModePopup;
    private View mStatusGuard;
    ViewGroup mSubDecor;
    private boolean mSubDecorInstalled;
    private Rect mTempRect1;
    private Rect mTempRect2;
    private int mThemeResId;
    private CharSequence mTitle;
    private TextView mTitleView;
    Window mWindow;
    boolean mWindowNoTitle;
    private static final boolean sCanReturnDifferentContext;
    private static boolean sInstalledExceptionHandler;
    private static final SimpleArrayMap sLocalNightModes;
    private static final int[] sWindowBackgroundStyleable;

    static {
        AppCompatDelegateImpl.sLocalNightModes = new SimpleArrayMap();
        AppCompatDelegateImpl.IS_PRE_LOLLIPOP = false;
        AppCompatDelegateImpl.sWindowBackgroundStyleable = new int[]{0x1010054};
        AppCompatDelegateImpl.sCanReturnDifferentContext = true;
    }

    AppCompatDelegateImpl(Activity activity0, AppCompatCallback appCompatCallback0) {
        this(activity0, null, appCompatCallback0, activity0);
    }

    AppCompatDelegateImpl(Dialog dialog0, AppCompatCallback appCompatCallback0) {
        this(dialog0.getContext(), dialog0.getWindow(), appCompatCallback0, dialog0);
    }

    AppCompatDelegateImpl(Context context0, Activity activity0, AppCompatCallback appCompatCallback0) {
        this(context0, null, appCompatCallback0, activity0);
    }

    AppCompatDelegateImpl(Context context0, Window window0, AppCompatCallback appCompatCallback0) {
        this(context0, window0, appCompatCallback0, context0);
    }

    private AppCompatDelegateImpl(Context context0, Window window0, AppCompatCallback appCompatCallback0, Object object0) {
        this.mFadeAnim = null;
        this.mHandleNativeActionModes = true;
        this.mLocalNightMode = -100;
        this.mInvalidatePanelMenuRunnable = new Runnable() {
            @Override
            public void run() {
                if((AppCompatDelegateImpl.this.mInvalidatePanelMenuFeatures & 1) != 0) {
                    AppCompatDelegateImpl.this.doInvalidatePanelMenu(0);
                }
                if((AppCompatDelegateImpl.this.mInvalidatePanelMenuFeatures & 0x1000) != 0) {
                    AppCompatDelegateImpl.this.doInvalidatePanelMenu(108);
                }
                AppCompatDelegateImpl.this.mInvalidatePanelMenuPosted = false;
                AppCompatDelegateImpl.this.mInvalidatePanelMenuFeatures = 0;
            }
        };
        this.mContext = context0;
        this.mAppCompatCallback = appCompatCallback0;
        this.mHost = object0;
        if(this.mLocalNightMode == -100 && object0 instanceof Dialog) {
            AppCompatActivity appCompatActivity0 = this.tryUnwrapContext();
            if(appCompatActivity0 != null) {
                this.mLocalNightMode = appCompatActivity0.getDelegate().getLocalNightMode();
            }
        }
        if(this.mLocalNightMode == -100) {
            SimpleArrayMap simpleArrayMap0 = AppCompatDelegateImpl.sLocalNightModes;
            Integer integer0 = (Integer)simpleArrayMap0.get(object0.getClass().getName());
            if(integer0 != null) {
                this.mLocalNightMode = (int)integer0;
                simpleArrayMap0.remove(object0.getClass().getName());
            }
        }
        if(window0 != null) {
            this.attachToWindow(window0);
        }
        AppCompatDrawableManager.preload();
    }

    @Override  // androidx.appcompat.app.AppCompatDelegate
    public void addContentView(View view0, ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        this.ensureSubDecor();
        ((ViewGroup)this.mSubDecor.findViewById(0x1020002)).addView(view0, viewGroup$LayoutParams0);
        this.mAppCompatWindowCallback.bypassOnContentChanged(this.mWindow.getCallback());
    }

    @Override  // androidx.appcompat.app.AppCompatDelegate
    boolean applyAppLocales() {
        if(AppCompatDelegateImpl.isAutoStorageOptedIn(this.mContext) && AppCompatDelegateImpl.getRequestedAppLocales() != null && !AppCompatDelegateImpl.getRequestedAppLocales().equals(AppCompatDelegateImpl.getStoredAppLocales())) {
            this.asyncExecuteSyncRequestedAndStoredLocales(this.mContext);
        }
        return this.applyApplicationSpecificConfig(true);
    }

    private boolean applyApplicationSpecificConfig(boolean z) {
        return this.applyApplicationSpecificConfig(z, true);
    }

    private boolean applyApplicationSpecificConfig(boolean z, boolean z1) {
        if(this.mDestroyed) {
            return false;
        }
        int v = this.calculateNightMode();
        int v1 = this.mapNightMode(this.mContext, v);
        LocaleListCompat localeListCompat0 = Build.VERSION.SDK_INT >= 33 ? null : this.calculateApplicationLocales(this.mContext);
        if(!z1 && localeListCompat0 != null) {
            localeListCompat0 = this.getConfigurationLocales(this.mContext.getResources().getConfiguration());
        }
        boolean z2 = this.updateAppConfiguration(v1, localeListCompat0, z);
        if(v == 0) {
            this.getAutoTimeNightModeManager(this.mContext).setup();
        }
        else {
            AutoNightModeManager appCompatDelegateImpl$AutoNightModeManager0 = this.mAutoTimeNightModeManager;
            if(appCompatDelegateImpl$AutoNightModeManager0 != null) {
                appCompatDelegateImpl$AutoNightModeManager0.cleanup();
            }
        }
        if(v == 3) {
            this.getAutoBatteryNightModeManager(this.mContext).setup();
            return z2;
        }
        AutoNightModeManager appCompatDelegateImpl$AutoNightModeManager1 = this.mAutoBatteryNightModeManager;
        if(appCompatDelegateImpl$AutoNightModeManager1 != null) {
            appCompatDelegateImpl$AutoNightModeManager1.cleanup();
        }
        return z2;
    }

    @Override  // androidx.appcompat.app.AppCompatDelegate
    public boolean applyDayNight() {
        return this.applyApplicationSpecificConfig(true);
    }

    private void applyFixedSizeWindow() {
        ContentFrameLayout contentFrameLayout0 = (ContentFrameLayout)this.mSubDecor.findViewById(0x1020002);
        View view0 = this.mWindow.getDecorView();
        contentFrameLayout0.setDecorPadding(view0.getPaddingLeft(), view0.getPaddingTop(), view0.getPaddingRight(), view0.getPaddingBottom());
        TypedArray typedArray0 = this.mContext.obtainStyledAttributes(styleable.AppCompatTheme);
        TypedValue typedValue0 = contentFrameLayout0.getMinWidthMajor();
        typedArray0.getValue(styleable.AppCompatTheme_windowMinWidthMajor, typedValue0);
        TypedValue typedValue1 = contentFrameLayout0.getMinWidthMinor();
        typedArray0.getValue(styleable.AppCompatTheme_windowMinWidthMinor, typedValue1);
        if(typedArray0.hasValue(styleable.AppCompatTheme_windowFixedWidthMajor)) {
            TypedValue typedValue2 = contentFrameLayout0.getFixedWidthMajor();
            typedArray0.getValue(styleable.AppCompatTheme_windowFixedWidthMajor, typedValue2);
        }
        if(typedArray0.hasValue(styleable.AppCompatTheme_windowFixedWidthMinor)) {
            TypedValue typedValue3 = contentFrameLayout0.getFixedWidthMinor();
            typedArray0.getValue(styleable.AppCompatTheme_windowFixedWidthMinor, typedValue3);
        }
        if(typedArray0.hasValue(styleable.AppCompatTheme_windowFixedHeightMajor)) {
            TypedValue typedValue4 = contentFrameLayout0.getFixedHeightMajor();
            typedArray0.getValue(styleable.AppCompatTheme_windowFixedHeightMajor, typedValue4);
        }
        if(typedArray0.hasValue(styleable.AppCompatTheme_windowFixedHeightMinor)) {
            TypedValue typedValue5 = contentFrameLayout0.getFixedHeightMinor();
            typedArray0.getValue(styleable.AppCompatTheme_windowFixedHeightMinor, typedValue5);
        }
        typedArray0.recycle();
        contentFrameLayout0.requestLayout();
    }

    @Override  // androidx.appcompat.app.AppCompatDelegate
    public Context attachBaseContext2(Context context0) {
        this.mBaseContextAttached = true;
        int v = this.mapNightMode(context0, this.calculateNightMode());
        if(AppCompatDelegateImpl.isAutoStorageOptedIn(context0)) {
            AppCompatDelegateImpl.syncRequestedAndStoredLocales(context0);
        }
        LocaleListCompat localeListCompat0 = this.calculateApplicationLocales(context0);
        if(context0 instanceof android.view.ContextThemeWrapper) {
            Configuration configuration0 = this.createOverrideAppConfiguration(context0, v, localeListCompat0, null, false);
            try {
                ((android.view.ContextThemeWrapper)context0).applyOverrideConfiguration(configuration0);
                return context0;
            }
            catch(IllegalStateException unused_ex) {
            }
        }
        if(context0 instanceof ContextThemeWrapper) {
            Configuration configuration1 = this.createOverrideAppConfiguration(context0, v, localeListCompat0, null, false);
            try {
                ((ContextThemeWrapper)context0).applyOverrideConfiguration(configuration1);
                return context0;
            }
            catch(IllegalStateException unused_ex) {
            }
        }
        if(!AppCompatDelegateImpl.sCanReturnDifferentContext) {
            return super.attachBaseContext2(context0);
        }
        Configuration configuration2 = new Configuration();
        configuration2.uiMode = -1;
        configuration2.fontScale = 0.0f;
        Configuration configuration3 = context0.createConfigurationContext(configuration2).getResources().getConfiguration();
        Configuration configuration4 = context0.getResources().getConfiguration();
        configuration3.uiMode = configuration4.uiMode;
        Configuration configuration5 = this.createOverrideAppConfiguration(context0, v, localeListCompat0, (configuration3.equals(configuration4) ? null : AppCompatDelegateImpl.generateConfigDelta(configuration3, configuration4)), true);
        ContextThemeWrapper contextThemeWrapper0 = new ContextThemeWrapper(context0, style.Theme_AppCompat_Empty);
        contextThemeWrapper0.applyOverrideConfiguration(configuration5);
        try {
            if(context0.getTheme() != null) {
                goto label_25;
            }
        }
        catch(NullPointerException unused_ex) {
        }
        return super.attachBaseContext2(contextThemeWrapper0);
    label_25:
        ThemeCompat.rebase(contextThemeWrapper0.getTheme());
        return super.attachBaseContext2(contextThemeWrapper0);
    }

    private void attachToWindow(Window window0) {
        if(this.mWindow != null) {
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        Window.Callback window$Callback0 = window0.getCallback();
        if(window$Callback0 instanceof AppCompatWindowCallback) {
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        }
        AppCompatWindowCallback appCompatDelegateImpl$AppCompatWindowCallback0 = new AppCompatWindowCallback(this, window$Callback0);
        this.mAppCompatWindowCallback = appCompatDelegateImpl$AppCompatWindowCallback0;
        window0.setCallback(appCompatDelegateImpl$AppCompatWindowCallback0);
        TintTypedArray tintTypedArray0 = TintTypedArray.obtainStyledAttributes(this.mContext, null, AppCompatDelegateImpl.sWindowBackgroundStyleable);
        Drawable drawable0 = tintTypedArray0.getDrawableIfKnown(0);
        if(drawable0 != null) {
            window0.setBackgroundDrawable(drawable0);
        }
        tintTypedArray0.recycle();
        this.mWindow = window0;
        if(Build.VERSION.SDK_INT >= 33 && this.mDispatcher == null) {
            this.setOnBackInvokedDispatcher(null);
        }
    }

    LocaleListCompat calculateApplicationLocales(Context context0) {
        LocaleListCompat localeListCompat2;
        if(Build.VERSION.SDK_INT >= 33) {
            return null;
        }
        LocaleListCompat localeListCompat0 = AppCompatDelegateImpl.getRequestedAppLocales();
        if(localeListCompat0 == null) {
            return null;
        }
        LocaleListCompat localeListCompat1 = this.getConfigurationLocales(context0.getApplicationContext().getResources().getConfiguration());
        if(Build.VERSION.SDK_INT >= 24) {
            localeListCompat2 = LocaleOverlayHelper.combineLocalesIfOverlayExists(localeListCompat0, localeListCompat1);
            return localeListCompat2.isEmpty() ? localeListCompat1 : localeListCompat2;
        }
        localeListCompat2 = localeListCompat0.isEmpty() ? LocaleListCompat.getEmptyLocaleList() : LocaleListCompat.forLanguageTags(Api21Impl.toLanguageTag(localeListCompat0.get(0)));
        return localeListCompat2.isEmpty() ? localeListCompat1 : localeListCompat2;
    }

    private int calculateNightMode() {
        int v = this.mLocalNightMode;
        return v == -100 ? AppCompatDelegateImpl.getDefaultNightMode() : v;
    }

    void callOnPanelClosed(int v, PanelFeatureState appCompatDelegateImpl$PanelFeatureState0, Menu menu0) {
        if(menu0 == null) {
            if(appCompatDelegateImpl$PanelFeatureState0 == null && v >= 0) {
                PanelFeatureState[] arr_appCompatDelegateImpl$PanelFeatureState = this.mPanels;
                if(v < arr_appCompatDelegateImpl$PanelFeatureState.length) {
                    appCompatDelegateImpl$PanelFeatureState0 = arr_appCompatDelegateImpl$PanelFeatureState[v];
                }
            }
            if(appCompatDelegateImpl$PanelFeatureState0 != null) {
                menu0 = appCompatDelegateImpl$PanelFeatureState0.menu;
            }
        }
        if((appCompatDelegateImpl$PanelFeatureState0 == null || appCompatDelegateImpl$PanelFeatureState0.isOpen) && !this.mDestroyed) {
            this.mAppCompatWindowCallback.bypassOnPanelClosed(this.mWindow.getCallback(), v, menu0);
        }
    }

    void checkCloseActionMenu(MenuBuilder menuBuilder0) {
        if(this.mClosingActionMenu) {
            return;
        }
        this.mClosingActionMenu = true;
        this.mDecorContentParent.dismissPopups();
        Window.Callback window$Callback0 = this.getWindowCallback();
        if(window$Callback0 != null && !this.mDestroyed) {
            window$Callback0.onPanelClosed(108, menuBuilder0);
        }
        this.mClosingActionMenu = false;
    }

    private void cleanupAutoManagers() {
        AutoNightModeManager appCompatDelegateImpl$AutoNightModeManager0 = this.mAutoTimeNightModeManager;
        if(appCompatDelegateImpl$AutoNightModeManager0 != null) {
            appCompatDelegateImpl$AutoNightModeManager0.cleanup();
        }
        AutoNightModeManager appCompatDelegateImpl$AutoNightModeManager1 = this.mAutoBatteryNightModeManager;
        if(appCompatDelegateImpl$AutoNightModeManager1 != null) {
            appCompatDelegateImpl$AutoNightModeManager1.cleanup();
        }
    }

    void closePanel(int v) {
        this.closePanel(this.getPanelState(v, true), true);
    }

    void closePanel(PanelFeatureState appCompatDelegateImpl$PanelFeatureState0, boolean z) {
        if(z && appCompatDelegateImpl$PanelFeatureState0.featureId == 0 && (this.mDecorContentParent != null && this.mDecorContentParent.isOverflowMenuShowing())) {
            this.checkCloseActionMenu(appCompatDelegateImpl$PanelFeatureState0.menu);
            return;
        }
        WindowManager windowManager0 = (WindowManager)this.mContext.getSystemService("window");
        if(windowManager0 != null && appCompatDelegateImpl$PanelFeatureState0.isOpen && appCompatDelegateImpl$PanelFeatureState0.decorView != null) {
            windowManager0.removeView(appCompatDelegateImpl$PanelFeatureState0.decorView);
            if(z) {
                this.callOnPanelClosed(appCompatDelegateImpl$PanelFeatureState0.featureId, appCompatDelegateImpl$PanelFeatureState0, null);
            }
        }
        appCompatDelegateImpl$PanelFeatureState0.isPrepared = false;
        appCompatDelegateImpl$PanelFeatureState0.isHandled = false;
        appCompatDelegateImpl$PanelFeatureState0.isOpen = false;
        appCompatDelegateImpl$PanelFeatureState0.shownPanelView = null;
        appCompatDelegateImpl$PanelFeatureState0.refreshDecorView = true;
        if(this.mPreparedPanel == appCompatDelegateImpl$PanelFeatureState0) {
            this.mPreparedPanel = null;
        }
        if(appCompatDelegateImpl$PanelFeatureState0.featureId == 0) {
            this.updateBackInvokedCallbackState();
        }
    }

    private Configuration createOverrideAppConfiguration(Context context0, int v, LocaleListCompat localeListCompat0, Configuration configuration0, boolean z) {
        int v1;
        switch(v) {
            case 1: {
                v1 = 16;
                break;
            }
            case 2: {
                v1 = 0x20;
                break;
            }
            default: {
                v1 = z ? 0 : context0.getApplicationContext().getResources().getConfiguration().uiMode & 0x30;
            }
        }
        Configuration configuration1 = new Configuration();
        configuration1.fontScale = 0.0f;
        if(configuration0 != null) {
            configuration1.setTo(configuration0);
        }
        configuration1.uiMode = v1 | configuration1.uiMode & -49;
        if(localeListCompat0 != null) {
            this.setConfigurationLocales(configuration1, localeListCompat0);
        }
        return configuration1;
    }

    private ViewGroup createSubDecor() {
        ViewGroup viewGroup0;
        TypedArray typedArray0 = this.mContext.obtainStyledAttributes(styleable.AppCompatTheme);
        if(typedArray0.hasValue(styleable.AppCompatTheme_windowActionBar)) {
            if(typedArray0.getBoolean(styleable.AppCompatTheme_windowNoTitle, false)) {
                this.requestWindowFeature(1);
            }
            else if(typedArray0.getBoolean(styleable.AppCompatTheme_windowActionBar, false)) {
                this.requestWindowFeature(108);
            }
            if(typedArray0.getBoolean(styleable.AppCompatTheme_windowActionBarOverlay, false)) {
                this.requestWindowFeature(109);
            }
            if(typedArray0.getBoolean(styleable.AppCompatTheme_windowActionModeOverlay, false)) {
                this.requestWindowFeature(10);
            }
            this.mIsFloating = typedArray0.getBoolean(styleable.AppCompatTheme_android_windowIsFloating, false);
            typedArray0.recycle();
            this.ensureWindow();
            this.mWindow.getDecorView();
            LayoutInflater layoutInflater0 = LayoutInflater.from(this.mContext);
            if(this.mWindowNoTitle) {
                viewGroup0 = this.mOverlayActionMode ? ((ViewGroup)layoutInflater0.inflate(layout.abc_screen_simple_overlay_action_mode, null)) : ((ViewGroup)layoutInflater0.inflate(layout.abc_screen_simple, null));
            }
            else if(this.mIsFloating) {
                viewGroup0 = (ViewGroup)layoutInflater0.inflate(layout.abc_dialog_title_material, null);
                this.mOverlayActionBar = false;
                this.mHasActionBar = false;
            }
            else if(this.mHasActionBar) {
                TypedValue typedValue0 = new TypedValue();
                this.mContext.getTheme().resolveAttribute(attr.actionBarTheme, typedValue0, true);
                Context context0 = typedValue0.resourceId == 0 ? this.mContext : new ContextThemeWrapper(this.mContext, typedValue0.resourceId);
                viewGroup0 = (ViewGroup)LayoutInflater.from(context0).inflate(layout.abc_screen_toolbar, null);
                DecorContentParent decorContentParent0 = (DecorContentParent)viewGroup0.findViewById(id.decor_content_parent);
                this.mDecorContentParent = decorContentParent0;
                decorContentParent0.setWindowCallback(this.getWindowCallback());
                if(this.mOverlayActionBar) {
                    this.mDecorContentParent.initFeature(109);
                }
                if(this.mFeatureProgress) {
                    this.mDecorContentParent.initFeature(2);
                }
                if(this.mFeatureIndeterminateProgress) {
                    this.mDecorContentParent.initFeature(5);
                }
            }
            else {
                viewGroup0 = null;
            }
            if(viewGroup0 == null) {
                throw new IllegalArgumentException("AppCompat does not support the current theme features: { windowActionBar: " + this.mHasActionBar + ", windowActionBarOverlay: " + this.mOverlayActionBar + ", android:windowIsFloating: " + this.mIsFloating + ", windowActionModeOverlay: " + this.mOverlayActionMode + ", windowNoTitle: " + this.mWindowNoTitle + " }");
            }
            ViewCompat.setOnApplyWindowInsetsListener(viewGroup0, new OnApplyWindowInsetsListener() {
                @Override  // androidx.core.view.OnApplyWindowInsetsListener
                public WindowInsetsCompat onApplyWindowInsets(View view0, WindowInsetsCompat windowInsetsCompat0) {
                    int v = windowInsetsCompat0.getSystemWindowInsetTop();
                    int v1 = AppCompatDelegateImpl.this.updateStatusGuard(windowInsetsCompat0, null);
                    if(v != v1) {
                        windowInsetsCompat0 = windowInsetsCompat0.replaceSystemWindowInsets(windowInsetsCompat0.getSystemWindowInsetLeft(), v1, windowInsetsCompat0.getSystemWindowInsetRight(), windowInsetsCompat0.getSystemWindowInsetBottom());
                    }
                    return ViewCompat.onApplyWindowInsets(view0, windowInsetsCompat0);
                }
            });
            if(this.mDecorContentParent == null) {
                this.mTitleView = (TextView)viewGroup0.findViewById(id.title);
            }
            ViewUtils.makeOptionalFitsSystemWindows(viewGroup0);
            ContentFrameLayout contentFrameLayout0 = (ContentFrameLayout)viewGroup0.findViewById(id.action_bar_activity_content);
            ViewGroup viewGroup1 = (ViewGroup)this.mWindow.findViewById(0x1020002);
            if(viewGroup1 != null) {
                while(viewGroup1.getChildCount() > 0) {
                    View view0 = viewGroup1.getChildAt(0);
                    viewGroup1.removeViewAt(0);
                    contentFrameLayout0.addView(view0);
                }
                viewGroup1.setId(-1);
                contentFrameLayout0.setId(0x1020002);
                if(viewGroup1 instanceof FrameLayout) {
                    ((FrameLayout)viewGroup1).setForeground(null);
                }
            }
            this.mWindow.setContentView(viewGroup0);
            contentFrameLayout0.setAttachListener(new OnAttachListener() {
                @Override  // androidx.appcompat.widget.ContentFrameLayout$OnAttachListener
                public void onAttachedFromWindow() {
                }

                @Override  // androidx.appcompat.widget.ContentFrameLayout$OnAttachListener
                public void onDetachedFromWindow() {
                    AppCompatDelegateImpl.this.dismissPopups();
                }
            });
            return viewGroup0;
        }
        typedArray0.recycle();
        throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");

        class androidx.appcompat.app.AppCompatDelegateImpl.4 implements OnFitSystemWindowsListener {
            @Override  // androidx.appcompat.widget.FitWindowsViewGroup$OnFitSystemWindowsListener
            public void onFitSystemWindows(Rect rect0) {
                rect0.top = AppCompatDelegateImpl.this.updateStatusGuard(null, rect0);
            }
        }

    }

    @Override  // androidx.appcompat.app.AppCompatDelegate
    public View createView(View view0, String s, Context context0, AttributeSet attributeSet0) {
        if(this.mAppCompatViewInflater == null) {
            TypedArray typedArray0 = this.mContext.obtainStyledAttributes(styleable.AppCompatTheme);
            String s1 = typedArray0.getString(styleable.AppCompatTheme_viewInflaterClass);
            typedArray0.recycle();
            if(s1 == null) {
                this.mAppCompatViewInflater = new AppCompatViewInflater();
            }
            else {
                try {
                    this.mAppCompatViewInflater = (AppCompatViewInflater)this.mContext.getClassLoader().loadClass(s1).getDeclaredConstructor(null).newInstance(null);
                }
                catch(Throwable throwable0) {
                    Log.i("AppCompatDelegate", "Failed to instantiate custom view inflater " + s1 + ". Falling back to default.", throwable0);
                    this.mAppCompatViewInflater = new AppCompatViewInflater();
                }
            }
        }
        if(AppCompatDelegateImpl.IS_PRE_LOLLIPOP) {
            if(this.mLayoutIncludeDetector == null) {
                this.mLayoutIncludeDetector = new LayoutIncludeDetector();
            }
            if(this.mLayoutIncludeDetector.detect(attributeSet0)) {
                return this.mAppCompatViewInflater.createView(view0, s, context0, attributeSet0, true, true, true, false);
            }
            if(attributeSet0 instanceof XmlPullParser) {
                return ((XmlPullParser)attributeSet0).getDepth() <= 1 ? this.mAppCompatViewInflater.createView(view0, s, context0, attributeSet0, false, true, true, false) : this.mAppCompatViewInflater.createView(view0, s, context0, attributeSet0, true, true, true, false);
            }
            boolean z = this.shouldInheritContext(((ViewParent)view0));
            return this.mAppCompatViewInflater.createView(view0, s, context0, attributeSet0, z, true, true, false);
        }
        return this.mAppCompatViewInflater.createView(view0, s, context0, attributeSet0, false, false, true, false);
    }

    void dismissPopups() {
        DecorContentParent decorContentParent0 = this.mDecorContentParent;
        if(decorContentParent0 != null) {
            decorContentParent0.dismissPopups();
        }
        if(this.mActionModePopup != null) {
            this.mWindow.getDecorView().removeCallbacks(this.mShowActionModePopup);
            if(this.mActionModePopup.isShowing()) {
                try {
                    this.mActionModePopup.dismiss();
                }
                catch(IllegalArgumentException unused_ex) {
                }
            }
            this.mActionModePopup = null;
        }
        this.endOnGoingFadeAnimation();
        PanelFeatureState appCompatDelegateImpl$PanelFeatureState0 = this.getPanelState(0, false);
        if(appCompatDelegateImpl$PanelFeatureState0 != null && appCompatDelegateImpl$PanelFeatureState0.menu != null) {
            appCompatDelegateImpl$PanelFeatureState0.menu.close();
        }
    }

    boolean dispatchKeyEvent(KeyEvent keyEvent0) {
        if(this.mHost instanceof Component || this.mHost instanceof AppCompatDialog) {
            View view0 = this.mWindow.getDecorView();
            if(view0 != null && KeyEventDispatcher.dispatchBeforeHierarchy(view0, keyEvent0)) {
                return true;
            }
        }
        if(keyEvent0.getKeyCode() == 82 && this.mAppCompatWindowCallback.bypassDispatchKeyEvent(this.mWindow.getCallback(), keyEvent0)) {
            return true;
        }
        int v = keyEvent0.getKeyCode();
        return keyEvent0.getAction() == 0 ? this.onKeyDown(v, keyEvent0) : this.onKeyUp(v, keyEvent0);
    }

    void doInvalidatePanelMenu(int v) {
        PanelFeatureState appCompatDelegateImpl$PanelFeatureState0 = this.getPanelState(v, true);
        if(appCompatDelegateImpl$PanelFeatureState0.menu != null) {
            Bundle bundle0 = new Bundle();
            appCompatDelegateImpl$PanelFeatureState0.menu.saveActionViewStates(bundle0);
            if(bundle0.size() > 0) {
                appCompatDelegateImpl$PanelFeatureState0.frozenActionViewState = bundle0;
            }
            appCompatDelegateImpl$PanelFeatureState0.menu.stopDispatchingItemsChanged();
            appCompatDelegateImpl$PanelFeatureState0.menu.clear();
        }
        appCompatDelegateImpl$PanelFeatureState0.refreshMenuContent = true;
        appCompatDelegateImpl$PanelFeatureState0.refreshDecorView = true;
        if((v == 0 || v == 108) && this.mDecorContentParent != null) {
            PanelFeatureState appCompatDelegateImpl$PanelFeatureState1 = this.getPanelState(0, false);
            if(appCompatDelegateImpl$PanelFeatureState1 != null) {
                appCompatDelegateImpl$PanelFeatureState1.isPrepared = false;
                this.preparePanel(appCompatDelegateImpl$PanelFeatureState1, null);
            }
        }
    }

    void endOnGoingFadeAnimation() {
        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat0 = this.mFadeAnim;
        if(viewPropertyAnimatorCompat0 != null) {
            viewPropertyAnimatorCompat0.cancel();
        }
    }

    private void ensureSubDecor() {
        if(!this.mSubDecorInstalled) {
            this.mSubDecor = this.createSubDecor();
            CharSequence charSequence0 = this.getTitle();
            if(!TextUtils.isEmpty(charSequence0)) {
                DecorContentParent decorContentParent0 = this.mDecorContentParent;
                if(decorContentParent0 != null) {
                    decorContentParent0.setWindowTitle(charSequence0);
                }
                else if(this.peekSupportActionBar() == null) {
                    TextView textView0 = this.mTitleView;
                    if(textView0 != null) {
                        textView0.setText(charSequence0);
                    }
                }
                else {
                    this.peekSupportActionBar().setWindowTitle(charSequence0);
                }
            }
            this.applyFixedSizeWindow();
            this.mSubDecorInstalled = true;
            PanelFeatureState appCompatDelegateImpl$PanelFeatureState0 = this.getPanelState(0, false);
            if(!this.mDestroyed && (appCompatDelegateImpl$PanelFeatureState0 == null || appCompatDelegateImpl$PanelFeatureState0.menu == null)) {
                this.invalidatePanelMenu(108);
            }
        }
    }

    private void ensureWindow() {
        if(this.mWindow == null) {
            Object object0 = this.mHost;
            if(object0 instanceof Activity) {
                this.attachToWindow(((Activity)object0).getWindow());
            }
        }
        if(this.mWindow == null) {
            throw new IllegalStateException("We have not been given a Window");
        }
    }

    PanelFeatureState findMenuPanel(Menu menu0) {
        PanelFeatureState[] arr_appCompatDelegateImpl$PanelFeatureState = this.mPanels;
        int v1 = arr_appCompatDelegateImpl$PanelFeatureState == null ? 0 : arr_appCompatDelegateImpl$PanelFeatureState.length;
        for(int v = 0; v < v1; ++v) {
            PanelFeatureState appCompatDelegateImpl$PanelFeatureState0 = arr_appCompatDelegateImpl$PanelFeatureState[v];
            if(appCompatDelegateImpl$PanelFeatureState0 != null && appCompatDelegateImpl$PanelFeatureState0.menu == menu0) {
                return appCompatDelegateImpl$PanelFeatureState0;
            }
        }
        return null;
    }

    @Override  // androidx.appcompat.app.AppCompatDelegate
    public View findViewById(int v) {
        this.ensureSubDecor();
        return this.mWindow.findViewById(v);
    }

    private static Configuration generateConfigDelta(Configuration configuration0, Configuration configuration1) {
        Configuration configuration2 = new Configuration();
        configuration2.fontScale = 0.0f;
        if(configuration1 != null && configuration0.diff(configuration1) != 0) {
            if(configuration0.fontScale != configuration1.fontScale) {
                configuration2.fontScale = configuration1.fontScale;
            }
            if(configuration0.mcc != configuration1.mcc) {
                configuration2.mcc = configuration1.mcc;
            }
            if(configuration0.mnc != configuration1.mnc) {
                configuration2.mnc = configuration1.mnc;
            }
            if(Build.VERSION.SDK_INT >= 24) {
                Api24Impl.generateConfigDelta_locale(configuration0, configuration1, configuration2);
            }
            else if(!ObjectsCompat.equals(configuration0.locale, configuration1.locale)) {
                configuration2.locale = configuration1.locale;
            }
            if(configuration0.touchscreen != configuration1.touchscreen) {
                configuration2.touchscreen = configuration1.touchscreen;
            }
            if(configuration0.keyboard != configuration1.keyboard) {
                configuration2.keyboard = configuration1.keyboard;
            }
            if(configuration0.keyboardHidden != configuration1.keyboardHidden) {
                configuration2.keyboardHidden = configuration1.keyboardHidden;
            }
            if(configuration0.navigation != configuration1.navigation) {
                configuration2.navigation = configuration1.navigation;
            }
            if(configuration0.navigationHidden != configuration1.navigationHidden) {
                configuration2.navigationHidden = configuration1.navigationHidden;
            }
            if(configuration0.orientation != configuration1.orientation) {
                configuration2.orientation = configuration1.orientation;
            }
            if((configuration0.screenLayout & 15) != (configuration1.screenLayout & 15)) {
                configuration2.screenLayout |= configuration1.screenLayout & 15;
            }
            if((configuration0.screenLayout & 0xC0) != (configuration1.screenLayout & 0xC0)) {
                configuration2.screenLayout |= configuration1.screenLayout & 0xC0;
            }
            if((configuration0.screenLayout & 0x30) != (configuration1.screenLayout & 0x30)) {
                configuration2.screenLayout |= configuration1.screenLayout & 0x30;
            }
            if((configuration0.screenLayout & 0x300) != (configuration1.screenLayout & 0x300)) {
                configuration2.screenLayout |= configuration1.screenLayout & 0x300;
            }
            if(Build.VERSION.SDK_INT >= 26) {
                Api26Impl.generateConfigDelta_colorMode(configuration0, configuration1, configuration2);
            }
            if((configuration0.uiMode & 15) != (configuration1.uiMode & 15)) {
                configuration2.uiMode |= configuration1.uiMode & 15;
            }
            if((configuration0.uiMode & 0x30) != (configuration1.uiMode & 0x30)) {
                configuration2.uiMode |= configuration1.uiMode & 0x30;
            }
            if(configuration0.screenWidthDp != configuration1.screenWidthDp) {
                configuration2.screenWidthDp = configuration1.screenWidthDp;
            }
            if(configuration0.screenHeightDp != configuration1.screenHeightDp) {
                configuration2.screenHeightDp = configuration1.screenHeightDp;
            }
            if(configuration0.smallestScreenWidthDp != configuration1.smallestScreenWidthDp) {
                configuration2.smallestScreenWidthDp = configuration1.smallestScreenWidthDp;
            }
            if(configuration0.densityDpi != configuration1.densityDpi) {
                configuration2.densityDpi = configuration1.densityDpi;
            }
        }
        return configuration2;
    }

    final Context getActionBarThemedContext() {
        ActionBar actionBar0 = this.getSupportActionBar();
        Context context0 = actionBar0 == null ? null : actionBar0.getThemedContext();
        return context0 == null ? this.mContext : context0;
    }

    private int getActivityHandlesConfigChangesFlags(Context context0) {
        int v;
        if(!this.mActivityHandlesConfigFlagsChecked && this.mHost instanceof Activity) {
            PackageManager packageManager0 = context0.getPackageManager();
            if(packageManager0 == null) {
                return 0;
            }
            try {
                if(Build.VERSION.SDK_INT >= 29) {
                    v = 0x100C0000;
                }
                else {
                    v = Build.VERSION.SDK_INT < 24 ? 0 : 0xC0000;
                }
                ActivityInfo activityInfo0 = packageManager0.getActivityInfo(new ComponentName(context0, this.mHost.getClass()), v);
                if(activityInfo0 != null) {
                    this.mActivityHandlesConfigFlags = activityInfo0.configChanges;
                }
            }
            catch(PackageManager.NameNotFoundException packageManager$NameNotFoundException0) {
                Log.d("AppCompatDelegate", "Exception while getting ActivityInfo", packageManager$NameNotFoundException0);
                this.mActivityHandlesConfigFlags = 0;
            }
        }
        this.mActivityHandlesConfigFlagsChecked = true;
        return this.mActivityHandlesConfigFlags;
    }

    private AutoNightModeManager getAutoBatteryNightModeManager(Context context0) {
        if(this.mAutoBatteryNightModeManager == null) {
            this.mAutoBatteryNightModeManager = new AutoBatteryNightModeManager(this, context0);
        }
        return this.mAutoBatteryNightModeManager;
    }

    private AutoNightModeManager getAutoTimeNightModeManager(Context context0) {
        if(this.mAutoTimeNightModeManager == null) {
            this.mAutoTimeNightModeManager = new AutoTimeNightModeManager(this, TwilightManager.getInstance(context0));
        }
        return this.mAutoTimeNightModeManager;
    }

    final AutoNightModeManager getAutoTimeNightModeManager() {
        return this.getAutoTimeNightModeManager(this.mContext);
    }

    LocaleListCompat getConfigurationLocales(Configuration configuration0) {
        return Build.VERSION.SDK_INT < 24 ? LocaleListCompat.forLanguageTags(Api21Impl.toLanguageTag(configuration0.locale)) : Api24Impl.getLocales(configuration0);
    }

    @Override  // androidx.appcompat.app.AppCompatDelegate
    public Context getContextForDelegate() {
        return this.mContext;
    }

    @Override  // androidx.appcompat.app.AppCompatDelegate
    public final Delegate getDrawerToggleDelegate() {
        return new ActionBarDrawableToggleImpl(this);
    }

    @Override  // androidx.appcompat.app.AppCompatDelegate
    public int getLocalNightMode() {
        return this.mLocalNightMode;
    }

    @Override  // androidx.appcompat.app.AppCompatDelegate
    public MenuInflater getMenuInflater() {
        if(this.mMenuInflater == null) {
            this.initWindowDecorActionBar();
            this.mMenuInflater = new SupportMenuInflater((this.mActionBar == null ? this.mContext : this.mActionBar.getThemedContext()));
        }
        return this.mMenuInflater;
    }

    protected PanelFeatureState getPanelState(int v, boolean z) {
        PanelFeatureState[] arr_appCompatDelegateImpl$PanelFeatureState = this.mPanels;
        if(arr_appCompatDelegateImpl$PanelFeatureState == null || arr_appCompatDelegateImpl$PanelFeatureState.length <= v) {
            PanelFeatureState[] arr_appCompatDelegateImpl$PanelFeatureState1 = new PanelFeatureState[v + 1];
            if(arr_appCompatDelegateImpl$PanelFeatureState != null) {
                System.arraycopy(arr_appCompatDelegateImpl$PanelFeatureState, 0, arr_appCompatDelegateImpl$PanelFeatureState1, 0, arr_appCompatDelegateImpl$PanelFeatureState.length);
            }
            this.mPanels = arr_appCompatDelegateImpl$PanelFeatureState1;
            arr_appCompatDelegateImpl$PanelFeatureState = arr_appCompatDelegateImpl$PanelFeatureState1;
        }
        PanelFeatureState appCompatDelegateImpl$PanelFeatureState0 = arr_appCompatDelegateImpl$PanelFeatureState[v];
        if(appCompatDelegateImpl$PanelFeatureState0 == null) {
            appCompatDelegateImpl$PanelFeatureState0 = new PanelFeatureState(v);
            arr_appCompatDelegateImpl$PanelFeatureState[v] = appCompatDelegateImpl$PanelFeatureState0;
        }
        return appCompatDelegateImpl$PanelFeatureState0;
    }

    ViewGroup getSubDecor() {
        return this.mSubDecor;
    }

    @Override  // androidx.appcompat.app.AppCompatDelegate
    public ActionBar getSupportActionBar() {
        this.initWindowDecorActionBar();
        return this.mActionBar;
    }

    // 去混淆评级： 低(20)
    final CharSequence getTitle() {
        return this.mHost instanceof Activity ? ((Activity)this.mHost).getTitle() : this.mTitle;
    }

    final Window.Callback getWindowCallback() {
        return this.mWindow.getCallback();
    }

    @Override  // androidx.appcompat.app.AppCompatDelegate
    public boolean hasWindowFeature(int v) {
        switch(this.sanitizeWindowFeatureId(v)) {
            case 1: {
                return this.mWindowNoTitle || this.mWindow.hasFeature(v);
            }
            case 2: {
                return this.mFeatureProgress || this.mWindow.hasFeature(v);
            }
            case 5: {
                return this.mFeatureIndeterminateProgress || this.mWindow.hasFeature(v);
            }
            case 10: {
                return this.mOverlayActionMode || this.mWindow.hasFeature(v);
            }
            case 108: {
                return this.mHasActionBar || this.mWindow.hasFeature(v);
            }
            case 109: {
                return this.mOverlayActionBar || this.mWindow.hasFeature(v);
            }
            default: {
                return this.mWindow.hasFeature(v);
            }
        }
    }

    private void initWindowDecorActionBar() {
        this.ensureSubDecor();
        if(this.mHasActionBar && this.mActionBar == null) {
            Object object0 = this.mHost;
            if(object0 instanceof Activity) {
                this.mActionBar = new WindowDecorActionBar(((Activity)this.mHost), this.mOverlayActionBar);
            }
            else if(object0 instanceof Dialog) {
                this.mActionBar = new WindowDecorActionBar(((Dialog)this.mHost));
            }
            ActionBar actionBar0 = this.mActionBar;
            if(actionBar0 != null) {
                actionBar0.setDefaultDisplayHomeAsUpEnabled(this.mEnableDefaultActionBarUp);
            }
        }
    }

    private boolean initializePanelContent(PanelFeatureState appCompatDelegateImpl$PanelFeatureState0) {
        if(appCompatDelegateImpl$PanelFeatureState0.createdPanelView != null) {
            appCompatDelegateImpl$PanelFeatureState0.shownPanelView = appCompatDelegateImpl$PanelFeatureState0.createdPanelView;
            return true;
        }
        if(appCompatDelegateImpl$PanelFeatureState0.menu == null) {
            return false;
        }
        if(this.mPanelMenuPresenterCallback == null) {
            this.mPanelMenuPresenterCallback = new PanelMenuPresenterCallback(this);
        }
        appCompatDelegateImpl$PanelFeatureState0.shownPanelView = (View)appCompatDelegateImpl$PanelFeatureState0.getListMenuView(this.mPanelMenuPresenterCallback);
        return appCompatDelegateImpl$PanelFeatureState0.shownPanelView != null;
    }

    private boolean initializePanelDecor(PanelFeatureState appCompatDelegateImpl$PanelFeatureState0) {
        appCompatDelegateImpl$PanelFeatureState0.setStyle(this.getActionBarThemedContext());
        appCompatDelegateImpl$PanelFeatureState0.decorView = new ListMenuDecorView(this, appCompatDelegateImpl$PanelFeatureState0.listPresenterContext);
        appCompatDelegateImpl$PanelFeatureState0.gravity = 81;
        return true;
    }

    private boolean initializePanelMenu(PanelFeatureState appCompatDelegateImpl$PanelFeatureState0) {
        Resources.Theme resources$Theme1;
        Context context0 = this.mContext;
        if((appCompatDelegateImpl$PanelFeatureState0.featureId == 0 || appCompatDelegateImpl$PanelFeatureState0.featureId == 108) && this.mDecorContentParent != null) {
            TypedValue typedValue0 = new TypedValue();
            Resources.Theme resources$Theme0 = context0.getTheme();
            resources$Theme0.resolveAttribute(attr.actionBarTheme, typedValue0, true);
            if(typedValue0.resourceId == 0) {
                resources$Theme0.resolveAttribute(attr.actionBarWidgetTheme, typedValue0, true);
                resources$Theme1 = null;
            }
            else {
                resources$Theme1 = context0.getResources().newTheme();
                resources$Theme1.setTo(resources$Theme0);
                resources$Theme1.applyStyle(typedValue0.resourceId, true);
                resources$Theme1.resolveAttribute(attr.actionBarWidgetTheme, typedValue0, true);
            }
            if(typedValue0.resourceId != 0) {
                if(resources$Theme1 == null) {
                    resources$Theme1 = context0.getResources().newTheme();
                    resources$Theme1.setTo(resources$Theme0);
                }
                resources$Theme1.applyStyle(typedValue0.resourceId, true);
            }
            if(resources$Theme1 != null) {
                ContextThemeWrapper contextThemeWrapper0 = new ContextThemeWrapper(context0, 0);
                contextThemeWrapper0.getTheme().setTo(resources$Theme1);
                context0 = contextThemeWrapper0;
            }
        }
        MenuBuilder menuBuilder0 = new MenuBuilder(context0);
        menuBuilder0.setCallback(this);
        appCompatDelegateImpl$PanelFeatureState0.setMenu(menuBuilder0);
        return true;
    }

    @Override  // androidx.appcompat.app.AppCompatDelegate
    public void installViewFactory() {
        LayoutInflater layoutInflater0 = LayoutInflater.from(this.mContext);
        if(layoutInflater0.getFactory() == null) {
            LayoutInflaterCompat.setFactory2(layoutInflater0, this);
            return;
        }
        if(!(layoutInflater0.getFactory2() instanceof AppCompatDelegateImpl)) {
            Log.i("AppCompatDelegate", "The Activity\'s LayoutInflater already has a Factory installed so we can not install AppCompat\'s");
        }
    }

    @Override  // androidx.appcompat.app.AppCompatDelegate
    public void invalidateOptionsMenu() {
        if(this.peekSupportActionBar() != null && !this.getSupportActionBar().invalidateOptionsMenu()) {
            this.invalidatePanelMenu(0);
        }
    }

    private void invalidatePanelMenu(int v) {
        this.mInvalidatePanelMenuFeatures |= 1 << v;
        if(!this.mInvalidatePanelMenuPosted) {
            ViewCompat.postOnAnimation(this.mWindow.getDecorView(), this.mInvalidatePanelMenuRunnable);
            this.mInvalidatePanelMenuPosted = true;
        }
    }

    @Override  // androidx.appcompat.app.AppCompatDelegate
    public boolean isHandleNativeActionModesEnabled() {
        return this.mHandleNativeActionModes;
    }

    int mapNightMode(Context context0, int v) {
        switch(v) {
            case -100: {
                return -1;
            }
            case 0: {
                return Build.VERSION.SDK_INT < 23 || ((UiModeManager)context0.getApplicationContext().getSystemService("uimode")).getNightMode() != 0 ? this.getAutoTimeNightModeManager(context0).getApplyableNightMode() : -1;
            }
            case -1: 
            case 1: 
            case 2: {
                return v;
            }
            case 3: {
                return this.getAutoBatteryNightModeManager(context0).getApplyableNightMode();
            }
            default: {
                throw new IllegalStateException("Unknown value set for night mode. Please use one of the MODE_NIGHT values from AppCompatDelegate.");
            }
        }
    }

    boolean onBackPressed() {
        boolean z = this.mLongPressBackDown;
        this.mLongPressBackDown = false;
        PanelFeatureState appCompatDelegateImpl$PanelFeatureState0 = this.getPanelState(0, false);
        if(appCompatDelegateImpl$PanelFeatureState0 != null && appCompatDelegateImpl$PanelFeatureState0.isOpen) {
            if(!z) {
                this.closePanel(appCompatDelegateImpl$PanelFeatureState0, true);
            }
            return true;
        }
        ActionMode actionMode0 = this.mActionMode;
        if(actionMode0 != null) {
            actionMode0.finish();
            return true;
        }
        ActionBar actionBar0 = this.getSupportActionBar();
        return actionBar0 != null && actionBar0.collapseActionView();
    }

    @Override  // androidx.appcompat.app.AppCompatDelegate
    public void onConfigurationChanged(Configuration configuration0) {
        if(this.mHasActionBar && this.mSubDecorInstalled) {
            ActionBar actionBar0 = this.getSupportActionBar();
            if(actionBar0 != null) {
                actionBar0.onConfigurationChanged(configuration0);
            }
        }
        AppCompatDrawableManager.get().onConfigurationChanged(this.mContext);
        this.mEffectiveConfiguration = new Configuration(this.mContext.getResources().getConfiguration());
        this.applyApplicationSpecificConfig(false, false);
    }

    @Override  // androidx.appcompat.app.AppCompatDelegate
    public void onCreate(Bundle bundle0) {
        this.mBaseContextAttached = true;
        this.applyApplicationSpecificConfig(false);
        this.ensureWindow();
        Object object0 = this.mHost;
        if(object0 instanceof Activity) {
            try {
                String s = null;
                s = NavUtils.getParentActivityName(((Activity)object0));
            }
            catch(IllegalArgumentException unused_ex) {
            }
            if(s != null) {
                ActionBar actionBar0 = this.peekSupportActionBar();
                if(actionBar0 == null) {
                    this.mEnableDefaultActionBarUp = true;
                }
                else {
                    actionBar0.setDefaultDisplayHomeAsUpEnabled(true);
                }
            }
            AppCompatDelegateImpl.addActiveDelegate(this);
        }
        this.mEffectiveConfiguration = new Configuration(this.mContext.getResources().getConfiguration());
        this.mCreated = true;
    }

    @Override  // android.view.LayoutInflater$Factory2
    public final View onCreateView(View view0, String s, Context context0, AttributeSet attributeSet0) {
        return this.createView(view0, s, context0, attributeSet0);
    }

    @Override  // android.view.LayoutInflater$Factory
    public View onCreateView(String s, Context context0, AttributeSet attributeSet0) {
        return this.onCreateView(null, s, context0, attributeSet0);
    }

    @Override  // androidx.appcompat.app.AppCompatDelegate
    public void onDestroy() {
        if(this.mHost instanceof Activity) {
            AppCompatDelegateImpl.removeActivityDelegate(this);
        }
        if(this.mInvalidatePanelMenuPosted) {
            this.mWindow.getDecorView().removeCallbacks(this.mInvalidatePanelMenuRunnable);
        }
        this.mDestroyed = true;
        if(this.mLocalNightMode == -100 || (!(this.mHost instanceof Activity) || !((Activity)this.mHost).isChangingConfigurations())) {
            String s1 = this.mHost.getClass().getName();
            AppCompatDelegateImpl.sLocalNightModes.remove(s1);
        }
        else {
            String s = this.mHost.getClass().getName();
            AppCompatDelegateImpl.sLocalNightModes.put(s, this.mLocalNightMode);
        }
        ActionBar actionBar0 = this.mActionBar;
        if(actionBar0 != null) {
            actionBar0.onDestroy();
        }
        this.cleanupAutoManagers();
    }

    boolean onKeyDown(int v, KeyEvent keyEvent0) {
        boolean z = true;
        switch(v) {
            case 4: {
                if((keyEvent0.getFlags() & 0x80) == 0) {
                    z = false;
                }
                this.mLongPressBackDown = z;
                return false;
            }
            case 82: {
                this.onKeyDownPanel(0, keyEvent0);
                return true;
            }
            default: {
                return false;
            }
        }
    }

    private boolean onKeyDownPanel(int v, KeyEvent keyEvent0) {
        if(keyEvent0.getRepeatCount() == 0) {
            PanelFeatureState appCompatDelegateImpl$PanelFeatureState0 = this.getPanelState(v, true);
            return appCompatDelegateImpl$PanelFeatureState0.isOpen ? false : this.preparePanel(appCompatDelegateImpl$PanelFeatureState0, keyEvent0);
        }
        return false;
    }

    boolean onKeyShortcut(int v, KeyEvent keyEvent0) {
        ActionBar actionBar0 = this.getSupportActionBar();
        if(actionBar0 != null && actionBar0.onKeyShortcut(v, keyEvent0)) {
            return true;
        }
        if(this.mPreparedPanel != null && this.performPanelShortcut(this.mPreparedPanel, keyEvent0.getKeyCode(), keyEvent0, 1)) {
            PanelFeatureState appCompatDelegateImpl$PanelFeatureState0 = this.mPreparedPanel;
            if(appCompatDelegateImpl$PanelFeatureState0 != null) {
                appCompatDelegateImpl$PanelFeatureState0.isHandled = true;
            }
            return true;
        }
        if(this.mPreparedPanel == null) {
            PanelFeatureState appCompatDelegateImpl$PanelFeatureState1 = this.getPanelState(0, true);
            this.preparePanel(appCompatDelegateImpl$PanelFeatureState1, keyEvent0);
            boolean z = this.performPanelShortcut(appCompatDelegateImpl$PanelFeatureState1, keyEvent0.getKeyCode(), keyEvent0, 1);
            appCompatDelegateImpl$PanelFeatureState1.isPrepared = false;
            return z;
        }
        return false;
    }

    boolean onKeyUp(int v, KeyEvent keyEvent0) {
        switch(v) {
            case 4: {
                return this.onBackPressed();
            }
            case 82: {
                this.onKeyUpPanel(0, keyEvent0);
                return true;
            }
            default: {
                return false;
            }
        }
    }

    private boolean onKeyUpPanel(int v, KeyEvent keyEvent0) {
        boolean z1;
        if(this.mActionMode != null) {
            return false;
        }
        boolean z = true;
        PanelFeatureState appCompatDelegateImpl$PanelFeatureState0 = this.getPanelState(v, true);
        if(v != 0 || (this.mDecorContentParent == null || !this.mDecorContentParent.canShowOverflowMenu() || ViewConfiguration.get(this.mContext).hasPermanentMenuKey())) {
            if(appCompatDelegateImpl$PanelFeatureState0.isOpen || appCompatDelegateImpl$PanelFeatureState0.isHandled) {
                boolean z2 = appCompatDelegateImpl$PanelFeatureState0.isOpen;
                this.closePanel(appCompatDelegateImpl$PanelFeatureState0, true);
                z = z2;
            }
            else if(appCompatDelegateImpl$PanelFeatureState0.isPrepared) {
                if(appCompatDelegateImpl$PanelFeatureState0.refreshMenuContent) {
                    appCompatDelegateImpl$PanelFeatureState0.isPrepared = false;
                    z1 = this.preparePanel(appCompatDelegateImpl$PanelFeatureState0, keyEvent0);
                }
                else {
                    z1 = true;
                }
                if(z1) {
                    this.openPanel(appCompatDelegateImpl$PanelFeatureState0, keyEvent0);
                }
                else {
                    z = false;
                }
            }
            else {
                z = false;
            }
        }
        else if(this.mDecorContentParent.isOverflowMenuShowing()) {
            z = this.mDecorContentParent.hideOverflowMenu();
        }
        else if(!this.mDestroyed && this.preparePanel(appCompatDelegateImpl$PanelFeatureState0, keyEvent0)) {
            z = this.mDecorContentParent.showOverflowMenu();
        }
        else {
            z = false;
        }
        if(z) {
            AudioManager audioManager0 = (AudioManager)this.mContext.getApplicationContext().getSystemService("audio");
            if(audioManager0 != null) {
                audioManager0.playSoundEffect(0);
                return true;
            }
            Log.w("AppCompatDelegate", "Couldn\'t get audio manager");
        }
        return z;
    }

    @Override  // androidx.appcompat.view.menu.MenuBuilder$Callback
    public boolean onMenuItemSelected(MenuBuilder menuBuilder0, MenuItem menuItem0) {
        Window.Callback window$Callback0 = this.getWindowCallback();
        if(window$Callback0 != null && !this.mDestroyed) {
            PanelFeatureState appCompatDelegateImpl$PanelFeatureState0 = this.findMenuPanel(menuBuilder0.getRootMenu());
            return appCompatDelegateImpl$PanelFeatureState0 == null ? false : window$Callback0.onMenuItemSelected(appCompatDelegateImpl$PanelFeatureState0.featureId, menuItem0);
        }
        return false;
    }

    @Override  // androidx.appcompat.view.menu.MenuBuilder$Callback
    public void onMenuModeChange(MenuBuilder menuBuilder0) {
        this.reopenMenu(true);
    }

    void onMenuOpened(int v) {
        if(v == 108) {
            ActionBar actionBar0 = this.getSupportActionBar();
            if(actionBar0 != null) {
                actionBar0.dispatchMenuVisibilityChanged(true);
            }
        }
    }

    void onPanelClosed(int v) {
        switch(v) {
            case 0: {
                PanelFeatureState appCompatDelegateImpl$PanelFeatureState0 = this.getPanelState(0, true);
                if(appCompatDelegateImpl$PanelFeatureState0.isOpen) {
                    this.closePanel(appCompatDelegateImpl$PanelFeatureState0, false);
                    return;
                }
                break;
            }
            case 108: {
                ActionBar actionBar0 = this.getSupportActionBar();
                if(actionBar0 != null) {
                    actionBar0.dispatchMenuVisibilityChanged(false);
                    return;
                }
                break;
            }
        }
    }

    @Override  // androidx.appcompat.app.AppCompatDelegate
    public void onPostCreate(Bundle bundle0) {
        this.ensureSubDecor();
    }

    @Override  // androidx.appcompat.app.AppCompatDelegate
    public void onPostResume() {
        ActionBar actionBar0 = this.getSupportActionBar();
        if(actionBar0 != null) {
            actionBar0.setShowHideAnimationEnabled(true);
        }
    }

    @Override  // androidx.appcompat.app.AppCompatDelegate
    public void onSaveInstanceState(Bundle bundle0) {
    }

    @Override  // androidx.appcompat.app.AppCompatDelegate
    public void onStart() {
        this.applyApplicationSpecificConfig(true, false);
    }

    @Override  // androidx.appcompat.app.AppCompatDelegate
    public void onStop() {
        ActionBar actionBar0 = this.getSupportActionBar();
        if(actionBar0 != null) {
            actionBar0.setShowHideAnimationEnabled(false);
        }
    }

    void onSubDecorInstalled(ViewGroup viewGroup0) {
    }

    private void openPanel(PanelFeatureState appCompatDelegateImpl$PanelFeatureState0, KeyEvent keyEvent0) {
        int v;
        if(appCompatDelegateImpl$PanelFeatureState0.isOpen || this.mDestroyed || appCompatDelegateImpl$PanelFeatureState0.featureId == 0 && (this.mContext.getResources().getConfiguration().screenLayout & 15) == 4) {
            return;
        }
        Window.Callback window$Callback0 = this.getWindowCallback();
        if(window$Callback0 != null && !window$Callback0.onMenuOpened(appCompatDelegateImpl$PanelFeatureState0.featureId, appCompatDelegateImpl$PanelFeatureState0.menu)) {
            this.closePanel(appCompatDelegateImpl$PanelFeatureState0, true);
            return;
        }
        WindowManager windowManager0 = (WindowManager)this.mContext.getSystemService("window");
        if(windowManager0 != null && this.preparePanel(appCompatDelegateImpl$PanelFeatureState0, keyEvent0)) {
            if(appCompatDelegateImpl$PanelFeatureState0.decorView == null || appCompatDelegateImpl$PanelFeatureState0.refreshDecorView) {
                if(appCompatDelegateImpl$PanelFeatureState0.decorView == null) {
                    if(!this.initializePanelDecor(appCompatDelegateImpl$PanelFeatureState0) || appCompatDelegateImpl$PanelFeatureState0.decorView == null) {
                        return;
                    }
                }
                else if(appCompatDelegateImpl$PanelFeatureState0.refreshDecorView && appCompatDelegateImpl$PanelFeatureState0.decorView.getChildCount() > 0) {
                    appCompatDelegateImpl$PanelFeatureState0.decorView.removeAllViews();
                }
                if(!this.initializePanelContent(appCompatDelegateImpl$PanelFeatureState0) || !appCompatDelegateImpl$PanelFeatureState0.hasPanelItems()) {
                    appCompatDelegateImpl$PanelFeatureState0.refreshDecorView = true;
                }
                else {
                    ViewGroup.LayoutParams viewGroup$LayoutParams1 = appCompatDelegateImpl$PanelFeatureState0.shownPanelView.getLayoutParams();
                    if(viewGroup$LayoutParams1 == null) {
                        viewGroup$LayoutParams1 = new ViewGroup.LayoutParams(-2, -2);
                    }
                    appCompatDelegateImpl$PanelFeatureState0.decorView.setBackgroundResource(appCompatDelegateImpl$PanelFeatureState0.background);
                    ViewParent viewParent0 = appCompatDelegateImpl$PanelFeatureState0.shownPanelView.getParent();
                    if(viewParent0 instanceof ViewGroup) {
                        ((ViewGroup)viewParent0).removeView(appCompatDelegateImpl$PanelFeatureState0.shownPanelView);
                    }
                    appCompatDelegateImpl$PanelFeatureState0.decorView.addView(appCompatDelegateImpl$PanelFeatureState0.shownPanelView, viewGroup$LayoutParams1);
                    if(!appCompatDelegateImpl$PanelFeatureState0.shownPanelView.hasFocus()) {
                        appCompatDelegateImpl$PanelFeatureState0.shownPanelView.requestFocus();
                    }
                    v = -2;
                label_35:
                    appCompatDelegateImpl$PanelFeatureState0.isHandled = false;
                    WindowManager.LayoutParams windowManager$LayoutParams0 = new WindowManager.LayoutParams(v, -2, appCompatDelegateImpl$PanelFeatureState0.x, appCompatDelegateImpl$PanelFeatureState0.y, 1002, 0x820000, -3);
                    windowManager$LayoutParams0.gravity = appCompatDelegateImpl$PanelFeatureState0.gravity;
                    windowManager$LayoutParams0.windowAnimations = appCompatDelegateImpl$PanelFeatureState0.windowAnimations;
                    windowManager0.addView(appCompatDelegateImpl$PanelFeatureState0.decorView, windowManager$LayoutParams0);
                    appCompatDelegateImpl$PanelFeatureState0.isOpen = true;
                    if(appCompatDelegateImpl$PanelFeatureState0.featureId == 0) {
                        this.updateBackInvokedCallbackState();
                    }
                }
            }
            else if(appCompatDelegateImpl$PanelFeatureState0.createdPanelView != null) {
                ViewGroup.LayoutParams viewGroup$LayoutParams0 = appCompatDelegateImpl$PanelFeatureState0.createdPanelView.getLayoutParams();
                v = viewGroup$LayoutParams0 == null || viewGroup$LayoutParams0.width != -1 ? -2 : -1;
                goto label_35;
            }
            else {
                v = -2;
                goto label_35;
            }
        }
    }

    final ActionBar peekSupportActionBar() {
        return this.mActionBar;
    }

    private boolean performPanelShortcut(PanelFeatureState appCompatDelegateImpl$PanelFeatureState0, int v, KeyEvent keyEvent0, int v1) {
        boolean z = false;
        if(keyEvent0.isSystem()) {
            return false;
        }
        if((appCompatDelegateImpl$PanelFeatureState0.isPrepared || this.preparePanel(appCompatDelegateImpl$PanelFeatureState0, keyEvent0)) && appCompatDelegateImpl$PanelFeatureState0.menu != null) {
            z = appCompatDelegateImpl$PanelFeatureState0.menu.performShortcut(v, keyEvent0, v1);
        }
        if(z && (v1 & 1) == 0 && this.mDecorContentParent == null) {
            this.closePanel(appCompatDelegateImpl$PanelFeatureState0, true);
        }
        return z;
    }

    private boolean preparePanel(PanelFeatureState appCompatDelegateImpl$PanelFeatureState0, KeyEvent keyEvent0) {
        if(this.mDestroyed) {
            return false;
        }
        if(appCompatDelegateImpl$PanelFeatureState0.isPrepared) {
            return true;
        }
        PanelFeatureState appCompatDelegateImpl$PanelFeatureState1 = this.mPreparedPanel;
        if(appCompatDelegateImpl$PanelFeatureState1 != null && appCompatDelegateImpl$PanelFeatureState1 != appCompatDelegateImpl$PanelFeatureState0) {
            this.closePanel(appCompatDelegateImpl$PanelFeatureState1, false);
        }
        Window.Callback window$Callback0 = this.getWindowCallback();
        if(window$Callback0 != null) {
            appCompatDelegateImpl$PanelFeatureState0.createdPanelView = window$Callback0.onCreatePanelView(appCompatDelegateImpl$PanelFeatureState0.featureId);
        }
        boolean z = appCompatDelegateImpl$PanelFeatureState0.featureId == 0 || appCompatDelegateImpl$PanelFeatureState0.featureId == 108;
        if(z) {
            DecorContentParent decorContentParent0 = this.mDecorContentParent;
            if(decorContentParent0 != null) {
                decorContentParent0.setMenuPrepared();
            }
        }
        if(appCompatDelegateImpl$PanelFeatureState0.createdPanelView == null && (!z || !(this.peekSupportActionBar() instanceof ToolbarActionBar))) {
            if(appCompatDelegateImpl$PanelFeatureState0.menu == null || appCompatDelegateImpl$PanelFeatureState0.refreshMenuContent) {
                if(appCompatDelegateImpl$PanelFeatureState0.menu == null && (!this.initializePanelMenu(appCompatDelegateImpl$PanelFeatureState0) || appCompatDelegateImpl$PanelFeatureState0.menu == null)) {
                    return false;
                }
                if(z && this.mDecorContentParent != null) {
                    if(this.mActionMenuPresenterCallback == null) {
                        this.mActionMenuPresenterCallback = new ActionMenuPresenterCallback(this);
                    }
                    this.mDecorContentParent.setMenu(appCompatDelegateImpl$PanelFeatureState0.menu, this.mActionMenuPresenterCallback);
                }
                appCompatDelegateImpl$PanelFeatureState0.menu.stopDispatchingItemsChanged();
                if(!window$Callback0.onCreatePanelMenu(appCompatDelegateImpl$PanelFeatureState0.featureId, appCompatDelegateImpl$PanelFeatureState0.menu)) {
                    appCompatDelegateImpl$PanelFeatureState0.setMenu(null);
                    if(z) {
                        DecorContentParent decorContentParent1 = this.mDecorContentParent;
                        if(decorContentParent1 != null) {
                            decorContentParent1.setMenu(null, this.mActionMenuPresenterCallback);
                        }
                    }
                    return false;
                }
                appCompatDelegateImpl$PanelFeatureState0.refreshMenuContent = false;
            }
            appCompatDelegateImpl$PanelFeatureState0.menu.stopDispatchingItemsChanged();
            if(appCompatDelegateImpl$PanelFeatureState0.frozenActionViewState != null) {
                appCompatDelegateImpl$PanelFeatureState0.menu.restoreActionViewStates(appCompatDelegateImpl$PanelFeatureState0.frozenActionViewState);
                appCompatDelegateImpl$PanelFeatureState0.frozenActionViewState = null;
            }
            if(!window$Callback0.onPreparePanel(0, appCompatDelegateImpl$PanelFeatureState0.createdPanelView, appCompatDelegateImpl$PanelFeatureState0.menu)) {
                if(z) {
                    DecorContentParent decorContentParent2 = this.mDecorContentParent;
                    if(decorContentParent2 != null) {
                        decorContentParent2.setMenu(null, this.mActionMenuPresenterCallback);
                    }
                }
                appCompatDelegateImpl$PanelFeatureState0.menu.startDispatchingItemsChanged();
                return false;
            }
            appCompatDelegateImpl$PanelFeatureState0.qwertyMode = KeyCharacterMap.load((keyEvent0 == null ? -1 : keyEvent0.getDeviceId())).getKeyboardType() != 1;
            appCompatDelegateImpl$PanelFeatureState0.menu.setQwertyMode(appCompatDelegateImpl$PanelFeatureState0.qwertyMode);
            appCompatDelegateImpl$PanelFeatureState0.menu.startDispatchingItemsChanged();
        }
        appCompatDelegateImpl$PanelFeatureState0.isPrepared = true;
        appCompatDelegateImpl$PanelFeatureState0.isHandled = false;
        this.mPreparedPanel = appCompatDelegateImpl$PanelFeatureState0;
        return true;
    }

    private void reopenMenu(boolean z) {
        if(this.mDecorContentParent != null && this.mDecorContentParent.canShowOverflowMenu() && (!ViewConfiguration.get(this.mContext).hasPermanentMenuKey() || this.mDecorContentParent.isOverflowMenuShowPending())) {
            Window.Callback window$Callback0 = this.getWindowCallback();
            if(this.mDecorContentParent.isOverflowMenuShowing() && z) {
                this.mDecorContentParent.hideOverflowMenu();
                if(!this.mDestroyed) {
                    window$Callback0.onPanelClosed(108, this.getPanelState(0, true).menu);
                    return;
                }
            }
            else if(window$Callback0 != null && !this.mDestroyed) {
                if(this.mInvalidatePanelMenuPosted && (this.mInvalidatePanelMenuFeatures & 1) != 0) {
                    this.mWindow.getDecorView().removeCallbacks(this.mInvalidatePanelMenuRunnable);
                    this.mInvalidatePanelMenuRunnable.run();
                }
                PanelFeatureState appCompatDelegateImpl$PanelFeatureState0 = this.getPanelState(0, true);
                if(appCompatDelegateImpl$PanelFeatureState0.menu != null && !appCompatDelegateImpl$PanelFeatureState0.refreshMenuContent && window$Callback0.onPreparePanel(0, appCompatDelegateImpl$PanelFeatureState0.createdPanelView, appCompatDelegateImpl$PanelFeatureState0.menu)) {
                    window$Callback0.onMenuOpened(108, appCompatDelegateImpl$PanelFeatureState0.menu);
                    this.mDecorContentParent.showOverflowMenu();
                }
            }
            return;
        }
        PanelFeatureState appCompatDelegateImpl$PanelFeatureState1 = this.getPanelState(0, true);
        appCompatDelegateImpl$PanelFeatureState1.refreshDecorView = true;
        this.closePanel(appCompatDelegateImpl$PanelFeatureState1, false);
        this.openPanel(appCompatDelegateImpl$PanelFeatureState1, null);
    }

    @Override  // androidx.appcompat.app.AppCompatDelegate
    public boolean requestWindowFeature(int v) {
        int v1 = this.sanitizeWindowFeatureId(v);
        if(this.mWindowNoTitle && v1 == 108) {
            return false;
        }
        if(this.mHasActionBar && v1 == 1) {
            this.mHasActionBar = false;
        }
        switch(v1) {
            case 1: {
                this.throwFeatureRequestIfSubDecorInstalled();
                this.mWindowNoTitle = true;
                return true;
            }
            case 2: {
                this.throwFeatureRequestIfSubDecorInstalled();
                this.mFeatureProgress = true;
                return true;
            }
            case 5: {
                this.throwFeatureRequestIfSubDecorInstalled();
                this.mFeatureIndeterminateProgress = true;
                return true;
            }
            case 10: {
                this.throwFeatureRequestIfSubDecorInstalled();
                this.mOverlayActionMode = true;
                return true;
            }
            case 108: {
                this.throwFeatureRequestIfSubDecorInstalled();
                this.mHasActionBar = true;
                return true;
            }
            default: {
                if(v1 != 109) {
                    return this.mWindow.requestFeature(v1);
                }
                this.throwFeatureRequestIfSubDecorInstalled();
                this.mOverlayActionBar = true;
                return true;
            }
        }
    }

    private int sanitizeWindowFeatureId(int v) {
        switch(v) {
            case 8: {
                Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR id when requesting this feature.");
                return 108;
            }
            case 9: {
                Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY id when requesting this feature.");
                return 109;
            }
            default: {
                return v;
            }
        }
    }

    void setConfigurationLocales(Configuration configuration0, LocaleListCompat localeListCompat0) {
        if(Build.VERSION.SDK_INT >= 24) {
            Api24Impl.setLocales(configuration0, localeListCompat0);
            return;
        }
        configuration0.setLocale(localeListCompat0.get(0));
        configuration0.setLayoutDirection(localeListCompat0.get(0));
    }

    @Override  // androidx.appcompat.app.AppCompatDelegate
    public void setContentView(int v) {
        this.ensureSubDecor();
        ViewGroup viewGroup0 = (ViewGroup)this.mSubDecor.findViewById(0x1020002);
        viewGroup0.removeAllViews();
        LayoutInflater.from(this.mContext).inflate(v, viewGroup0);
        this.mAppCompatWindowCallback.bypassOnContentChanged(this.mWindow.getCallback());
    }

    @Override  // androidx.appcompat.app.AppCompatDelegate
    public void setContentView(View view0) {
        this.ensureSubDecor();
        ViewGroup viewGroup0 = (ViewGroup)this.mSubDecor.findViewById(0x1020002);
        viewGroup0.removeAllViews();
        viewGroup0.addView(view0);
        this.mAppCompatWindowCallback.bypassOnContentChanged(this.mWindow.getCallback());
    }

    @Override  // androidx.appcompat.app.AppCompatDelegate
    public void setContentView(View view0, ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        this.ensureSubDecor();
        ViewGroup viewGroup0 = (ViewGroup)this.mSubDecor.findViewById(0x1020002);
        viewGroup0.removeAllViews();
        viewGroup0.addView(view0, viewGroup$LayoutParams0);
        this.mAppCompatWindowCallback.bypassOnContentChanged(this.mWindow.getCallback());
    }

    void setDefaultLocalesForLocaleList(LocaleListCompat localeListCompat0) {
        if(Build.VERSION.SDK_INT >= 24) {
            Api24Impl.setDefaultLocales(localeListCompat0);
            return;
        }
        Locale.setDefault(localeListCompat0.get(0));
    }

    @Override  // androidx.appcompat.app.AppCompatDelegate
    public void setHandleNativeActionModesEnabled(boolean z) {
        this.mHandleNativeActionModes = z;
    }

    @Override  // androidx.appcompat.app.AppCompatDelegate
    public void setLocalNightMode(int v) {
        if(this.mLocalNightMode != v) {
            this.mLocalNightMode = v;
            if(this.mBaseContextAttached) {
                this.applyDayNight();
            }
        }
    }

    @Override  // androidx.appcompat.app.AppCompatDelegate
    public void setOnBackInvokedDispatcher(OnBackInvokedDispatcher onBackInvokedDispatcher0) {
        super.setOnBackInvokedDispatcher(onBackInvokedDispatcher0);
        OnBackInvokedDispatcher onBackInvokedDispatcher1 = this.mDispatcher;
        if(onBackInvokedDispatcher1 != null) {
            OnBackInvokedCallback onBackInvokedCallback0 = this.mBackCallback;
            if(onBackInvokedCallback0 != null) {
                Api33Impl.unregisterOnBackInvokedCallback(onBackInvokedDispatcher1, onBackInvokedCallback0);
                this.mBackCallback = null;
            }
        }
        this.mDispatcher = onBackInvokedDispatcher0 != null || (!(this.mHost instanceof Activity) || ((Activity)this.mHost).getWindow() == null) ? onBackInvokedDispatcher0 : Api33Impl.getOnBackInvokedDispatcher(((Activity)this.mHost));
        this.updateBackInvokedCallbackState();
    }

    @Override  // androidx.appcompat.app.AppCompatDelegate
    public void setSupportActionBar(Toolbar toolbar0) {
        if(!(this.mHost instanceof Activity)) {
            return;
        }
        ActionBar actionBar0 = this.getSupportActionBar();
        if(actionBar0 instanceof WindowDecorActionBar) {
            throw new IllegalStateException("This Activity already has an action bar supplied by the window decor. Do not request Window.FEATURE_SUPPORT_ACTION_BAR and set windowActionBar to false in your theme to use a Toolbar instead.");
        }
        this.mMenuInflater = null;
        if(actionBar0 != null) {
            actionBar0.onDestroy();
        }
        this.mActionBar = null;
        if(toolbar0 == null) {
            this.mAppCompatWindowCallback.setActionBarCallback(null);
        }
        else {
            ToolbarActionBar toolbarActionBar0 = new ToolbarActionBar(toolbar0, this.getTitle(), this.mAppCompatWindowCallback);
            this.mActionBar = toolbarActionBar0;
            this.mAppCompatWindowCallback.setActionBarCallback(toolbarActionBar0.mMenuCallback);
            toolbar0.setBackInvokedCallbackEnabled(true);
        }
        this.invalidateOptionsMenu();
    }

    @Override  // androidx.appcompat.app.AppCompatDelegate
    public void setTheme(int v) {
        this.mThemeResId = v;
    }

    @Override  // androidx.appcompat.app.AppCompatDelegate
    public final void setTitle(CharSequence charSequence0) {
        this.mTitle = charSequence0;
        DecorContentParent decorContentParent0 = this.mDecorContentParent;
        if(decorContentParent0 != null) {
            decorContentParent0.setWindowTitle(charSequence0);
            return;
        }
        if(this.peekSupportActionBar() != null) {
            this.peekSupportActionBar().setWindowTitle(charSequence0);
            return;
        }
        TextView textView0 = this.mTitleView;
        if(textView0 != null) {
            textView0.setText(charSequence0);
        }
    }

    // 去混淆评级： 低(20)
    final boolean shouldAnimateActionModeView() {
        return this.mSubDecorInstalled && (this.mSubDecor != null && this.mSubDecor.isLaidOut());
    }

    private boolean shouldInheritContext(ViewParent viewParent0) {
        if(viewParent0 == null) {
            return false;
        }
        View view0 = this.mWindow.getDecorView();
        while(true) {
            if(viewParent0 == null) {
                return true;
            }
            if(viewParent0 == view0 || !(viewParent0 instanceof View) || ((View)viewParent0).isAttachedToWindow()) {
                break;
            }
            viewParent0 = viewParent0.getParent();
        }
        return false;
    }

    boolean shouldRegisterBackInvokedCallback() {
        if(this.mDispatcher == null) {
            return false;
        }
        PanelFeatureState appCompatDelegateImpl$PanelFeatureState0 = this.getPanelState(0, false);
        return appCompatDelegateImpl$PanelFeatureState0 == null || !appCompatDelegateImpl$PanelFeatureState0.isOpen ? this.mActionMode != null : true;
    }

    @Override  // androidx.appcompat.app.AppCompatDelegate
    public ActionMode startSupportActionMode(androidx.appcompat.view.ActionMode.Callback actionMode$Callback0) {
        if(actionMode$Callback0 == null) {
            throw new IllegalArgumentException("ActionMode callback can not be null.");
        }
        ActionMode actionMode0 = this.mActionMode;
        if(actionMode0 != null) {
            actionMode0.finish();
        }
        ActionModeCallbackWrapperV9 appCompatDelegateImpl$ActionModeCallbackWrapperV90 = new ActionModeCallbackWrapperV9(this, actionMode$Callback0);
        ActionBar actionBar0 = this.getSupportActionBar();
        if(actionBar0 != null) {
            ActionMode actionMode1 = actionBar0.startActionMode(appCompatDelegateImpl$ActionModeCallbackWrapperV90);
            this.mActionMode = actionMode1;
            if(actionMode1 != null) {
                AppCompatCallback appCompatCallback0 = this.mAppCompatCallback;
                if(appCompatCallback0 != null) {
                    appCompatCallback0.onSupportActionModeStarted(actionMode1);
                }
            }
        }
        if(this.mActionMode == null) {
            this.mActionMode = this.startSupportActionModeFromWindow(appCompatDelegateImpl$ActionModeCallbackWrapperV90);
        }
        this.updateBackInvokedCallbackState();
        return this.mActionMode;
    }

    ActionMode startSupportActionModeFromWindow(androidx.appcompat.view.ActionMode.Callback actionMode$Callback0) {
        Context context0;
        this.endOnGoingFadeAnimation();
        ActionMode actionMode0 = this.mActionMode;
        if(actionMode0 != null) {
            actionMode0.finish();
        }
        if(!(actionMode$Callback0 instanceof ActionModeCallbackWrapperV9)) {
            actionMode$Callback0 = new ActionModeCallbackWrapperV9(this, actionMode$Callback0);
        }
        AppCompatCallback appCompatCallback0 = this.mAppCompatCallback;
        ActionMode actionMode1 = null;
        if(appCompatCallback0 != null && !this.mDestroyed) {
            try {
                actionMode1 = appCompatCallback0.onWindowStartingSupportActionMode(actionMode$Callback0);
            }
            catch(AbstractMethodError unused_ex) {
            }
        }
        boolean z = true;
        if(actionMode1 == null) {
            if(this.mActionModeView == null) {
                if(this.mIsFloating) {
                    TypedValue typedValue0 = new TypedValue();
                    Resources.Theme resources$Theme0 = this.mContext.getTheme();
                    resources$Theme0.resolveAttribute(attr.actionBarTheme, typedValue0, true);
                    if(typedValue0.resourceId == 0) {
                        context0 = this.mContext;
                    }
                    else {
                        Resources.Theme resources$Theme1 = this.mContext.getResources().newTheme();
                        resources$Theme1.setTo(resources$Theme0);
                        resources$Theme1.applyStyle(typedValue0.resourceId, true);
                        context0 = new ContextThemeWrapper(this.mContext, 0);
                        context0.getTheme().setTo(resources$Theme1);
                    }
                    this.mActionModeView = new ActionBarContextView(context0);
                    PopupWindow popupWindow0 = new PopupWindow(context0, null, attr.actionModePopupWindowStyle);
                    this.mActionModePopup = popupWindow0;
                    PopupWindowCompat.setWindowLayoutType(popupWindow0, 2);
                    this.mActionModePopup.setContentView(this.mActionModeView);
                    this.mActionModePopup.setWidth(-1);
                    context0.getTheme().resolveAttribute(attr.actionBarSize, typedValue0, true);
                    int v = TypedValue.complexToDimensionPixelSize(typedValue0.data, context0.getResources().getDisplayMetrics());
                    this.mActionModeView.setContentHeight(v);
                    this.mActionModePopup.setHeight(-2);
                    this.mShowActionModePopup = new Runnable() {
                        @Override
                        public void run() {
                            AppCompatDelegateImpl.this.mActionModePopup.showAtLocation(AppCompatDelegateImpl.this.mActionModeView, 55, 0, 0);
                            AppCompatDelegateImpl.this.endOnGoingFadeAnimation();
                            if(AppCompatDelegateImpl.this.shouldAnimateActionModeView()) {
                                AppCompatDelegateImpl.this.mActionModeView.setAlpha(0.0f);
                                AppCompatDelegateImpl.this.mFadeAnim = ViewCompat.animate(AppCompatDelegateImpl.this.mActionModeView).alpha(1.0f);
                                AppCompatDelegateImpl.this.mFadeAnim.setListener(new ViewPropertyAnimatorListenerAdapter() {
                                    @Override  // androidx.core.view.ViewPropertyAnimatorListenerAdapter
                                    public void onAnimationEnd(View view0) {
                                        AppCompatDelegateImpl.this.mActionModeView.setAlpha(1.0f);
                                        AppCompatDelegateImpl.this.mFadeAnim.setListener(null);
                                        AppCompatDelegateImpl.this.mFadeAnim = null;
                                    }

                                    @Override  // androidx.core.view.ViewPropertyAnimatorListenerAdapter
                                    public void onAnimationStart(View view0) {
                                        AppCompatDelegateImpl.this.mActionModeView.setVisibility(0);
                                    }
                                });
                                return;
                            }
                            AppCompatDelegateImpl.this.mActionModeView.setAlpha(1.0f);
                            AppCompatDelegateImpl.this.mActionModeView.setVisibility(0);
                        }
                    };
                }
                else {
                    ViewStubCompat viewStubCompat0 = (ViewStubCompat)this.mSubDecor.findViewById(id.action_mode_bar_stub);
                    if(viewStubCompat0 != null) {
                        viewStubCompat0.setLayoutInflater(LayoutInflater.from(this.getActionBarThemedContext()));
                        this.mActionModeView = (ActionBarContextView)viewStubCompat0.inflate();
                    }
                }
            }
            if(this.mActionModeView != null) {
                this.endOnGoingFadeAnimation();
                this.mActionModeView.killMode();
                Context context1 = this.mActionModeView.getContext();
                ActionBarContextView actionBarContextView0 = this.mActionModeView;
                if(this.mActionModePopup != null) {
                    z = false;
                }
                StandaloneActionMode standaloneActionMode0 = new StandaloneActionMode(context1, actionBarContextView0, actionMode$Callback0, z);
                if(actionMode$Callback0.onCreateActionMode(standaloneActionMode0, standaloneActionMode0.getMenu())) {
                    standaloneActionMode0.invalidate();
                    this.mActionModeView.initForMode(standaloneActionMode0);
                    this.mActionMode = standaloneActionMode0;
                    if(this.shouldAnimateActionModeView()) {
                        this.mActionModeView.setAlpha(0.0f);
                        ViewPropertyAnimatorCompat viewPropertyAnimatorCompat0 = ViewCompat.animate(this.mActionModeView).alpha(1.0f);
                        this.mFadeAnim = viewPropertyAnimatorCompat0;
                        viewPropertyAnimatorCompat0.setListener(new ViewPropertyAnimatorListenerAdapter() {
                            @Override  // androidx.core.view.ViewPropertyAnimatorListenerAdapter
                            public void onAnimationEnd(View view0) {
                                AppCompatDelegateImpl.this.mActionModeView.setAlpha(1.0f);
                                AppCompatDelegateImpl.this.mFadeAnim.setListener(null);
                                AppCompatDelegateImpl.this.mFadeAnim = null;
                            }

                            @Override  // androidx.core.view.ViewPropertyAnimatorListenerAdapter
                            public void onAnimationStart(View view0) {
                                AppCompatDelegateImpl.this.mActionModeView.setVisibility(0);
                                if(AppCompatDelegateImpl.this.mActionModeView.getParent() instanceof View) {
                                    ViewCompat.requestApplyInsets(((View)AppCompatDelegateImpl.this.mActionModeView.getParent()));
                                }
                            }
                        });
                    }
                    else {
                        this.mActionModeView.setAlpha(1.0f);
                        this.mActionModeView.setVisibility(0);
                        if(this.mActionModeView.getParent() instanceof View) {
                            ViewCompat.requestApplyInsets(((View)this.mActionModeView.getParent()));
                        }
                    }
                    if(this.mActionModePopup != null) {
                        this.mWindow.getDecorView().post(this.mShowActionModePopup);
                    }
                }
                else {
                    this.mActionMode = null;
                }
            }
        }
        else {
            this.mActionMode = actionMode1;
        }
        ActionMode actionMode2 = this.mActionMode;
        if(actionMode2 != null) {
            AppCompatCallback appCompatCallback1 = this.mAppCompatCallback;
            if(appCompatCallback1 != null) {
                appCompatCallback1.onSupportActionModeStarted(actionMode2);
            }
        }
        this.updateBackInvokedCallbackState();
        return this.mActionMode;
    }

    private void throwFeatureRequestIfSubDecorInstalled() {
        if(this.mSubDecorInstalled) {
            throw new AndroidRuntimeException("Window feature must be requested before adding content");
        }
    }

    private AppCompatActivity tryUnwrapContext() {
        for(Context context0 = this.mContext; context0 != null; context0 = ((ContextWrapper)context0).getBaseContext()) {
            if(context0 instanceof AppCompatActivity) {
                return (AppCompatActivity)context0;
            }
            if(!(context0 instanceof ContextWrapper)) {
                break;
            }
        }
        return null;
    }

    private void updateActivityConfiguration(Configuration configuration0) {
        Activity activity0 = (Activity)this.mHost;
        if(activity0 instanceof LifecycleOwner) {
            if(((LifecycleOwner)activity0).getLifecycle().getCurrentState().isAtLeast(State.CREATED)) {
                activity0.onConfigurationChanged(configuration0);
            }
        }
        else if(this.mCreated && !this.mDestroyed) {
            activity0.onConfigurationChanged(configuration0);
        }
    }

    private boolean updateAppConfiguration(int v, LocaleListCompat localeListCompat0, boolean z) {
        boolean z3;
        Configuration configuration0 = this.createOverrideAppConfiguration(this.mContext, v, localeListCompat0, null, false);
        int v1 = this.getActivityHandlesConfigChangesFlags(this.mContext);
        Configuration configuration1 = this.mEffectiveConfiguration == null ? this.mContext.getResources().getConfiguration() : this.mEffectiveConfiguration;
        int v2 = configuration1.uiMode & 0x30;
        int v3 = configuration0.uiMode & 0x30;
        LocaleListCompat localeListCompat1 = this.getConfigurationLocales(configuration1);
        LocaleListCompat localeListCompat2 = localeListCompat0 == null ? null : this.getConfigurationLocales(configuration0);
        boolean z1 = false;
        int v4 = v2 == v3 ? 0 : 0x200;
        if(localeListCompat2 != null && !localeListCompat1.equals(localeListCompat2)) {
            v4 |= 0x2004;
        }
        boolean z2 = true;
        if((~v1 & v4) == 0 || !z || !this.mBaseContextAttached || !AppCompatDelegateImpl.sCanReturnDifferentContext && !this.mCreated || (!(this.mHost instanceof Activity) || ((Activity)this.mHost).isChild())) {
            z3 = false;
        }
        else {
            if(Build.VERSION.SDK_INT >= 0x1F && (v4 & 0x2000) != 0) {
                ((Activity)this.mHost).getWindow().getDecorView().setLayoutDirection(configuration0.getLayoutDirection());
            }
            ActivityCompat.recreate(((Activity)this.mHost));
            z3 = true;
        }
        if(z3 || v4 == 0) {
            z2 = z3;
        }
        else {
            if((v4 & v1) == v4) {
                z1 = true;
            }
            this.updateResourcesConfiguration(v3, localeListCompat2, z1, null);
        }
        if(localeListCompat2 != null) {
            this.setDefaultLocalesForLocaleList(this.getConfigurationLocales(this.mContext.getResources().getConfiguration()));
        }
        return z2;
    }

    void updateBackInvokedCallbackState() {
        if(Build.VERSION.SDK_INT >= 33) {
            boolean z = this.shouldRegisterBackInvokedCallback();
            if(z && this.mBackCallback == null) {
                this.mBackCallback = Api33Impl.registerOnBackPressedCallback(this.mDispatcher, this);
                return;
            }
            if(!z) {
                OnBackInvokedCallback onBackInvokedCallback0 = this.mBackCallback;
                if(onBackInvokedCallback0 != null) {
                    Api33Impl.unregisterOnBackInvokedCallback(this.mDispatcher, onBackInvokedCallback0);
                    this.mBackCallback = null;
                }
            }
        }
    }

    private void updateResourcesConfiguration(int v, LocaleListCompat localeListCompat0, boolean z, Configuration configuration0) {
        Resources resources0 = this.mContext.getResources();
        Configuration configuration1 = new Configuration(resources0.getConfiguration());
        if(configuration0 != null) {
            configuration1.updateFrom(configuration0);
        }
        configuration1.uiMode = v | resources0.getConfiguration().uiMode & -49;
        if(localeListCompat0 != null) {
            this.setConfigurationLocales(configuration1, localeListCompat0);
        }
        resources0.updateConfiguration(configuration1, null);
        if(Build.VERSION.SDK_INT < 26) {
            ResourcesFlusher.flush(resources0);
        }
        int v1 = this.mThemeResId;
        if(v1 != 0) {
            this.mContext.setTheme(v1);
            if(Build.VERSION.SDK_INT >= 23) {
                this.mContext.getTheme().applyStyle(this.mThemeResId, true);
            }
        }
        if(z && this.mHost instanceof Activity) {
            this.updateActivityConfiguration(configuration1);
        }
    }

    final int updateStatusGuard(WindowInsetsCompat windowInsetsCompat0, Rect rect0) {
        int v9;
        int v8;
        int v1;
        int v = 0;
        if(windowInsetsCompat0 == null) {
            v1 = rect0 == null ? 0 : rect0.top;
        }
        else {
            v1 = windowInsetsCompat0.getSystemWindowInsetTop();
        }
        if(this.mActionModeView == null || !(this.mActionModeView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            v9 = 0;
        }
        else {
            ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0 = (ViewGroup.MarginLayoutParams)this.mActionModeView.getLayoutParams();
            int v2 = 1;
            if(this.mActionModeView.isShown()) {
                if(this.mTempRect1 == null) {
                    this.mTempRect1 = new Rect();
                    this.mTempRect2 = new Rect();
                }
                Rect rect1 = this.mTempRect1;
                Rect rect2 = this.mTempRect2;
                if(windowInsetsCompat0 == null) {
                    rect1.set(rect0);
                }
                else {
                    rect1.set(windowInsetsCompat0.getSystemWindowInsetLeft(), windowInsetsCompat0.getSystemWindowInsetTop(), windowInsetsCompat0.getSystemWindowInsetRight(), windowInsetsCompat0.getSystemWindowInsetBottom());
                }
                ViewUtils.computeFitSystemWindows(this.mSubDecor, rect1, rect2);
                int v3 = rect1.top;
                int v4 = rect1.left;
                int v5 = rect1.right;
                WindowInsetsCompat windowInsetsCompat1 = ViewCompat.getRootWindowInsets(this.mSubDecor);
                int v6 = windowInsetsCompat1 == null ? 0 : windowInsetsCompat1.getSystemWindowInsetLeft();
                int v7 = windowInsetsCompat1 == null ? 0 : windowInsetsCompat1.getSystemWindowInsetRight();
                if(viewGroup$MarginLayoutParams0.topMargin != v3 || viewGroup$MarginLayoutParams0.leftMargin != v4 || viewGroup$MarginLayoutParams0.rightMargin != v5) {
                    viewGroup$MarginLayoutParams0.topMargin = v3;
                    viewGroup$MarginLayoutParams0.leftMargin = v4;
                    viewGroup$MarginLayoutParams0.rightMargin = v5;
                    v8 = 1;
                }
                else {
                    v8 = 0;
                }
                if(v3 <= 0 || this.mStatusGuard != null) {
                    View view1 = this.mStatusGuard;
                    if(view1 != null) {
                        ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams1 = (ViewGroup.MarginLayoutParams)view1.getLayoutParams();
                        if(viewGroup$MarginLayoutParams1.height != viewGroup$MarginLayoutParams0.topMargin || viewGroup$MarginLayoutParams1.leftMargin != v6 || viewGroup$MarginLayoutParams1.rightMargin != v7) {
                            viewGroup$MarginLayoutParams1.height = viewGroup$MarginLayoutParams0.topMargin;
                            viewGroup$MarginLayoutParams1.leftMargin = v6;
                            viewGroup$MarginLayoutParams1.rightMargin = v7;
                            this.mStatusGuard.setLayoutParams(viewGroup$MarginLayoutParams1);
                        }
                    }
                }
                else {
                    View view0 = new View(this.mContext);
                    this.mStatusGuard = view0;
                    view0.setVisibility(8);
                    FrameLayout.LayoutParams frameLayout$LayoutParams0 = new FrameLayout.LayoutParams(-1, viewGroup$MarginLayoutParams0.topMargin, 51);
                    frameLayout$LayoutParams0.leftMargin = v6;
                    frameLayout$LayoutParams0.rightMargin = v7;
                    this.mSubDecor.addView(this.mStatusGuard, -1, frameLayout$LayoutParams0);
                }
                View view2 = this.mStatusGuard;
                if(view2 == null) {
                    v2 = 0;
                }
                else if(view2.getVisibility() != 0) {
                    this.updateStatusGuardColor(this.mStatusGuard);
                }
                if(!this.mOverlayActionMode && v2 != 0) {
                    v1 = 0;
                }
                v9 = v2;
                v2 = v8;
            }
            else if(viewGroup$MarginLayoutParams0.topMargin == 0) {
                v9 = 0;
                v2 = 0;
            }
            else {
                viewGroup$MarginLayoutParams0.topMargin = 0;
                v9 = 0;
            }
            if(v2 != 0) {
                this.mActionModeView.setLayoutParams(viewGroup$MarginLayoutParams0);
            }
        }
        View view3 = this.mStatusGuard;
        if(view3 != null) {
            if(v9 == 0) {
                v = 8;
            }
            view3.setVisibility(v);
        }
        return v1;
    }

    private void updateStatusGuardColor(View view0) {
        view0.setBackgroundColor(((ViewCompat.getWindowSystemUiVisibility(view0) & 0x2000) == 0 ? ContextCompat.getColor(this.mContext, color.abc_decor_view_status_guard) : ContextCompat.getColor(this.mContext, color.abc_decor_view_status_guard_light)));
    }

    class androidx.appcompat.app.AppCompatDelegateImpl.1 implements Thread.UncaughtExceptionHandler {
        androidx.appcompat.app.AppCompatDelegateImpl.1(Thread.UncaughtExceptionHandler thread$UncaughtExceptionHandler0) {
        }

        private boolean shouldWrapException(Throwable throwable0) {
            if(throwable0 instanceof Resources.NotFoundException) {
                String s = throwable0.getMessage();
                return s != null && (s.contains("drawable") || s.contains("Drawable"));
            }
            return false;
        }

        @Override
        public void uncaughtException(Thread thread0, Throwable throwable0) {
            if(this.shouldWrapException(throwable0)) {
                Resources.NotFoundException resources$NotFoundException0 = new Resources.NotFoundException(throwable0.getMessage() + ". If the resource you are trying to use is a vector resource, you may be referencing it in an unsupported way. See AppCompatDelegate.setCompatVectorFromResourcesEnabled() for more info.");
                resources$NotFoundException0.initCause(throwable0.getCause());
                resources$NotFoundException0.setStackTrace(throwable0.getStackTrace());
                this.val$defHandler.uncaughtException(thread0, resources$NotFoundException0);
                return;
            }
            this.val$defHandler.uncaughtException(thread0, throwable0);
        }
    }

}

