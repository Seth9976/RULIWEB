package androidx.appcompat.app;

import android.app.Activity;
import android.app.Dialog;
import android.app.LocaleManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ServiceInfo;
import android.content.res.Configuration;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.LocaleList;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.window.OnBackInvokedDispatcher;
import androidx.appcompat.view.ActionMode.Callback;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.VectorEnabledTintResources;
import androidx.collection.ArraySet;
import androidx.core.app.AppLocalesStorageHelper;
import androidx.core.os.LocaleListCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.Executor;

public abstract class AppCompatDelegate {
    static class Api24Impl {
        static LocaleList localeListForLanguageTags(String s) {
            return LocaleList.forLanguageTags(s);
        }
    }

    static class Api33Impl {
        static LocaleList localeManagerGetApplicationLocales(Object object0) {
            return ((LocaleManager)object0).getApplicationLocales();
        }

        static void localeManagerSetApplicationLocales(Object object0, LocaleList localeList0) {
            ((LocaleManager)object0).setApplicationLocales(localeList0);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface NightMode {
    }

    static class SerialExecutor implements Executor {
        Runnable mActive;
        final Executor mExecutor;
        private final Object mLock;
        final Queue mTasks;

        SerialExecutor(Executor executor0) {
            this.mLock = new Object();
            this.mTasks = new ArrayDeque();
            this.mExecutor = executor0;
        }

        @Override
        public void execute(Runnable runnable0) {
            synchronized(this.mLock) {
                AppCompatDelegate.SerialExecutor..ExternalSyntheticLambda0 appCompatDelegate$SerialExecutor$$ExternalSyntheticLambda00 = () -> try {
                    runnable0.run();
                }
                finally {
                    this.scheduleNext();
                };
                this.mTasks.add(appCompatDelegate$SerialExecutor$$ExternalSyntheticLambda00);
                if(this.mActive == null) {
                    this.scheduleNext();
                }
            }
        }

        // 检测为 Lambda 实现
        void lambda$execute$0$androidx-appcompat-app-AppCompatDelegate$SerialExecutor(Runnable runnable0) [...]

        protected void scheduleNext() {
            synchronized(this.mLock) {
                Runnable runnable0 = (Runnable)this.mTasks.poll();
                this.mActive = runnable0;
                if(runnable0 != null) {
                    this.mExecutor.execute(runnable0);
                }
            }
        }
    }

    static class ThreadPerTaskExecutor implements Executor {
        @Override
        public void execute(Runnable runnable0) {
            new Thread(runnable0).start();
        }
    }

    static final String APP_LOCALES_META_DATA_HOLDER_SERVICE_NAME = "androidx.appcompat.app.AppLocalesMetadataHolderService";
    static final boolean DEBUG = false;
    public static final int FEATURE_ACTION_MODE_OVERLAY = 10;
    public static final int FEATURE_SUPPORT_ACTION_BAR = 108;
    public static final int FEATURE_SUPPORT_ACTION_BAR_OVERLAY = 109;
    @Deprecated
    public static final int MODE_NIGHT_AUTO = 0;
    public static final int MODE_NIGHT_AUTO_BATTERY = 3;
    @Deprecated
    public static final int MODE_NIGHT_AUTO_TIME = 0;
    public static final int MODE_NIGHT_FOLLOW_SYSTEM = -1;
    public static final int MODE_NIGHT_NO = 1;
    public static final int MODE_NIGHT_UNSPECIFIED = -100;
    public static final int MODE_NIGHT_YES = 2;
    static final String TAG = "AppCompatDelegate";
    private static final ArraySet sActivityDelegates;
    private static final Object sActivityDelegatesLock;
    private static final Object sAppLocalesStorageSyncLock;
    private static int sDefaultNightMode;
    private static Boolean sIsAutoStoreLocalesOptedIn;
    private static boolean sIsFrameworkSyncChecked;
    private static LocaleListCompat sRequestedAppLocales;
    static SerialExecutor sSerialExecutorForLocalesStorage;
    private static LocaleListCompat sStoredAppLocales;

    static {
        AppCompatDelegate.sSerialExecutorForLocalesStorage = new SerialExecutor(new ThreadPerTaskExecutor());
        AppCompatDelegate.sDefaultNightMode = -100;
        AppCompatDelegate.sRequestedAppLocales = null;
        AppCompatDelegate.sStoredAppLocales = null;
        AppCompatDelegate.sIsAutoStoreLocalesOptedIn = null;
        AppCompatDelegate.sIsFrameworkSyncChecked = false;
        AppCompatDelegate.sActivityDelegates = new ArraySet();
        AppCompatDelegate.sActivityDelegatesLock = new Object();
        AppCompatDelegate.sAppLocalesStorageSyncLock = new Object();
    }

    static void addActiveDelegate(AppCompatDelegate appCompatDelegate0) {
        synchronized(AppCompatDelegate.sActivityDelegatesLock) {
            AppCompatDelegate.removeDelegateFromActives(appCompatDelegate0);
            WeakReference weakReference0 = new WeakReference(appCompatDelegate0);
            AppCompatDelegate.sActivityDelegates.add(weakReference0);
        }
    }

    public abstract void addContentView(View arg1, ViewGroup.LayoutParams arg2);

    boolean applyAppLocales() {
        return false;
    }

    public abstract boolean applyDayNight();

    private static void applyDayNightToActiveDelegates() {
        synchronized(AppCompatDelegate.sActivityDelegatesLock) {
            for(Object object1: AppCompatDelegate.sActivityDelegates) {
                AppCompatDelegate appCompatDelegate0 = (AppCompatDelegate)((WeakReference)object1).get();
                if(appCompatDelegate0 != null) {
                    appCompatDelegate0.applyDayNight();
                }
            }
        }
    }

    private static void applyLocalesToActiveDelegates() {
        for(Object object0: AppCompatDelegate.sActivityDelegates) {
            AppCompatDelegate appCompatDelegate0 = (AppCompatDelegate)((WeakReference)object0).get();
            if(appCompatDelegate0 != null) {
                appCompatDelegate0.applyAppLocales();
            }
        }
    }

    void asyncExecuteSyncRequestedAndStoredLocales(Context context0) {
        AppCompatDelegate.sSerialExecutorForLocalesStorage.execute(() -> if(AppCompatDelegate.isAutoStorageOptedIn(context0)) {
            if(Build.VERSION.SDK_INT < 33) {
                Object object0 = AppCompatDelegate.sAppLocalesStorageSyncLock;
                synchronized(object0) {
                    LocaleListCompat localeListCompat0 = AppCompatDelegate.sRequestedAppLocales;
                    if(localeListCompat0 == null) {
                        if(AppCompatDelegate.sStoredAppLocales == null) {
                            AppCompatDelegate.sStoredAppLocales = LocaleListCompat.forLanguageTags(AppLocalesStorageHelper.readLocales(context0));
                        }
                        if(AppCompatDelegate.sStoredAppLocales.isEmpty()) {
                            return;
                        }
                        AppCompatDelegate.sRequestedAppLocales = AppCompatDelegate.sStoredAppLocales;
                    }
                    else if(!localeListCompat0.equals(AppCompatDelegate.sStoredAppLocales)) {
                        AppCompatDelegate.sStoredAppLocales = AppCompatDelegate.sRequestedAppLocales;
                        AppLocalesStorageHelper.persistLocales(context0, AppCompatDelegate.sRequestedAppLocales.toLanguageTags());
                    }
                }
            }
            else if(!AppCompatDelegate.sIsFrameworkSyncChecked) {
                AppCompatDelegate.sSerialExecutorForLocalesStorage.execute(() -> {
                    AppCompatDelegate.syncLocalesToFramework(context0);
                    AppCompatDelegate.sIsFrameworkSyncChecked = true;
                });
            }
        });
    }

    @Deprecated
    public void attachBaseContext(Context context0) {
    }

    public Context attachBaseContext2(Context context0) {
        return context0;
    }

    public static AppCompatDelegate create(Activity activity0, AppCompatCallback appCompatCallback0) {
        return new AppCompatDelegateImpl(activity0, appCompatCallback0);
    }

    public static AppCompatDelegate create(Dialog dialog0, AppCompatCallback appCompatCallback0) {
        return new AppCompatDelegateImpl(dialog0, appCompatCallback0);
    }

    public static AppCompatDelegate create(Context context0, Activity activity0, AppCompatCallback appCompatCallback0) {
        return new AppCompatDelegateImpl(context0, activity0, appCompatCallback0);
    }

    public static AppCompatDelegate create(Context context0, Window window0, AppCompatCallback appCompatCallback0) {
        return new AppCompatDelegateImpl(context0, window0, appCompatCallback0);
    }

    public abstract View createView(View arg1, String arg2, Context arg3, AttributeSet arg4);

    public abstract View findViewById(int arg1);

    public static LocaleListCompat getApplicationLocales() {
        if(Build.VERSION.SDK_INT >= 33) {
            Object object0 = AppCompatDelegate.getLocaleManagerForApplication();
            return object0 == null ? LocaleListCompat.getEmptyLocaleList() : LocaleListCompat.wrap(Api33Impl.localeManagerGetApplicationLocales(object0));
        }
        return AppCompatDelegate.sRequestedAppLocales == null ? LocaleListCompat.getEmptyLocaleList() : AppCompatDelegate.sRequestedAppLocales;
    }

    public Context getContextForDelegate() {
        return null;
    }

    public static int getDefaultNightMode() {
        return AppCompatDelegate.sDefaultNightMode;
    }

    public abstract Delegate getDrawerToggleDelegate();

    public int getLocalNightMode() {
        return -100;
    }

    static Object getLocaleManagerForApplication() {
        for(Object object0: AppCompatDelegate.sActivityDelegates) {
            AppCompatDelegate appCompatDelegate0 = (AppCompatDelegate)((WeakReference)object0).get();
            if(appCompatDelegate0 != null) {
                Context context0 = appCompatDelegate0.getContextForDelegate();
                if(context0 != null) {
                    return context0.getSystemService("locale");
                }
                if(false) {
                    break;
                }
            }
        }
        return null;
    }

    public abstract MenuInflater getMenuInflater();

    static LocaleListCompat getRequestedAppLocales() {
        return AppCompatDelegate.sRequestedAppLocales;
    }

    static LocaleListCompat getStoredAppLocales() {
        return AppCompatDelegate.sStoredAppLocales;
    }

    public abstract ActionBar getSupportActionBar();

    public abstract boolean hasWindowFeature(int arg1);

    public abstract void installViewFactory();

    public abstract void invalidateOptionsMenu();

    static boolean isAutoStorageOptedIn(Context context0) {
        if(AppCompatDelegate.sIsAutoStoreLocalesOptedIn == null) {
            try {
                ServiceInfo serviceInfo0 = AppLocalesMetadataHolderService.getServiceInfo(context0);
                if(serviceInfo0.metaData != null) {
                    AppCompatDelegate.sIsAutoStoreLocalesOptedIn = Boolean.valueOf(serviceInfo0.metaData.getBoolean("autoStoreLocales"));
                    return AppCompatDelegate.sIsAutoStoreLocalesOptedIn.booleanValue();
                }
            }
            catch(PackageManager.NameNotFoundException unused_ex) {
                Log.d("AppCompatDelegate", "Checking for metadata for AppLocalesMetadataHolderService : Service not found");
                AppCompatDelegate.sIsAutoStoreLocalesOptedIn = Boolean.FALSE;
                return AppCompatDelegate.sIsAutoStoreLocalesOptedIn.booleanValue();
            }
        }
        return AppCompatDelegate.sIsAutoStoreLocalesOptedIn.booleanValue();
    }

    // 去混淆评级： 低(20)
    public static boolean isCompatVectorFromResourcesEnabled() {
        return false;
    }

    public abstract boolean isHandleNativeActionModesEnabled();

    // 检测为 Lambda 实现
    static void lambda$syncRequestedAndStoredLocales$1(Context context0) [...]

    public abstract void onConfigurationChanged(Configuration arg1);

    public abstract void onCreate(Bundle arg1);

    public abstract void onDestroy();

    public abstract void onPostCreate(Bundle arg1);

    public abstract void onPostResume();

    public abstract void onSaveInstanceState(Bundle arg1);

    public abstract void onStart();

    public abstract void onStop();

    static void removeActivityDelegate(AppCompatDelegate appCompatDelegate0) {
        synchronized(AppCompatDelegate.sActivityDelegatesLock) {
            AppCompatDelegate.removeDelegateFromActives(appCompatDelegate0);
        }
    }

    private static void removeDelegateFromActives(AppCompatDelegate appCompatDelegate0) {
        synchronized(AppCompatDelegate.sActivityDelegatesLock) {
            Iterator iterator0 = AppCompatDelegate.sActivityDelegates.iterator();
            while(iterator0.hasNext()) {
                Object object1 = iterator0.next();
                AppCompatDelegate appCompatDelegate1 = (AppCompatDelegate)((WeakReference)object1).get();
                if(appCompatDelegate1 == appCompatDelegate0 || appCompatDelegate1 == null) {
                    iterator0.remove();
                }
            }
        }
    }

    public abstract boolean requestWindowFeature(int arg1);

    static void resetStaticRequestedAndStoredLocales() {
        AppCompatDelegate.sRequestedAppLocales = null;
        AppCompatDelegate.sStoredAppLocales = null;
    }

    public static void setApplicationLocales(LocaleListCompat localeListCompat0) {
        Objects.requireNonNull(localeListCompat0);
        if(Build.VERSION.SDK_INT >= 33) {
            Object object0 = AppCompatDelegate.getLocaleManagerForApplication();
            if(object0 != null) {
                Api33Impl.localeManagerSetApplicationLocales(object0, Api24Impl.localeListForLanguageTags(localeListCompat0.toLanguageTags()));
            }
        }
        else if(!localeListCompat0.equals(AppCompatDelegate.sRequestedAppLocales)) {
            synchronized(AppCompatDelegate.sActivityDelegatesLock) {
                AppCompatDelegate.sRequestedAppLocales = localeListCompat0;
                AppCompatDelegate.applyLocalesToActiveDelegates();
            }
        }
    }

    public static void setCompatVectorFromResourcesEnabled(boolean z) {
        VectorEnabledTintResources.setCompatVectorFromResourcesEnabled(z);
    }

    public abstract void setContentView(int arg1);

    public abstract void setContentView(View arg1);

    public abstract void setContentView(View arg1, ViewGroup.LayoutParams arg2);

    public static void setDefaultNightMode(int v) {
        if(v != -1 && v != 0 && (v != 1 && v != 2 && v != 3)) {
            Log.d("AppCompatDelegate", "setDefaultNightMode() called with an unknown mode");
            return;
        }
        if(AppCompatDelegate.sDefaultNightMode != v) {
            AppCompatDelegate.sDefaultNightMode = v;
            AppCompatDelegate.applyDayNightToActiveDelegates();
        }
    }

    public abstract void setHandleNativeActionModesEnabled(boolean arg1);

    static void setIsAutoStoreLocalesOptedIn(boolean z) {
        AppCompatDelegate.sIsAutoStoreLocalesOptedIn = Boolean.valueOf(z);
    }

    public abstract void setLocalNightMode(int arg1);

    public void setOnBackInvokedDispatcher(OnBackInvokedDispatcher onBackInvokedDispatcher0) {
    }

    public abstract void setSupportActionBar(Toolbar arg1);

    public void setTheme(int v) {
    }

    public abstract void setTitle(CharSequence arg1);

    public abstract ActionMode startSupportActionMode(Callback arg1);

    static void syncLocalesToFramework(Context context0) {
        if(Build.VERSION.SDK_INT >= 33) {
            ComponentName componentName0 = new ComponentName(context0, "androidx.appcompat.app.AppLocalesMetadataHolderService");
            if(context0.getPackageManager().getComponentEnabledSetting(componentName0) != 1) {
                if(AppCompatDelegate.getApplicationLocales().isEmpty()) {
                    String s = AppLocalesStorageHelper.readLocales(context0);
                    Object object0 = context0.getSystemService("locale");
                    if(object0 != null) {
                        Api33Impl.localeManagerSetApplicationLocales(object0, Api24Impl.localeListForLanguageTags(s));
                    }
                }
                context0.getPackageManager().setComponentEnabledSetting(componentName0, 1, 1);
            }
        }
    }

    // 检测为 Lambda 实现
    static void syncRequestedAndStoredLocales(Context context0) [...]
}

